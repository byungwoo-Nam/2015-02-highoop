package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JMenuItem;

import constants.GEConstant;
import constants.GEConstant.EColorMenuItems;

public class GEColorMenu extends GEMenu implements ActionListener{
	
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
	
	private void lineColor(){
		this.drawingPanel.lineColor();
	}
	
	private void fillColor(){
		this.drawingPanel.fillColor();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// ��� �޴� �׸������� ���� �ٸ� �޽����� ����Ѵ�.
		if(e.getActionCommand().equals(EColorMenuItems.Line_color.getName())){
			lineColor();
		}else if(e.getActionCommand().equals(EColorMenuItems.Fill_color.getName())){
			fillColor();
		}
	}
}
