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
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cn.suishou.common.Config;
import cn.suishou.common.Enums.ActionStatus;
import cn.suishou.dao.UserDAO;
import cn.suishou.manager.ImageManager;
import cn.suishou.utils.NetUtils;
import cn.suishou.utils.RespStatusBuilder;

@WebServlet("/api/setUserIcon")
public class SetUserIcon extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(SetUserIcon.class);
	
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();		
		String uid = "";	
		String url = "";
		Map<String,String> params = new HashMap<String, String>();	
		Map<String,BufferedImage> fileParams = new HashMap<String, BufferedImage>();		
		try {
    		DiskFileItemFactory disk = new DiskFileItemFactory();
    		if (ServletFileUpload.isMultipartContent(request)){    		
				ServletFileUpload up = new ServletFileUpload(disk);
				List<FileItem> fileItems = up.parseRequest(request);				
				for (FileItem fileItem : fileItems){				
					if (fileItem.isFormField()){						
						if("uid".equals(fileItem.getFieldName())){
							uid = fileItem.getString();					
						}
					}else{						
						BufferedImage image = ImageIO.read(fileItem.getInputStream());	
						image = ImageManager.cutAndResizeImg(image, 100);
						params.put("fileName", fileItem.getName());
						fileParams.put("upFile", image);
						String result = NetUtils.doPostWithFile2(Config.IMG_CENTER_URL+"/img/operation", params, fileParams, null);					
						JSONObject obj = JSONObject.fromObject(result);
						url = obj.getString("url");
					}
				}
    		}
    		if(!"".equals(url)){
    			UserDAO.getInstance().updateUserIcon(url, uid);
    			retMap.put("url", url);
    			retMap.put("status", new RespStatusBuilder(ActionStatus.NORMAL_RETURNED));	
    		}else{
    			retMap.put("status", new RespStatusBuilder(ActionStatus.SERVER_ERROR));
    		}
    		response.getWriter().print(gson.toJson(retMap));
		} catch (Exception e) {
			logger.error("error stack",e);
			retMap.put("status", new RespStatusBuilder(ActionStatus.SERVER_ERROR));			
			response.getWriter().print(gson.toJson(retMap));
		}
	}
	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	public static void main(String[] args){
		try {
			int x=0;
			int y=0;
			int sideLength=0;
			int finalSideLength = 200;		
		
			BufferedImage sourceImg = ImageIO.read(new File("D:\\war\\top3.jpg"));

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
		    
		    ImageIO.write(tmpImg,"jpg", new File("D:\\war\\new1.jpg"));
		    
		    ImageFilter imageFilter1 = new CropImageFilter(0, 0, finalSideLength, finalSideLength);
		    Image scaledInstanceImg1=tmpImg.getScaledInstance(finalSideLength, finalSideLength, Image.SCALE_DEFAULT);
		    Image img1 = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(scaledInstanceImg1.getSource(), imageFilter1));				
		    BufferedImage finalImg = new BufferedImage(finalSideLength,finalSideLength,BufferedImage.TYPE_INT_RGB); 

		    Graphics2D g1 = finalImg.createGraphics();
		    g1.drawImage(img1,0,0,null);		   
		    g1.dispose();
		   
		    ImageIO.write(finalImg,"jpg", new File("D:\\war\\new2.jpg"));
	   
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
