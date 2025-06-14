package model;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.*;

public class ConnectDB {

    private static final String server = "LEGION-5";
    private static final String user = "sa";
    private static final String password = "123456";
    private static final String db = "cafe";
    private static final int port = 1433;

    public static Connection getConnection() {
        Connection con = null;
        try {
            SQLServerDataSource ds = new SQLServerDataSource();
            ds.setUser(user);
            ds.setPassword(password);
            ds.setPortNumber(port);
            ds.setDatabaseName(db);
            ds.setServerName(server);
            ds.setTrustServerCertificate(true);
            con = ds.getConnection();
            System.out.println("Kết nối thành công");
            System.out.println(con.getCatalog());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void main(String[] args) {
        getConnection();
    }
}
