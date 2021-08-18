package pe.edu.tecsup.tienda.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import pe.edu.tecsup.tienda.entities.Producto;

/**
 * Servlet implementation class CarritoEliminarServlet
 */
@WebServlet("/CarritoEliminarServlet")
public class CarritoEliminarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final Logger log = Logger.getLogger(CarritoEliminarServlet.class);

	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarritoEliminarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		
		int id_eliminar = Integer.parseInt(request.getParameter("id"));
		log.info("id_eliminar: " + id_eliminar);
		
		// Create session
		HttpSession session = request.getSession();
		
		// Obtiene la info de la session
		List<Producto> productos = (List<Producto>) session.getAttribute("productos");
		
		if(productos == null) {
			productos = new ArrayList<Producto>();
		}

		log.info("CARRTITO ==> " + productos);
		
		Producto prod_eliminar = null; 
		
		for (Producto producto : productos) {
			if (producto.getId() == id_eliminar ) {
				log.info("ELIMINAR ==> " +  producto);
				prod_eliminar = producto;
			}
		}
		if (prod_eliminar != null)
		     productos.remove(prod_eliminar);
		
		
		request.getSession().setAttribute("success", "Registro eliminado satisfactoriamente");

		response.sendRedirect(request.getContextPath() + "/CarritoListarServlet");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
