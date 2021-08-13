package pe.edu.tecsup.tienda.servlets;


import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import pe.edu.tecsup.tienda.entities.Producto;
import pe.edu.tecsup.tienda.services.ProductoService;

@WebServlet("/ProductoListarServlet")
public class ProductoListarServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	private static final Logger log = Logger.getLogger(ProductoListarServlet.class);
	
	private ProductoService productoService;
	
	public ProductoListarServlet() {
		this.productoService = new ProductoService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		log.info("call doGet()");
		
		try {
			
			List<Producto> productos = productoService.listar();
			
			//log.info("PRODUCTOS =>" + productos);
			
			request.setAttribute("productos", productos);
			 
			
			request.getRequestDispatcher("/WEB-INF/jsp/producto/listar.jsp")
			.forward(request, response);
	        
			/*
			request.getRequestDispatcher("/hola2.html").forward(request, response);
			*/
			/*
			RequestDispatcher rd 
				= request.getRequestDispatcher("/hola.html");
			rd.forward(request, response);
			*/
			
		} catch (Exception e) {
			log.error(e, e);
			throw new ServletException(e.getMessage(), e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
