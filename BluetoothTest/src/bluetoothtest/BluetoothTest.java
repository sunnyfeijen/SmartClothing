/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bluetoothtest;

import bluetoothtest.connect.DeviceFinder;

/**
 *
 * @author Sasa2905
 */
public class BluetoothTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DeviceFinder finder = new DeviceFinder();
        finder.initLocalDevice();
    }
    
}
