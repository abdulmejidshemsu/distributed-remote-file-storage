/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datanode_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author dlham
 */
public class DBConnection {

    private static final String HOST = "localhost";
    private static final int PORT = 3306;
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "";
    private static final String DATABASE = "ds_remote_file_storage_data_node_2";
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
                connection = DriverManager.getConnection(url, USER_NAME, PASSWORD);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
