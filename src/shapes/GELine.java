package shapes;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Line2D;

import constants.GEConstants;

public class GELine extends GEShape{
	
	private Line2D.Double line;
	
    public GELine(){
    	super();
    	shapeType = GEConstants.ShapeType.Simple;
    	line = new Line2D.Double();
    	vectorShape.add(line);
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
    	line.setLine(start, end);
    }
}