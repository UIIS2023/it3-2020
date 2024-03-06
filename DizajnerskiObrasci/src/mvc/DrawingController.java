package mvc;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import adapter.HexagonAdapter;
import command.AddShapeCmd;
import command.BringToBackCmd;
import command.BringToFrontCmd;
import command.Command;
import command.DeselectCmd;
import command.RemoveShapeCmd;
import command.SelectCmd;
import command.ToBackCmd;
import command.ToFrontCmd;
import command.UpdateCircleCmd;
import command.UpdateDonutCmd;
import command.UpdateHexagonCmd;
import command.UpdateLineCmd;
import command.UpdatePointCmd;
import command.UpdateRectangleCmd;
import drawing.DlgCircle;
import drawing.DlgDelete;
import drawing.DlgDonut;
import drawing.DlgHexagon;
import drawing.DlgLine;
import drawing.DlgPoint;
import drawing.DlgRectangle;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import hexagon.Hexagon;
import strategy.DrawingSaving;
import strategy.LogSaving;

public class DrawingController {
	
	private DrawingModel model;
	private DrawingFrame frame;
	private Point startPoint;
    private ArrayList<Command> commandList = new ArrayList();
    private ArrayList<Command> redoCommandList = new ArrayList();
    private ArrayList<Shape> selectedShapes = new ArrayList();
	private AddShapeCmd addShapeCmd;
	private ToFrontCmd toFrontCmd;
	private ToBackCmd toBackCmd;
	private BringToFrontCmd bringToFrontCmd;
	private BringToBackCmd bringToBackCmd;
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
	private Color innerColor;
	private Color borderColor;
	
	String line;
	private PropertyChangeSupport pcs;
	private int executeIndex=0;
	private DefaultListModel<String> logModel;

	
	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
		this.logModel=frame.logModel;
		pcs = new PropertyChangeSupport(this);
	}
	
	
	//**********************************MOUSECLICKED / DRAWING**************************************
	
	public void mouseClicked(MouseEvent e) {
		Point click = new Point(e.getX(), e.getY());
		if (frame.getTglbtnPoint().isSelected()) {
			DlgPoint dlgPoint = new DlgPoint();
			dlgPoint.getBtnColor().setBackground(frame.getBtnBorderCol().getBackground());
			Point p = new Point(e.getX(), e.getY(), frame.getBtnBorderCol().getBackground());
			p.setColor(dlgPoint.getBtnColor().getBackground());
			addShapeCmd = new AddShapeCmd(model, p);
			addShapeCmd.execute();
			commandList.add(addShapeCmd);
			logModel.addElement("Added Point"+ p.toString());
			redoCommandList.clear();
			//model.shapesAdd(p);
			frame.repaint();
		}
		if (frame.getTglbtnLine().isSelected()) {
			DlgLine dlgLine = new DlgLine();
			dlgLine.getBtnColor().setBackground(frame.getBtnBorderCol().getBackground());
			if (startPoint == null) {
				startPoint = click;
			} else {
				Line l = new Line(startPoint, new Point(e.getX(), e.getY()), frame.getBtnBorderCol().getBackground());
				l.setColor(dlgLine.getBtnColor().getBackground());
				addShapeCmd = new AddShapeCmd(model, l);
				addShapeCmd.execute();
				commandList.add(addShapeCmd);
				logModel.addElement("Added Line"+ l.toString());
				//model.shapesAdd(l);
				redoCommandList.clear();
				frame.repaint();
				startPoint = null;
			}
		}
		if (frame.getTglbtnRectangle().isSelected()) {
			DlgRectangle dlgRectangle = new DlgRectangle();
			dlgRectangle.getTxtULPX().setText(Integer.toString(e.getX()));
			dlgRectangle.getTxtULPY().setText(Integer.toString(e.getY()));
			dlgRectangle.getTxtULPX().setEditable(false);
			dlgRectangle.getTxtULPY().setEditable(false);
			dlgRectangle.getBtnInnerCol().setBackground(frame.getBtnInnerCol().getBackground());
			dlgRectangle.getBtnBorderCol().setBackground(frame.getBtnBorderCol().getBackground());
			dlgRectangle.getBtnInnerCol().setEnabled(false);
			dlgRectangle.getBtnBorderCol().setEnabled(false);
			dlgRectangle.setVisible(true);

			if (dlgRectangle.isOkay()) {
				int w = Integer.parseInt(dlgRectangle.getTxtWidth().getText());
				int h = Integer.parseInt(dlgRectangle.getTxtHeight().getText());
				Color colorRect = frame.getBtnInnerCol().getBackground();;
				Color borderRect = frame.getBtnBorderCol().getBackground();
		
				/*if (dlgRectangle.isColorOkay()) {
					colorRect = dlgRectangle.getColor();
				}
				else 
				{
					colorRect= Color.black;
				}
				if (dlgRectangle.isBorderOkay()) {
					borderRect = dlgRectangle.getBorder();
				} 
				else 
				{
					borderRect = Color.white;
				}*/
				Rectangle r = new Rectangle(new Point(e.getX(), e.getY()),w,h);
				System.out.println(dlgRectangle.getBtnBorderCol().getBackground());
				r.setInnerColor(dlgRectangle.getBtnInnerCol().getBackground());
				r.setColor(dlgRectangle.getBtnBorderCol().getBackground());
				addShapeCmd=new AddShapeCmd(model, r);
				addShapeCmd.execute();
				commandList.add(addShapeCmd);
				logModel.addElement("Added Rectangle"+ r.toString());
				//model.shapesAdd(r);
				redoCommandList.clear();
				frame.repaint();
			}

		}
		if (frame.getTglbtnDonut().isSelected()) {
			DlgDonut dlgDonut = new DlgDonut();
			dlgDonut.getTxtCordinateX().setText(Integer.toString(e.getX()));
			dlgDonut.getTxtCordinateY().setText(Integer.toString(e.getY()));
			dlgDonut.getTxtCordinateX().setEditable(false);
			dlgDonut.getTxtCordinateY().setEditable(false);
			dlgDonut.getBtnInnerColor().setBackground(frame.getBtnInnerCol().getBackground());
			dlgDonut.getBtnBorderColor().setBackground(frame.getBtnBorderCol().getBackground());
		    dlgDonut.getBtnInnerColor().setEnabled(false);
		    dlgDonut.getBtnBorderColor().setEnabled(false);
			dlgDonut.setVisible(true);

			if (dlgDonut.isOk1()) {
				int radius = Integer.parseInt(dlgDonut.getTxtRadius().getText());
				int inner = Integer.parseInt(dlgDonut.getTxtInnerRadius().getText());
				Color colorDonut = frame.getBtnInnerCol().getBackground();
				Color borderDonut = frame.getBtnBorderCol().getBackground();
				/*if (dlgDonut.isBorderOkay()) {
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
				}*/

				Donut d = new Donut(new Point(e.getX(), e.getY()), radius, inner, borderDonut, colorDonut);
				addShapeCmd=new AddShapeCmd(model, d);
				addShapeCmd.execute();
				commandList.add(addShapeCmd);
				logModel.addElement("Added Donut"+ d.toString());
				//model.shapesAdd(d);
				redoCommandList.clear();
				frame.repaint();
			}

		}
		if (frame.getTglbtnCircle().isSelected()) {
			DlgCircle dlgCircle = new DlgCircle();
			dlgCircle.getTxtCordinateX().setText(Integer.toString(e.getX()));
			dlgCircle.getTxtCordinateY().setText(Integer.toString(e.getY()));
			dlgCircle.getTxtCordinateX().setEditable(false); // da kordinate ne mogu da se manjaju
			dlgCircle.getTxtCordinateY().setEditable(false);
			dlgCircle.getBtnInnerCol().setBackground(frame.getBtnInnerCol().getBackground());
			dlgCircle.getBtnBorderCol().setBackground(frame.getBtnBorderCol().getBackground());
			dlgCircle.getBtnInnerCol().setEnabled(false);
			dlgCircle.getBtnBorderCol().setEnabled(false);
			dlgCircle.setVisible(true);

			if (dlgCircle.isOk()) {
				Color colorCircle = frame.getBtnInnerCol().getBackground();
				Color borderCircle = frame.getBtnBorderCol().getBackground();
			/*	if (dlgCircle.isColorOkay()) {
					colorCircle = dlgCircle.getColorC();
				}
				else {
					colorCircle=Color.white;
				}

				if (dlgCircle.isBorderOkay()) {
					borderCircle = dlgCircle.getBorderC();
				}
				else {
					borderCircle=Color.black;
				}*/

				Circle c = new Circle(new Point(e.getX(), e.getY()),
						(Integer.parseInt(dlgCircle.getTxtRadius().getText())), borderCircle, colorCircle);
				addShapeCmd = new AddShapeCmd(model, c);
				addShapeCmd.execute();
				commandList.add(addShapeCmd);
				logModel.addElement("Added Circle"+ c.toString());
				//model.shapesAdd(c);
				redoCommandList.clear();
				frame.repaint();
			}
		}
		
		if(frame.getTglbtnHexagon().isSelected())
		{
			DlgHexagon dlgHexagon = new DlgHexagon();
			dlgHexagon.getTxtX().setText(Integer.toString(e.getX()));
			dlgHexagon.getTxtY().setText(Integer.toString(e.getY()));
	      //dlgHexagon.getTxtRadius().setText(Integer.toString(donut.getRadius()));
		    dlgHexagon.getBtnHexInner().setBackground(frame.getBtnInnerCol().getBackground());
			dlgHexagon.getBtnHexBorder().setBackground(frame.getBtnBorderCol().getBackground());
			dlgHexagon.getBtnHexInner().setEnabled(false);
			dlgHexagon.getBtnHexBorder().setEnabled(false);
			dlgHexagon.setVisible(true);
			  if (dlgHexagon.isGood()) {
			    HexagonAdapter hexA = new HexagonAdapter(new Hexagon(e.getX(),e.getY(),Integer.parseInt(dlgHexagon.getTxtRadius().getText())));
				hexA.setInnerColor(frame.getBtnInnerCol().getBackground());
				hexA.setColor(frame.getBtnBorderCol().getBackground());
				hexA.setSelected(false);
				addShapeCmd=new AddShapeCmd(model, hexA);
				addShapeCmd.execute();
				commandList.add(addShapeCmd);
				logModel.addElement("Added Hexagon"+ hexA.toString());
			    //model.getShapes().add(hexA);
				redoCommandList.clear();
				frame.repaint();
			}
		    
		}
		
		if (frame.getTglbtnSelect().isSelected()) {
			Point p = new Point(e.getX(), e.getY());
			selShape(e); 
		}
	}
	
