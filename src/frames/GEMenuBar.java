package frames;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import constants.GEConstant;

public class GEMenuBar extends JMenuBar{

	// attributes
	private static final long serialVersionUID = 1L;
	
	public GEMenuBar(){
		super();
		
		JMenu menu;
		for(GEConstant.EMenus eMenus: GEConstant.EMenus.values()) {
			menu = eMenus.getMenu();
			menu.setText(eMenus.getName());
			this.add(menu);
		}
	}
}
