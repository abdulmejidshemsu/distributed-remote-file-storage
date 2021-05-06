/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datanode_1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import data.Request;

/**
 *
 * @author dlham
 */
class ClientHandler extends Thread {

    final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try { 
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            Request request = (Request) objectInputStream.readObject();
            String requestType = request.getRequestType(); 

            switch (requestType) {

                case "echo":
                    System.out.println("Request (echo) received: " + request + " with type -> " + request.getRequestType());
                    (new EchoHandler(request)).sendEchoResponse(objectOutputStream);
                    break;

                case "read":
                    System.out.println("Request (read) received: " + request);
                    (new FileManager()).sendFileResponse(request, objectOutputStream);
                    break;

                case "write":
                    System.out.println("Request (write) received: " + request.getChunkedBytes());
                    (new FileManager()).write(request, objectOutputStream);
                    break;
                 
                case "delete":
                    System.out.println("Request (delete) received: " + request.getFileId());
                    (new FileManager()).delete(request);
                    break;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