//*****************************************SELECT***********************************************
		
	//private boolean ctrlPressed = false;
	
	/*private void checkedCtrlPressed (MouseEvent e) {
		
		if(e.getModifiersEx()==128) {
			ctrlPressed=true;
			return;
		}
		ctrlPressed=false;
	}*/
	
	public void selShape(MouseEvent e) {
		int count=0;
	  
		if (model.getShapes().size() == 0) {
			JOptionPane.showMessageDialog(null, "There is no shapes :( ");
		} else {
			Shape selected = null; // pocetna vrednost na null
			for(int i=0;i<model.getShapes().size();i++) {
				if(model.getShapes().get(i).isSelected()) {
					
				}
				else {
					Shape shape=model.getShapes().get(i);
					
				if(model.getShapes().get(i).contains(e.getX(), e.getY())) {
					selected=shape;	
			}
		  }
		}
			if (selected != null) {
				//selectedShapes.add(selected);
				//selected.setSelected(true); // oblik je selectovan	
                if(selected instanceof Point) {
                	logModel.addElement("Selected Point" + selected.toString()+ "," + model.getShapes().indexOf(selected));
                }
                else if(selected instanceof Line) {
                	logModel.addElement("Selected Line" + selected.toString()+ "," + model.getShapes().indexOf(selected));
                } 
                else if(selected instanceof Donut) {
                	logModel.addElement("Selected Donut" + selected.toString()+ "," + model.getShapes().indexOf(selected));
                }
                else if(selected instanceof Circle) {
                	logModel.addElement("Selected Circle" + selected.toString()+ "," + model.getShapes().indexOf(selected));
                } 
                else if(selected instanceof Rectangle) {
                	logModel.addElement("Selected Rectangle" + selected.toString()+ "," + model.getShapes().indexOf(selected));
                }
                else if(selected instanceof HexagonAdapter) {
                	logModel.addElement("Selected Hexagon" + selected.toString()+ "," + model.getShapes().indexOf(selected));
                }
                SelectCmd selectCmd = new SelectCmd(selectedShapes, selected);
				commandList.add(selectCmd);
				selectCmd.execute();
				frame.repaint();
			}
			else {
				for(int i=0;i<model.getShapes().size();i++) {
					if(model.getShapes().get(i).isSelected()) {
					  if(model.getShapes().get(i).contains(e.getX(), e.getY())) {
						  if(model.getShapes().get(i) instanceof Point) {
							  logModel.addElement("Deselected Point" + model.getShapes().get(i).toString()+ "," + model.getShapes().indexOf(model.getShapes().get(i)));
						  }
						  else if(model.getShapes().get(i) instanceof Line){
							  logModel.addElement("Deselected Line" + model.getShapes().get(i).toString()+ "," + model.getShapes().indexOf(model.getShapes().get(i)));

						  } else  if(model.getShapes().get(i) instanceof Rectangle) {
							  logModel.addElement("Deselected Rectangle" + model.getShapes().get(i).toString()+ "," + model.getShapes().indexOf(model.getShapes().get(i)));
						  }
						  else  if(model.getShapes().get(i) instanceof Donut) {
							  logModel.addElement("Deselected Donut" + model.getShapes().get(i).toString()+ "," + model.getShapes().indexOf(model.getShapes().get(i)));
						  }
						  else  if(model.getShapes().get(i) instanceof Circle) {
							  logModel.addElement("Deselected Circle" + model.getShapes().get(i).toString()+ "," + model.getShapes().indexOf(model.getShapes().get(i)));
						  }

						  else  if(model.getShapes().get(i) instanceof HexagonAdapter) {
							  logModel.addElement("Deselected Hexagon" + model.getShapes().get(i).toString()+ "," + model.getShapes().indexOf(model.getShapes().get(i)));
						  }
						  DeselectCmd deselectCmd = new DeselectCmd(selectedShapes, (Shape)model.getShapes().get(i));
						  commandList.add(deselectCmd);
						  deselectCmd.execute();
						  frame.repaint();
						  break;
					  }
					  else {
						  count++;
					  }
					}
					else {
						count++;
					}
				}
			}
			
			if(count==model.getShapes().size()) {
				deselect((ArrayList<Shape>)model.getShapes());
			}
		
			observer();
	
		}
		System.out.println(selectedShapes.size());
		//System.out.println(count);
	}
	
