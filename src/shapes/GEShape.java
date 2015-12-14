package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.io.Serializable;

abstract public class GEShape implements Cloneable, Serializable{
	
	private static final long serialVersionUID = 1L;
	public static enum EAnchors {NN, SS, EE, WW, NE, NW, SE, SW, RR, MM};
	private GEAnchors anchors;
    protected GEAnchors getAnchors() {return anchors;}
	public EAnchors geteSelectedAnchor() {return eSelectedAnchor;}
	private EAnchors eSelectedAnchor;
	private boolean selected;
	public boolean isSelected() {return selected;}
	public void setSelected(boolean selected) {
		this.selected = selected;
		this.anchors = this.selected ? new GEAnchors() : null;
	}
	private Shape shape;
	private Point originPoint;
	public Shape getShape() { return this.shape; }
	public void setShape(Shape shape) { this.shape = shape; }
	public Point getOriginPoint() { return this.originPoint; }
	
	protected Color lineColor, fillColor;
	public void setLineColor(Color lineColor) { this.lineColor = lineColor;}
	public void setFillColor(Color fillColor) { this.lineColor = fillColor;}
	
    public GEShape(Shape shape){
    	this.shape = shape;
    	this.eSelectedAnchor = null;
    	this.lineColor = Color.black;
    	this.fillColor = Color.black;
    }

	public void draw(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		g2D.setXORMode(g2D.getBackground());
		g2D.draw(shape);
		if(this.selected){
			this.anchors = new GEAnchors();
			Rectangle boundingRect = shape.getBounds();
			this.anchors.setAnchorGeo(new Point(boundingRect.x, boundingRect.y), new Point(boundingRect.width, boundingRect.height));
			this.anchors.draw(g2D);
		}else{
			this.anchors = null;
		}
	}
    
    public boolean onShape(Point p){
    	if(this.selected){
    		this.eSelectedAnchor = this.anchors.onAnchors(p);
    		if(this.eSelectedAnchor != null){
    			return true;
    		}
    	}
    	
    	if(this.shape.contains(p)){
    		this.eSelectedAnchor = EAnchors.MM;
    		return true;
    	}
    	return false;
	}
    
    abstract public void setPoint(Point p);
	abstract public void addPoint(Point p);
	abstract public void movePoint(Point p);
	abstract public void moveShape(Point p);
	abstract public void resizeShape(Point p);
}
