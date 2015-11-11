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
import javax.bluetooth.RemoteDevice;

/**
 *
 * @author Sasa2905
 */
public class DeviceFinder {

    public static Object lock = new Object();
    LocalDevice localDevice;
    DiscoveryAgent agent;

    public DeviceFinder() {
        try {
            localDevice = LocalDevice.getLocalDevice();
            agent = localDevice.getDiscoveryAgent();
            RemoteDevice[] devices = agent.retrieveDevices(0);
            System.out.println(devices.length);
        } catch (BluetoothStateException ex) {
            Logger.getLogger(DeviceFinder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void initLocalDevice() {
        try {
            agent.startInquiry(DiscoveryAgent.GIAC, new MyDiscoveryListener(agent));
            synchronized (lock) {
                lock.wait();
            }
        } catch (BluetoothStateException | InterruptedException ex) {
            Logger.getLogger(DeviceFinder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