//********************************************DESELECT*********************************************
	
	//deselekcija vise oblika
	public void deselect(ArrayList<Shape> shapes) {
		
		for(int i = 0;i<shapes.size();i++) {
			if(shapes.get(i).isSelected()) {
			if(shapes.get(i) instanceof Point) {
			    logModel.addElement("Deselected Point" + model.getShapes().get(i).toString() + "," + model.getShapes().indexOf(shapes.get(i)));
		    }
		    else if(shapes.get(i) instanceof Line) {
		    	logModel.addElement("Deselected Line" + model.getShapes().get(i).toString() + "," + model.getShapes().indexOf(shapes.get(i)));
		    }
			else if(shapes.get(i) instanceof Rectangle) {
				logModel.addElement("Deselected Rectangle" + model.getShapes().get(i).toString() + "," + model.getShapes().indexOf(shapes.get(i)));
			}
			else if(shapes.get(i) instanceof Donut) {
				logModel.addElement("Deselected Donut" + model.getShapes().get(i).toString() + "," + model.getShapes().indexOf(shapes.get(i)));
			}
			else if(shapes.get(i) instanceof Circle) {
				logModel.addElement("Deselected Circle" + model.getShapes().get(i).toString() + "," + model.getShapes().indexOf(shapes.get(i)));
			}

			else if(shapes.get(i) instanceof HexagonAdapter) {
				logModel.addElement("Deselected Hexagon" + model.getShapes().get(i).toString() + "," + model.getShapes().indexOf(shapes.get(i)));
			}
				
			DeselectCmd deselectCmd = new DeselectCmd(selectedShapes, shapes.get(i));
			commandList.add(deselectCmd);
			deselectCmd.execute();
			}			
	  }
		frame.repaint();
}
	
	
//*******************************************MODIFY*************************************************

	public void modify() {
		if (model.getShapes().size() == 0) {
			JOptionPane.showMessageDialog(null, "There is no shapes :( ");
		}
		else {
			boolean selected = false;
			for (int i = 0; i < model.getShapes().size(); i++) {
				if (model.getShapes().get(i).isSelected())
				{
				   selected=true;
				}
		     }
		  	if(selected=false) {
		  		JOptionPane.showMessageDialog(null, "Select the shape first");
		  		return;
		  	}
		for (int i = 0; i < model.getShapes().size(); i++) {
			if (model.getShapes().get(i).isSelected()) {
				if (model.getShapes().get(i) instanceof Point) {
					if (model.getShapes().get(i).isSelected()) {
						DlgPoint dlgPoint = new DlgPoint();
						Point point = (Point)model.getShapes().get(i);
						dlgPoint.getTxtCordinateX().setText(Integer.toString(point.getX()));
						dlgPoint.getTxtCordinateY().setText(Integer.toString(point.getY()));
						dlgPoint.getBtnColor().setBackground(point.getColor());
						dlgPoint.setVisible(true);
						if (dlgPoint.isGood()) {
							int x = (Integer.parseInt(dlgPoint.getTxtCordinateX().getText()));
							int y = (Integer.parseInt(dlgPoint.getTxtCordinateY().getText()));
							//Color pointColor = frame.getBtnBorderCol().getBackground();
							
							/*if (dlgPoint.isColorIsOk()) {
								pointColor = dlgPoint.getFill();
							} else {
								pointColor = dlgPoint.getFill();
							}*/
							Point p = new Point(x, y);
							p.setColor(dlgPoint.getBtnColor().getBackground());
							p.setSelected(true);
							UpdatePointCmd updatePointCmd = new UpdatePointCmd(point, p);
							commandList.add(updatePointCmd);
							updatePointCmd.execute();
							logModel.addElement("Modified Point"+ p.toString());
							//model.getShapes().add(p);
							//model.getShapes().remove(i);
							redoCommandList.clear();
							frame.repaint();
						}
					} else {
					//	model.getShapes().get(i).setSelected(false);
						frame.repaint();
					//setSelDelete(true);
					}
				}

				else if (model.getShapes().get(i) instanceof Line) {
					if (model.getShapes().get(i).isSelected()) {
						DlgLine dlgLine = new DlgLine();
						Line line = (Line) model.getShapes().get(i);
						dlgLine.getTxtSPX().setText(Integer.toString(line.getStartPoint().getX()));
						dlgLine.getTxtSPY().setText(Integer.toString(line.getStartPoint().getY()));
						dlgLine.getTxtEPX().setText(Integer.toString(line.getEndPoint().getX()));
						dlgLine.getTxtEPY().setText(Integer.toString(line.getEndPoint().getY()));
						dlgLine.getBtnColor().setBackground(line.getColor());
						dlgLine.setVisible(true);
						if (dlgLine.isGood()) {
							int sX = Integer.parseInt(dlgLine.getTxtSPX().getText());
							int sY = Integer.parseInt(dlgLine.getTxtSPY().getText());
							int eX = Integer.parseInt(dlgLine.getTxtEPX().getText());
							int eY = Integer.parseInt(dlgLine.getTxtEPY().getText());
							Color lineColor = frame.getBtnBorderCol().getBackground();
							/*if (dlgLine.isColorIsOk()) {
								lineColor = dlgLine.getFill();
							} else {
								lineColor = dlgLine.getFill();
							}*/
							
							Line l = new Line(new Point(sX, sY), new Point(eX, eY), lineColor);
							l.setColor(dlgLine.getBtnColor().getBackground());
							l.setSelected(true);
							UpdateLineCmd updateLineCmd = new UpdateLineCmd(line, l);
							commandList.add(updateLineCmd);
							updateLineCmd.execute();
							logModel.addElement("Modified Line"+ l.toString());
						  //model.getShapes().add(l);
						  //model.getShapes().remove(i);
							redoCommandList.clear();
							frame.repaint();

						}

					} else {
						//model.getShapes().get(i).setSelected(false);
						frame.repaint();
						//setSelDelete(true);
					}

				} else if (model.getShapes().get(i) instanceof Rectangle) {
					if (model.getShapes().get(i).isSelected()) {
						DlgRectangle dlgRectangle = new DlgRectangle();
						Rectangle rect = (Rectangle) model.getShapes().get(i);
						dlgRectangle.getTxtULPX().setText(Integer.toString(rect.getUpperLeft().getX()));
						dlgRectangle.getTxtULPY().setText(Integer.toString(rect.getUpperLeft().getY()));
						dlgRectangle.getTxtWidth().setText(Integer.toString(rect.getWidth()));
						dlgRectangle.getTxtHeight().setText(Integer.toString(rect.getHeight()));
						dlgRectangle.getBtnInnerCol().setBackground(rect.getInnerColor());
						dlgRectangle.getBtnBorderCol().setBackground(rect.getColor());
						dlgRectangle.setVisible(true);

						if (dlgRectangle.isOkay()) {
							int uLX = Integer.parseInt(dlgRectangle.getTxtULPX().getText());
							int uLY = Integer.parseInt(dlgRectangle.getTxtULPY().getText());
							int h = Integer.parseInt(dlgRectangle.getTxtHeight().getText());
							int w = Integer.parseInt(dlgRectangle.getTxtWidth().getText());
							Color colorRect = frame.getBtnInnerCol().getBackground();;
							Color borderRect = frame.getBtnBorderCol().getBackground();
							/*if (dlgRectangle.isColorOkay()) {
								colorRect = dlgRectangle.getColor();

							} else
								colorRect = dlgRectangle.getColor();

							if (dlgRectangle.isBorderOkay()) {
								borderRect = dlgRectangle.getBorder();
							} else {
								borderRect = dlgRectangle.getBorder();
							}*/
							Rectangle r = new Rectangle(new Point(uLX, uLY), w, h, borderRect, colorRect);
						    r.setSelected(true);
						    r.setInnerColor(dlgRectangle.getBtnInnerCol().getBackground());
							r.setColor(dlgRectangle.getBtnBorderCol().getBackground());
						    UpdateRectangleCmd updateRectangleCmd = new UpdateRectangleCmd(rect, r);
						    commandList.add(updateRectangleCmd);
						    updateRectangleCmd.execute();
							logModel.addElement("Modified Rectangle"+ r.toString());
							redoCommandList.clear();
							frame.repaint();
						}

					} else {
					//	model.getShapes().get(i).setSelected(false);
						frame.repaint();
					//	setSelDelete(true);
					}

				}
				 else if (model.getShapes().get(i) instanceof Donut) {
						if (model.getShapes().get(i).isSelected()) {
							DlgDonut dlgDonut = new DlgDonut();
							Donut donut = (Donut)model.getShapes().get(i);
							dlgDonut.getTxtCordinateX().setText(Integer.toString(donut.getCenter().getX()));
							dlgDonut.getTxtCordinateY().setText(Integer.toString(donut.getCenter().getY()));
							dlgDonut.getTxtRadius().setText(Integer.toString(donut.getRadius()));
							dlgDonut.getTxtInnerRadius().setText(Integer.toString(donut.getInnerRadius()));
							dlgDonut.getBtnInnerColor().setBackground(donut.getInnerColor());
							dlgDonut.getBtnBorderColor().setBackground(donut.getColor());
							dlgDonut.setVisible(true);
							if (dlgDonut.isOk1()) {
								int dX = Integer.parseInt(dlgDonut.getTxtCordinateX().getText());
								int dY = Integer.parseInt(dlgDonut.getTxtCordinateY().getText());
								int radius = Integer.parseInt(dlgDonut.getTxtRadius().getText());
								int inner = Integer.parseInt(dlgDonut.getTxtInnerRadius().getText());
								Color colorDonut = frame.getBtnInnerCol().getBackground();
								Color borderDonut = frame.getBtnBorderCol().getBackground();
								/*	if (dlgDonut.isColorOkay()) {
									colorDonut = dlgDonut.getColor();
								} else {
									colorDonut = dlgDonut.getColor();
								}
								if (dlgDonut.isBorderOkay()) {
									borderDonut = dlgDonut.getBorder();
								} else {
									borderDonut = dlgDonut.getBorder();
								}*/
								Donut d = new Donut(new Point(dX, dY), radius, inner, borderDonut, colorDonut);
								d.setSelected(true);
								d.setInnerColor(dlgDonut.getBtnInnerColor().getBackground());
								d.setColor(dlgDonut.getBtnBorderColor().getBackground());
								UpdateDonutCmd updateDonutCmd = new UpdateDonutCmd(donut, d);
	                            commandList.add(updateDonutCmd);
	                            updateDonutCmd.execute();
								logModel.addElement("Modified Donut"+ d.toString());
								redoCommandList.clear();
								//model.getShapes().add(d);
								//model.getShapes().remove(model.getShapes().get(i));
								frame.repaint();
							}
						} else {
						//model.getShapes().get(i).setSelected(false);
							frame.repaint();
						//	setSelDelete(true);
						}
					}
				else if (model.getShapes().get(i) instanceof Circle && model.getShapes().get(i) instanceof Donut == false) {
					if (model.getShapes().get(i).isSelected()) {
						DlgCircle dlgCircle = new DlgCircle();
						Circle circle = (Circle) model.getShapes().get(i);
						dlgCircle.getTxtCordinateX().setText(Integer.toString(circle.getCenter().getX()));
						dlgCircle.getTxtCordinateY().setText(Integer.toString(circle.getCenter().getY()));
						dlgCircle.getTxtRadius().setText(Integer.toString(circle.getRadius()));
						dlgCircle.getBtnInnerCol().setBackground(circle.getInnerColor());
						dlgCircle.getBtnBorderCol().setBackground(circle.getColor());
						dlgCircle.setVisible(true);
						if (dlgCircle.isOk()) {
							int cX = Integer.parseInt(dlgCircle.getTxtCordinateX().getText());
							int cY = Integer.parseInt(dlgCircle.getTxtCordinateY().getText());
							int rad = Integer.parseInt(dlgCircle.getTxtRadius().getText());
							Color colorCircle = frame.getBtnInnerCol().getBackground();
							Color borderCircle = frame.getBtnBorderCol().getBackground();
							/*if (dlgCircle.isColorOkay()) {
								colorCircle = dlgCircle.getColorC();
							} else {
								colorCircle = dlgCircle.getColorC();
							}
							if (dlgCircle.isBorderOkay()) {

								borderCircle = dlgCircle.getBorderC();
							} else {
								borderCircle = dlgCircle.getBorderC();
							}*/
							
							Circle c = new Circle(new Point(cX, cY), rad, borderCircle, colorCircle);
							c.setInnerColor(dlgCircle.getBtnInnerCol().getBackground());
							c.setColor(dlgCircle.getBtnBorderCol().getBackground());
							UpdateCircleCmd updateCircleCmd = new UpdateCircleCmd(circle, c);
							commandList.add(updateCircleCmd);
							updateCircleCmd.execute();
							logModel.addElement("Modified Circle"+ c.toString());
							//model.getShapes().add(c);
							//model.getShapes().remove(model.getShapes().get(i));
							redoCommandList.clear();
							frame.repaint();
							
						}
					} else {
					//	model.getShapes().get(i).setSelected(false);
						frame.repaint();
					//	setSelDelete(true);

					}

				}
				 else if (model.getShapes().get(i) instanceof HexagonAdapter) {
						if (model.getShapes().get(i).isSelected()) {
							DlgHexagon dlgHexagon = new DlgHexagon();
							HexagonAdapter ha = (HexagonAdapter)model.getShapes().get(i);
							dlgHexagon.getTxtX().setText(Integer.toString(ha.getHexagon().getX()));
							dlgHexagon.getTxtY().setText(Integer.toString(ha.getHexagon().getY()));
					        dlgHexagon.getTxtRadius().setText(Integer.toString(ha.getHexagon().getR()));
						    dlgHexagon.getBtnHexInner().setBackground(ha.getInnerColor());
							dlgHexagon.getBtnHexBorder().setBackground(ha.getColor());
							dlgHexagon.setVisible(true);
							  if (dlgHexagon.isGood()) {
								int dX = Integer.parseInt(dlgHexagon.getTxtX().getText());
								int dY = Integer.parseInt(dlgHexagon.getTxtY().getText());
								int radius = Integer.parseInt(dlgHexagon.getTxtRadius().getText());
								/*if (dlgHexagon.isGood()) {
									colorDonut = dlgHexagon.getColor();
								} else {
									colorDonut = dlgHexagon.getColor();
								}
								if (dlgDonut.isBorderOkay()) {
									borderDonut = dlgDonut.getBorder();
								} else {
									borderDonut = dlgDonut.getBorder();
								}*/
								HexagonAdapter hexA = new HexagonAdapter(new Hexagon(dX, dY, radius));
								hexA.setInnerColor(dlgHexagon.getBtnHexInner().getBackground());
								hexA.setColor(dlgHexagon.getBtnHexBorder().getBackground());
								hexA.setSelected(true);
								//model.getShapes().add(hexA);
							    //model.getShapes().remove(model.getShapes().get(i));
								UpdateHexagonCmd updateHexagonCmd = new UpdateHexagonCmd(ha, hexA);
								commandList.add(updateHexagonCmd);
								updateHexagonCmd.execute();
								logModel.addElement("Modified Hexagon"+ hexA.toString());
								redoCommandList.clear();
								frame.repaint();
							}
						} else {
						//	model.getShapes().get(i).setSelected(false);
							frame.repaint();
						//	setSelDelete(true);
						}
					}
			}
		
		}
	}
}

