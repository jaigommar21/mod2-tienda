package pe.edu.tecsup.tienda.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import pe.edu.tecsup.tienda.entities.Categoria;
import pe.edu.tecsup.tienda.services.CategoriaService;

/**
 * Servlet implementation class CategoriaServlet
 */
@WebServlet("/CategoriaServlet")
public class CategoriaServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
        
	private static final Logger log = Logger.getLogger(CategoriaServlet.class);
	
	private CategoriaService categoriaService;

	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoriaServlet() {
        super();
        this.categoriaService = new CategoriaService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		log.info("Get ProductoRegistrarServlet");
		try {
			
			List<Categoria> categorias = categoriaService.listar();
			
			//request.setAttribute("categorias", categorias);
			
			//request.getRequestDispatcher("/WEB-INF/jsp/producto/registrar.jsp").forward(request, response);
	        
		} catch (Exception e) {
			log.error(e, e);
			throw new ServletException(e.getMessage(), e);
		}

				
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
