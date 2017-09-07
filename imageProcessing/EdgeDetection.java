package imageProcessing;

import java.awt.image.ConvolveOp;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.image.Kernel;


public class EdgeDetection {
	public static void main(String args[]) {
		BufferedImage img = null;
		File f = null;

		// read image
		try {
			f = new File("path.jpg");
			img = ImageIO.read(f);
		}
		catch(IOException e){
			System.out.println(e);
		}

		float roberts[] = {1, 1, -1, -1};
		float prewitts[] = {-2, -1, 0, -1, 0, 1, 0, 1, 2};
		float sobels[] = {-2, -2, 0, -2, 0, 2, 0, 2, 2};

//		ConvolveOp robertsOperation = new ConvolveOp(new Kernel(2, 2, roberts), ConvolveOp.EDGE_NO_OP, null);
//		ConvolveOp prewittsOperation = new ConvolveOp(new Kernel(3, 3, prewitts), ConvolveOp.EDGE_NO_OP, null);
//		ConvolveOp sobelsOperation = new ConvolveOp(new Kernel(3, 3, sobels), ConvolveOp.EDGE_NO_OP, null);
//		robertsImg = robertsOperation.filter(img, null);
//		prewittsImg = prewittsOperation.filter(img, null);
//		sobelsImg = sobelsOperation.filter(img, null);
//
//		// write image
//		try{
//			ImageIO.write(robertsImg, "jpg", new File("path.jpg"));
//			ImageIO.write(prewittsImg, "jpg", new File("path.jpg"));
//			ImageIO.write(sobelsImg, "jpg", new File("path.jpg"));
//		}
//		catch(IOException e){
//			System.out.println(e);
//		}

		try {
			convolveOperation(2, 2, roberts, img);
			convolveOperation(3, 3, prewitts, img);
			convolveOperation(3, 3, sobels, img);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void convolveOperation(int width, int height, float kernelArray[], BufferedImage src) throws IOException {
		ConvolveOp operation = new ConvolveOp(new Kernel(width, height, kernelArray), ConvolveOp.EDGE_NO_OP, null);
		ImageIO.write(operation.filter(src, null), "jpg", new File("path.jpg"));
	}

}
