package ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class AddEstudentsFrame extends JFrame {

	private static final long serialVersionUID = 4L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddEstudentsFrame frame = new AddEstudentsFrame();
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
	public AddEstudentsFrame() {
		setTitle("A\u00F1adir Estudiante");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 745, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDni = new JLabel("DNI: ");
		lblDni.setBounds(10, 11, 60, 14);
		contentPane.add(lblDni);
		
		JLabel lblIngresoUn = new JLabel("Ingreso un: ");
		lblIngresoUn.setBounds(10, 36, 70, 14);
		contentPane.add(lblIngresoUn);
		
		JLabel lblCorreoUnal = new JLabel("Correo unal: ");
		lblCorreoUnal.setBounds(10, 61, 76, 14);
		contentPane.add(lblCorreoUnal);
		
		JLabel lblunaleduco = new JLabel("@unal.edu.co");
		lblunaleduco.setBounds(231, 61, 85, 14);
		contentPane.add(lblunaleduco);
		
		textField = new JTextField();
		textField.setBounds(90, 58, 131, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JComboBox<Object> comboBox = new JComboBox<Object>();
		comboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"2009-01", "2009-02", "2009-03", "2010-01", "2010-02", "2010-03", "2011-01", "2011-02", "2011-03", "2012-01", "2012-02", "2012-03", "2013-01", "2013-02", "2013-03", "2014-01", "2014-02", "2014-03", "2015-01", "2015-02", "2015-03", "2016-01", "2016-02", "2016-03", "2017-01", "2017-02", "2017-03"}));
		comboBox.setBounds(90, 33, 131, 20);
		contentPane.add(comboBox);
		
		textField_1 = new JTextField();
		textField_1.setBounds(90, 8, 131, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
	}

}
