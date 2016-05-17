import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


enum Emoji{
		happyFace,
		coolFace
		}



public class BMPCreator {

	private static final String IMAGE_FILE = "MyImage.bmp";
	final static int WIDTH = 320;
	final static int HEIGHT = 240;
	private BufferedImage image;
	private Graphics2D g;
	private Color _fontcolor;
	
	/*
	 * Standard constructor for creation of BMP Creator Object.
	 * 
	 * height*width: 240*320 
	 * _fontcolor: black
	 */
	public BMPCreator(){
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	    g = (Graphics2D)image.getGraphics();
	    _fontcolor = Color.black;
	}
	
	/*
	 * Sets current active Color.
	 */
	public void setColor(Color col){
		_fontcolor=col;
		g.setColor(_fontcolor);
	}
	
	/*
	 * return current active Color.
	 */
	
	public Color getColor(){
		return _fontcolor;
	}
	
	/*
	 * Sets current active Font
	 * 
	 */
	public void setFont(String font, int style, int size){
		g.setFont(new Font(font,style,size));
	}
	
	/*
	 * get current active Font
	 * 	 
	 */
	
	public Font getFont(String font, int style, int size){
		return g.getFont();
	}
	
	/*
	 * Write current image into BMP File.
	 */
	public void write() throws IOException{
		ImageIO.write(image, "BMP", new File(IMAGE_FILE));
	}
	
	/*
	 * Clears current image.
	 * Must be written to BMP file to be active.
	 * 
	 */
	public void clear() throws IOException{
	    g.setColor(Color.white);
	    g.fillRect(0, 0, WIDTH, HEIGHT);
	    g.setColor(_fontcolor);
	}
	
	/*
	 * Closes current image. Should be closed after printing.
	 * 
	 */
	public void close(){
	    g.dispose();
	}
	
	/*
	 * Prints message with currently set font color.
	 * 
	 */
	public void printMessage(String str) throws IOException{
		printMessage(str,20,20,_fontcolor);
	}
	
	/*
	 * Prints message with custom font color.
	 * Does not change the active font color.
	 * 
	 */
	public void printMessage(String str,Color col) throws IOException{
		printMessage(str,20,20,col);
	}
	
	/*
	 * Print message at starting position x,y.
	 * 
	 */
	public void printMessage(String str,int x, int y) throws IOException{
		printMessage(str,x,y,_fontcolor);
	}
	public void printMessage(String str,int x, int y, Color col) throws IOException{
		String[] strings = str.split("\n");
		g.setColor(col);
		int yOffset=0;
		for(String s : strings){
			g.drawString(s.trim(),x,y+yOffset);
			yOffset+=g.getFont().getSize()*1.3;
		}
		g.setColor(_fontcolor);
	}
	
	
	/*
	 * Draws a line from x1,y1 to x2,y2.
	 * 
	 */
	public void drawLine(int x1,int y1,int x2,int y2){
		g.setStroke(new BasicStroke(10));
		g.drawLine(x1,y1,x2,y2);
	}
	

	/*
	 * Draws a circle with radius r centered at x and y.
	 * 
	 */
	public void drawCircle(int r, int x, int y){
		g.drawOval(x-r,y-r,2*r,2*r);
	}
	
	/*
	 * Fills a circle with radius r centered at x and y.
	 * 
	 */
	public void fillCircle(int r, int x, int y){
		g.fillOval(x-r,y-r,2*r,2*r);
	}
	
	
	/*
	 * Draws a Rectangle starting at x,y with the width of a and height of b.
	 * 
	 */
	public void drawRectangle(int a, int b, int x, int y){
		g.draw3DRect(x, y, a, b, true);
	}
	
	/*
	 * Fills a Rectangle starting at x,y with the width of a and height of b.
	 * 
	 */
	public void fillRectangle(int a, int b, int x, int y){
		g.fill3DRect(x, y, a, b, true);
	}
		
	/*********************************************************************************************************
	 *********************************************************************************************************
	 **																										**
	 **							Section for creating emoteIcons												**
	 **																										**
	 *********************************************************************************************************
	 *********************************************************************************************************/
	
	public void drawEmoji(Emoji emoji, int size, int x, int y){
		switch(emoji){
			case happyFace:
				drawHappyFace(size, x, y);
				break;
				
			case coolFace:
				drawCoolFace(size, x, y);
				break;
		}
	}
	
	private void drawHappyFace(int size, int x, int y){
		g.setColor(Color.YELLOW);
		fillCircle(size, x, y);
		g.setColor(Color.BLACK);
		fillCircle(size/4, x-size/3, y-size/3);
		fillCircle(size/4, x+size/3, y-size/3);
		g.drawArc(x-size/2, y, size, (int)(size/1.5), 180, 180);
		g.setColor(_fontcolor);
	}
	

	private void drawCoolFace(int size, int x, int y){

	}

}
