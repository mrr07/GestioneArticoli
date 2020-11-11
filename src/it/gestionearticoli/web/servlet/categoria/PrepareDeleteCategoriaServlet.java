package it.gestionearticoli.web.servlet.categoria;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticoli.model.Categoria;

/**
 * Servlet implementation class PrepareDeleteCategoriaServlet
 */
@WebServlet("/PrepareDeleteCategoriaServlet")
public class PrepareDeleteCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrepareDeleteCategoriaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idCategoria = request.getParameter("IdDaEliminare");
		String ruolo = request.getParameter("ruoloUtente");
		String nomeRicerca = request.getParameter("nomeRicerca");
		if(ruolo == null) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		System.out.println(nomeRicerca);
		
		Long id = Long.valueOf(idCategoria);
		
		Categoria categoriaInstance = new Categoria();
		categoriaInstance.setId(id);
		request.setAttribute("EliminaID", categoriaInstance);
		request.setAttribute("ruoloUtente", ruolo);
		request.setAttribute("nomeRicerca", nomeRicerca);
		
		request.getRequestDispatcher("confermaEliminazioneCategoria.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
