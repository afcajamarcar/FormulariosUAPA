package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.border.*;

public class ConsolRecEstFrame extends JFrame {

	private static final long serialVersionUID = 2L;
	private JPanel contentPane;
	private JTextField dniInput;
	private JTable table;
	private JComboBox<Object> programasComboBox;
	private JComboBox<Object> periodComboBox;

	/**
	 * Launch the application.
	 */
	public void initialize() {
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
		lblProgramas.setBounds(10, 86, 66, 14);
		contentPane.add(lblProgramas);
		lblProgramas.setFont(new Font("Calibri", Font.PLAIN, 11));
		
		programasComboBox = new JComboBox<Object>();
		programasComboBox.setBounds(79, 83, 213, 20);
		contentPane.add(programasComboBox);
		programasComboBox.setFont(new Font("Calibri", Font.PLAIN, 11));
		programasComboBox.setModel(new DefaultComboBoxModel<Object>(AuthenticationFrame.consult.getProgram()));
		
		dniInput = new JTextField();
		dniInput.setBounds(79, 52, 131, 20);
		contentPane.add(dniInput);
		dniInput.setFont(new Font("Calibri", Font.PLAIN, 11));
		dniInput.setColumns(10);
		dniInput.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyChar() == KeyEvent.VK_ENTER) {
					if(dniInput.getText().toString().length() == 0) {
						JOptionPane.showMessageDialog(null, "El documento de identificacion no puede estar vacio");
					}else {
						AuthenticationFrame.consult.getProgramForConsol(dniInput.getText().toString(),periodComboBox.getSelectedItem().toString());
					}
		
				}
				
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JLabel lblDni = new JLabel("DNI: ");
		lblDni.setBounds(10, 55, 25, 14);
		contentPane.add(lblDni);
		lblDni.setFont(new Font("Calibri", Font.PLAIN, 11));
		
		periodComboBox = new JComboBox<Object>();
		periodComboBox.setFont(new Font("Calibri", Font.PLAIN, 11));
		periodComboBox.setBounds(79, 21, 131, 20);
		contentPane.add(periodComboBox);
		periodComboBox.setModel(new DefaultComboBoxModel<Object>(AuthenticationFrame.consult.getPeriod()));
		
		JLabel lblPeriodo = new JLabel("Periodo: ");
		lblPeriodo.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblPeriodo.setBounds(10, 21, 46, 14);
		contentPane.add(lblPeriodo);
		
		JPanel panel = new JPanel();
		panel.setBounds(307, 166, 300, 165);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 597, 165);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
