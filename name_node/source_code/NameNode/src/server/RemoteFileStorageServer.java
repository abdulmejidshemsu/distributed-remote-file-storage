/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author dlham
 */
public class RemoteFileStorageServer {
    private static final int PORT = 8400;
    
    public static void main(String args[]) {
        try { 
            Registry registry = LocateRegistry.createRegistry(PORT);
            
            RemoteFileStorageImplementation rfsImp = new RemoteFileStorageImplementation(); 
            
            registry.rebind("RemoteFileStorageService", rfsImp); 
            
            System.out.println("Server running on port "+ PORT +"....");
            
            (new EchoManager()).startHeartBeat();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
