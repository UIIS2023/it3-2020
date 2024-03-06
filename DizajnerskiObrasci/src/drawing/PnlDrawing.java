package drawing;

// VIEEEWW

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class PnlDrawing extends JPanel {

	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private boolean selDelete;
	// private boolean selModify;
	private Color pointColor;
	private Color lineColor;
	protected Color colorCircle; // color 1-border color
	protected Color borderCircle; // color 2-inner color
	protected Color colorRect;
	protected Color borderRect;
	protected Color colorDonut;
	protected Color borderDonut;

	public void shapesAdd(Shape shape) {
		this.shapes.add(shape);
		repaint();
	}
/*
	//**************************************SELECT******************************************
	
	public void selShape(MouseEvent e) {
		if (shapes.size() == 0) {
			JOptionPane.showMessageDialog(null, "There is no shapes :( ");
		} else {
			Shape selected = null; // pocetna vrednost na null
			Iterator<Shape> it = shapes.iterator();
			while (it.hasNext()) // hasnext vraca da li postoji ili ne naredni el u listi
			{
				Shape shape = it.next(); // next vraca referencu na sledeci el u listi
				shape.setSelected(false);
				if (shape.contains(e.getX(), e.getY())) {
					selected = shape;
					setSelDelete(false);
				}
			}
			if (selected != null) {
				selected.setSelected(true); // oblik je selectovan
			}
			repaint();
		}
	}
	
	//***************************************MODIFY********************************************

	public void modify() {
		if (shapes.size() == 0) {
			JOptionPane.showMessageDialog(null, "There is no shapes :( ");
		}
		else {
			for (int i = 0; i < shapes.size(); i++) {
				if (!shapes.get(i).isSelected())
				{
			       JOptionPane.showMessageDialog(null, "Select the shape first");
				}
		}
		for (int i = 0; i < shapes.size(); i++) {
			if (shapes.get(i).isSelected()) {
				if (shapes.get(i) instanceof Point) {
					if (shapes.get(i).isSelected()) {
						DlgPoint dlgPoint = new DlgPoint();
						Point point = (Point) shapes.get(i);
						dlgPoint.getTxtCordinateX().setText(Integer.toString(point.getX()));
						dlgPoint.getTxtCordinateY().setText(Integer.toString(point.getY()));
						dlgPoint.setVisible(true);
						if (dlgPoint.isGood()) {
							int x = (Integer.parseInt(dlgPoint.getTxtCordinateX().getText()));
							int y = (Integer.parseInt(dlgPoint.getTxtCordinateY().getText()));
							if (dlgPoint.isColorIsOk()) {
								pointColor = dlgPoint.getFill();
							} else {
								pointColor = dlgPoint.fill;
							}
							Point p = new Point(x, y, pointColor);
							shapes.add(p);
							shapes.remove(i);
							repaint();
						}
					} else {
						shapes.get(i).setSelected(false);
						repaint();
						setSelDelete(true);
					}
				}

				else if (shapes.get(i) instanceof Line) {
					if (shapes.get(i).isSelected()) {
						DlgLine dlgLine = new DlgLine();
						Line line = (Line) shapes.get(i);
						dlgLine.getTxtSPX().setText(Integer.toString(line.getStartPoint().getX()));
						dlgLine.getTxtSPY().setText(Integer.toString(line.getStartPoint().getY()));
						dlgLine.getTxtEPX().setText(Integer.toString(line.getEndPoint().getX()));
						dlgLine.getTxtEPY().setText(Integer.toString(line.getEndPoint().getY()));
						dlgLine.setVisible(true);
						if (dlgLine.isGood()) {
							int sX = Integer.parseInt(dlgLine.getTxtSPX().getText());
							int sY = Integer.parseInt(dlgLine.getTxtSPY().getText());
							int eX = Integer.parseInt(dlgLine.getTxtEPX().getText());
							int eY = Integer.parseInt(dlgLine.getTxtEPY().getText());
							if (dlgLine.isColorIsOk()) {
								lineColor = dlgLine.getFill();
							} else {
								lineColor = dlgLine.fill;
							}
							Line l = new Line(new Point(sX, sY), new Point(eX, eY), lineColor);
							shapes.add(l);
							shapes.remove(i);
							repaint();

						}

					} else {
						shapes.get(i).setSelected(false);
						repaint();
						setSelDelete(true);
					}

				} else if (shapes.get(i) instanceof Rectangle) {
					if (shapes.get(i).isSelected()) {
						DlgRectangle dlgRectangle = new DlgRectangle();
						Rectangle rect = (Rectangle) shapes.get(i);
						dlgRectangle.getTxtULPX().setText(Integer.toString(rect.getUpperLeft().getX()));
						dlgRectangle.getTxtULPY().setText(Integer.toString(rect.getUpperLeft().getY()));
						dlgRectangle.getTxtWidth().setText(Integer.toString(rect.getWidth()));
						dlgRectangle.getTxtHeight().setText(Integer.toString(rect.getHeight()));
						dlgRectangle.setVisible(true);

						if (dlgRectangle.isOkay) {
							int uLX = Integer.parseInt(dlgRectangle.getTxtULPX().getText());
							int uLY = Integer.parseInt(dlgRectangle.getTxtULPY().getText());
							int h = Integer.parseInt(dlgRectangle.getTxtHeight().getText());
							int w = Integer.parseInt(dlgRectangle.getTxtWidth().getText());
							if (dlgRectangle.colorOkay) {
								colorRect = dlgRectangle.getColor();

							} else
								colorRect = dlgRectangle.color;

							if (dlgRectangle.borderOkay) {
								borderRect = dlgRectangle.getBorder();
							} else {
								borderRect = dlgRectangle.border;
							}
							Rectangle r = new Rectangle(new Point(uLX, uLY), h, w, borderRect, colorRect);
							shapes.add(r);
							shapes.remove(shapes.get(i));
							repaint();
						}

					} else {
						shapes.get(i).setSelected(false);
						repaint();
						setSelDelete(true);
					}

				}

				else if (shapes.get(i) instanceof Circle && shapes.get(i) instanceof Donut == false) {
					if (shapes.get(i).isSelected()) {
						DlgCircle dlgCircle = new DlgCircle();
						Circle circle = (Circle) shapes.get(i);
						dlgCircle.getTxtCordinateX().setText(Integer.toString(circle.getCenter().getX()));
						dlgCircle.getTxtCordinateY().setText(Integer.toString(circle.getCenter().getY()));
						dlgCircle.getTxtRadius().setText(Integer.toString(circle.getRadius()));
						dlgCircle.setVisible(true);
						if (dlgCircle.isOk) {
							int cX = Integer.parseInt(dlgCircle.getTxtCordinateX().getText());
							int cY = Integer.parseInt(dlgCircle.getTxtCordinateY().getText());
							int rad = Integer.parseInt(dlgCircle.getTxtRadius().getText());
							if (dlgCircle.colorOkay) {
								colorCircle = dlgCircle.getColorC();
							} else {
								colorCircle = dlgCircle.colorC;
							}
							if (dlgCircle.borderOkay) {

								borderCircle = dlgCircle.getBorderC();
							} else {
								borderCircle = dlgCircle.borderC;
							}
							Circle c = new Circle(new Point(cX, cY), rad, borderCircle, colorCircle);
							shapes.add(c);
							shapes.remove(shapes.get(i));
							repaint();
							
						}
					} else {
						shapes.get(i).setSelected(false);
						repaint();
						setSelDelete(true);

					}

				} else if (shapes.get(i) instanceof Donut) {
					if (shapes.get(i).isSelected()) {
						DlgDonut dlgDonut = new DlgDonut();
						Donut donut = (Donut) shapes.get(i);
						dlgDonut.getTxtCordinateX().setText(Integer.toString(donut.getCenter().getX()));
						dlgDonut.getTxtCordinateY().setText(Integer.toString(donut.getCenter().getY()));
						dlgDonut.getTxtRadius().setText(Integer.toString(donut.getRadius()));
						dlgDonut.getTxtInnerRadius().setText(Integer.toString(donut.getInnerRadius()));
						// dlgDonut.getBtnColor1().setBackground(donut.getColor());
						// dlgDonut.getBtnColor2().setBackground(donut.getColor());
						dlgDonut.setVisible(true);
						if (dlgDonut.isOk1) {
							int dX = Integer.parseInt(dlgDonut.getTxtCordinateX().getText());
							int dY = Integer.parseInt(dlgDonut.getTxtCordinateY().getText());
							int radius = Integer.parseInt(dlgDonut.getTxtRadius().getText());
							int inner = Integer.parseInt(dlgDonut.getTxtInnerRadius().getText());
							if (dlgDonut.isColorOkay()) {
								colorDonut = dlgDonut.getColor();
							} else {
								colorDonut = dlgDonut.color;
							}
							if (dlgDonut.isBorderOkay()) {
								borderDonut = dlgDonut.getBorder();
							} else {
								borderDonut = dlgDonut.border;
							}
							Donut d = new Donut(new Point(dX, dY), radius, inner, borderDonut, colorDonut);
							shapes.add(d);
							shapes.remove(shapes.get(i));
							repaint();
						}
					} else {
						shapes.get(i).setSelected(false);
						repaint();
						setSelDelete(true);
					}
				}
			}

		
		}
		}
		}

	//*****************************************DELETE********************************************
	public void delete() {

		if (shapes.size() == 0) {
			JOptionPane.showMessageDialog(null, "There is no shapes :( ");
			setSelDelete(true);
		} else
			setSelDelete(false);
		for (int i = 0; i < shapes.size(); i++) {
			if (shapes.get(i).isSelected()) {
				setSelDelete(true);
				DlgDelete dlgDelete = new DlgDelete();
				dlgDelete.setVisible(true);
				if (dlgDelete.isOkay()) {
					shapes.remove(shapes.get(i));
					repaint();
				} else {
					shapes.get(i).setSelected(false);
					repaint();
					setSelDelete(true);
				}
			}
		
			}
		if (isSelDelete() == false) {
			JOptionPane.showMessageDialog(null, "Select the shape first");
			setSelDelete(true);

		}

	}
	*/
	
	//***************************************************************************

	/*public void paint(Graphics g) {
		super.paint(g);
		Iterator<Shape> it = shapes.iterator();
		while (it.hasNext()) {
			it.next().draw(g);
		}
	}*/
	
	//***********************************GET & SET*****************************************

	public Color getPointColor() {
		return pointColor;
	}

	public void setPointColor(Color pointColor) {
		this.pointColor = pointColor;
	}

	public Color getLineColor() {
		return lineColor;
	}

	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}

	public Color getColorCircle() {
		return colorCircle;
	}

	public void setColorCircle(Color colorCircle) {
		this.colorCircle = colorCircle;
	}

	public Color getBorderCircle() {
		return borderCircle;
	}

	public void setBorderCircle(Color borderCircle) {
		this.borderCircle = borderCircle;
	}

	public Color getColorRect() {
		return colorRect;
	}

	public void setColorRect(Color colorRect) {
		this.colorRect = colorRect;
	}

	public Color getBorderRect() {
		return borderRect;
	}

	public void setBorderRect(Color borderRect) {
		this.borderRect = borderRect;
	}

	public Color getColorDonut() {
		return colorDonut;
	}

	public void setColorDonut(Color colorDonut) {
		this.colorDonut = colorDonut;
	}

	public Color getBorderDonut() {
		return borderDonut;
	}

	public void setBorderDonut(Color borderDonut) {
		this.borderDonut = borderDonut;
	}

	public boolean isSelDelete() {
		return selDelete;
	}

	public void setSelDelete(boolean selDelete) {
		this.selDelete = selDelete;
	}

}
