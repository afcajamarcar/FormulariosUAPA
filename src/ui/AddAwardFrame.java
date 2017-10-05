package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddAwardFrame extends JFrame {

	private JPanel contentPane;
	private JTextField InputTipRec;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddAwardFrame frame = new AddAwardFrame();
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
	public AddAwardFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblTipRec = new JLabel("tipo_reconocimiento:");
		contentPane.add(lblTipRec);
		
		InputTipRec = new JTextField();
		contentPane.add(InputTipRec);
		InputTipRec.setColumns(10);
		
		JLabel lblNomRec = new JLabel("nobre_reconocimiento:");
		contentPane.add(lblNomRec);
		
		textField = new JTextField();
		textField.setColumns(10);
		contentPane.add(textField);
		
		JLabel lblAmbRec = new JLabel("ambito_reconocimiento:");
		contentPane.add(lblAmbRec);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		contentPane.add(textField_1);
		
		JLabel lblCaracter = new JLabel("caracter: ");
		contentPane.add(lblCaracter);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		contentPane.add(textField_2);
		
		JLabel lblInstitucinreconocimeinto = new JLabel("instituci\u00F3n_reconocimeinto:");
		contentPane.add(lblInstitucinreconocimeinto);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		contentPane.add(textField_3);
		
		JLabel lblPaisinstitucin = new JLabel("pais_instituci\u00F3n:");
		contentPane.add(lblPaisinstitucin);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		contentPane.add(textField_4);
		
		JLabel label = new JLabel("");
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("");
		contentPane.add(label_1);
		
		JButton btnNewButton = new JButton("Añadir");

		contentPane.add(btnNewButton);
	}
}
