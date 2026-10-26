<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>

<head>
  <title>Listado de Pedidos</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
</head>

<body>
  <div class="container">
    <h1 class="text-center my-4">Listado de Pedidos</h1>
	<h4 class="text-center text-muted mb-4">Cliente: <c:out value="${cliente.nombre}" /> <c:out value="${cliente.apellidos}" /></h4>

    <div class="row justify-content-center">
        <div class="col-md-6">
            <table class="table table-striped table-bordered">
                <thead class="table-light">
                    <tr>
                        <th>Artículo</th>
                        <th>Precio</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${cliente.pedidos}" var="pedido">
                        <tr>
                            <td><c:out value="${pedido.articulo}" /></td>
                            <td><fmt:formatNumber value="${pedido.precio}" type="currency" currencySymbol="€"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <div class="d-flex justify-content-between mt-3">
                <a href="ClienteController?action=listado" class="btn btn-secondary">Volver</a>
                <a href="PedidoController?action=insertar&clienteId=${cliente.id}" class="btn btn-primary">Insertar Pedido</a>
            </div>
        </div>
    </div>
</body>
</html>