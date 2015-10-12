package menus;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.GEConstants;
import constants.GEConstants.ColorMenuItems;

public class GEColorMenu extends JMenu{
	private Vector<JMenuItem> vectorMenuItems;  // ���ͺ��� ����
	
	public GEColorMenu(){		
		vectorMenuItems = new Vector<JMenuItem>();
		for(GEConstants.ColorMenuItems colorMenuItems : GEConstants.ColorMenuItems.values()){
			vectorMenuItems.add(new JMenuItem(colorMenuItems.getName()));
			this.add(vectorMenuItems.get(colorMenuItems.ordinal()));		// �޴��ٿ� Color�޴� �׸� �߰�
		}
	}
}
