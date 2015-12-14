package frames;

import javax.swing.JMenuBar;

import constants.GEConstant;
import menus.GEMenu;

public class GEMenuBar extends JMenuBar{

	// attributes
	private static final long serialVersionUID = 1L;
	
	public GEMenuBar(){
		super();
		
		for(GEConstant.EMenus eMenus: GEConstant.EMenus.values()) {
			GEMenu menu = eMenus.getMenu();
			menu.setText(eMenus.getName());
			this.add(menu);
		}
	}
	
	public void init(GEPanel drawingPanel) {
		for(int i=0; i<this.getMenuCount(); i++){
			GEMenu menu = (GEMenu) this.getMenu(i);
			menu.init(drawingPanel);
		}
	}
}
