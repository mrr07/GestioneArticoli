package it.gestionearticoli.dao.categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.gestionearticoli.dao.AbstractMySQLDAO;
import it.gestionearticoli.model.Categoria;

public class CategoriaDAOImpl extends AbstractMySQLDAO implements CategoriaDAO {

	@Override
	public List<Categoria> list() throws Exception {
		if (isNotActive()) {
			return null;
		}

		ArrayList<Categoria> result = new ArrayList<Categoria>();
		Categoria categoriaTemp = null;

		try (Statement ps = connection.createStatement()) {
			ResultSet rs = ps.executeQuery("select * from categoria");

			while (rs.next()) {
				categoriaTemp = new Categoria();
				categoriaTemp.setId(rs.getLong("id_categoria"));
				categoriaTemp.setNome(rs.getString("nome"));
				
				result.add(categoriaTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Categoria get(Long id) throws Exception {
		if (isNotActive()) {
			return null;
		}
		
		
		ResultSet result = null;
		Categoria categoria = new Categoria();
		try (PreparedStatement ps = connection
				.prepareStatement("select * from categoria where id_categoria=?")) {
			
			ps.setLong(1, id);
			result = ps.executeQuery();

			while (result.next()) {
				
				categoria.setId(result.getLong("id_categoria"));
				categoria.setNome(result.getString("nome"));
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		
		return categoria;
	}
	
	public Categoria get(String nome) throws Exception {
		if (isNotActive()) {
			return null;
		}
		
		
		ResultSet result = null;
		Categoria categoria = new Categoria();
		try (PreparedStatement ps = connection
				.prepareStatement("select * from categoria where nome=?")) {
			
			ps.setString(1, nome);
			result = ps.executeQuery();

			while (result.next()) {
				
				categoria.setId(result.getLong("id_categoria"));
				categoria.setNome(result.getString("nome"));
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		
		return categoria;
	}

	@Override
	public int update(Categoria categoria) throws Exception {
		String query = "UPDATE categoria SET nome = ? WHERE id_categoria = ?";
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, categoria.getNome());
            preparedStatement.setLong(2, categoria.getId());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return 0;
	}

	@Override
	public int insert(Categoria categoria) throws Exception {
		if (isNotActive() || categoria == null) {
			return -1;
		}
		
		String query = "INSERT INTO categoria(nome) VALUES (?);";
		int result = 0;
		
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setString(1, categoria.getNome());
			
			result = ps.executeUpdate();
			
			return result;
			
		} catch (SQLException e) {
            e.printStackTrace();
        } 
		
		return -1;
	}

	@Override
	public int delete(Categoria categoria) throws Exception {
		if (isNotActive() || categoria == null) {
			return -1;
		}
		
		String query = "DELETE FROM categoria WHERE id_categoria = ?";
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setLong(1, categoria.getId());
            
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            String msg = "Impossibile eseguire";
            return 0;
        } 
	}

	@Override
	public List<Categoria> findByExample(Categoria input) throws Exception {
		if (isNotActive()) {
			return null;
		}
		
		String query = "SELECT * FROM categoria WHERE nome like ?";
		
		
		ResultSet result = null;
		
		List<Categoria> lista = new ArrayList<Categoria>();
		try (
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, "%"+input.getNome()+"%");
            
            result = preparedStatement.executeQuery();
            
            while (result.next()) {
				Categoria categoria = new Categoria();
				categoria.setId(result.getLong("id_categoria"));
				categoria.setNome(result.getString("nome"));
				lista.add(categoria);
				
			}
        } catch (SQLException e) {
            e.printStackTrace();
        } 
		
		
		return lista;
		
		
	}

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;

	}
	
}
