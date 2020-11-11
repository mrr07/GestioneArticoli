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
 * Servlet implementation class ExecuteSearchArticoloServlet
 */
@WebServlet("/ExecuteSearchArticoloServlet")
public class ExecuteSearchArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteSearchArticoloServlet() {
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
		
		String ricercato = "ricercato";
		String codiceInputParam = request.getParameter("codice");
		String descrizioneInputParam = request.getParameter("descrizione");
		String prezzoInputStringParam = request.getParameter("prezzo");
		String categoriaInputStringParam = request.getParameter("categoria");
		String ruolo = request.getParameter("ruoloUtente");
		Integer prezzo = !prezzoInputStringParam.isEmpty() ? Integer.parseInt(prezzoInputStringParam) : null;
		
		Categoria categoria = new Categoria();
		try {
			if(categoriaInputStringParam == null) {
				categoria = null;
			}
			else {
				categoria = MyServiceFactory.getCategoriaServiceInstance().findByNome(categoriaInputStringParam);
			}
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
		
		Articolo articoloInstance = new Articolo(codiceInputParam, descrizioneInputParam, prezzo, categoria);
		
		try {
			
			List<Articolo> lista = new ArrayList<>();
			lista = MyServiceFactory.getArticoloServiceInstance().findByExample(articoloInstance);
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
			request.setAttribute("ricercato", ricercato);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("results.jsp").forward(request, response);
	}

}
