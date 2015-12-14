package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import constants.GEConstant;
import constants.GEConstant.EFileMenuItems;
import entity.GEModel;
import frames.GEPanel;

public class GEFileMenu extends GEMenu implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private Vector<JMenuItem> menuItems;
	private String currentDirectory;
	private String fileName = null;
	
	public GEFileMenu(){
		menuItems = new Vector<JMenuItem>();
		for(EFileMenuItems fileMenuItems : EFileMenuItems.values()){
			menuItems.add(new JMenuItem(fileMenuItems.getName()));
			menuItems.get(fileMenuItems.ordinal()).setActionCommand(fileMenuItems.getName());
			menuItems.get(fileMenuItems.ordinal()).addActionListener(this);
			this.add(menuItems.get(fileMenuItems.ordinal()));		// 메뉴바에 File메뉴 항목 추가
		}
	}
	
	@Override
	public void init(GEPanel drawingPanel){
		super.init(drawingPanel);
		openDirectory();
	}
	
	private void openDirectory() {
		try {
			this.currentDirectory = (String) GEModel.read(GEConstant.SConfigWorkSpace);
		} catch (ClassNotFoundException | IOException e) {
			this.currentDirectory = GEConstant.SDefaultWorkSpace;
		}
	}
	
	private int SaveOrNot() {
		int reply = JOptionPane.NO_OPTION;
		if(drawingPanel.isUpdated()==true) {
			reply = JOptionPane.showConfirmDialog(null, GEConstant.SFileDialogMessage[1]);
		}
		if(reply == JOptionPane.OK_OPTION) {
			save();
		}
		return reply;
	}
	
	private void newFile() {
		int reply = SaveOrNot();
		if(!(reply == JOptionPane.CANCEL_OPTION)) {
			this.drawingPanel.newShapes();
		}
	}
	
	private JFileChooser createChooser() {
	    JFileChooser chooser = new JFileChooser(this.currentDirectory);
	    FileNameExtensionFilter filter = 
	    		new FileNameExtensionFilter(GEConstant.SFileDialogDescription, GEConstant.SAVE_FILE_EXTENSION);
	    chooser.setFileFilter(filter);
		return chooser;
	}
	
	private void open() {
		int reply = SaveOrNot();
		if(!(reply == JOptionPane.CANCEL_OPTION)) {
			JFileChooser chooser = createChooser();
		    int returnVal = chooser.showOpenDialog(null);
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		    	this.currentDirectory = chooser.getSelectedFile().getParent();
		    	fileName = this.currentDirectory + "\\" + chooser.getSelectedFile().getName();
		    	this.drawingPanel.readShapes(fileName);
		    }
		}
	}	
	
	private void save() {
		if (drawingPanel.isUpdated() == true) {
			if (fileName == null) {
				saveAs();
			} else {
				this.drawingPanel.saveShapes(fileName);
			}
		}
	}	
	
	private void saveAs() {
		openDirectory();
		JFileChooser chooser = createChooser();
		int returnVal = chooser.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			this.currentDirectory = chooser.getSelectedFile().getParent();
			fileName = this.currentDirectory + "\\"
					+ chooser.getSelectedFile().getName();
			if (!fileName.endsWith(GEConstant.SAVE_FILE_EXTENSION)) {
				fileName = fileName + "." + GEConstant.SAVE_FILE_EXTENSION;
			}
			this.drawingPanel.saveShapes(fileName);
			try {
				GEModel.save(GEConstant.SConfigWorkSpace, currentDirectory);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void printFile() {
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

	private void exitSystem() {
		int reply = SaveOrNot();
		System.exit(1);		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// 어느 메뉴 항목인지에 따라서 다른 메시지를 출력한다.
		if(e.getActionCommand().equals(EFileMenuItems.New.getName())){
			newFile();
		}else if(e.getActionCommand().equals(EFileMenuItems.Open.getName())){
			open();
		}else if(e.getActionCommand().equals(EFileMenuItems.Save.getName())){
			save();
		}else if(e.getActionCommand().equals(EFileMenuItems.Save_As.getName())){
			saveAs();
		}else if(e.getActionCommand().equals(EFileMenuItems.Print.getName())){
			printFile();
		}else if(e.getActionCommand().equals(EFileMenuItems.Exit.getName())){
			exitSystem();
		}
	}
}
