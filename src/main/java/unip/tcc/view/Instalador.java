package unip.tcc.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.springframework.scheduling.annotation.Async;

public class Instalador extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	
	private ButtonGroup grupo1;

	/**
	 * Launch the application.
	 */
	@Async
	public static void load() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Instalador frame = new Instalador();
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
	public Instalador() {
		setTitle("Instalador");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdDoEquipamento = new JLabel("Id do Equipamento:");
		lblIdDoEquipamento.setBounds(106, 12, 118, 17);
		contentPane.add(lblIdDoEquipamento);
		
		textField = new JTextField();
		textField.setBounds(229, 10, 114, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblIdDoEquipamento_1 = new JLabel("IP JMS:");
		lblIdDoEquipamento_1.setBounds(106, 49, 118, 17);
		contentPane.add(lblIdDoEquipamento_1);
		
		JLabel lblIdDoEquipamento_2 = new JLabel("SSID da Rede:");
		lblIdDoEquipamento_2.setBounds(106, 91, 118, 17);
		contentPane.add(lblIdDoEquipamento_2);
		
		JLabel lblIdDoEquipamento_3 = new JLabel("Senha da Rede:");
		lblIdDoEquipamento_3.setBounds(106, 138, 118, 17);
		contentPane.add(lblIdDoEquipamento_3);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(229, 47, 114, 21);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(229, 89, 114, 21);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(229, 136, 114, 21);
		contentPane.add(textField_3);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(65, 232, 105, 27);
		contentPane.add(btnReset);
		
		JButton btnReset_1 = new JButton("Salvar");
		btnReset_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnReset_1.setBounds(229, 232, 105, 27);
		contentPane.add(btnReset_1);
		
		JLabel lblIdDoEquipamento_3_1 = new JLabel("Trifásico:");
		lblIdDoEquipamento_3_1.setBounds(106, 177, 118, 17);
		contentPane.add(lblIdDoEquipamento_3_1);
		
		JRadioButton rdbtnAtivado = new JRadioButton("Ativado");
		rdbtnAtivado.setBounds(217, 173, 130, 25);
		contentPane.add(rdbtnAtivado);
		
		JRadioButton rdbtnDesativado = new JRadioButton("Desativado");
		rdbtnDesativado.setSelected(true);
		rdbtnDesativado.setBounds(217, 199, 130, 25);
		
		grupo1 = new ButtonGroup();
		  grupo1.add(rdbtnAtivado);
		  grupo1.add(rdbtnDesativado);
		contentPane.add(rdbtnDesativado);
	}
}
