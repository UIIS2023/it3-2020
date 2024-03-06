package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class DlgHexagon extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtRadius;
	private boolean isGood;
	private boolean cancelCircle;
	private JButton btnHexBorder;
	private JButton btnHexInner;
	
	Color innerH;
	Color borderH;
	
	private boolean borderOkay;
	private boolean innerOkay;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgCircle dialog = new DlgCircle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgHexagon() {
		setBounds(100, 100, 450, 300);
		setModal(true);
		setTitle("Hexagon");
		getContentPanel().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		/*{
			Box horizontalBox = Box.createHorizontalBox();
			GridBagConstraints gbc_horizontalBox = new GridBagConstraints();
			gbc_horizontalBox.insets = new Insets(0, 0, 5, 5);
			gbc_horizontalBox.gridx = 1;
			gbc_horizontalBox.gridy = 1;
			contentPanel.add(horizontalBox, gbc_horizontalBox);
		}
		/*{
			JLabel lblCenter = new JLabel("Center of Hexagon");
			GridBagConstraints gbc_lblCenter = new GridBagConstraints();
			gbc_lblCenter.anchor = GridBagConstraints.WEST;
			gbc_lblCenter.insets = new Insets(0, 0, 5, 0);
			gbc_lblCenter.gridx = 2;
			gbc_lblCenter.gridy = 1;
			contentPanel.add(lblCenter, gbc_lblCenter);
		}*/
		{
			JLabel lblCX = new JLabel("Coordinate X:");
			GridBagConstraints gbc_lblCX = new GridBagConstraints();
			gbc_lblCX.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblCX.gridx = 1;
			gbc_lblCX.gridy = 1;
			contentPanel.add(lblCX, gbc_lblCX);
		}
		{
			txtX = new JTextField();
			GridBagConstraints gbc_txtX = new GridBagConstraints();
			gbc_txtX.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtX.insets = new Insets(0, 0, 5, 0);
			gbc_txtX.gridx = 3;
			gbc_txtX.gridy = 1;
			contentPanel.add(txtX, gbc_txtX);
			txtX.setColumns(10);
		}
		{
			JLabel lblCY = new JLabel("Coordinate Y:");
			GridBagConstraints gbc_lblCY = new GridBagConstraints();
			gbc_lblCY.insets = new Insets(0, 0, 5, 5);
			gbc_lblCY.gridx = 1;
			gbc_lblCY.gridy = 2;
			contentPanel.add(lblCY, gbc_lblCY);
		}
		{
			txtY = new JTextField();
			GridBagConstraints gbc_txtY = new GridBagConstraints();
			gbc_txtY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtY.insets = new Insets(0, 0, 5, 0);
			gbc_txtY.gridx = 3;
			gbc_txtY.gridy = 2;
			contentPanel.add(txtY, gbc_txtY);
			txtY.setColumns(10);
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
			btnHexInner = new JButton("Inner color:");
			btnHexInner.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (e.getSource() == btnHexInner) {
						innerH = JColorChooser.showDialog(null, "Choose inner color:", innerH);
						if(innerH != null) {
						btnHexInner.setBackground(innerH);
						innerOkay = true;
						System.out.println(innerH);
						}
					}
				}

			});
			GridBagConstraints gbc_btnColor1 = new GridBagConstraints();
			gbc_btnColor1.insets = new Insets(0, 0, 5, 5);
			gbc_btnColor1.gridx = 1;
			gbc_btnColor1.gridy = 5;
			contentPanel.add(btnHexInner, gbc_btnColor1);
		}
		{
			btnHexBorder = new JButton("Border color:");
			btnHexBorder.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					borderH = JColorChooser.showDialog(null, "Choose border color:", borderH);
					if(borderH !=null) {
					btnHexBorder.setBackground(borderH);
					borderOkay = true;
					}
				}
			});
			GridBagConstraints gbc_btnColor2 = new GridBagConstraints();
			gbc_btnColor2.insets = new Insets(0, 0, 0, 5);
			gbc_btnColor2.gridx = 1;
			gbc_btnColor2.gridy = 6;
			contentPanel.add(btnHexBorder, gbc_btnColor2);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if(txtRadius.getText().trim().isEmpty())  
								JOptionPane.showMessageDialog(null, "Please enter value in the field!");
								else if ((Integer.parseInt(txtRadius.getText().trim())<=0))
									JOptionPane.showMessageDialog(null,"Please, enter value greater than 0!");
								else {
									setGood(true);
									setVisible(false);
								}
									
						}catch(Exception e1) {
							JOptionPane.showMessageDialog(null, "Please, enter the number!");
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
						setCancelCircle(true);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			contentPanel.setBackground(Color.ORANGE);

		}
	}

	public JTextField getTxtX() {
		return txtX;
	}

	public void setTxtX(JTextField txtX) {
		this.txtX = txtX;
	}

	public JTextField getTxtY() {
		return txtY;
	}

	public void setTxtY(JTextField txtY) {
		this.txtY = txtY;
	}

	public JTextField getTxtRadius() {
		return txtRadius;
	}

	public void setTxtRadius(JTextField txtRadius) {
		this.txtRadius = txtRadius;
	}

	public boolean isGood() {
		return isGood;
	}

	public void setGood(boolean isGood) {
		this.isGood = isGood;
	}

	public boolean isCancelCircle() {
		return cancelCircle;
	}

	public void setCancelCircle(boolean cancelCircle) {
		this.cancelCircle = cancelCircle;
	}

	public JButton getBtnHexBorder() {
		return btnHexBorder;
	}

	public void setBtnHexBorder(JButton btnHexBorder) {
		this.btnHexBorder = btnHexBorder;
	}

	public JButton getBtnHexInner() {
		return btnHexInner;
	}

	public void setBtnHexInner(JButton btnHexInner) {
		this.btnHexInner = btnHexInner;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}
	
	
	
}


