package geometry;

import java.util.Arrays;

import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;

public class Test {

	public static void main(String[] args) {
		// vezbe 2
		Point p = new Point();
		// promenljiva sadrzi referencu na objekat
		System.out.println(p);
		// obelezje x je private i nije vidljivo iz klase Test
		// System.out.println(p.x);

		System.out.println(p.getX() + " " + p.getY() + " " + p.isSelected());
		p.setX(10);
		p.setY(10);
		p.setSelected(true);
		System.out.println(p.getX() + " " + p.getY() + " " + p.isSelected());

		double result = p.distance(40, 50);
		System.out.println("Distance between points is: " + result);

		// vezbe 3
		Point p1 = new Point();
		p1.setX(15);
		p1.setY(27);
		p1.setSelected(true);

		Line l1 = new Line();
		Rectangle r1 = new Rectangle();
		Circle c1 = new Circle();
		// Inicijalizovati x kordinatu tacke p na vrednost y kordinate tacke p1
		System.out.println("Stara vrednost x kordinate tacke p: " + p.getX());
		p.setX(p1.getY());
		System.out.println("Nova vrednost x kordinate tacke p: " + p.getX());

		// Postaviti za pocetnu tacku linije l1 tacku p,a za krajnju p1
		l1.setStartPoint(p);
		l1.setEndPoint(p1);

		// Postaviti y kordinatu krajnje racke l1 na 23
		System.out.println("Stara vrednost y kordinate tacke l1: " + l1.getEndPoint().getY());
		l1.getEndPoint().setY(23);
		System.out.println("Nova vrednost y kordinate tacke l1: " + l1.getEndPoint().getY());

		// Inicijalizovati x koordinatu pocetne tacke l1 na vrednost l1 na vrednost y
		// kordinate krajnje tacke linije l1
		System.out.println("Stara vrednost x kordinate pocetne tacke l1: " + l1.getStartPoint().getX());
		l1.getStartPoint().setX(l1.getEndPoint().getY());
		System.out.println("Nova vrednost x kordinate pocetne tacke l1: " + l1.getStartPoint().getX());

		// Postaviti x kordinatu krajnje tacke l1 na vrednost duzine linije l1
		// umanjenje za za vrednost zbira x i y koorddinate oocetne tacke linije l1
		l1.getEndPoint().setX((int) (l1.length() - (l1.getStartPoint().getX() + l1.getStartPoint().getY())));

		// Postaviti x koordinatu tacke gore levo pravougaonika
		// r1 na vrednost 10 i y koordinatu na vrednost 15
		r1.setUpperLeft(p1);
		r1.getUpperLeft().setX(10);
		r1.getUpperLeft().setY(15);
		System.out.println("x koordinate tacke gore levo:" + r1.getUpperLeft().getX());
		System.out.println("y koordinate tacke gore levo:" + r1.getUpperLeft().getY());

		// Postaviti centar kruga c1 na koordinate tacke gore levo od r1
		c1.setCenter(p1);
		c1.setCenter(r1.getUpperLeft());
		System.out.println("X koordinata centra" + c1.getCenter().getX());
		System.out.println("Y koordinata centra" + c1.getCenter().getY());

		// Postaviti x koordinatu centra kruga c1 na vrednost razlicite
		// povrsine pravougaonika r1 i y koordinate pocetne linije l1
		r1.setHeight(20);
		r1.setWidth(30);
		c1.getCenter().setX((r1.area() - l1.getStartPoint().getY()));
		System.out.println("X koordinata centra" + c1.getCenter().getX());

		// Vezbe 4.

		Point p2 = new Point(50, 100);
		// postavice false za selected
		System.out.println(p2.isSelected());
		Line l2 = new Line(p2, new Point(400, 500));
		Rectangle r2 = new Rectangle(p2, 50, 80);
		System.out.println(p2);
		System.out.println(l2);
		System.out.println(r2);

		// Vezbe 7
		System.out.println("Vezbe 7-testiranje metoda i nizovi");
		System.out.println("\nTacka");
		System.out.println(p1);
		p1.moveBy(5, 3);
		System.out.println(p2);
		p2.moveTo(5, 3);
		System.out.println(p1);

		System.out.println("\nLinija");
		System.out.println(l1);
		l1.moveBy(5, 3);
		System.out.println(l1);
		l2.moveTo(5, 3); // nista se ne menja jer smo 'lazno' implementirali metodu
		System.out.println(l1);

		System.out.println("\nPravougaonik");
		System.out.println(r1);
		r1.moveBy(5, 3);
		System.out.println(r2);
		r2.moveTo(5, 3);
		System.out.println(r1);

		System.out.println("\nKrug");
		System.out.println(c1);
		c1.moveBy(5, 3);
		System.out.println(c1);
		c1.moveTo(5, 3);
		System.out.println(c1);

		Donut d1 = new Donut(new Point(800, 100), 50, 25);
		System.out.println("\nKrug sa rupom");
		System.out.println(d1);
		d1.moveBy(5, 3);
		System.out.println(d1);
		d1.moveTo(5, 3);
		System.out.println(d1);

		Shape d3 = new Donut(p1, 10, 5); // moze ovako, ne moze sa druge strane Shape
		Shape p9 = new Point();
		Shape l9 = l1;
		Shape c9 = c1;
		System.out.println("\nIspis oblika");
		Shape[] shapes = { d3, p9, l9, c9 };
		for (int i = 0; i < shapes.length; i++) {
			System.out.println(shapes[i]);
		}
		for (int i = 0; i < shapes.length; i++) {
			shapes[i].moveBy(1, 2);
		}
		System.out.println("Ispis oblika nakon moveby");
		for (int i = 0; i < shapes.length; i++) {
			System.out.println(shapes[i]);
		}

		int[] ints = { 5, 2, 3, 4, 1 };
		System.out.println("Ispis nesortiranog niza");
		for (int i = 0; i < ints.length; i++) {
			System.out.println(ints[i]);
		}
		Arrays.sort(ints);
		System.out.println("Ispis sortiranog niza");
		for (int i = 0; i < ints.length; i++) {
			System.out.println(ints[i]);
		}

		System.out.println("\n");
		System.out.println(ints[1]);

		Point p10 = new Point(10, 10);
		Point p20 = new Point(20, 20);
		Point p30 = new Point(30, 30);
		Point p40 = new Point(40, 40);
		Point p50 = new Point(50, 50);
		Point[] points = { p10, p20, p30, p50, p40 };
		System.out.println("Ispis nesortiranog niza");
		for (int i = 0; i < points.length; i++) {
			System.out.println(points[i]);
		}
		Arrays.sort(points);
		System.out.println("Ispis sortiranog niza");
		for (int i = 0; i < points.length; i++) {
			System.out.println(points[i]);
		}

		Circle c20 = new Circle(p1, 20);
		Circle c30 = new Circle(p1, 30);
		Circle c40 = new Circle(p1, 40);
		Circle c50 = new Circle(p1, 50);
		Circle[] circles = { c20, c30, c50, c40 };
		System.out.println("Ispis nesortiranog niza");
		for (int i = 0; i < circles.length; i++) {
			System.out.println(circles[i]);
		}
		Arrays.sort(circles);
		System.out.println("Ispis sortiranog niza");
		for (int i = 0; i < circles.length; i++) {
			System.out.println(circles[i]);
		}

	}

}