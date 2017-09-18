package ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class AddPeopleFrame extends JFrame {

	private static final long serialVersionUID = 3L;
	private JPanel contentPane;
	private JTextField dniInput;
	private JTextField nombresInput;
	private JTextField apellidosInput;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPeopleFrame frame = new AddPeopleFrame();
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
	public AddPeopleFrame() {
		setTitle("A\u00F1adir Persona");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 304, 229);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombres = new JLabel("Nombres: ");
		lblNombres.setBounds(23, 66, 62, 14);
		contentPane.add(lblNombres);
		
		JLabel lblDni = new JLabel("DNI: ");
		lblDni.setBounds(23, 29, 62, 14);
		contentPane.add(lblDni);
		
		JLabel lblApellidos = new JLabel("Apellidos: ");
		lblApellidos.setBounds(23, 103, 62, 14);
		contentPane.add(lblApellidos);
		
		JButton btnAadir = new JButton("A\u00F1adir");
		btnAadir.setBounds(96, 145, 89, 23);
		contentPane.add(btnAadir);
		
		dniInput = new JTextField();
		dniInput.setBounds(82, 26, 183, 20);
		contentPane.add(dniInput);
		dniInput.setColumns(10);
		
		nombresInput = new JTextField();
		nombresInput.setColumns(10);
		nombresInput.setBounds(82, 63, 183, 20);
		contentPane.add(nombresInput);
		
		apellidosInput = new JTextField();
		apellidosInput.setColumns(10);
		apellidosInput.setBounds(82, 100, 183, 20);
		contentPane.add(apellidosInput);
	}

}
