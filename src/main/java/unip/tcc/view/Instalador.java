package unip.tcc.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import unip.tcc.core.Comandos;
import unip.tcc.service.ComunicacaoSerial;
import unip.tcc.view.log.TextAreaOutputStream;
import unip.tcc.view.log.TextAreaOutputStreamPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Instalador extends JFrame {

	private ComunicacaoSerial serial;

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	private ButtonGroup grupo1;
	private ButtonGroup grupo2;
	
	 private JTextArea textArea = new JTextArea(15, 30);
	   private TextAreaOutputStream taOutputStream = new TextAreaOutputStream(
	         textArea, "Log");

	/**
	 * Launch the application.
	 */
	@Async
	public static void load(ComunicacaoSerial serial) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Instalador frame = new Instalador(serial);
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
	public Instalador(ComunicacaoSerial serial) {
		this.serial = serial;
		setTitle("Instalador");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 520);
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
		lblIdDoEquipamento_3.setBounds(106, 135, 118, 17);
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
		textField_3.setBounds(229, 133, 114, 21);
		contentPane.add(textField_3);

		JLabel lblIdDoEquipamento_3_1 = new JLabel("Rede:");
		lblIdDoEquipamento_3_1.setBounds(106, 177, 118, 17);
		contentPane.add(lblIdDoEquipamento_3_1);

		JRadioButton rdbtnAtivado = new JRadioButton("Monofásica");
		rdbtnAtivado.setBounds(217, 173, 130, 25);
		contentPane.add(rdbtnAtivado);

		JRadioButton rdbtnDesativado = new JRadioButton("Bifásica");
		rdbtnDesativado.setSelected(true);
		rdbtnDesativado.setBounds(217, 199, 130, 25);

		grupo1 = new ButtonGroup();
		grupo1.add(rdbtnAtivado);
		grupo1.add(rdbtnDesativado);
		contentPane.add(rdbtnDesativado);

		JLabel lblIdDoEquipamento_3_1_1 = new JLabel("Neutro:");
		lblIdDoEquipamento_3_1_1.setBounds(106, 232, 118, 17);
		contentPane.add(lblIdDoEquipamento_3_1_1);

		JRadioButton rdbtnAtivado_1 = new JRadioButton("Ativado");
		rdbtnAtivado_1.setBounds(217, 228, 130, 25);
		contentPane.add(rdbtnAtivado_1);

		JRadioButton rdbtnAtivado_2 = new JRadioButton("Desativado");
		rdbtnAtivado_2.setSelected(true);
		rdbtnAtivado_2.setBounds(217, 257, 130, 25);
		contentPane.add(rdbtnAtivado_2);

		grupo2 = new ButtonGroup();
		grupo2.add(rdbtnAtivado_1);
		grupo2.add(rdbtnAtivado_2);

		JLabel lblComandoEnviado = new JLabel("Comando enviado");
		lblComandoEnviado.setVisible(false);
		lblComandoEnviado.setBounds(249, 362, 114, 17);
		contentPane.add(lblComandoEnviado);

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Comandos command = new Comandos();
				command.setComand("R");
				serial.sendCommand(command);
				lblComandoEnviado.setVisible(true);
			}
		});
		btnReset.setBounds(84, 315, 105, 27);
		contentPane.add(btnReset);

		JButton btnReset_1 = new JButton("Salvar");
		btnReset_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Comandos command = new Comandos();
				command.setEquipmentId(textField.getText());
				command.setIpJMS(textField_1.getText());
				command.setSSID(textField_2.getText());
				command.setSenhaRede(textField_3.getText());
				command.setComand("N");
				command.setIsEnabledSystem("1");
				command.setNeutroAtivo("1");
				command.setTipoRede("M");

				serial.sendCommand(command);
				lblComandoEnviado.setVisible(true);
			}
		});
		btnReset_1.setBounds(406, 315, 105, 27);
		contentPane.add(btnReset_1);
		
		JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
	            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(23, 391, 565, 88);
		contentPane.add(scrollPane);
		System.setOut(new PrintStream(taOutputStream));

	      int timerDelay = 1000;
	      new Timer(timerDelay , new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent arg0) {

	            // though this outputs via System.out.println, it actually displays
	            // in the JTextArea:
	         }
	      }).start();
	}
}
