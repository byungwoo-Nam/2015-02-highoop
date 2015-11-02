package shapes;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

public class GEPolygon extends GEShape{
	
	private Polygon polygon;
	
	public GEPolygon() {
		super(new Polygon());
	}

	@Override
	public void initDrawing(Graphics g, Point p) {
		this.polygon = (Polygon)shape;
		this.polygon.addPoint(p.x, p.y);
		this.polygon.addPoint(p.x, p.y);
		this.draw(g);
	}

	@Override
	public void keepDrawing(Graphics g, Point p) {
		this.draw(g);
		this.polygon.xpoints[this.polygon.npoints-1] = p.x;
		this.polygon.ypoints[this.polygon.npoints-1] = p.y;
		this.draw(g);		
	}
	@Override
	public void continueDrawing(Graphics g, Point p) {
		this.polygon.addPoint(p.x, p.y);
	}

	@Override
	public void finishDrawing(Graphics g, Point p) {
	}
}