package strategy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import geometry.Shape;
import mvc.DrawingFrame;
import mvc.DrawingModel;

public class DrawingSaving implements Serializable,Strategy {

	private DrawingFrame frame;
	private DrawingModel model;
	private List<Shape> shapes;
	
	
	public DrawingSaving(List<Shape> shapes) {
		super();
		this.shapes = shapes;
	}

	public DrawingSaving(DrawingModel model,DrawingFrame frame) {
		super();
		this.frame = frame;
		this.model = model;
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		JFileChooser jFileChooser=new JFileChooser("C:\\Users\\PC\\Desktop\\Dizajnerski-projekat\\drawings");
		jFileChooser.setFileFilter(new FileNameExtensionFilter("ser file (.ser)", "ser"));
		
		if(jFileChooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION) {
			File fileToSave = new File(jFileChooser.getSelectedFile().getAbsolutePath()+ ".ser");
			if (fileToSave.exists()) {
				JOptionPane.showMessageDialog(null, "File with that name already exists!", "Error", JOptionPane.ERROR_MESSAGE);
			}	
			else {
			 try {
				    FileOutputStream file = new FileOutputStream(fileToSave.getAbsolutePath());
					ObjectOutputStream fileOut = new ObjectOutputStream(file);
					fileOut.writeObject(shapes);
					fileOut.close();
					file.close();
					
					JOptionPane.showMessageDialog(null, "Successfully Saved!", "Saving Done!",
							JOptionPane.INFORMATION_MESSAGE);
					
		
				}
			catch (FileNotFoundException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error while saving the file!)", "Error!",JOptionPane.ERROR_MESSAGE);
			}
			catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
		}
	}


	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		JFileChooser jFileChooser = new JFileChooser("C:\\Users\\PC\\Desktop\\Dizajnerski-projekat\\drawings");
		if(jFileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
			File fileToOpen = new File(jFileChooser.getSelectedFile().getAbsolutePath());
				try {
					FileInputStream file = new FileInputStream(fileToOpen);
					ObjectInputStream fileIn = new ObjectInputStream(file);
					model.getShapes().clear();
					frame.getLogModel().clear();
					@SuppressWarnings("unchecked")
					List<Shape> shapeList = (List<Shape>)fileIn.readObject();

					for (Shape s : shapeList) {
						model.shapesAdd(s);
						if(s.isSelected()) {
							s.setSelected(true);
						}
					}
					fileIn.close();
					file.close();
	                JOptionPane.showMessageDialog(null, "Drawing loaded succesifuly", "Succesful!",JOptionPane.INFORMATION_MESSAGE);
                    frame.repaint();
            
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error while saving the file. Please contact a developer :)", "Error!",JOptionPane.ERROR_MESSAGE);
				}
				catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
				catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
			}
		
  }

}
