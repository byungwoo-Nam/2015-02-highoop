package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.GEConstant;

public class GEFileMenu extends JMenu implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private Vector<JMenuItem> vectorMenuItems;  // 벡터변수 정의
	
	public GEFileMenu(){		
		vectorMenuItems = new Vector<JMenuItem>();
		for(GEConstant.EFileMenuItems fileMenuItems : GEConstant.EFileMenuItems.values()){
			vectorMenuItems.add(new JMenuItem(fileMenuItems.getName()));
			vectorMenuItems.get(fileMenuItems.ordinal()).setActionCommand(fileMenuItems.getName());
			vectorMenuItems.get(fileMenuItems.ordinal()).addActionListener(this);
			this.add(vectorMenuItems.get(fileMenuItems.ordinal()));		// 메뉴바에 File메뉴 항목 추가
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// 어느 메뉴 항목인지에 따라서 다른 메시지를 출력한다.
		if(e.getActionCommand().equals("Exit")){
			System.exit(0);
		}else if(e.getActionCommand().equals("Print")){
			PrinterJob printerJob =  PrinterJob.getPrinterJob();
			printerJob.setPrintable(null);
			if(!printerJob.printDialog()){
				return;
			}
			try{
				printerJob.print();
			}catch(PrinterException pe){
				pe.getStackTrace();
			}
		}
	}
}
