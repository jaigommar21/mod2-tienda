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
		<form
			action="<%=request.getContextPath()%>/ProductoEditarServlet?id=<c:out value="${producto.id}"/>"
			method="post" enctype="multipart/form-data">
			<div class="row justify-content-center">
				<div class="col-lg-8">
					<div class="card mb-2">
						<div class="card-header">Editar de Producto</div>
						<div class="card-body">
							<div class="form-group">
								<label for="nombre">Nombre</label> <input type="text"
									name="nombre" id="nombre" class="form-control" maxlength="100"
									value="<c:out value="${producto.nombre}" />" required>
							</div>
							<div class="form-group">
								<label for="categorias_id">Categoría</label> <select
									name="categorias_id" id="categorias_id" class="form-control"
									required>
									<option value="" selected disabled>Seleccione un valor</option>

									<c:forEach items="${categorias}" var="categoria">
										<c:if test="${categoria.id == producto.categoria.id}">
											<option value="<c:out value="${categoria.id}"/>" selected>
												<c:out value="${categoria.nombre}" /></option>
										</c:if>
										<c:if test="${categoria.id != producto.categoria.id}">
											<option value="<c:out value="${categoria.id}"/>">
												<c:out value="${categoria.nombre}" /></option>
										</c:if>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label for="precio">Precio</label>
								<div class="input-group">
									<div class="input-group-prepend">
										<div class="input-group-text">S/</div>
									</div>
									<input type="number" name="precio" id="precio"
										class="form-control" min="0" step="0.01"
										value="<c:out value="${producto.precio}" />">
								</div>
							</div>
							<div class="form-group">
								<label for="stock">Stock</label> <input type="number"
									name="stock" id="stock" class="form-control" min="0"
									value="<c:out value="${producto.stock}" />">
							</div>
							<div class="form-group">
								<label for="imagen">Imagen</label>
								<c:if test="${producto.imagen_nombre != null}">
									<img
										src="<%=request.getContextPath()%>/files/<c:out value="${producto.imagen_nombre}"/>"
										alt="" height="100">
								</c:if>
								<div class="custom-file">
									<input type="file" id="imagen" name="imagen"
										class="custom-file-input" /> <label class="custom-file-label"
										for="imagen"> Seleccionar archivo </label>
								</div>
							</div>

							<div class="form-group">
								<label for="descripcion">Descripción</label>
								<textarea name="descripcion" id="descripcion"
									class="form-control" rows="5" style="resize: none;"><c:out value="${producto.descripcion}" /></textarea>
							</div>
						</div>
						<div class="card-footer">
							<button type="submit" class="btn btn-primary">Guardar</button>
							<a href="<%=request.getContextPath()%>/ProductoListarServlet"
								class="btn btn-secondary">Cancelar</a>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>