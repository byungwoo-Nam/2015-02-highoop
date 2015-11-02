package constants;

import javax.swing.JMenu;

import menus.GEColorMenu;
import menus.GEEditMenu;
import menus.GEFileMenu;
import shapes.*;

public class GEConstant{
	public static final int FRAME_W = 600;
	public static final int FRAME_H = 600;
	private static final String BUTTON_PATH = "rsc/";			//	버튼이미지 폴더 경로
	
	public static enum EDrawingState{idle, drawingTP, drawingNP};
	
	public static enum EButtons{
		Rectangle("rectangle.png", "rectangle_SLT.png", "rectangle_ROV.png", new GERectangle()),
		Ellipse("ellipse.png", "ellipse_SLT.png", "ellipse_ROV.png", new GEEllipse()),
		Polygon("polygon.png", "polygon_SLT.png", "polygon_ROV.png", new GEPolygon()),
		Heart("heart.png", "heart_SLT.png", "heart_ROV.png", new GERectangle()),
		Line("line.png", "line_SLT.png", "line_ROV.png", new GELine()),
		Text("text.png", "text_SLT.png", "text_ROV.png", new GERectangle());
		
		private String buttonImage;
		private String selectedButtonImage;
		private String rolloverButtonImage;
		private GEShape newShape;
		
		private EButtons(String buttonImage, String selectedButtonImage, String rolloverButtonImage, GEShape newShape){
			this.buttonImage = buttonImage;
			this.selectedButtonImage = selectedButtonImage;
			this.rolloverButtonImage = rolloverButtonImage;
			this.newShape = newShape;
		}
		public String getButtonImage(){return BUTTON_PATH + buttonImage;}
		public String getSelectedButtonImage(){return BUTTON_PATH + selectedButtonImage;}
		public String getRolloverButtonImage(){return BUTTON_PATH + rolloverButtonImage;}
		public GEShape getShape(){return newShape;}
	}
	
	
	public static enum EMenus{
		File("File", new GEFileMenu()), 
		Edit("Edit", new GEEditMenu()), 
		Color("Color", new GEColorMenu());
		
		private String name;
		private JMenu menu;
		
		private EMenus(String name, JMenu menu){
			this.name = name;
			this.menu = menu;
		}
		public String getName(){return this.name;}
		public JMenu getMenu(){return this.menu;}
	};
	
	public static enum EFileMenuItems{
		New("New"), Open("Open"), Save("Save"), Save_As("Save_As"), Close("Close"), Print("Print"), Exit("Exit"); // print, close
		private String name;
		private EFileMenuItems(String s){	this.name = s;}
		public String getName(){return this.name;}
	};
	
	public static enum EEditMenuItems{
		Undo("Undo"), Redo("Redo"), Cut("Cut"), Copy("Copy"), Paste("Paste"), Delete("Delete"), Select_all("Select_all"), Deselect_all("Deselect_all");
		private String name;
		private EEditMenuItems(String s){this.name = s;}
		public String getName(){return this.name;}
	};
	
	public static enum EColorMenuItems{
		Line_color("Line_color"), Fill_color("Fill_color"); //텍스트칼라포함
		private String name;
		private EColorMenuItems(String s){this.name = s;}
		public String getName(){return this.name;}
	};
}