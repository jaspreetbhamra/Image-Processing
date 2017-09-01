package imageProcessing;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
 
public class HistogramEqualizationTry2
{
	public static void main(String args[]) throws IOException
	{
    	BufferedImage img = null;
    	File f = null;
    	int redNk[] = new int[256];
    	int blueNk[] = new int[256];
    	int greenNk[] = new int[256];      
      int n = 0;
    	try{
        	f = new File("/home/itlab/Desktop/IP/Water.jpeg");
        	img = ImageIO.read(f);
    	} catch(IOException e){
        	System.out.println(e);
    	}
 
    	int width = img.getWidth();
    	int height = img.getHeight();
    	double pdfRed[] = new double[256];
    	double pdfBlue[] = new double[256];
    	double pdfGreen[] = new double[256];
    	for (int y = 0; y < height; y++)
    	{
        	for (int x = 0; x < width; x++)
        	{   
            Color p = new Color(img.getRGB(x, y));
            int r = (int)(p.getRed());
            int g = (int)(p.getGreen());
            int b = (int)(p.getBlue());
            int a = p.getAlpha();
            redNk[r]++;
            blueNk[b]++;
            greenNk[g]++;
            //pdf[temp]++;
            ++n;
        	}
    	}
    	
    	for(int i=0; i<redNk.length; ++i){
    	   pdfRed[i] = (double)redNk[i]/(double)n;
    	   pdfBlue[i] = (double)blueNk[i]/(double)n;
    	   pdfGreen[i] = (double)greenNk[i]/(double)n;
    	}
    	
    	double cdfRed[] = new double[256];
    	double cdfBlue[] = new double[256];
    	double cdfGreen[] = new double[256];
    	cdfRed[0] = pdfRed[0];
    	cdfBlue[0] = pdfBlue[0];
    	cdfGreen[0] = pdfGreen[0];
    	
    	for(int i=1; i<redNk.length; ++i){
    	   cdfRed[i] = cdfRed[i-1] + pdfRed[i];
    	   cdfBlue[i] = cdfBlue[i-1] + pdfBlue[i];
    	   cdfGreen[i] = cdfGreen[i-1] + pdfGreen[i];
    	}
    	
    	int newGreyLevelsRed[] = new int[256];
    	int newGreyLevelsBlue[] = new int[256];
    	int newGreyLevelsGreen[] = new int[256];
    	
    	for(int i=0; i<redNk.length; ++i){
    	   newGreyLevelsRed[i] = (int)Math.round(cdfRed[i]*255);
    	   newGreyLevelsBlue[i] = (int)Math.round(cdfBlue[i]*255);
    	   newGreyLevelsGreen[i] = (int)Math.round(cdfGreen[i]*255);
    	}
    	
    	int newNkRed[] = new int[256];
    	int newNkBlue[] = new int[256];
    	int newNkGreen[] = new int[256];
    	
    	for(int i=0; i<redNk.length; ++i){
    	   newNkRed[newGreyLevelsRed[i]] += redNk[i];
    	   newNkBlue[newGreyLevelsBlue[i]] += blueNk[i];
    	   newNkGreen[newGreyLevelsGreen[i]] += greenNk[i];
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
            if(newNkRed[r]==0){
               r = newGreyLevelsRed[r];
            }
            if(newNkBlue[b]==0){
               b = newGreyLevelsBlue[b];
            }
            if(newNkRed[g]==0){
               g = newGreyLevelsBlue[g];
            }
            Color greyScaleLevel = new Color(r, b, g);
            img.setRGB(y, x, greyScaleLevel.getRGB());
    	   }
    	}
    	
    	try{
        	f = new File("/home/itlab/Desktop/IP/Water_Changed.jpeg");
        	ImageIO.write(img, "jpg", f);
    	} catch(IOException e) {
        	System.out.println(e);
    	}
	}
}

