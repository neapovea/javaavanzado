package tema2;

import java.io.File;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.sqlite.SQLiteDataSource;

public class ExampleSQLInjection {
    public static void main(String[] args) {
    	
    	
        SQLiteDataSource ds = new SQLiteDataSource();
        
        
        ds.setUrl("jdbc:sqlite:/home/alx/Documentos/Propios/Formación/INAP/FT26023400001 PROGRAMACIÓN ORIENTADA A OBJETOS- JAVA AVANZADO/Tema 2. 01. Bases de datos/EjemplosTema2/data/ejemplo1.db");        
        
        String query = "SELECT * FROM Personas WHERE NOMBRE = \"" + args[0] + "\"";

        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement();) {

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
        }
    }
}
