import java.awt.Color;
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
		
		int i = 0;
		
		for(GEConstants.ToolBars name : GEConstants.ToolBars.values()){				
			VectorButtons.add(new JRadioButton());
			VectorButtons.get(i).setIcon(new ImageIcon("rsc/" + name + ".png"));
			VectorButtons.get(i).setSelectedIcon(new ImageIcon("rsc/" + name + "_click.png"));
			VectorButtons.get(i).setBackground(Color.decode("#fafbfc"));
			this.add(VectorButtons.get(i));		// 메뉴바에 메뉴 항목 추가
			buttonGroup.add(VectorButtons.get(i));	// 버튼 그룹 항목으로 등록
			i++;
		}
	}
}
