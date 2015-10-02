import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;


public class GEFrame extends JFrame {
	public GEFrame(){
		JMenuBar menuBar = new GEMenuBar();
		JToolBar toolBar = new GEToolBar();
		this.setJMenuBar(menuBar);
		this.setTitle("60092478 남병우 그림판");
		this.add(toolBar, BorderLayout.NORTH);
		
		JPanel panel = new GEPanel();
		this.add(panel, BorderLayout.CENTER);
	}
}