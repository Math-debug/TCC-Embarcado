package unip.tcc;

import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import unip.tcc.view.Instalador;

@Service
public class MainClass {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MainClass.class);

	StringBuilder mensagem = new StringBuilder();

	SerialPort activePort;
	SerialPort[] ports = SerialPort.getCommPorts();

	public void setPort(int portIndex) {
		activePort = ports[portIndex];

		if (activePort.openPort())
			System.out.println(activePort.getPortDescription() + " port opened.");

		activePort.addDataListener(new SerialPortDataListener() {

//			@Override
			public void serialEvent(SerialPortEvent event) {
				int size = event.getSerialPort().bytesAvailable();
				byte[] buffer = new byte[size];
				event.getSerialPort().readBytes(buffer, size);
				String msg = new String(buffer, StandardCharsets.US_ASCII);

//				StringBuilder msg = new StringBuilder();
//				for (byte b : buffer) {
//					msg.append((char) b);
//					System.out.print(msg.toString());
//				}
//				System.out.println(msg.toString().replaceAll("\\n", ""));
				mensagem.append(msg);
				String receive = mensagem.toString();
				int x = receive.indexOf("_");
				if (x > 0) {
					LOGGER.info(receive.replace("_", ""));
					mensagem = new StringBuilder();
				}
			}

//			@Override
			public int getListeningEvents() {
				return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
			}
		});
	}

	public void start() {
		setPort(0);
		Instalador.load();
	}

}