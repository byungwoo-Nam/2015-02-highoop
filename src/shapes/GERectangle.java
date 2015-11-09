package shapes;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class GERectangle extends GEShape{
	
	private static final long serialVersionUID = 1L;
	private Rectangle rectangle;
	
    public GERectangle(){
    	super(new Rectangle());
    }
    
	@Override
	public void initDrawing(Graphics g, Point p) {
		rectangle = (Rectangle) shape;
		rectangle.setFrame(p.x, p.y, 0, 0);
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
		
    	rectangle.setFrame(x, y, w, h);
		this.draw(g);		
	}
	@Override
	public void finishDrawing(Graphics g, Point p) {
	}
	@Override
	public void continueDrawing(Graphics g, Point p) {
		// TODO Auto-generated method stub
	}
	@Override
	public void initMoving(Graphics g, Point p) {
		originPoint.setLocation(p);
	}
	@Override
	public void keepMoving(Graphics g, Point p) {
		this.draw(g);
		rectangle.setLocation(rectangle.x+p.x-originPoint.x, rectangle.y+p.y-originPoint.y);
		this.draw(g);
		originPoint.setLocation(p);
	}
	@Override
	public void finishMoving(Graphics g, Point p) {
		// TODO Auto-generated method stub
	}
}