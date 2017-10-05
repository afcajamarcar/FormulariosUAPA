package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.border.*;

public class AddPersonFrame extends JFrame {

	private static final long serialVersionUID = 3L;
	private JPanel contentPane;
	private JTextField dniInput;
	private JTextField nombresInput;
	private JTextField apellidosInput;
	private JLabel typeDNILabel;
	private String[] dniTypes = new String[] {"CC", "TI", "PS"};
	private JComboBox<String> typeDNIBox;
	private boolean dniInputB, nombresInputB, apellidosInputB = false;
	private JButton btnAadir;

	/**
	 * Launch the application.
	 */
	public void initialize() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPersonFrame frame = new AddPersonFrame();
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
	public AddPersonFrame() {
		setTitle("A\u00F1adir Persona");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 304, 236);
		setLocationRelativeTo(null); //centers the frame
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombres = new JLabel("Nombres: ");
		lblNombres.setBounds(23, 82, 62, 14);
		contentPane.add(lblNombres);
		
		JLabel lblDni = new JLabel("DNI: ");
		lblDni.setBounds(23, 45, 62, 14);
		contentPane.add(lblDni);
		
		JLabel lblApellidos = new JLabel("Apellidos: ");
		lblApellidos.setBounds(23, 119, 62, 14);
		contentPane.add(lblApellidos);
		
		btnAadir = new JButton("A\u00F1adir");
		btnAadir.setBounds(96, 161, 89, 23);
		btnAadir.setEnabled(false);
		btnAadir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AuthenticationFrame.consult.addPerson(dniInput.getText(), typeDNIBox.getSelectedItem().toString(), nombresInput.getText(), apellidosInput.getText());
			}
		});
		contentPane.add(btnAadir);
		
		dniInput = new JTextField();
		dniInput.setBounds(95, 45, 183, 20);
		dniInput.setFocusable(true);
		dniInput.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				dniInputB = true;
				if(dniInputB && nombresInputB && apellidosInputB) {
					btnAadir.setEnabled(true);
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
		nombresInput.setBounds(95, 82, 183, 20);
		nombresInput.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				nombresInputB = true;
				if(dniInputB && nombresInputB && apellidosInputB) {
					btnAadir.setEnabled(true);
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
		apellidosInput.setBounds(95, 119, 183, 20);
		apellidosInput.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				apellidosInputB = true;
				if(dniInputB && nombresInputB && apellidosInputB) {
					btnAadir.setEnabled(true);
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
		
		
		this.getRootPane().setDefaultButton(btnAadir);
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println(e.getKeyCode());
				if(e.getKeyCode() == e.VK_ESCAPE) {
					dispose();
				}
			}
		});
	}
}
