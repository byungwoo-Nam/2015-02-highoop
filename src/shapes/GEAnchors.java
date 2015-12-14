package shapes;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.Vector;

import shapes.GEShape.EAnchors;

public class GEAnchors implements Serializable{
	private final int AW = 4;
	private final int AH = 4;
	
	private Vector<Rectangle> anchors;
	
	public GEAnchors(){
		this.anchors = new Vector<Rectangle>();
		for(int i=0; i<EAnchors.values().length-1; i++){
			anchors.add(new Rectangle());
		}
	}
	
	public void setAnchorGeo(Point loc_p, Point wh_p){		// loc_p : x, y  wh_p : w, h
		int d = AW/2;
		this.anchors.get(EAnchors.EE.ordinal()).setBounds		(	loc_p.x+wh_p.x-d, 		loc_p.y+wh_p.y/2-d,		AW, 	AH	);
		this.anchors.get(EAnchors.WW.ordinal()).setBounds	(	loc_p.x-d, 				loc_p.y+wh_p.y/2-d,		AW, 	AH	);
		this.anchors.get(EAnchors.NN.ordinal()).setBounds	(	loc_p.x+wh_p.x/2-d,		loc_p.y-d, 				AW, 	AH	);
		this.anchors.get(EAnchors.SS.ordinal()).setBounds		(	loc_p.x+wh_p.x/2-d,		loc_p.y+wh_p.y-d, 		AW, 	AH	);
		this.anchors.get(EAnchors.NE.ordinal()).setBounds	(	loc_p.x+wh_p.x-d, 		loc_p.y-d, 				AW, 	AH	);
		this.anchors.get(EAnchors.NW.ordinal()).setBounds	(	loc_p.x-d, 				loc_p.y-d, 				AW, 	AH	);
		this.anchors.get(EAnchors.SE.ordinal()).setBounds		(	loc_p.x+wh_p.x-d, 		loc_p.y+wh_p.y-d, 		AW, 	AH	);
		this.anchors.get(EAnchors.SW.ordinal()).setBounds	(	loc_p.x-d, 				loc_p.y+wh_p.y-d, 		AW, 	AH	);
		this.anchors.get(EAnchors.RR.ordinal()).setBounds		(	loc_p.x+wh_p.x/2-d,		loc_p.y-wh_p.y/2-d, 		AW, 	AH	);
	}
	
	public void draw(Graphics2D g2D){
		for(int i=0; i<EAnchors.values().length-1; i++){
			g2D.draw(this.anchors.get(i));
		}
	}
	
	public EAnchors onAnchors(Point p){
		for(int i=0; i<EAnchors.values().length-1; i++){
			if(this.anchors.get(i).contains(p)){
				return EAnchors.values()[i];
			}
		}
		return null;
	}

}
