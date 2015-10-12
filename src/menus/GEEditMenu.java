package menus;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.GEConstants;
import constants.GEConstants.EditMenuItems;

public class GEEditMenu extends JMenu{
	private Vector<JMenuItem> vectorMenuItems;  // 벡터변수 정의
	
	public GEEditMenu(){		
		vectorMenuItems = new Vector<JMenuItem>();
		for(GEConstants.EditMenuItems editMenuItems : GEConstants.EditMenuItems.values()){
			vectorMenuItems.add(new JMenuItem(editMenuItems.getName()));
			this.add(vectorMenuItems.get(editMenuItems.ordinal()));		// 메뉴바에 Edit메뉴 항목 추가
		}
	}
}
