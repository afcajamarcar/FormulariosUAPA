package ui;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import com.sun.glass.events.KeyEvent;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;

public class GetSomeFromFrame extends JFrame {

	private static final long serialVersionUID = 4L;
	private JPanel contentPane;
	private JTextField inputFieldValue;
	private DefaultTableModel tableModel;
	private JComboBox<String> fieldBox;
	private JComboBox<String> comboTables;
	private JScrollPane scrollPane;
	private JTable dataTable;
	private String[] tables = new String[] {"Seleccionar tabla...", "consolidado_reconocimientos_estudiantiles", "estudiantes","personas_unal_uapa", "programas", "reconocimientos", "rel_estudiante_programa"};
	private AddPersonFrame addPerson;
	private JMenuItem mntmAadirPersona;
	private JMenuItem mntmReconocimiento;
	private AddAwardFrame addAward;
	private static GetSomeFromFrame frame;

	/**
	 * When external classes call this method, it launches the application.
	 */
	public static void initialize() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new GetSomeFromFrame();
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
	public GetSomeFromFrame() {
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
				frame.setVisible(false);
			}
		});
		mnSomeFromTable.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.CTRL_MASK));
		mnConsulta.add(mnSomeFromTable);
		
		JMenuItem mnAllFromTable = new JMenuItem("Consulta especifica Tabla");
		mnAllFromTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GetSomeFromFrame.initialize();
				frame.setVisible(false);
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
		
		mntmReconocimiento = new JMenuItem("Reconocimiento");
		mntmReconocimiento.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addAward = new AddAwardFrame();
				addAward.initialize();
			}
		});
		mnAadir.add(mntmReconocimiento);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblField = new JLabel("Campo: ");
		lblField.setFont(new Font("Calibri", Font.PLAIN, 10));
		lblField.setBounds(246, 11, 58, 14);
		contentPane.add(lblField);
		
		JLabel lblFieldValue = new JLabel("Valor del campo: ");
		lblFieldValue.setFont(new Font("Calibri", Font.PLAIN, 10));
		lblFieldValue.setBounds(10, 35, 84, 14);
		contentPane.add(lblFieldValue);
		
		JLabel lblTables = new JLabel("De la Tabla:");
		lblTables.setFont(new Font("Calibri", Font.PLAIN, 10));
		lblTables.setBounds(10, 11, 68, 14);
		contentPane.add(lblTables);
		
		inputFieldValue = new JTextField();
		inputFieldValue.setFont(new Font("Calibri", Font.PLAIN, 10));
		inputFieldValue.setBounds(88, 33, 131, 20);
		inputFieldValue.setToolTipText("Presione Enter para consultar.");
		contentPane.add(inputFieldValue);
		inputFieldValue.setColumns(10);
		inputFieldValue.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[][] data = AuthenticationFrame.consult.getSomeFromTable(fieldBox.getSelectedItem().toString(), inputFieldValue.getText(), comboTables.getSelectedItem().toString());
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
				dataTable.setAutoResizeMode(dataTable.AUTO_RESIZE_OFF);
				scrollPane.setViewportView(dataTable);
				
			}
		});
		
		comboTables = new JComboBox<String>();
		comboTables.setFont(new Font("Calibri", Font.PLAIN, 10));
		comboTables.setModel(new DefaultComboBoxModel<String>(tables));
		comboTables.setBounds(88, 8, 131, 20);
		contentPane.add(comboTables);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 67, 535, 245);
		contentPane.add(scrollPane);
		
		comboTables.addItemListener(new ItemChangeListener());
		fieldBox = new JComboBox<String>();
		fieldBox.setBounds(285, 7, 131, 20);
		contentPane.add(fieldBox);
		
	}
	
	
	class ItemChangeListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent event) {
			if (event.getStateChange() == ItemEvent.SELECTED) {
		          Object item = event.getItem();
		          fieldBox.setModel(new DefaultComboBoxModel<String>(AuthenticationFrame.consult.getColumnNames(item.toString())));
		    }
		}
	}


	public JComboBox<String> getFieldBox() {
		return fieldBox;
	}

	public void setFieldBox(JComboBox<String>fieldBox) {
		this.fieldBox = fieldBox;
	}
}
