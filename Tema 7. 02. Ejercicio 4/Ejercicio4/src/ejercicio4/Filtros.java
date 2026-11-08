package ejercicio4;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Clase que implementa filtros sobre listas de ciudadanos.
 * 
 * @author Miguel
 *
 */
public class Filtros {

	
	/**
	 * Obtiene una lista con los NIF de los ciudadanos, ordenados ascendentemente
	 * 
	 * @param listaCiudadanos la lista de ciudadanos 

	 * @return la lista de NIF
	 */
	public static List<String> obtenerNifsCiudadanos(List<Ciudadano> listaCiudadanos) {
		return listaCiudadanos.stream()
				.map(Ciudadano::getNIF) 
				.sorted()
				.collect(Collectors.toList());
	}


	/**
	 * Obtiene una lista con los id de expediente de todos los expedientes de la lista de ciudadanos,
	 * ordenados ascendentemente.
	 * 
	 * @param listaCiudadanos la lista de ciudadanos
	 * @return la lista de ids de expediente
	 */
	public static List<String> obtenerIdsExpedientes(List<Ciudadano> listaCiudadanos) {
		
		return listaCiudadanos.stream() 
				.flatMap(ciudadano -> ciudadano.getExpedientes().stream()) 
					.map(Expediente::getIdExpediente) 
					.sorted() 
					.collect(Collectors.toList());
	}
	
	
	/**
	 * Obtiene una lista con las salidas no leídas, ordenadas por titulo
	 * 
	 * @param listaCiudadanos la lista de ciudadanos
	 * @return la lista de salidas
	 */
	public static List<SalidaRegistro> obtenerSalidasNoLeidas(List<Ciudadano> listaCiudadanos) {
		return listaCiudadanos.stream() 
				.flatMap(ciudadano -> ciudadano.getExpedientes().stream()) 
					.flatMap(expediente -> expediente.getSalidasRegistro().stream()) 
					.filter(salida -> !salida.isLeida()) 
					.sorted((s1, s2) -> s1.getTitulo().compareTo(s2.getTitulo())) 
					.collect(Collectors.toList());
	}
	
	
	/**
	 * Obtiene una lista con los códigos de registro de las salidas no leídas, ordenados descendentemente
	 * 
	 * @param listaCiudadanos la lista de ciudadanos
	 * @return la lista con los códigos de registro
	 */
	public static List<String> obtenerCodigosRegistroSalidasNoLeidas(List<Ciudadano> listaCiudadanos) {
		return listaCiudadanos.stream() 
				.flatMap(ciudadano -> ciudadano.getExpedientes().stream()) 
					.flatMap(expediente -> expediente.getSalidasRegistro().stream()) 
					.filter(salida -> !salida.isLeida()) 
					.map(SalidaRegistro::getCodRegistro) 
					.sorted(Comparator.reverseOrder()) 
					.collect(Collectors.toList());
	}


}
