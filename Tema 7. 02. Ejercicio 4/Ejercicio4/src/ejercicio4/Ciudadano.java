package ejercicio4;

/////////////////////////////////////////////////////
//
//ESTA CLASE NO DEBE SER MODIFICADA POR EL ALUMNO
//
/////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.List;

public class Ciudadano {

	private String nif;

	private String nombre;

	private List<Expediente> expedientes;

	/**
	 * Constructor
	 * 
	 * @param nif el NIF
	 * @param nombre el nombre
	 */
	public Ciudadano(String NIF, String nombre) {
		this.nif = NIF;
		this.nombre = nombre;
		this.expedientes = new ArrayList<Expediente>();
	}
	
	/**
	 * Obtiene el NIF
	 * 
	 * @return el NIF
	 */
	public String getNIF() {
		return nif;
	}

	/**
	 * Obtiene el nombre
	 * 
	 * @return el nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Obtiene la lista de expedientes
	 * 
	 * @return la lista de expedientes
	 */
	public List<Expediente> getExpedientes() {
		return expedientes;
	}

}
