package it.gestionearticoli.service.utente;

//import java.util.List;

import it.gestionearticoli.model.Utente;
import it.gestionearticoli.dao.utente.UtenteDAO;

public interface UtenteService {
	
	public void setUtenteDao(UtenteDAO utenteDao);

	//public List<Utente> listAll() throws Exception;

	public Utente findById(Long idInput) throws Exception;
	
	public Utente findByUserPass(String user, String pass) throws Exception;
	
	//public Utente findByNome(String nome) throws Exception;

	//public int aggiorna(Categoria input) throws Exception;

	//public int inserisciNuovo(Categoria input) throws Exception;

	//public int rimuovi(Categoria input) throws Exception;

	//public List<Categoria> findByExample(Categoria input) throws Exception;

}
