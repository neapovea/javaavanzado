package dao;

import java.util.ArrayList;
import java.util.HashMap; 
import java.util.List;
import java.util.Map;
import model.Cliente;

import jakarta.validation.Validation; 
import jakarta.validation.Validator; 
import jakarta.validation.ConstraintViolation; 
import java.util.Set;


public class ClienteDao {
	private HashMap<Integer, Cliente> bdClientes;
	private int contadorId; // Secuencia autonumérica para tener los ids únicos
	
	// Instancia única (Singleton) 
	static private ClienteDao dao = new ClienteDao(); 
		
 
	public ClienteDao() {
		bdClientes = new HashMap<Integer, Cliente>();
		contadorId = 0; // Inicialización del contador

	}
	 
	public static ClienteDao getInstance() { 
		 return dao; 
	}	 
 
	public List<Cliente> obtenerClientes() {
	        return new ArrayList<>(bdClientes.values());
	}
	 
	public Cliente obtenerClienteById(int clienteId) {
		 return bdClientes.get(clienteId);
	}
	
	// Devuelve el siguiente ID disponible incrementando la secuencia 
	public synchronized int getSiguienteId() { 
		return ++contadorId; 
	}
	
	public List<String> insertarCliente(Cliente cliente) { 

		List<String> errores = new ArrayList<>();
		
		// Obtener la instancia del validador [6]
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

		// Validar la entidad
		Set<ConstraintViolation<Cliente>> violations = validator.validate(cliente);

		if (!violations.isEmpty()) {
		    // Extraer los mensajes de error
		    for (ConstraintViolation<Cliente> violation : violations) {
		        errores.add(violation.getMessage());
		    }
		} else {
			// coje el siguiente nº 
			cliente.setId(getSiguienteId()); 			
		    // Guardar en DAO y redirigir mediante sendRedirect [8]
			bdClientes.put(cliente.getId(), cliente);
		}
		//devuelve errores si los hay, sino devuelve la lista vacia
		return errores;
		
		  
	} 
	 
	 
	public void borrarCliente(int clienteId) {
		bdClientes.remove(clienteId);
	}
	
	public  List<String> actualizarCliente(Cliente cliente) { 
		List<String> errores = new ArrayList<>();
		
		// Obtener la instancia del validador [6]
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		// Validar la entidad
		Set<ConstraintViolation<Cliente>> violations = validator.validate(cliente);		

		if (!violations.isEmpty()) {
		    // Extraer los mensajes de error
		    
		    for (ConstraintViolation<Cliente> violation : violations) {
		        errores.add(violation.getMessage());
		    }
		} else {

			// Preservamos la lista de pedidos existente al actualizar los datos personales 
			Cliente clienteExistente = bdClientes.get(cliente.getId());
	
			
			if (clienteExistente != null) { 
				 clienteExistente.setNombre(cliente.getNombre()); 
				 clienteExistente.setApellidos(cliente.getApellidos()); 
				 clienteExistente.setFechaNacimiento(cliente.getFechaNacimiento()); 
			}
		}
		return errores;
	}
	

	
}
