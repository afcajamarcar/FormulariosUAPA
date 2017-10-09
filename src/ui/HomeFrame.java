package ui;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.Font;
import java.awt.event.*;

public class HomeFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static HomeFrame frame;

	/**
	 * Launch the application.
	 */
	public static void initialize() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new HomeFrame();
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
	public HomeFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 237);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JButton btnGetSome = new JButton("<html><center>"+"Consulta especifica Tabla"+"</center></html>");
		btnGetSome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GetSomeFromFrame.initialize();
				frame.setVisible(false);
			}
		});
		btnGetSome.setFont(new Font("Calibri Light", Font.PLAIN, 11));
		btnGetSome.setBounds(294, 105, 130, 47);
		contentPane.add(btnGetSome);
		
		JButton btnGetAll = new JButton("<html><center>"+"Consulta Tabla"+"</center></html>");
		btnGetAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GetAllFromFrame.initialize();
				frame.setVisible(false);
			}
		});
		btnGetAll.setFont(new Font("Calibri Light", Font.PLAIN, 11));
		btnGetAll.setBounds(294, 36, 130, 47);
		contentPane.add(btnGetAll);
		
		ImageIcon imageIcon = new ImageIcon(HomeFrame.class.getResource("/resources/UAPA_LOGO.png"));
		Image image = imageIcon.getImage().getScaledInstance(270, 150,  java.awt.Image.SCALE_SMOOTH); 
		imageIcon = new ImageIcon(image);
		JLabel lblImage = new JLabel(imageIcon);
		lblImage.setBounds(10, 11, 274, 150);
		contentPane.add(lblImage);
		
		
	}
}
