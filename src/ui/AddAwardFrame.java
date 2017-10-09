package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.TextField;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class AddAwardFrame extends JFrame {

	private JPanel contentPane;
	private JTextField InputTipRec;
	private JTextField InputNomRec;
	private JTextField InputInstRec;
	private JLabel lblcodRecnocimientos;
	private JTextField codReconocimientoTextField;
	private JButton btnaddCountryButton;
	private String[] ambitos = new String[] {"Seleccionar...", "Nacional", "Internacional", "Universidad", "Otro"};
	private JComboBox comboAmbRec;
	private String newAmbito;
	private JTextField otroTextField;
	private JLabel lblOtroLabel;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 469);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));
		
		lblcodRecnocimientos = new JLabel("cod_reconocimiento");
		contentPane.add(lblcodRecnocimientos);
		
		codReconocimientoTextField = new JTextField();
		contentPane.add(codReconocimientoTextField);
		codReconocimientoTextField.setColumns(10);
		
		JLabel lblTipRec = new JLabel("tipo_reconocimiento:");
		contentPane.add(lblTipRec);
		
		InputTipRec = new JTextField();
		contentPane.add(InputTipRec);
		InputTipRec.setColumns(10);
		
		JLabel lblNomRec = new JLabel("nobre_reconocimiento:");
		contentPane.add(lblNomRec);
		
		InputNomRec = new JTextField();
		InputNomRec.setColumns(10);
		contentPane.add(InputNomRec);
		
		JLabel lblAmbRec = new JLabel("ambito_reconocimiento:");
		contentPane.add(lblAmbRec);
		
		comboAmbRec = new JComboBox();
		comboAmbRec.setModel(new DefaultComboBoxModel(ambitos));
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
				comboAmbRec.setModel(new DefaultComboBoxModel(aux));
				
			}
		});
		
		JLabel lblCaracter = new JLabel("caracter: ");
		contentPane.add(lblCaracter);
		
		JComboBox comboCarac = new JComboBox();
		comboCarac.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar...", "Interna", "Externa"}));
		contentPane.add(comboCarac);
		
		JLabel lblInstitucinreconocimeinto = new JLabel("instituci\u00F3n_reconocimeinto:");
		contentPane.add(lblInstitucinreconocimeinto);
		
		InputInstRec = new JTextField();
		InputInstRec.setColumns(10);
		contentPane.add(InputInstRec);
		
		JLabel lblPaisinstitucin = new JLabel("pais_instituci\u00F3n:");
		contentPane.add(lblPaisinstitucin);
		
		JComboBox comboPaisIns = new JComboBox();
		comboPaisIns.setModel(new DefaultComboBoxModel<String>(AuthenticationFrame.consult.getCountry()));
		contentPane.add(comboPaisIns);
		
		JLabel label = new JLabel("");
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("");
		contentPane.add(label_1);
		
		btnaddCountryButton = new JButton("Añadir");
		btnaddCountryButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AuthenticationFrame.consult.getCountry();
				
			}
		});

		contentPane.add(btnaddCountryButton);
	}
	
	
	public String getNewAmbito() {
		return newAmbito;
	}

	public void setNewAmbito(String newAmbito) {
		this.newAmbito = newAmbito;
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
