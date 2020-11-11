package it.gestionearticoli.dao.utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
import java.util.List;


import it.gestionearticoli.dao.AbstractMySQLDAO;
import it.gestionearticoli.model.Ruolo;
import it.gestionearticoli.model.Utente;

public class UtenteDAOImpl extends AbstractMySQLDAO implements UtenteDAO{
	
	@Override
	public List<Utente> list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utente get(Long id) throws Exception {
		if (isNotActive()) {
			return null;
		}
		
		
		ResultSet result = null;
		Utente utente = new Utente();
		try (PreparedStatement ps = connection
				.prepareStatement("select * from utente where id_utente=?")) {
			
			ps.setLong(1, id);
			result = ps.executeQuery();

			while (result.next()) {
				
				utente.setId(result.getLong("id_utente"));
				utente.setNome(result.getString("nome"));
				utente.setCognome(result.getString("cognome"));
				utente.setCodice_fiscale(result.getString("cf"));
				utente.setUsername(result.getString("username"));
				utente.setPassword(result.getString("password"));
				utente.setRuolo(Ruolo.valueOf(result.getString("ruolo")));
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		
		return utente;
	}
	
	@Override
	public Utente get(String user, String pass) throws Exception {
		if (isNotActive()) {
			return null;
		}
		
		
		ResultSet result = null;
		Utente utente = new Utente();
		try (PreparedStatement ps = connection
				.prepareStatement("select * from utente where username=? and password=?")) {
			
			ps.setString(1, user);
			ps.setString(2, pass);
			result = ps.executeQuery();

			while (result.next()) {
				
				utente.setId(result.getLong("id_utente"));
				utente.setNome(result.getString("nome"));
				utente.setCognome(result.getString("cognome"));
				utente.setCodice_fiscale(result.getString("cf"));
				utente.setUsername(result.getString("username"));
				utente.setPassword(result.getString("password"));
				utente.setRuolo(Ruolo.valueOf(result.getString("ruolo")));
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		
		return utente;
	}
	
	public Utente get(String nome) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Utente categoria) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Utente categoria) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Utente> findByExample(Utente input) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;

	}

	@Override
	public int insert(Utente input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
