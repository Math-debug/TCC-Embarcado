package unip.tcc.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
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

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import unip.tcc.config.XmlConfig;
import unip.tcc.core.Comandos;
import unip.tcc.entities.Configuracoes;
import unip.tcc.repository.ConfiguracoesRepository;
import unip.tcc.service.ComunicacaoSerial;
import unip.tcc.view.log.TextAreaOutputStream;

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
    private TextAreaOutputStream taOutputStream = new TextAreaOutputStream(textArea);
    private JTextField fieldGateway;
    private JTextField fieldSubRede;

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
                    try {
                        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                            if ("Nimbus".equals(info.getName())) {
                                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                                break;
                            }
                        }
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
                        java.util.logging.Logger.getLogger(Instalador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
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
        
        File xml = new File(new File("config.xml").getAbsolutePath());
        
        Class<?>[] classes = new Class[] { XmlConfig.class };
        XStream stream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(stream);
        stream.allowTypes(classes);
        XmlConfig xmlConfig = (XmlConfig) stream.fromXML(xml);
        
        this.serial = serial;
        setTitle("Instalador");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 604, 581);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblIdDoEquipamento = new JLabel("Id do Equipamento:");
        lblIdDoEquipamento.setBounds(112, 91, 118, 17);
        contentPane.add(lblIdDoEquipamento);

        JLabel IP = new JLabel("IP:");
        IP.setBounds(112, 116, 118, 17);
        contentPane.add(IP);

        JLabel lblSsidRede = new JLabel("SSID da Rede:");
        lblSsidRede.setBounds(112, 192, 118, 17);
        contentPane.add(lblSsidRede);

        JLabel lblPassword = new JLabel("Senha da Rede:");
        lblPassword.setBounds(112, 218, 118, 17);
        contentPane.add(lblPassword);

        fieldIdDoEquipamento = new JTextField();
        fieldIdDoEquipamento.setBounds(235, 89, 291, 21);
        contentPane.add(fieldIdDoEquipamento);
        fieldIdDoEquipamento.setColumns(10);

        fieldIpJMS = new JTextField();
        fieldIpJMS.setColumns(10);
        fieldIpJMS.setBounds(235, 114, 291, 21);
        contentPane.add(fieldIpJMS);

        fieldSsidRede = new JTextField();
        fieldSsidRede.setColumns(10);
        fieldSsidRede.setBounds(235, 190, 291, 21);
        contentPane.add(fieldSsidRede);

        fieldPassword = new JTextField();
        fieldPassword.setColumns(10);
        fieldPassword.setBounds(235, 216, 291, 21);
        contentPane.add(fieldPassword);

        JLabel lblTipoRede = new JLabel("Rede:");
        lblTipoRede.setBounds(98, 249, 49, 17);
        contentPane.add(lblTipoRede);

        JRadioButton tipoRedeM = new JRadioButton("Monofásica");
        tipoRedeM.setBounds(147, 245, 93, 25);
        tipoRedeM.setVisible(xmlConfig.getEnableMonofasico());
        contentPane.add(tipoRedeM);

        JRadioButton tipoRedeB = new JRadioButton("Bifásica");
        tipoRedeB.setBounds(147, 276, 93, 25);
        tipoRedeB.setVisible(xmlConfig.getEnableBifasico());

        JRadioButton tipoRedeT = new JRadioButton("Trifásica");
        tipoRedeT.setBounds(147, 305, 93, 25);
        tipoRedeT.setVisible(xmlConfig.getEnableTrifasico());
        contentPane.add(tipoRedeT);

        tipoRede = new ButtonGroup();
        tipoRede.add(tipoRedeM);
        tipoRede.add(tipoRedeB);
        tipoRede.add(tipoRedeT);
        contentPane.add(tipoRedeB);

        JLabel lblNeutro = new JLabel("Neutro:");
        lblNeutro.setBounds(347, 309, 61, 17);
        lblNeutro.setVisible(xmlConfig.getEnableNeutro());
        contentPane.add(lblNeutro);

        JRadioButton neutroAtivo = new JRadioButton("Ativado");
        neutroAtivo.setBounds(422, 305, 98, 25);
        neutroAtivo.setVisible(xmlConfig.getEnableNeutro());
        contentPane.add(neutroAtivo);

        JRadioButton neutroDesativado = new JRadioButton("Desativado");
        neutroDesativado.setBounds(422, 334, 98, 25);
        neutroDesativado.setVisible(xmlConfig.getEnableNeutro());
        if(!xmlConfig.getEnableNeutro()) {
            neutroDesativado.setSelected(true);
        }
        contentPane.add(neutroDesativado);

        enableNeutro = new ButtonGroup();
        enableNeutro.add(neutroAtivo);
        enableNeutro.add(neutroDesativado);

        JLabel lblTerra = new JLabel("Terra:");
        lblTerra.setBounds(347, 249, 61, 17);
        lblTerra.setVisible(xmlConfig.getEnableTerra());
        contentPane.add(lblTerra);

        JRadioButton terraAtivo = new JRadioButton("Ativado");
        terraAtivo.setBounds(422, 245, 98, 25);
        terraAtivo.setVisible(xmlConfig.getEnableTerra());
        contentPane.add(terraAtivo);

        JRadioButton terraDesativado = new JRadioButton("Desativado");
        terraDesativado.setBounds(422, 276, 98, 25);
        terraDesativado.setVisible(xmlConfig.getEnableTerra());
        if(!xmlConfig.getEnableTerra()) {
            terraDesativado.setSelected(true);
        }
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
                configDefault.setIp("");
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
        btnReset.setBounds(90, 397, 105, 27);
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
                command.setGateway(fieldGateway.getText());
                command.setSubrede(fieldSubRede.getText());
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
                configDefault.setIp(fieldIpJMS.getText());
                configDefault.setSSID(fieldSsidRede.getText());
                configDefault.setSenhaRede(fieldPassword.getText());
                configDefault.setGateway(fieldGateway.getText());
                configDefault.setSubrede(fieldSubRede.getText());
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
        btnSalvar.setBounds(253, 397, 105, 27);
        contentPane.add(btnSalvar);

        JButton btnLog = new JButton("Log");
        btnLog.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //TextAreaOutputStreamPanel.load();
                SimpleDateFormat format = new SimpleDateFormat("YYYYMMDDhhmmss");
                String dataLog = format.format(new Date());
                File log = new File(new File(dataLog + "-log.txt").getAbsolutePath());
                JOptionPane.showMessageDialog(contentPane, "Log Gerado em "+ log);
                try {
                    FileOutputStream output = new FileOutputStream(log);
                    output.write(textArea.getText().getBytes());
                    output.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnLog.setBounds(415, 397, 105, 27);
        contentPane.add(btnLog);

        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(27, 452, 565, 88);
        contentPane.add(scrollPane);
        
        JLabel gateway = new JLabel("Gateway:");
        gateway.setBounds(112, 141, 118, 17);
        contentPane.add(gateway);
        
        fieldGateway = new JTextField();
        fieldGateway.setColumns(10);
        fieldGateway.setBounds(235, 139, 291, 21);
        contentPane.add(fieldGateway);
        
        JLabel mascaraRede = new JLabel("Máscara SubRede:");
        mascaraRede.setBounds(112, 163, 118, 17);
        contentPane.add(mascaraRede);
        
        fieldSubRede = new JTextField();
        fieldSubRede.setColumns(10);
        fieldSubRede.setBounds(235, 165, 291, 21);
        contentPane.add(fieldSubRede);
        
        JLabel lblNewLabel = new JLabel("SOFTWARE DE CONFIGURAÇÃO - MGE");
        lblNewLabel.setIcon(new ImageIcon(Instalador.class.getResource("/unip/tcc/view/logo_resized.png")));
        lblNewLabel.setBounds(174, 12, 300, 56);
        contentPane.add(lblNewLabel);

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
            fieldSubRede.setText(config.getSubrede());
            fieldGateway.setText(config.getGateway());
            fieldIpJMS.setText(config.getIp());
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
