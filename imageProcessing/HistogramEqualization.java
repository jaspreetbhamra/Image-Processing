package imageProcessing;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
 
public class HistogramEqualization
{
	public static void main(String args[]) throws IOException
	{
    	BufferedImage img = null;
    	File f = null;
    	int nk[] = new int[256];       //Array to store greyscale
      int n = 0;
    	try{
        	f = new File("path.jpeg");
        	img = ImageIO.read(f);
    	} catch(IOException e){
        	System.out.println(e);
    	}
 
    	int width = img.getWidth();
    	int height = img.getHeight();
    	double pdf[] = new double[256];
    	for (int y = 0; y < height; y++)
    	{
        	for (int x = 0; x < width; x++)
        	{   
            Color p = new Color(img.getRGB(x, y));
            int r = (int)(p.getRed()*0.299);
            int g = (int)(p.getGreen()*0.587);
            int b = (int)(p.getBlue()*0.114);
            int a = p.getAlpha();
            int greyLevel = r+g+b;
            //int temp = img.getRGB(x, y);
            nk[greyLevel]++;
            //pdf[temp]++;
            ++n;
            //System.out.println(greyLevel);
            Color greyScaleLevel = new Color(r+g+b, r+g+b, r+g+b);
            img.setRGB(x, y, greyScaleLevel.getRGB());
        	}
    	}
    	
    	System.out.println(n);
    	
    	for(int i=0; i<nk.length; ++i){
    	   pdf[i] = (double)nk[i]/(double)n;
    	}
    	double cdf[] = new double[256];
    	cdf[0] = pdf[0];
    	for(int i=1; i<nk.length; ++i){
    	   cdf[i] = cdf[i-1] + pdf[i];
    	}
    	int newGreyLevels[] = new int[256];
    	for(int i=0; i<nk.length; ++i){
    	   newGreyLevels[i] = (int)Math.round(cdf[i]*255);
    	}
    	
    	int newNk[] = new int[256];
    	for(int i=0; i<nk.length; ++i){
    	   newNk[newGreyLevels[i]] += nk[i];
    	}
    	
    	/*for(int i=0; i<newNk.length; ++i){
    	   System.out.println(newGreyLevels[i] + "\t" + newNk[i]);
    	   System.out.println(newGreyLevels[i]);
    	}*/
    	
    	for(int x = 0; x<height; ++x){
    	   for(int y = 0; y<width; ++y){
    	      Color p = new Color(img.getRGB(y, x));
            int r = (int)(p.getRed()*0.299);
            int g = (int)(p.getGreen()*0.587);
            int b = (int)(p.getBlue()*0.114);
            int a = p.getAlpha();
            int greyLevel = r+g+b;
            if(newNk[greyLevel]==0){
               greyLevel = newGreyLevels[greyLevel];
            }
            Color greyScaleLevel = new Color(greyLevel, greyLevel, greyLevel);
            img.setRGB(y, x, greyScaleLevel.getRGB());
    	   }
    	}
    	
    	try{
        	f = new File("path.jpeg");
        	ImageIO.write(img, "jpg", f);
    	} catch(IOException e) {
        	System.out.println(e);
    	}
	}
}

