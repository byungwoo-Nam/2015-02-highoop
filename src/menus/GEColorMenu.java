package menus;

import java.util.Vector;

import javax.swing.JMenuItem;

import constants.GEConstant;
import frames.GEMenu;

public class GEColorMenu extends GEMenu{
	
	private static final long serialVersionUID = 1L;
	private Vector<JMenuItem> vectorMenuItems;  // 벡터변수 정의
	
	public GEColorMenu(){		
		super();
		
		vectorMenuItems = new Vector<JMenuItem>();
		for(GEConstant.EColorMenuItems colorMenuItems : GEConstant.EColorMenuItems.values()){
			vectorMenuItems.add(new JMenuItem(colorMenuItems.getName()));
			this.add(vectorMenuItems.get(colorMenuItems.ordinal()));		// 메뉴바에 Color메뉴 항목 추가
		}
	}
}
