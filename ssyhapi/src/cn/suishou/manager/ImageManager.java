package cn.suishou.manager;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;

import org.apache.log4j.Logger;


public class ImageManager {
	protected static Logger logger = Logger.getLogger(ImageManager.class);
	
	public static BufferedImage cutAndResizeImg(BufferedImage sourceImg, int finalSideLength){
		try{
			int x=0;
			int y=0;
			int sideLength=0;				

			int srcWidth = sourceImg.getWidth(); 
			int srcHeight = sourceImg.getHeight(); 
			
			if(srcHeight>srcWidth){
				y = (srcHeight - srcWidth) / 2;
				sideLength = srcWidth;
			}else{
				x = (srcWidth - srcHeight) / 2;
				sideLength = srcHeight;
			}
			
			//cut
			ImageFilter imageFilter = new CropImageFilter(x, y, sideLength, sideLength);
			Image scaledInstanceImg=sourceImg.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
			Image img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(scaledInstanceImg.getSource(), imageFilter));				
		    BufferedImage tmpImg = new BufferedImage(sideLength,sideLength,BufferedImage.TYPE_INT_RGB);
		     
		    Graphics2D g = tmpImg.createGraphics();
		    g.drawImage(img,0,0,null);		   
		    g.dispose();		    
	
		    //resize
		    ImageFilter imageFilter1 = new CropImageFilter(0, 0, finalSideLength, finalSideLength);
		    Image scaledInstanceImg1=tmpImg.getScaledInstance(finalSideLength, finalSideLength, Image.SCALE_DEFAULT);
		    Image img1 = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(scaledInstanceImg1.getSource(), imageFilter1));				
		    BufferedImage finalImg = new BufferedImage(finalSideLength,finalSideLength,BufferedImage.TYPE_INT_RGB); 

		    Graphics2D g1 = finalImg.createGraphics();
		    g1.drawImage(img1,0,0,null);		   
		    g1.dispose();
		   
		    return finalImg;
		  
		}catch(Exception e){
			logger.error("error stack", e);
			return null;
		}

	}
	
	
}
