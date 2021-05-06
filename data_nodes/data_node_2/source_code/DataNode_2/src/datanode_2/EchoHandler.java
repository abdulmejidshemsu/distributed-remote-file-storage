/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datanode_2;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import data.Request;

/**
 *
 * @author dlham
 */
public class EchoHandler {

    private final Request request;

    public EchoHandler(Request request) {
        this.request = request;
    }

    public void sendEchoResponse(ObjectOutputStream objectOutputStream) {
        File diskPartition = new File("./");
        request.setTotalStorageSpace(diskPartition.getTotalSpace());
        request.setFreeStorageSpace(diskPartition.getUsableSpace());
        try {
            objectOutputStream.writeObject(request);
            objectOutputStream.flush(); 
        } catch (IOException ex) {
            Logger.getLogger(EchoHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
