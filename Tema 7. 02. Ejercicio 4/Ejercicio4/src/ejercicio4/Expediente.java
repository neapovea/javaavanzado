package ejercicio4;

/////////////////////////////////////////////////////
//
//ESTA CLASE NO DEBE SER MODIFICADA POR EL ALUMNO
//
/////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.List;

public class Expediente {
	
	private String idExpediente;
	
	private List<EntradaRegistro> entradasRegistro;
	
	private List<SalidaRegistro> salidasRegistro;

	/**
	 * Constructor
	 * 
	 * @param idExpediente el identificador del expediente
	 */
	public Expediente(String idExpediente) {
		this.idExpediente = idExpediente;
		this.entradasRegistro = new ArrayList<EntradaRegistro>();
		this.salidasRegistro = new ArrayList<SalidaRegistro>();
	}

	/**
	 * Obtiene el identificador del expediente
	 * 
	 * @return el identificador del expediente
	 */
	public String getIdExpediente() {
		return idExpediente;
	}

	/**
	 * Obtiene la lista de entradas de registro
	 * 
	 * @return la lista de entradas de registro
	 */
	public List<EntradaRegistro> getEntradasRegistro() {
		return entradasRegistro;
	}

	/**
	 * Obtiene la lista de salidas de registro
	 * 
	 * @return la lista de salidas de registro
	 */
	public List<SalidaRegistro> getSalidasRegistro() {
		return salidasRegistro;
	}
	

	
	

}
