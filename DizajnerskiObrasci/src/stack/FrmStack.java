package stack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Point;
import geometry.Rectangle;
import sort.DlgAdd;

import javax.swing.JLabel;
import java.awt.GridBagLayout;
import javax.swing.JToggleButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

public class FrmStack extends JFrame {

	private JPanel contentPane;
	protected DefaultListModel<Rectangle> dlm = new DefaultListModel<Rectangle>();
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmStack frame = new FrmStack();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmStack() {
		setTitle("Tunic Jovana IT3-2020");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel pnlNorth = new JPanel();
		contentPane.add(pnlNorth, BorderLayout.NORTH);

		JLabel lblStack = new JLabel("STACK");
		pnlNorth.add(lblStack);
		pnlNorth.setBackground(Color.orange);

		JPanel pnlCenter = new JPanel();
		contentPane.add(pnlCenter, BorderLayout.CENTER);
		GridBagLayout gbl_pnlCenter = new GridBagLayout();
		gbl_pnlCenter.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_pnlCenter.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_pnlCenter.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_pnlCenter.rowWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		pnlCenter.setLayout(gbl_pnlCenter);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 6;
		gbc_scrollPane.gridy = 2;
		pnlCenter.add(scrollPane, gbc_scrollPane);

		JList list = new JList();
		scrollPane.setViewportView(list);
		list.setModel(dlm);

		JPanel pnlSouth = new JPanel();
		contentPane.add(pnlSouth, BorderLayout.SOUTH);

		//***************************************ADD*******************************************

		JToggleButton tglbtnAdd = new JToggleButton("Add");
		tglbtnAdd.setBackground(new Color(240, 230, 140));
		buttonGroup.add(tglbtnAdd);
		tglbtnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgAdd dlgAddRect=new DlgAdd();
				dlgAddRect.setVisible(true);
			        try {
					int x = Integer.parseInt(dlgAddRect.getTxtX().getText());
					int y = Integer.parseInt(dlgAddRect.getTxtY().getText());
					int height = Integer.parseInt(dlgAddRect.getTxtHeight().getText());
					int width = Integer.parseInt(dlgAddRect.getTxtWidth().getText());
					if (dlgAddRect.getTxtX().getText().trim().isEmpty() || dlgAddRect.getTxtY().getText().trim().isEmpty()
							|| dlgAddRect.getTxtWidth().getText().trim().isEmpty()
							|| dlgAddRect.getTxtHeight().getText().trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "You didn't enter any numbers!");
					} else if (Integer.parseInt(dlgAddRect.getTxtHeight().getText()) < 0
							|| Integer.parseInt(dlgAddRect.getTxtWidth().getText()) < 0) {
						JOptionPane.showMessageDialog(null, "Width and height can not be negative numbers!");
					}
					else {
						Rectangle r = new Rectangle(new Point(x, y), width, height);
						dlm.addElement(r);// 0 dodajemo na pocetak liste-na prvo mesto
					}
				} catch (Exception numberFormatException) {
					JOptionPane.showMessageDialog(null, "You didn't enter correct number");
				}
			
			}
		});
		pnlSouth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pnlSouth.add(tglbtnAdd);
		pnlSouth.setBackground(Color.orange);

		// ************************************DELETE*****************************************

		JToggleButton tglbtnDelete = new JToggleButton("Delete");
		tglbtnDelete.setBackground(new Color(240, 230, 140));
		tglbtnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dlm.isEmpty()) {
					JOptionPane.showMessageDialog(null, "List is empty!");
				} else {

					DlgAdd dlgAddRect = new DlgAdd(); // povezivanje dijaloga add sa delete
					Point p = dlm.getElementAt(0).getUpperLeft(); // uzimanje vrednosti sa 0 indeksa
					int width = dlm.getElementAt(0).getWidth(); // uzimanje vrednosti sirine sa 0 indeksa
					int height = dlm.getElementAt(0).getHeight(); // uzimanje vrednosti visine sa 0 indeksa

					dlgAddRect.getTxtX().setText(Integer.toString(p.getX())); // ispisisuje poslednji napisan br
					dlgAddRect.getTxtY().setText(Integer.toString(p.getY()));
					dlgAddRect.getTxtWidth().setText(Integer.toString(width));
					dlgAddRect.getTxtHeight().setText(Integer.toString(height));
					dlgAddRect.setVisible(true);

					if (dlgAddRect.isOk()) {
						dlm.removeElementAt(0); // brise poslednji dodat element
					}
				}

			}
		});
		buttonGroup.add(tglbtnDelete);
		pnlSouth.add(tglbtnDelete);
	}

}
