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
 * Servlet implementation class ExecuteInsertCategoriaServlet
 */
@WebServlet("/ExecuteInsertCategoriaServlet")
public class ExecuteInsertCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteInsertCategoriaServlet() {
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
		
		Categoria categoria = new Categoria();
		categoria.setNome(nomeCategoria);
		try {
			MyServiceFactory.getCategoriaServiceInstance().inserisciNuovo(categoria);
			List<Categoria> lista = new ArrayList<>();
			lista = MyServiceFactory.getCategoriaServiceInstance().listAll();
			request.setAttribute("listaCategorieAttribute", lista);
			request.setAttribute("ruoloUtente", ruolo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("results1.jsp").forward(request, response);
	}

}
