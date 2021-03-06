package transformer;

import java.awt.Graphics;
import java.awt.Point;

import shapes.GEShape;

public class GEResizer extends GETransformer {

	private Point origin;
	
	public GEResizer(GEShape shape) {
		super(shape);
	}

	@Override
	public void initTransforming(Graphics g, Point p) {
		origin = p;
	}

	@Override
	public void keepTransforming(Graphics g, Point p) {
		this.getShape().draw(g);
		this.getShape().resizeShape(new Point(p.x-origin.x, p.y-origin.y));
		this.getShape().draw(g);
		origin = p;
	}

	@Override
	public void continueTransforming(Graphics g, Point p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void finishTransforming(Graphics g, Point p) {
		// TODO Auto-generated method stub

	}

}
