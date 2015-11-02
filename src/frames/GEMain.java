package frames;

import javax.swing.JFrame;

public class GEMain{
	
	private static JFrame myFrame;
	
	public static void main(String[] args){
		myFrame = new GEFrame();
		myFrame.setVisible(true);
	}
}