package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import constants.GEConstant;
import constants.GEConstant.EFileMenuItems;
import entity.GEModelShape;
import frames.GEMenu;

public class GEFileMenu extends GEMenu implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private Vector<JMenuItem> vectorMenuItems;  // 벡터변수 정의
	
	public GEFileMenu(){		
		vectorMenuItems = new Vector<JMenuItem>();
		for(EFileMenuItems fileMenuItems : EFileMenuItems.values()){
			vectorMenuItems.add(new JMenuItem(fileMenuItems.getName()));
			vectorMenuItems.get(fileMenuItems.ordinal()).setActionCommand(fileMenuItems.getName());
			vectorMenuItems.get(fileMenuItems.ordinal()).addActionListener(this);
			this.add(vectorMenuItems.get(fileMenuItems.ordinal()));		// 메뉴바에 File메뉴 항목 추가
		}
	}
	
	private void open(){
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Graphics Editor(*." + GEConstant.SAVE_FILE_EXTENSION + ")", GEConstant.SAVE_FILE_EXTENSION);
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION){
			GEModelShape.read(chooser.getSelectedFile());
			this.drawingPanel.repaint();
		}
	}
	
	private void save(){
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Graphics Editor(*." + GEConstant.SAVE_FILE_EXTENSION + ")", GEConstant.SAVE_FILE_EXTENSION);
		chooser.setFileFilter(filter);
		int returnVal = chooser.showSaveDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION){
			String path = chooser.getSelectedFile().getAbsolutePath();
			if(!path.toLowerCase().endsWith("."+GEConstant.SAVE_FILE_EXTENSION)){
			    path += "." + GEConstant.SAVE_FILE_EXTENSION;
			}
			GEModelShape.save(new File(path));
		}
	}
	
	private void print(){
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// 어느 메뉴 항목인지에 따라서 다른 메시지를 출력한다.
		if(e.getActionCommand().equals(EFileMenuItems.Open.getName())){
			open();
		}else if(e.getActionCommand().equals(EFileMenuItems.Save.getName())){
			save();
		}else if(e.getActionCommand().equals(EFileMenuItems.Print.getName())){
			print();
		}else if(e.getActionCommand().equals(EFileMenuItems.Exit.getName())){
			System.exit(0);
		}
	}
}
