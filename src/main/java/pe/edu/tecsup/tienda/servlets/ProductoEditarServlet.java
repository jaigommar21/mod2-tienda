package pe.edu.tecsup.tienda.servlets;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import pe.edu.tecsup.tienda.entities.Categoria;
import pe.edu.tecsup.tienda.entities.Producto;
import pe.edu.tecsup.tienda.services.CategoriaService;
import pe.edu.tecsup.tienda.services.ProductoService;

/**
 * Servlet implementation class ProductoEditarServlet
 */
@WebServlet("/ProductoEditarServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class ProductoEditarServlet extends HttpServlet {
	private static final Logger log = Logger.getLogger(ProductoListarServlet.class);
	private CategoriaService categoriaService;
	private ProductoService productoService;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductoEditarServlet() {
		super();
		this.productoService = new ProductoService();
		this.categoriaService = new CategoriaService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("Get ProductoEditarServlet");
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));

			List<Categoria> categorias = categoriaService.listar();

			request.setAttribute("producto", productoService.obtener(id));
			request.setAttribute("categorias", categorias);
			request.getRequestDispatcher("/WEB-INF/jsp/producto/editar.jsp").forward(request, response);
		} catch (Exception e) {
			log.error(e, e);
			throw new ServletException(e.getMessage(), e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("Post ProductoEditarServlet");

		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			Producto productoRequest= productoService.obtener(id);
			
			// Lee datos del request
			String categorias_id = request.getParameter("categorias_id");
			String nombre = request.getParameter("nombre");
			String precio = request.getParameter("precio");
			String stock = request.getParameter("stock");
			String descripcion = request.getParameter("descripcion");

			// Tratamiento de los datos
			Producto producto = new Producto();
			producto.setCategorias_id(Integer.parseInt(categorias_id));
			producto.setNombre(nombre);
			producto.setPrecio(Double.parseDouble(precio));
			producto.setStock(Integer.parseInt(stock));
			producto.setDescripcion(descripcion);

			Part part = request.getPart("imagen");

			if (part.getSubmittedFileName() != null && part.getSize() > 0) {

				File filepath = new File(getServletContext().getRealPath("") + File.separator + "files");
				// File filepath = new File("D:" + File.separator + "files");

				if (!filepath.exists())
					filepath.mkdir();

				String filename = System.currentTimeMillis() + "-" + part.getSubmittedFileName();
				part.write(filepath + File.separator + filename);

				log.info("Imagen cargada en: " + filepath + File.separator + filename);

				producto.setImagen_nombre(filename);
				producto.setImagen_tipo(part.getContentType());
				producto.setImagen_tamanio(part.getSize());
			}else {
				producto.setImagen_nombre(productoRequest.getImagen_nombre());
				producto.setImagen_tipo(productoRequest.getImagen_tipo());
				producto.setImagen_tamanio(productoRequest.getImagen_tamanio());
			}

			log.info(producto);

			// Graba en la BBDD
			productoService.actualizar(id, producto);

			request.getSession().setAttribute("success", "Registro editado satisfactoriamente");

			// Redirecciona salida
			response.sendRedirect(request.getContextPath() + "/ProductoListarServlet");

		} catch (Exception e) {
			log.error(e, e);
			throw new ServletException(e.getMessage(), e);
		}

	}

}
