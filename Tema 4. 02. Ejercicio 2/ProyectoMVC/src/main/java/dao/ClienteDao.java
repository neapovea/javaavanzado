package dao;

import java.util.ArrayList;
import java.util.HashMap; 
import java.util.List;
import java.util.Map;
import model.Cliente;

public class ClienteDao {
	private HashMap<Integer, Cliente> bdClientes;
	
	// Instancia única (Singleton) 
	static private ClienteDao dao = new ClienteDao(); 
		
 
	public ClienteDao() {
		bdClientes = new HashMap<Integer, Cliente>();

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
	 
	public void insertarCliente(Cliente cliente) { 
		 bdClientes.put(cliente.getId(), cliente); 
	} 
	 
	 
	public void borrarCliente(int clienteId) {
		bdClientes.remove(clienteId);
	}
	
	public void actualizarCliente(Cliente cliente) { 
		// Preservamos la lista de pedidos existente al actualizar los datos personales 
		Cliente clienteExistente = bdClientes.get(cliente.getId()); 
		if (clienteExistente != null) { 
			 clienteExistente.setNombre(cliente.getNombre()); 
			 clienteExistente.setApellidos(cliente.getApellidos()); 
			 clienteExistente.setFechaNacimiento(cliente.getFechaNacimiento()); 
		} 
	}
	
}
