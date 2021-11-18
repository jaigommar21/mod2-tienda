<%@page import="pe.edu.tecsup.tienda.entities.Categoria"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/jsp/includes/head.jsp"%>
</head>
<body>
	<%@include file="/WEB-INF/jsp/includes/navbar.jsp"%>

	<div class="container-fluid pt-3">
		<div class="display-4 mb-3">Mantenimiento de Productos</div>
		<div class="row justify-content-center">
			<div class="col-lg-8">
				<div class="card mb-2">
					<div class="card-header">Mostrar de Producto</div>
					<div class="card-body">
						<div class="form-group">
							<label><b>Nombre:</b></label> <label><c:out
									value="${producto.nombre}" /></label>
						</div>
						<div class="form-group">
							<label><b>Categoría:</b></label> <label><c:out
									value="${producto.categoria.nombre}" /></label>
						</div>
						<div class="form-group">
							<label><b>Precio:</b></label> <label><c:out
									value="${producto.precio}" /></label>
						</div>
						<div class="form-group">
							<label><b>Stock:</b></label> <label><c:out
									value="${producto.stock}" /></label>
						</div>
						<div class="form-group">
							<label><b>Imagen:</b></label>
							<c:if test="${producto.imagen_nombre != null}">
								<img
									src="<%=request.getContextPath()%>/files/<c:out value="${producto.imagen_nombre}"/>"
									alt="" height="100">
							</c:if>
						</div>

						<div class="form-group">
							<label for="descripcion"><b>Descripción</b></label>
							<textarea name="descripcion" id="descripcion"
								class="form-control" style="resize: none;" rows="5" disabled><c:out value="${producto.descripcion}"></c:out></textarea>
						</div>
					</div>
					<div class="card-footer">
						<a href="<%=request.getContextPath()%>/ProductoListarServlet"
							class="btn btn-primary">Regresar</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>