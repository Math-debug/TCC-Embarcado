package unip.tcc.service;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import unip.tcc.core.Comandos;
import unip.tcc.repository.ConfiguracoesRepository;
import unip.tcc.view.Instalador;

@Service
public class ComunicacaoSerial {
    
    @Autowired
    ConfiguracoesRepository configuracoesRepository;

    StringBuilder mensagem = new StringBuilder();

    SerialPort activePort;
    SerialPort[] ports = SerialPort.getCommPorts();

    String[] configs = new String[6];

    public void start() {
        setPort(0);
        Instalador.load(this,configuracoesRepository);
    }

    public void setPort(int portIndex) {
        try {
            activePort = ports[portIndex];
            activePort.setBaudRate(9600);

            if (activePort.openPort()) {
                System.out.println(activePort.getPortDescription() + " port opened.");
            }

            activePort.addDataListener(new SerialPortDataListener() {

                // @Override
                public void serialEvent(SerialPortEvent event) {
                    int size = event.getSerialPort().bytesAvailable();
                    byte[] buffer = new byte[size];
                    event.getSerialPort().readBytes(buffer, size);
                    String msg = new String(buffer, StandardCharsets.US_ASCII);
                    mensagem.append(msg);
                    String receive = mensagem.toString();
                    int x = receive.indexOf("\n");
                    if (x > 0) {
                        System.out.print(receive);
                        mensagem = new StringBuilder();
                    }
                }

                // @Override
                public int getListeningEvents() {
                    return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeMessage(String mensagem) throws InterruptedException {
        try {
            activePort.writeBytes(mensagem.getBytes(), mensagem.length());
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendCommand(Comandos command) {
        if (command.getComand().equals("R")) {
            try {
                writeMessage("R");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (command.getComand().equals("N")) {
            try {
                writeMessage("N");

                StringBuilder comando = new StringBuilder();
                comando.append(command.getEquipmentId());
                comando.append(";");
                comando.append(command.getIpJMS());
                comando.append(";");
                comando.append(command.getSSID());
                comando.append(";");
                comando.append(command.getSenhaRede());
                comando.append(";");
                comando.append(command.getIsEnabledSystem());
                comando.append(";");
                comando.append(command.getTipoRede());
                comando.append(";");
                comando.append(command.getNeutroAtivo());
                comando.append(";");
                comando.append(command.getTerraAtivo());
                comando.append(";");

                writeMessage(comando.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}