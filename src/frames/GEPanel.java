package frames;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JPanel;

import constants.GEConstant;
import constants.GEConstant.EDrawingState;
import entity.GEModel;
import shapes.GEGroup;
import shapes.GEShape;
import shapes.GEShape.EAnchors;
import transformer.GEDrawer;
import transformer.GEMover;
import transformer.GEResizer;
import transformer.GERotator;
import transformer.GETransformer;

public class GEPanel extends JPanel implements Cloneable {
	
	// attributes
	private static final long serialVersionUID = 1L;
	private boolean bUpdated;
	public boolean isUpdated() {return bUpdated;}
	
	// components
	private MouseAdapter mouseAdapter;
	
	private Vector<GEShape> shapes;
	public Vector<GEShape> getShapes() {return this.shapes;}
	public void setShapes(Vector<GEShape> shapes) {this.shapes = shapes;}
	
	// association variables
	private GEShape currentTool;
	public void setCurrentTool(GEShape currentTool) { this.currentTool = currentTool; }
	
	// working variables
	private GETransformer currentTransformer;
	private GEShape selectedShape, copyShape;
	
	private void setSelectedShape(GEShape selectedShape) {
		if (this.selectedShape != null) {
			this.selectedShape.setSelected(false);
		}
		this.selectedShape = selectedShape;
		if (this.selectedShape != null) {
			this.selectedShape.setSelected(true);
		}
	}
	
	private GEShape getSelectedShape() {return this.selectedShape; }
	
	private GEShapeStack undoStack, redoStack;
	
	public GEPanel(){
		// class attributes
		bUpdated = false;
		
		// create components
		this.shapes = new Vector<GEShape>();
		
		// create comonents
		this.mouseAdapter = new MouseHandler();
		this.addMouseListener(mouseAdapter);
		this.addMouseMotionListener(mouseAdapter);
		
		this.undoStack = new GEShapeStack();
		this.redoStack = new GEShapeStack();
	}
	
	public void init() {
	}
	
	public void redo(){
		if(this.shapes!=null && this.redoStack.getSize()>0){
			this.undoStack.push(this.shapes);
			this.shapes = this.redoStack.pop();
			repaint();
		}
	}
	
	public void undo(){
		if(this.shapes!=null && this.undoStack.getSize()>0){
			this.redoStack.push(this.shapes);
			this.shapes = this.undoStack.pop();
			repaint();
		}
	}
	
	public void copyShape(){
		if(getSelectedShape() != null){
			copyShape = (GEShape) GEModel.deepClone(getSelectedShape());
		}
	}
	
	public void cutShape(){
		if(getSelectedShape() != null){
			copyShape = (GEShape) GEModel.deepClone(getSelectedShape());
			this.shapes.removeElement(getSelectedShape());
			repaint();
		}
	}
	
	public void pasteShape(){
		if(copyShape != null){
			this.setSelected();
			this.undoStack.push(this.shapes);
			this.redoStack.init();
			repaint();
			
			if(equalShape(copyShape)){
				currentTransformer = new GEMover(this.copyShape);
				initTransforming(this.copyShape.getShape().getBounds().getLocation());
				keepTransforming(new Point(this.copyShape.getShape().getBounds().x+10,this.copyShape.getShape().getBounds().y+10));
			}
			
			this.getShapes().add(this.copyShape);
			
			this.copyShape = (GEShape) GEModel.deepClone(this.copyShape);
		}
	}
	
	public void deleteShape(){
		if(getSelectedShape() != null){
			this.shapes.removeElement(getSelectedShape());
			repaint();
		}
	}
	
	public void lineColor(){
		if(getSelectedShape() != null){
			getSelectedShape().setLineColor(Color.GREEN);
			repaint();
		}
	}
	
	public void fillColor(){
		if(getSelectedShape() != null){
			getSelectedShape().setFillColor(Color.GREEN);
			repaint();
		}
	}
	
	public void newShapes() {
		this.shapes.removeAllElements();
		this.repaint();		
	}
	
