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

public class GEFileMenu extends GEMenu implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private Vector<JMenuItem> vectorMenuItems;  // ���ͺ��� ����
	private String currentDirectory;
	
	public GEFileMenu(){
		vectorMenuItems = new Vector<JMenuItem>();
		for(EFileMenuItems fileMenuItems : EFileMenuItems.values()){
			vectorMenuItems.add(new JMenuItem(fileMenuItems.getName()));
			vectorMenuItems.get(fileMenuItems.ordinal()).setActionCommand(fileMenuItems.getName());
			vectorMenuItems.get(fileMenuItems.ordinal()).addActionListener(this);
			this.add(vectorMenuItems.get(fileMenuItems.ordinal()));		// �޴��ٿ� File�޴� �׸� �߰�
		}
	}
	
	public void init(){
		try {
			currentDirectory = (String)GEModel.read(GEConstant.SFileConfig);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void newAction(){
		if(actionContinueCheck()){
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
				this.drawingPanel.openGEShape(chooser.getSelectedFile().getName());
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
	
	private int save(){
		JFileChooser chooser = new JFileChooser(currentDirectory);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(GEConstant.SFileDialogDescription, GEConstant.SAVE_FILE_EXTENSION);
		chooser.setFileFilter(filter);
		int returnVal = chooser.showSaveDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION){
			String path = chooser.getSelectedFile().getAbsolutePath();
			if(!path.toLowerCase().endsWith("."+GEConstant.SAVE_FILE_EXTENSION)){
			    path += "." + GEConstant.SAVE_FILE_EXTENSION;
			}
			this.drawingPanel.saveGEShape(new File(path).getName());
		}
		return returnVal;
	}
	
	private void closeAction(){
		if(actionContinueCheck()){
			this.drawingPanel.newGEShape();
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
		// ��� �޴� �׸������� ���� �ٸ� �޽����� ����Ѵ�.
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
