package adapter;

import java.awt.Color;
import java.awt.Graphics;

import geometry.Point;
import geometry.ShapeS;
import hexagon.Hexagon;


public class HexagonAdapter extends ShapeS implements Cloneable {

	private Hexagon hexagon;

	public HexagonAdapter(Hexagon hex) {
		this.hexagon=hex;
	}

	public HexagonAdapter() {
		// TODO Auto-generated constructor stub
	}


//implementira metode iz Shape
	
	@Override
	public void moveTo(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveBy(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void fill(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(Point p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(int x, int y) {
		// TODO Auto-generated method stub
		return hexagon.doesContain(x, y);
	}

	@Override
	public void draw(Graphics g) {
        hexagon.paint(g);	
        if (selected == true) {
			g.setColor(Color.BLUE);
			g.drawRect(getX() - 2, getY() - 2, 4, 4);
			g.drawRect(getX() - getR(), getY() - 2, 4, 4);
			g.drawRect(getX() + getR(), getY() - 2, 4, 4);
			g.drawRect(getX() - 2, getY() - getR() + 6, 4, 4);
			g.drawRect(getX() - 2, getY() + getR() - 10, 4, 4);

		}
	}
	
	public void setInnerColor(Color innerColor) {
		hexagon.setAreaColor(innerColor);
	}
	
	public void setColor(Color borderColor) {
		hexagon.setBorderColor(borderColor);
	}
	
	public Color getInnerColor() {
		return hexagon.getAreaColor(); //inner
	}
	
	public Color getColor() {
		return hexagon.getBorderColor();
	}
	
	public int getX() {
		return hexagon.getX();
	}
	
	public void setX(int x) {
		hexagon.setX(x);
	}
	
	public int getY() {
		return hexagon.getY();
	}
	
	public void setY(int y) {
		hexagon.setY(y);
	}
	
	public int getR() {
		return hexagon.getR();
	}
	
	public void setR(int r) {
		hexagon.setR(r);
	}
	
	public Hexagon getHexagon() {
		return hexagon;
	}

	public void setHexagon(Hexagon hexagon) {
		this.hexagon = hexagon;
	}

	public String toString() {
		return " Center: " + "(" + getX() + "," + getY() + ")" + ", Radius= " + getR() + ", Border color= " + getColor().getRGB() + ", Inner color= " + getInnerColor().getRGB(); 
	}
	
	public HexagonAdapter clone() {
		
		HexagonAdapter hexagonAdapter=new HexagonAdapter();
		
		hexagonAdapter.setHexagon(new Hexagon(0,0,0));
		
		hexagonAdapter.setX(this.getX());
		hexagonAdapter.setY(this.getY());		
		hexagonAdapter.setR(this.getR());
		hexagonAdapter.setInnerColor(this.getInnerColor());
		hexagonAdapter.setColor(this.getColor());

		return hexagonAdapter;
	}



}
