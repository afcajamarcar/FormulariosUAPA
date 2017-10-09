package ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class ConsolRecEstFrame extends JFrame {

	private static final long serialVersionUID = 2L;
	private JPanel contentPane;
	private JTextField dniInput;
	private JTextField nombresInput;
	private JTextField apellidosInput;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsolRecEstFrame frame = new ConsolRecEstFrame();
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
	public ConsolRecEstFrame() {
		setTitle("Consolidado Reconocimeintos Estudiantiles");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 633, 381);
		setLocationRelativeTo(null); //centers the frame
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProgramas = new JLabel("Programa(s): ");
		lblProgramas.setBounds(10, 69, 66, 14);
		contentPane.add(lblProgramas);
		lblProgramas.setFont(new Font("Calibri", Font.PLAIN, 11));
		
		JComboBox<Object> programasBox = new JComboBox<Object>();
		programasBox.setBounds(79, 66, 432, 20);
		contentPane.add(programasBox);
		programasBox.setFont(new Font("Calibri", Font.PLAIN, 11));
		programasBox.setEnabled(false);
		programasBox.setEditable(true);
		
		apellidosInput = new JTextField();
		apellidosInput.setBounds(387, 40, 220, 20);
		contentPane.add(apellidosInput);
		apellidosInput.setFont(new Font("Calibri", Font.PLAIN, 11));
		apellidosInput.setEnabled(false);
		apellidosInput.setColumns(10);
		
		JLabel lblApellidos = new JLabel("Apellidos: ");
		lblApellidos.setBounds(326, 41, 49, 14);
		contentPane.add(lblApellidos);
		lblApellidos.setFont(new Font("Calibri", Font.PLAIN, 11));
		
		nombresInput = new JTextField();
		nombresInput.setBounds(79, 40, 231, 20);
		contentPane.add(nombresInput);
		nombresInput.setFont(new Font("Calibri", Font.PLAIN, 11));
		nombresInput.setEnabled(false);
		nombresInput.setColumns(10);
		
		JLabel lblNombres = new JLabel("Nombres: ");
		lblNombres.setBounds(10, 42, 49, 14);
		contentPane.add(lblNombres);
		lblNombres.setFont(new Font("Calibri", Font.PLAIN, 11));
		
		dniInput = new JTextField();
		dniInput.setBounds(80, 11, 231, 20);
		contentPane.add(dniInput);
		dniInput.setFont(new Font("Calibri", Font.PLAIN, 11));
		dniInput.setColumns(10);
		
		JLabel lblDni = new JLabel("DNI: ");
		lblDni.setBounds(10, 11, 25, 14);
		contentPane.add(lblDni);
		lblDni.setFont(new Font("Calibri", Font.PLAIN, 11));
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(531, 66, 76, 23);
		contentPane.add(btnBuscar);
		btnBuscar.setFont(new Font("Calibri", Font.PLAIN, 11));
		
		JComboBox<Object> comboBox = new JComboBox<Object>();
		comboBox.setFont(new Font("Calibri", Font.PLAIN, 11));
		comboBox.setBounds(385, 11, 131, 20);
		contentPane.add(comboBox);
		comboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"2009-01", "2009-02", "2009-03", "2010-01", "2010-02", "2010-03", "2011-01", "2011-02", "2011-03", "2012-01", "2012-02", "2012-03", "2013-01", "2013-02", "2013-03", "2014-01", "2014-02", "2014-03", "2015-01", "2015-02", "2015-03", "2016-01", "2016-02", "2016-03", "2017-01", "2017-02", "2017-03"}));
		
		JLabel lblPeriodo = new JLabel("Periodo: ");
		lblPeriodo.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblPeriodo.setBounds(330, 11, 46, 14);
		contentPane.add(lblPeriodo);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 94, 597, 237);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 597, 237);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
