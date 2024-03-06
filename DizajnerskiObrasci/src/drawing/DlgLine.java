package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

public class DlgLine extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtSPX;
	private JTextField txtSPY;
	private JTextField txtEPX;
	private JTextField txtEPY;
	private boolean isGood;
	private boolean colorIsOk;
	private JButton btnColor;
	Color fill;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgLine dialog = new DlgLine();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgLine() {
		setBounds(100, 100, 450, 300);
		setTitle("Modify");
		setModal(true);
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
			JLabel lblStartPointX = new JLabel("Start Point X:");
			GridBagConstraints gbc_lblStartPointX = new GridBagConstraints();
			gbc_lblStartPointX.insets = new Insets(0, 0, 5, 5);
			gbc_lblStartPointX.gridx = 1;
			gbc_lblStartPointX.gridy = 1;
			contentPanel.add(lblStartPointX, gbc_lblStartPointX);
		}
		{
			txtSPX = new JTextField();
			GridBagConstraints gbc_txtSPX = new GridBagConstraints();
			gbc_txtSPX.insets = new Insets(0, 0, 5, 0);
			gbc_txtSPX.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtSPX.gridx = 3;
			gbc_txtSPX.gridy = 1;
			contentPanel.add(txtSPX, gbc_txtSPX);
			txtSPX.setColumns(10);
		}
		{
			JLabel lblStartPointY = new JLabel("Start Point Y:");
			GridBagConstraints gbc_lblStartPointY = new GridBagConstraints();
			gbc_lblStartPointY.insets = new Insets(0, 0, 5, 5);
			gbc_lblStartPointY.gridx = 1;
			gbc_lblStartPointY.gridy = 2;
			contentPanel.add(lblStartPointY, gbc_lblStartPointY);
		}
		{
			txtSPY = new JTextField();
			GridBagConstraints gbc_txtSPY = new GridBagConstraints();
			gbc_txtSPY.insets = new Insets(0, 0, 5, 0);
			gbc_txtSPY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtSPY.gridx = 3;
			gbc_txtSPY.gridy = 2;
			contentPanel.add(txtSPY, gbc_txtSPY);
			txtSPY.setColumns(10);
		}
		{
			JLabel lblEndPointX = new JLabel("End Point X:");
			GridBagConstraints gbc_lblEndPointX = new GridBagConstraints();
			gbc_lblEndPointX.insets = new Insets(0, 0, 5, 5);
			gbc_lblEndPointX.gridx = 1;
			gbc_lblEndPointX.gridy = 3;
			contentPanel.add(lblEndPointX, gbc_lblEndPointX);
		}
		{
			txtEPX = new JTextField();
			GridBagConstraints gbc_txtEPX = new GridBagConstraints();
			gbc_txtEPX.insets = new Insets(0, 0, 5, 0);
			gbc_txtEPX.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtEPX.gridx = 3;
			gbc_txtEPX.gridy = 3;
			contentPanel.add(txtEPX, gbc_txtEPX);
			txtEPX.setColumns(10);
		}
		{
			JLabel lblEndPointY = new JLabel("End Point Y:");
			GridBagConstraints gbc_lblEndPointY = new GridBagConstraints();
			gbc_lblEndPointY.insets = new Insets(0, 0, 5, 5);
			gbc_lblEndPointY.gridx = 1;
			gbc_lblEndPointY.gridy = 4;
			contentPanel.add(lblEndPointY, gbc_lblEndPointY);
		}
		{
			txtEPY = new JTextField();
			GridBagConstraints gbc_txtEPY = new GridBagConstraints();
			gbc_txtEPY.insets = new Insets(0, 0, 5, 0);
			gbc_txtEPY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtEPY.gridx = 3;
			gbc_txtEPY.gridy = 4;
			contentPanel.add(txtEPY, gbc_txtEPY);
			txtEPY.setColumns(10);
		}
		{
		    btnColor = new JButton("Color:");
			btnColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					fill = JColorChooser.showDialog(null, "Choose a color", fill);
					btnColor.setBackground(fill);
					colorIsOk = true;
				}
			});
			GridBagConstraints gbc_btnColor = new GridBagConstraints();
			gbc_btnColor.insets = new Insets(0, 0, 0, 5);
			gbc_btnColor.gridx = 1;
			gbc_btnColor.gridy = 6;
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
							if (txtSPX.getText().trim().isEmpty() || txtSPY.getText().trim().isEmpty()
									|| txtEPX.getText().trim().isEmpty() || txtEPY.getText().trim().isEmpty()) {
								JOptionPane.showMessageDialog(null, "You didn't enter any numbers");
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
		}
		contentPanel.setBackground(Color.orange);
	}
	
	//****************************************GET & SET*******************************************

	public JTextField getTxtSPX() {
		return txtSPX;
	}

	public void setTxtSPX(JTextField txtSPX) {
		this.txtSPX = txtSPX;
	}

	public JTextField getTxtSPY() {
		return txtSPY;
	}

	public void setTxtSPY(JTextField txtSPY) {
		this.txtSPY = txtSPY;
	}

	public JTextField getTxtEPX() {
		return txtEPX;
	}

	public void setTxtEPX(JTextField txtEPX) {
		this.txtEPX = txtEPX;
	}

	public JTextField getTxtEPY() {
		return txtEPY;
	}

	public void setTxtEPY(JTextField txtEPY) {
		this.txtEPY = txtEPY;
	}

	public boolean isGood() {
		return isGood;
	}

	public void setGood(boolean isGood) {
		this.isGood = isGood;
	}

	public boolean isColorIsOk() {
		return colorIsOk;
	}

	public void setColorIsOk(boolean colorIsOk) {
		this.colorIsOk = colorIsOk;
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

	
}
