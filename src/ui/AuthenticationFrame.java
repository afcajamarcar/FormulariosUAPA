package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import mysql.Querys;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class AuthenticationFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField paswdInput;
	private JTextField userInput;
	private JButton btnLogIn;
	private static AuthenticationFrame frame;
	static Querys consult;
	
	public void initialize() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new AuthenticationFrame();
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
	public AuthenticationFrame() {
		setFont(new Font("Calibri", Font.PLAIN, 11));
		setTitle("Autenticacion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 243, 215);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario: ");
		lblUsuario.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblUsuario.setBounds(10, 11, 211, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a: ");
		lblContrasea.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblContrasea.setBounds(10, 67, 211, 14);
		contentPane.add(lblContrasea);
		
		paswdInput = new JPasswordField();
		paswdInput.setFont(new Font("Calibri", Font.PLAIN, 11));
		paswdInput.setBounds(10, 92, 211, 20);
		contentPane.add(paswdInput);
		paswdInput.setColumns(10);
		
		userInput = new JTextField();
		userInput.setFont(new Font("Calibri", Font.PLAIN, 11));
		userInput.setBounds(10, 36, 211, 20);
		contentPane.add(userInput);
		userInput.setColumns(10);
		
		btnLogIn = new JButton("Acceder");
		btnLogIn.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consult = new Querys();
				consult.connect(userInput.getText(), paswdInput.getText());
				GetSomeFromFrame getSomeFromFrame = new GetSomeFromFrame();
				getSomeFromFrame.initialize();
				frame.setVisible(false);
				
			}
		});
		btnLogIn.setBounds(67, 142, 89, 23);
		contentPane.add(btnLogIn);
	}

}
