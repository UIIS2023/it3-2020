package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class Shape implements Moveable, Comparable, Serializable {

	protected boolean selected; // nece biti vidljiva u klasama koje modifikuju klasu shape
	// moze biti definisana i kao protected da se ne bi menjao ostata koda
	private Color color;
	private Color innerColor;

	public abstract boolean contains(Point p);

	public abstract void fill(Graphics g);

	public Shape() {

	}

	public Shape(Color color) {
		this.color = color;
	}

	public Shape(boolean selected, Color color) {
		this.selected = selected;
		this.color = color;
	}

	public Shape(boolean selected, Color color, Color innerColor) {
		this(selected, color);
		this.innerColor = color;
	}

	public abstract boolean contains(int x, int y);

	public abstract void draw(Graphics g);

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getInnerColor() {
		return innerColor;
	}

	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}

}
