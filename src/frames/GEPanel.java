package frames;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JPanel;

import constants.GEConstant.EDrawingState;
import shapes.GEShape;

public class GEPanel extends JPanel {
	
	// attributes
	private static final long serialVersionUID = 1L;
	
	// components
	private MouseAdapter mouseAdapter;
	private Vector<GEShape> vectorGEShape;

	// working variables
	private GEShape currentShape;
	public void setCurrentShape(GEShape currentShape) { this.currentShape = currentShape; }
	
	public GEPanel(){
		mouseAdapter = new MouseHandler();
		this.addMouseListener(mouseAdapter);
		this.addMouseMotionListener(mouseAdapter);
		this.vectorGEShape = new Vector<GEShape>();
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		for(GEShape shape : vectorGEShape){
			shape.draw((Graphics2D)g);
		}
	}
	
	private void initDrawing(Point p) {
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
		this.vectorGEShape.add(currentShape);
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
		}
		
		private void mouse2Clicked(MouseEvent e){
			if (eDrawingState == EDrawingState.drawingNP) {
				finishDrawing(e.getPoint());
				eDrawingState = EDrawingState.idle;
			}
		}
		
		@Override
		public void mouseMoved(MouseEvent e){
		}
		
		@Override
		public void mousePressed(MouseEvent e){
			if (eDrawingState == EDrawingState.idle) {
				initDrawing(e.getPoint());
				eDrawingState = currentShape.getClass().getSimpleName().equals("GEPolygon") ? EDrawingState.drawingNP : EDrawingState.drawingTP;
			}
		}

		@Override
		public void mouseDragged(MouseEvent e){
			if (eDrawingState == EDrawingState.drawingTP || eDrawingState == EDrawingState.drawingNP) {
				keepDrawing(e.getPoint());
			}
		}
		
		@Override
		public void mouseReleased(MouseEvent e){
			if (eDrawingState == EDrawingState.drawingTP) {
				finishDrawing(e.getPoint());
				eDrawingState = EDrawingState.idle;
			}else if (eDrawingState == EDrawingState.drawingNP) {
				continueDrawing(e.getPoint());
			}
		}
	}
}
