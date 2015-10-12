package shapes;
import java.awt.Point;
import java.awt.Rectangle;

public class GEText extends GEShape{
	
	private Rectangle rectangle;
	
    public GEText(){
    	super();
    	rectangle = new Rectangle();
    	vectorShape.add(rectangle);
    }
    
    @Override
    public void setBound(Point start, Point end){
//    	rectangle.setBounds(x, y, w, h);
    }
}