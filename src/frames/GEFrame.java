package frames;

import java.awt.BorderLayout;
import javax.swing.JFrame;

import constants.GEConstant;

public class GEFrame extends JFrame{
	
	// attributes
	private static final long serialVersionUID = 1L;
	
	// components
	private GEMenuBar menuBar;
	private GEToolBar toolBar;
	private GEPanel drawingPanel;
	
	// association
	
	// working variable
	
	public GEFrame(){
		super();
		// attributes initialization
		this.setTitle("60092478 남병우 그림판");
		this.setSize(GEConstant.FRAME_W, GEConstant.FRAME_H);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// components lifecycle management
		drawingPanel = new GEPanel();
		this.add(drawingPanel, BorderLayout.CENTER);
		
		menuBar = new GEMenuBar();
		this.setJMenuBar(menuBar);
		
		toolBar = new GEToolBar();
		this.add(toolBar, BorderLayout.NORTH);
	}
	
	public void init(){
		menuBar.init(drawingPanel);
		toolBar.init(drawingPanel);
	}
}