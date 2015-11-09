package frames;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

import constants.GEConstant;

public class GEToolBar extends JToolBar implements ActionListener{
	
	// attributes
	private static final long serialVersionUID = 1L;
	
	// components
	private ButtonGroup buttonGroup;
	
	// Association
	private GEPanel drawingPanel;
	
	public GEToolBar(){
		super();
		
		buttonGroup = new ButtonGroup();
		
		for(GEConstant.EButtons eButton: GEConstant.EButtons.values()){				
			JRadioButton button = new JRadioButton();
			button.setIcon(new ImageIcon(eButton.getButtonImage()));
			button.setRolloverIcon(new ImageIcon(eButton.getRolloverButtonImage()));
			button.setSelectedIcon(new ImageIcon(eButton.getSelectedButtonImage()));
			button.setBackground(Color.decode("#fafbfc"));
			button.setActionCommand(eButton.name());
			button.addActionListener(this);		// 라디오버튼 리스너 등록
			this.add(button);						// 메뉴바에 메뉴 항목 추가
			buttonGroup.add(button);				// 버튼 그룹 항목으로 등록
		}
	}

	public void init(GEPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
		this.setBackground(Color.decode("#fafbfc"));
		((JRadioButton)this.getComponent(GEConstant.EButtons.Rectangle.ordinal())).doClick();
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		drawingPanel.setCurrentShape(GEConstant.EButtons.valueOf(e.getActionCommand()).getShape());	
	}
}
