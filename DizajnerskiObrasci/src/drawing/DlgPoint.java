package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Point;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

public class DlgPoint extends JDialog {

	private final JPanel contentPanel = new JPanel();
	protected JTextField txtCordinateX;
	protected JTextField txtCordinateY;
	private boolean isGood;
	protected boolean ColorIsOk;
	Color fill;
	private JButton btnColor;
	//private JButton btnLine;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgPoint dialog = new DlgPoint();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgPoint() {
		setBounds(100, 100, 450, 300);
		setModal(true);
		setTitle("Modify");
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
			lblCordinateY.setDisplayedMnemonic('l');
			GridBagConstraints gbc_lblCordinateY = new GridBagConstraints();
			gbc_lblCordinateY.insets = new Insets(0, 0, 5, 5);
			gbc_lblCordinateY.gridx = 1;
			gbc_lblCordinateY.gridy = 3;
			contentPanel.add(lblCordinateY, gbc_lblCordinateY);
		}
		{
			txtCordinateY = new JTextField();
			GridBagConstraints gbc_txtCordinateY = new GridBagConstraints();
			gbc_txtCordinateY.insets = new Insets(0, 0, 5, 0);
			gbc_txtCordinateY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtCordinateY.gridx = 3;
			gbc_txtCordinateY.gridy = 3;
			contentPanel.add(txtCordinateY, gbc_txtCordinateY);
			txtCordinateY.setColumns(10);
		}
		{
		    btnColor = new JButton("Color:");
			btnColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					fill = JColorChooser.showDialog(null, "Choose a color", fill);
					if(fill!=null) {
					btnColor.setBackground(fill); // dugme da bude iste boje kao izabrana boja
					ColorIsOk = true;
					}
				}
			});
			GridBagConstraints gbc_btnColor = new GridBagConstraints();
			gbc_btnColor.insets = new Insets(0, 0, 5, 5);
			gbc_btnColor.gridx = 1;
			gbc_btnColor.gridy = 5;
			contentPanel.add(btnColor, gbc_btnColor);
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
							if (txtCordinateX.getText().trim().isEmpty() || txtCordinateY.getText().trim().isEmpty()) {
								JOptionPane.showMessageDialog(null, "You didn't enter any numbers!!!");
							} else {
								isGood = true;
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
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			contentPanel.setBackground(Color.orange);
		}
	}
	
	//**************************************GET & SET*****************************************

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

	public boolean isGood() {
		return isGood;
	}

	public void setGood(boolean isGood) {
		this.isGood = isGood;
	}

	public boolean isColorIsOk() {
		return ColorIsOk;
	}

	public void setColorIsOk(boolean colorIsOk) {
		ColorIsOk = colorIsOk;
	}

	public Color getFill() {
		return fill;
	}

	public void setFill(Color fill) {
		this.fill = fill;
	}

	public JButton getBtnColor() {
		return btnColor;
	}

	public void setBtnColor(JButton btnColor) {
		this.btnColor = btnColor;
	}




	// ____________________

}
