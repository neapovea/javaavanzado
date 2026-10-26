<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Datos pedido</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
  <style>
    .contenedor-formulario {
      display: inline-grid;
      grid-template-columns: auto auto;
      gap: 1rem;
      align-items: center;
    }
    
    .form-label {
      margin-bottom: 0;
    }
    
    .readonly {
      background-color: var(--bs-secondary-bg);;
    }
  </style>
</head>
<body class="container mt-4">
    <h1 class="text-center mb-4">Datos pedido</h1>

    <div class="row justify-content-center">
        <div class="col-md-4">
            <form action="PedidoController" method="POST">
                <input type="hidden" name="clienteId" value="${clienteId}" />

                <div class="mb-3">
                    <label class="form-label">Artículo:</label>
                    <input type="text" name="articulo" class="form-control" required />
                </div>

                <div class="mb-3">
                    <label class="form-label">Precio:</label>
                    <input type="number" step="0.01" name="precio" class="form-control" required />
                </div>

                <div class="d-flex justify-content-between">
                    <a href="PedidoController?action=listado&clienteId=${clienteId}" class="btn btn-secondary">Volver</a>
                    <button type="submit" class="btn btn-primary">Enviar</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>