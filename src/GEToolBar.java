import java.awt.Color;
import java.lang.reflect.Constructor;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

public class GEToolBar extends JToolBar{
	private Vector<JRadioButton> VectorButtons = new Vector<JRadioButton>();
	
	public GEToolBar(){
		this.setSize(400, 100);
		this.setBackground(Color.decode("#fafbfc"));
		VectorButtons = new Vector<JRadioButton>();
		ButtonGroup buttonGroup = new ButtonGroup();
		
		Class cls;
		Constructor ct;
		Object[] constructorParamObject;
		Class[] constructorParamClass = new Class[] {String.class};
		int i = 0;
		try {
			// reflection, enum, vector를 이용한 동적 툴바 버튼 생성
			for(GEConstants.toolBarButtonNames name : GEConstants.toolBarButtonNames.values()){
				cls = Class.forName("javax.swing.JRadioButton");
				constructorParamObject = new Object[] {name.toString()};
				VectorButtons.add((JRadioButton)cls.newInstance());	// 새로운 객체 생성
				VectorButtons.get(i).setIcon(new ImageIcon("rsc/" + name + ".png"));
				VectorButtons.get(i).setSelectedIcon(new ImageIcon("rsc/" + name + "_click.png"));
				VectorButtons.get(i).setBackground(Color.decode("#fafbfc"));
				this.add(VectorButtons.get(i));		// 메뉴바에 메뉴 항목 추가
				buttonGroup.add(VectorButtons.get(i));	// 버튼 그룹 항목으로 등록
				i++;
			}
		} catch (ClassNotFoundException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
