package shapes;

import java.awt.Point;
import java.awt.geom.Line2D;

public class GELine extends GEShape{
	
	private static final long serialVersionUID = 1L;
	private Line2D line;
	
    public GELine(){
    	super(new Line2D.Double());
    	this.line = (Line2D) this.getShape();
    }
    
    @Override
	public void setPoint(Point p) {
    	this.line.setLine(p.x, p.y, p.x, p.y);
	}
	@Override
	public void addPoint(Point p) {
	}
	@Override
	public void movePoint(Point p) {
		line.setLine(line.getX1(), line.getY1(), p.x, p.y);
	}
	@Override
	public void moveShape(Point p) {
	}

	@Override
	public void resizeShape(Point p) {
		// TODO Auto-generated method stub
		
	}
    
//    @Override
//	public void initDrawing(Graphics g, Point p) {
//    	line = (Line2D.Double) shape;
//		line.setLine(p, p);
//		originPoint.setLocation(p);
//		this.draw(g);
//	}
//	@Override
//	public void keepDrawing(Graphics g, Point p) {
//		this.draw(g);
//		line.setLine(originPoint, p);
//		this.draw(g);		
//	}
//	@Override
//	public void finishDrawing(Graphics g, Point p) {
//	}
//	@Override
//	public void continueDrawing(Graphics g, Point p) {
//		// TODO Auto-generated method stub
//	}
//
//	@Override
//	public void initMoving(Graphics g, Point p) {
//		// TODO Auto-generated method stub
//	}
//	@Override
//	public void keepMoving(Graphics g, Point p) {
//		// TODO Auto-generated method stub
//	}
//	@Override
//	public void finishMoving(Graphics g, Point p) {
//		// TODO Auto-generated method stub
//	}
}