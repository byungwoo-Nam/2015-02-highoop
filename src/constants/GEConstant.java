package constants;

import java.awt.Cursor;

import menus.GEColorMenu;
import menus.GEEditMenu;
import menus.GEFileMenu;
import menus.GEMenu;
import shapes.*;

public class GEConstant{
	public static final String SProgramTitle = "Graphics Editor";	//	Program Title
	public static final int FRAME_W = 600;
	public static final int FRAME_H = 600;
	public static final String SAVE_FILE_EXTENSION = "nam";			//	저장파일 확장자
	public static final Object[] dialogOption={"예","아니오","취소"};	//	dialog 옵션
	public static final String SDefaultWorkSpace = "data\\";
	public static final String SConfigWorkSpace = "config\\filepath.config";
	public static final String SFileDialogDescription = SProgramTitle + "(*." + SAVE_FILE_EXTENSION + ")";	//	File관련 dialog Description
	public static final String[] SFileDialogTitle = {"파일저장", "변경 내용 확인"};		//	File관련 dialog 타이틀
	public static final String[] SFileDialogMessage = {"정말 저장하시겠습니까?", "변경된 사항을 저장하시겠습니까?"};	//	File관련 dialog 메세지
	private static final String BUTTON_PATH = "rsc\\";					//	버튼이미지 폴더 경로
	
	public static Cursor DEFAULT_CURSOR 	= new Cursor(Cursor.DEFAULT_CURSOR);
	public static Cursor DRAW_CURSOR 		= new Cursor(Cursor.CROSSHAIR_CURSOR);
	public static Cursor MM_CURSOR 			= new Cursor(Cursor.MOVE_CURSOR);
	public static Cursor RR_CURSOR 			= new Cursor(Cursor.HAND_CURSOR);
	public static Cursor EE_RESIZE_CURSOR 	= new Cursor(Cursor.E_RESIZE_CURSOR);
	public static Cursor WW_RESIZE_CURSOR 	= new Cursor(Cursor.W_RESIZE_CURSOR);
	public static Cursor SS_RESIZE_CURSOR 	= new Cursor(Cursor.S_RESIZE_CURSOR);
	public static Cursor NN_RESIZE_CURSOR 	= new Cursor(Cursor.N_RESIZE_CURSOR);
	public static Cursor SE_RESIZE_CURSOR 	= new Cursor(Cursor.SE_RESIZE_CURSOR);
	public static Cursor NE_RESIZE_CURSOR 	= new Cursor(Cursor.NE_RESIZE_CURSOR);
	public static Cursor SW_RESIZE_CURSOR 	= new Cursor(Cursor.SW_RESIZE_CURSOR);
	public static Cursor NW_RESIZE_CURSOR 	= new Cursor(Cursor.NW_RESIZE_CURSOR);
	
	public static enum EDrawingState{idle, drawingTP, drawingNP, moving};
	
	public static enum EButtons{
		Rectangle("rectangle.png", "rectangle_SLT.png", "rectangle_ROV.png", new GERectangle()),
		Ellipse("ellipse.png", "ellipse_SLT.png", "ellipse_ROV.png", new GEEllipse()),
		Polygon("polygon.png", "polygon_SLT.png", "polygon_ROV.png", new GEPolygon()),
//		Heart("heart.png", "heart_SLT.png", "heart_ROV.png", new GERectangle()),
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
		private GEMenu menu;
		
		private EMenus(String name, GEMenu menu){
			this.name = name;
			this.menu = menu;
		}
		public String getName(){return this.name;}
		public GEMenu getMenu(){return this.menu;}
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