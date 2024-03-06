package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Drawing extends JPanel {

	public static void main(String[] args) {

		JFrame frame = new JFrame("Drawings");
		frame.setSize(800, 600);
		frame.setVisible(true);
		Drawing drawing = new Drawing();// prosiruje JPanel
		frame.getContentPane().add(drawing);// poziv metode paint(g)
	}

	// redefinisana metoda iz klase JComponent
	@Override
	public void paint(Graphics g) {
		Point p = new Point(50, 50);
		p.draw(g);
		g.setColor(Color.red);

		Line l1 = new Line(new Point(100, 100), new Point(200, 200));
		l1.draw(g);

		Rectangle r1 = new Rectangle(l1.getEndPoint(), 100, 50);
		r1.draw(g);

		Circle c1 = new Circle(new Point(500, 100), 80);
		c1.draw(g);

		Donut d1 = new Donut(new Point(800, 100), 50, 25);
		d1.draw(g);

		Rectangle k1 = new Rectangle(new Point(500, 300), 50, 50);
		k1.draw(g);

		// zadatak 5.
		int innerR = (int) (k1.getHeight() * Math.sqrt(2) / 2);
		Donut d2 = new Donut(
				new Point(k1.getUpperLeft().getX() + k1.getHeight() / 2, k1.getUpperLeft().getY() + k1.getHeight() / 2),
				80, innerR);
		d2.draw(g);

		// vezbe 8
		ArrayList<Shape> shapes = new ArrayList<>();
		shapes.add(p);
		shapes.add(l1);
		shapes.add(c1);
		shapes.add(d1);
		shapes.add(k1);
		Iterator<Shape> it = shapes.iterator();
		while (it.hasNext()) {
			it.next().moveBy(10, 0);
		}

		// Zadatak 2.
		g.setColor(Color.black);
		shapes.get(3).draw(g);
		shapes.get(shapes.size() - 1).draw(g);
		shapes.remove(1);
		shapes.get(1).draw(g);
		// preklopice se sa prethodnim kvadratom
		shapes.get(3).draw(g);
		shapes.add(3, l1);

		it = shapes.iterator(); // moramo ga ponovo inicijalizovati da bismo opet mogli da prolazimo kroz listu
		// jer smo u prethodnom sve istrosili
		while (it.hasNext()) {
			Shape s = it.next();
			if (s instanceof Circle || s instanceof Rectangle)
				s.draw(g);
		}

		// Zadatak 3.
		try {
			c1.setRadius(-10);
		} catch (Exception e) {
			e.printStackTrace(); // obrada greske (da se ispise na konzolnoj liniji)
		}
		// nije se prekinulo izvrsavanje i nije se setovao radius
		System.out.println(c1);

		// Zadatak 4.
		it = shapes.iterator();
		while (it.hasNext()) {

		}

		// Zadatak 5.
		HashMap<String, Shape> hmShapes = new HashMap<>();
		hmShapes.put("point", p);
		hmShapes.put("line", l1);
		System.out.println(hmShapes.get("line"));

	}

}
