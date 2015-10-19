package constants;
import javax.swing.JMenu;

import menus.GEColorMenu;
import menus.GEEditMenu;
import menus.GEFileMenu;
import shapes.*;

public class GEConstants {
	final private static String ICON_EXTENSION = ".png";	//	icon확장자
	final private static String ICON_PATH = "rsc/";			//	icon 폴더 경로
	
	public enum ICON_KIND{
		DEFAULT(""), 
		ROLLOVER("ROV"), 
		SELECTED("SLT");
		
		private String name;
		
		private ICON_KIND(String name){
			this.name = name;
		}
		
		public String getName(){
			return this.name;
		}
	};
	
	public static enum Menus {
		File("File", new GEFileMenu()), 
		Edit("Edit", new GEEditMenu()), 
		Color("Color", new GEColorMenu());
		
		private String name;
		private JMenu menu;
		
		private Menus(String name, JMenu menu){
			this.name = name;
			this.menu = menu;
		}
		public String getName(){
			return this.name;
		}
		public JMenu getMenu(){
			return this.menu;
		}
	};
	
	public static enum ToolBars {
		Rectangle("rectangle", GERectangle.class.getName()), 
		Ellipse("ellipse", GEEllipse.class.getName()),
		Polygon("polygon", GEPolygon.class.getName()),
		Heart("heart", GEHeart.class.getName()),
		Line("line", GELine.class.getName()),
		Text("text", GEText.class.getName());
		
		private String name;
		private String className;
		private ToolBars(String name, String className){
			this.name = name;
			this.className = className;
		}
		public String getName(){
			return this.name;
		}
		public String getClassName(){
			return this.className;
		}
		public String getIconFilePath(ICON_KIND i){
			if(i == ICON_KIND.DEFAULT){
				return  ICON_PATH + this.getName() + ICON_EXTENSION;
			}else{
				return  ICON_PATH + this.getName() + "_" + i.getName() + ICON_EXTENSION;
			}
		}
	};
	
	public static enum FileMenuItems{
		New("New"), Open("Open"), Save("Save"), Save_As("Save_As"), Exit("Exit");
		private String name;
		private FileMenuItems(String s){
			this.name = s;
		}
		public String getName(){
			return this.name;
		}
	};
	
	public static enum EditMenuItems{
		Undo("Undo"), Redo("Redo"), Cut("Cut"), Copy("Copy"), Paste("Paste"), Delete("Delete"), Select_all("Select_all"), Deselect_all("Deselect_all");
		private String name;
		private EditMenuItems(String s){
			this.name = s;
		}
		public String getName(){
			return this.name;
		}
	};
	
	public static enum ColorMenuItems{
		Line_color("Line_color"), Fill_color("Fill_color");
		private String name;
		private ColorMenuItems(String s){
			this.name = s;
		}
		public String getName(){
			return this.name;
		}
	};
	
	public static enum ShapeType{Simple, Complex}
}