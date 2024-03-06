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

public class DlgRectangle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	protected JTextField txtULPX;
	protected JTextField txtULPY;
	protected JTextField txtWidth;
	protected JTextField txtHeight;
	protected boolean isOkay;
	protected boolean colorOkay;
	protected boolean borderOkay;
	private JButton btnInnerCol;
	private JButton btnBorderCol;
	Color inner; // inner
	Color border; // border

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgRectangle dialog = new DlgRectangle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgRectangle() {
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
			JLabel lblULPX = new JLabel("Upper Left Point - X:");
			GridBagConstraints gbc_lblULPX = new GridBagConstraints();
			gbc_lblULPX.insets = new Insets(0, 0, 5, 5);
			gbc_lblULPX.gridx = 1;
			gbc_lblULPX.gridy = 1;
			contentPanel.add(lblULPX, gbc_lblULPX);
		}
		{
			txtULPX = new JTextField();
			GridBagConstraints gbc_txtULPX = new GridBagConstraints();
			gbc_txtULPX.insets = new Insets(0, 0, 5, 0);
			gbc_txtULPX.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtULPX.gridx = 3;
			gbc_txtULPX.gridy = 1;
			contentPanel.add(txtULPX, gbc_txtULPX);
			txtULPX.setColumns(10);
		}
		{
			JLabel lblULPY = new JLabel("Upper Left Point - Y:");
			GridBagConstraints gbc_lblULPY = new GridBagConstraints();
			gbc_lblULPY.insets = new Insets(0, 0, 5, 5);
			gbc_lblULPY.gridx = 1;
			gbc_lblULPY.gridy = 2;
			contentPanel.add(lblULPY, gbc_lblULPY);
		}
		{
			txtULPY = new JTextField();
			GridBagConstraints gbc_txtULPY = new GridBagConstraints();
			gbc_txtULPY.insets = new Insets(0, 0, 5, 0);
			gbc_txtULPY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtULPY.gridx = 3;
			gbc_txtULPY.gridy = 2;
			contentPanel.add(txtULPY, gbc_txtULPY);
			txtULPY.setColumns(10);
		}
		{
			JLabel lblWidth = new JLabel("Width:");
			GridBagConstraints gbc_lblWidth = new GridBagConstraints();
			gbc_lblWidth.insets = new Insets(0, 0, 5, 5);
			gbc_lblWidth.gridx = 1;
			gbc_lblWidth.gridy = 3;
			contentPanel.add(lblWidth, gbc_lblWidth);
		}
		{
			txtWidth = new JTextField();
			GridBagConstraints gbc_txtWidth = new GridBagConstraints();
			gbc_txtWidth.insets = new Insets(0, 0, 5, 0);
			gbc_txtWidth.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtWidth.gridx = 3;
			gbc_txtWidth.gridy = 3;
			contentPanel.add(txtWidth, gbc_txtWidth);
			txtWidth.setColumns(10);
		}
		{
			JLabel lblHeight = new JLabel("Height:");
			GridBagConstraints gbc_lblHeight = new GridBagConstraints();
			gbc_lblHeight.insets = new Insets(0, 0, 5, 5);
			gbc_lblHeight.gridx = 1;
			gbc_lblHeight.gridy = 4;
			contentPanel.add(lblHeight, gbc_lblHeight);
		}
		{
			txtHeight = new JTextField();
			GridBagConstraints gbc_txtHeight = new GridBagConstraints();
			gbc_txtHeight.insets = new Insets(0, 0, 5, 0);
			gbc_txtHeight.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtHeight.gridx = 3;
			gbc_txtHeight.gridy = 4;
			contentPanel.add(txtHeight, gbc_txtHeight);
			txtHeight.setColumns(10);
		}
	    {
			btnInnerCol = new JButton("Inner color:");
			btnInnerCol.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					inner = JColorChooser.showDialog(null, "Choose inner color:", inner);
					if(inner!=null) {
					btnInnerCol.setBackground(inner);
					colorOkay = true;
					}
				}

			});
			GridBagConstraints gbc_btnColor1 = new GridBagConstraints();
			gbc_btnColor1.insets = new Insets(0, 0, 5, 5);
			gbc_btnColor1.gridx = 1;
			gbc_btnColor1.gridy = 6;
			contentPanel.add(btnInnerCol, gbc_btnColor1);

		     {
		    	btnBorderCol = new JButton("Border color:");
		    	btnBorderCol.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						border = JColorChooser.showDialog(null, "Choose border color:", border);
						if(border!=null) {
						btnBorderCol.setBackground(border);
						borderOkay = true;
						}
					}

				});
				GridBagConstraints gbc_btnColor2 = new GridBagConstraints();
				gbc_btnColor2.insets = new Insets(0, 0, 0, 5);
				gbc_btnColor2.gridx = 1;
				gbc_btnColor2.gridy = 7;
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
							// isOk2=true;
							// dispose();
							try {
								if (txtULPX.getText().trim().isEmpty() || txtULPY.getText().trim().isEmpty()
										|| txtHeight.getText().trim().isEmpty()
										|| txtWidth.getText().trim().isEmpty()) {
									JOptionPane.showMessageDialog(null, "You didn't enter any numbers");
								}
								else if (Integer.parseInt(txtHeight.getText()) < 0
										|| Integer.parseInt(txtWidth.getText()) < 0) {
									JOptionPane.showMessageDialog(null, "Width and height can not be negative numbers");
								} else {
									isOkay = true;
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
				contentPanel.setBackground(Color.ORANGE);
			}
		}
	}
	
	//**************************************GET & SET*****************************************

	public JTextField getTxtULPX() {
		return txtULPX;
	}

	public void setTxtULPX(JTextField txtULPX) {
		this.txtULPX = txtULPX;
	}

	public JTextField getTxtULPY() {
		return txtULPY;
	}

	public void setTxtULPY(JTextField txtULPY) {
		this.txtULPY = txtULPY;
	}

	public JTextField getTxtWidth() {
		return txtWidth;
	}

	public void setTxtWidth(JTextField txtWidth) {
		this.txtWidth = txtWidth;
	}

	public JTextField getTxtHeight() {
		return txtHeight;
	}

	public void setTxtHeight(JTextField txtHeight) {
		this.txtHeight = txtHeight;
	}

	public boolean isOkay() {
		return isOkay;
	}

	public void setOkay(boolean isOkay) {
		this.isOkay = isOkay;
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


	public Color getInner() {
		return inner;
	}

	public void setInner(Color inner) {
		this.inner = inner;
	}

	public Color getBorder() {
		return border;
	}

	public void setBorder(Color border) {
		this.border = border;
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
