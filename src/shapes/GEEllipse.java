package shapes;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Ellipse2D;


public class GEEllipse extends GEShape{
	
	private Ellipse2D ellipse;
	
	public GEEllipse() {
		super(new Ellipse2D.Double());
	}
	
	@Override
	public void initDrawing(Graphics g, Point p) {
		ellipse = (Ellipse2D) shape;
		ellipse.setFrame(p.x, p.y, 0, 0);
		originPoint.setLocation(p);
		this.draw(g);
	}
	@Override
	public void keepDrawing(Graphics g, Point p) {
		this.draw(g);

		// 마우스가 원점에서 좌,상 방향일 경우에도 그려주기 위해
		int x = originPoint.x < p.x ? originPoint.x : p.x;
		int y = originPoint.y < p.y ? originPoint.y : p.y;
		int w = Math.abs(originPoint.x - p.x);
		int h = Math.abs(originPoint.y - p.y);
		
		ellipse.setFrame(x, y, w, h);
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