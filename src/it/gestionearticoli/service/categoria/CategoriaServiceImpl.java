package it.gestionearticoli.service.categoria;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import it.gestionearticoli.connection.MyConnection;
import it.gestionearticoli.dao.Constants;
import it.gestionearticoli.dao.categoria.CategoriaDAO;
import it.gestionearticoli.model.Categoria;


public class CategoriaServiceImpl implements CategoriaService{
	
	private CategoriaDAO categoriaDao;

	public void setCategoriaDao(CategoriaDAO categoriaDao) {
		this.categoriaDao = categoriaDao;
	}

	@Override
	public List<Categoria> listAll() throws Exception {
		List<Categoria> result = new ArrayList<>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			categoriaDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = categoriaDao.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Categoria findById(Long idInput) throws Exception {
		Categoria categoria = new Categoria();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			categoriaDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			categoria = categoriaDao.get(idInput);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return categoria;
	}
	
	@Override
	public Categoria findByNome(String nome) throws Exception {
		Categoria categoria = new Categoria();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			categoriaDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			categoria = categoriaDao.get(nome);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return categoria;
	}

	@Override
	public int aggiorna(Categoria input) throws Exception {
		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			categoriaDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = categoriaDao.update(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int inserisciNuovo(Categoria input) throws Exception {
		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			categoriaDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = categoriaDao.insert(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int rimuovi(Categoria input) throws Exception {
		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			categoriaDao.setConnection(connection);
			

			// eseguo quello che realmente devo fare
			result = categoriaDao.delete(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<Categoria> findByExample(Categoria input) throws Exception {
		List<Categoria> lista = new ArrayList<Categoria>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			categoriaDao.setConnection(connection);
			

			// eseguo quello che realmente devo fare
			lista = categoriaDao.findByExample(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return lista;
	}
	
}
