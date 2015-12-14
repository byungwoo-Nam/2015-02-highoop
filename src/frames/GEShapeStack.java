package frames;

import java.util.Vector;

import shapes.GEShape;

public class GEShapeStack {
	private final int MAXSIZE = 10;
	private Vector<Vector<GEShape>> stack;
	
	public GEShapeStack(){
		this.stack = new Vector<Vector<GEShape>>();
	}
	
	public void init(){
		this.stack.clear();
	}
	
	@SuppressWarnings("unchecked")
	public void push(Vector<GEShape> shapes){
		if(this.stack.size() > MAXSIZE){
			this.stack.remove(0);
		}
		this.stack.add((Vector<GEShape>)shapes.clone());
	}
	
	public Vector<GEShape> pop(){
		return this.stack.size() > 0 ? this.stack.remove(this.stack.size()-1) : null;
	}
	
	public int getSize(){
		return this.stack.size();
	}
}
