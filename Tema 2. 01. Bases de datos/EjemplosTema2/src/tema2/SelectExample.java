package tema2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.sqlite.SQLiteDataSource;

public class SelectExample {
    public static void main(String[] args) {
        SQLiteDataSource ds = null;
        Connection conn = null;
        Statement stmt = null;

        try {
            ds = new SQLiteDataSource();
            
            ds.setUrl("jdbc:sqlite:/home/alx/Documentos/Propios/Formación/INAP/FT26023400001 PROGRAMACIÓN ORIENTADA A OBJETOS- JAVA AVANZADO/Tema 2. 01. Bases de datos/EjemplosTema2/data/ejemplo1.db");        
            
            String query = "SELECT * FROM Personas";
           

            conn = ds.getConnection();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("ID");
                String nombre = rs.getString("NOMBRE");
                String apellidos = rs.getString("APELLIDOS");
                System.out.println("Fila: ID = " + id + ", NOMBRE = " + nombre + ", APELLIDOS = " + apellidos);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace(System.err);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                    System.out.println("Statement cerrado");
                } catch (SQLException e) {
                    System.err.println("Error al cerrar Statement");
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Conexión cerrada");
                } catch (SQLException e) {
                    System.err.println("Error al cerrar la conexión");
                    e.printStackTrace();
                }
            }
        }
    }
}
