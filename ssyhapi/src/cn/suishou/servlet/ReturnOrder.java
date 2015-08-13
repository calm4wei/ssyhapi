package cn.suishou.servlet;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;

import cn.suishou.bean.Order;
import cn.suishou.bean.ReturnOrderBean;
import cn.suishou.common.Config;
import cn.suishou.common.Enums.ActionStatus;
import cn.suishou.common.Value;
import cn.suishou.dao.OrderDAO;
import cn.suishou.dao.ReturnOrderDAO;
import cn.suishou.utils.DateUtil;
import cn.suishou.utils.NetUtils;
import cn.suishou.utils.RespStatusBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * 用户申请退款/退货API
 * @author  haol
 * @date	2014-12-31
 */
@WebServlet("/api/returnOrder")
public class ReturnOrder extends HttpServlet 
{
	private static Logger logger = Logger.getLogger(ReturnOrder.class);
	private static final long serialVersionUID = 1L;
	/** 1天时间的毫秒数 */
	private static final long ONE_DAY_STAMP = 1 * 24 * 60 * 60 * 1000;
	/** order退款状态，0：初始状态 （1：申请退款 2：退款处理中  3：退款完成） */
	private static int ORDER_RETURN_STATUS_UNRETURN = 0;
	/** server执行正常返回的状态 */
	private static final JsonObject successJson = new RespStatusBuilder(ActionStatus.NORMAL_RETURNED).toJsonObject();
	/** server执行异常返回的状态 */
	private static final JsonObject errorJson = new RespStatusBuilder(ActionStatus.SERVER_ERROR).toJsonObject();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}
	
	/** Post方法处理 用户申请退款/退货 请求。先验证订单是否可退，再执行申请操作。 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		JsonObject resultJson = new JsonObject();				// 响应结果JSON
		try {
			ReturnOrderBean bean = parseRequestToBean(request);	// 从request解析参数，封装到实体Bean
			if (canRefundOrNot(bean, resultJson)) {
				// 1.用户申请退款/退货，修改order表的return_status为 1：申请退款
				boolean updateOrderReturnStatusOK = OrderDAO.getInstance().updateOrderReturnStatus(bean.getUid(), bean.getOrderId());
				if (updateOrderReturnStatusOK) {
					// 2.新增退款/退货记录，返回新退款/退货记录ID
					long returnOrderId = ReturnOrderDAO.getReturnOrderDAO().addReturnOrder(bean);
					bean.setId(returnOrderId);
					resultJson.add("status", successJson);
				}
			}
		} catch(Exception e) {
			logger.error(e, e);
			resultJson.add("status", errorJson);
		} finally {
			response.getWriter().print(new Gson().toJson(resultJson));
		}
	}

	/**
	 * 判断当前order是否可以退款/退货，需要验证的类别：<br>
	 * (1).获取不到user要退货的order，这可能表明该订单不属于该用户，或者uid有误<br>
	 * (2).判断订单状态是否可以退款/退货，1：未支付 和  5：已退款 不可以；2：已支付 3：已发货 4：已收货 可以<br>
	 * (3).判断申请退款金额是否大于订单总金额，如是，则拒绝<br>
	 * (4).判断该笔订单是否已申请过退款/退货，如是，则拒绝<br>
	 * (5).退款时限已过，具体时长由Value.returnOrderEnableDays定义<br>
	 * @param bean			当前order对象
	 * @param resultJson	响应结果JSON
	 * @return				当前order是否可以退款/退货
	 */
	private boolean canRefundOrNot(ReturnOrderBean bean, JsonObject resultJson) 
	{
		// 1.加载user要退货的order，如果获取不到，则可能表明该订单不属于该用户，或者uid有误
		Order order = OrderDAO.getInstance().getOrder(bean.getOrderId(), bean.getUid());
		if (order == null) {
			resultJson.add("status", new RespStatusBuilder(ActionStatus.RETURN_ORDER_NULL).toJsonObject());
			return false;
		}
		// 2.判断订单状态是否可以退款/退货，1：未支付 和  5：已退款 不可以；2：已支付 3：已发货 4：已收货 可以
		if (Value.order_status_unpaid == order.getStatus() || Value.order_status_refund == order.getStatus()) {
			resultJson.add("status", new RespStatusBuilder(ActionStatus.RETURN_ORDER_STATUS).toJsonObject());
			return false;
		}
		// 3.判断申请退款金额是否大于订单总金额，如是，则拒绝
		if (bean.getRefundAmount() > order.getTotalPrice()) {
			resultJson.add("status", new RespStatusBuilder(ActionStatus.RETURN_MONEY_OUT).toJsonObject());
			return false;
		}
		// 4.判断该笔订单是否已申请过退款/退货
		if (ORDER_RETURN_STATUS_UNRETURN != order.getReturn_status()) {	// order表return_status非初始状态，则表明已申请退款
			resultJson.add("status", new RespStatusBuilder(ActionStatus.RETURN_ALREADY_EXIST).toJsonObject());
			return false;
		}
		ReturnOrderBean returnOrder = ReturnOrderDAO.getReturnOrderDAO().getByOrderId(bean.getUid(), bean.getOrderId());
		if (returnOrder != null) {
			resultJson.add("status", new RespStatusBuilder(ActionStatus.RETURN_ALREADY_EXIST).toJsonObject());
			return false;
		}
		// 5.判断退款时限是否已过，自支付时间开始算起
		String payTimeStr = order.getPayTime();	// 格式：yyyy-MM-dd HH:mm:ss
		if (StringUtils.isNotBlank(payTimeStr)) {
			long payTime = DateUtil.strToStamp(payTimeStr);
			if (System.currentTimeMillis() - payTime > Value.returnOrderEnableDays * ONE_DAY_STAMP) {
				resultJson.add("status", new RespStatusBuilder(ActionStatus.RETURN_OUT_DATE).toJsonObject());
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 从request解析参数，封装到实体Bean
	 * @return	实体Bean
	 */
	@SuppressWarnings("unchecked")
	private ReturnOrderBean parseRequestToBean(HttpServletRequest request) 
	{
		String uid  = "";
		String orderId = "";
		int isReceived = -1;
		int reason = -1;
		double refundAmount = 0;
		String remark = "";
		String img1 = "", img2 = "", img3 = "", img4 = "";	// 退货凭证图片，最多4张
		
		try {
    		DiskFileItemFactory disk = new DiskFileItemFactory();
    		if (ServletFileUpload.isMultipartContent(request)) {
				ServletFileUpload up = new ServletFileUpload(disk);
				List<FileItem> fileItems = up.parseRequest(request);
				
				for (FileItem fileItem : fileItems) {
					if (fileItem.isFormField()) {
						if("uid".equals(fileItem.getFieldName())) {
							uid = fileItem.getString();					
						} else if ("orderId".equals(fileItem.getFieldName())) {
							orderId = fileItem.getString();
						} else if ("isReceived".equals(fileItem.getFieldName())) {
							isReceived = NumberUtils.isNumber(fileItem.getString()) ? Integer.parseInt(fileItem.getString()) : -1;
						} else if ("reason".equals(fileItem.getFieldName())) {
							reason = NumberUtils.isNumber(fileItem.getString()) ? Integer.parseInt(fileItem.getString()) : -1;
						} else if ("refundAmount".equals(fileItem.getFieldName())) {
							refundAmount = NumberUtils.isNumber(fileItem.getString()) ? Double.parseDouble(fileItem.getString()) : -1;
						} else if ("remark".equals(fileItem.getFieldName())) {
							remark = fileItem.getString();
							if (StringUtils.isNotBlank(remark)) {
								remark = new String(remark.getBytes("iso-8859-1"), "utf-8");
							}
						}
					} else {
						// 用户选择了图片则上传，文件域为空则不上传
						if ("img1".equals(fileItem.getFieldName()) && StringUtils.isNotBlank(fileItem.getName())) {
							img1 = uploadImgItem(fileItem, "img1");
						} else if ("img2".equals(fileItem.getFieldName()) && StringUtils.isNotBlank(fileItem.getName())) {
							img2 = uploadImgItem(fileItem, "img2");
						} else if ("img3".equals(fileItem.getFieldName()) && StringUtils.isNotBlank(fileItem.getName())) {
							img3 = uploadImgItem(fileItem, "img3");
						} else if ("img4".equals(fileItem.getFieldName()) && StringUtils.isNotBlank(fileItem.getName())) {
							img4 = uploadImgItem(fileItem, "img4");
						}
					}
				}
    		}
		} catch (Exception e) {
			logger.error("parseRequestToBean error stack", e);
		}
		ReturnOrderBean bean = new ReturnOrderBean(uid, orderId, isReceived, reason, refundAmount, remark, img1, img2, img3, img4);
		return bean;
	}
	
	/**
	 * 传入文件上传控件和控件name，上传图片到imgcenter服务器，并返回图片在imgcenter的URL
	 * @param fileItem		文件上传控件
	 * @param imgFieldName	控件name
	 * @return	上传图片在imgcenter的URL
	 * @throws IOException	读取文件流异常
	 */
	private String uploadImgItem(FileItem fileItem, String imgFieldName) throws IOException
	{
		String imgUrl = "";		// 上传到imgcenter服务器的图片路径
		if (!imgFieldName.equals(fileItem.getFieldName())) {
			return imgUrl;
		}
		Map<String, String> fileNameParams = new HashMap<String, String>();		// 上传图片文件名map
		Map<String, FileItem> fileParams = new HashMap<String, FileItem>(); // 保存图片流
//		BufferedImage image = ImageIO.read(fileItem.getInputStream());
//		image = ImageManager.cutAndResizeImg(image, 100);
		fileNameParams.put("fileName", fileItem.getName());
		fileParams.put("upFile", fileItem);
		String result = NetUtils.doPostWithFile(Config.IMG_CENTER_URL + "/img/operation", fileNameParams, fileParams, null);					
		JSONObject obj = JSONObject.fromObject(result);
		imgUrl = obj.getString("url");
		return imgUrl;
	}
	
	private static String uploadBufferedImgToImgCenter(BufferedImage image)
	{
		Map<String, String> params = new HashMap<String, String>();
		Map<String, BufferedImage> fileParams = new HashMap<String, BufferedImage>();
		Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("X-Forwarded-For", "10.0.0.29");
//		headerMap.put("X-Real-IP", "10.10.33.229");
//		image = ImageManager.cutAndResizeImg(image, 100);
		params.put("fileName", "picName.jpg");
		fileParams.put("upFile", image);
//		String imgCenterUrl = Config.IMG_CENTER_URL;
		String imgCenterUrl = "http://service.suishouyouhui.com/imgcenter";
		String result = NetUtils.doPostWithFile2(imgCenterUrl + "/img/operation", params, fileParams, headerMap);					
		JSONObject obj = JSONObject.fromObject(result);
		String url = obj.getString("url");
		System.out.println("Uploaded image url is :" + url);
		return url;
	}
	
	public static void main(String[] args)
	{
		try {
			int x = 0;
			int y = 0;
			int sideLength = 0;
			int finalSideLength = 200;		
		
			BufferedImage sourceImg = ImageIO.read(new File("H:\\Demo_Pics\\IMG_2938.JPG"));
			ReturnOrder.uploadBufferedImgToImgCenter(sourceImg);

			int srcWidth = sourceImg.getWidth(); 
			int srcHeight = sourceImg.getHeight(); 
			
			if(srcHeight>srcWidth){
				y = (srcHeight - srcWidth) / 2;
				sideLength = srcWidth;
			}else{
				x = (srcWidth - srcHeight) / 2;
				sideLength = srcHeight;
			}
			
			ImageFilter imageFilter = new CropImageFilter(x, y, sideLength, sideLength);
			Image scaledInstanceImg=sourceImg.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
			Image img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(scaledInstanceImg.getSource(), imageFilter));				
		    BufferedImage tmpImg = new BufferedImage(sideLength,sideLength,BufferedImage.TYPE_INT_RGB);
		     
		    Graphics2D g = tmpImg.createGraphics();
		    g.drawImage(img,0,0,null);		   
		    g.dispose();
		    
		    ImageIO.write(tmpImg,"jpg", new File("H:\\new1.jpg"));
		    
		    ImageFilter imageFilter1 = new CropImageFilter(0, 0, finalSideLength, finalSideLength);
		    Image scaledInstanceImg1=tmpImg.getScaledInstance(finalSideLength, finalSideLength, Image.SCALE_DEFAULT);
		    Image img1 = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(scaledInstanceImg1.getSource(), imageFilter1));				
		    BufferedImage finalImg = new BufferedImage(finalSideLength,finalSideLength,BufferedImage.TYPE_INT_RGB); 
		    
		    Graphics2D g1 = finalImg.createGraphics();
		    g1.drawImage(img1,0,0,null);		   
		    g1.dispose();
		   
		    ImageIO.write(finalImg,"jpg", new File("H:\\new2.jpg"));
	   
		} catch (IOException e) {
		}
	}
	
}
