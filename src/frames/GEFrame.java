package frames;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

import constants.GEConstant;

public class GEFrame extends JFrame{
	
	// attributes
	private static final long serialVersionUID = 1L;
	
	// components
	private JMenuBar menuBar;
	private GEToolBar toolBar;
	private GEPanel panel;
	
	// association
	
	// working variable
	
	public GEFrame(){
		super();
		// attributes initialization
		this.setTitle("60092478 남병우 그림판");
		this.setSize(GEConstant.FRAME_W, GEConstant.FRAME_H);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// components lifecycle management
		menuBar = new GEMenuBar();
		this.setJMenuBar(menuBar);
		
		panel = new GEPanel();
		this.add(panel, BorderLayout.CENTER);
		
		toolBar = new GEToolBar();
		toolBar.init(panel);
		this.add(toolBar, BorderLayout.NORTH);
	}
}