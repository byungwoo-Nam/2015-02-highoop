package frames;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import constants.GEConstant;
import menus.GEFileMenu;

public class GEMenuBar extends JMenuBar{

	// attributes
	private static final long serialVersionUID = 1L;
	
	// Association
	private GEPanel drawingPanel;
	
	private JMenu menu;
	private GEFileMenu fileMenu;
	
	public GEMenuBar(){
		super();
		
		for(GEConstant.EMenus eMenus: GEConstant.EMenus.values()) {
			menu = eMenus.getMenu();
			menu.setText(eMenus.getName());
			if(menu.getText().equals("File")){
				fileMenu = (GEFileMenu)menu;
				menu = fileMenu;
			}
			this.add(menu);
		}
	}
	
	public void init(GEPanel panel) {
		this.drawingPanel = panel;
		fileMenu.setDrawingPanel(drawingPanel);
		
	}
}
