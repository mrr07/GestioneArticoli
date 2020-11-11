package it.gestionearticoli.web.servlet.articolo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticoli.service.MyServiceFactory;
import it.gestionearticoli.model.*;

@WebServlet("/ListArticoliServlet")
public class ListArticoliServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListArticoliServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//preparo la lista di articoli
		String ruolo = request.getParameter("ruoloUtente");
		if(ruolo == null) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		try {
			List<Articolo> lista = new ArrayList<>();
			lista = MyServiceFactory.getArticoloServiceInstance().listAll();
			long id;
			for(int i=0; i<lista.size(); i++) {
				if(lista.get(i).getCategoria().getId() == 0) {
					id = 5;
				}
				else {
					id = lista.get(i).getCategoria().getId();
				}
				
				lista.get(i).getCategoria().setNome(MyServiceFactory.getCategoriaServiceInstance().findById(id).getNome());
			}
			request.setAttribute("listaArticoliAttribute", lista);
			request.setAttribute("ruoloUtente", ruolo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("results.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
