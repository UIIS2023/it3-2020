package command;

import geometry.Shape;
import mvc.DrawingModel;

public class ToBackCmd implements Command{

	private DrawingModel model;
	private Shape shape;
	private int i;
	
	public ToBackCmd(DrawingModel model, Shape shape) {
		super();
		this.model = model;
		this.shape = shape;
		this.i = model.getShapes().indexOf(shape);
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		model.remove(shape);
		if(i==0) {
			model.getShapes().add(0,shape);
		}
		else {
			model.getShapes().add(i-1,shape);
		}
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		model.remove(shape);
		if (i==model.getShapes().size()) {
			model.getShapes().add(shape);
		}
		else
		{
			model.getShapes().add(i+1,shape);
		}
	}

}
