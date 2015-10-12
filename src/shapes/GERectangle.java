package shapes;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;

import constants.GEConstants;

public class GERectangle extends GEShape{
	
	private Rectangle rectangle;
	
    public GERectangle(){
    	super();
    	shapeType = GEConstants.ShapeType.Simple;
    	rectangle = new Rectangle();
    	vectorShape.add(rectangle);
    }
    
    @Override
    public void draw(Graphics2D g2D){
    	for(Shape vector : vectorShape){
    		g2D.draw(vector);
    	}
    }
    
    @Override
    public void tempDraw(Graphics2D g2D, Color color){
    	g2D.setXORMode(color);
		draw(g2D);
    }
    
    @Override
    public void setBound(Point start, Point end){
    	x = start.x < end.x ? start.x : end.x;
		y = start.y < end.y ? start.y : end.y;
		w = Math.abs(start.x - end.x);
		h = Math.abs(start.y - end.y);
    	rectangle.setBounds(x, y, w, h);
    }
}