/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;
 
import java.io.FileOutputStream;
import java.io.IOException;
import rmi.RemoteFileStorageInterface;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import data.File;
import data.NameResolution;
import data.Request;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import utils.FileUtils;

/**
 *
 * @author dlham
 */
public class RemtoteFileStorageClient {

    public static RemoteFileStorageInterface rfsReference = null;
    private static final String HOST = "localhost";
    private static final int PORT = 8400;

    public RemtoteFileStorageClient() {
        try {
            rfsReference = (RemoteFileStorageInterface) Naming.lookup("rmi://" + HOST + ":" + PORT + "/RemoteFileStorageService");
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(RemtoteFileStorageClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void download(File file, String saveToPath) {
        saveToPath = saveToPath.replaceAll("\\\\", "/") + "/" + file.getName();
        System.out.println(saveToPath);

        try {
            ArrayList<NameResolution> nameResolutions = rfsReference.getNameResolution(file);
            ArrayList<byte[]> toBeMergedChunks = new ArrayList<>();

            for (NameResolution nameResolution : nameResolutions) {
                System.out.println("fileId: " + nameResolution.getFileId());
                System.out.println("dataNodeId: " + nameResolution.getDataNodeId());
                System.out.println("ipAddress: " + nameResolution.getIpAddress());
                System.out.println("port: " + nameResolution.getPort());
                System.out.println("part: " + nameResolution.getPart());
                System.out.println("replica: " + nameResolution.getReplcia());
                System.out.println("size: " + nameResolution.getSize());

                try {
                    Socket socket = new Socket(nameResolution.getIpAddress(), nameResolution.getPort());

                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    Request outputRequest = new Request("read");
                    outputRequest.setUniqueChunkName(nameResolution.getUniqueChunkName());
                    objectOutputStream.writeObject(outputRequest);
                    objectOutputStream.flush();

                    try {
                        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                        Request inputRequest = (Request) objectInputStream.readObject();
                        toBeMergedChunks.add(inputRequest.getIndividualChunk());
                        System.out.println("Unique: " + nameResolution.getUniqueChunkName());
                        System.out.println("Unique: " + FileUtils.humanReadableByte(inputRequest.getIndividualChunk().length));
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(RemtoteFileStorageClient.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(RemtoteFileStorageClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            createFile(saveToPath, toBeMergedChunks);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createFile(String fileName, ArrayList<byte[]> chunks) {
        FileOutputStream fos = null;
        try {
            java.io.File newFile = new java.io.File(fileName);
            fos = new FileOutputStream(newFile);
            Iterator<byte[]> it = chunks.iterator();
            while (it.hasNext()) {
                try {
                    fos.write(it.next());
                } catch (IOException ex) {
                    Logger.getLogger(RemtoteFileStorageClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(RemtoteFileStorageClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(RemtoteFileStorageClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
