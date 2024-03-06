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
import java.io.PrintWriter;
import java.io.Serializable;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import mvc.DrawingFrame;
import mvc.DrawingModel;

public class LogSaving implements Strategy,Serializable{
    
	private DefaultListModel<String> logModel;
	private DrawingFrame frame;
	private BufferedReader bufferReader;
	private DrawingModel model;
	
	public LogSaving(DefaultListModel<String> dlm)
	{
		this.logModel=dlm;
	}
	
	public LogSaving(DrawingModel model, DrawingFrame frame) {
		this.model=model;
		this.frame=frame;
	}
	
	@Override
	public void save() {
		// TODO Auto-generated method stub
		JFileChooser jFileChooser=new JFileChooser("C:\\Users\\PC\\Desktop\\Dizajnerski-projekat\\logs");
		jFileChooser.setFileFilter(new FileNameExtensionFilter("log file (.log)", "log"));
		
		if(jFileChooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION) {
			File fileToSave = new File(jFileChooser.getSelectedFile().getAbsolutePath()+ ".log");
			if (fileToSave.exists()) {
				JOptionPane.showMessageDialog(null, "File with that name already exists!", "Error", JOptionPane.ERROR_MESSAGE);
			}	
				try {
					FileOutputStream file = new FileOutputStream(fileToSave.getAbsolutePath());
					PrintWriter fileOut = new PrintWriter(file);
					for (int i=0; i<logModel.size();i++) {
						System.out.println(logModel.get(i));
						fileOut.println(logModel.get(i));
						//fileOut.writeObject(logModel.get(i)); 
					}
					fileOut.close();
					file.close();
					
					JOptionPane.showMessageDialog(null, "Successfully Saved!", "Saving Done!",
							JOptionPane.INFORMATION_MESSAGE);
		
				}catch (FileNotFoundException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error while saving the file!)", "Error!",JOptionPane.ERROR_MESSAGE);
				}
				catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
			}
		}


	@Override
	public void load() {
		// TODO Auto-generated method stub
		JFileChooser jFileChooser = new JFileChooser("C:\\Users\\PC\\Desktop\\Dizajnerski-projekat\\logs");
		if(jFileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
			File fileToOpen = new File(jFileChooser.getSelectedFile().getAbsolutePath());
				try {
					bufferReader = new BufferedReader(new FileReader(fileToOpen));
					frame.getLogModel().clear();
					String line;
					try {
						while ((line=bufferReader.readLine())!=null) {
							System.out.println(line);
							frame.getLogModel().addElement(line);
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
		}
		
	}
   }
}
