package imageProcessing.rle_compression_and_decompression;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class Decompression {

	public static BufferedImage decompressImage(int width, int height, String compressedString) {
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		int countForWidth = 0, countForHeight = 0;
		int x = 0, y = 0;
		String input[] = compressedString.split(",");
		for(int i = 0; i < input.length; ++i) {
			String current[] = input[i].split("/");
			int count = Integer.parseInt(current[0]);
			int value = Integer.parseInt(current[1]);
			System.out.println(value);
			for(int j = 0; j < count; ++j) {
				if(x >= width) {
					y++;
					x = 0;
				}
				img.setRGB(x, y, value);
				x++;
			}
		}
		return img;
	}

	public static void main(String args[]) {
		File f = null;
		String arr[] = null;
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("path/compressedImage"));
			arr = (String[]) ois.readObject();
			ois.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		BufferedImage img = decompressImage(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]), arr[2]);
		try {
			f = new File("path/newImage.png");
			ImageIO.write(img, "png", f);
		}
		catch(IOException e) {
			System.out.println(e);
		}
		System.out.println("Done");
	}
}

