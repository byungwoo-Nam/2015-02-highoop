package shapes;

import java.awt.Point;
import java.awt.geom.Ellipse2D;

public class GEEllipse extends GEShape{
	
	private static final long serialVersionUID = 1L;
	private Ellipse2D ellipse;
	
	public GEEllipse() {
		super(new Ellipse2D.Double());
		this.ellipse = (Ellipse2D) this.getShape();
	}
	
	@Override
	public void setPoint(Point p) {
		this.ellipse.setFrame(p.x, p.y, 0, 0);
	}
	@Override
	public void addPoint(Point p) {
		this.ellipse.setFrame(this.ellipse.getX(), this.ellipse.getY(), p.x-this.ellipse.getX(), p.y-this.ellipse.getY());
	}
	@Override
	public void movePoint(Point p) {
		this.ellipse.setFrame(this.ellipse.getX(), this.ellipse.getY(), p.x-this.ellipse.getX(), p.y-this.ellipse.getY());
	}
	@Override
	public void moveShape(Point p) {
		this.ellipse.setFrame(this.ellipse.getX()+p.x, this.ellipse.getY()+p.y, this.ellipse.getWidth(), this.ellipse.getHeight());
	}
	@Override
	public void resizeShape(Point p){
		this.ellipse.setFrame(this.ellipse.getX(), this.ellipse.getY(), this.ellipse.getWidth()+p.x, this.ellipse.getHeight()+p.y);
	}
}