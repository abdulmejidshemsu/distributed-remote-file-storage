/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import data.Request;
import data.DataNode;

/**
 *
 * @author dlham
 */
public class EchoManager {

    private final int ECHO_INTERVAL = 10; // in seconds

    public void startHeartBeat() {
        Runnable heartBeat = this::checkHeartBeat;
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(heartBeat, 0, ECHO_INTERVAL, TimeUnit.SECONDS);
    }

    private void checkHeartBeat() {
        ArrayList<DataNode> dataNodes = (new NodeManager()).getAllDataNodes();

        dataNodes.forEach((DataNode dataNode) -> {
            try {
                Socket socket = new Socket(dataNode.getIpAddress(), dataNode.getPort());

                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                Request outputrequest = new Request("echo");
                objectOutputStream.writeObject(outputrequest);
                objectOutputStream.flush();

                try {
                    ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                    Request inputRequest = (Request) objectInputStream.readObject();
                    (new ResourceManager()).updateDataNodeStatus(dataNode.getId(), inputRequest, true);
                    System.out.println("Successfully Echoed: " + inputRequest);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(EchoManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                (new ResourceManager()).updateDataNodeStatus(dataNode.getId(), null, false);
//                    Logger.getLogger(EchoManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

}
