package menus;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.GEConstants;
import constants.GEConstants.EditMenuItems;

public class GEEditMenu extends JMenu{
	private Vector<JMenuItem> vectorMenuItems;  // ���ͺ��� ����
	
	public GEEditMenu(){		
		vectorMenuItems = new Vector<JMenuItem>();
		for(GEConstants.EditMenuItems editMenuItems : GEConstants.EditMenuItems.values()){
			vectorMenuItems.add(new JMenuItem(editMenuItems.getName()));
			this.add(vectorMenuItems.get(editMenuItems.ordinal()));		// �޴��ٿ� Edit�޴� �׸� �߰�
		}
	}
}
