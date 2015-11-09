/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieplayer.output;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Sasa2905
 */
public class ActuatorFile {
    
    public ActuatorFile() {
        
    }
    
    public void createFile() throws FileNotFoundException, IOException {
        File signalFile = new File("fileOutput.txt");
        PrintWriter writer = new PrintWriter(signalFile);
        writer.write("HALLO");
    }
}
