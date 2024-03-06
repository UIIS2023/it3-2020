package command;

import geometry.Rectangle;

public class UpdateRectangleCmd implements Command{

	private Rectangle oldRectangle;
	private Rectangle newRectangle;
	private Rectangle original = new Rectangle();
	
	
	public UpdateRectangleCmd(Rectangle oldRectangle, Rectangle newRectangle) {
		super();
		this.oldRectangle = oldRectangle;
		this.newRectangle = newRectangle;
	}

	@Override
	public void execute() {
		
		original=oldRectangle.clone();
		// TODO Auto-generated method stub
		oldRectangle.getUpperLeft().setX(newRectangle.getUpperLeft().getX());
		oldRectangle.getUpperLeft().setY(newRectangle.getUpperLeft().getY());
		oldRectangle.setHeight(newRectangle.getHeight());
		oldRectangle.setWidth(newRectangle.getWidth());
		oldRectangle.setColor(newRectangle.getColor());
		oldRectangle.setInnerColor(newRectangle.getInnerColor());
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		oldRectangle.getUpperLeft().setX(original.getUpperLeft().getX());
		oldRectangle.getUpperLeft().setY(original.getUpperLeft().getY());
		oldRectangle.setHeight(original.getHeight());
		oldRectangle.setWidth(original.getWidth());
		oldRectangle.setColor(original.getColor());
		oldRectangle.setInnerColor(original.getInnerColor());


	}

}
