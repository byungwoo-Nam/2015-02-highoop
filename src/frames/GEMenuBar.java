package frames;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import constants.GEConstants;

public class GEMenuBar extends JMenuBar{

	// attributes
	private static final long serialVersionUID = 1L;
	
	private Vector<JMenu> vectorMenus;  // ���ͺ��� ����
	
	public GEMenuBar(){
		super();
		
		vectorMenus = new Vector<JMenu>();		
		
		for(GEConstants.Menus menus : GEConstants.Menus.values()){				
			vectorMenus.add(menus.getMenu());
			vectorMenus.get(menus.ordinal()).setText(menus.getName());
			this.add(vectorMenus.get(menus.ordinal()));		// �޴��ٿ� �޴� �׸� �߰�
		}
	}
}
