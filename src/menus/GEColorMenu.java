package menus;

import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.GEConstant;

public class GEColorMenu extends JMenu{
	
	private static final long serialVersionUID = 1L;
	private Vector<JMenuItem> vectorMenuItems;  // ���ͺ��� ����
	
	public GEColorMenu(){		
		super();
		
		vectorMenuItems = new Vector<JMenuItem>();
		for(GEConstant.EColorMenuItems colorMenuItems : GEConstant.EColorMenuItems.values()){
			vectorMenuItems.add(new JMenuItem(colorMenuItems.getName()));
			this.add(vectorMenuItems.get(colorMenuItems.ordinal()));		// �޴��ٿ� Color�޴� �׸� �߰�
		}
	}
}
