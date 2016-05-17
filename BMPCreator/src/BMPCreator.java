import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BMPCreator {

	private static final String IMAGE_FILE = "MyImage.bmp";
	final static int WIDTH = 320;
	final static int HEIGHT = 240;
	private BufferedImage image;
	private Graphics g;
	private Color _color;
	
	public BMPCreator(){
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	    g = image.getGraphics();
	    _color = Color.black;
	}
	public void write() throws IOException{
		ImageIO.write(image, "BMP", new File(IMAGE_FILE));
	}
	public void clear() throws IOException{
	    g.setColor(Color.white);
	    g.fillRect(0, 0, WIDTH, HEIGHT);
	    g.setColor(_color);
	}
	public void printMessage(String str) throws IOException{
		printMessage(str,20,20,_color);
	}
	public void printMessage(String str,Color col) throws IOException{
		printMessage(str,20,20,col);
	}
	public void printMessage(String str,int x, int y) throws IOException{
		printMessage(str,x,y,_color);
	}
	public void printMessage(String str,int x, int y, Color col) throws IOException{
		String[] strings = str.split("\n");
		g.setColor(col);
		int yOffset=0;
		for(String s : strings){
			g.drawString(s.trim(),x,y+yOffset);
			yOffset+=g.getFont().getSize()*1.3;
		}
		g.setColor(_color);
	}
	public void close(){
	    g.dispose();
	}
	public void setColor(Color col){
		_color=col;
		g.setColor(_color);
	}
	public void setFont(String font, int style, int size){
		g.setFont(new Font(font,style,size));
	}
	public void drawCircle(int r, int x, int y){
		g.drawOval(x,y,2*r,2*r);
	}
	public void drawLine(int x1,int y1,int x2,int y2){
		g.drawLine(x1,y1,x2,y2);
	}
	public void drawRectangle(int a, int b, int x, int y){
		g.draw3DRect(x, y, a, b, true);
	}
	public void fillCircle(int r, int x, int y){
		g.fillOval(x,y,2*r,2*r);
	}
	public void fillRectangle(int a, int b, int x, int y){
		g.fill3DRect(x, y, a, b, true);
	}
}
