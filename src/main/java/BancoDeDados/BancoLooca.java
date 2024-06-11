package BancoDeDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BancoLooca {

//    private static final String url1 = "jdbc:sqlserver://52.20.245.218:1433;databaseName=securityWings";
//
//    private static final String user1 = "sa";
//    private static final String password1 = "Urubu.100";
//
//    private static Connection conn1;
//
//    public static Connection getbancoLooca() {
//
//            try {
//                if (conn1 == null) {
//                    conn1 = DriverManager.getConnection(url1, user1, password1);
//                    return conn1;
//                } else {
//                    return conn1;
//                }
//
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//
//    }

    private static final String url = "jdbc:mysql://localhost:3306/securityWings";

    private static final String user = "root";
    private static final String password = "secwings100";

    private static Connection conn;

    public static Connection getbancoLooca2() {

        try {
            if (conn == null) {
                conn = DriverManager.getConnection(url, user, password);
                return conn;
            } else {
                return conn;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}

