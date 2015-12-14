package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JMenuItem;

import constants.GEConstant;
import constants.GEConstant.EColorMenuItems;

public class GEColorMenu extends GEMenu implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private Vector<JMenuItem> vectorMenuItems;  // 벡터변수 정의
	
	public GEColorMenu(){		
		super();
		
		vectorMenuItems = new Vector<JMenuItem>();
		for(GEConstant.EColorMenuItems colorMenuItems : GEConstant.EColorMenuItems.values()){
			vectorMenuItems.add(new JMenuItem(colorMenuItems.getName()));
			this.add(vectorMenuItems.get(colorMenuItems.ordinal()));		// 메뉴바에 Color메뉴 항목 추가
		}
	}
	
	private void lineColor(){
		this.drawingPanel.lineColor();
	}
	
	private void fillColor(){
		this.drawingPanel.fillColor();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// 어느 메뉴 항목인지에 따라서 다른 메시지를 출력한다.
		if(e.getActionCommand().equals(EColorMenuItems.Line_color.getName())){
			lineColor();
		}else if(e.getActionCommand().equals(EColorMenuItems.Fill_color.getName())){
			fillColor();
		}
	}
}
