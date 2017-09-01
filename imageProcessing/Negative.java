package imageProcessing;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
 
public class Negative
{
	public static void main(String args[])throws IOException
	{
    	BufferedImage img = null;
    	File f = null;
 
    	// read image
    	try
    	{
        	f = new File("path.jpeg");
        	img = ImageIO.read(f);
    	}
    	catch(IOException e)
    	{
        	System.out.println(e);
    	}
 
    	// Get image width and height
    	int width = img.getWidth();
    	int height = img.getHeight();
 
    	// Convert to negative
    	for (int y = 0; y < height; y++)
    	{
        	for (int x = 0; x < width; x++)
        	{   
            	Color p = new Color(img.getRGB(x, y));
            	int r = p.getRed();
            	int g = p.getGreen();
            	int b = p.getBlue();
            	int a = p.getAlpha();
 
            	//subtract RGB from 255
            	r = 255 - r;
            	g = 255 - g;
            	b = 255 - b;
 
            	//set new RGB value
            	int changed = (a<<24) | (r<<16) | (g<<8) | b;
            	img.setRGB(x, y, changed);
        	}
    	}
 
    	// write image
    	try
    	{
        	f = new File("path.jpeg");
        	ImageIO.write(img, "jpg", f);
    	}
    	catch(IOException e)
    	{
        	System.out.println(e);
    	}
	}
}
