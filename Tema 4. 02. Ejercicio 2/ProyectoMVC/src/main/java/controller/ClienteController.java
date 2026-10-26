package controller;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;

import jakarta.validation.Validation; 
import jakarta.validation.Validator; 
import jakarta.validation.ConstraintViolation; 
import java.util.Set;

import dao.*;
import model.*;


public class ClienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String INSERTAR_O_EDITAR = "/cliente.jsp";
	private static String LISTADO = "/listadoClientes.jsp";
	private ClienteDao dao;
	public ClienteController() {
		super();
		dao = ClienteDao.getInstance();	// Acceso mediante Singleton
	}
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		String urlVista="";
		String action = request.getParameter("action");
		
		switch (action) {
		case "borrar": {
			int clienteId = Integer.parseInt(request.getParameter("clienteId"));
			dao.borrarCliente(clienteId);
			request.setAttribute("clientes", dao.obtenerClientes());
			urlVista = LISTADO;
			break;
		}
		case "editar" :{
			int clienteId = Integer.parseInt(request.getParameter("clienteId"));
			Cliente cliente = dao.obtenerClienteById(clienteId);
			request.setAttribute("cliente", cliente);
			urlVista = INSERTAR_O_EDITAR;
			break;
		}
		case "listado" : {
			request.setAttribute("clientes", dao.obtenerClientes());
			urlVista = LISTADO;
			break;
		}
		default:
			urlVista = INSERTAR_O_EDITAR;
		}
		RequestDispatcher vista = request.getRequestDispatcher(urlVista);
		vista.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{ 
		String urlVista="";
		// Recuperamos los parámetros
		String strClienteId = request.getParameter("clienteId");
		String strNombre = request.getParameter("nombre");   
		String strApellidos = request.getParameter("apellidos");
		String strFechaNacimiento = request.getParameter("fechaNacimiento");
		
		String strNumPedido = request.getParameter("numPedidos");
	
		// Creamos la clase Cliente y le asignamos los valores
		try {
			Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(strFechaNacimiento);
			if(strClienteId == null || strClienteId.isEmpty())
			{
			   // Si NO tiene idCliente, es nuevo
			   Cliente cliente = new Cliente();
			   cliente.setNombre(strNombre);
			   cliente.setApellidos(strApellidos);
			   
			   cliente.setFechaNacimiento(fecha);
			   int nuevoId = dao.obtenerClientes().size() + 1;
			   cliente.setId(nuevoId);
			   dao.insertarCliente(cliente);
			}
			else
			{
  			    // Si tiene idCliente, es una actualización
				// Modificación respetando los pedidos existentes 
				int id = Integer.parseInt(strClienteId); 
				Cliente cliente = dao.obtenerClienteById(id); 
				if (cliente != null) { 
					// si existe cliente actualizmos datos
					cliente.setNombre(strNombre); 
					cliente.setApellidos(strApellidos); 
					cliente.setFechaNacimiento(fecha); 
					dao.actualizarCliente(cliente); 
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//en vez de enviar a la vista jsp enviamos un redirect al navegador, de esta forma
		//evitamos que si el usuario recarga la página vuelva a crear otra vez el mismo cliente
		//response.sendRedirect("ClienteController?action=listado");		
		RequestDispatcher vista = request.getRequestDispatcher(LISTADO);
		request.setAttribute("clientes", dao.obtenerClientes());
		vista.forward(request, response);
		
	}
}
