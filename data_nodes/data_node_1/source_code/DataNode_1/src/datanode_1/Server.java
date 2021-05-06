/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datanode_1;
  
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket; 

/**
 *
 * @author dlham
 */
public class Server {
    private final int SOCKET_PORT = 8100;

    public void start() throws IOException { 
        ServerSocket serverSocket = new ServerSocket(SOCKET_PORT);
        System.out.println("Server started at port: " + SOCKET_PORT);
 
        while (true) {
            Socket socket = null;

            try { 
                socket = serverSocket.accept();
                System.out.println("A new client is connected : " + socket);
  
                Thread clientHandler = new ClientHandler(socket); 
                clientHandler.start();

            } catch (IOException e) {
                socket.close();
                e.printStackTrace();
            }
        }
    }
}
