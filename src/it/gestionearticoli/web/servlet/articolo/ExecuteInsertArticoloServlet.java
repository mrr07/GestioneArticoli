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

@WebServlet("/ExecuteInsertArticoloServlet")
public class ExecuteInsertArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteInsertArticoloServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ruolo = request.getParameter("ruoloUtente");
		if(ruolo == null) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// validiamo input
		String codiceInputParam = request.getParameter("codice");
		String descrizioneInputParam = request.getParameter("descrizione");
		String prezzoInputStringParam = request.getParameter("prezzo");
		String categoriaInputStringParam = request.getParameter("categoria");
		String ruolo = request.getParameter("ruoloUtente");
		Integer prezzo = !prezzoInputStringParam.isEmpty() ? Integer.parseInt(prezzoInputStringParam) : 0;
		// se la validazione fallisce torno in pagina
		if (codiceInputParam.isEmpty() || descrizioneInputParam.isEmpty() || prezzo < 1 || prezzo == null || categoriaInputStringParam == null) {
			try {
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
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("insert.jsp").forward(request, response);
			return;
		}

		//occupiamoci delle operazioni di business
		
		
		
		Categoria categoria = new Categoria();
		try {
			categoria = MyServiceFactory.getCategoriaServiceInstance().findByNome(categoriaInputStringParam);
		} catch (Exception e){
			e.printStackTrace();
		}
		
		
		Articolo articoloInstance = new Articolo(codiceInputParam, descrizioneInputParam, prezzo, categoria);
		try {
			MyServiceFactory.getArticoloServiceInstance().inserisciNuovo(articoloInstance);
			
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

		//andiamo ai risultati
		request.getRequestDispatcher("results.jsp").forward(request, response);

	}

}
