package controller;

import java.io.IOException; 
import java.math.BigDecimal;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.*;
import model.*; 


/**
 * Servlet implementation class PedidoController
 */


public class PedidoController extends HttpServlet { 
	private static final long serialVersionUID = 1L;
	private static String INSERTAR = "/pedido.jsp";
	private static String LISTADO = "/listadoPedidos.jsp";
	
	private ClienteDao dao; 
	public PedidoController() { 
		super(); dao = ClienteDao.getInstance(); // Acceso al mismo DAO Singleton 	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String urlVista = "";
		if ("listado".equals(action)) { 
			int clienteId = Integer.parseInt(request.getParameter("clienteId"));
			Cliente cliente = dao.obtenerClienteById(clienteId);
			request.setAttribute("cliente", cliente);
			urlVista = LISTADO;
		} else if ("insertar".equals(action)) { // 
			int clienteId = Integer.parseInt(request.getParameter("clienteId"));
			request.setAttribute("clienteId", clienteId);
			urlVista = INSERTAR;
		}

		RequestDispatcher view = request.getRequestDispatcher(urlVista);
		view.forward(request, response);
	} 

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		int clienteId = Integer.parseInt(request.getParameter("clienteId"));
		String articulo = request.getParameter("articulo");
		String precioStr = request.getParameter("precio");
		
		BigDecimal precio = BigDecimal.ZERO;
		try { 
			if (precioStr != null && !precioStr.trim().isEmpty()) {
				precio = new BigDecimal(precioStr.replace(",", "."));
			} 
		} catch (Exception e) { 
			e.printStackTrace();
		} 

		// Recuperamos el cliente y añadimos el nuevo pedido 
		Cliente cliente = dao.obtenerClienteById(clienteId);
		if (cliente != null) { 
			Pedido pedido = new Pedido(articulo, precio);
			cliente.getPedidos().add(pedido);
		} // Redirección mediante GET al listado de pedidos para evitar registros duplicados  
			response.sendRedirect("PedidoController?action=listado&clienteId=" + clienteId);
	}
}