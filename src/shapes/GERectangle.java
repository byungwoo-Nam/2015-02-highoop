package shapes;

import java.awt.Graphics;
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
	public void initMoving(Graphics g, Point p) {
		originPoint.setLocation(p);
	}
	@Override
	public void keepMoving(Graphics g, Point p) {
		this.draw(g);
		rectangle.setLocation(rectangle.x+p.x-originPoint.x, rectangle.y+p.y-originPoint.y);
		this.draw(g);
		originPoint.setLocation(p);
	}
	@Override
	public void finishMoving(Graphics g, Point p) {
		// TODO Auto-generated method stub
	}
}