package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCon {

    final static String url = "jdbc:mysql://localhost/mysql";
    final static String user = "user1";
    final static String password = "passw!";
    private static DBConnectionString prefConnection;

    private Connection DBConnect;

    public DBCon() {
    }

    public static Connection connection() {
        Connection con = null;
        try {
            if (prefConnection != null) {
                con = DriverManager.getConnection(prefConnection.getUrl(),
                        prefConnection.getUser(),
                        prefConnection.getPassword());
            } else {
                throw new SQLException("failed to open connect for given user info");
            }
        }catch(SQLException e){
            System.out.println("Failed to open connection");
        }
        return con;
    }

    public void setPrefConnection(String url, String user, String password){
        prefConnection = new DBConnectionString(url, user, password);
    }

    public void connect(){
        try {
            DBConnect = DriverManager.getConnection(url, user, password);
            System.out.println("successful connection!");
        } catch (SQLException e) {
            System.out.println("Failed to open connection");
        }
    }

    public void disconnect(){
        if(DBConnect != null){
            try {
                DBConnect.close();
            } catch (SQLException e) {
                System.out.println("Failed to close connection");
            }
        }
    }

    public static void disconnect(Connection conn){
        if(conn != null){
            try {
                conn.close();
                System.out.println("Connection closed");
            } catch (SQLException e) {
                System.out.println("Can't close connection");
            }
        }
    }
}
