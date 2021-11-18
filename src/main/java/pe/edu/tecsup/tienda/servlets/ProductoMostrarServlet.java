package pe.edu.tecsup.tienda.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import pe.edu.tecsup.tienda.services.ProductoService;

@WebServlet("/ProductoMostrarServlet")
public class ProductoMostrarServlet extends HttpServlet {

	private static final Logger log = Logger.getLogger(ProductoListarServlet.class);

	private ProductoService productoService;

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductoMostrarServlet() {
		super();
		this.productoService = new ProductoService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("Get ProductoMostrarServlet");
 
		try {

			Integer id = Integer.parseInt(request.getParameter("id"));

			request.setAttribute("producto", productoService.obtener(id));

			request.getRequestDispatcher("/WEB-INF/jsp/producto/mostrar.jsp").forward(request, response);

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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