//**********************************************DELETE**************************************************
	public void delete() {
     if(selectedShapes.size()==1) {
		if (model.getShapes().size() == 0) {
			JOptionPane.showMessageDialog(null, "There is no shapes :( ");
			setSelDelete(true);
		} else
			setSelDelete(false);
		for (int i = 0; i < model.getShapes().size(); i++) {
			if (model.getShapes().get(i).isSelected()) {
				setSelDelete(true);
				DlgDelete dlgDelete = new DlgDelete();
				dlgDelete.setVisible(true);
				if (dlgDelete.isOkay()) {
					RemoveShapeCmd removeShapeCmd = new RemoveShapeCmd(model, model.getShapes().get(i));
					selectedShapes.remove(model.getShapes().get(i));
					if(model.getShapes().get(i) instanceof Point) {
					logModel.addElement("Deleted Point"+model.getShapes().get(i).toString());	
					}
					else if(model.getShapes().get(i) instanceof Line) {
						logModel.addElement("Deleted Line"+model.getShapes().get(i).toString());	
					}
					else if(model.getShapes().get(i) instanceof Donut) {
						logModel.addElement("Deleted Donut"+model.getShapes().get(i).toString());	
					}
					else if(model.getShapes().get(i) instanceof Circle) {
						logModel.addElement("Deleted Circle"+model.getShapes().get(i).toString());	
					}
					else if(model.getShapes().get(i) instanceof Rectangle) {
						logModel.addElement("Deleted Rectangle"+model.getShapes().get(i).toString());	
					}
					else if(model.getShapes().get(i) instanceof HexagonAdapter) {
						logModel.addElement("Deleted Hexagon"+model.getShapes().get(i).toString());	
					}
					removeShapeCmd.execute();
					commandList.add(removeShapeCmd);
				//    model.getShapes().remove(model.getShapes().get(i));
				    redoCommandList.clear();
				}
				else {
					if(model.getShapes().get(i) instanceof Point) {
						logModel.addElement("Deselected Point"+model.getShapes().get(i).toString());	
					}
					else if(model.getShapes().get(i) instanceof Line) {
						logModel.addElement("Deselected Line"+model.getShapes().get(i).toString());	
					}
					else if(model.getShapes().get(i) instanceof Donut) {
						logModel.addElement("Deselected Donut"+model.getShapes().get(i).toString());	
					}
					else if(model.getShapes().get(i) instanceof Circle) {
						logModel.addElement("Deselected Circle"+model.getShapes().get(i).toString());	
					}
					else if(model.getShapes().get(i) instanceof Rectangle) {
						logModel.addElement("Deselected Rectangle"+model.getShapes().get(i).toString());	
					}
					else if(model.getShapes().get(i) instanceof HexagonAdapter) {
						logModel.addElement("Deselected Hexagon"+model.getShapes().get(i).toString());	
					}
					DeselectCmd deselectCmd = new DeselectCmd(selectedShapes, (model.getShapes().get(i)));
					commandList.add(deselectCmd);
					deselectCmd.execute();
					redoCommandList.clear();
					frame.repaint();
				}
				/*else {
					//model.getShapes().get(i).setSelected(false);
					frame.repaint();
					setSelDelete(true);
				}*/
			}
		}
		frame.repaint();

		if (isSelDelete() == false) {
			JOptionPane.showMessageDialog(null, "Select the shape first");
			setSelDelete(true);

		}
     }
     else {
    	 ArrayList<Shape> shapes = new ArrayList<Shape>();
 				int option = JOptionPane.showConfirmDialog(null, "Do you want to delete all selected shapes?");
 				if(option==JOptionPane.YES_OPTION) {
 					for(int i=0;i<model.getShapes().size();i++) {
 						if(!model.getShapes().get(i).isSelected()) {
 							shapes.add(model.getShapes().get(i));
 						}
 						else {
 							RemoveShapeCmd removeShapeCmd = new RemoveShapeCmd(model, model.getShapes().get(i));
 		 					//removeShapeCmd.execute();
 		 					commandList.add(removeShapeCmd);
 		 					if(model.getShapes().get(i) instanceof Point) {
 		 						logModel.addElement("Deleted Point"+model.getShapes().get(i).toString());	
 		 						}
 		 						else if(model.getShapes().get(i) instanceof Line) {
 		 							logModel.addElement("Deleted Line"+model.getShapes().get(i).toString());	
 		 						}
 		 						else if(model.getShapes().get(i) instanceof Donut) {
 		 							logModel.addElement("Deleted Donut"+model.getShapes().get(i).toString());	
 		 						}
 		 						else if(model.getShapes().get(i) instanceof Circle) {
 		 							logModel.addElement("Deleted Circle"+model.getShapes().get(i).toString());	
 		 						}
 		 						else if(model.getShapes().get(i) instanceof Rectangle) {
 		 							logModel.addElement("Deleted Rectangle"+model.getShapes().get(i).toString());	
 		 						}
 		 						else if(model.getShapes().get(i) instanceof HexagonAdapter) {
 		 							logModel.addElement("Deleted Hexagon"+model.getShapes().get(i).toString());	
 		 						}
 		 					selectedShapes.remove(model.getShapes().get(i));
 						}
 					}
 						redoCommandList.clear();
 						model.getShapes().clear();
 					
 						for (int i=0;i<shapes.size();i++) {
 							model.getShapes().add(shapes.get(i));
 						}
 						frame.repaint();
 					}
 				else {
 					for(int i=0;i<model.getShapes().size();i++) {
 						if(model.getShapes().get(i).isSelected()) {
 							if(model.getShapes().get(i) instanceof Point) {
 								logModel.addElement("Deselected Point"+model.getShapes().get(i).toString());	
 							}
 							else if(model.getShapes().get(i) instanceof Line) {
 								logModel.addElement("Deselected Line"+model.getShapes().get(i).toString());	
 							}
 							else if(model.getShapes().get(i) instanceof Donut) {
 								logModel.addElement("Deselected Donut"+model.getShapes().get(i).toString());	
 							}
 							else if(model.getShapes().get(i) instanceof Circle) {
 								logModel.addElement("Deselected Circle"+model.getShapes().get(i).toString());	
 							}
 							else if(model.getShapes().get(i) instanceof Rectangle) {
 								logModel.addElement("Deselected Rectangle"+model.getShapes().get(i).toString());	
 							}
 							else if(model.getShapes().get(i) instanceof HexagonAdapter) {
 								logModel.addElement("Deselected Hexagon"+model.getShapes().get(i).toString());	
 							}
 							DeselectCmd deselectCmd = new DeselectCmd(selectedShapes, (model.getShapes().get(i)));
 							commandList.add(deselectCmd);
 							deselectCmd.execute();
 							redoCommandList.clear();
 							//frame.repaint();	
 						}
 					}
 					frame.repaint();
 				}
 				
 				//    model.getShapes().remove(model.getShapes().get(i));
 				   // redoCommandList.clear();
 					frame.repaint();
 	}
 }
 			
