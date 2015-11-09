/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bluetoothtest.connect;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.bluetooth.DataElement;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;

/**
 *
 * @author Sasa2905
 */
public class MyDiscoveryListener implements DiscoveryListener{
    
    MyDiscoveryListener() {}
    
    @Override
    public void deviceDiscovered(RemoteDevice btDevice, DeviceClass arg1) {
        String name;
        try {
            name = btDevice.getFriendlyName(false);
        } catch (Exception e) {
            name = btDevice.getBluetoothAddress();
        }
        
        System.out.println("device found: " + name);
        
    }

    @Override
    public void inquiryCompleted(int arg0) {
        synchronized(DeviceFinder.lock){
            DeviceFinder.lock.notify();
        }
    }

    @Override
    public void serviceSearchCompleted(int arg0, int arg1) {
        synchronized(DeviceFinder.lock) {
            DeviceFinder.lock.notify();
        }
    }

    @Override
    public void servicesDiscovered(int arg0, ServiceRecord[] arg1) {
        for (int i = 0; i < arg1.length; i++) {
            String url = arg1[i].getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false);
            if(url == null) {
                continue;
            }
            DataElement serviceName = arg1[i].getAttributeValue(0x0100);
            if(serviceName != null) {
                System.out.println("serive " + serviceName.getValue()+ " found" + url);
            } else {
                System.out.println("service found " + url);
            }
            if(serviceName.getValue().equals("OBEX Object Push")) {
                Messenger messenger = new Messenger();
                try {
                    messenger.sendMessageToDevice(url);
                } catch (IOException ex) {
                    Logger.getLogger(MyDiscoveryListener.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
    }
    

}
