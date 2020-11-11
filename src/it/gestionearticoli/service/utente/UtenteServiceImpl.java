package it.gestionearticoli.service.utente;

import java.sql.Connection;

//import java.sql.Connection;
//import java.util.ArrayList;
//import java.util.List;

import it.gestionearticoli.dao.Constants;
import it.gestionearticoli.connection.MyConnection;
import it.gestionearticoli.dao.utente.UtenteDAO;
import it.gestionearticoli.model.Utente;


public class UtenteServiceImpl implements UtenteService{
	
	private UtenteDAO utenteDao;

	
	public void setUtenteDao(UtenteDAO utenteDao) {
		this.utenteDao = utenteDao;
	}
	
	@Override
	public Utente findById(Long idInput) throws Exception {
		Utente utente = new Utente();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			utenteDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			utente = utenteDao.get(idInput);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return utente;
	}
	
	@Override
	public Utente findByUserPass(String user, String pass) throws Exception {
		Utente utente = new Utente();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			utenteDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			utente = utenteDao.get(user, pass);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return utente;
	}
}
