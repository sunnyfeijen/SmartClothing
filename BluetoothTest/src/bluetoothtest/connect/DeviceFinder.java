/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bluetoothtest.connect;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.UUID;

/**
 *
 * @author Sasa2905
 */
public class DeviceFinder {

    UUID[] uuidSet = new UUID[1];

    public static Object lock = new Object();
    LocalDevice localDevice;
    DiscoveryAgent agent;

    DeviceFinder() {
        try {
            localDevice = LocalDevice.getLocalDevice();
            agent = localDevice.getDiscoveryAgent();
        } catch (BluetoothStateException ex) {
            Logger.getLogger(DeviceFinder.class.getName()).log(Level.SEVERE, null, ex);
        }
        uuidSet[0] = new UUID(
                0x1105); //OBEX Object Push service
        int[] attrIDs = new int[]{
            0x0100 // Service name
        };
    }

    private void initLocalDevice() {
        try {
            agent.startInquiry(DiscoveryAgent.GIAC, new MyDiscoveryListener());
            synchronized (lock) {
                lock.wait();
            }
        } catch (BluetoothStateException | InterruptedException ex) {
            Logger.getLogger(DeviceFinder.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
