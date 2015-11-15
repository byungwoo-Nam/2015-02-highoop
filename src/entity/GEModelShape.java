package entity;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import shapes.GEShape;

public class GEModelShape {
	static private Vector<GEShape> vectorGEShape = new Vector<GEShape>();
	
	static public Vector<GEShape> getVectorGEShape() {
		return GEModelShape.vectorGEShape;
	}
	static public void setVectorGEShape(Vector<GEShape> vectorGEShape) {
		GEModelShape.vectorGEShape = vectorGEShape;
	}
	@SuppressWarnings("unchecked")
	static public void openGEShape(File file){
		try {
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis);
			ObjectInputStream ois = new ObjectInputStream(bis);
			setVectorGEShape((Vector<GEShape>) ois.readObject());
			ois.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static public void saveGEShape(File file){
		try {
			FileOutputStream fos = new FileOutputStream(file);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(getVectorGEShape());
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static public void newGEShape(){
		setVectorGEShape(new Vector<GEShape>());
	}
}