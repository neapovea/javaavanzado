package tema2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.sqlite.SQLiteDataSource;

public class EjercicioResuelto {

	public static void main(String[] args) throws Exception {
		
		//Creamos la lista de personas:
		ArrayList<Persona> listaPersonas = new ArrayList<Persona>();
		
		
		
		//Paso 1: Leemos el fichero
		try (BufferedReader reader = new BufferedReader(new FileReader("EjercicioResueltoTema2/data/clientes.txt")))
		{
			String linea = reader.readLine();  //la primera línea es la cabecera y la vamos a ignorar.
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						
	        for (int numLinea = 1; linea!=null; numLinea++){
	        	
	            linea = reader.readLine();
	            
	            if (linea != null)
	            {
	            	String[] valores = linea.split(";");
	            	if (valores.length != 5) {
	            		throw new Exception("Error en línea " + numLinea + ": se esperaban 5 columnas pero la línea tiene " + valores.length + " columnas" );
	            	}
	            	Persona persona = new Persona (
	            							Integer.parseInt(valores[0]),
	            							valores[1],
	            							valores[2],
	            							dateFormat.parse(valores[3]),
	            							Integer.parseInt(valores[4])
	            							);
	            	
	            	listaPersonas.add(persona);
	            }
	        }
		}
		catch (IOException e)
		{
			System.err.println("Error al leer fichero: " + e.getMessage());
			e.printStackTrace(System.err);
		}
		
		/*
		for (Persona persona: listaPersonas){
			System.out.println(persona);
		}
		*/
		
		//Paso 2: Escribimos en base de datos:
		SQLiteDataSource ds = new SQLiteDataSource();
		ds.setUrl("jdbc:sqlite:EjercicioResueltoTema2/data/clientes.db");
		
		SimpleDateFormat datFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		int numFilasInsertadas = 0;


		try (Connection conn = ds.getConnection();
		    PreparedStatement stmt = conn.prepareStatement("INSERT INTO CLIENTES VALUES (?,?,?,?,?)");) {
			
			//Obs�rvese que preparamos el statement una �nica vez y utilizamos el mismo statement
			//para todas las filas.
			
			for (Persona persona: listaPersonas) {
				stmt.setInt(1, persona.getId());
				stmt.setString(2, persona.getNombre());
				stmt.setString(3, persona.getApellidos());
				stmt.setString(4, datFormat.format(persona.getFechaNacimiento()));
				stmt.setInt(5, persona.getNumPedidos());
				
				numFilasInsertadas += stmt.executeUpdate();
			}
					
		} catch (SQLException e) {
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace(System.err);
		}
		
		System.out.println(numFilasInsertadas + " filas insertadas");
		
		//3. Listar personas nacidas antes de 01/01/1978 con m�s de 10 pedidos.
		
		System.out.println("Personas nacidas antes de 01/01/1978 con más de 10 pedidos:");
		
		try (Connection conn = ds.getConnection();
			    PreparedStatement stmt = 
			    		conn.prepareStatement("SELECT * FROM CLIENTES WHERE FECHA_NACIMIENTO < ? AND NUM_PEDIDOS > ?");) {
				
				stmt.setString(1, "1978-01-01");
				stmt.setInt(2, 10);
				
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					Persona persona = new Persona(
											rs.getInt("ID"),
											rs.getString("NOMBRE"),
											rs.getString("APELLIDOS"),
											datFormat.parse(rs.getString("FECHA_NACIMIENTO")),
											rs.getInt("NUM_PEDIDOS")
											);

					System.out.println(persona);
				}
			} catch (SQLException e) {
				System.err.println("Error: " + e.getMessage());
				e.printStackTrace(System.err);
			}

	}
	
	
	private static class Persona
	{
		private int id;
		private String nombre;
		private String apellidos;
		private Date fechaNacimiento;
		private int numPedidos;
		
		
		/**
		 * Constructor.
		 * 
		 * @param id identificador único
		 * @param nombre nombre
		 * @param apellidos apellidos
		 * @param fechaNacimiento fecha de nacimiento
		 * @param numPedidos número de pedidos
		 */
		public Persona(int id, String nombre, String apellidos, Date fechaNacimiento, int numPedidos) {
			super();
			this.id = id;
			this.nombre = nombre;
			this.apellidos = apellidos;
			this.fechaNacimiento = fechaNacimiento;
			this.numPedidos = numPedidos;
		}
		
		public int getId() {
			return id;
		}
		public String getNombre() {
			return nombre;
		}
		public String getApellidos() {
			return apellidos;
		}
		public Date getFechaNacimiento() {
			return fechaNacimiento;
		}
		public int getNumPedidos() {
			return numPedidos;
		}

		@Override
		public String toString() {
			return "Persona [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", fechaNacimiento="
					+ new SimpleDateFormat("dd/MM/yyyy").format(fechaNacimiento) + ", numPedidos=" + numPedidos + "]";
		}
		
		
		
	}

}
