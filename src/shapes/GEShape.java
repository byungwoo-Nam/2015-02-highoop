package shapes;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.util.Vector;

import constants.GEConstants;

public class GEShape {
	protected int x, y, w, h;
	protected Vector<Shape> vectorShape = new Vector<Shape>();
	protected GEConstants.ShapeType shapeType;
    public GEShape(){
    }
    public void draw(Graphics2D g2D){
    }
    public void tempDraw(Graphics2D g2D, Color color){
    }
    public void setBound(Point start, Point end){
    }
    public GEConstants.ShapeType getShapeType(){
    	return shapeType;
    }
}
