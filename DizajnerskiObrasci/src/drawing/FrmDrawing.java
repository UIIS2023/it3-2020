/*package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.html.HTMLDocument.Iterator;

import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;

import java.awt.GridBagLayout;
import javax.swing.JToggleButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmDrawing extends JFrame {

	private JPanel contentPane;
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	Point startPoint;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmDrawing frame = new FrmDrawing();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FrmDrawing() {
		setTitle("Tunic Jovana IT3-2020");
		PnlDrawing pnlCenter = new PnlDrawing();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel pnlNorth = new JPanel();
		contentPane.add(pnlNorth, BorderLayout.NORTH);
		pnlNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JToggleButton tglbtnPoint = new JToggleButton("Point");

		tglbtnPoint.setBackground(new Color(240, 230, 140));

		buttonGroup.add(tglbtnPoint);
		pnlNorth.add(tglbtnPoint);

		JToggleButton tglbtnLine = new JToggleButton("Line");

		tglbtnLine.setBackground(new Color(240, 230, 140));
		buttonGroup.add(tglbtnLine);
		pnlNorth.add(tglbtnLine);

		JToggleButton tglbtnRectangle = new JToggleButton("Rectangle");

		tglbtnRectangle.setBackground(new Color(240, 230, 140));
		buttonGroup.add(tglbtnRectangle);
		pnlNorth.add(tglbtnRectangle);

		JToggleButton tglbtnCircle = new JToggleButton("Circle");

		tglbtnCircle.setBackground(new Color(240, 230, 140));
		buttonGroup.add(tglbtnCircle);
		pnlNorth.add(tglbtnCircle);

		JToggleButton tglbtnDonut = new JToggleButton("Donut");

		tglbtnDonut.setBackground(new Color(240, 230, 140));
		buttonGroup.add(tglbtnDonut);
		pnlNorth.add(tglbtnDonut);

		JPanel pnlSouth = new JPanel();
		contentPane.add(pnlSouth, BorderLayout.SOUTH);
		JToggleButton tglbtnSelect = new JToggleButton("Select");
	
		tglbtnSelect.setBackground(new Color(240, 230, 140));
		buttonGroup.add(tglbtnSelect);
		pnlSouth.add(tglbtnSelect);

		JToggleButton tglbtnModify = new JToggleButton("Modify");
		tglbtnModify.setBackground(new Color(240, 230, 140));
		tglbtnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlCenter.modify();
			}
		});
		buttonGroup.add(tglbtnModify);
		pnlSouth.add(tglbtnModify);

		JToggleButton tglbtnDelete = new JToggleButton("Delete");
		tglbtnDelete.setBackground(new Color(240, 230, 140));
		tglbtnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlCenter.delete();
			}
		});
		buttonGroup.add(tglbtnDelete);
		pnlSouth.add(tglbtnDelete);

		// PnlDrawing pnlCenter=new PnlDrawing();
		pnlCenter.setBackground(Color.white);
		//*******************************ISCRTAVANJE*********************************************
		pnlCenter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Point click = new Point(e.getX(), e.getY());
				if (tglbtnPoint.isSelected()) {
					Point p = new Point(e.getX(), e.getY(), Color.black);
					pnlCenter.shapesAdd(p);
				}
				if (tglbtnLine.isSelected()) {

					if (startPoint == null) {
						startPoint = click;
					} else {
						Line l = new Line(startPoint, new Point(e.getX(), e.getY()), Color.black);
						pnlCenter.shapesAdd(l);
						startPoint = null;
					}

				}
				if (tglbtnRectangle.isSelected()) {
					DlgRectangle dlgRectangle = new DlgRectangle();
					dlgRectangle.getTxtULPX().setText(Integer.toString(e.getX()));
					dlgRectangle.getTxtULPY().setText(Integer.toString(e.getY()));
					dlgRectangle.getTxtULPX().setEditable(false);
					dlgRectangle.getTxtULPY().setEditable(false);
					dlgRectangle.setVisible(true);

					if (dlgRectangle.isOkay) {
						int w = Integer.parseInt(dlgRectangle.getTxtWidth().getText());
						int h = Integer.parseInt(dlgRectangle.getTxtHeight().getText());
						Color colorRect = null;
						Color borderRect = null;
						if (dlgRectangle.colorOkay) {
							colorRect = dlgRectangle.getColor();
						}
						else 
						{
							colorRect= Color.black;
						}
						if (dlgRectangle.borderOkay) {
							borderRect = dlgRectangle.getBorder();
						} 
						else 
						{
							borderRect = Color.white;
						}
						Rectangle r = new Rectangle(new Point(e.getX(), e.getY()), w, h,borderRect,colorRect);
						pnlCenter.shapesAdd(r);
					}

				}
				if (tglbtnCircle.isSelected()) {
					DlgCircle dlgCircle = new DlgCircle();
					dlgCircle.getTxtCordinateX().setText(Integer.toString(e.getX()));
					dlgCircle.getTxtCordinateY().setText(Integer.toString(e.getY()));
					dlgCircle.getTxtCordinateX().setEditable(false); // da kordinate ne mogu da se manjaju
					dlgCircle.getTxtCordinateY().setEditable(false);
					dlgCircle.setVisible(true);

					if (dlgCircle.isOk) {
						Color colorCircle = null;
						Color borderCircle = null;
						if (dlgCircle.colorOkay) {
							colorCircle = dlgCircle.getColorC();
						}
						else {
							colorCircle=Color.white;
						}

						if (dlgCircle.borderOkay) {
							borderCircle = dlgCircle.getBorderC();
						}
						else {
							borderCircle=Color.black;
						}
	
						Circle c = new Circle(new Point(e.getX(), e.getY()),
								(Integer.parseInt(dlgCircle.getTxtRadius().getText())), borderCircle, colorCircle);
						pnlCenter.shapesAdd(c);
					}

				}
				if (tglbtnDonut.isSelected()) {
					DlgDonut dlgDonut = new DlgDonut();
					dlgDonut.getTxtCordinateX().setText(Integer.toString(e.getX()));
					dlgDonut.getTxtCordinateY().setText(Integer.toString(e.getY()));
					dlgDonut.getTxtCordinateX().setEditable(false);
					dlgDonut.getTxtCordinateY().setEditable(false);
					dlgDonut.setVisible(true);

					if (dlgDonut.isOk1) {
						int radius = Integer.parseInt(dlgDonut.getTxtRadius().getText());
						int inner = Integer.parseInt(dlgDonut.getTxtInnerRadius().getText());
						Color colorDonut = null;
						Color borderDonut = null;
						if (dlgDonut.isBorderOkay()) {
							colorDonut = dlgDonut.getColor();
						}
						else {
							colorDonut=Color.black;
						}
						if (dlgDonut.isColorOkay()) {
							borderDonut = dlgDonut.getBorder();
						} 
						else {
							borderDonut=Color.black;
						}
	
						Donut d = new Donut(new Point(e.getX(), e.getY()), radius, inner, borderDonut, colorDonut);
						pnlCenter.shapesAdd(d);
					}

				}
				if (tglbtnSelect.isSelected()) {
					Point p = new Point(e.getX(), e.getY());
					pnlCenter.selShape(e);
					// pnlCenter.selShape((Shape)(new Point(e.getX(),e.getY())));
				}
			}
			
				 * if(issSelected()) { pnlCenter.selShape(e); setsSelected(false); }
				 * /*if(issDelete()) { pnlCenter.delete(); setsDelete(false); }
				 * 
				 * if(issModify()) { pnlCenter.modify(); setsModify(false); }
				 
			
		});
		contentPane.add(pnlCenter, BorderLayout.CENTER);
		GridBagLayout gbl_pnlCenter = new GridBagLayout();
		gbl_pnlCenter.columnWidths = new int[] { 0 };
		gbl_pnlCenter.rowHeights = new int[] { 0 };
		gbl_pnlCenter.columnWeights = new double[] { Double.MIN_VALUE };
		gbl_pnlCenter.rowWeights = new double[] { Double.MIN_VALUE };
		pnlCenter.setLayout(gbl_pnlCenter);
		pnlNorth.setBackground(Color.orange);
		pnlSouth.setBackground(Color.ORANGE);
		pnlSouth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5)); 

		/*
		 * public boolean issSelected() { return sSelected; }
		 * 
		 * public void setsSelected(boolean sSelected) { this.sSelected = sSelected; }
		 

	}
} 
*/
