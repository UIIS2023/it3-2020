package command;

import java.util.ArrayList;

import geometry.Shape;

public class SelectCmd implements Command {

	private ArrayList<Shape> selectedShapes;
	private Shape shape;

	

	public SelectCmd(Shape shape) {
		super();
		this.shape = shape;
	}
	public SelectCmd(ArrayList<Shape> selectedshapes, Shape shape) {
		
		this.selectedShapes=selectedshapes;
		this.shape=shape;
		
	}
	@Override
	public void execute() {
		selectedShapes.add(shape);
		shape.setSelected(true);
		
	}

	@Override
	public void unexecute() {
		
		selectedShapes.remove(shape);
		shape.setSelected(false);
		
	}

}
