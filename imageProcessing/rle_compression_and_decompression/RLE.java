package imageProcessing.rle_compression_and_decompression;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class RLE{

	public static String arr[];

	public static String compressImage(BufferedImage img) {
		int maxCount = 0;
		int width = img.getWidth();
		int height = img.getHeight();
		int current=0, previous=0, count=0;
		String compressedImage = null;
		arr[0] = String.valueOf(width); arr[1] = String.valueOf(height);
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if(y==0 && x==0) {
					previous = img.getRGB(x, y);
					count = 1;
				}
				else {
					current = img.getRGB(x, y);
					if(current == previous)
						count++;
					else {
						if(compressedImage ==  null){
							compressedImage = count + "/" + previous;
							maxCount = count;
						} else {
							compressedImage += "," + count + "/" + previous;
							if(count>maxCount)
								maxCount = count;
						}
						previous = current;
						count = 1;
					}
				}
			}
		}
		return compressedImage;
	}

	public static void main(String args[])throws IOException {
		BufferedImage img = null;
		File f = null;
		arr = new String[3];
		try {
			f = new File("path/images.jpg");
			img = ImageIO.read(f);
		}
		catch(IOException e) {
			System.out.println(e);
		}

		String output = compressImage(img);
		arr[2] = output;
		System.out.println(output);

		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("path/compressedImage"));
			oos.writeObject(arr);
			oos.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
