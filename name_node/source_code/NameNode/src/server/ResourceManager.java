/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import data.Request;
import data.ChunkedByte;
import data.DataNode;

/**
 *
 * @author dlham
 */
public class ResourceManager {

    public ArrayList<DataNode> assignChunkesToDataNodes(ArrayList<DataNode> dataNodes, ArrayList<ChunkedByte> chunkedBytes) {
        while (!chunkedBytes.isEmpty()) {
            Iterator dataNodesIter = dataNodes.iterator();
            ChunkedByte currentChunkedByte = chunkedBytes.get(0);

            while (dataNodesIter.hasNext() && !chunkedBytes.isEmpty()) {
                DataNode currentDataNode = (DataNode) dataNodesIter.next();

                if (currentDataNode.hasEnoughSpaceFor(currentChunkedByte.getSize())
                        && !currentDataNode.containsChunkedByte(currentChunkedByte.getPart(), currentChunkedByte.getReplica())) {
                    currentDataNode.addChunkedByte(currentChunkedByte);
                    chunkedBytes.remove(0);
                    if (!chunkedBytes.isEmpty()) {
                        currentChunkedByte = chunkedBytes.get(0);
                    }
                }
            }
        }
        return dataNodes;
    }

    public void updateDataNodeStatus(long dataNodeId, Request request, boolean isUp) { 
        try {
            Connection connection = DBConnection.getConnection();
            if (isUp) {
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE data_nodes SET is_up =1, "
                        + "total_storage_space = ?, free_storage_space = ? WHERE id =" + dataNodeId);

                preparedStatement.setLong(1, request.getTotalStorageSpace());
                preparedStatement.setLong(2, request.getFreeStorageSpace());
                preparedStatement.executeUpdate();
            } else {
                (connection.prepareStatement("UPDATE data_nodes SET is_up=0 WHERE id=" + dataNodeId)).executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ResourceManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
