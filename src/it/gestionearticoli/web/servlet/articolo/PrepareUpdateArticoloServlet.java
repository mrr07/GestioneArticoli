package it.gestionearticoli.web.servlet.articolo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticoli.model.Articolo;
import it.gestionearticoli.model.Categoria;
import it.gestionearticoli.service.MyServiceFactory;

/**
 * Servlet implementation class PrepareUpdateServlet
 */
@WebServlet("/PrepareUpdateArticoloServlet")
public class PrepareUpdateArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public PrepareUpdateArticoloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String IDInputParam = request.getParameter("IdDaAggiornare");
		String ruolo = request.getParameter("ruoloUtente");
		if(ruolo == null) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		Long id = Long.valueOf(IDInputParam);
	
		Articolo articoloInstance = new Articolo();
		articoloInstance = null;
		
		try {	
			articoloInstance = MyServiceFactory.getArticoloServiceInstance().findById(id);
			if(articoloInstance.getCategoria().getId() == 0) {
				articoloInstance.getCategoria().setNome("Non Categorizzato");
			}else {
				articoloInstance.getCategoria().setNome(MyServiceFactory.getCategoriaServiceInstance().findById(articoloInstance.getCategoria().getId()).getNome());
			}
			request.setAttribute("articoloDaAggiornare", articoloInstance);
			request.setAttribute("ruoloUtente", ruolo);
			
		} catch (Exception e) {
			e.printStackTrace();
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
		request.getRequestDispatcher("update.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
