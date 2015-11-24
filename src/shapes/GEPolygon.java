package shapes;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;

public class GEPolygon extends GEShape{

	private static final long serialVersionUID = 1L;
	private Polygon polygon;
	
	public GEPolygon() {
		super(new Polygon());
		this.polygon = (Polygon) this.getShape();
	}
	
	@Override
	public void setPoint(Point p) {
		this.polygon.setLocation(p);
	}
	@Override
	public void addPoint(Point p) {
		this.polygon.setSize(p.x-this.polygon.x, p.y-this.polygon.y);
	}
	@Override
	public void movePoint(Point p) {
		this.polygon.setSize(p.x-this.polygon.x, p.y-this.polygon.y);
	}

//	@Override
//	public void initDrawing(Graphics g, Point p) {
//		polygon = (Polygon)shape;
//		polygon.addPoint(p.x, p.y);
//		polygon.addPoint(p.x, p.y);
//		this.draw(g);
//	}
//	@Override
//	public void keepDrawing(Graphics g, Point p) {
//		this.draw(g);
//		this.polygon.xpoints[this.polygon.npoints-1] = p.x;
//		this.polygon.ypoints[this.polygon.npoints-1] = p.y;
//		this.draw(g);		
//	}
//	@Override
//	public void continueDrawing(Graphics g, Point p) {
//		this.polygon.addPoint(p.x, p.y);
//	}
//	@Override
//	public void finishDrawing(Graphics g, Point p) {
//	}
	@Override
	public void initMoving(Graphics g, Point p) {
		// TODO Auto-generated method stub
		originPoint.setLocation(p);
	}
	@Override
	public void keepMoving(Graphics g, Point p) {
		// TODO Auto-generated method stub
		this.draw(g);
		polygon.translate(p.x-originPoint.x, p.y-originPoint.y);
		this.draw(g);
		originPoint.setLocation(p);
	}
	@Override
	public void finishMoving(Graphics g, Point p) {
		// TODO Auto-generated method stub
	}
}