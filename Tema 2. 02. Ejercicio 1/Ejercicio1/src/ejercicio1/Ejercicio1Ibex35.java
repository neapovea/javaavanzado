package ejercicio1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.sqlite.SQLiteDataSource;



/**
 * Curso Java Avanzado - Ejercicio 1
 * Extracción de cotizaciones del IBEX 35 mediante Web Scraping y Expresiones Regulares, Guardando los datos en BBDD
 * Esta clase se conecta a la página HTML de cotizaciones del IBEX 35,
 * procesa el documento eliminando saltos de línea y aplica patrones Regex
 * para extraer el nombre de cada empresa y su última cotización.
 * En un segundo paso guarda los datos en una BBDD, borrando los que haya anteriormente en la Tabla.
 *  */
public class Ejercicio1Ibex35 {

	//Creamos la lista de empresasIbex:
	

    public static void main(String[] args) {
    		ArrayList<empresaIbex> listaEmpresas = new ArrayList<empresaIbex>();

    		System.out.println("=========================================================");
            System.out.println("   EJERCICIO 1 ALEJANDRO MAILLARD  (JAVA AVANZADO) ");
            System.out.println("=========================================================");
            
    		if (extraerDatos(listaEmpresas, Boolean.FALSE)) {
    			if (guardarDatos(listaEmpresas, Boolean.FALSE)) {
    				System.out.println("Fin del proceso");
    			}
    		}
    }


    /**
     * Primera parte: extracción de la información conectar a la web y carga la lista de empresas con su última cotización
     * @param listaEmpresas ArrayList con datos de las empresas del tipo empresaIbex
     * @param mostrarDatosLeidos si es TRUE muestra los datos cargados en la BBDD.
     * @return TRUE si todo ha ido bien, en caso contrario false
     * @throws Exception Si ocurre algún error
     **/
    private static Boolean extraerDatos(ArrayList<empresaIbex> listaEmpresas, Boolean mostrarDatosLeidos) {
        // URL sitio web
        String URL_TARGET = "https://taferk.github.io/CursoJavaAvanzado/site/ejercicio1_ibex35.html";
        System.out.println("Conectando al sitio web: " + URL_TARGET);
        
        try {
            // Descargar el código HTML
            String htmlContent = descargarHTML(URL_TARGET);

            // Quitar todos los saltos de línea para dejar todo el HTML en una sola línea de texto
            String htmlSingleLine = htmlContent.replaceAll("\\r?\\n", " ");

            // Extraer el <tbody> de la tabla con id "id-listado-acciones"
            // Se utiliza el cuantificador "lazy" (.+?) para detener la coincidencia en la primera aparición
            Pattern tableBodyPattern = Pattern.compile("<table.+?id=\"id-listado-acciones\".+?<tbody>(.+?)</tbody>.+?</table>", Pattern.CASE_INSENSITIVE);
            Matcher tableBodyMatcher = tableBodyPattern.matcher(htmlSingleLine);

            if (tableBodyMatcher.find()) {
                String tbodyContent = tableBodyMatcher.group(1);

                // Extraer los datos de cada fila <tr> dentro del tbody
                // Grupo 1: Nombre de la empresa (dentro del primer <td>)
                // Grupo 2: Valor de la última cotización (dentro del segundo <td>, habitualmente dentro de un <span>)
                Pattern trPattern = Pattern.compile("<tr>\\s*<td[^>]*>(.*?)</td>\\s*<td[^>]*>(?:<span[^>]*>)?(.*?)(?:</span>)?</td>", Pattern.CASE_INSENSITIVE);
                Matcher trMatcher = trPattern.matcher(tbodyContent);

                int contador = 0;

                if (mostrarDatosLeidos) {
                	System.out.println("Datos leidos:");
                	// Cabecera formateada para la salida por consola
                	System.out.printf("\t%-4s | %-22s | %-18s%n", "Nº", "Acción / Empresa", "Última Cotización");
                	System.out.println("\t---------------------------------------------------------");
                }

                // Recorrer todas las coincidencias encontradas
                while (trMatcher.find()) {
                	contador++;
                	String nombreEmpresa = trMatcher.group(1).trim();
                	String cotizacion = trMatcher.group(2).trim();

                	// Limpieza de etiquetas HTML internas o espacios extra si existieran
                	nombreEmpresa = nombreEmpresa.replaceAll("<[^>]*>", "").trim();
                	cotizacion = cotizacion.replaceAll("<[^>]*>", "").trim();
                    
                	// Cargar los datos en el objeto empresaIbex
                    empresaIbex empresa = new empresaIbex (
							contador,
							nombreEmpresa,
							cotizacion
							);

                    // Agregar empresas a Array para poder guardarlas despues en la BBDD
	            	listaEmpresas.add(empresa);
	            	if (mostrarDatosLeidos) {
	            		// Devolver empresa cargada con su información
	            		System.out.printf("\t%-4d | %-22s | %-18s%n", contador, nombreEmpresa, cotizacion);
	            	}

                }

                System.out.println("Total de registros extraídas: " + contador);

            } else {
                System.err.println("Error: No se ha podido localizar la tabla 'id-listado-acciones' en el HTML.");
                return false;
            }

        } catch (Exception e) {
            System.err.println("Ocurrió un error inesperado al procesar las cotizaciones:");
            e.printStackTrace();
            return false;
        }
    	
    	return true;
    	
    	
    }
    

