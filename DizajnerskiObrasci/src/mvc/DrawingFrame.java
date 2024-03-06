package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import drawing.PnlDrawing;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JList;

public class DrawingFrame extends JFrame implements PropertyChangeListener {
	
	private DrawingView view = new DrawingView();
	private DrawingController controller;
	
	private JPanel contentPane;
	private JPanel panel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final JPanel contentPanel = new JPanel();

	private JToggleButton tglbtnPoint;
	private JToggleButton tglbtnLine;
	private JToggleButton tglbtnRectangle;
	private JToggleButton tglbtnCircle;
	private JToggleButton tglbtnDonut;
	private JToggleButton tglbtnSelect;
	private JToggleButton tglbtnModify;
	private JToggleButton tglbtnDelete;
	private JToggleButton tglbtnHexagon;
	private JButton btnInnerCol;
	private JButton btnBorderCol;
	private JButton btnUndo;
	private JButton btnRedo;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();
	private JPanel panel_1;
	private JButton btnToBack;
	private JButton btnToFront;
	private JButton btnBringToFront;
	private JButton btnBringToBack;
	private JButton btnSave;
	private JButton btnLoad;
	private JButton btnExecute;
	private JPanel panel_2;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JList<String> list;
	public DefaultListModel<String> logModel = new DefaultListModel<String>();
	private JScrollPane scrollPanel;
	private JList<String> logList;
	private JButton btnSaveDrawing;
	private JButton btnLoadDrawing;
	/*Color colorInner;
	Color colorBorder;
	boolean colorIsOkay;
	*/
	
	
	public DrawingFrame() {
			setTitle("Tunic Jovana IT3-2020");
			PnlDrawing pnlCenter = new PnlDrawing();
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(1, 0, 1000, 800);
			//setPreferredSize(new Dimension (1500,700));
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			setContentPane(contentPane);

			JPanel pnlNorth = new JPanel();
			contentPane.add(pnlNorth, BorderLayout.NORTH);
			pnlNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			tglbtnHexagon = new JToggleButton("Hexagon");
			buttonGroup.add(tglbtnHexagon);
			tglbtnHexagon.setBackground(new Color(240, 230, 140));
			pnlNorth.add(tglbtnHexagon);

			tglbtnPoint = new JToggleButton("Point");
			tglbtnPoint.setBackground(new Color(240, 230, 140));
			buttonGroup.add(tglbtnPoint);
			pnlNorth.add(tglbtnPoint);

			tglbtnLine = new JToggleButton("Line");
			tglbtnLine.setBackground(new Color(240, 230, 140));
			buttonGroup.add(tglbtnLine);
			pnlNorth.add(tglbtnLine);

		    tglbtnRectangle = new JToggleButton("Rectangle");
			tglbtnRectangle.setBackground(new Color(240, 230, 140));
			buttonGroup.add(tglbtnRectangle);
			pnlNorth.add(tglbtnRectangle);

			tglbtnCircle = new JToggleButton("Circle");
			tglbtnCircle.setBackground(new Color(240, 230, 140));
			buttonGroup.add(tglbtnCircle);
			pnlNorth.add(tglbtnCircle);

			tglbtnDonut = new JToggleButton("Donut");
			tglbtnDonut.setBackground(new Color(240, 230, 140));
			buttonGroup.add(tglbtnDonut);
			pnlNorth.add(tglbtnDonut);

			JPanel pnlSouth = new JPanel();
			contentPane.add(pnlSouth, BorderLayout.SOUTH);
			
			tglbtnSelect = new JToggleButton("Select");
			tglbtnSelect.setBackground(new Color(240, 230, 140));
			buttonGroup.add(tglbtnSelect);
			pnlSouth.add(tglbtnSelect);

			tglbtnModify = new JToggleButton("Modify");
			tglbtnModify.setBackground(new Color(240, 230, 140));
			tglbtnModify.setEnabled(false);
			tglbtnModify.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.modify();
				}
			});
			buttonGroup.add(tglbtnModify);
			pnlSouth.add(tglbtnModify);

			tglbtnDelete = new JToggleButton("Delete");
			tglbtnDelete.setBackground(new Color(240, 230, 140));
			tglbtnDelete.setEnabled(false);
			tglbtnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.delete();
				}
			});
			buttonGroup.add(tglbtnDelete);
			pnlSouth.add(tglbtnDelete);

			// PnlDrawing pnlCenter=new PnlDrawing();
			contentPane.setBackground(Color.white);
			//view.setBackground(Color.white);
			view.addMouseListener(new MouseAdapter() {
				public void mouseClicked (MouseEvent e) {
					controller.mouseClicked(e);
				}
			});
			
			contentPane.add(view, BorderLayout.CENTER);
			GridBagLayout gbl_pnlCenter = new GridBagLayout();
			gbl_pnlCenter.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			gbl_pnlCenter.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			gbl_pnlCenter.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
			gbl_pnlCenter.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0 };
			view.setLayout(gbl_pnlCenter);
			
			pnlNorth.setBackground(Color.orange);
			pnlSouth.setBackground(Color.ORANGE);
			pnlSouth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			

			
			btnInnerCol = new JButton("Inner Color");
			btnInnerCol.setBackground(new Color(255, 255, 255));
			btnInnerCol.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.innerColor();
					//prebaceno u controller
					/*if (e.getSource()==btnInnerCol) {
						colorInner = JColorChooser.showDialog(null, "Choose inner color:", colorInner);
						btnInnerCol.setBackground(colorInner);
					}*/
				}
			
			});
			//btnInnerCol.setBackground(Color.WHITE);
			GridBagConstraints gbc_btnInnerCol = new GridBagConstraints();
			gbc_btnInnerCol.insets = new Insets(0, 0, 5, 5);
			gbc_btnInnerCol.gridx = 1;
			gbc_btnInnerCol.gridy = 5;
			pnlSouth.add(btnInnerCol);
			
			
			btnBorderCol = new JButton("Border Color");
			btnBorderCol.setBackground(new Color(0,0,0));
			btnBorderCol.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				  controller.borderColor();
					/*if (e.getSource()==btnBorderCol) {
						colorBorder = JColorChooser.showDialog(null, "Choose border color:", colorBorder);
						btnBorderCol.setBackground(colorBorder);
					}*/
				}
			});
			//btnBorderCol.setBackground(Color.BLACK);
			GridBagConstraints gbc_btnBorderCol = new GridBagConstraints();
			gbc_btnBorderCol.insets = new Insets(0, 0, 5, 5);
			gbc_btnBorderCol.gridx = 1;
			gbc_btnBorderCol.gridy = 5;
			pnlSouth.add(btnBorderCol);
			
			btnUndo = new JButton("Undo");
			pnlSouth.add(btnUndo);
		//	btnUndo.setEnabled(false);
			btnUndo.setBackground(new Color(240, 230, 140));
			btnUndo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					controller.undo();
				}
			});
			
			btnRedo = new JButton("Redo");
			btnRedo.setBackground(new Color(240, 230, 140));
			pnlSouth.add(btnRedo);
			btnRedo.addActionListener(new ActionListener() {	
					@Override
					public void actionPerformed(ActionEvent e) {
						controller.redo();
					}
				});
			
			btnSaveDrawing = new JButton("Save Drawing");
			pnlSouth.add(btnSaveDrawing);
			btnSaveDrawing.setBackground(new Color(240, 230, 140));
			btnSaveDrawing.addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
					controller.saveDrawing();
				}
			});
			
			btnLoadDrawing = new JButton("Load Drawing");
			btnLoadDrawing.setBackground(new Color(240, 230, 140));
			pnlSouth.add(btnLoadDrawing);
			btnLoadDrawing.addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
					controller.loadDrawing();
				}
			});
           
			
			panel_1 = new JPanel();
			panel_1.setBackground(Color.ORANGE);
			contentPane.add(panel_1, BorderLayout.EAST);
			GridBagLayout gbl_panel_1 = new GridBagLayout();
			gbl_panel_1.columnWidths = new int[]{89, 0};
			gbl_panel_1.rowHeights = new int[]{23, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			panel_1.setLayout(gbl_panel_1);
			
			btnToFront = new JButton("To Front");
			GridBagConstraints gbc_btnToFront = new GridBagConstraints();
			btnToFront.setBackground(new Color(240, 230, 140));
			gbc_btnToFront.insets = new Insets(0, 0, 5, 0);
			gbc_btnToFront.gridx = 0;
			gbc_btnToFront.gridy = 6;
			panel_1.add(btnToFront, gbc_btnToFront);
			
			btnToFront.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
                     controller.toFront();					
				}
			});
			
			
			btnToBack = new JButton("To Back");
			GridBagConstraints gbc_btnToBack = new GridBagConstraints();
			btnToBack.setBackground(new Color(240, 230, 140));
			gbc_btnToBack.insets = new Insets(0, 0, 5, 0);
			gbc_btnToBack.anchor = GridBagConstraints.NORTH;
			gbc_btnToBack.gridx = 0;
			gbc_btnToBack.gridy = 7;
			panel_1.add(btnToBack, gbc_btnToBack);
			
			btnToBack.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
                     controller.toBack();					
				}
			});
			
			btnBringToFront = new JButton("Bring To Front");
			GridBagConstraints gbc_btnBringToFront = new GridBagConstraints();
			btnBringToFront.setBackground(new Color(240, 230, 140));
			gbc_btnBringToFront.insets = new Insets(0, 0, 5, 0);
			gbc_btnBringToFront.gridx = 0;
			gbc_btnBringToFront.gridy = 8;
			panel_1.add(btnBringToFront, gbc_btnBringToFront);
			
			btnBringToFront.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
                     controller.bringToFront();					
				}
			});
			
			
			btnBringToBack = new JButton("Bring To Back");
			GridBagConstraints gbc_btnBringToBack = new GridBagConstraints();
			gbc_btnBringToBack.insets = new Insets(0, 0, 5, 0);
			btnBringToBack.setBackground(new Color(240, 230, 140));
			gbc_btnBringToBack.gridx = 0;
			gbc_btnBringToBack.gridy = 9;
			panel_1.add(btnBringToBack, gbc_btnBringToBack);
			
			btnBringToBack.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
                     controller.bringToBack();					
				}
			});
			
			btnSave = new JButton("Save");
			btnSave.setBackground(new Color(240, 230, 140));
			GridBagConstraints gbc_btnSave = new GridBagConstraints();
			gbc_btnSave.insets = new Insets(0, 0, 5, 0);
			gbc_btnSave.gridx = 0;
			gbc_btnSave.gridy = 17;
			panel_1.add(btnSave, gbc_btnSave);
			
			btnSave.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
                     controller.save();					
				}
			});
			
			btnLoad = new JButton("Load");
			btnLoad.setBackground(new Color(240, 230, 140));
			GridBagConstraints gbc_btnLoad = new GridBagConstraints();
			gbc_btnLoad.insets = new Insets(0, 0, 5, 0);
			gbc_btnLoad.gridx = 0;
			gbc_btnLoad.gridy = 18;
			panel_1.add(btnLoad, gbc_btnLoad);
			
			btnLoad.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
                     controller.load();					
				}
			});
			
			btnExecute = new JButton("Load Next");
			btnExecute.setBackground(new Color(240, 230, 140));
			GridBagConstraints gbc_btnExecute = new GridBagConstraints();
			gbc_btnExecute.insets = new Insets(0, 0, 5, 0);
			gbc_btnExecute.gridx = 0;
			gbc_btnExecute.gridy = 19;
			panel_1.add(btnExecute, gbc_btnExecute);
			
			btnExecute.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
                     controller.loadNext();					
				}
			});
			
			scrollPanel = new JScrollPane();
			GridBagConstraints gbc_scrollPanel = new GridBagConstraints();
			gbc_scrollPanel.fill = GridBagConstraints.BOTH;
			gbc_scrollPanel.gridx = 0;
			gbc_scrollPanel.gridy = 20;
			panel_1.add(scrollPanel, gbc_scrollPanel);
			//panel_1.add(scrollPanel);
			
		
			logList = new JList<String>();
			//logList.setBounds(100,1000,300,50);
			scrollPanel.setViewportView(logList);
			logList.setModel(logModel);
			
			
			
	}
	
	//*******************************************************************************************//

	public void propertyChange(PropertyChangeEvent p) {
		if(p.getPropertyName()=="Delete enable") {
			tglbtnDelete.setEnabled(true);
		}
		else if(p.getPropertyName()=="Delete disabled") {
			tglbtnDelete.setEnabled(false);
		}
		else if(p.getPropertyName()=="Modify enable") {
			tglbtnModify.setEnabled(true);
		}
		else if(p.getPropertyName()=="Modify disabled") {
			tglbtnModify.setEnabled(false);
		}

     }

	//***********************************************************************************************//
	
	public JList<String> getLogList() {
		return logList;
	}

	public void setLogList(JList<String> logList) {
		this.logList = logList;
	}
	
	public JButton getBtnInnerCol() {
		return btnInnerCol;
	}

	public void setBtnInnerCol(JButton btnInnerCol) {
		this.btnInnerCol = btnInnerCol;
	}

	public JButton getBtnBorderCol() {
		return btnBorderCol;
	}

	public void setBtnBorderCol(JButton btnBorderCol) {
		this.btnBorderCol = btnBorderCol;
	}

	public DrawingView getView() {
		return view;
	}

	public void setDrawingController(DrawingController drawingController) {
		this.controller = drawingController;
	}

	public JToggleButton getTglbtnPoint() {
		return tglbtnPoint;
	}

	public void setTglbtnPoint(JToggleButton tglbtnPoint) {
		this.tglbtnPoint = tglbtnPoint;
	}

	public JToggleButton getTglbtnLine() {
		return tglbtnLine;
	}

	public void setTglbtnLine(JToggleButton tglbtnLine) {
		this.tglbtnLine = tglbtnLine;
	}

	public JToggleButton getTglbtnRectangle() {
		return tglbtnRectangle;
	}

	public void setTglbtnRectangle(JToggleButton tglbtnRectangle) {
		this.tglbtnRectangle = tglbtnRectangle;
	}

	public JToggleButton getTglbtnCircle() {
		return tglbtnCircle;
	}

	public void setTglbtnCircle(JToggleButton tglbtnCircle) {
		this.tglbtnCircle = tglbtnCircle;
	}

	public JToggleButton getTglbtnDonut() {
		return tglbtnDonut;
	}

	public void setTglbtnDonut(JToggleButton tglbtnDonut) {
		this.tglbtnDonut = tglbtnDonut;
	}

	public JToggleButton getTglbtnSelect() {
		return tglbtnSelect;
	}

	public void setTglbtnSelect(JToggleButton tglbtnSelect) {
		this.tglbtnSelect = tglbtnSelect;
	}

	public JToggleButton getTglbtnModify() {
		return tglbtnModify;
	}

	public void setTglbtnModify(JToggleButton tglbtnModify) {
		this.tglbtnModify = tglbtnModify;
	}

	public JToggleButton getTglbtnDelete() {
		return tglbtnDelete;
	}

	public void setTglbtnDelete(JToggleButton tglbtnDelete) {
		this.tglbtnDelete = tglbtnDelete;
	}

	public JToggleButton getTglbtnHexagon() {
		return tglbtnHexagon;
	}

	public void setTglbtnHexagon(JToggleButton tglbtnHexagon) {
		this.tglbtnHexagon = tglbtnHexagon;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

    public DefaultListModel<String> getLogModel() {
		return logModel;
	}

	public void setLogModel(DefaultListModel<String> logModel) {
		this.logModel = logModel;
	}


	public JButton getBtnUndo() {
		return btnUndo;
	}


	public void setBtnUndo(JButton btnUndo) {
		this.btnUndo = btnUndo;
	}


	public JButton getBtnRedo() {
		return btnRedo;
	}


	public void setBtnRedo(JButton btnRedo) {
		this.btnRedo = btnRedo;
	}


	public JButton getBtnToBack() {
		return btnToBack;
	}


	public void setBtnToBack(JButton btnToBack) {
		this.btnToBack = btnToBack;
	}


	public JButton getBtnToFront() {
		return btnToFront;
	}


	public void setBtnToFront(JButton btnToFront) {
		this.btnToFront = btnToFront;
	}


	public JButton getBtnBringToFront() {
		return btnBringToFront;
	}


	public void setBtnBringToFront(JButton btnBringToFront) {
		this.btnBringToFront = btnBringToFront;
	}


	public JButton getBtnBringToBack() {
		return btnBringToBack;
	}


	public void setBtnBringToBack(JButton btnBringToBack) {
		this.btnBringToBack = btnBringToBack;
	}


	public JButton getBtnSave() {
		return btnSave;
	}


	public void setBtnSave(JButton btnSave) {
		this.btnSave = btnSave;
	}


	public JButton getBtnLoad() {
		return btnLoad;
	}


	public void setBtnLoad(JButton btnLoad) {
		this.btnLoad = btnLoad;
	}


	public JButton getBtnExecute() {
		return btnExecute;
	}


	public void setBtnExecute(JButton btnExecute) {
		this.btnExecute = btnExecute;
	}


	public JButton getBtnSaveDrawing() {
		return btnSaveDrawing;
	}


	public void setBtnSaveDrawing(JButton btnSaveDrawing) {
		this.btnSaveDrawing = btnSaveDrawing;
	}


	public JButton getBtnLoadDrawing() {
		return btnLoadDrawing;
	}


	public void setBtnLoadDrawing(JButton btnLoadDrawing) {
		this.btnLoadDrawing = btnLoadDrawing;
	}


	

	
}

