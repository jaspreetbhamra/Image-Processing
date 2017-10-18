package imageProcessing;
//This code will create an image from scratch. The output will be an image depicting a spider's web

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class CreateImage{

	public static String arr[];

	public static BufferedImage createImage(int dimension) {
		BufferedImage img = new BufferedImage(dimension, dimension, BufferedImage.TYPE_INT_RGB);
		int color = 0;
		for (int y = 0; y < dimension; y++) {
			img.setRGB(y, y, color);
		}
		color = 0x00ff99;
		for (int i = 0; i < dimension; ++i) {
			img.setRGB(dimension/2, i, color);    // vertical
			img.setRGB(i, dimension/2, color);    // horizontal
			img.setRGB(i, i, color);   	 // backward slash
			img.setRGB(dimension-1-i, i, color);    // forward slash
			img.setRGB(((dimension/2)+i)/2, i, color);
			img.setRGB(((dimension/2)+(dimension-1-i))/2, i, color);
			img.setRGB(i, ((dimension/2)+i)/2, color);
			img.setRGB(i, ((dimension/2)+(dimension-1-i))/2, color);
		}
		return img;
	}

	public static void main(String args[])throws IOException {
		BufferedImage img = null;
		File f = null;
		img = createImage(1024);
		try {
			f = new File("path/NewImage125.jpg");
			ImageIO.write(img, "jpg", f);
		}
		catch(IOException e) {
			System.out.println(e);
		}
	}
}
