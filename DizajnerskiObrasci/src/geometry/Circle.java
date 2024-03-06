package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends ShapeS implements Cloneable {

	protected Point center;
	private int radius;
//	protected boolean selected;

	public Circle() {

	}

	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}

	/*
	 * public Circle(Point center, int radius, boolean selected) { this(center,
	 * radius); setSelected(selected); // this.selected = selected; }
	 */

	public Circle(Point center, int radius, Color color) {
		this(center, radius);
		this.setColor(color);
	}

	public Circle(Point center, int radius, Color color, Color innerColor) {
		this(center, radius, color);
		this.setInnerColor(innerColor);
	}

	public double area() { // double zbog Pi
		return radius * radius * Math.PI;
	}

	public double circumference() {
		return 2 * radius * Math.PI;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Circle) {
			Circle pomocna = (Circle) obj;
			if (this.center.equals(pomocna.center) && this.radius == pomocna.radius) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean contains(int x, int y) {
		return center.distance(x, y) <= radius;
	}

	public boolean contains(Point p) {
		return center.distance(p.getX(), p.getY()) <= radius;
	}

	@Override
	public void moveTo(int x, int y) {
		center.moveTo(x, y);

	}

	@Override
	public void moveBy(int x, int y) {
		center.moveBy(x, y);

	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Circle) {
			return (int) (this.area() - ((Circle) o).area());
		}
		return 0;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawOval(center.getX() - radius, center.getY() - radius, 2 * radius, 2 * radius);
		this.fill(g);
		if (selected == true) {
			g.setColor(Color.BLUE);
			g.drawRect(center.getX() - 2, center.getY() - 2, 4, 4);
			g.drawRect(center.getX() - radius, center.getY() - 2, 4, 4);
			g.drawRect(center.getX() + radius, center.getY() - 2, 4, 4);
			g.drawRect(center.getX() - 2, center.getY() - radius, 4, 4);
			g.drawRect(center.getX() - 2, center.getY() + radius, 4, 4);

		}
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) throws Exception // moguce da vraca izuzetak
	{
		if (radius < 0)
			throw new Exception("Radius ne sme biti negativan");
		this.radius = radius;
	}

	/*
	 * public boolean isSelected() { return selected; }
	 * 
	 * public void setSelected(boolean selected) { this.selected = selected; }
	 */

	public String toString() {
		return " Center= (" + center.getX() + "," + center.getY() + "), radius= " + radius + ", borderColor= " + getColor().getRGB() + ", innerColor= " + getInnerColor().getRGB();
	}

	@Override
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		g.fillOval(center.getX() - radius, center.getY() - radius, 2 * radius, 2 * radius);
		// TODO Auto-generated method stub

	}
	
	public Circle clone() {
		
		Circle circle = new Circle();
		circle.setCenter(new Point());
		circle.getCenter().setX(this.getCenter().getX());
		circle.getCenter().setY(this.getCenter().getY());

		try {
			circle.setRadius(this.getRadius());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		circle.setColor(getColor());
		circle.setInnerColor(getInnerColor());
		
		
		return circle;
	}

}
