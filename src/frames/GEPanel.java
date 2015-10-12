package frames;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import constants.GEConstants;
import shapes.GEShape;

public class GEPanel extends JPanel {
	
	private MouseAdapter mouseAdapter;
	private Point start, end;
	private Vector<GEShape> vectorGEShape = new Vector<GEShape>();
	
	// Working Mode
	private GEShape drawingShape;
	
	public GEPanel(){
		mouseAdapter = new MouseHandler();
		this.addMouseListener(mouseAdapter);
		this.addMouseMotionListener(mouseAdapter);
		start = end = null;
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		for(GEShape vector : vectorGEShape){
			vector.draw((Graphics2D)g);
		}
	}
	
	public void setDrawingShape(GEShape drawingShape){
		this.drawingShape = drawingShape;
	}
	
	public GEShape getDrawingShape(){
		return this.drawingShape;
	}
	
	public void initDrawing(int x, int y){
			start = new Point(x, y);
			end = new Point(x, y);
	}
	
	public void firstDrawing(MouseEvent e){
		if(this.drawingShape == null){
			JOptionPane.showMessageDialog(null, "사용할 툴을 선택해 주세요.");
		}else{
			Graphics2D g2D = (Graphics2D)this.getGraphics();
			end.x  = e.getX();
			end.y  = e.getY();
			if(Math.abs(start.x - end.x)!=0 && Math.abs(start.y - end.y)!=0){
				this.drawingShape.tempDraw(g2D, this.getBackground());
				this.drawingShape.setBound(start, end);
				this.drawingShape.tempDraw(g2D, this.getBackground());
			}
		}
	}
	
	// polygon에서 사용
	public void continueDrawing(int x, int y){
		if(this.drawingShape != null && this.drawingShape.getShapeType() == GEConstants.ShapeType.Complex && start != null){
			Graphics2D g2D = (Graphics2D)this.getGraphics();
			end.x  = x;
			end.y  = y;
			if(Math.abs(start.x - end.x)!=0 && Math.abs(start.y - end.y)!=0){
				this.drawingShape.tempDraw(g2D, this.getBackground());
				this.drawingShape.setBound(start, end);
				this.drawingShape.tempDraw(g2D, this.getBackground());
			}
		}
	}
	
	public void saveShape(){
		if(this.drawingShape != null){
			switch(this.drawingShape.getShapeType()){
				case Simple :
					start = end = null;
					vectorGEShape.add(this.drawingShape);
					try {
						this.drawingShape = this.drawingShape.getClass().newInstance();
					} catch (InstantiationException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					repaint();
					break;
				case Complex :
					break;
				default :
					break;
			}
		}
	}
	
	private class MouseHandler extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			// polygon사용
			saveShape();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			initDrawing(e.getX(), e.getY());
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			saveShape();
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			firstDrawing(e);
		}
		
		@Override
		public void mouseMoved(MouseEvent e) {
//			continueDrawing(e.getX(), e.getY());
		}
	}
}
