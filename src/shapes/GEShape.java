package shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.io.Serializable;

abstract public class GEShape implements Cloneable, Serializable{
	
	private static final long serialVersionUID = 1L;
	protected Shape shape;
	protected Point originPoint;
	
    public GEShape(Shape shape){
    	this.shape = shape;
    	originPoint = new Point();
    }
    public void draw(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		g2D.setXORMode(g2D.getBackground());
		g2D.draw(shape);		
	}
    public boolean onShape(Point p){
		return this.shape.contains(p);
	}
    
    abstract public void initDrawing(Graphics g, Point p);
	abstract public void keepDrawing(Graphics g, Point p);
	abstract public void continueDrawing(Graphics g, Point p);
	abstract public void finishDrawing(Graphics g, Point p);
	abstract public void initMoving(Graphics g, Point p);
	abstract public void keepMoving(Graphics g, Point p);
	abstract public void finishMoving(Graphics g, Point p);
}
