<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="com.tenpay.RequestHandler" %>
<%@ page import="com.tenpay.ResponseHandler" %>   
<%@ page import="com.tenpay.client.ClientResponseHandler" %>    
<%@ page import="com.tenpay.client.TenpayHttpClient" %>
<%@ page import="cn.suishou.dao.*"%> 
<%@ page import="cn.suishou.manager.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
//---------------------------------------------------------
//�Ƹ�֧ͨ��֪ͨ����̨֪ͨ��ʾ�����̻����մ��ĵ����п�������
//---------------------------------------------------------
//�̻���
String partner = "1225255901";

//��Կ
String key = "606d514c0241e03274d71856622a7dbf";

//����֧��Ӧ�����
ResponseHandler resHandler = new ResponseHandler(request, response);
resHandler.setKey(key);

//�ж�ǩ��
if(resHandler.isTenpaySign()) {
	
	//֪ͨid
	String notify_id = resHandler.getParameter("notify_id");
	
	//�����������
	RequestHandler queryReq = new RequestHandler(null, null);
	
	//ͨ�Ŷ���
	TenpayHttpClient httpClient = new TenpayHttpClient();
	//Ӧ�����
	ClientResponseHandler queryRes = new ClientResponseHandler();
	
	//ͨ��֪ͨID��ѯ��ȷ��֪ͨ�����Ƹ�ͨ
	queryReq.init();
	queryReq.setKey(key);
	queryReq.setGateUrl("https://gw.tenpay.com/gateway/verifynotifyid.xml");
	queryReq.setParameter("partner", partner);
	queryReq.setParameter("notify_id", notify_id);
	
	//ͨ�Ŷ���
	httpClient.setTimeOut(5);
	//������������
	httpClient.setReqContent(queryReq.getRequestURL());
	System.out.println("queryReq:" + queryReq.getRequestURL());
	//��̨����
	if(httpClient.call()) {
		//���ý������
		queryRes.setContent(httpClient.getResContent());
		System.out.println("queryRes:" + httpClient.getResContent());
		queryRes.setKey(key);
			
			
		//��ȡ���ز���
		String retcode = queryRes.getParameter("retcode");
		String trade_state = queryRes.getParameter("trade_state");	
		String trade_mode = queryRes.getParameter("trade_mode");
		String out_trade_no = queryRes.getParameter("out_trade_no");
		String transaction_id = queryRes.getParameter("transaction_id");
		String total_fee = queryRes.getParameter("total_fee");
		//�ж�ǩ�������
		if(queryRes.isTenpaySign()&& "0".equals(retcode) && "0".equals(trade_state) && "1".equals(trade_mode)) {
			System.out.println("������ѯ�ɹ�");
			//ȡ���������ҵ����				
			System.out.println("out_trade_no:" + queryRes.getParameter("out_trade_no")+
					" transaction_id:" + queryRes.getParameter("transaction_id"));
			System.out.println("trade_state:" + queryRes.getParameter("trade_state")+
					" total_fee:" + queryRes.getParameter("total_fee"));
		        //�����ʹ���ۿ�ȯ��discount��ֵ��total_fee+discount=ԭ�����total_fee
			System.out.println("discount:" + queryRes.getParameter("discount")+
					" time_end:" + queryRes.getParameter("time_end"));
			//------------------------------
			//����ҵ��ʼ
			//------------------------------

			String[] orderIds = OrderDAO.getInstance().getOrderIdsFromPaymentToOrder(out_trade_no);
			OrderDAO.getInstance().updateOrderPayStatus(orderIds, transaction_id);
			OrderDAO.getInstance().updateJfPayStatus(orderIds);
			
			OrderManager.updateStock(orderIds);
			OrderManager.updateUserPurchaseNum(orderIds);
			//------------------------------
			//����ҵ�����
			//------------------------------
			resHandler.sendToCFT("Success");
		}
		else{
				//����ʱ�����ؽ��δǩ������¼retcode��retmsg��ʧ�����顣
				System.out.println("��ѯ��֤ǩ��ʧ�ܻ�ҵ�����");
				System.out.println("retcode:" + queryRes.getParameter("retcode")+
						" retmsg:" + queryRes.getParameter("retmsg"));
		}
	
	} else {

		System.out.println("��̨����ͨ��ʧ��");
			
		System.out.println(httpClient.getResponseCode());
		System.out.println(httpClient.getErrInfo());
		//�п�����Ϊ����ԭ�������Ѿ���������δ�յ�Ӧ��
	}
}
else{
	System.out.println("֪ͨǩ����֤ʧ��");
}

%>
