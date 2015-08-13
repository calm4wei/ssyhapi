package cn.suishou.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.fileupload.FileItem;

public class NetUtils {
	private static  String METHOD_GET = "GET";
	private static  String METHOD_POST = "POST";
	private static final int DEFAULT_CONNECT_TIMEOUT = 3000;//
	private static final int DEFAULT_READ_TIMEOUT = 15000;//
	
	private static  String CHARSET = "UTF-8";
	
	public static String upload(String url, String cType, Map<String, String> map){
		String content = "";
		try {
			HttpURLConnection conn = getHttpConn(url, METHOD_GET, cType, map);
					
			InputStream in = conn.getInputStream();
			byte[] bytes = new byte[1024];
			int b = 0;
			while ((b = in.read()) != -1){
				in.read(bytes, 0, b);
			}
			content = getContent(bytes);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}
	
	public static String getContent(byte[] bytes) throws UnsupportedEncodingException{
		return new String(bytes,"UTF-8");
	}
	
	public static HttpURLConnection getHttpConn(String _url, String method, String cType, Map<String, String> headerMap){
		HttpURLConnection conn = null; 
		try {
			URL url = new URL(_url);
			conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod(method);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type", cType);
			conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.120 Safari/537.36");
			
			if (null != headerMap){
				Set<Entry<String, String>> setEntry = headerMap.entrySet();
				for (Map.Entry<String, String> entry : setEntry){
					conn.setRequestProperty(entry.getKey(), entry.getValue().toString());
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static String doPostWithFile(String url, Map<String, String> params, Map<String, FileItem> fileParams,
			 Map<String, String> headerMap){
		HttpURLConnection conn = null;
		OutputStream out = null;
		String rsp = "";
		String boundary = System.currentTimeMillis() + "";
		// 设置文件请求头
//		String cType = "multipart/form-data;charset=" + CHARSET + ";boundary=" + boundary;
		String cType = "multipart/form-data;boundary=" + boundary;
		
		conn = getHttpConn(url, METHOD_POST, cType, headerMap);
		conn.setReadTimeout(DEFAULT_READ_TIMEOUT);
		conn.setConnectTimeout(DEFAULT_CONNECT_TIMEOUT);
		
		try {
			out = conn.getOutputStream();

			byte[] entryBoundaryBytes = ("\r\n--" + boundary + "\r\n").getBytes(CHARSET);
			// 上传文件头信息
//			Set<Entry<String, String>> textEntrySet = params.entrySet();
//			for (Entry<String, String> textEntry : textEntrySet) {
//				byte[] textBytes = getTextEntry(textEntry.getKey(), textEntry.getValue(), CHARSET);
//				out.write(entryBoundaryBytes);
//				out.write(textBytes);
//			}
			
			// 上传文件内容
			Set<Entry<String, FileItem>> fileEntrySet = fileParams.entrySet();
			for (Entry<String, FileItem> fileEntry : fileEntrySet) {
				FileItem fileItem = fileEntry.getValue();
				if (fileItem.getInputStream() == null) {
					continue;
				}
				byte[] fileBytes = getFileEntry(fileEntry.getKey(), fileItem.getName(), fileItem.getContentType(), CHARSET);
				out.write(entryBoundaryBytes);
				out.write(fileBytes);
				out.write(getContentBytes(fileItem));
//				InputStream in = fileItem.getInputStream();
//				byte[] bytes = new byte[in.available()];
//				int b = 0;
//				while((b=in.read()) != -1){
//					in.read(bytes, 0, b);
//				}
//				out.write(bytes);
				
				
			}
			
			// 设置结束标石
			byte[] endBoundaryBytes = ("\r\n--" + boundary + "--\r\n").getBytes(CHARSET);
			out.write(endBoundaryBytes);
			rsp = getResponseAsString(conn);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return rsp;
	}
	
	public static byte[] getContentBytes(FileItem fileItem){
		byte[] content = null;
		ByteArrayOutputStream out = null;
		try {
			InputStream in = fileItem.getInputStream();
			out = new ByteArrayOutputStream();
			int ch = 0;
			while ((ch = in.read()) != -1) {
				out.write(ch);
			}
			content = out.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != out){
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return content;
	}	
	
	public static String doPostWithFile2(String url, Map<String, String> params, Map<String, BufferedImage> fileParams, Map<String, String> headerMap){
		HttpURLConnection conn = null;
		OutputStream out = null;
		String rsp = "";
		String boundary = System.currentTimeMillis() + "";
		// 设置文件请求头
		String cType = "multipart/form-data;boundary=" + boundary;
		
		conn = getHttpConn(url, METHOD_POST, cType, headerMap);
		conn.setReadTimeout(DEFAULT_READ_TIMEOUT);
		conn.setConnectTimeout(DEFAULT_CONNECT_TIMEOUT);
		
		try {
			out = conn.getOutputStream();

			byte[] entryBoundaryBytes = ("\r\n--" + boundary + "\r\n").getBytes(CHARSET);
			
			// 上传文件内容
			Set<Entry<String, BufferedImage>> fileEntrySet = fileParams.entrySet();
			for (Entry<String, BufferedImage> fileEntry : fileEntrySet) {
				BufferedImage bfi = fileEntry.getValue();
				if (ImgUtil.getInputStream(bfi) == null) {
					continue;
				}
				byte[] fileBytes = getFileEntry(fileEntry.getKey(), params.get("fileName"), "image/jpg", CHARSET);
				out.write(entryBoundaryBytes);
				out.write(fileBytes);
				out.write(getContentBytes(bfi));
			}
			
			// 设置结束标石
			byte[] endBoundaryBytes = ("\r\n--" + boundary + "--\r\n").getBytes(CHARSET);
			out.write(endBoundaryBytes);
			rsp = getResponseAsString(conn);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return rsp;
	}
	
	
	
	public static byte[] getContentBytes(BufferedImage bfi){
		byte[] content = null;
		ByteArrayOutputStream out = null;
		try {
			out = new ByteArrayOutputStream();
			InputStream in =ImgUtil.getInputStream(bfi);			
			int ch = 0;
			while ((ch = in.read()) != -1) {
				out.write(ch);
			}
			content = out.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != out){
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return content;
	}
	
	private static byte[] getFileEntry(String fieldName, String fileName, String mimeType, String charset)
			throws IOException {
		StringBuilder entry = new StringBuilder();
		entry.append("Content-Disposition:form-data;name=\"");
		entry.append(fieldName);
		entry.append("\";filename=\"");
		entry.append(fileName);
		entry.append("\"\r\nContent-Type:");
		entry.append("image/jpg");
		entry.append("\r\n\r\n");
		return entry.toString().getBytes(charset);
	}
	
	protected static String getResponseAsString(HttpURLConnection conn) throws IOException {
		String charset = CHARSET;
		InputStream es = conn.getErrorStream();
		if (es == null) {
			return getStreamAsString(conn.getInputStream(), charset);
		} else {
			String msg = getStreamAsString(es, charset);
			if (StringUtil.isEmpty(msg)) {
				throw new IOException(conn.getResponseCode() + ":" + conn.getResponseMessage());
			} else {
				throw new IOException(msg);
			}
		}
	}
	
	private static String getStreamAsString(InputStream stream, String charset) throws IOException {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charset));
			StringWriter writer = new StringWriter();

			char[] chars = new char[256];
			int count = 0;
			while ((count = reader.read(chars)) > 0) {
				writer.write(chars, 0, count);
			}

			return writer.toString();
		} finally {
			if (stream != null) {
				stream.close();
			}
		}
	}
}
