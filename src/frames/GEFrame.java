package frames;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class GEFrame extends JFrame {
	
	final public static int WIDTH = 600;
	final public static int HEIGHT = 600;
	
	public GEFrame(){
		this.setTitle("60092478 남병우 그림판");
		
		JMenuBar menuBar = new GEMenuBar();
		this.setJMenuBar(menuBar);
		
		GEToolBar toolBar = new GEToolBar();
		this.add(toolBar, BorderLayout.NORTH);
		
		GEPanel panel = new GEPanel();
		this.add(panel, BorderLayout.CENTER);
		
		toolBar.setPanel(panel);
	}
}