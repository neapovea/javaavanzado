<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>

<head>
  <title>Datos pedido</title>

  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
  <link rel="stylesheet" href="css/mvc.css">

  <script type="text/javascript" src="js/mvc.js"></script>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</head>

<body>
<div class="container">
  <h1 class="text-center my-4">Datos pedido</h1>
  <form method="POST" action='PedidoController' name="formulario" id="formulario">
    <div class="d-flex justify-content-center">
      <div class="contenedor-formulario">

        <input type="hidden" name="clienteId" value="<c:out value="${clienteId}" />" /> 

        <label class="form-label" for="articulo">Artículo</label>
        <input class="form-control" type="text" name="articulo" id="articulo" required="required"/> 

        <label class="form-label" for="strPrecio">Precio</label> 
        <input class="form-control" type="number" min="0.01" max="999999.99" step="0.01" name="strPrecio" id="strPrecio"  required="required"/>
        
      </div>
    </div>
<% if(request.getAttribute("mensajeError")!=null) { %>
    <div class="d-flex justify-content-center">
      <div class="alert alert-danger d-inline mt-3">
        <i class="bi bi-exclamation-triangle me-2"></i><span><%= request.getAttribute("mensajeError")%></span>
      </div>
    </div>
<% } %>
    <div class="text-center mt-4">
      <a href="PedidoController?action=listado&clienteId=${clienteId}" class="btn btn-secondary me-1">Volver</a>
      <input type="submit" value="Enviar" class="btn btn-primary ms-1"/>
    </div>
    
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