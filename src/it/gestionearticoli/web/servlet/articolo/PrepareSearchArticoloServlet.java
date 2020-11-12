package it.gestionearticoli.web.servlet.articolo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticoli.model.Categoria;
import it.gestionearticoli.service.MyServiceFactory;

/**
 * Servlet implementation class PrepareSearchArticoloServlet
 */
@WebServlet("/PrepareSearchArticoloServlet")
public class PrepareSearchArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrepareSearchArticoloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ruolo = request.getParameter("ruoloUtente");
		
		if(ruolo == null || ruolo == "" || Pattern.matches("[a-zA-Z]+", ruolo) == false) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		List<Categoria> listaCategorie = new ArrayList<>();
		try {
			listaCategorie = MyServiceFactory.getCategoriaServiceInstance().listAll();
			for(int i=0; i<listaCategorie.size(); i++) {
				if(listaCategorie.get(i).getId() == 5) {
					listaCategorie.remove(i);
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("listaCategorie", listaCategorie);
		request.setAttribute("ruoloUtente", ruolo);
		request.getRequestDispatcher("ricercaArticolo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
