package tema2;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.sqlite.SQLiteDataSource;

public class PreparedStatementExample {
    public static void main(String[] args) {
        SQLiteDataSource ds = new SQLiteDataSource();
        
        
        ds.setUrl("jdbc:sqlite:/home/alx/Documentos/Propios/Formación/INAP/FT26023400001 PROGRAMACIÓN ORIENTADA A OBJETOS- JAVA AVANZADO/Tema 2. 01. Bases de datos/EjemplosTema2/data/ejemplo1.db");        
        
       
        String query = "SELECT * FROM Personas WHERE NOMBRE = ? AND APELLIDOS = ?";

        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);) {

            stmt.setString(1, "MARIA");
            stmt.setString(2, "GARCIA PEREZ");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String nombre = rs.getString("NOMBRE");
                String apellidos = rs.getString("APELLIDOS");
                System.out.println("Fila: ID = " + id + ", NOMBRE = " + nombre + ", APELLIDOS = " + apellidos);
            }

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }
}
