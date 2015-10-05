public class GEConstants {
	public static enum Menus {
		File("File", GEConstants.FileMenuItems.values()), 
		Edit("Edit", GEConstants.EditMenuItems.values()), 
		Color("Color", GEConstants.ColorMenuItems.values());
		
		private String name;
		private Enum[] items;
		private Menus(String s, Enum[] e){
			this.name = s;
			this.items = e;
		}
		public String getName(){
			return this.name;
		}
		public Enum[] getItems(){
			return this.items;
		}
	};
	
	public static enum ToolBars {
		Oval("oval"), Rectangle("rectangle"), Line("line"), Polygon("polygon"), Heart("heart"), Text("text");
		private String name;
		private ToolBars(String s){
			this.name = s;
		}
		public String getName(){
			return this.name;
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
}