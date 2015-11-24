package transformer;

import java.awt.Graphics;
import java.awt.Point;
import shapes.GEShape;

public class GEDrawer extends GETransformer {

	public GEDrawer(GEShape shape) {
		super(shape);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initTransforming(Graphics g, Point p) {
//		rectangle = (Rectangle) shape;
//		rectangle.setFrame(p.x, p.y, 0, 0);
//		originPoint.setLocation(p);
		this.getShape().setPoint(p);
		this.getShape().addPoint(p);
		this.getShape().draw(g);
	}

	@Override
	public void keepTransforming(Graphics g, Point p) {
		this.getShape().draw(g);

//		// ���콺�� �������� ��,�� ������ ��쿡�� �׷��ֱ� ����
//		int x = originPoint.x < p.x ? originPoint.x : p.x;
//		int y = originPoint.y < p.y ? originPoint.y : p.y;
//		int w = Math.abs(originPoint.x - p.x);
//		int h = Math.abs(originPoint.y - p.y);
//		
//    	rectangle.setFrame(x, y, w, h);
		this.getShape().movePoint(p);
		this.getShape().draw(g);		
	}

	@Override
	public void continueTransforming(Graphics g, Point p) {
	}

	@Override
	public void finishTransforming(Graphics g, Point p) {
	}

}