    /**
     * Con una conexión HTTP a la URL devuelve el contenido HTML como una String.
     * Utiliza UTF-8 para garantizar la correcta codificación de caracteres especiales y acentos.
     * 
     * @param urlString Dirección URL a consultar
     * @return Contenido HTML completo de la página web
     * @throws Exception Si ocurre algún error en la conexión o lectura de datos
     */
    private static String descargarHTML(String urlString) throws Exception {
        URL url = URI.create(urlString).toURL();
        URLConnection connection = url.openConnection();

        // Configuración de cabeceras HTTP (User-Agent) para asegurar una respuesta exitosa
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) JavaClient/1.0");
        connection.setConnectTimeout(10000); // 10 segundos de tiempo de espera para conexión
        connection.setReadTimeout(10000);    // 10 segundos de tiempo de espera para lectura

        StringBuilder sb = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        }

        return sb.toString();
    }

    /*
     *
     */

    /**
     * Segunda parte: almacenamiento en base de datos
     * @param listaEmpresas ArrayList con datos de las empresas del tipo empresaIbex.
     * @param mostrarDatosCargados si es TRUE muestra los datos cargados en la BBDD.
     * @return TRUE si todo ha ido bien, en caso contrario false
     * @throws Exception Si ocurre algún error
     **/
    private static  Boolean guardarDatos(ArrayList<empresaIbex> listaEmpresas, Boolean mostrarDatosCargados) {
        
    	
		SQLiteDataSource ds = new SQLiteDataSource();
		ds.setUrl("jdbc:sqlite:Ejercicio1/data/cotizaciones.db");

    	// Borrar todas las filas de la tabla (SQL: “DELETE FROM COTIZACIONES”).
        try (Connection conn = ds.getConnection();
        		Statement stmt = conn.createStatement();) {        	
        		stmt.executeUpdate("DELETE FROM COTIZACIONES");
        		System.out.println("Borrado de los registro actuales de la tabla COTIZACIONES");
        		
        } catch (SQLException e) {
        		System.err.println("Error: " + e.getMessage());
        		e.printStackTrace(System.err);
        		return false;
        }
        
    	//A continuación insertará una fila por cada uno de los 35 valores leídos		
		int numEmpresasProcesadas = 0;

		try (Connection conn = ds.getConnection();
		    PreparedStatement stmt = conn.prepareStatement("INSERT INTO COTIZACIONES VALUES (?,?)");) {

			//Cargamos los datos por cada empresa en la BBDD
			for (empresaIbex empresa: listaEmpresas) {
//				stmt.setInt(1, empresa.getId());
				stmt.setString(1, empresa.getNombre());
				stmt.setString(2, empresa.getultimaCotizacion());
				
				numEmpresasProcesadas += stmt.executeUpdate();
			}
    		System.out.println("Insertados los registro en la tabla COTIZACIONES");
					
		} catch (SQLException e) {
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace(System.err);
			return false;
		}
		
		System.out.println("Registrados datos de " + numEmpresasProcesadas + " empresas.");

		if (mostrarDatosCargados) {
			String query = "SELECT * FROM COTIZACIONES";
	        try (Connection conn = ds.getConnection();
		             PreparedStatement stmt = conn.prepareStatement(query);) {

	        	System.out.println("Datos de la tabla COTIZACIONES:");
	            ResultSet rs = stmt.executeQuery();			        	
	            while (rs.next()) {
	                String nombre = rs.getString("NOMBRE");
	                String cotizacion = rs.getString("ULTIMO");
	                System.out.println("\tFila: NOMBRE = " + nombre + ", ULTIMO = " + cotizacion);
	            }

	           } catch (SQLException e) {
	               System.err.println("Error: " + e.getMessage());
	               e.printStackTrace(System.err);
	               return false;
	           }        
			
		}
    	
    	return true;
    	
    }
        
    
	
	private static  class empresaIbex
	{
		private int id;
		private String nombre;
		private String ultimaCotizacion;
		
		
		/**
		 * Constructor.
		 * 
		 * @param id identificador único
		 * @param nombre nombre
		 * @param ultimaCotizacion ultimaCotizacion
		 */
		public empresaIbex(int id, String nombre, String ultimaCotizacion) {
			super();
			this.id = id;
			this.nombre = nombre;
			this.ultimaCotizacion = ultimaCotizacion;
		}
		
		public int getId() {
			return id;
		}
		public String getNombre() {
			return nombre;
		}
		public String getultimaCotizacion() {
			return ultimaCotizacion;
		}

		@Override
		public String toString() {
			return "Empresa [id=" + id + ", nombre=" + nombre + ", apellidos=" + ultimaCotizacion;
		}
		
		
		
	}
}