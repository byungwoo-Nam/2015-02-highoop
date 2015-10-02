import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class GEMenuBar extends JMenuBar{
	private Vector<JMenu> VectorMenus;  // ���ͺ��� ����
	
	public GEMenuBar(){
		VectorMenus = new Vector<JMenu>();
		
		Class cls;
		Constructor ct;
		Object[] constructorParamObject;
		Class[] constructorParamClass = new Class[] {String.class};
		int i = 0;
		try {
			// reflection, enum, vector�� �̿��� ���� �޴� �׸� ����
			for(GEConstants.menuNames name : GEConstants.menuNames.values()){
				cls = Class.forName("GEMenuElement");
				ct = cls.getConstructor(constructorParamClass);
				constructorParamObject = new Object[] {name.toString()};
				VectorMenus.add((JMenu)ct.newInstance(constructorParamObject));	// ���ο� ��ü ����
				this.add(VectorMenus.get(i));		// �޴��ٿ� �޴� �׸� �߰�
				i++;
			}
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
