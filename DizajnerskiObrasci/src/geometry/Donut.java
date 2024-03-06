package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Donut extends Circle implements Cloneable {
	private int innerRadius;

	public Donut() {
	}

	public Donut(Point center, int radius, int innerRadius) {
		// this.center-nije moguce jer je private u Circle klasi
		/*
		 * this.setCenter(center); this.setRadius(radius);
		 */
		super(center, radius); // mora biti prva naredba u okviru konstruktora
		this.innerRadius = innerRadius;
	}

	/*
	 * public Donut(Point center, int radius, int innerRadius, boolean selected) {
	 * this(center, radius, innerRadius); // this.setSelected(selected); // jer je
	 * selected u Circle definisana kao // private setSelected(selected); //
	 * this.selected = selected; // moze ovako ako je selected u Circle definisan
	 * protected }
	 */

	public Donut(Point center, int radius, int innerRadius, Color color) {
		this(center, radius, innerRadius);
		this.setColor(color);
	}

	public Donut(Point center, int radius, int innerRadius, Color color, Color innerColor) {
		this(center, radius, innerRadius, color);
		this.setInnerColor(innerColor);
	}

	public double area() {
		return super.area() - innerRadius * innerRadius * Math.PI;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut pomocni = (Donut) obj;
			if (getCenter().equals(pomocni.getCenter()) && getRadius() == pomocni.getRadius()
					&& innerRadius == pomocni.innerRadius) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean contains(int x, int y) {
		double dFromCenter = getCenter().distance(x, y);
		return super.contains(x, y) && dFromCenter > innerRadius;
	}

	public boolean contains(Point p) {
		double dFromCenter = getCenter().distance(p.getX(), p.getY());
		return super.contains(p.getX(), p.getY()) && dFromCenter > innerRadius;
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Donut) {
			return (int) (this.area() - ((Donut) o).area());
		}
		return 0;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		super.draw(g); // crtanje spoljasnjeg kruga
		g.drawOval(center.getX() - innerRadius, center.getY() - innerRadius, 2 * innerRadius, 2 * innerRadius);
		if (selected == true) {
			g.setColor(Color.BLUE);
			g.drawRect(center.getX() - 2, center.getY() - 2, 4, 4);
			g.drawRect(center.getX() - innerRadius, center.getY() - 2, 4, 4);
			g.drawRect(center.getX() + innerRadius, center.getY() - 2, 4, 4);
			g.drawRect(center.getX() - 2, center.getY() - innerRadius, 4, 4);
			g.drawRect(center.getX() - 2, center.getY() + innerRadius, 4, 4);
		}
	}

	@Override
	public void fill(Graphics g) {
		
	//TRANSPARENTNOST//
		
		Ellipse2D eIner=new Ellipse2D.Float(center.getX()-innerRadius,center.getY()- innerRadius,2* innerRadius,2* innerRadius);
		Ellipse2D eOuter=new Ellipse2D.Float(center.getX()-getRadius(),center.getY()- getRadius(),2* getRadius(),2* getRadius());
		Area outer=new Area(eOuter);
		Area iner=new Area(eIner);
		outer.subtract(iner);
		
		g.setColor(getInnerColor());
		((Graphics2D) g).fill(outer);
		/*g.setColor(getInnerColor());
		super.fill(g);
		g.setColor(Color.WHITE);
		g.fillOval(center.getX() - innerRadius, center.getY() - innerRadius, 2 * innerRadius, 2 * innerRadius);
	*/
	}

	public String toString() {
		return " Center= (" + center.getX() + "," + center.getY() + "), Radius= " + getRadius() + ", InnerRadius= " + innerRadius +", Color= " + getColor().getRGB() + ", InnerColor= " + getInnerColor().getRGB();
	}

	public int getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}
	
	public Donut clone() {
	Donut donut = new Donut();
	donut.setCenter(new Point());
	donut.getCenter().setX(this.getCenter().getX());
	donut.getCenter().setY(this.getCenter().getY());

	try {
		donut.setRadius(this.getRadius());
	}
	catch (Exception e)
	{
		e.printStackTrace();
	}
	donut.setInnerRadius(this.getInnerRadius());
	donut.setColor(this.getColor());
	donut.setInnerColor(this.getInnerColor());
	
	
	return donut;
	}
}
