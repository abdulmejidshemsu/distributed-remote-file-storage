/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datanode_2;

import data.ChunkedByte;
import data.Request;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.FileUtils;

/**
 *
 * @author dlham
 */
public class FileManager {
 
    public void write(Request request, ObjectOutputStream objectOutputStream) {
        try {
            ArrayList<ChunkedByte> chunkedBytes = request.getChunkedBytes();
            Connection connection;

            connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(""
                    + "INSERT INTO chunks (file_id, part, replica, size, local_path, unique_chunk_name) "
                    + "VALUES (?, ?, ?, ?, ?, ?)");

            for (ChunkedByte chunkedByte : chunkedBytes) {

                File file = new File("src/chunk_storage/" + chunkedByte.getUniqueChunkName());
                if (!file.exists()) {
                    file.createNewFile();
                }
                try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file))) {
                    bufferedOutputStream.write(chunkedByte.getContent());
                }

                preparedStatement.setLong(1, chunkedByte.getFileId());
                preparedStatement.setInt(2, chunkedByte.getPart());
                preparedStatement.setInt(3, chunkedByte.getReplica());
                preparedStatement.setLong(4, chunkedByte.getSize());
                preparedStatement.setString(5, file.getAbsolutePath());
                preparedStatement.setString(6, chunkedByte.getUniqueChunkName());
                preparedStatement.addBatch();
                preparedStatement.clearParameters();
            }

            preparedStatement.executeBatch();

            request.setChunkedBytes(null);
            request.setWriteSuccess(true);
            objectOutputStream.writeObject(request);
            objectOutputStream.flush();
        } catch (IOException | SQLException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendFileResponse(Request request, ObjectOutputStream objectOutputStream) {
        Connection connection;
        try {
            connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT local_path FROM chunks WHERE unique_chunk_name='"
                    + request.getUniqueChunkName() + "';");
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String path = rs.getString("local_path");
                request.setIndividualChunk(FileUtils.fileToByte(path));
                request.setChunkedBytes(null); 
                objectOutputStream.writeObject(request);
                objectOutputStream.flush();
            }
        } catch (IOException | SQLException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void delete(Request request) {
         Connection connection;
        try {
            connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT local_path FROM chunks WHERE file_id=" + request.getFileId());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {  
                System.out.println("Deleting: "+ rs.getString("local_path"));
                (new java.io.File(rs.getString("local_path"))).delete(); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
