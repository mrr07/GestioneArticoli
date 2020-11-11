package it.gestionearticoli.web.servlet.categoria;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import it.gestionearticoli.model.Categoria;
import it.gestionearticoli.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteSearchCategoriaServlet
 */
@WebServlet("/ExecuteSearchCategoriaServlet")
public class ExecuteSearchCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteSearchCategoriaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ruolo = request.getParameter("ruoloUtente");
		if(ruolo == null) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nomeCategoria = request.getParameter("nome");
		String ruolo = request.getParameter("ruoloUtente");
		String ricerca = "ricercato";
		Categoria categoria = new Categoria();
		categoria.setNome(nomeCategoria);
		
		List<Categoria> listaCategorie = new ArrayList<>();
		try {
			
			listaCategorie = MyServiceFactory.getCategoriaServiceInstance().findByExample(categoria);
			
			request.setAttribute("nomeRicerca", nomeCategoria);
			request.setAttribute("listaCategorieAttribute", listaCategorie); 
			request.setAttribute("ruoloUtente", ruolo);
			request.setAttribute("ricerca", ricerca);
		
		} catch (Exception e){
			e.printStackTrace();
		}	
		
		
		request.getRequestDispatcher("results1.jsp").forward(request, response);
	}

}
