package Conexion;	
import Conexion.conexion;
import java.sql.Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {
	
    private static Connection conn;
    private static final String URL = "jdbc:mysql://localhost:3307/ClinicaVeterinariaABC"; 
    private static final String USER = "root";
    private static final String PASS = ""; 
    
    public static Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(URL, USER, PASS);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}














//jdbc:mysql://localhots:3306/bd_ClinicaVeterinaria","root","123"