package transformer;

import java.awt.Graphics;
import java.awt.Point;

import shapes.GEShape;

public abstract class GETransformer {
	private GEShape shape;
	// drawing, moving, resizing, rotating
	public GETransformer(GEShape shape) {
		this.shape = shape;
	}
	public GEShape getShape() {return shape;}
	
	abstract public void initTransforming(Graphics g, Point p);
	abstract public void keepTransforming(Graphics g, Point p);
	abstract public void continueTransforming(Graphics g, Point p);
	abstract public void finishTransforming(Graphics g, Point p);
}
