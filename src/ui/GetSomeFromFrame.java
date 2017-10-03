package ui;

import java.awt.*;

import javax.management.Query;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import mysql.Querys;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;

public class GetSomeFromFrame extends JFrame {

	private static final long serialVersionUID = 4L;
	private JPanel contentPane;
	private JTextField inputFieldValue;
	private DefaultTableModel tableModel;
	private JComboBox fieldBox;
	private JComboBox<String> comboTables;
	private JButton btnSearchFieldFrom;
	private JScrollPane scrollPane;
	private JTable dataTable;

	/**
	 * When external classes call this method, it launches the application.
	 */
	public void initialize() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GetSomeFromFrame frame = new GetSomeFromFrame();
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
		setTitle("A\u00F1adir Estudiante");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 481, 362);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblField = new JLabel("Campo: ");
		lblField.setFont(new Font("Calibri", Font.PLAIN, 10));
		lblField.setBounds(10, 36, 58, 14);
		contentPane.add(lblField);
		
		JLabel lblFieldValue = new JLabel("Valor del campo: ");
		lblFieldValue.setFont(new Font("Calibri", Font.PLAIN, 10));
		lblFieldValue.setBounds(229, 11, 84, 14);
		contentPane.add(lblFieldValue);
		
		JLabel lblTables = new JLabel("De la Tabla:");
		lblTables.setFont(new Font("Calibri", Font.PLAIN, 10));
		lblTables.setBounds(10, 11, 68, 14);
		contentPane.add(lblTables);
		
		inputFieldValue = new JTextField();
		inputFieldValue.setFont(new Font("Calibri", Font.PLAIN, 10));
		inputFieldValue.setBounds(323, 8, 131, 20);
		contentPane.add(inputFieldValue);
		inputFieldValue.setColumns(10);
		inputFieldValue.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				checkFields();
				
			}
		});
		
		comboTables = new JComboBox<String>();
		comboTables.setFont(new Font("Calibri", Font.PLAIN, 10));
		comboTables.setModel(new DefaultComboBoxModel<String>(new String[] {"Null", "consolidado_reconocimientos_estudiantiles", "estudiantes", "programas", "reconocimientos", "rel_estudiante_programa"}));
		comboTables.setBounds(88, 8, 131, 20);
		contentPane.add(comboTables);
		
		btnSearchFieldFrom = new JButton("Buscar");
		btnSearchFieldFrom.setFont(new Font("Calibri", Font.PLAIN, 10));
		btnSearchFieldFrom.setEnabled(false);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 112, 444, 200);
		contentPane.add(scrollPane);
		
		
        //getContentPane().add(new JScrollPane(dataTable), BorderLayout.CENTER);
		btnSearchFieldFrom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[][] data = AuthenticationFrame.consult.getSomeFromTable(fieldBox.getSelectedItem().toString(), inputFieldValue.getText(), comboTables.getSelectedItem().toString());
				String[] columnNames = data[0];
				
				for (int i = 0; i < data.length-1; i++) {
					data[i] = data[i+1];
				}
				System.out.println(Arrays.deepToString(data));
				
				dataTable = new JTable(new DefaultTableModel(data,columnNames));
				dataTable.setAutoResizeMode(dataTable.AUTO_RESIZE_OFF);
				scrollPane.setViewportView(dataTable);

			}
		});
		btnSearchFieldFrom.setBounds(365, 36, 89, 23);
		contentPane.add(btnSearchFieldFrom);
		
		comboTables.addItemListener(new ItemChangeListener());
		fieldBox = new JComboBox<String>();
		fieldBox.setBounds(88, 36, 131, 20);
		contentPane.add(fieldBox);
		
		
		
		
		
	}
	
	
	class ItemChangeListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent event) {
			if (event.getStateChange() == ItemEvent.SELECTED) {
		          Object item = event.getItem();
		          fieldBox.setModel(new DefaultComboBoxModel<String>(AuthenticationFrame.consult.getColumnNames(item.toString())));
		          checkFields();
		       }
			
		}       
	}


	public JComboBox getFieldBox() {
		return fieldBox;
	}

	public void setFieldBox(JComboBox fieldBox) {
		this.fieldBox = fieldBox;
	}
	
	/**
	 * Checks if the necessary fields are filled with data in order to activate the search button
	 */
	private void checkFields() {
		if(comboTables.getSelectedItem() != null && !inputFieldValue.getText().isEmpty() && fieldBox.getSelectedItem() != null) {
			btnSearchFieldFrom.setEnabled(true);
		}
	}
}
