import java.lang.reflect.Field;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class GEMenuElement extends JMenu{
	private Vector<JMenuItem> VectorMenuItems;  // ���ͺ��� ����
	
	public GEMenuElement(String name){
		super(name);
		
		VectorMenuItems = new Vector<JMenuItem>();

		// reflection, enum, vector�� �̿��� ���� �޴������� ����
		try {
			Class cls = Class.forName("GEConstants");
			Field fld = cls.getField(name + "MenuItems");
			GEConstants geConstants = new GEConstants();
			String[] itemArr = (String[])(fld.get(geConstants));
			for(int i=0; i<itemArr.length; i++){
				VectorMenuItems.add(new JMenuItem(itemArr[i]));	// File �޴��� ���� �׸� ����
				this.add(VectorMenuItems.get(i));		// �� �޴��� �����׸� �߰�
			}
         }catch (Throwable e) {
            System.err.println(e);
         }
	}
}
