package shapes;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Line2D;

public class GELine extends GEShape{
	
	private Line2D line;
	
    public GELine(){
    	super(new Line2D.Double());
    }
    
    @Override
	public void initDrawing(Graphics g, Point p) {
    	line = (Line2D.Double) shape;
		line.setLine(p, p);
		originPoint.setLocation(p);
		this.draw(g);
	}
	@Override
	public void keepDrawing(Graphics g, Point p) {
		this.draw(g);
		line.setLine(originPoint, p);
		this.draw(g);		
	}
	@Override
	public void finishDrawing(Graphics g, Point p) {
	}
	@Override
	public void continueDrawing(Graphics g, Point p) {
		// TODO Auto-generated method stub
	}
}