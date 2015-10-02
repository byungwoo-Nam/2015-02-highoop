import java.lang.reflect.Field;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class GEMenuElement extends JMenu{
	private Vector<JMenuItem> VectorMenuItems;  // 벡터변수 정의
	
	public GEMenuElement(String name){
		super(name);
		
		VectorMenuItems = new Vector<JMenuItem>();

		// reflection, enum, vector를 이용한 동적 메뉴아이템 생성
		try {
			Class cls = Class.forName("GEConstants");
			Field fld = cls.getField(name + "MenuItems");
			GEConstants geConstants = new GEConstants();
			String[] itemArr = (String[])(fld.get(geConstants));
			for(int i=0; i<itemArr.length; i++){
				VectorMenuItems.add(new JMenuItem(itemArr[i]));	// File 메뉴의 세부 항목 설정
				this.add(VectorMenuItems.get(i));		// 각 메뉴에 세부항목 추가
			}
         }catch (Throwable e) {
            System.err.println(e);
         }
	}
}
