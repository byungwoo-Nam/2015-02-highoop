package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JMenuItem;

import constants.GEConstant;
import constants.GEConstant.EEditMenuItems;

public class GEEditMenu extends GEMenu implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private Vector<JMenuItem> vectorMenuItems;  // 벡터변수 정의
	
	public GEEditMenu(){		
		super();
		
		vectorMenuItems = new Vector<JMenuItem>();
		for(GEConstant.EEditMenuItems editMenuItems : GEConstant.EEditMenuItems.values()){
			vectorMenuItems.add(new JMenuItem(editMenuItems.getName()));
			vectorMenuItems.get(editMenuItems.ordinal()).setActionCommand(editMenuItems.getName());
			vectorMenuItems.get(editMenuItems.ordinal()).addActionListener(this);
			this.add(vectorMenuItems.get(editMenuItems.ordinal()));		// 메뉴바에 Edit메뉴 항목 추가
		}
	}
	
	private void undoAction(){
		this.drawingPanel.undo();
	}
	
	private void redoAction(){
		this.drawingPanel.redo();
	}
	
	private void copyAction(){
		this.drawingPanel.copyShape();
	}
	
	private void pasteAction(){
		this.drawingPanel.pasteShape();
	}
	
	private void cutAction(){
		this.drawingPanel.cutShape();
	}
	
	private void deleteAction(){
		this.drawingPanel.deleteShape();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// 어느 메뉴 항목인지에 따라서 다른 메시지를 출력한다.
		if(e.getActionCommand().equals(EEditMenuItems.Undo.getName())){
			undoAction();
		}else if(e.getActionCommand().equals(EEditMenuItems.Redo.getName())){
			redoAction();
		}else if(e.getActionCommand().equals(EEditMenuItems.Copy.getName())){
			copyAction();
		}else if(e.getActionCommand().equals(EEditMenuItems.Paste.getName())){
			pasteAction();
		}else if(e.getActionCommand().equals(EEditMenuItems.Cut.getName())){
			cutAction();
		}else if(e.getActionCommand().equals(EEditMenuItems.Delete.getName())){
			deleteAction();
		}
	}
}
