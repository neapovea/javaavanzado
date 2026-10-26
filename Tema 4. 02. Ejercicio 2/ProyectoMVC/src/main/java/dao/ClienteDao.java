package dao;

import java.util.ArrayList;
import java.util.HashMap; 
import java.util.List;
import java.util.Map;
import model.Cliente;

public class ClienteDao {
	private static Integer id = 1;
	// Instancia única Singlenton //private HashMap<Integer, Cliente> bdClientes;
	private static Map<Integer, Cliente> bdClientes = new HashMap<>();
 
 public ClienteDao() {
	 bdClientes = new HashMap<Integer, Cliente>();
	 id = 1;
 }
 
 public void insertarCliente(Cliente cliente) {
	 cliente.setId(id);
	 bdClientes.put(id, cliente);
	 id++;
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

 public List<Cliente> obtenerClientes() {
	 return new ArrayList<Cliente>(bdClientes.values());
 }
 public Cliente obtenerClienteById(int clienteId) {
	 return bdClientes.get(clienteId);
 }
}
