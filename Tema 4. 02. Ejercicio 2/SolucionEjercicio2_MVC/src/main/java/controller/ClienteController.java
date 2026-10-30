package controller;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import dao.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import model.*;
import jakarta.servlet.RequestDispatcher;


public class ClienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String INSERTAR_O_EDITAR = "/cliente.jsp";
	private static String LISTADO = "/listadoClientes.jsp";
	private ClienteDao dao;
	public ClienteController() {
		super();
		dao = ClienteDao.getInstance();
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
		// Recuperamos los parámetros
		String strClienteId = request.getParameter("clienteId");
		String strNombre = request.getParameter("nombre");   
		String strApellidos = request.getParameter("apellidos");
		String strFechaNacimiento = request.getParameter("fechaNacimiento");
		
		
		// Creamos el objeto Cliente con los parámetros que deseamos validar
		Cliente cliente = new Cliente();
		cliente.setNombre(strNombre);
		cliente.setApellidos(strApellidos);
		cliente.setStrFechaNacimiento(strFechaNacimiento);
		
		// Validamos el objeto Cliente
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Cliente>> violations = validator.validate(cliente);
        if (violations.isEmpty()){
        	// Si cumple las validaciones
        	try {
	        	Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("fechaNacimiento"));
				cliente.setFechaNacimiento(fecha);
	        } catch (ParseException e) {
				e.printStackTrace();
			}
        	
			if(strClienteId == null || strClienteId.isEmpty())
			{
				// Si NO tiene idCliente, es nuevo
				dao.insertarCliente(cliente);
			}
			else
			{
				// Si tiene idCliente, es una actualización
				// Para no perder los pedidos, recuperamos el cliente y actualizamos la información recogida
				Cliente clienteBD = dao.obtenerClienteById(Integer.parseInt(strClienteId));
				clienteBD.setNombre(cliente.getNombre());
				clienteBD.setApellidos(cliente.getApellidos());
				clienteBD.setFechaNacimiento(cliente.getFechaNacimiento());
				dao.actualizarCliente(clienteBD); // Esta última linea no hace falta, pero si fuese una BD sería como realizar un commit
			}
			
			//en vez de enviar a la vista jsp enviamos un redirect al navegador, de esta forma
			//evitamos que si el usuario recarga la página vuelva a crear otra vez el mismo cliente
			response.sendRedirect("ClienteController?action=listado");

		} else {
	        // Concatenamos los errores de validación y los devolvemos
			System.out.println(violations.toString());
			String mensajeError="Hay errores en el formulario:<ul>";
			for (ConstraintViolation<Cliente> constraintViolation : violations) {
				mensajeError += "<li>" + constraintViolation.getMessage() + "</li> ";
		    }
			mensajeError += "</ul>";
			request.setAttribute("mensajeError", mensajeError);
			
			if(strClienteId != null && !strClienteId.isEmpty()) {
				int clienteId = Integer.parseInt(request.getParameter("clienteId"));
				cliente.setId(clienteId);
				request.setAttribute("cliente", cliente);
			}
			
			RequestDispatcher vista = request.getRequestDispatcher(INSERTAR_O_EDITAR);
			vista.forward(request, response);	
		}
		
	}

}

