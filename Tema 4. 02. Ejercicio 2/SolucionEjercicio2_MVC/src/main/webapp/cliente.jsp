<%@ page language="java" contentType="text/html; charset=UTF-8"	%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>

<head>
  <title>Datos cliente</title>

  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
  <link rel="stylesheet" href="css/mvc.css">
  <script type="text/javascript" src="js/mvc.js"></script>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>

</head>

<body>
  <div class="container">
    <h1 class="text-center my-4">Datos cliente</h1>
    <form method="POST" action='ClienteController' name="formulario" id="formulario">
    <div class="d-flex justify-content-center">
      <div class="contenedor-formulario">
        <label class="form-label" for="clienteId">Cliente Id</label>
        <input class="form-control readonly" type="text" readonly="readonly" name="clienteId" id="clienteId"  
               value="<c:out value="${cliente.id}" />" />
    
        <label class="form-label" for="nombre">Nombre</label>
        <input class="form-control" type="text" name="nombre" id="nombre" required="required"
               value="<c:out value="${cliente.nombre}" />" /> 
              
        <label class="form-label" for="apellidos">Apellidos</label>
        <input class="form-control" type="text" name="apellidos" id="apellidos" required="required"
               value="<c:out value="${cliente.apellidos}"/>" />
              
        <label class="form-label" for="fechaNacimiento">Fecha Nacimiento</label> 
        <input class="form-control" type="date" name="fechaNacimiento" id="fechaNacimiento" required="required"
               value="<fmt:formatDate pattern="yyyy-MM-dd" value="${cliente.fechaNacimiento}" />" />
        </div>
      </div>
      <div class="text-center mt-5">
        <a href="." class="btn btn-secondary me-1">Volver</a>
        <input type="submit" value="Enviar" class="btn btn-primary ms-1" />
      </div>

<% if(request.getAttribute("mensajeError")!=null) { %>
    <div class="d-flex justify-content-center">
      <div class="alert alert-danger d-inline mt-3">
        <i class="bi bi-exclamation-triangle me-2"></i><span><%= request.getAttribute("mensajeError")%></span>
      </div>
    </div>
<% } %>
      
      <div class="d-flex justify-content-center mt-5">
        <div class="form-check">
          <input class="form-check-input" type="checkbox" id="checkboxDeshabilitarValidacionesCliente" onclick="onClickCheckboxValidaciones();">
          <label for="checkboxDeshabilitarValidacionesCliente">Deshabilitar validaciones del navegador</label>
        </div>
      </div>
      
    </form>
  </div>
</body>
</html>