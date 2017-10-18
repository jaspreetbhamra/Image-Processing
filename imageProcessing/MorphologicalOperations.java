package imageProcessing;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.Scanner;

public class MorphologicalOperations {

	public static BufferedImage img;
	public static int width, height;

	public static BufferedImage dilation(int[][] se, int originX, int originY) {
		for(int x = 0; x < width-1; ++x) {
			for(int y = 0; y < height-1; ++y) {
				//a b
				//c d
				int a = img.getRGB(x, y);
				int b = img.getRGB(x, y+1);
				int c = img.getRGB(x+1, y);
				int d = img.getRGB(x+1, y+1);
				if(a == se[0][0] || b == se[0][1] || c == se[1][0] || d == se[1][1]) {
					img.setRGB(x, y, -1);
				} else {
					img.setRGB(x, y, -16777216);
				}
			}
		}
		return img;
	}

	public static BufferedImage erosion(int[][] se, int originX, int originY) {
		for(int x = 0; x < width-1; ++x) {
			for(int y = 0; y < height-1; ++y) {
				//a b
				//c d
				int a = img.getRGB(x, y);
				int b = img.getRGB(x, y+1);
				int c = img.getRGB(x+1, y);
				int d = img.getRGB(x+1, y+1);
				if(a == se[0][0] && b == se[0][1] && c == se[1][0] && d == se[1][1]) {
					img.setRGB(x, y, -1);
				} else {
					img.setRGB(x, y, -16777216);
				}
			}
		}
		return img;
	}

	public static void main(String args[])throws IOException {

		Scanner sc = new Scanner(System.in);

		File f = null;

		// read image
		try
		{
			f = new File("path/images.jpg");
			img = ImageIO.read(f);
		}
		catch(IOException e)
		{
			System.out.println(e);
		}

		int se[][] = new int[2][2];

		se[0][0] = -1;
		se[0][1] = -1;
		se[1][0] = -1;
		se[1][1] = -1;
		int originX = 0; int originY = 0;

		// Get image width and height
		width = img.getWidth();
		height = img.getHeight();

		System.out.println("Which operation?");
		int what = sc.nextInt();

		switch(what) {
			case 1: img = dilation(se, originX, originY);
				break;
			case 2: img = erosion(se, originX, originY);
				break;
			case 3: img = erosion(se, originX, originY);
				img = dilation(se, originX, originY);
				break;
			case 4: img = dilation(se, originX, originY);
				img = erosion(se, originX, originY);
				break;
		}

		try
		{
			f = new File("path/newImage.jpg");
			ImageIO.write(img, "jpg", f);
		}
		catch(IOException e)
		{
			System.out.println(e);
		}


	}
}
