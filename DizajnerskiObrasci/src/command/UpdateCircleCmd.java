package command;

import geometry.Circle;

public class UpdateCircleCmd implements Command{

	private Circle oldCircle;
	private Circle newCircle;
	private Circle original = new Circle();
	
	public UpdateCircleCmd(Circle oldCircle, Circle newCircle) {
		super();
		this.oldCircle = oldCircle;
		this.newCircle = newCircle;
	}

	@Override
	public void execute() {
	   original=oldCircle.clone();
	    
	   oldCircle.getCenter().setX(newCircle.getCenter().getX());
	   oldCircle.getCenter().setY(newCircle.getCenter().getY());
	    try {
	    	oldCircle.setRadius(newCircle.getRadius());
	    } catch (Exception e)
	    {
	    	e.printStackTrace();
	    }
	    
	    oldCircle.setColor(newCircle.getColor());
	    oldCircle.setInnerColor(newCircle.getInnerColor());

	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		oldCircle.getCenter().setX(original.getCenter().getX());
		oldCircle.getCenter().setY(original.getCenter().getY());
		try {
			oldCircle.setRadius(original.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		oldCircle.setInnerColor(original.getInnerColor());
		oldCircle.setColor(original.getColor());
	}

}
