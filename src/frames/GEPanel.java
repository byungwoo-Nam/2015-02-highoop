package frames;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import constants.GEConstant.EDrawingState;
import entity.GEModelShape;
import shapes.GEShape;

public class GEPanel extends JPanel {
	
	// attributes
	private static final long serialVersionUID = 1L;
	
	// components
	private MouseAdapter mouseAdapter;

	// working variables
	private GEShape currentShape;
	public void setCurrentShape(GEShape currentShape) { this.currentShape = currentShape; }
	public GEShape getCurrentShape() { return this.currentShape; }
	private boolean editStatus;
	
	public GEPanel(){
		mouseAdapter = new MouseHandler();
		this.addMouseListener(mouseAdapter);
		this.addMouseMotionListener(mouseAdapter);
		this.setEditStatus(false);
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		for(GEShape shape : GEModelShape.getVectorGEShape()){
			shape.draw((Graphics2D)g);
		}
	}
	
	private GEShape onShape(Point p){
		if(currentShape.onShape(p)){
			return currentShape;
		}
		return null;
	}
	
	private void initDrawing(Point p) {
		this.setEditStatus(true);
		try {
			this.currentShape = this.currentShape.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		this.currentShape.initDrawing(this.getGraphics(), p);	
	}
	private void keepDrawing(Point p) {
		this.currentShape.keepDrawing(this.getGraphics(), p);	
	}
	private void continueDrawing(Point p) {
		this.currentShape.continueDrawing(this.getGraphics(), p);	
	}
	private void finishDrawing(Point p) {
		this.currentShape.finishDrawing(this.getGraphics(), p);	
		GEModelShape.getVectorGEShape().add(currentShape);
	}
	
	private void initMoving(Point p){
		this.currentShape.initMoving(this.getGraphics(), p);
	}
	private void keepMoving(Point p){
		this.currentShape.keepMoving(this.getGraphics(), p);
	}
	private void finishMoving(Point p){
		this.currentShape.finishMoving(this.getGraphics(), p);
	}
	
	public boolean isEditStatus() {
		return editStatus;
	}

	public void setEditStatus(boolean editStatus) {
		this.editStatus = editStatus;
	}
	
	private class MouseHandler extends MouseAdapter{
		
		private EDrawingState eDrawingState = EDrawingState.idle;
		
		@Override
		public void mouseClicked(MouseEvent e){
			if(e.getClickCount() == 1){
				mouse1Clicked(e);
			}else if(e.getClickCount() == 2){
				mouse2Clicked(e);
			}
		}
		
		private void mouse1Clicked(MouseEvent e){
			if (eDrawingState == EDrawingState.idle && currentShape.getClass().getSimpleName().equals("GEPolygon")) {
				initDrawing(e.getPoint());
				eDrawingState = EDrawingState.drawingNP;
			} else if (eDrawingState == EDrawingState.drawingNP) {
				continueDrawing(e.getPoint());
			}
		}
		
		private void mouse2Clicked(MouseEvent e){
			if (eDrawingState == EDrawingState.drawingNP) {
				finishDrawing(e.getPoint());
				eDrawingState = EDrawingState.idle;
			}
		}
		
		@Override
		public void mouseMoved(MouseEvent e){
			if (eDrawingState == EDrawingState.drawingNP) {
				keepDrawing(e.getPoint());
			}
		}
		
		@Override
		public void mousePressed(MouseEvent e){
			if (eDrawingState == EDrawingState.idle && !currentShape.getClass().getSimpleName().equals("GEPolygon") && onShape(e.getPoint()) == null) {
				initDrawing(e.getPoint());
				eDrawingState = EDrawingState.drawingTP;
			}else if(eDrawingState == EDrawingState.idle){
				initMoving(e.getPoint());
				eDrawingState = EDrawingState.moving;
			}
		}

		@Override
		public void mouseDragged(MouseEvent e){
			if (eDrawingState == EDrawingState.drawingTP) {
				keepDrawing(e.getPoint());
			}else if(eDrawingState == EDrawingState.moving){
				keepMoving(e.getPoint());
			}
		}
		
		@Override
		public void mouseReleased(MouseEvent e){
			if (eDrawingState == EDrawingState.drawingTP) {
				finishDrawing(e.getPoint());
				eDrawingState = EDrawingState.idle;
			}else if(eDrawingState == EDrawingState.moving){
				finishMoving(e.getPoint());
				eDrawingState = EDrawingState.idle;
			}
		}
	}
}