package controller;


import java.io.IOException;
import java.math.BigDecimal;
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
import jakarta.servlet.ServletContext;


public class PedidoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String INICIO = "/index.jsp";
	private static String INSERTAR = "/pedido.jsp";
	private static String LISTADO = "/listadoPedidos.jsp";
			
	private ClienteDao dao;
	
	public PedidoController() {
		super();
		dao = ClienteDao.getInstance();
	}
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		String urlVista="";
		String action = request.getParameter("action");
		String strClienteId = request.getParameter("clienteId");
		
		switch (action) {
		case "listado" : {
			// Recuperamos el cliente y sus pedidos
			int clienteId = Integer.parseInt(strClienteId);
			Cliente cliente = dao.obtenerClienteById(clienteId);
			request.setAttribute("cliente", cliente);
			request.setAttribute("pedidos", cliente.getPedidos());
			urlVista = LISTADO;
			break;
		}
		default:
			// Transmitimos el indentificador del cliente
			int clienteId = Integer.parseInt(strClienteId);
			request.setAttribute("clienteId", clienteId);
			urlVista = INSERTAR;
		}
		RequestDispatcher vista = request.getRequestDispatcher(urlVista);
		vista.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{ 
		// Recuperamos los parámetros recibidos
		String clienteId = request.getParameter("clienteId");
		String articulo = request.getParameter("articulo");
		String strPrecio = request.getParameter("strPrecio");
		
		
		// Creamos el objeto pedido con los parámetros recibidos
		Pedido pedido = new Pedido();
		pedido.setArticulo(articulo);
		pedido.setStrPrecio(strPrecio);

		// Validamos el objeto pedido
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Pedido>> violations = validator.validate(pedido);
		
		RequestDispatcher vista = null;
        if (violations.isEmpty()){
		    // Si es valido
        	// Obtengo el ciente
			Cliente cliente = dao.obtenerClienteById(Integer.parseInt(clienteId));
			// Se añade el pedido (ojo, el precio lo guardamos en el atributo numerico precio)
			pedido.setPrecio(new BigDecimal(pedido.getStrPrecio()));
			cliente.getPedidos().add(pedido);
			dao.actualizarCliente(cliente);
			
			response.sendRedirect("PedidoController?action=listado&clienteId="+clienteId);
		
        }else{
	        // Concatenamos los errores de validación y los devolvemos 
			String mensajeError="Hay errores en el formulario:<ul>";
			for (ConstraintViolation<Pedido> constraintViolation : violations) {
				mensajeError += "<li>" + constraintViolation.getMessage() + "</li> ";
		    }
			mensajeError += "</ul>";
			request.setAttribute("mensajeError", mensajeError);
			request.setAttribute("clienteId", clienteId);
			vista = request.getRequestDispatcher(INSERTAR);
			vista.forward(request, response);
        }
		
	}
}