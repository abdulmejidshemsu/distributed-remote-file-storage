/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datanode_1;
 
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dlham
 */
public class DataNode_1 {
 

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.start();
        } catch (IOException ex) {
            Logger.getLogger(DataNode_1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 

}
