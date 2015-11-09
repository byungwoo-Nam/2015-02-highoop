package frames;

import javax.swing.JMenu;

public class GEMenu extends JMenu{
	// attributes
	private static final long serialVersionUID = 1L;
	
	// Association
	protected GEPanel drawingPanel;
	
	public GEMenu(){
		super();
	}
	
	public void init(GEPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
	}
}
