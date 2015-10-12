package shapes;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import constants.GEConstants;

public class GEEllipse extends GEShape{
	
	private Ellipse2D.Double ellipse;
	
    public GEEllipse(){
    	super();
    	shapeType = GEConstants.ShapeType.Simple;
    	ellipse = new Ellipse2D.Double();
    	vectorShape.add(ellipse);
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
		ellipse.setFrame(x, y, w, h);
    }
}