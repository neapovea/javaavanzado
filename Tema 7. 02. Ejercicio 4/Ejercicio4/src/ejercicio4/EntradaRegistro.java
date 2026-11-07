package ejercicio4;

/////////////////////////////////////////////////////
//
//ESTA CLASE NO DEBE SER MODIFICADA POR EL ALUMNO
//
/////////////////////////////////////////////////////

public class EntradaRegistro {
	
	private String codRegistro;
	
	private String titulo;

	/**
	 * Constructor
	 * 
	 * @param codRegistro el código de registro electrónico
	 * @param titulo el titulo
	 */
	public EntradaRegistro(String codRegistro, String titulo) {
		this.codRegistro = codRegistro;
		this.titulo = titulo;
	}

	
	/**
	 * Obtiene el código de registro electrónico
	 * 
	 * @return el código de registro electrónico
	 */
	public String getCodRegistro() {
		return codRegistro;
	}

	/**
	 * Obtiene el titulo
	 * 
	 * @return el titulo
	 */
	public String getTitulo() {
		return titulo;
	}
	
	
	
	
}
