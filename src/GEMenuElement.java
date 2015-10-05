import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class GEMenuElement extends JMenu{
	private Vector<JMenuItem> VectorMenuItems;  // 벡터변수 정의
	
	public GEMenuElement(){		
		VectorMenuItems = new Vector<JMenuItem>();
	}

	public void setElements(GEConstants.Menus name) {
		// TODO Auto-generated method stub
		this.setText(name.getName());
		
		int i = 0;
		
		for(Enum e : name.getItems()){				
			VectorMenuItems.add(new JMenuItem(e.name()));
			this.add(VectorMenuItems.get(i));		// 메뉴바에 메뉴 항목 추가
			i++;
		}
	}
}
