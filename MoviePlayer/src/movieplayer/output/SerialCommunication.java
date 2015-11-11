/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieplayer.output;

import com.fazecast.jSerialComm.SerialPort;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sasa2905
 */
public class SerialCommunication {

    static SerialPort chosenPort;
    public void initCommunication() {
        SerialPort[] portNames = SerialPort.getCommPorts();
        SerialPort chosenPort = null;
        System.out.println(portNames.length);
        for (int i = 0; i < portNames.length; i++) {
            if (portNames[i].getDescriptivePortName().contains("Flora")) {
                chosenPort = portNames[i];
                break;
            }
        }
        if (chosenPort != null) {
            chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
            if (chosenPort.openPort()) {
                System.out.println("Port opened");
                try {
                    ObjectOutputStream os = new ObjectOutputStream(chosenPort.getOutputStream());
                    os.writeObject("Hallo g");
                } catch (IOException ex) {
                    Logger.getLogger(SerialCommunication.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
