package command;

import geometry.Shape;
import mvc.DrawingModel;

public class AddShapeCmd implements Command{

	private DrawingModel model;
	private Shape shape;
	
	
	public AddShapeCmd(DrawingModel model, Shape shape) {
		super();
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		model.shapesAdd(shape);
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		model.remove(shape);
	}

}
