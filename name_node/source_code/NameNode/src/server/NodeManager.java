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
import java.util.logging.Level;
import java.util.logging.Logger;
import data.File;
import data.DataNode;

/**
 *
 * @author dlham
 */
public class NodeManager {

    Connection connection;

    public NodeManager() {
        try {
            connection = DBConnection.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(RemoteFileStorageImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<DataNode> getAllDataNodes() {
        return queryDataNodes("SELECT * FROM data_nodes;");
    }
    
    public ArrayList<DataNode> getAvailableDataNodes() {
        return queryDataNodes("SELECT * FROM data_nodes WHERE free_storage_space > 0 AND is_up=1;");
    } 

    private ArrayList<DataNode> queryDataNodes(String sql) {
        ArrayList<DataNode> dataNodes = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                DataNode dataNode = new DataNode();
                try {
                    dataNode.setId(rs.getLong("id"));
                    dataNode.setName(rs.getString("name"));
                    dataNode.setIpAddress(rs.getString("ip_address"));
                    dataNode.setPort(rs.getInt("port"));
                    dataNode.setTotalStorageSpace(rs.getLong("total_storage_space"));
                    dataNode.setFreeStorageSpace(rs.getLong("free_storage_space"));
                    dataNode.setIsUp(rs.getBoolean("is_up"));
                    dataNode.setCreatedAt(rs.getTimestamp("created_at"));
                    dataNode.setCreatedAt(rs.getTimestamp("updated_at"));
                } catch (SQLException ex) {
                    Logger.getLogger(RemoteFileStorageImplementation.class.getName()).log(Level.SEVERE, null, ex);
                }
                dataNodes.add(dataNode);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RemoteFileStorageImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dataNodes;
    }
     
}