//***************************************INNER I BORDER******************************************
	
	public void innerColor() {
        innerColor = JColorChooser.showDialog(null, "Choose inner color:", innerColor);
        frame.getBtnInnerCol().getBackground();
		if (innerColor!=null) {
		frame.getBtnInnerCol().setBackground(innerColor);
     	}
    }
	
	public void borderColor() {
        borderColor = JColorChooser.showDialog(null, "Choose border color:", borderColor);
        frame.getBtnBorderCol().getBackground();
		if (borderColor!=null) {
		frame.getBtnBorderCol().setBackground(borderColor);
	    }
	}
	

	
//**************************************FRONT & BACK*********************************************

	public void toFront() {
		if(model.getShapes().size()==0) {
			JOptionPane.showMessageDialog(null, "There are no shapes!");
		}
		else if(selectedShapes.size()==0) {
			JOptionPane.showMessageDialog(null, "There are no selected shapes!");
		}
		else if(selectedShapes.size()==1) {
		// TODO Auto-generated method stub
		toFrontCmd = new ToFrontCmd(model, selectedShapes.get(selectedShapes.size()-1));
		commandList.add(toFrontCmd);
		logModel.addElement("To Front " + selectedShapes.get(selectedShapes.size()-1));
		toFrontCmd.execute();
		}
		else if(selectedShapes.size()>1) {
			JOptionPane.showMessageDialog(null, "There is more than one selected shape!");
		}
		redoCommandList.clear();
		frame.repaint(); //gde imamo akciju promene
	}
	
	public void toBack() {
		if(model.getShapes().size()==0) {
			JOptionPane.showMessageDialog(null, "There are no shapes!");
		}
		else if(selectedShapes.size()==0) {
			JOptionPane.showMessageDialog(null, "There are no selected shapes!");
		}
		else if(selectedShapes.size()==1)
		{
		// TODO Auto-generated method stub
		toBackCmd = new ToBackCmd(model, selectedShapes.get(selectedShapes.size()-1));
		commandList.add(toBackCmd);
		logModel.addElement("To Back " + selectedShapes.get(selectedShapes.size()-1));
		toBackCmd.execute();
		}
		else if(selectedShapes.size()>1) {
			JOptionPane.showMessageDialog(null, "There is more than one selected shape!");
		}
		redoCommandList.clear();
		frame.repaint();
	}
	
	public void bringToFront() {
		if(model.getShapes().size()==0) {
			JOptionPane.showMessageDialog(null, "There are no shapes!");
		}
		else if(selectedShapes.size()==0) {
			JOptionPane.showMessageDialog(null, "There are no selected shapes!");
		}
		else if(selectedShapes.size()==1)
		{
		// TODO Auto-generated method stub
		bringToFrontCmd = new BringToFrontCmd(model, selectedShapes.get(selectedShapes.size()-1));
		commandList.add(bringToFrontCmd);
		logModel.addElement("Bring To Front " + selectedShapes.get(selectedShapes.size()-1));
		bringToFrontCmd.execute();
		}
		else if(selectedShapes.size()>1) {
			JOptionPane.showMessageDialog(null, "There is more than one selected shape!");
		}
		redoCommandList.clear();
		frame.repaint();
	}
	
	public void bringToBack() {
		if(model.getShapes().size()==0) {
			JOptionPane.showMessageDialog(null, "There are no shapes!");
		}
		else if(selectedShapes.size()==0) {
			JOptionPane.showMessageDialog(null, "There are no selected shapes!");
		}
		else if(selectedShapes.size()==1) {
		// TODO Auto-generated method stub
		bringToBackCmd = new BringToBackCmd(model, selectedShapes.get(selectedShapes.size()-1));
		commandList.add(bringToBackCmd);
		logModel.addElement("Bring To Back " + selectedShapes.get(selectedShapes.size()-1));
		bringToBackCmd.execute();
		}
		else if(selectedShapes.size()>1) {
			JOptionPane.showMessageDialog(null, "There is more than one selected shape!");
		}
		redoCommandList.clear();
		frame.repaint();

	}

