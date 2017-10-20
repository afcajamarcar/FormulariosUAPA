package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;


import javax.swing.*;
import javax.swing.border.*;

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

	/**
	 * Launch the application.
	 */
	public void initialize(String dniPersona) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setDniPersona(dniPersona);
					UpdatePersonFrame frame = new UpdatePersonFrame();
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
	public UpdatePersonFrame() {
		
		String personsTable = "personas_unal_uapa";
		if(getDniPersona().length() != 0) {
			String[][] data = AuthenticationFrame.consult.getSomeFromTable("dni_persona", getDniPersona(), personsTable);
			   System.out.println(data.length);
		        for (int i = 0; i < data.length; i++) {
					for (int j = 0; j < data[0].length; j++) {
						System.out.println(data[i][j]);
					}
				}
		        //System.out.println(temp.length);
		        System.out.println(data[0][0].toString());
		}
		
		//int r= data.length;
        //int c= data[0].length;
        //String [][] temp = new String [r-1][c];
        //for(int k = 0; k < r-1; k++ ) {
        //	for (int l = 0; l < c; l++) {
        //		temp[k][l] = data[k+1][l];
		//	}
        //}
     
		
        
		
		setTitle("A\u00F1adir Persona");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 304, 283);
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
		btnUpdate.setBounds(96, 210, 89, 23);
		btnUpdate.setEnabled(false);
		btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String aux = null;
				String[] splitted = userTextField.getText().split("@"); 
				int t = splitted.length;
				if( t != 0) {
					aux = splitted[0]; 
					AuthenticationFrame.consult.addPerson(dniInput.getText(), typeDNIBox.getSelectedItem().toString(), nombresInput.getText(), apellidosInput.getText(), aux);
				}else {
					AuthenticationFrame.consult.addPerson(dniInput.getText(), typeDNIBox.getSelectedItem().toString(), nombresInput.getText(), apellidosInput.getText(), userTextField.getText());
				}
				
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
		//typeDNIBox.setSelectedItem( temp[0][0]);
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
	}

	public String getDniPersona() {
		return dniPersona;
	}

	public void setDniPersona(String dniPersona) {
		this.dniPersona = dniPersona;
	}
	
}
