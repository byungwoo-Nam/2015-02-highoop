package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import constants.GEConstant;
import constants.GEConstant.EFileMenuItems;
import entity.GEModelShape;
import frames.GEMenu;
import shapes.GERectangle;
import shapes.GEShape;

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
	
	private void newAction(){
		if(actionContinueCheck()){
			GEModelShape.newGEShape();
			try {
				this.drawingPanel.setCurrentShape(this.drawingPanel.getCurrentShape().getClass().newInstance());
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			this.drawingPanel.repaint();
		}
	}
	
	private void openAction(){
		if(actionContinueCheck()){
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Graphics Editor(*." + GEConstant.SAVE_FILE_EXTENSION + ")", GEConstant.SAVE_FILE_EXTENSION);
			chooser.setFileFilter(filter);
			int returnVal = chooser.showOpenDialog(null);
			if(returnVal == JFileChooser.APPROVE_OPTION){
				GEModelShape.openGEShape(chooser.getSelectedFile());
				try {
					this.drawingPanel.setCurrentShape(this.drawingPanel.getCurrentShape().getClass().newInstance());
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
				this.drawingPanel.repaint();
				this.drawingPanel.setEditStatus(false);
			}
		}
	}
	
	private void saveAction(){
		int response = JOptionPane.showOptionDialog(null, "정말 저장하시겠습니까?", "파일 저장", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, GEConstant.dialogOption, GEConstant.dialogOption[0]);
		switch(response){
			case 0 : 
				save();
				break;
			case 1 :
				break;
			case 2 :
				break;
			default :
				break;
		}
	}
	
	private int save(){
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Graphics Editor(*." + GEConstant.SAVE_FILE_EXTENSION + ")", GEConstant.SAVE_FILE_EXTENSION);
		chooser.setFileFilter(filter);
		int returnVal = chooser.showSaveDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION){
			String path = chooser.getSelectedFile().getAbsolutePath();
			if(!path.toLowerCase().endsWith("."+GEConstant.SAVE_FILE_EXTENSION)){
			    path += "." + GEConstant.SAVE_FILE_EXTENSION;
			}
			GEModelShape.saveGEShape(new File(path));
			this.drawingPanel.setEditStatus(false);
		}
		return returnVal;
	}
	
	private void closeAction(){
		if(actionContinueCheck()){
			GEModelShape.newGEShape();
			try {
				this.drawingPanel.setCurrentShape(this.drawingPanel.getCurrentShape().getClass().newInstance());
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			this.drawingPanel.repaint();
		}
	}
	
	private void printAction(){
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
	
	private void exitAction(){
		if(actionContinueCheck()){
			System.exit(0);
		}
	}
	
	private boolean actionContinueCheck(){
		boolean actionContinueStatus = false;
		if(this.drawingPanel.isEditStatus()){
			int response = JOptionPane.showOptionDialog(null, "변경된 사항을 저장하시겠습니까?", "변경 내용 확인", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, GEConstant.dialogOption, GEConstant.dialogOption[0]);
			switch(response){
				case 0 : 
					actionContinueStatus = (save() == 0) ? true : actionContinueStatus;
					break;
				case 1 :
					actionContinueStatus = true;
					break;
				case 2 :
					break;
				default :
					break;
			}
		}else{
			actionContinueStatus = true;
		}
		return actionContinueStatus;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// 어느 메뉴 항목인지에 따라서 다른 메시지를 출력한다.
		if(e.getActionCommand().equals(EFileMenuItems.New.getName())){
			newAction();
		}else if(e.getActionCommand().equals(EFileMenuItems.Open.getName())){
			openAction();
		}else if(e.getActionCommand().equals(EFileMenuItems.Save.getName())){
			saveAction();
		}else if(e.getActionCommand().equals(EFileMenuItems.Close.getName())){
			closeAction();
		}else if(e.getActionCommand().equals(EFileMenuItems.Print.getName())){
			printAction();
		}else if(e.getActionCommand().equals(EFileMenuItems.Exit.getName())){
			exitAction();
		}
	}
}
