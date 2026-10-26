package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.Cliente;

public class ClienteDao {
 private Integer id = 0;
 private HashMap<Integer, Cliente> bdClientes;
 
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
	 bdClientes.put(cliente.getId(), cliente);
	 
 }
 public List<Cliente> obtenerClientes() {
	 return new ArrayList<Cliente>(bdClientes.values());
 }
 public Cliente obtenerClienteById(int clienteId) {
	 return bdClientes.get(clienteId);
 }
}
