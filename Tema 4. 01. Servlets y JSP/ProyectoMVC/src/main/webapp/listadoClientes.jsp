<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>

<head>
  <title>Listado de Clientes</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
</head>

<body>
  <div class="container">
    <h1 class="text-center my-4">Listado de clientes</h1>
    <table class="table table-center table-bordered w-auto mx-auto">
      <thead>
        <tr class="text-center">
          <th>Id</th>
          <th>Nombre</th>
          <th>Apellidos</th>
          <th>Fecha Nacimiento</th>
          <th>Pedidos</th>
          <th colspan=2>Acción</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${clientes}" var="cliente">
          <tr>
            <td><c:out value="${cliente.id}" /></td>
            <td><c:out value="${cliente.nombre}" /></td>
            <td><c:out value="${cliente.apellidos}" /></td>
            <td><fmt:formatDate pattern="dd/MM/yyyy" value="${cliente.fechaNacimiento}" /></td>
            <td class="text-center"><c:out value="${cliente.numPedidos}" /></td>
            <td><a href="ClienteController?action=editar&clienteId=<c:out value="${cliente.id}"/>">Actualizar<i class="bi bi-pencil ms-1"></i></a></td>
            <td><a href="ClienteController?action=borrar&clienteId=<c:out value="${cliente.id}"/>">Borrar<i class="bi bi-trash ms-1"></i></a></td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    <div class="text-center mt-5">
    <a href="ClienteController?action=insertar" class="btn btn-primary">Agregar Cliente</a>
    </div>
  </div>
</body>
</html>