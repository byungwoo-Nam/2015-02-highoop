import java.util.Vector;

import javax.swing.JMenuBar;

public class GEMenuBar extends JMenuBar{
	private Vector<GEMenuElement> VectorMenus;  // ���ͺ��� ����
	
	public GEMenuBar(){
		VectorMenus = new Vector<GEMenuElement>();		
		
		int i = 0;
		
		for(GEConstants.Menus name : GEConstants.Menus.values()){				
			VectorMenus.add(new GEMenuElement());
			VectorMenus.get(i).setElements(name);
			this.add(VectorMenus.get(i));		// �޴��ٿ� �޴� �׸� �߰�
			i++;
		}
	}
}
