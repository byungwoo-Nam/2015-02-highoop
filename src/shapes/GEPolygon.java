package shapes;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.util.Vector;

import javax.swing.JOptionPane;

import constants.GEConstants;

public class GEPolygon extends GEShape{
	
	private Polygon polygon;
	private Line2D.Double line;
	private boolean isComplete = true;
	protected Vector<Shape> vectorTempShape = new Vector<Shape>();
	protected Vector<Line2D.Double> vectorTempLine = new Vector<Line2D.Double>();
	
    public GEPolygon(){
    	super();
//    	JOptionPane.showMessageDialog(null, "polygon Ό³Έν");
    	shapeType = GEConstants.ShapeType.Complex;
    	polygon = new Polygon();
    	line = new Line2D.Double();
    	vectorShape.add(polygon);
    	vectorTempLine.add(line);
    }
    
    @Override
    public void draw(Graphics2D g2D){
    	if(isComplete){
    		for(Shape vector : vectorShape){
//    			polygon.addPoint(50,50);
        		g2D.draw(vector);
        	}
    	}else{
    		for(Shape vector : vectorTempLine){
        		g2D.draw(vector);
        	}
    	}
    }
    
    @Override
    public void tempDraw(Graphics2D g2D, Color color){
//    	polygon.addPoint(start.x, start.y);
    	this.isComplete = false;
    	g2D.setXORMode(color);
		draw(g2D);
    }
    
    @Override
    public void setBound(Point start, Point end){
//    	System.out.println("poly::" + polygon.npoints);
//    	vectorTempLine.add(new Line2D.Double());
//    	vectorTempLine.get(0).setLine(start, end);
    	line.setLine(start, end);
//    	polygon.
    }
}