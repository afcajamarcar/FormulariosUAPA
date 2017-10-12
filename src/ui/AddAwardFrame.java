package ui;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;

public class AddAwardFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField InputTipRec;
	private JTextField InputNomRec;
	private JTextField InputInstRec;
	private JLabel lblcodRecnocimientos;
	private JTextField codReconocimientoTextField;
	private JButton btnaddAwardButton;
	private String[] ambitos = new String[] {"Seleccionar...", "Nacional", "Internacional", "Universidad", "Otro"};
	private JComboBox<String> comboAmbRec;
	private String newAmbito;
	private JTextField otroTextField;
	private JLabel lblOtroLabel;
	private JComboBox<String> comboCarac;
	private JComboBox<String> comboPaisIns;
	
	private boolean codReconocimientoTextFieldB = false;
	private boolean InputTipRecB = false;
	private boolean InputNomRecB = false;
	private boolean InputInstRecB = false;
	
	
	/**
	 * Launch the application.
	 */
	public void initialize() {
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
		setTitle("A\u00F1adir Reconocimiento");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 469);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));
		setLocationRelativeTo(null);
		
		lblcodRecnocimientos = new JLabel("cod_reconocimiento");
		contentPane.add(lblcodRecnocimientos);
		
		codReconocimientoTextField = new JTextField();
		codReconocimientoTextField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				codReconocimientoTextFieldB = true;
				
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
		contentPane.add(codReconocimientoTextField);
		codReconocimientoTextField.setColumns(10);
		
		JLabel lblTipRec = new JLabel("tipo_reconocimiento:");
		contentPane.add(lblTipRec);
		
		InputTipRec = new JTextField();
		InputTipRec.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				InputTipRecB = true;
				
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
		contentPane.add(InputTipRec);
		InputTipRec.setColumns(10);
		
		JLabel lblNomRec = new JLabel("nobre_reconocimiento:");
		contentPane.add(lblNomRec);
		
		InputNomRec = new JTextField();
		InputNomRec.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				InputNomRecB = true;
				
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
		InputNomRec.setColumns(10);
		contentPane.add(InputNomRec);
		
		JLabel lblAmbRec = new JLabel("ambito_reconocimiento:");
		contentPane.add(lblAmbRec);
		
		comboAmbRec = new JComboBox<String>();
		comboAmbRec.setModel(new DefaultComboBoxModel<String>(AuthenticationFrame.consult.getAmbito()));
		comboAmbRec.addItemListener(new ItemChangeListener());
		
		contentPane.add(comboAmbRec);
		
		lblOtroLabel = new JLabel("otro:");
		lblOtroLabel.setVisible(false);
		contentPane.add(lblOtroLabel);
		
		otroTextField = new JTextField();
		otroTextField.setVisible(false);
		contentPane.add(otroTextField);
		otroTextField.setColumns(10);
		otroTextField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				/**
				 * 
				 
				String[] aux = new String[ambitos.length+1];
				for (int i = 0; i < aux.length; i++) {
					if(i < ambitos.length) {
						aux[i] = ambitos[i];
					}else {
						aux[i] = "Otro";
					}
					if(i == ambitos.length-1) {
						aux[i] = otroTextField.getText();
					}
				}
				
				comboAmbRec.setModel(new DefaultComboBoxModel<String>(aux));
				*/
			}
		});
		
		JLabel lblCaracter = new JLabel("caracter: ");
		contentPane.add(lblCaracter);
		
		comboCarac = new JComboBox<String>();
		comboCarac.setModel(new DefaultComboBoxModel<String>(AuthenticationFrame.consult.getCaracter()));
		contentPane.add(comboCarac);
		
		JLabel lblInstitucinreconocimeinto = new JLabel("instituci\u00F3n_reconocimeinto:");
		contentPane.add(lblInstitucinreconocimeinto);
		
		InputInstRec = new JTextField();
		InputInstRec.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				InputInstRecB = true;
				if(codReconocimientoTextFieldB && InputTipRecB && InputNomRecB && InputInstRecB) {
					btnaddAwardButton.setEnabled(true);
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
		InputInstRec.setColumns(10);
		contentPane.add(InputInstRec);
		
		JLabel lblPaisinstitucin = new JLabel("pais_instituci\u00F3n:");
		contentPane.add(lblPaisinstitucin);
		
		comboPaisIns = new JComboBox<String>();
		comboPaisIns.setModel(new DefaultComboBoxModel<String>(AuthenticationFrame.consult.getCountry()));
		contentPane.add(comboPaisIns);
		
		JLabel label = new JLabel("");
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("");
		contentPane.add(label_1);
		
		btnaddAwardButton = new JButton("A�adir");
		btnaddAwardButton.setEnabled(false);
		btnaddAwardButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!otroTextField.getText().isEmpty()) {
					AuthenticationFrame.consult.addAward(codReconocimientoTextField.getText(), InputTipRec.getText(),
							InputNomRec.getText(), otroTextField.getText(), comboCarac.getSelectedItem().toString(),
							InputInstRec.getText(), splitCountry(comboPaisIns.getSelectedItem().toString()));
				}else {
					AuthenticationFrame.consult.addAward(codReconocimientoTextField.getText(), InputTipRec.getText(),
							InputNomRec.getText(), comboAmbRec.getSelectedItem().toString(), comboCarac.getSelectedItem().toString(),
							InputInstRec.getText(), splitCountry(comboPaisIns.getSelectedItem().toString()));
				} 
			}
		});

		contentPane.add(btnaddAwardButton);
		this.getRootPane().setDefaultButton(btnaddAwardButton);
		KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0,false);
		Action escapeAction = new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
			}
			
		};
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "ESCAPE");
		getRootPane().getActionMap().put("ESCAPE", escapeAction);

	}
	
	
	public String getNewAmbito() {
		return newAmbito;
	}

	public void setNewAmbito(String newAmbito) {
		this.newAmbito = newAmbito;
	}

	public String splitCountry(String countryPlusId) {
		String country;
		country =countryPlusId.split("-")[0];
		return country;
	}
	
	class ItemChangeListener implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent event) {
			if (event.getStateChange() == ItemEvent.SELECTED) {
				if(event.getItem().toString() == "Otro") {
					otroTextField.setVisible(true);
					lblOtroLabel.setVisible(true);
					lblOtroLabel.setOpaque(true);
					lblOtroLabel.setBackground(Color.gray);
					
		          }
		    }
		}
	}
}
