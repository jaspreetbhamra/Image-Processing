package imageProcessing;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.Scanner;

public class GreyLevelSlicing
{
	public static void main(String args[])throws IOException
	{
		BufferedImage img = null;
		File f = null;
		System.out.println("Enter the color range");
		int low = new Scanner(System.in).nextInt();
		int high = new Scanner(System.in).nextInt();
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

				if(r>low && r<high){
					r = 255;
				} else
					r = 0;

				if(b>low && b<high){
					b = 255;
				} else
					b = 0;

				if(g>low && g<high){
					g = 255;
				} else
					g = 0;

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
