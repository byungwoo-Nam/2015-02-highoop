package frames;
import javax.swing.JFrame;

public class GEMain{
	public static void main(String[] args){
		JFrame myFrame = new GEFrame();
		myFrame.setSize(GEFrame.WIDTH, GEFrame.HEIGHT);
		myFrame.setVisible(true);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}