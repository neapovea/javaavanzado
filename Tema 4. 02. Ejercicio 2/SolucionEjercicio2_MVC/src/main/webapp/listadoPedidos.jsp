<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>

<head>
  <title>Listado de pedidos</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</head>

<body>
  <div class="container">
    <h1 class="text-center my-4">Listado de Pedidos</h1>
    <div class="d-flex justify-content-center mb-4">
      <span class="fs-4">Cliente: ${cliente.nombre} ${cliente.apellidos}</span>
    </div>
    <table class="table table-center table-bordered w-auto mx-auto">
      <thead>
        <tr class="text-center">
        <th>Artículo</th>
        <th>Precio</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${cliente.pedidos}" var="pedido">
          <tr>
            <td><c:out value="${pedido.articulo}" /></td>
            <td class="text-end"><fmt:formatNumber value="${pedido.precio}" type="currency" pattern="#,###.00"/></td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    <div class="text-center mt-5">
      <a href="." class="btn btn-secondary me-1">Volver</a>
      <a href="PedidoController?action=insertar&clienteId=${cliente.id}" class="btn btn-primary">Insertar Pedido</a>
    </div>
  </div>
</body>
</html>