import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class GEMenuBar extends JMenuBar{
	private Vector<JMenu> VectorMenus;  // 벡터변수 정의
	
	public GEMenuBar(){
		VectorMenus = new Vector<JMenu>();
		
		Class cls;
		Constructor ct;
		Object[] constructorParamObject;
		Class[] constructorParamClass = new Class[] {String.class};
		int i = 0;
		try {
			// reflection, enum, vector를 이용한 동적 메뉴 항목 생성
			for(GEConstants.menuNames name : GEConstants.menuNames.values()){
				cls = Class.forName("GEMenuElement");
				ct = cls.getConstructor(constructorParamClass);
				constructorParamObject = new Object[] {name.toString()};
				VectorMenus.add((JMenu)ct.newInstance(constructorParamObject));	// 새로운 객체 생성
				this.add(VectorMenus.get(i));		// 메뉴바에 메뉴 항목 추가
				i++;
			}
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
