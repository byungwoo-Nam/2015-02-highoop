package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import constants.GEConstant;
import constants.GEConstant.EFileMenuItems;
import entity.GEModel;
import frames.GEMenu;
import frames.GEPanel;

public class GEFileMenu extends GEMenu implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private Vector<JMenuItem> vectorMenuItems;  // 벡터변수 정의
	private String currentDirectory;
	private String fileName = null;
	
	public GEFileMenu(){
		vectorMenuItems = new Vector<JMenuItem>();
		for(EFileMenuItems fileMenuItems : EFileMenuItems.values()){
			vectorMenuItems.add(new JMenuItem(fileMenuItems.getName()));
			vectorMenuItems.get(fileMenuItems.ordinal()).setActionCommand(fileMenuItems.getName());
			vectorMenuItems.get(fileMenuItems.ordinal()).addActionListener(this);
			this.add(vectorMenuItems.get(fileMenuItems.ordinal()));		// 메뉴바에 File메뉴 항목 추가
		}
	}
	
	@Override
	public void init(GEPanel drawingPanel){
		super.init(drawingPanel);
		try {
			currentDirectory = GEModel.fileCheck(GEConstant.SConfigWorkSpace) ? (String)GEModel.read(GEConstant.SConfigWorkSpace) : GEConstant.SDefaultWorkSpace;
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void newAction(){
		if(actionContinueCheck()){
			fileName = null;
			this.drawingPanel.newGEShape();
			this.drawingPanel.repaint();
		}
	}
	
	private void openAction(){
		if(actionContinueCheck()){
			JFileChooser chooser = new JFileChooser(currentDirectory);
			FileNameExtensionFilter filter = new FileNameExtensionFilter(GEConstant.SFileDialogDescription, GEConstant.SAVE_FILE_EXTENSION);
			chooser.setFileFilter(filter);
			int returnVal = chooser.showOpenDialog(null);
			if(returnVal == JFileChooser.APPROVE_OPTION){
				fileName = chooser.getSelectedFile().getAbsolutePath();
				this.drawingPanel.openGEShape(fileName);
				try {
					this.drawingPanel.setCurrentShape(this.drawingPanel.getCurrentShape().getClass().newInstance());
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
				this.drawingPanel.repaint();
			}
		}
	}
	
	private void saveAction(){
		int response = JOptionPane.showOptionDialog(null, GEConstant.SFileDialogMessage[0], GEConstant.SFileDialogTitle[0], JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, GEConstant.dialogOption, GEConstant.dialogOption[0]);
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
	
	private void saveAsAction(){
		int response = JOptionPane.showOptionDialog(null, GEConstant.SFileDialogMessage[0], GEConstant.SFileDialogTitle[0], JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, GEConstant.dialogOption, GEConstant.dialogOption[0]);
		switch(response){
			case 0 : 
				saveAs();
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
		if(fileName == null){
			return saveAs();
		}else{
			this.drawingPanel.saveGEShape(fileName);
			return 0;
		}
	}
	
	private int saveAs(){
		JFileChooser chooser = new JFileChooser(currentDirectory);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(GEConstant.SFileDialogDescription, GEConstant.SAVE_FILE_EXTENSION);
		chooser.setFileFilter(filter);
		int returnVal = chooser.showSaveDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION){
			fileName = chooser.getSelectedFile().getAbsolutePath();
			if(!fileName.toLowerCase().endsWith("."+GEConstant.SAVE_FILE_EXTENSION)){
				fileName += "." + GEConstant.SAVE_FILE_EXTENSION;
			}
			this.drawingPanel.saveGEShape(fileName);
			try {
				GEModel.write(GEConstant.SConfigWorkSpace, chooser.getCurrentDirectory().getAbsolutePath());
				currentDirectory = chooser.getCurrentDirectory().getAbsolutePath();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return returnVal;
	}
	
	private void closeAction(){
		if(actionContinueCheck()){
			this.drawingPanel.newGEShape();
			fileName = null;
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
			int response = JOptionPane.showOptionDialog(null, GEConstant.SFileDialogMessage[1], GEConstant.SFileDialogTitle[1], JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, GEConstant.dialogOption, GEConstant.dialogOption[0]);
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
		}else if(e.getActionCommand().equals(EFileMenuItems.Save_As.getName())){
			saveAsAction();
		}else if(e.getActionCommand().equals(EFileMenuItems.Close.getName())){
			closeAction();
		}else if(e.getActionCommand().equals(EFileMenuItems.Print.getName())){
			printAction();
		}else if(e.getActionCommand().equals(EFileMenuItems.Exit.getName())){
			exitAction();
		}
	}
}
