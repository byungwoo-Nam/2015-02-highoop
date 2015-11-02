package shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;

abstract public class GEShape implements Cloneable{
	
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
    
    abstract public void initDrawing(Graphics g, Point p);
	abstract public void keepDrawing(Graphics g, Point p);
	abstract public void continueDrawing(Graphics g, Point p);
	abstract public void finishDrawing(Graphics g, Point p);
}
