package menus;

import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.GEConstant;

public class GEEditMenu extends JMenu{
	
	private static final long serialVersionUID = 1L;
	private Vector<JMenuItem> vectorMenuItems;  // ���ͺ��� ����
	
	public GEEditMenu(){		
		super();
		
		vectorMenuItems = new Vector<JMenuItem>();
		for(GEConstant.EEditMenuItems editMenuItems : GEConstant.EEditMenuItems.values()){
			vectorMenuItems.add(new JMenuItem(editMenuItems.getName()));
			this.add(vectorMenuItems.get(editMenuItems.ordinal()));		// �޴��ٿ� Edit�޴� �׸� �߰�
		}
	}
}
