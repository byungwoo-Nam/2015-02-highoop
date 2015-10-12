package shapes;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import constants.GEConstants;

public class GEHeart extends GEShape{
	
	private Ellipse2D.Double ellipse1, ellipse2;
	private Line2D.Double line1;
	
    public GEHeart(){
    	super();
    	shapeType = GEConstants.ShapeType.Complex;
    	ellipse1 = new Ellipse2D.Double();
    	ellipse2 = new Ellipse2D.Double();
    	line1 = new Line2D.Double();
    	vectorShape.add(line1);
//    	vectorShape.add(ellipse2);
//    	Shape shape;
//    	shape.
//    	shape = new Rectangle();
    }
    
    @Override
    public void draw(Graphics2D g2D){
    	for(Shape vector : vectorShape){
//    		final AffineTransform rotate = AffineTransform.getRotateInstance(Math.toRadians(45), vector.getBounds2D().getX()+vector.getBounds2D().getWidth()/2, vector.getBounds2D().getY()+vector.getBounds2D().getHeight()/2);
//    		vector = rotate.createTransformedShape(vector);
    		g2D.draw(vector);
    	}
    }
    
    @Override
    public void tempDraw(Graphics2D g2D, Color color){
    	g2D.setXORMode(color);
//    	g2D.rotate(Math.toRadians(45));
		draw(g2D);
//		g2D.setColor(Color.GREEN);	
//		g2D.rotate(Math.toRadians(-95));
    }
    
    @Override
    public void setBound(Point start, Point end){
    	
//    	ellipse1.x = 300.0;
//    	ellipse1.y = 20.0;
//    	ellipse1.width = 50.0;
//    	ellipse1.height = 70.0;
//    	final AffineTransform rotate = AffineTransform.getRotateInstance(Math.toRadians(45), vectorShape.get(0).getBounds2D().getX()+vectorShape.get(0).getBounds2D().getWidth()/2, vectorShape.get(0).getBounds2D().getY()+vectorShape.get(0).getBounds2D().getHeight()/2);
//    	vectorShape.insertElementAt(rotate.createTransformedShape(vectorShape.get(0)), 0);
    	
//    	ellipse2.x = 500.0;
//    	ellipse2.y = 100.0;
//    	ellipse2.width = 50.0;
//    	ellipse2.height = 70.0;
    }
}