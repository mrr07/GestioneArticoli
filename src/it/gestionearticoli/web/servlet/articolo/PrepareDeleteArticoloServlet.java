package it.gestionearticoli.web.servlet.articolo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticoli.model.Articolo;

/**
 * Servlet implementation class PrepareDeleteArticoloServlet
 */
@WebServlet("/PrepareDeleteArticoloServlet")
public class PrepareDeleteArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrepareDeleteArticoloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idArticolo = request.getParameter("IdDaEliminare");
		String ruolo = request.getParameter("ruoloUtente");
		if(ruolo == null) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		Long id = Long.valueOf(idArticolo);
		
		Articolo articoloInstance = new Articolo();
		articoloInstance.setId(id);
		request.setAttribute("EliminaID", articoloInstance);
		request.setAttribute("ruoloUtente", ruolo);
		
		request.getRequestDispatcher("confermaEliminazione.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
