package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

public class DlgDonut extends JDialog {

	private final JPanel contentPanel = new JPanel();
	protected JTextField txtCordinateX;
	protected JTextField txtCordinateY;
	protected JTextField txtRadius;
	protected JTextField txtInnerRadius;
	protected boolean isOk1;
	private boolean colorOkay;
	private boolean borderOkay;
	Color color;
	Color border;
	private JButton btnInnerColor;
	private JButton btnBorderColor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgDonut dialog = new DlgDonut();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgDonut() {
		setBounds(100, 100, 450, 300);
		setModal(true);
		setTitle("Add/Modify");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
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
			JLabel lblInnerRadius = new JLabel("Inner Radius:");
			GridBagConstraints gbc_lblInnerRadius = new GridBagConstraints();
			gbc_lblInnerRadius.insets = new Insets(0, 0, 5, 5);
			gbc_lblInnerRadius.gridx = 1;
			gbc_lblInnerRadius.gridy = 4;
			contentPanel.add(lblInnerRadius, gbc_lblInnerRadius);
		}
		{
			txtInnerRadius = new JTextField();
			GridBagConstraints gbc_txtInnerRadius = new GridBagConstraints();
			gbc_txtInnerRadius.insets = new Insets(0, 0, 5, 0);
			gbc_txtInnerRadius.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtInnerRadius.gridx = 3;
			gbc_txtInnerRadius.gridy = 4;
			contentPanel.add(txtInnerRadius, gbc_txtInnerRadius);
			txtInnerRadius.setColumns(10);
		}
		{
		    btnInnerColor = new JButton("Inner color:");
		    btnInnerColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					color = JColorChooser.showDialog(null, "Choose inner color:", color);
				    if(color!=null) {	
					btnInnerColor.setBackground(color);
					colorOkay = true;
				    }
				}
			});
			GridBagConstraints gbc_btnColor1 = new GridBagConstraints();
			gbc_btnColor1.insets = new Insets(0, 0, 5, 5);
			gbc_btnColor1.gridx = 1;
			gbc_btnColor1.gridy = 6;
			contentPanel.add(btnInnerColor, gbc_btnColor1);
		}
		{
			btnBorderColor = new JButton("Border color:");
			btnBorderColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					border = JColorChooser.showDialog(null, "Choose border color:", border);
					if(border!=null) {
					btnBorderColor.setBackground(border);
					borderOkay = true;
					}
				}
			});
			GridBagConstraints gbc_btnColor2 = new GridBagConstraints();
			gbc_btnColor2.insets = new Insets(0, 0, 0, 5);
			gbc_btnColor2.gridx = 1;
			gbc_btnColor2.gridy = 7;
			contentPanel.add(btnBorderColor, gbc_btnColor2);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// isOk1=true;
						// dispose();
						try {
							if (txtCordinateX.getText().trim().isEmpty() || txtCordinateY.getText().trim().isEmpty()
									|| txtRadius.getText().trim().isEmpty()
									|| txtInnerRadius.getText().trim().isEmpty()) {
								JOptionPane.showMessageDialog(null, "You didn't enter any numbers");
							} 
							else if (Integer.parseInt(txtRadius.getText()) < 0
									|| Integer.parseInt(txtInnerRadius.getText()) < 0) {
								JOptionPane.showMessageDialog(null,
										"Radius and innerradius can not be negative numbers");
							}
							else if (Integer.parseInt(txtRadius.getText()) < Integer.parseInt(txtInnerRadius.getText())) {
								JOptionPane.showMessageDialog(null, "Radius must be greather than inner radius");
							}
							else {
								isOk1 = true;
								setVisible(false);
							}
							
						} catch (Exception numberFormatException) {
							JOptionPane.showMessageDialog(null, "Enter numbers!!");
						}
						//pokusaj//
						/*try {
							if (Integer.parseInt(txtRadius.getText()) < 0
									|| Integer.parseInt(txtInnerRadius.getText()) < 0) {
								JOptionPane.showMessageDialog(null,
										"Radius and innerradius can not be negative numbers");
							}
							else if (Integer.parseInt(txtRadius.getText()) < Integer.parseInt(txtInnerRadius.getText())) {
								JOptionPane.showMessageDialog(null, "Radius must be greather than inner radius");
							}
							else {
								isOk1 = true;
								setVisible(false);
							}

						} catch (Exception numberFormatException) {
							JOptionPane.showMessageDialog(null, "Enter numbers!!");
						} */


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
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			contentPanel.setBackground(Color.ORANGE);
		}
	}

	
	//****************************************GET & SET********************************************
	
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

	public JTextField getTxtInnerRadius() {
		return txtInnerRadius;
	}

	public void setTxtInnerRadius(JTextField txtInnerRadius) {
		this.txtInnerRadius = txtInnerRadius;
	}

	public boolean isOk1() {
		return isOk1;
	}

	public void setOk(boolean isOk1) {
		this.isOk1 = isOk1;
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

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getBorder() {
		return border;
	}

	public void setBorder(Color border) {
		this.border = border;
	}

	public JButton getBtnInnerColor() {
		return btnInnerColor;
	}

	public void setBtnInnerColor(JButton btnInnerColor) {
		this.btnInnerColor = btnInnerColor;
	}

	public JButton getBtnBorderColor() {
		return btnBorderColor;
	}

	public void setBtnBorderColor(JButton btnBorderColor) {
		this.btnBorderColor = btnBorderColor;
	}





	

}
