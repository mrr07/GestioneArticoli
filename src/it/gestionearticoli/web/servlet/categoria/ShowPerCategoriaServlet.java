package it.gestionearticoli.web.servlet.categoria;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import it.gestionearticoli.connection.MyConnection;
import it.gestionearticoli.model.Articolo;
import it.gestionearticoli.model.Categoria;
import it.gestionearticoli.service.MyServiceFactory;

/**
 * Servlet implementation class ShowPerCategoriaServlet
 */
@WebServlet("/ShowPerCategoriaServlet")
public class ShowPerCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowPerCategoriaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idCategoria = request.getParameter("IdDaVisualizzare");
		long id = Long.parseLong(idCategoria);
		String ruolo = request.getParameter("ruoloUtente");
		if(ruolo == null) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		List<Articolo> lista = new ArrayList<>();
		
		
		try {
			Categoria categoria = new Categoria();
			categoria = MyServiceFactory.getCategoriaServiceInstance().findById(id);
			lista = MyServiceFactory.getArticoloServiceInstance().listByCategoria(categoria);
			
			request.setAttribute("listaArticoliPerCategoria", lista);
			request.setAttribute("ruoloUtente", ruolo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("show2.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
