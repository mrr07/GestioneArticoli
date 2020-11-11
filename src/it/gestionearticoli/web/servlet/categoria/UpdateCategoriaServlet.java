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
 * Servlet implementation class UpdateCategoriaServlet
 */
@WebServlet("/UpdateCategoriaServlet")
public class UpdateCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCategoriaServlet() {
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
		
		String idInputParam = request.getParameter("id");
		Long id = Long.valueOf(idInputParam);
		String nomeInputParam = request.getParameter("nome");
		String ruolo = request.getParameter("ruolo");
		// se la validazione fallisce torno in pagina
		if (nomeInputParam.isEmpty()) {
			Categoria categoria = new Categoria();
			categoria.setId(id);
			
			
			request.setAttribute("categoriaDaAggiornare", categoria);
			request.setAttribute("ruoloUtente", ruolo);
			
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("update1.jsp").forward(request, response);
			return;
		}

		
		Categoria categoria = new Categoria(id, nomeInputParam);
		try {
			MyServiceFactory.getCategoriaServiceInstance().aggiorna(categoria);
			
			List<Categoria> lista = new ArrayList<>();
			lista = MyServiceFactory.getCategoriaServiceInstance().listAll();
			
			request.setAttribute("listaCategorieAttribute", lista);
			request.setAttribute("ruoloUtente", ruolo);
			
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch (Exception e) {
			e.printStackTrace();
		}

		//andiamo ai risultati
		request.getRequestDispatcher("results1.jsp").forward(request, response);
		
	}

}
