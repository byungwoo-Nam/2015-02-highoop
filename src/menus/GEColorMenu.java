package menus;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.GEConstants;

public class GEColorMenu extends JMenu{
	private Vector<JMenuItem> vectorMenuItems;  // 벡터변수 정의
	
	public GEColorMenu(){		
		super();
		
		vectorMenuItems = new Vector<JMenuItem>();
		for(GEConstants.ColorMenuItems colorMenuItems : GEConstants.ColorMenuItems.values()){
			vectorMenuItems.add(new JMenuItem(colorMenuItems.getName()));
			this.add(vectorMenuItems.get(colorMenuItems.ordinal()));		// 메뉴바에 Color메뉴 항목 추가
		}
	}
}
