package mvc;

import java.util.ArrayList;
import java.util.List;
import geometry.Shape;

public class DrawingModel {

private List<Shape> shapes = new ArrayList<>();

	public void remove(Shape s) {
		shapes.remove(s);
	}
	
	public List<Shape> getShapes(){
		return shapes;
	}
	
	public void shapesAdd(Shape shape) {
		this.shapes.add(shape);
		//repaint();
	}
	
}
