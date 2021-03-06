package transformer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import shapes.GEShape;

public class GEDrawer extends GETransformer {

	public GEDrawer(GEShape shape) {
		super(shape);
	}

	@Override
	public void initTransforming(Graphics g, Point p) {
		this.getShape().setPoint(p);
		this.getShape().addPoint(p);
//		this.getShape().draw(g);
	}

	@Override
	public void keepTransforming(Graphics g, Point p) {
		this.getShape().draw(g);
		this.getShape().movePoint(p);
		this.getShape().draw(g);		
	}

	@Override
	public void continueTransforming(Graphics g, Point p) {
		this.getShape().addPoint(p);
	}

	@Override
	public void finishTransforming(Graphics g, Point p) {
	}

}
