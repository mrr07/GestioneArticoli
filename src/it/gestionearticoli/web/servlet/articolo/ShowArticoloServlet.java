package it.gestionearticoli.web.servlet.articolo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticoli.model.Articolo;
import it.gestionearticoli.service.MyServiceFactory;

/**
 * Servlet implementation class ShowArticoloServlet
 */
@WebServlet("/ShowArticoloServlet")
public class ShowArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowArticoloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long d = Long.valueOf(request.getParameter("IdDaVisualizzare"));
		String ruolo = request.getParameter("ruoloUtente");
		if(ruolo == null) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		try {
			Articolo articolo = new Articolo();
			articolo = MyServiceFactory.getArticoloServiceInstance().findById(d);
			if(articolo.getCategoria().getId() == 0) {
				articolo.getCategoria().setNome("Non Categorizzato");
			}else {
				articolo.getCategoria().setNome(MyServiceFactory.getCategoriaServiceInstance().findById(articolo.getCategoria().getId()).getNome());
			}
			request.setAttribute("articoloDaVisualizzare", articolo);
			request.setAttribute("ruoloUtente", ruolo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("show.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
