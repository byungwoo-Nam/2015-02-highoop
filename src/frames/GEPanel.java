package frames;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JPanel;

import constants.GEConstant;
import constants.GEConstant.EDrawingState;
import entity.GEModel;
import shapes.GEShape;
import transformer.GEDrawer;
import transformer.GETransformer;

public class GEPanel extends JPanel {
	
	// attributes
	private static final long serialVersionUID = 1L;
	
	// components
	private MouseAdapter mouseAdapter;
	private Vector<GEShape> vectorGEShape = new Vector<GEShape>();
	public Vector<GEShape> getVectorGEShape() { return this.vectorGEShape; }
	public void setVectorGEShape(Vector<GEShape> vectorGEShape) { this.vectorGEShape = vectorGEShape; 	}

	// association variables
	private GEShape currentShape;
	public void setCurrentShape(GEShape currentShape) { this.currentShape = currentShape; }
	public GEShape getCurrentShape() { return this.currentShape; }
	private GETransformer currentTransformer;
	
	// working variables
	private boolean editStatus;
	public boolean isEditStatus() { return editStatus; }
	
	public GEPanel(){
		mouseAdapter = new MouseHandler();
		this.addMouseListener(mouseAdapter);
		this.addMouseMotionListener(mouseAdapter);
		this.editStatus = false;
	}
	
	public void init() {
	}
	
	public void newGEShape(){
		this.setVectorGEShape(new Vector<GEShape>());
		try {
			this.setCurrentShape(this.getCurrentShape().getClass().newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void openGEShape(String fileName) {
		try {
			this.vectorGEShape = (Vector<GEShape>)GEModel.read(fileName);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.editStatus = false;
	}
	
	public void saveGEShape(String fileName) {
		try {
			GEModel.write(fileName, this.vectorGEShape);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.editStatus = false;
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		for(GEShape shape : this.getVectorGEShape()){
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
		this.editStatus = true;
		try {
			this.currentShape = this.currentShape.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		this.currentTransformer = new GEDrawer(this.currentShape);
		this.currentTransformer.initTransforming(this.getGraphics(), p);
//		this.currentShape.initDrawing(this.getGraphics(), p);	
	}
	private void keepDrawing(Point p) {
		this.currentTransformer.keepTransforming(this.getGraphics(), p);	
	}
	private void continueDrawing(Point p) {
		this.currentTransformer.continueTransforming(this.getGraphics(), p);	
	}
	private void finishDrawing(Point p) {
		this.currentTransformer.finishTransforming(this.getGraphics(), p);	
		this.getVectorGEShape().add(currentShape);
	}
	
	private void initMoving(Point p){
		this.editStatus = true;
		this.currentShape.initMoving(this.getGraphics(), p);
	}
	private void keepMoving(Point p){
		this.currentShape.keepMoving(this.getGraphics(), p);
	}
	private void finishMoving(Point p){
		this.currentShape.finishMoving(this.getGraphics(), p);
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