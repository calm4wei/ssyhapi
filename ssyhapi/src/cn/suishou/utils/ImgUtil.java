package cn.suishou.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;


public class ImgUtil {
	public static InputStream getInputStream(BufferedImage bfi){	
		try {	
			ByteArrayOutputStream bs =new ByteArrayOutputStream();
			ImageOutputStream imOut =ImageIO.createImageOutputStream(bs);
			ImageIO.write(bfi,"jpg",imOut); //scaledImage1为BufferedImage，jpg为图像的类型
			InputStream in =new ByteArrayInputStream(bs.toByteArray());
			return in;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} 
	}
	


	
}
