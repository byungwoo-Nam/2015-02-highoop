package shapes;

import java.awt.Point;
import java.awt.Rectangle;

public class GERectangle extends GEShape{
	
	private static final long serialVersionUID = 1L;
	private Rectangle rectangle;
	
    public GERectangle(){
    	super(new Rectangle());
    	this.rectangle = (Rectangle) this.getShape();
    }
    
	@Override
	public void setPoint(Point p) {
		this.rectangle.setLocation(p);
	}
	@Override
	public void addPoint(Point p) {
		this.rectangle.setSize(p.x-this.rectangle.x, p.y-this.rectangle.y);
	}
	@Override
	public void movePoint(Point p) {
		this.rectangle.setSize(p.x-this.rectangle.x, p.y-this.rectangle.y);
	}
	@Override
	public void moveShape(Point p) {
		this.rectangle.setLocation(this.rectangle.x+p.x, this.rectangle.y+p.y);
	}
	@Override
	public void resizeShape(Point p){
		this.rectangle.width = this.rectangle.width + p.x;
		this.rectangle.height = this.rectangle.height + p.y;
	}
}