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
import it.gestionearticoli.service.MyServiceFactory;

/**
 * Servlet implementation class DeleteArticoloServlet
 */
@WebServlet("/DeleteArticoloServlet")
public class DeleteArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteArticoloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long d = Long.valueOf(request.getParameter("IdDaEliminare"));
		String ruolo = request.getParameter("ruoloUtente");
		if(ruolo == null) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		Articolo articolo = new Articolo();
		articolo.setId(d);
		
		try {
			
			MyServiceFactory.getArticoloServiceInstance().rimuovi(articolo);
			List<Articolo> lista = new ArrayList<>();
			lista = MyServiceFactory.getArticoloServiceInstance().listAll();
			for(int i=0; i<lista.size(); i++) {
				long id = lista.get(i).getCategoria().getId();
				lista.get(i).getCategoria().setNome(MyServiceFactory.getCategoriaServiceInstance().findById(id).getNome());
			}
			request.setAttribute("listaArticoliAttribute", lista);
			request.setAttribute("ruoloUtente", ruolo);
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("results.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