//**************************************UNDO & REDO***********************************************
	
	public void undo() {
		if(commandList.size()!=0) {
			logModel.addElement("Undo: " + commandList.get(commandList.size()-1));
			commandList.get(commandList.size()-1).unexecute();
			redoCommandList.add(commandList.get(commandList.size()-1));
			commandList.remove(commandList.size()-1);
			frame.repaint();
		}
		else {
			JOptionPane.showMessageDialog(null, "No undo commands");
		}

		observer();	
	}

	public void redo() {
		// TODO Auto-generated method stub
		if(redoCommandList.size()!=0) {
			commandList.add(redoCommandList.get(redoCommandList.size()-1));
			logModel.addElement("Redo: " + redoCommandList.get(redoCommandList.size()-1));
			redoCommandList.remove(redoCommandList.get(redoCommandList.size()-1));
			commandList.get(commandList.size()-1).execute();
		   frame.repaint();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "No redo commands");
            return;
		}

		observer();
	}
	
//**************************************SAVE,LOAD,LOAD NEXT********************************************
	
	public void save() {
		// TODO Auto-generated method stub
		LogSaving logSaving = new LogSaving(logModel);
		logSaving.save();
		
	}
	
	public void load() {
		// TODO Auto-generated method stub
		LogSaving loadLog = new LogSaving(model,frame);
		loadLog.load();
		executeIndex=0;
	}

	public void saveDrawing() {
		// TODO Auto-generated method stub
		DrawingSaving saveD = new DrawingSaving(model.getShapes());
	    saveD.save();
	}
	
	public void loadDrawing() {
		// TODO Auto-generated method stub
		DrawingSaving loadD = new DrawingSaving(model,frame);
		loadD.load();
		
	}
	
	public void loadNext() {
		// TODO Auto-generated method stub
		//System.out.println(frame.getLogList().getModel().getSize());

		// System.out.println(line);
	    	/*  System.out.println(split[1]);
			  System.out.println(split[2]);
			  System.out.println(split[3]);
			  System.out.println(split[4]);
			  System.out.println(split[5]);
			  System.out.println(split[6]);
			  System.out.println(split[7]);
			  System.out.println(split[8]);
			  System.out.println(split[9]);
			  System.out.println(split[10]);
			  System.out.println(split[11]);
			  System.out.println(split[12]);
			  System.out.println(split[13]);
			  System.out.println(split[14]);
			  System.out.println(split[15]);
			  System.out.println(split[16]);
			  System.out.println(split[17]);
			  System.out.println(split[18]);
			  System.out.println(split[19]);
			  System.out.println(split[20]);*/
		  
          if(executeIndex==frame.getLogList().getModel().getSize()) {
        	  JOptionPane.showMessageDialog(null, "File is loaded complitely");
          }
          else {
    		  String line = frame.getLogList().getModel().getElementAt(executeIndex);
    		  String[] split = line.split(",|\\(|\\)|\\ ");

		  //***********************************POINT*********************************//
		  if(split[1].contentEquals("Point")) {
			  String action =split[0];
			  Point p = new Point(Integer.parseInt(split[2]),Integer.parseInt(split[3]),new Color(Integer.parseInt(split[7])));

				  /*System.out.println(split[4]);
				  System.out.println(split[5]);
				  System.out.println(split[6]);
				  System.out.println(split[7]);*/
			  if(action.contentEquals("Added")){
				  addShapeCmd = new AddShapeCmd(model, p);
				  addShapeCmd.execute();
				  commandList.add(addShapeCmd);
				  redoCommandList.clear();
				  frame.repaint();
			  }
			  else if(action.contentEquals("Selected")) {
					int index = Integer.parseInt(split[8]);
					SelectCmd selectCmd = new SelectCmd(selectedShapes,model.getShapes().get(index));
					commandList.add(selectCmd);
					selectCmd.execute();
					frame.repaint();
			  } else if(action.contentEquals("Deselected")) {
					int index = Integer.parseInt(split[8]);
					DeselectCmd deselectCmd = new DeselectCmd(selectedShapes,model.getShapes().get(index));
					commandList.add(deselectCmd);
					deselectCmd.execute();
					frame.repaint();
			 
			  } else if(action.contentEquals("Modified")){
				  
				  for(int i=0;i<model.getShapes().size();i++) {
						if(model.getShapes().get(i) instanceof Point) {
							if(model.getShapes().get(i).isSelected()) {
								Point p1 = (Point)model.getShapes().get(i);
								//System.out.println(Integer.parseInt(split[7]));
								Point p2 = new Point(Integer.parseInt(split[2]),Integer.parseInt(split[3]),new Color(Integer.parseInt(split[7])));
								System.out.println(Integer.parseInt(split[7]));
								p2.setColor(new Color(Integer.parseInt(split[7])));
								p2.setSelected(true);
								UpdatePointCmd updatePointCmd = new UpdatePointCmd(p1,p2);
								updatePointCmd.execute();
								commandList.add(updatePointCmd);
								redoCommandList.clear();
                                frame.repaint();
							}
						}
					}
			  } else if(action.contentEquals("Deleted")) {
					for(int i=0;i<model.getShapes().size();i++) {
						if(model.getShapes().get(i).isSelected()) {
							RemoveShapeCmd removeShapeCmd = new RemoveShapeCmd(model,model.getShapes().get(i));
							selectedShapes.remove(model.getShapes().get(i));
							removeShapeCmd.execute();
							commandList.add(removeShapeCmd);	
							frame.repaint();
						}
					}
					redoCommandList.clear();
			  }
			  
			  //***********************************LINE*********************************//

	      } else if(split[1].contentEquals("Line")) {
			  String action =split[0];
			  Point startPoint = new Point(Integer.parseInt(split[2]),Integer.parseInt(split[3]));
			  Point endPoint = new Point (Integer.parseInt(split[7]),Integer.parseInt(split[8]));
			  Line l = new Line(startPoint,endPoint,new Color(Integer.parseInt(split[12])));
			  if(action.contentEquals("Added")){
				 // System.out.println(Integer.parseInt(split[14]));
				  addShapeCmd = new AddShapeCmd(model, l);
				  addShapeCmd.execute();
				  commandList.add(addShapeCmd);
				  redoCommandList.clear();
				  frame.repaint();
			  }else if(action.contentEquals("Selected")) {
					int index = Integer.parseInt(split[13]);
					SelectCmd selectCmd = new SelectCmd(selectedShapes,model.getShapes().get(index));
					commandList.add(selectCmd);
					selectCmd.execute();
					frame.repaint();
			  } else if(action.contentEquals("Deselected")) {
					int index = Integer.parseInt(split[13]);
					DeselectCmd deselectCmd = new DeselectCmd(selectedShapes,model.getShapes().get(index));
					commandList.add(deselectCmd);
					deselectCmd.execute();
					frame.repaint();
			 
			  } else if(action.contentEquals("Modified")){
				  for(int i=0;i<model.getShapes().size();i++) {
						if(model.getShapes().get(i) instanceof Line) {
							if(model.getShapes().get(i).isSelected()) {
								Line l1 = (Line)model.getShapes().get(i);
								//System.out.println(Integer.parseInt(split[7]));
								 Point startPointL2 = new Point(Integer.parseInt(split[2]),Integer.parseInt(split[3]));
								 Point endPointL2 = new Point (Integer.parseInt(split[7]),Integer.parseInt(split[8]));
								 Line l2 = new Line(startPointL2,endPointL2,new Color(Integer.parseInt(split[12])));
								l2.setColor(new Color(Integer.parseInt(split[12])));
								l2.setSelected(true);
								UpdateLineCmd updateLineCmd = new UpdateLineCmd(l1,l2);
								updateLineCmd.execute();
								commandList.add(updateLineCmd);
								redoCommandList.clear();
                              frame.repaint();
							}
						}
					}
			  } else if(action.contentEquals("Deleted")) {
					for(int i=0;i<model.getShapes().size();i++) {
						if(model.getShapes().get(i).isSelected()) {
							RemoveShapeCmd removeShapeCmd = new RemoveShapeCmd(model,model.getShapes().get(i));
							selectedShapes.remove(model.getShapes().get(i));
							removeShapeCmd.execute();
							commandList.add(removeShapeCmd);	
							frame.repaint();
						}
					}
					redoCommandList.clear();
			  }
			  
			  //***********************************RECTANGLE*********************************//
	      
	      } else if(split[1].contentEquals("Rectangle")) {
			
	    	  String action =split[0];
			  Point p = new Point(Integer.parseInt(split[6]),Integer.parseInt(split[7]));
			  int width = Integer.parseInt(split[11]);
			  int height = Integer.parseInt(split[14]);

			  Rectangle r = new Rectangle(p,width,height,new Color(Integer.parseInt(split[17])),new Color(Integer.parseInt(split[20])));
			  if(action.contentEquals("Added")){
				  addShapeCmd = new AddShapeCmd(model, r);
				  addShapeCmd.execute();
				  commandList.add(addShapeCmd);
				  redoCommandList.clear();
				  frame.repaint();
			  }
			  else if(action.contentEquals("Selected")) {
					int index = Integer.parseInt(split[21]);
					SelectCmd selectCmd = new SelectCmd(selectedShapes,model.getShapes().get(index));
					commandList.add(selectCmd);
					selectCmd.execute();
					frame.repaint();
			  } else if(action.contentEquals("Deselected")) {
					int index = Integer.parseInt(split[21]);
					DeselectCmd deselectCmd = new DeselectCmd(selectedShapes,model.getShapes().get(index));
					commandList.add(deselectCmd);
					deselectCmd.execute();
					frame.repaint();
			 
			  } else if(action.contentEquals("Modified")){

				  for(int i=0;i<model.getShapes().size();i++) {
						if(model.getShapes().get(i) instanceof Rectangle) {
							if(model.getShapes().get(i).isSelected()) {
								Rectangle r1 = (Rectangle)model.getShapes().get(i);
							    Point p2 = new Point(Integer.parseInt(split[6]),Integer.parseInt(split[7]));
							    int width2 = Integer.parseInt(split[11]);
							    int height2 = Integer.parseInt(split[14]);
							    Color col1 = new Color(Integer.parseInt(split[17]));
							    Color col2 = new Color(Integer.parseInt(split[20]));
								Rectangle r2 = new Rectangle(p2,width2,height2,col1,col2);								
								r2.setColor(col1);
								r2.setInnerColor(col2);
								r2.setSelected(true);
								UpdateRectangleCmd updateRectangleCmd = new UpdateRectangleCmd(r1,r2);
								updateRectangleCmd.execute();
								commandList.add(updateRectangleCmd);
								redoCommandList.clear();
                            frame.repaint();
							}
						}
					}
			  } else if(action.contentEquals("Deleted")) {
					for(int i=0;i<model.getShapes().size();i++) {
						if(model.getShapes().get(i).isSelected()) {
							RemoveShapeCmd removeShapeCmd = new RemoveShapeCmd(model,model.getShapes().get(i));
							selectedShapes.remove(model.getShapes().get(i));
							removeShapeCmd.execute();
							commandList.add(removeShapeCmd);	
							frame.repaint();
						}
					}
					redoCommandList.clear();
			  }
			 
			  //***********************************DONUT*********************************//
			  
	      } else if(split[1].contentEquals("Donut")) {

			  String action =split[0];
			  Point p = new Point(Integer.parseInt(split[4]),Integer.parseInt(split[5]));
			  int radius = Integer.parseInt(split[9]);
			  int innerRadius = Integer.parseInt(split[12]);

			  Donut d = new Donut(p,radius,innerRadius,new Color(Integer.parseInt(split[15])),new Color(Integer.parseInt(split[18])));
			  
			  if(action.contentEquals("Added")){
				  System.out.println(Integer.parseInt(split[18]));
				  addShapeCmd = new AddShapeCmd(model, d);
				  addShapeCmd.execute();
				  commandList.add(addShapeCmd);
				  redoCommandList.clear();
				  frame.repaint();
			  } else if(action.contentEquals("Selected")) {
					int index = Integer.parseInt(split[19]);
					SelectCmd selectCmd = new SelectCmd(selectedShapes,model.getShapes().get(index));
					commandList.add(selectCmd);
					selectCmd.execute();
					frame.repaint();
			  } else if(action.contentEquals("Deselected")) {
					int index = Integer.parseInt(split[19]);
					DeselectCmd deselectCmd = new DeselectCmd(selectedShapes,model.getShapes().get(index));
					commandList.add(deselectCmd);
					deselectCmd.execute();
					frame.repaint();

			  } else if(action.contentEquals("Modified")){
				  for(int i=0;i<model.getShapes().size();i++) {
						if(model.getShapes().get(i) instanceof Donut) {
							if(model.getShapes().get(i).isSelected()) {
								Donut d1 = (Donut)model.getShapes().get(i);
								Point p2= new Point(Integer.parseInt(split[4]),Integer.parseInt(split[5]));
								int radius2 = Integer.parseInt(split[9]);
						        int innerRadius2 = Integer.parseInt(split[12]);
								Donut d2 = new Donut(p2,radius2,innerRadius2,new Color(Integer.parseInt(split[15])),new Color(Integer.parseInt(split[18])));
								d2.setColor(new Color(Integer.parseInt(split[15])));
								d2.setInnerColor(new Color(Integer.parseInt(split[18])));
								d2.setSelected(true);
								UpdateDonutCmd updateDonutCmd = new UpdateDonutCmd(d1,d2);
								updateDonutCmd.execute();
								commandList.add(updateDonutCmd);
								redoCommandList.clear();
                          frame.repaint();
							}
						}
					}
			  } else if(action.contentEquals("Deleted")) {
					for(int i=0;i<model.getShapes().size();i++) {
						if(model.getShapes().get(i).isSelected()) {
							RemoveShapeCmd removeShapeCmd = new RemoveShapeCmd(model,model.getShapes().get(i));
							selectedShapes.remove(model.getShapes().get(i));
							removeShapeCmd.execute();
							commandList.add(removeShapeCmd);	
							frame.repaint();
						}
					}
					redoCommandList.clear();
			  }
	      }
		  
		  //***********************************CIRCLE*********************************//

          else if(split[1].contentEquals("Circle")) {

		  String action =split[0];
		  Point p = new Point(Integer.parseInt(split[4]),Integer.parseInt(split[5]));
		  int radius = Integer.parseInt(split[9]);
		  Circle c = new Circle(p,radius,new Color(Integer.parseInt(split[12])),new Color(Integer.parseInt(split[15])));
		  if(action.contentEquals("Added")){
			  addShapeCmd = new AddShapeCmd(model, c);
			  addShapeCmd.execute();
			  commandList.add(addShapeCmd);				
			  redoCommandList.clear();
			  frame.repaint();
		  } else if(action.contentEquals("Selected")) {
				int index = Integer.parseInt(split[16]);
				SelectCmd selectCmd = new SelectCmd(selectedShapes,model.getShapes().get(index));
				commandList.add(selectCmd);
				selectCmd.execute();
				frame.repaint();
		  } else if(action.contentEquals("Deselected")) {
				int index = Integer.parseInt(split[16]);
				DeselectCmd deselectCmd = new DeselectCmd(selectedShapes,model.getShapes().get(index));
				commandList.add(deselectCmd);
				deselectCmd.execute();
				frame.repaint();
		 
		  } else if(action.contentEquals("Modified")){
			  for(int i=0;i<model.getShapes().size();i++) {
					if(model.getShapes().get(i) instanceof Circle) {
						if(model.getShapes().get(i).isSelected()) {
							Circle c1 = (Circle)model.getShapes().get(i);
							Point p2 = new Point(Integer.parseInt(split[4]),Integer.parseInt(split[5]));
							int radius2 = Integer.parseInt(split[9]);
							Circle c2 = new Circle(p2,radius2,new Color(Integer.parseInt(split[12])),new Color(Integer.parseInt(split[15])));
							c2.setColor(new Color(Integer.parseInt(split[12])));
							c2.setInnerColor(new Color(Integer.parseInt(split[15])));
							c2.setSelected(true);
							UpdateCircleCmd updateCircleCmd = new UpdateCircleCmd(c1,c2);
							updateCircleCmd.execute();
							commandList.add(updateCircleCmd);
							redoCommandList.clear();
                    frame.repaint();
						}
					}
				}
		  } else if(action.contentEquals("Deleted")) {
				for(int i=0;i<model.getShapes().size();i++) {
					if(model.getShapes().get(i).isSelected()) {
						RemoveShapeCmd removeShapeCmd = new RemoveShapeCmd(model,model.getShapes().get(i));
						selectedShapes.remove(model.getShapes().get(i));
						removeShapeCmd.execute();
						commandList.add(removeShapeCmd);	
						frame.repaint();
					}
				}
				redoCommandList.clear();
		  }
        }
		  
		  //***********************************HEXAGON*********************************//

			  else if(split[1].contentEquals("Hexagon")) {
	
				  String action =split[0];
				  HexagonAdapter hexA = new HexagonAdapter(new Hexagon(Integer.parseInt(split[4]), Integer.parseInt(split[5]), Integer.parseInt(split[9])));
				  hexA.setColor(new Color(Integer.parseInt(split[13])));
				  hexA.setInnerColor(new Color(Integer.parseInt(split[17])));
				  if(action.contentEquals("Added")){
					  addShapeCmd = new AddShapeCmd(model, hexA);
					  addShapeCmd.execute();
					  commandList.add(addShapeCmd);	
					  redoCommandList.clear();
					  frame.repaint();
				  }else if(action.contentEquals("Selected")) {
						int index = Integer.parseInt(split[18]);
						SelectCmd selectCmd = new SelectCmd(selectedShapes,model.getShapes().get(index));
						commandList.add(selectCmd);
						selectCmd.execute();
						frame.repaint();
				  } else if(action.contentEquals("Deselected")) {
						int index = Integer.parseInt(split[18]);
						DeselectCmd deselectCmd = new DeselectCmd(selectedShapes,model.getShapes().get(index));
						commandList.add(deselectCmd);
						deselectCmd.execute();
						frame.repaint();
				  } else if(action.contentEquals("Modified")){
					  for(int i=0;i<model.getShapes().size();i++) {
							if(model.getShapes().get(i) instanceof HexagonAdapter) {
								if(model.getShapes().get(i).isSelected()) {
									HexagonAdapter h1 = (HexagonAdapter)model.getShapes().get(i);
									HexagonAdapter h2 = new HexagonAdapter(new Hexagon(Integer.parseInt(split[4]), Integer.parseInt(split[5]), Integer.parseInt(split[9])));
								    h2.setColor(new Color(Integer.parseInt(split[13])));
								    h2.setInnerColor(new Color(Integer.parseInt(split[17])));	
									UpdateHexagonCmd updateHexagonCmd = new UpdateHexagonCmd(h1,h2);
									updateHexagonCmd.execute();
									commandList.add(updateHexagonCmd);
									redoCommandList.clear();
	                            frame.repaint();
								}
							}
						}
				  } else if(action.contentEquals("Deleted")) {
						for(int i=0;i<model.getShapes().size();i++) {
							if(model.getShapes().get(i).isSelected()) {
								RemoveShapeCmd removeShapeCmd = new RemoveShapeCmd(model,model.getShapes().get(i));
								selectedShapes.remove(model.getShapes().get(i));
								removeShapeCmd.execute();
								commandList.add(removeShapeCmd);	
								frame.repaint();
							}
						}
						redoCommandList.clear();
				  }
	      }
		  //***********************************UNDO & REDO*********************************//
		  
			  else if(split[0].contentEquals("Undo:")){
				//  undo();
					commandList.get(commandList.size()-1).unexecute();
					redoCommandList.add(commandList.get(commandList.size()-1));
					commandList.remove(commandList.size()-1);
					frame.repaint();
			  }
			  else if(split[0].contentEquals("Redo:")){
				 // redo();
					commandList.add(redoCommandList.get(redoCommandList.size()-1));
					redoCommandList.remove(redoCommandList.get(redoCommandList.size()-1));
					commandList.get(commandList.size()-1).execute();
				    frame.repaint();
			  }
		  
		  //***********************************FRONT & BACK*********************************//

			  else if(split[1].contentEquals("Front")){
				 // System.out.println(Integer.parseInt(split[1]));
				  ToFrontCmd toFrontCmd = new ToFrontCmd(model, selectedShapes.get(selectedShapes.size()-1));
				  commandList.add(toFrontCmd);
			      toFrontCmd.execute();
			      frame.repaint();
			  }
			  else if(split[1].contentEquals("Back")){
				  ToBackCmd toBackCmd = new ToBackCmd(model, selectedShapes.get(selectedShapes.size()-1));
				  commandList.add(toBackCmd);
				  toBackCmd.execute();
				  frame.repaint();
			  }	
			  else if(split[2].contentEquals("Front")){
				BringToFrontCmd bringToFrontCmd = new BringToFrontCmd(model, selectedShapes.get(selectedShapes.size()-1));
					commandList.add(bringToFrontCmd);
					bringToFrontCmd.execute();
				      frame.repaint();

				 // bringToFront();
			  }
			  else if(split[2].contentEquals("Back")){
				 BringToBackCmd bringToBackCmd = new BringToBackCmd(model, selectedShapes.get(selectedShapes.size()-1));
				  commandList.add(bringToBackCmd);
				  bringToBackCmd.execute();
			      frame.repaint();

				 // bringToBack();
			  }
		  
		  executeIndex++;
          }
	  }
	
	

	//**********************************OBSERVER**************************************
    
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		pcs.addPropertyChangeListener(pcl);
	}
	
	public void removePropertyChange(PropertyChangeListener pcl) {
		pcs.removePropertyChangeListener(pcl);
	}
	
	public void observer() {
		if(selectedShapes.size()==0) {
			pcs.firePropertyChange("Delete disabled", selectedShapes == null, selectedShapes != null);
			pcs.firePropertyChange("Modify disabled", selectedShapes == null, selectedShapes != null);
		}
		else if(selectedShapes.size()==1) {
			pcs.firePropertyChange("Modify enable", false, selectedShapes != null && selectedShapes.size() == 1);
			pcs.firePropertyChange("Delete enable", false, selectedShapes != null && selectedShapes.size() == 1);
		}
		else if(selectedShapes.size()>1){
			pcs.firePropertyChange("Delete enable", false, selectedShapes != null && selectedShapes.size() == 1);
			pcs.firePropertyChange("Modify disabled", selectedShapes == null, selectedShapes != null);
			
		}
	}
	
	//*************************************************************************************************//

	public boolean isSelDelete() {
		return selDelete;
	}

	public void setSelDelete(boolean selDelete) {
		this.selDelete = selDelete;
	}

	public DefaultListModel<String> getLogModel() {
		return logModel;
	}

	public void setLogModel(DefaultListModel<String> logModel) {
		this.logModel = logModel;
	}

};


