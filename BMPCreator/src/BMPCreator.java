import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BMPCreator {

	private static final String IMAGE_FILE = "MyImage.bmp";
	final static int WIDTH = 320;
	final static int HEIGHT = 240;
	
	
	public static void clear() throws IOException{
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	    Graphics g = image.getGraphics();
	    g.setColor(Color.white);
	    g.fillRect(0, 0, WIDTH, HEIGHT);
		ImageIO.write(image, "BMP", new File(IMAGE_FILE));
	    g.dispose();
	}
	
	public static void printMessage(String str) throws IOException{
		String[] strings = str.split("\n");
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	    Graphics g = image.getGraphics();
	    g.setColor(Color.white);
	    g.fillRect(0, 0, WIDTH, HEIGHT);
	    g.setColor(Color.black);
		int y=20;
		for(String s : strings){
			g.drawString(s.trim(),20,y);
			y+=15;
		}
		ImageIO.write(image, "BMP", new File(IMAGE_FILE));
	    g.dispose();
	}
	
}
