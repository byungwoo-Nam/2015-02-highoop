package menus;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.GEConstants;
import constants.GEConstants.FileMenuItems;

public class GEFileMenu extends JMenu{
	private Vector<JMenuItem> vectorMenuItems;  // ���ͺ��� ����
	
	public GEFileMenu(){		
		vectorMenuItems = new Vector<JMenuItem>();
		for(GEConstants.FileMenuItems fileMenuItems : GEConstants.FileMenuItems.values()){
			vectorMenuItems.add(new JMenuItem(fileMenuItems.getName()));
			this.add(vectorMenuItems.get(fileMenuItems.ordinal()));		// �޴��ٿ� File�޴� �׸� �߰�
		}
	}
}
