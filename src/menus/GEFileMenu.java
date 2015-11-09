package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.GEConstant.EFileMenuItems;
import frames.GEPanel;

public class GEFileMenu extends JMenu implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private Vector<JMenuItem> vectorMenuItems;  // 벡터변수 정의
	
	// Association
	private GEPanel drawingPanel;
	
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
		ObjectInputStream in;
		try {
			in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("save.nam")));
			Object obj = in.readObject();
			drawingPanel.setVectorGEShape(obj);
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void save(){
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("save.nam")));
			outputStream.writeObject(drawingPanel.getVectorGEShape()); //드로잉패널의 shape배열을 파일에 씀
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	public void setDrawingPanel(GEPanel panel) {
		this.drawingPanel = panel;
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
