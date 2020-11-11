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
 * Servlet implementation class UpdateArticoloServlet
 */
@WebServlet("/UpdateArticoloServlet")
public class UpdateArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateArticoloServlet() {
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
		String codiceInputParam = request.getParameter("codice");
		String descrizioneInputParam = request.getParameter("descrizione");
		String prezzoInputStringParam = request.getParameter("prezzo");
		String categoriaInputStringParam = request.getParameter("categoria");
		String ruolo = request.getParameter("ruolo");
		Integer prezzo = !prezzoInputStringParam.isEmpty() ? Integer.parseInt(prezzoInputStringParam) : 0;
		// se la validazione fallisce torno in pagina
		try {
			Categoria cat = new Categoria();
			cat = MyServiceFactory.getCategoriaServiceInstance().findByNome(categoriaInputStringParam);
			if(cat.getNome() == null) {
				Articolo articolo = new Articolo();
				articolo.setId(id);
				articolo.setCodice(codiceInputParam);
				articolo.setDescrizione(descrizioneInputParam);
				articolo.setPrezzo(prezzo);
				Categoria categoria = new Categoria();
				articolo.setCategoria(categoria);
				articolo.getCategoria().setNome(categoriaInputStringParam);
				
				request.setAttribute("articoloDaAggiornare", articolo);
				request.setAttribute("ruoloUtente", ruolo);
				
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.getRequestDispatcher("update.jsp").forward(request, response);
				return;
			}
		} catch (Exception e) {
			
		}
		if (codiceInputParam.isEmpty() || descrizioneInputParam.isEmpty() || prezzo < 1 || categoriaInputStringParam.isEmpty()) {
			Articolo articolo = new Articolo();
			articolo.setId(id);
			articolo.setCodice(codiceInputParam);
			articolo.setDescrizione(descrizioneInputParam);
			articolo.setPrezzo(prezzo);
			Categoria categoria = new Categoria();
			articolo.setCategoria(categoria);
			articolo.getCategoria().setNome(categoriaInputStringParam);
			
			
			request.setAttribute("articoloDaAggiornare", articolo);
			request.setAttribute("ruoloUtente", ruolo);
			
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("update.jsp").forward(request, response);
			return;
		}

		//occupiamoci delle operazioni di business
		Categoria categoria1 = new Categoria();
		try {
			categoria1 = MyServiceFactory.getCategoriaServiceInstance().findByNome(categoriaInputStringParam);
		} catch (Exception e){
			e.printStackTrace();
		}
		
		Articolo articoloInstance = new Articolo(codiceInputParam, descrizioneInputParam, prezzo, categoria1);
		articoloInstance.setId(id);
		try {
			MyServiceFactory.getArticoloServiceInstance().aggiorna(articoloInstance);
			
			List<Articolo> lista = new ArrayList<>();
			lista = MyServiceFactory.getArticoloServiceInstance().listAll();
			for(int i=0; i<lista.size(); i++) {
				long id1 = lista.get(i).getCategoria().getId();
				lista.get(i).getCategoria().setNome(MyServiceFactory.getCategoriaServiceInstance().findById(id1).getNome());
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
