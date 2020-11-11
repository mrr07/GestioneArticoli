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
 * Servlet implementation class DeleteCategoriaServlet
 */
@WebServlet("/DeleteCategoriaServlet")
public class DeleteCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCategoriaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long d = Long.valueOf(request.getParameter("IdDaEliminare"));
		String ruolo = request.getParameter("ruoloUtente");
		String nomeRicerca = request.getParameter("nomeRicerca");
		if(ruolo == null) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		Categoria categoria = new Categoria();
		categoria.setId(d);
		Categoria categoria1 = new Categoria();
		categoria1.setNome(nomeRicerca);
		
		System.out.println(categoria1.getNome());
		
		try {
			int risultato;
			risultato=MyServiceFactory.getCategoriaServiceInstance().rimuovi(categoria);
			List<Categoria> lista = new ArrayList<>();
			lista = MyServiceFactory.getCategoriaServiceInstance().listAll();
			
			
			request.setAttribute("listaCategorieAttribute", lista);
			request.setAttribute("ruoloUtente", ruolo);
			if(risultato == 0) {
				request.setAttribute("errorMessage", "Operazione Fallita");
				
				
				List<Categoria> listaCategorie1 = new ArrayList<>();
				try {
					
					listaCategorie1 = MyServiceFactory.getCategoriaServiceInstance().findByExample(categoria1);
					
					request.setAttribute("nomePerRicerca", nomeRicerca);
					request.setAttribute("listaCategorieAttribute", listaCategorie1); 
					request.setAttribute("ruoloUtente", ruolo);
				
				} catch (Exception e){
					e.printStackTrace();
				}	
			} else {
				request.setAttribute("successMessage", "Operazione effettuata con successo");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("results1.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
