package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends ShapeS implements Cloneable {

	protected Point upperLeft;
	protected int width;
	protected int height;

	// private boolean selected;

	public Rectangle() {

	}

	public Rectangle(Point upperLeft, int width, int height) {
		this.upperLeft = upperLeft;
		this.width = width;
		this.height = height;
	}

	public Rectangle(Point upperLeft, int width, int height, Color color) {
		this(upperLeft, width, height);
		this.setColor(color);
	}

	public Rectangle(Point upperLeft, int width, int height, Color color, Color innerColor) {
		this(upperLeft, width, height, color);
		this.setInnerColor(innerColor);
	}

	public int area() {
		return this.width * this.height;
	}

	public int circumference() {
		return this.width * 2 + this.height * 2;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle p = (Rectangle) obj;
			if (this.upperLeft.equals(p.upperLeft) && this.width == p.width
					&& this.height == p.height)
				return true;
			else
				return false;
		} else
			return false;
	}

	public boolean contains(int x, int y) {
		if (upperLeft.getX() <= x && x <= upperLeft.getX() + width && upperLeft.getY() <= y
				&& y <= upperLeft.getY() + height)
			return true;
		return false;
	}

	public boolean contains(Point p) {
		if (upperLeft.getX() <= p.getX() && p.getX() <= upperLeft.getX() + width && upperLeft.getY() <= p.getY()
				&& p.getY() <= upperLeft.getY() + height)
			return true;
		return false;
	}

	@Override
	public void moveTo(int x, int y) {
		upperLeft.moveTo(x, y);
		upperLeft.moveBy(x, y);

	}

	@Override
	public void moveBy(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Rectangle) {
			return this.area() - ((Rectangle) o).area();
		}
		return 0;
	}

	@Override
	public void draw(Graphics g) {
	//	System.out.println("test"+getColor());
		g.setColor(getColor());
		g.drawRect(upperLeft.getX(), upperLeft.getY(), width, height);
		this.fill(g);
		if (selected == true) {
			g.setColor(Color.BLUE);
			g.drawRect(upperLeft.getX() - 2, upperLeft.getY() - 2, 4, 4);
			g.drawRect(upperLeft.getX() + width - 2, upperLeft.getY() - 2, 4, 4);
			g.drawRect(upperLeft.getX() - 2, upperLeft.getY() + height - 2, 4, 4);
			g.drawRect(upperLeft.getX() + width - 2, upperLeft.getY() + height - 2, 4, 4);
		}
	}

	public Point getUpperLeft() {
		return upperLeft;
	}

	public void setUpperLeft(Point upperLeft) {
		this.upperLeft = upperLeft;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	/*
	 * public boolean isSelected() { return selected; }
	 * 
	 * public void setSelected(boolean selected) { this.selected = selected; }
	 */

	public String toString() {
		return " Upper left point: ("+ upperLeft.getX() + "," + upperLeft.getY() + "), width= " + width + ", height= "+height + ", borderColor= " + getColor().getRGB() + ", innerColor= " + getInnerColor().getRGB();
	}

	@Override
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		g.fillRect(upperLeft.getX(), upperLeft.getY(), width, height);

	}
	
	public Rectangle clone() {

		Rectangle rect = new Rectangle();
		rect.setUpperLeft(new Point());
		
		rect.getUpperLeft().setX(this.getUpperLeft().getX());
		rect.getUpperLeft().setY(this.getUpperLeft().getY());
		rect.setHeight(this.getHeight());
		rect.setWidth(this.getWidth());
		rect.setColor(this.getColor());
		rect.setInnerColor(this.getInnerColor());

		
		
		return rect;
	}
}