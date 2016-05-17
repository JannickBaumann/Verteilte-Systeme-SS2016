import java.awt.Color;
import java.awt.Font;

import java.io.IOException;


public class Main {

	private static final String IMAGE_FILE = "MyImage.bmp";
	final static int WIDTH = 320;
	final static int HEIGHT = 240;
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BMPCreator bmp = new BMPCreator();
		bmp.clear();
		bmp.printMessage("STATUS: ONLINE\n" +
								"CPU: 117%\n" +
								"Homofürst");
		bmp.setFont(Font.MONOSPACED,Font.ITALIC,20);
		bmp.printMessage("Guten Tag, Erdenmenschen",120,120,Color.green);
		bmp.drawCircle(20,0,0);
		bmp.fillCircle(15, 120, 20);
		bmp.drawCircle(20,180,20);
		bmp.fillCircle(15, 180, 20);
		bmp.drawRectangle(100, 100, 100, 100);
		bmp.drawEmoji(Emoji.happyFace, 100, 120, 120);
		bmp.drawLine(10, 10, 24, 20);
		bmp.write();
		
		
	}

}
