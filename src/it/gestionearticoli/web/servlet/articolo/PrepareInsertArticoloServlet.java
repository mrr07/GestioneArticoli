package it.gestionearticoli.web.servlet.articolo;

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

@WebServlet("/PrepareInsertArticoloServlet")
public class PrepareInsertArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PrepareInsertArticoloServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String ruolo = request.getParameter("ruoloUtente");
		if(ruolo == null) {
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
		request.getRequestDispatcher("insert.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
