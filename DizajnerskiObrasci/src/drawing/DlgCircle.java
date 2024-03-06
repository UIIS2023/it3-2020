package drawing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class DlgCircle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	protected JTextField txtCordinateX;
	protected JTextField txtCordinateY;
	protected JTextField txtRadius;
	private JButton btnInnerCol;
	private JButton btnBorderCol;
	protected boolean isOk;
	protected boolean colorOkay;
	protected boolean borderOkay;
	Color colorC;
	Color borderC;


	public static void main(String[] args) {
		try {
			DlgCircle dialog = new DlgCircle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DlgCircle() {
		setTitle("Add/Modify");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblCordinateX = new JLabel("Cordinate X:");
			GridBagConstraints gbc_lblCordinateX = new GridBagConstraints();
			gbc_lblCordinateX.insets = new Insets(0, 0, 5, 5);
			gbc_lblCordinateX.gridx = 1;
			gbc_lblCordinateX.gridy = 1;
			contentPanel.add(lblCordinateX, gbc_lblCordinateX);
		}
		{
			txtCordinateX = new JTextField();
			GridBagConstraints gbc_txtCordinateX = new GridBagConstraints();
			gbc_txtCordinateX.insets = new Insets(0, 0, 5, 0);
			gbc_txtCordinateX.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtCordinateX.gridx = 3;
			gbc_txtCordinateX.gridy = 1;
			contentPanel.add(txtCordinateX, gbc_txtCordinateX);
			txtCordinateX.setColumns(10);
		}
		{
			JLabel lblCordinateY = new JLabel("Cordinate Y:");
			GridBagConstraints gbc_lblCordinateY = new GridBagConstraints();
			gbc_lblCordinateY.insets = new Insets(0, 0, 5, 5);
			gbc_lblCordinateY.gridx = 1;
			gbc_lblCordinateY.gridy = 2;
			contentPanel.add(lblCordinateY, gbc_lblCordinateY);
		}
		{
			txtCordinateY = new JTextField();
			GridBagConstraints gbc_txtCordinateY = new GridBagConstraints();
			gbc_txtCordinateY.insets = new Insets(0, 0, 5, 0);
			gbc_txtCordinateY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtCordinateY.gridx = 3;
			gbc_txtCordinateY.gridy = 2;
			contentPanel.add(txtCordinateY, gbc_txtCordinateY);
			txtCordinateY.setColumns(10);
		}
		{
			JLabel lblRadius = new JLabel("Radius:");
			GridBagConstraints gbc_lblRadius = new GridBagConstraints();
			gbc_lblRadius.insets = new Insets(0, 0, 5, 5);
			gbc_lblRadius.gridx = 1;
			gbc_lblRadius.gridy = 3;
			contentPanel.add(lblRadius, gbc_lblRadius);
		}
		{
			txtRadius = new JTextField();
			GridBagConstraints gbc_txtRadius = new GridBagConstraints();
			gbc_txtRadius.insets = new Insets(0, 0, 5, 0);
			gbc_txtRadius.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtRadius.gridx = 3;
			gbc_txtRadius.gridy = 3;
			contentPanel.add(txtRadius, gbc_txtRadius);
			txtRadius.setColumns(10);
		}
		{
			btnInnerCol = new JButton("Inner color:");
			btnInnerCol.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (e.getSource() == btnInnerCol) {
						colorC = JColorChooser.showDialog(null, "Choose inner color:", colorC);
						if(colorC!=null) {
						btnInnerCol.setBackground(colorC);
						colorOkay = true;
						}
					}
				}
			});
			GridBagConstraints gbc_btnColor1 = new GridBagConstraints();
			gbc_btnColor1.insets = new Insets(0, 0, 5, 5);
			gbc_btnColor1.gridx = 1;
			gbc_btnColor1.gridy = 5;
			contentPanel.add(btnInnerCol, gbc_btnColor1);
		}
		{
			btnBorderCol = new JButton("Border color:");
			btnBorderCol.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					borderC = JColorChooser.showDialog(null, "Choose border color:", borderC);
					if(borderC!=null) {
					btnBorderCol.setBackground(borderC);
					borderOkay = true;
					}
				}
			});
			GridBagConstraints gbc_btnColor2 = new GridBagConstraints();
			gbc_btnColor2.insets = new Insets(0, 0, 0, 5);
			gbc_btnColor2.gridx = 1;
			gbc_btnColor2.gridy = 6;
			contentPanel.add(btnBorderCol, gbc_btnColor2);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// isOk=true;
						// dispose(); // da se izadje iz prozora
						try {
							if (txtCordinateX.getText().trim().isEmpty() || txtCordinateY.getText().trim().isEmpty()
									|| txtRadius.getText().trim().isEmpty()) {
								JOptionPane.showMessageDialog(null, "You didn't enter any numbers");
							} 
							else if(Integer.parseInt(txtRadius.getText()) < 0) {
									JOptionPane.showMessageDialog(null, "Radius can not be negative number");
								}
							else {
								isOk = true;
								setVisible(false);
							}
						} catch (Exception numberFormatException) {
							JOptionPane.showMessageDialog(null, "Enter numbers!!");
						}
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			contentPanel.setBackground(Color.ORANGE);
		}
	}

	
	//****************************************GET & SET*****************************************
	
	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}

	public JTextField getTxtCordinateX() {
		return txtCordinateX;
	}

	public void setTxtCordinateX(JTextField txtCordinateX) {
		this.txtCordinateX = txtCordinateX;
	}

	public JTextField getTxtCordinateY() {
		return txtCordinateY;
	}

	public void setTxtCordinateY(JTextField txtCordinateY) {
		this.txtCordinateY = txtCordinateY;
	}

	public JTextField getTxtRadius() {
		return txtRadius;
	}

	public void setTxtRadius(JTextField txtRadius) {
		this.txtRadius = txtRadius;
	}

	public boolean isColorOkay() {
		return colorOkay;
	}

	public void setColorOkay(boolean colorOkay) {
		this.colorOkay = colorOkay;
	}

	public boolean isBorderOkay() {
		return borderOkay;
	}

	public void setBorderOkay(boolean borderOkay) {
		this.borderOkay = borderOkay;
	}

	public Color getColorC() {
		return colorC;
	}

	public void setColorC(Color colorC) {
		this.colorC = colorC;
	}

	public Color getBorderC() {
		return borderC;
	}

	public void setBorderC(Color borderC) {
		this.borderC = borderC;
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

	
	
}
