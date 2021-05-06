/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger; 
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import server.DBConnection; 

/**
 *
 * @author dlham
 */
public class DataNode implements Serializable {

    // table columns
    private long id;
    private String name;
    private String ipAddress;
    private int port;
    private long totalStorageSpace;
    private long freeStorageSpace;
    private boolean isUp;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // to be added chunks
    private ArrayList<ChunkedByte> toBeAddedChunks = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public long getTotalStorageSpace() {
        return totalStorageSpace;
    }

    public void setTotalStorageSpace(long totalStorageSpace) {
        this.totalStorageSpace = totalStorageSpace;
    }

    public long getFreeStorageSpace() {
        return freeStorageSpace;
    }

    public void setFreeStorageSpace(long freeStorageSpace) {
        this.freeStorageSpace = freeStorageSpace;
    }

    public boolean isIsUp() {
        return isUp;
    }

    public void setIsUp(boolean isUp) {
        this.isUp = isUp;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    // Utils
    public boolean hasEnoughSpaceFor(long size) {
        return this.freeStorageSpace >= size;
    }

    public void addChunkedByte(ChunkedByte chunkedByte) {
        this.toBeAddedChunks.add(chunkedByte);
    }

    public boolean containsChunkedByte(long part, long replica) {
        return toBeAddedChunks.stream().anyMatch((chunkedByte) -> (chunkedByte.getPart() == part && chunkedByte.getReplica() == replica));
    }

    public ArrayList<ChunkedByte> getToBeAddedChunks() {
        return toBeAddedChunks;
    }

    public void setToBeAddedChunks(ArrayList<ChunkedByte> toBeAddedChunks) {
        this.toBeAddedChunks = toBeAddedChunks;
    }

    public boolean write() {
        try {
            Socket socket = new Socket(this.getIpAddress(), this.getPort());

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            Request outputRequest = new Request("write");
            outputRequest.setChunkedBytes(toBeAddedChunks);
            objectOutputStream.writeObject(outputRequest);
            objectOutputStream.flush();

            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                Request inputRequest = (Request) objectInputStream.readObject();
                if (inputRequest.isWriteSuccess()) {
                    saveChunkMetadataToDatabase();
                    return true;
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DataNode.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
//                    Logger.getLogger(EchoManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private void saveChunkMetadataToDatabase() {
        Connection connection;
        try {
            connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(""
                    + "INSERT INTO chunks (file_id, data_node_id, part, replica, size, unique_chunk_name) "
                    + "VALUES (?, ?, ?, ?, ?, ?)");

            for (ChunkedByte chunkedByte : toBeAddedChunks) {
                preparedStatement.setLong(1, chunkedByte.getFileId());
                preparedStatement.setLong(2, this.getId());
                preparedStatement.setInt(3, chunkedByte.getPart());
                preparedStatement.setInt(4, chunkedByte.getReplica());
                preparedStatement.setLong(5, chunkedByte.getSize());
                preparedStatement.setString(6, chunkedByte.getUniqueChunkName());
                preparedStatement.addBatch();
                preparedStatement.clearParameters();
            }

            int[] results = preparedStatement.executeBatch();
        } catch (SQLException ex) {
            Logger.getLogger(DataNode.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
