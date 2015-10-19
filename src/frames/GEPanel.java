package frames;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import shapes.GEShape;

public class GEPanel extends JPanel {
	
	private MouseAdapter mouseAdapter;
	private Point start, end;
	private Vector<GEShape> vectorGEShape = new Vector<GEShape>();
	private enum DrawState{IDLE,DRAWING};
	private DrawState ds;
	
	private GEShape drawingShape;
	
	public GEPanel(){
		mouseAdapter = new MouseHandler();
		this.addMouseListener(mouseAdapter);
		this.addMouseMotionListener(mouseAdapter);
		ds = DrawState.IDLE;
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
	
	public DrawState initDrawing(MouseEvent e){
		if(this.drawingShape == null){
			JOptionPane.showMessageDialog(null, "사용할 툴을 선택해 주세요.");
			return ds;
		}else{
			start = new Point(e.getPoint());
			end = new Point(e.getPoint());
			return DrawState.DRAWING;
		}
	}
	
	public void keepDrawing(MouseEvent e){
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		end.x  = e.getX();
		end.y  = e.getY();
		if(Math.abs(start.x - end.x)!=0 && Math.abs(start.y - end.y)!=0){
			this.drawingShape.tempDraw(g2D, this.getBackground());
			this.drawingShape.setBound(start, end);
			this.drawingShape.tempDraw(g2D, this.getBackground());
		}
	}
	
	public void saveShape(){
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
	
	private class MouseHandler extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e){
			if(e.getClickCount() == 1){
				mouse1Clicked(e);
			}else if(e.getClickCount() == 1){
				mouse2Clicked(e);
			}
		}
		
		private void mouse1Clicked(MouseEvent e){
		}
		
		private void mouse2Clicked(MouseEvent e){
		}
		
		@Override
		public void mousePressed(MouseEvent e){
			if(ds == DrawState.IDLE){
				ds = initDrawing(e);
			}
		}

		@Override
		public void mouseReleased(MouseEvent e){
			if(ds == DrawState.DRAWING){
				saveShape();
				ds = DrawState.IDLE;
			}
		}
		
		@Override
		public void mouseDragged(MouseEvent e){
			if(ds == DrawState.DRAWING){
				keepDrawing(e);
			}
		}
		
		@Override
		public void mouseMoved(MouseEvent e){
		}
	}
}
