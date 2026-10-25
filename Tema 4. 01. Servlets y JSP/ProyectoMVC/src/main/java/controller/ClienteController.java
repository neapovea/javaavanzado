package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import dao.*;
import model.*;

public class ClienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String INSERTAR_O_EDITAR = "/cliente.jsp";
	private static String LISTADO = "/listadoClientes.jsp";
	private ClienteDao dao;

	public ClienteController() {
		super();
		dao = new ClienteDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String urlVista = "";
		String action = request.getParameter("action");

		switch (action) {
		case "borrar": {
			int clienteId = Integer.parseInt(request.getParameter("clienteId"));
			dao.borrarCliente(clienteId);
			request.setAttribute("clientes", dao.obtenerClientes());
			urlVista = LISTADO;
			break;
		}
		case "editar": {
			int clienteId = Integer.parseInt(request.getParameter("clienteId"));
			Cliente cliente = dao.obtenerClienteById(clienteId);
			request.setAttribute("cliente", cliente);
			urlVista = INSERTAR_O_EDITAR;
			break;
		}
		case "listado": {
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recuperamos los parámetros
		String strClienteId = request.getParameter("clienteId");
		String strNombre = request.getParameter("nombre");
		String strApellidos = request.getParameter("apellidos");
		String strFechaNacimiento = request.getParameter("fechaNacimiento");
		String strNumPedido = request.getParameter("numPedidos");

		// Creamos la clase Cliente y le asignamos los valores
		try {
			Cliente cliente = new Cliente();
			cliente.setNombre(strNombre);
			cliente.setApellidos(strApellidos);
			Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(strFechaNacimiento);
			//Date fecha = new SimpleDateFormat("dd/MM/yyyy").parse(strFechaNacimiento);
			cliente.setFechaNacimiento(fecha);
			cliente.setNumPedidos(Integer.parseInt(strNumPedido));
			if (strClienteId == null || strClienteId.isEmpty()) {
				// Si NO tiene idCliente, es nuevo
				dao.insertarCliente(cliente);
			} else {
				// Si tiene idCliente, es una actualización
				cliente.setId(Integer.parseInt(strClienteId));
				dao.actualizarCliente(cliente);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// en vez de enviar a la vista jsp enviamos un redirect al navegador, de esta
		// forma
		// evitamos que si el usuario recarga la página vuelva a crear otra vez el mismo
		// cliente
		response.sendRedirect("ClienteController?action=listado");
		
		/*
        RequestDispatcher vista = request.getRequestDispatcher(LISTADO);
        request.setAttribute("clientes", dao.obtenerClientes());
        vista.forward(request, response);
        */
	}
}