	@SuppressWarnings("unchecked")
	public void readShapes(String fileName) {
		try {
			this.shapes = (Vector<GEShape>) GEModel.read(fileName);
			bUpdated = false;		
			this.repaint();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}	
	
	public void saveShapes(String fileName) {
		try {
			GEModel.save(fileName, this.shapes);
			bUpdated = false;		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		for(GEShape shape : this.getShapes()){
			shape.draw(g);
		}
	}
	
	// check whether the mouse pointer is on a shape or not
	private GEShape onShape(Point p){
		for(GEShape shape : this.getShapes()){
			if (shape.onShape(p)) {
				return shape;
			}
		}
		return null;
	}
	
	private boolean equalShape(GEShape s){
		for(GEShape shape : this.getShapes()){
			if (shape.getShape().equals(s.getShape())) {
				return true;
			}
		}
		return false;
	}
	
	private void setSelected(){
		for (GEShape shape: this.shapes) {
			shape.setSelected(false);
		}
		this.getSelectedShape().setSelected(true);
	}
	
	private void initTransforming(Point p) {
		this.currentTransformer.initTransforming(this.getGraphics(), p);
	}
	private void keepTransforming(Point p) {
		this.currentTransformer.keepTransforming(this.getGraphics(), p);	
	}
	private void continueTransforming(Point p) {
		this.currentTransformer.continueTransforming(this.getGraphics(), p);	
	}
	private void finishTransforming(Point p) {
		this.currentTransformer.finishTransforming(this.getGraphics(), p);
		bUpdated = true;
		this.setSelected();
		this.undoStack.push(this.shapes);
		this.redoStack.init();
		this.repaint();
		
		if (this.currentTransformer.getClass().getSimpleName().equals("GEDrawer")) {
			if (this.getSelectedShape().getClass().getSimpleName().equals("GEGroup")) {
				GEGroup group = (GEGroup) this.getSelectedShape();
				
				for (GEShape shape: this.shapes) {
					if (group.addShape(shape)) {
						this.shapes.remove(shape);
					}
				}
			}
			this.getShapes().add(getSelectedShape());
		}
		System.out.println(this.shapes.get(0).getShape());
	}
	
	private void setCursor(Point p){
		GEShape shape = onShape(p);
		if(shape == null){
			this.setCursor(GEConstant.DEFAULT_CURSOR);
		}else{
			switch(shape.geteSelectedAnchor()){
				case NN : setCursor(GEConstant.NN_RESIZE_CURSOR); break;
				case SS : setCursor(GEConstant.SS_RESIZE_CURSOR); break;
				case EE : setCursor(GEConstant.EE_RESIZE_CURSOR); break;
				case WW : setCursor(GEConstant.WW_RESIZE_CURSOR); break;
				case NE : setCursor(GEConstant.NE_RESIZE_CURSOR); break;
				case NW : setCursor(GEConstant.NW_RESIZE_CURSOR); break;
				case SE : setCursor(GEConstant.SE_RESIZE_CURSOR); break;
				case SW : setCursor(GEConstant.SW_RESIZE_CURSOR); break;
				case RR : setCursor(GEConstant.RR_CURSOR); break;
				case MM : setCursor(GEConstant.MM_CURSOR); break;
			}
		}
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
			if (eDrawingState == EDrawingState.idle) {
				if (currentTool.getClass().getSimpleName().equals("GEPolygon")) {
					try {
						setSelectedShape(currentTool.getClass().newInstance());
					} catch (InstantiationException | IllegalAccessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					currentTransformer = new GEDrawer(getSelectedShape());
					initTransforming(e.getPoint());
					eDrawingState = EDrawingState.drawingNP;
				}
			} else if (eDrawingState == EDrawingState.drawingNP) {
				continueTransforming(e.getPoint());
			}
		}
		
		private void mouse2Clicked(MouseEvent e){
			if (eDrawingState == EDrawingState.drawingNP) {
				finishTransforming(e.getPoint());
				eDrawingState = EDrawingState.idle;
			}
		}
		
		@Override
		public void mouseMoved(MouseEvent e){
			if(eDrawingState == EDrawingState.idle) {
				setCursor(e.getPoint());
			}else if(eDrawingState == EDrawingState.drawingNP) {
				keepTransforming(e.getPoint());
			}
		}
		
		@Override
		public void mousePressed(MouseEvent e){
			try {
				if (eDrawingState == EDrawingState.idle){
					// 마우스 밑에 그림이 있는지 확인
					setSelectedShape(onShape(e.getPoint()));
					// 그림이 없으면
					if (getSelectedShape() == null) {
						// 도구가 Polygon이 아니면
						setSelectedShape(currentTool.getClass().newInstance());
						currentTransformer = new GEDrawer(getSelectedShape());
						if (!currentTool.getClass().getSimpleName().equals("GEPolygon")) {
							// 그림 그리기 시작
							initTransforming(e.getPoint());
							eDrawingState = EDrawingState.drawingTP;
						}
						// 그림이 밑에 있으면
					} else {
						// 움직이기 시작						
						if (getSelectedShape().geteSelectedAnchor()==EAnchors.MM) {
							currentTransformer = new GEMover(getSelectedShape());
						} else if (getSelectedShape().geteSelectedAnchor()==EAnchors.RR) {
							currentTransformer = new GERotator(getSelectedShape());							
						} else {
							currentTransformer = new GEResizer(getSelectedShape());							
						}
						initTransforming(e.getPoint());
						eDrawingState = EDrawingState.moving;
					}
				}
			} catch (InstantiationException | IllegalAccessException err) {
				err.printStackTrace();
			}
		}

		@Override
		public void mouseDragged(MouseEvent e){
			if (eDrawingState == EDrawingState.drawingTP) {
				keepTransforming(e.getPoint());
			}else if(eDrawingState == EDrawingState.moving){
				keepTransforming(e.getPoint());
			}
		}
		
		@Override
		public void mouseReleased(MouseEvent e){
			if (eDrawingState == EDrawingState.drawingTP) {
				finishTransforming(e.getPoint());
				eDrawingState = EDrawingState.idle;
			}else if(eDrawingState == EDrawingState.moving){
				finishTransforming(e.getPoint());
				eDrawingState = EDrawingState.idle;
			}
		}
	}

}