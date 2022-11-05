package unip.tcc.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import org.springframework.scheduling.annotation.Async;

import unip.tcc.core.Comandos;
import unip.tcc.entities.Configuracoes;
import unip.tcc.repository.ConfiguracoesRepository;
import unip.tcc.service.ComunicacaoSerial;
import unip.tcc.view.log.TextAreaOutputStream;
import unip.tcc.view.log.TextAreaOutputStreamPanel;

public class Instalador extends JFrame {

    @SuppressWarnings("unused")
    private ComunicacaoSerial serial;

    private Configuracoes configDefault = new Configuracoes();

    private JPanel contentPane;
    private JTextField fieldIdDoEquipamento;
    private JTextField fieldIpJMS;
    private JTextField fieldSsidRede;
    private JTextField fieldPassword;

    private ButtonGroup tipoRede;
    private ButtonGroup enableNeutro;
    private ButtonGroup enableTerra;

    private JTextArea textArea = new JTextArea(15, 30);
    private TextAreaOutputStream taOutputStream = new TextAreaOutputStream(
            textArea, "Log");

    /**
     * Launch the application.
     */
    @Async
    public static void load(ComunicacaoSerial serial, ConfiguracoesRepository configuracoesRepository) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Instalador frame = new Instalador(serial, configuracoesRepository);
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
    public Instalador(ComunicacaoSerial serial, ConfiguracoesRepository configuracoesRepository) {
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

        JLabel lblIpJMS = new JLabel("IP JMS:");
        lblIpJMS.setBounds(106, 49, 118, 17);
        contentPane.add(lblIpJMS);

        JLabel lblSsidRede = new JLabel("SSID da Rede:");
        lblSsidRede.setBounds(106, 91, 118, 17);
        contentPane.add(lblSsidRede);

        JLabel lblPassword = new JLabel("Senha da Rede:");
        lblPassword.setBounds(106, 135, 118, 17);
        contentPane.add(lblPassword);

        fieldIdDoEquipamento = new JTextField();
        fieldIdDoEquipamento.setBounds(229, 10, 291, 21);
        contentPane.add(fieldIdDoEquipamento);
        fieldIdDoEquipamento.setColumns(10);

        fieldIpJMS = new JTextField();
        fieldIpJMS.setColumns(10);
        fieldIpJMS.setBounds(229, 47, 291, 21);
        contentPane.add(fieldIpJMS);

        fieldSsidRede = new JTextField();
        fieldSsidRede.setColumns(10);
        fieldSsidRede.setBounds(229, 89, 291, 21);
        contentPane.add(fieldSsidRede);

        fieldPassword = new JTextField();
        fieldPassword.setColumns(10);
        fieldPassword.setBounds(229, 133, 291, 21);
        contentPane.add(fieldPassword);

        JLabel lblTipoRede = new JLabel("Rede:");
        lblTipoRede.setBounds(111, 177, 49, 17);
        contentPane.add(lblTipoRede);

        JRadioButton tipoRedeM = new JRadioButton("Monofásica");
        tipoRedeM.setBounds(174, 173, 93, 25);
        contentPane.add(tipoRedeM);

        JRadioButton tipoRedeB = new JRadioButton("Bifásica");
        tipoRedeB.setBounds(174, 202, 93, 25);

        JRadioButton tipoRedeT = new JRadioButton("Trifásica");
        tipoRedeT.setBounds(174, 231, 93, 25);
        contentPane.add(tipoRedeT);

        tipoRede = new ButtonGroup();
        tipoRede.add(tipoRedeM);
        tipoRede.add(tipoRedeB);
        tipoRede.add(tipoRedeT);
        contentPane.add(tipoRedeB);

        JLabel lblNeutro = new JLabel("Neutro:");
        lblNeutro.setBounds(347, 251, 61, 17);
        contentPane.add(lblNeutro);

        JRadioButton neutroAtivo = new JRadioButton("Ativado");
        neutroAtivo.setBounds(422, 247, 98, 25);
        contentPane.add(neutroAtivo);

        JRadioButton neutroDesativado = new JRadioButton("Desativado");
        neutroDesativado.setBounds(422, 276, 98, 25);
        contentPane.add(neutroDesativado);

        enableNeutro = new ButtonGroup();
        enableNeutro.add(neutroAtivo);
        enableNeutro.add(neutroDesativado);

        JLabel lblTerra = new JLabel("Terra:");
        lblTerra.setBounds(347, 177, 61, 17);
        contentPane.add(lblTerra);

        JRadioButton terraAtivo = new JRadioButton("Ativado");
        terraAtivo.setBounds(422, 173, 98, 25);
        contentPane.add(terraAtivo);

        JRadioButton terraDesativado = new JRadioButton("Desativado");
        terraDesativado.setBounds(422, 202, 98, 25);
        contentPane.add(terraDesativado);

        enableTerra = new ButtonGroup();
        enableTerra.add(terraAtivo);
        enableTerra.add(terraDesativado);

//        JLabel lblComandoEnviado = new JLabel("Comando enviado");
//        lblComandoEnviado.setVisible(false);
//        lblComandoEnviado.setBounds(249, 362, 114, 17);
//        contentPane.add(lblComandoEnviado);

        JButton btnReset = new JButton("Reset");
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Comandos command = new Comandos();
                command.setComand("R");
                serial.sendCommand(command);
                JOptionPane.showMessageDialog(contentPane, "Comando Enviado");
                configDefault.setEquipmentId("");
                configDefault.setIpJMS("");
                configDefault.setSSID("");
                configDefault.setSenhaRede("");
                if (neutroAtivo.isSelected()) {
                    configDefault.setNeutroAtivo("1");
                } else {
                    configDefault.setNeutroAtivo("0");
                }
                if (tipoRedeB.isSelected()) {
                    configDefault.setTipoRede("B");
                } else if (tipoRedeM.isSelected()) {
                    configDefault.setTipoRede("M");
                }else {
                    configDefault.setTipoRede("T");
                }
                if (terraAtivo.isSelected()) {
                    configDefault.setTerraAtivo("1");
                } else {
                    configDefault.setTerraAtivo("0");
                }
                configDefault = configuracoesRepository.save(configDefault);
            }
        });
        btnReset.setBounds(94, 323, 105, 27);
        contentPane.add(btnReset);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Comandos command = new Comandos();
                command.setEquipmentId(fieldIdDoEquipamento.getText());
                command.setIpJMS(fieldIpJMS.getText());
                command.setSSID(fieldSsidRede.getText());
                command.setSenhaRede(fieldPassword.getText());
                command.setComand("N");
                command.setIsEnabledSystem("1");
                if (neutroAtivo.isSelected()) {
                    command.setNeutroAtivo("1");
                } else {
                    command.setNeutroAtivo("0");
                }
                if (tipoRedeB.isSelected()) {
                    command.setTipoRede("B");
                } else if (tipoRedeM.isSelected()) {
                    command.setTipoRede("M");
                }else {
                    command.setTipoRede("T");
                }
                if (terraAtivo.isSelected()) {
                    command.setTerraAtivo("1");
                } else {
                    command.setTerraAtivo("0");
                }

                serial.sendCommand(command);
                JOptionPane.showMessageDialog(contentPane, "Comando Enviado");

                configDefault.setEquipmentId(fieldIdDoEquipamento.getText());
                configDefault.setIpJMS(fieldIpJMS.getText());
                configDefault.setSSID(fieldSsidRede.getText());
                configDefault.setSenhaRede(fieldPassword.getText());
                if (neutroAtivo.isSelected()) {
                    configDefault.setNeutroAtivo("1");
                } else {
                    configDefault.setNeutroAtivo("0");
                }
                if (tipoRedeB.isSelected()) {
                    configDefault.setTipoRede("B");
                } else if (tipoRedeM.isSelected()) {
                    configDefault.setTipoRede("M");
                }else {
                    configDefault.setTipoRede("T");
                }
                if (terraAtivo.isSelected()) {
                    configDefault.setTerraAtivo("1");
                } else {
                    configDefault.setTerraAtivo("0");
                }
                configDefault = configuracoesRepository.save(configDefault);
            }
        });
        btnSalvar.setBounds(249, 323, 105, 27);
        contentPane.add(btnSalvar);

        JButton btnLog = new JButton("Log");
        btnLog.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TextAreaOutputStreamPanel.load();
            }
        });
        btnLog.setBounds(418, 323, 105, 27);
        contentPane.add(btnLog);

        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(23, 391, 565, 88);
        contentPane.add(scrollPane);

        System.setOut(new PrintStream(taOutputStream));

        int timerDelay = 1000;
        new Timer(timerDelay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                // though this outputs via System.out.println, it actually displays
                // in the JTextArea:
            }
        }).start();

        List<Configuracoes> configList = configuracoesRepository.findAll();
        if (configList != null && !configList.isEmpty()) {
            Configuracoes config = configList.get(0);
            fieldIdDoEquipamento.setText(config.getEquipmentId());
            fieldIpJMS.setText(config.getIpJMS());
            fieldSsidRede.setText(config.getSSID());
            fieldPassword.setText(config.getSenhaRede());
            if (config.getTipoRede().equals("M")) {
                tipoRedeM.setSelected(true);
            } else if (config.getTipoRede().equals("B")) {
                tipoRedeB.setSelected(true);
            } else {
                tipoRedeT.setSelected(true);
            }
            if (config.getNeutroAtivo().equals("1")) {
                neutroAtivo.setSelected(true);
            } else {
                neutroDesativado.setSelected(true);
            }
            if (config.getTerraAtivo().equals("1")) {
                terraAtivo.setSelected(true);
            } else {
                terraDesativado.setSelected(true);
            }
            configDefault = config;
        }
    }
}
