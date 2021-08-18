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

@WebServlet("/CarritoListarServlet")
public class CarritoListarServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	private static final Logger log = Logger.getLogger(CarritoListarServlet.class);
	
	public CarritoListarServlet() {
		
	}
	
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		log.info("Get CarritoListarServlet");
		try {
			
			HttpSession session = request.getSession();
			List<Producto> productos 
				= (List<Producto>) session.getAttribute("productos");
			
			if(productos == null) {
				productos = new ArrayList<Producto>();
			}
			
			request.setAttribute("productos", productos);
			
			request.getRequestDispatcher("/WEB-INF/jsp/carrito/listar.jsp").forward(request, response);
	        
		} catch (Exception e) {
			log.error(e, e);
			throw new ServletException(e.getMessage(), e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
