package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class ConnectionManager {

    private static Connection conn = null;

    public static Connection getConnection() {

        if (conn == null) {
            String STR_DRIVER = "org.gjt.mm.mysql.Driver";
            String STR_CONEX = "jdbc:mysql://localhost:3306/agenda";
            String USER = "root";
            String PASSWORD = "1704";

            try {
                Class.forName(STR_DRIVER);
                conn = DriverManager.getConnection(STR_CONEX, USER, PASSWORD);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro de conexão com o banco de dados!");
            }
        }
        return conn;
    }
}
