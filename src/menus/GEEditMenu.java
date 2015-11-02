package menus;

import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.GEConstant;

public class GEEditMenu extends JMenu{
	
	private static final long serialVersionUID = 1L;
	private Vector<JMenuItem> vectorMenuItems;  // 벡터변수 정의
	
	public GEEditMenu(){		
		super();
		
		vectorMenuItems = new Vector<JMenuItem>();
		for(GEConstant.EEditMenuItems editMenuItems : GEConstant.EEditMenuItems.values()){
			vectorMenuItems.add(new JMenuItem(editMenuItems.getName()));
			this.add(vectorMenuItems.get(editMenuItems.ordinal()));		// 메뉴바에 Edit메뉴 항목 추가
		}
	}
}
