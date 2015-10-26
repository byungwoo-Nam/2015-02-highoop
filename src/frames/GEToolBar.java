package frames;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

import constants.GEConstants;
import shapes.GEShape;

public class GEToolBar extends JToolBar implements ActionListener{
	
	// attributes
	private static final long serialVersionUID = 1L;
	
	private Vector<JRadioButton> vectorButtons = new Vector<JRadioButton>();
	
	// Association
	private GEPanel panel;
	
	public GEToolBar(){
		super();
		
		this.setSize(400, 100);
		this.setBackground(Color.decode("#fafbfc"));
		vectorButtons = new Vector<JRadioButton>();
		ButtonGroup buttonGroup = new ButtonGroup();
		
		for(GEConstants.ToolBars toolBars : GEConstants.ToolBars.values()){				
			vectorButtons.add(new JRadioButton());
			vectorButtons.get(toolBars.ordinal()).setIcon(new ImageIcon(toolBars.getIconFilePath(GEConstants.ICON_KIND.DEFAULT)));
			vectorButtons.get(toolBars.ordinal()).setRolloverIcon(new ImageIcon(toolBars.getIconFilePath(GEConstants.ICON_KIND.ROLLOVER)));
			vectorButtons.get(toolBars.ordinal()).setSelectedIcon(new ImageIcon(toolBars.getIconFilePath(GEConstants.ICON_KIND.SELECTED)));
			vectorButtons.get(toolBars.ordinal()).setBackground(Color.decode("#fafbfc"));
			vectorButtons.get(toolBars.ordinal()).setActionCommand(toolBars.getClassName());
			vectorButtons.get(toolBars.ordinal()).addActionListener(this);		// 라디오버튼 리스너 등록
			this.add(vectorButtons.get(toolBars.ordinal()));						// 메뉴바에 메뉴 항목 추가
			buttonGroup.add(vectorButtons.get(toolBars.ordinal()));			// 버튼 그룹 항목으로 등록
		}
	}

	public void setPanel(GEPanel panel) {
		this.panel = panel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			if(this.panel.getDrawingShape()==null || !this.panel.getDrawingShape().getClass().getSimpleName().equals(e.getActionCommand())){
				this.panel.setDrawingShape((GEShape)Class.forName(e.getActionCommand()).newInstance());
			}
//			if(this.panel.getDrawingShape().getShapeType() == GEConstants.ShapeType.Complex){
//				JOptionPane.showMessageDialog(null, "구현중입니다.");
//				this.panel.setDrawingShape(null);
//			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
