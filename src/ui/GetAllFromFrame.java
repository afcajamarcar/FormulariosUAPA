package ui;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import com.sun.glass.events.KeyEvent;

import java.awt.event.*;

public class GetAllFromFrame extends JFrame {

	private static final long serialVersionUID = 4L;
	private JPanel contentPane;
	private JComboBox<String> comboTables;
	private JScrollPane scrollPane;
	private String[] tables = new String[] {"Seleccionar tabla...", "consolidado_reconocimientos_estudiantiles", "estudiantes","personas_unal_uapa", "programas", "reconocimientos", "rel_estudiante_programa"};
	private AddPersonFrame addPerson;
	private JMenuItem mntmAadirPersona;
	private JTable dataTable;
	private AddAwardFrame addAward;

	/**
	 * When external classes call this method, it launches the application.
	 */
	public static void initialize() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GetAllFromFrame frame = new GetAllFromFrame();
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
	public GetAllFromFrame() {
		setTitle("Consultar tablas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 570, 384);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnConsulta = new JMenu("Consultar");
		menuBar.add(mnConsulta);
		
		JMenuItem mnSomeFromTable = new JMenuItem("Consulta Tabla");
		mnSomeFromTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GetAllFromFrame.initialize();
				dispose();
			}
		});
		mnSomeFromTable.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.CTRL_MASK));
		mnConsulta.add(mnSomeFromTable);
		
		JMenuItem mnAllFromTable = new JMenuItem("Consulta especifica Tabla");
		mnAllFromTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GetSomeFromFrame.initialize();
				dispose();
			}
		});
		mnAllFromTable.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.CTRL_MASK));
		mnConsulta.add(mnAllFromTable);
		
		JMenu mnAadir = new JMenu("A\u00F1adir");
		menuBar.add(mnAadir);
		
		mntmAadirPersona = new JMenuItem("Persona");
		mntmAadirPersona.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addPerson = new AddPersonFrame();
				addPerson.initialize();
			}
		});
		KeyStroke f2 = KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0);
		mntmAadirPersona.setAccelerator(f2);
		mnAadir.add(mntmAadirPersona);
		
		JMenuItem mntmEstudiante = new JMenuItem("Estudiante");
		mnAadir.add(mntmEstudiante);
		
		JMenuItem mntmReconocimiento = new JMenuItem("Reconocimiento");
		mntmReconocimiento.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0));
		mnAadir.add(mntmReconocimiento);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		mntmReconocimiento.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addAward = new AddAwardFrame();
				addAward.initialize();
				
			}
		});
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTables = new JLabel("De la Tabla:");
		lblTables.setFont(new Font("Calibri", Font.PLAIN, 10));
		lblTables.setBounds(10, 11, 68, 14);
		contentPane.add(lblTables);
		
		comboTables = new JComboBox<String>();
		comboTables.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[][] data = AuthenticationFrame.consult.getAllFromTable( comboTables.getSelectedItem().toString() );
				String[] columnNames = data[0];
				
				int r= data.length;
		        int c= data[0].length;
		        String [][] temp = new String [r-1][c];
		        for(int k = 0; k < r-1; k++ ) {
		        	for (int l = 0; l < c; l++) {
		        		temp[k][l] = data[k+1][l];
					}
		        }
		        
				dataTable = new JTable(new DefaultTableModel(temp,columnNames));
				dataTable.setAutoResizeMode(dataTable.AUTO_RESIZE_ALL_COLUMNS);
				scrollPane.setViewportView(dataTable);
			}
		});
		comboTables.setFont(new Font("Calibri", Font.PLAIN, 10));
		comboTables.setModel(new DefaultComboBoxModel<String>(AuthenticationFrame.consult.getTableNames()));
		comboTables.setBounds(88, 8, 131, 20);
		contentPane.add(comboTables);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 535, 276);
		contentPane.add(scrollPane);
		
	}
}
