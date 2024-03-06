package command;

import geometry.Shape;
import mvc.DrawingModel;

public class RemoveShapeCmd implements Command {

	private DrawingModel model;
	private Shape shape;
	
	
	
	public RemoveShapeCmd(DrawingModel model) {
		super();
		this.model = model;
	}

	public RemoveShapeCmd(DrawingModel model, Shape shape) {
		super();
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		model.remove(shape);
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		model.shapesAdd(shape);	
	}

}
