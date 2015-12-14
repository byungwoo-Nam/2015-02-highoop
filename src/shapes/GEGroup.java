package shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

public class GEGroup extends GERectangle {
	private Vector<GEShape> shapes;
	
	private static final long serialVersionUID = 1L;
	private Rectangle rectangle;
	
    public GEGroup(){
    	super();
    	this.rectangle = (Rectangle) this.getShape();
    }
    
    public void draw(Graphics g){
    	Graphics2D g2D = (Graphics2D) g;
    	g2D.setXORMode(g2D.getBackground());
    	Rectangle boundingRect = new Rectangle();
    	for(GEShape shape : this.shapes){
			g2D.draw(shape.getShape());
			if(this.isSelected()){
				boundingRect = boundingRect.union(shape.getShape().getBounds());
			}
		}
    	if(this.isSelected()){
    		g2D.draw(boundingRect);
    		this.getAnchors().setAnchorGeo(new Point(boundingRect.x, boundingRect.y), new Point(boundingRect.width, boundingRect.height));
			this.getAnchors().draw(g2D);
    	}
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
		for(GEShape shape : this.shapes){
			shape.movePoint(p);
		}
	}
	@Override
	public void moveShape(Point p) {
		this.rectangle.setLocation(this.rectangle.x+p.x, this.rectangle.y+p.y);
	}
	@Override
	public void resizeShape(Point p){
		this.rectangle.width = this.rectangle.width + p.x;
		this.rectangle.height = this.rectangle.height + p.y;
		for(GEShape shape : this.shapes){
			shape.resizeShape(p);
		}
	}
	public boolean addShape(GEShape shape){
		if(this.rectangle.contains(shape.getShape().getBounds())){
			this.shapes.add(shape);
			return true;
		}
		return false;
	}
}
