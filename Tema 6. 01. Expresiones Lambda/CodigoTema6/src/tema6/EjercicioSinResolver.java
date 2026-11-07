package tema6;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class EjercicioSinResolver {
	
	private static class Cliente
	{
		public static enum Campos {
			ID,
			NOMBRE,
			APELLIDOS
		}
		
		private long id;
		private String nombre;
		private String apellidos;
		
		public Cliente(long id, String nombre, String apellidos) {
			super();
			this.id = id;
			this.nombre = nombre;
			this.apellidos = apellidos;
		}
		
		public long getId() {
			return id;
		}

		public String getNombre() {
			return nombre;
		}

		public String getApellidos() {
			return apellidos;
		}

		@Override
		public String toString() {
			return "Cliente [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + "]";
		}

		
	}

	public static void main(String[] args) {
		
		//Obtenemos la lista de clientes de la fuente:
		List<Cliente> todos = obtenerTodosLosClientes();
		
		//Obtenemos los clientes de la página 2, tamaño de página 5,
		//ordenada por apellidos:
		
		List<Cliente> filtrados = obtenerClientesPagina(todos,2,5,Cliente.Campos.APELLIDOS);
		
		filtrados.forEach(cliente -> System.out.println(cliente));
		
	}


	private static List<Cliente> obtenerTodosLosClientes() {
		
		ArrayList<Cliente> resultado = new ArrayList<Cliente>();
		
		resultado.add(new Cliente(1,"NESTOR","MOREIRA"));
		resultado.add(new Cliente(2,"EMILIANA","ORDOÑEZ"));
		resultado.add(new Cliente(3,"ANGELICA","CASTELLO"));
		resultado.add(new Cliente(4,"VERONICA","BARROSO"));
		resultado.add(new Cliente(5,"DOLORES","AGUIRRE"));
		resultado.add(new Cliente(6,"ASIER","CHACON"));
		resultado.add(new Cliente(7,"LEONOR","ARCE"));
		resultado.add(new Cliente(8,"JUAN","CASTILLA"));
		resultado.add(new Cliente(9,"LUIS","NOVOA"));
		resultado.add(new Cliente(10,"CANDELARIA","BERNABE"));
		resultado.add(new Cliente(11,"TATIANA","RIBAS"));
		resultado.add(new Cliente(12,"CESAREO","DE MIGUEL"));
		resultado.add(new Cliente(13,"CARLOS","PADRON"));
		resultado.add(new Cliente(14,"BENITA","FRUTOS"));
		resultado.add(new Cliente(15,"ILDEFONSO","TALAVERA"));
		resultado.add(new Cliente(16,"LAUREANO","ROSELLO"));
		resultado.add(new Cliente(17,"SEGUNDO","INIESTA"));
		resultado.add(new Cliente(18,"JUSTO","VAQUERO"));
		resultado.add(new Cliente(19,"PASCUAL","BARRANCO"));
		resultado.add(new Cliente(20,"ARACELI","JAEN"));

		return resultado;
	}
	
	public static List<Cliente> obtenerClientesPagina(
			List<Cliente> clientes, 
			int pageNum, 
			int pageSize,
			Cliente.Campos sortBy) {
		
		//TODO: a implementar por el alumno.
		return null;	
	}


}
