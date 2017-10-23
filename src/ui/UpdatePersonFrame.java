package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;


import javax.swing.*;
import javax.swing.border.*;

import entities.Person;

public class UpdatePersonFrame extends JFrame {

	private static final long serialVersionUID = 3L;
	private JPanel contentPane;
	private JTextField dniInput;
	private JTextField nombresInput;
	private JTextField apellidosInput;
	private JLabel typeDNILabel;
	private String[] dniTypes = new String[] {"CC", "TI", "PS", "CE"};
	private JComboBox<String> typeDNIBox;
	private boolean dniInputB, nombresInputB, apellidosInputB, userInputB = false;
	private JButton btnUpdate;
	private JTextField userTextField;
	private JLabel lbluserEmail;
	private String dniPersona;
	private String [][] personData = null;

	/**
	 * Launch the application.
	 */
	public static void initialize(String dniPerson) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdatePersonFrame frame = new UpdatePersonFrame(dniPerson);
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
	public UpdatePersonFrame(String dniPerson) {
		
		String personsTable = "personas_unal_uapa";
		if(dniPerson.length() != 0) {
			String[][] data = AuthenticationFrame.consult.getSomeFromTable("dni_persona", dniPerson, personsTable);
			//removal of the first row containing the names of the columns
			int r= data.length;
	        int c= data[0].length;
	        personData = new String [r-1][c];
	        for(int k = 0; k < r-1; k++ ) {
	        	for (int l = 0; l < c; l++) {
	        		personData[k][l] = data[k+1][l];
				}
	        }
	        System.out.println(personData.length + " " + personData[0].length);
	        for (int i = 0; i < personData.length; i++) {
				for (int j = 0; j < personData[0].length; j++) {
					System.out.println(personData[i][j].toString());
				}
			}
		}
		
		setTitle("Actualizar Persona");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 304, 295);
		setLocationRelativeTo(null); //centers the frame
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombres = new JLabel("Nombres: ");
		lblNombres.setBounds(23, 81, 62, 14);
		contentPane.add(lblNombres);
		
		JLabel lblDni = new JLabel("DNI: ");
		lblDni.setBounds(23, 45, 62, 14);
		contentPane.add(lblDni);
		
		JLabel lblApellidos = new JLabel("Apellidos: ");
		lblApellidos.setBounds(23, 120, 62, 14);
		contentPane.add(lblApellidos);
		
		btnUpdate = new JButton("Actualizar");
		btnUpdate.setBounds(95, 204, 97, 23);
		btnUpdate.setEnabled(false);
		btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String useremail = userTextField.getText().toString()+"@unal.edu.co";
				Person person = new Person(dniPerson,dniInput.getText().toString(),typeDNIBox.getSelectedItem().toString(),
						nombresInput.getText().toString(), apellidosInput.getText().toString(), useremail);
				AuthenticationFrame.consult.updatePerson(person);
			}
		});
		
		contentPane.add(btnUpdate);
		
		dniInput = new JTextField();
		dniInput.setBounds(95, 45, 183, 20);
		dniInput.setFocusable(true);
		dniInput.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				dniInputB = true;
				if(dniInputB && nombresInputB && apellidosInputB && userInputB) {
					btnUpdate.setEnabled(true);
				}
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		contentPane.add(dniInput);
		dniInput.setColumns(10);
		
		nombresInput = new JTextField();
		nombresInput.setColumns(10);
		nombresInput.setBounds(95, 81, 183, 20);
		nombresInput.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				nombresInputB = true;
				if(dniInputB && nombresInputB && apellidosInputB && userInputB) {
					btnUpdate.setEnabled(true);
				}
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		contentPane.add(nombresInput);
		
		apellidosInput = new JTextField();
		apellidosInput.setColumns(10);
		apellidosInput.setBounds(95, 120, 183, 20);
		apellidosInput.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				apellidosInputB = true;
				if(dniInputB && nombresInputB && apellidosInputB && userInputB) {
					btnUpdate.setEnabled(true);
				}
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		contentPane.add(apellidosInput);
		
		typeDNIBox = new JComboBox<String>();
		typeDNIBox.setBounds(95, 14, 46, 20);
		typeDNIBox.setModel(new DefaultComboBoxModel<String>(dniTypes));
		contentPane.add(typeDNIBox);
		
		typeDNILabel = new JLabel("Tipo DNI:");
		typeDNILabel.setBounds(23, 14, 62, 14);
		contentPane.add(typeDNILabel);
		
		
		this.getRootPane().setDefaultButton(btnUpdate);
		
		lbluserEmail = new JLabel("Usuario:");
		lbluserEmail.setBounds(23, 162, 62, 14);
		contentPane.add(lbluserEmail);
		
		userTextField = new JTextField();
		userTextField.setBounds(95, 159, 183, 20);
		userTextField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				userInputB = true;
				if(dniInputB && nombresInputB && apellidosInputB && userInputB) {
					btnUpdate.setEnabled(true);
				}
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		contentPane.add(userTextField);
		userTextField.setColumns(10);
		userTextField.setToolTipText("usuario sin '@unal.edu.co'");
		KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0,false);
		Action escapeAction = new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
			}
			
		};
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "ESCAPE");
		getRootPane().getActionMap().put("ESCAPE", escapeAction);
		
		fillFields();
	}

	public String getDniPersona() {
		return dniPersona;
	}

	public void setDniPersona(String dniPersona) {
		this.dniPersona = dniPersona;
	}
	
	
	/**
	 * populates the jframe fields according to the data obtained in the CosnsolRecEstFrame jframe
	 * follows the next order: six columns, one row (dni, type, names,last names, fullname, userunal)
	 */
	public void fillFields() {
		if(personData != null) {
			
			String[] splitted = personData[0][5].toString().split("@");
			dniInput.setText(personData[0][0].toString());
			typeDNIBox.setSelectedItem(personData[0][1].toString());
			nombresInput.setText(personData[0][2].toString());
			apellidosInput.setText(personData[0][3].toString());
			userTextField.setText(splitted[0]);
			btnUpdate.setEnabled(true);
			
		}
	}
}
