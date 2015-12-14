package transformer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

import shapes.GEShape;

public class GERotator extends GETransformer {

	private Point centerP;
	
	public GERotator(GEShape shape) {
		super(shape);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initTransforming(Graphics g, Point p) {
		centerP = new Point((int)shape.getShape().getBounds().getCenterX(), (int)shape.getShape().getBounds().getCenterY());
	}

	@Override
	public void keepTransforming(Graphics g, Point p) {
		Graphics2D g2D = (Graphics2D)g;
		this.getShape().draw(g2D);
		AffineTransform affineTransform = new AffineTransform();
	      
		//회전변환
		affineTransform.setToRotation(0.02, centerP.getX(), centerP.getY());
		this.getShape().setShape(affineTransform.createTransformedShape(getShape().getShape()));
		this.getShape().draw(g2D);
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
