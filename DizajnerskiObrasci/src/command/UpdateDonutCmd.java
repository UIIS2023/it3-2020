package command;

import geometry.Donut;

public class UpdateDonutCmd implements Command{

	private Donut oldDonut;
	private Donut newDonut;
	private Donut original = new Donut();
	
	public UpdateDonutCmd(Donut oldDonut, Donut newDonut) {
		super();
		this.oldDonut = oldDonut;
		this.newDonut = newDonut;
	}

	@Override
	public void execute() {
		original=oldDonut.clone();
		// TODO Auto-generated method stub
		oldDonut.getCenter().setX(newDonut.getCenter().getX());
		oldDonut.getCenter().setY(newDonut.getCenter().getY());
		    try {
		    	oldDonut.setRadius(newDonut.getRadius());
		    } catch (Exception e)
		    {
		    	e.printStackTrace();
		    }
		    oldDonut.setInnerColor(newDonut.getInnerColor());
		    oldDonut.setColor(newDonut.getColor());
		    oldDonut.setInnerColor(newDonut.getInnerColor());
		    
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		oldDonut.getCenter().setX(original.getCenter().getX());
		oldDonut.getCenter().setY(original.getCenter().getY());
		try {
			oldDonut.setRadius(original.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		oldDonut.setInnerRadius(original.getInnerRadius());
		oldDonut.setInnerColor(original.getInnerColor());
		oldDonut.setColor(original.getColor());
	}

}
