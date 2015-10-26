package frames;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

import constants.GEConstants;

public class GEFrame extends JFrame {
	
	// attributes
	private static final long serialVersionUID = 1L;
	
	// components
	private JMenuBar menuBar;
	private GEToolBar toolBar;
	private GEPanel panel;
	
	// association
	
	// working variable
	
	public GEFrame(){
		// attributes initialization
		this.setTitle("60092478 남병우 그림판");
		this.setSize(GEConstants.FRAME_W, GEConstants.FRAME_H);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// components lifecycle management
		menuBar = new GEMenuBar();
		this.setJMenuBar(menuBar);
		
		toolBar = new GEToolBar();
		this.add(toolBar, BorderLayout.NORTH);
		
		panel = new GEPanel();
		this.add(panel, BorderLayout.CENTER);
		
		toolBar.setPanel(panel);
	}
}