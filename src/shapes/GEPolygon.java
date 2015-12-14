package shapes;

import java.awt.Point;
import java.awt.Polygon;

public class GEPolygon extends GEShape{

	private static final long serialVersionUID = 1L;
	private Polygon polygon;
	public GEPolygon() {
		super(new Polygon());
		this.polygon = (Polygon) this.getShape();
	}
	@Override
	public void setPoint(Point p) {
		this.polygon.addPoint(p.x, p.y);
	}
	@Override
	public void addPoint(Point p) {
		this.polygon.addPoint(p.x, p.y);
	}
	@Override
	public void movePoint(Point p) {
		this.polygon.xpoints[this.polygon.npoints-1] = p.x;
		this.polygon.ypoints[this.polygon.npoints-1] = p.y;
	}
	@Override
	public void moveShape(Point p) {
		for (int i=0; i<this.polygon.npoints; i++) {
			this.polygon.xpoints[i] = this.polygon.xpoints[i]+p.x;
			this.polygon.ypoints[i] = this.polygon.ypoints[i]+p.y;
		}
	}
	@Override
	public void resizeShape(Point p) {
		// TODO Auto-generated method stub
	}

}