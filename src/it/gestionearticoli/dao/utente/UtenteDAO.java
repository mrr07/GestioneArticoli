package it.gestionearticoli.dao.utente;

import it.gestionearticoli.dao.IBaseDAO;
import it.gestionearticoli.model.Utente;

public interface UtenteDAO extends IBaseDAO<Utente>{

	Utente get(String user, String pass) throws Exception;
}
