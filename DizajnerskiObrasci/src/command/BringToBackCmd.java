package command;

import geometry.Shape;
import mvc.DrawingModel;

public class BringToBackCmd implements Command{

	private DrawingModel model;
	private Shape shape;
	
	public BringToBackCmd(DrawingModel model, Shape shape) {
		super();
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		model.remove(shape);
		model.getShapes().add(0,shape);
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		model.getShapes().remove(shape);
		model.shapesAdd(shape);
		
	}

}
