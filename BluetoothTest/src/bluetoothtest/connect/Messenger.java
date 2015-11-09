/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bluetoothtest.connect;

import java.io.IOException;
import java.io.OutputStream;
import javax.microedition.io.Connector;
import javax.obex.ClientSession;
import javax.obex.HeaderSet;
import javax.obex.Operation;
import javax.obex.ResponseCodes;

/**
 *
 * @author Sasa2905
 */
public class Messenger {
        public void sendMessageToDevice(String serverURL) throws IOException {
        System.out.println("Connecting to " + serverURL);
        ClientSession clientSession = (ClientSession)Connector.open(serverURL);
        HeaderSet hsConnectReply = clientSession.connect(null);
        if(hsConnectReply.getResponseCode() != ResponseCodes.OBEX_HTTP_OK) {
            System.out.println("Failed to connect");
            return;
        }
        HeaderSet hsOperation = clientSession.createHeaderSet();
        hsOperation.setHeader(HeaderSet.NAME, "Hello.txt");
        hsOperation.setHeader(HeaderSet.TYPE, "text");
        
        Operation putOperation = clientSession.put(hsOperation);
        byte data[] = "Hello World !!!".getBytes("iso-8859-1");
            OutputStream os = putOperation.openOutputStream();
            os.write(data);
            os.close();
     
            putOperation.close();
            clientSession.disconnect(null);
            clientSession.close();
        }
}
