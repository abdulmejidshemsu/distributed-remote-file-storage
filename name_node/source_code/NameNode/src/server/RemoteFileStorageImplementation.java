/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import utils.FileUtils;
import utils.NameResolutionComparator;
import rmi.RemoteFileStorageInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import data.Dashboard;
import data.File;
import data.ChunkedByte;
import data.DataNode;
import data.NameResolution;
import data.Request;
import java.io.IOException; 
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Collections; 

/**
 *
 * @author dlham
 */
public class RemoteFileStorageImplementation extends UnicastRemoteObject implements RemoteFileStorageInterface {

    Connection connection;

    public RemoteFileStorageImplementation() throws RemoteException {
        super();
        try {
            connection = DBConnection.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(RemoteFileStorageImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Dashboard getDashboardData() throws RemoteException {
        System.out.println("*** RemoteFileStorageImplementation: getDashboardData()");
        Dashboard dashboard = new Dashboard();
        try {
            dashboard = getDashboardCountsAndSizes(dashboard);
            dashboard = getDashboardRecentFiles(dashboard);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dashboard;
    }

    private Dashboard getDashboardCountsAndSizes(Dashboard dashboard) {
        String sql = "SELECT "
                + "SUM(IF(type = 'Image', 1, 0)) AS totalImagesCount, "
                + "SUM(IF(type = 'Image', size, 0)) AS totalImagesSize, "
                + "SUM(IF(type = 'Video', 1, 0)) AS totalVideosCount, "
                + "SUM(IF(type = 'Video', size, 0)) AS totalVideosSize, "
                + "SUM(IF(type = 'Audio', 1, 0)) AS totalAudiosCount, "
                + "SUM(IF(type = 'Audio', size, 0)) AS totalAudiosSize, "
                + "SUM(IF(type = 'Document', 1, 0)) AS totalDocumentsCount, "
                + "SUM(IF(type = 'Document', size, 0)) AS totalDocumentsSize "
                + "FROM files;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    dashboard.setTotalDocumentsCount(rs.getInt("totalDocumentsCount"));
                    dashboard.setTotalDocumentsSize(rs.getDouble("totalDocumentsSize"));

                    dashboard.setTotalImagesCount(rs.getInt("totalImagesCount"));
                    dashboard.setTotalImagesSize(rs.getDouble("totalImagesSize"));

                    dashboard.setTotalVideosCount(rs.getInt("totalVideosCount"));
                    dashboard.setTotalVideosSize(rs.getDouble("totalVideosSize"));

                    dashboard.setTotalAudiosCount(rs.getInt("totalAudiosCount"));
                    dashboard.setTotalAudiosSize(rs.getDouble("totalAudiosSize"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RemoteFileStorageImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dashboard;
    }

    private Dashboard getDashboardRecentFiles(Dashboard dashboard) {
        dashboard.setRecentFiles(queryFiles("SELECT * FROM files ORDER BY id DESC LIMIT 5;"));
        return dashboard;
    }

    @Override
    public ArrayList<File> getAllFiles() {
        System.out.println("*** RemoteFileStorageImplementation: getAllFiles()");
        return queryFiles("SELECT * FROM files ORDER BY id DESC;");
    }

    @Override
    public ArrayList<File> getImages() throws RemoteException {
        return queryFiles("SELECT * FROM files WHERE type='Image' ORDER BY id DESC;");
    }

    @Override
    public ArrayList<File> getAudios() throws RemoteException {
        return queryFiles("SELECT * FROM files WHERE type='Audio' ORDER BY id DESC;");
    }

    @Override
    public ArrayList<File> getVideos() throws RemoteException {
        return queryFiles("SELECT * FROM files WHERE type='Video' ORDER BY id DESC;");
    }

    @Override
    public ArrayList<File> getDocuments() throws RemoteException {
        return queryFiles("SELECT * FROM files WHERE type='Document' ORDER BY id DESC;");
    }

    @Override
    public ArrayList<File> getFavorites() throws RemoteException {
        return queryFiles("SELECT * FROM files WHERE is_favorite=1 ORDER BY id DESC;");
    }

    @Override
    public ArrayList<File> getWithFileNameLike(String query) throws RemoteException {
        return queryFiles("SELECT * FROM files WHERE name LIKE '" + query + "%';");
    }

    @Override
    public ArrayList<File> getFilesWithExtension(String extension) throws RemoteException {
        return queryFiles("SELECT * FROM files WHERE extension='" + extension + "' ORDER BY id DESC;");
    }

    private ArrayList<File> queryFiles(String sql) {
        ArrayList<File> files = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                File file = new File();
                try {
                    file.setId(rs.getInt("id"));
                    file.setName(rs.getString("name"));
                    file.setType(rs.getString("type"));
                    file.setSize(rs.getLong("size"));
                    file.setExtension(rs.getString("extension"));
                    file.setIsFavorite(rs.getBoolean("is_favorite"));
                    file.setUploadedAt(rs.getTimestamp("uploaded_at"));
                } catch (SQLException ex) {
                    Logger.getLogger(RemoteFileStorageImplementation.class.getName()).log(Level.SEVERE, null, ex);
                }
                files.add(file);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RemoteFileStorageImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return files;
    }

    @Override
    public void upload(String fileName, long fileSize, byte[] fileContent) throws RemoteException {
        try {
            long fileId = saveUploadedFileMetadataToDatabase(fileName, fileSize);
            if (fileId != 0) {
                ArrayList<ChunkedByte> chunkedBytes = FileUtils.getFileChunks(fileContent, fileId);
                ArrayList<DataNode> availableDatanodes = (new NodeManager()).getAvailableDataNodes();
                ResourceManager resourceManager = new ResourceManager();
                ArrayList<DataNode> assignedDataNodes = resourceManager.assignChunkesToDataNodes(availableDatanodes, chunkedBytes);
                showAssignedResources(assignedDataNodes); // show on console 
                assignedDataNodes.forEach((dataNode) -> {
                    dataNode.write();
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAssignedResources(ArrayList<DataNode> assignedDataNodes) {
        assignedDataNodes.stream().map((dataNode) -> {
            System.out.println("--------------------------------------------------------------");
            System.out.println("-> " + dataNode.getName() + " @ " + dataNode.getIpAddress() + ":" + dataNode.getPort());
            System.out.println("-> Total chunks: " + dataNode.getToBeAddedChunks().size());
            return dataNode;
        }).forEachOrdered((dataNode) -> {
            dataNode.getToBeAddedChunks().stream().map((chunkedByte) -> {
                System.out.println("Size: " + FileUtils.humanReadableByte(chunkedByte.getSize()));
                return chunkedByte;
            }).map((chunkedByte) -> {
                System.out.println("Part: " + chunkedByte.getPart());
                return chunkedByte;
            }).forEachOrdered((chunkedByte) -> {
                System.out.println("Replica: " + chunkedByte.getReplica());
                System.out.println("****************************");
            });
        });
    }

    private long saveUploadedFileMetadataToDatabase(String name, long size) {
        String extension = FileUtils.getFileExtension(name);
        String type = FileUtils.getFileType(extension);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO files (name, type, size, extension) VALUES(?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, type);
            preparedStatement.setLong(3, size);
            preparedStatement.setString(4, extension);
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(RemoteFileStorageImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public void delete(File file) throws RemoteException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM files WHERE id=" + file.getId());
            preparedStatement.executeUpdate();

            ArrayList<NameResolution> nameResolutions = new ArrayList<>();
            try {
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatementToDelete = connection.prepareStatement(""
                        + "SELECT * FROM chunks CHUNK INNER JOIN data_nodes DATA_NODE "
                        + "ON CHUNK.data_node_id = DATA_NODE.id "
                        + "WHERE  file_id=" + file.getId()
                );
                ResultSet rs = preparedStatementToDelete.executeQuery();

                ArrayList<Long> addedHosts = new ArrayList<>();

                while (rs.next()) {
                    long dataNodeId = rs.getLong("data_node_id");
                    if (rs.getBoolean("is_up") && !addedHosts.contains(dataNodeId)) {
                        NameResolution nameResolution = new NameResolution();
                        nameResolution.setFileId(rs.getLong("file_id"));
                        nameResolution.setDataNodeId(dataNodeId);
                        nameResolution.setIpAddress(rs.getString("ip_address"));
                        nameResolution.setPort(rs.getInt("port"));
                        nameResolution.setPart(rs.getInt("part"));
                        nameResolution.setReplcia(rs.getInt("replica"));
                        nameResolution.setSize(rs.getLong("size"));
                        nameResolution.setUniqueChunkName(rs.getString("unique_chunk_name"));
                        nameResolutions.add(nameResolution);
                        addedHosts.add(dataNodeId);
                    }
                    System.out.println(rs.getLong("data_node_id") + " - " + rs.getString("ip_address") + rs.getString("is_up"));

                }
                sendDeleteRequests(nameResolutions);
            } catch (SQLException ex) {
                Logger.getLogger(RemoteFileStorageImplementation.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RemoteFileStorageImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sendDeleteRequests(ArrayList<NameResolution> nameResolutions) {
        nameResolutions.forEach((nameResolution) -> {
            Socket socket;
            try {
                socket = new Socket(nameResolution.getIpAddress(), nameResolution.getPort());
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                Request outputRequest = new Request("delete");
                outputRequest.setFileId(nameResolution.getFileId());
                outputRequest.setChunkedBytes(null);
                objectOutputStream.writeObject(outputRequest);
                objectOutputStream.flush();
            } catch (IOException ex) {
                Logger.getLogger(RemoteFileStorageImplementation.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @Override
    public ArrayList<NameResolution> getNameResolution(File file) throws RemoteException {
        ArrayList<NameResolution> nameResolutions = new ArrayList<>();
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(""
                    + "SELECT * FROM chunks CHUNK INNER JOIN data_nodes DATA_NODE "
                    + "ON CHUNK.data_node_id = DATA_NODE.id "
                    + "WHERE  file_id=" + file.getId()
            );
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                if (rs.getBoolean("is_up")) {
                    if (!containsReplica(nameResolutions, rs.getInt("part"))) {
                        NameResolution nameResolution = new NameResolution();
                        nameResolution.setFileId(rs.getLong("file_id"));
                        nameResolution.setDataNodeId(rs.getLong("data_node_id"));
                        nameResolution.setIpAddress(rs.getString("ip_address"));
                        nameResolution.setPort(rs.getInt("port"));
                        nameResolution.setPart(rs.getInt("part"));
                        nameResolution.setReplcia(rs.getInt("replica"));
                        nameResolution.setSize(rs.getLong("size"));
                        nameResolution.setUniqueChunkName(rs.getString("unique_chunk_name"));
                        nameResolutions.add(nameResolution);
                    }
                }
                System.out.println(rs.getLong("data_node_id") + " - " + rs.getString("ip_address") + rs.getString("is_up"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(RemoteFileStorageImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

        Collections.sort(nameResolutions, new NameResolutionComparator());
        return nameResolutions;
    }

    private boolean containsReplica(ArrayList<NameResolution> nameResolutions, int part) {
        return nameResolutions.stream().anyMatch((nameResolution) -> (nameResolution.getPart() == part));
    }

    @Override
    public void makeFavorite(File file) throws RemoteException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE files SET is_favorite=" + (!file.isIsFavorite()) + " WHERE id=" + file.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RemoteFileStorageImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
