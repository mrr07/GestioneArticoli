package it.gestionearticoli.dao.articolo;

//import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.gestionearticoli.dao.AbstractMySQLDAO;
import it.gestionearticoli.model.Articolo;
import it.gestionearticoli.model.Categoria;

public class ArticoloDAOImpl extends AbstractMySQLDAO implements ArticoloDAO {

	@Override
	public List<Articolo> list() throws Exception {
		if (isNotActive()) {
			return null;
		}

		ArrayList<Articolo> result = new ArrayList<Articolo>();
		Articolo articoloTemp = null;

		try (Statement ps = connection.createStatement()) {
			ResultSet rs = ps.executeQuery("select * from articolo");

			while (rs.next()) {
				articoloTemp = new Articolo();
				articoloTemp.setCodice(rs.getString("codice"));
				articoloTemp.setDescrizione(rs.getString("descrizione"));
				articoloTemp.setPrezzo(rs.getInt("prezzo"));
				articoloTemp.setId(rs.getLong("id_articolo"));
				Categoria categoria = new Categoria();
				categoria.setId(rs.getLong("categoria"));
				articoloTemp.setCategoria(categoria);
				result.add(articoloTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Articolo get(Long id) throws Exception {
		if (isNotActive()) {
			return null;
		}
		
		
		ResultSet result = null;
		Articolo articolo = new Articolo();
		try (PreparedStatement ps = connection
				.prepareStatement("select * from articolo where id_articolo=?")) {
			
			ps.setLong(1, id);
			result = ps.executeQuery();

			while (result.next()) {
				
				articolo.setCodice(result.getString("codice"));
				articolo.setDescrizione(result.getString("descrizione"));
				articolo.setPrezzo(result.getInt("prezzo"));
				articolo.setId(result.getLong("id_articolo"));
				Categoria categoria = new Categoria();
				categoria.setId(result.getLong("categoria"));
				articolo.setCategoria(categoria);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		
		return articolo;
	}
	
	@Override
	public List<Articolo> getByCategoria(Categoria categoria) throws Exception {
		if (isNotActive()) {
			return null;
		}
		
		List<Articolo> result = new ArrayList<Articolo>();
		Articolo articoloTemp = null;
		ResultSet resultSet = null;

		try (PreparedStatement ps = connection
				.prepareStatement("select * from articolo where categoria = ?")){
			
			ps.setLong(1, categoria.getId());
			resultSet = ps.executeQuery();

			while (resultSet.next()) {
				articoloTemp = new Articolo();
				articoloTemp.setCodice(resultSet.getString("codice"));
				articoloTemp.setDescrizione(resultSet.getString("descrizione"));
				articoloTemp.setPrezzo(resultSet.getInt("prezzo"));
				articoloTemp.setId(resultSet.getLong("id_articolo"));
				//Categoria categoria1 = new Categoria();
				//categoria.setId(rs.getLong("categoria"));
				articoloTemp.setCategoria(categoria);
				result.add(articoloTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
		
	}
	
	public Articolo get(String nome) throws Exception {
		if (isNotActive()) {
			return null;
		}
		
		
		ResultSet result = null;
		Articolo articolo = new Articolo();
		try (PreparedStatement ps = connection
				.prepareStatement("select * from articolo where nome=?")) {
			
			ps.setString(1, nome);
			result = ps.executeQuery();

			while (result.next()) {
				
				articolo.setCodice(result.getString("codice"));
				articolo.setDescrizione(result.getString("descrizione"));
				articolo.setPrezzo(result.getInt("prezzo"));
				articolo.setId(result.getLong("id_articolo"));
				Categoria categoria = new Categoria();
				categoria.setId(result.getLong("categoria"));
				articolo.setCategoria(categoria);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		
		return articolo;
	}

	@Override
	public int update(Articolo input) throws Exception {
		String query = "UPDATE articolo SET codice = ?, descrizione = ?, prezzo = ?, categoria = ? WHERE id_articolo = ?";
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, input.getCodice());
            preparedStatement.setString(2, input.getDescrizione());
            preparedStatement.setInt(3, input.getPrezzo());
            preparedStatement.setLong(4, input.getCategoria().getId());
            preparedStatement.setLong(5, input.getId());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return 0;
	}

	@Override
	public int insert(Articolo input) throws Exception {
		if (isNotActive() || input == null) {
			return -1;
		}

		int result = 0;

		try (PreparedStatement ps = connection
				.prepareStatement("INSERT INTO articolo (codice, descrizione, prezzo, categoria) VALUES (?, ?, ?, ?);")) {
			ps.setString(1, input.getCodice());
			ps.setString(2, input.getDescrizione());
			ps.setInt(3, input.getPrezzo());
			ps.setLong(4, input.getCategoria().getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int delete(Articolo input) throws Exception {
		if (isNotActive() || input == null) {
			return -1;
		}
		
		String query = "DELETE FROM articolo WHERE id_articolo = ?";
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setLong(1, input.getId());
            
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return 0;
	}

	@Override
	public List<Articolo> findByExample(Articolo input) throws Exception {
		if (isNotActive()) {
			return null;
		}
		
		int valoriNull = 0;
		int valoriAnd = 0;
		String query = "";
		int[] array= {0,0,0,0,0};
		
		if(input.getCodice() == "") {
			valoriNull++;
		}
		if(input.getDescrizione() == "") {
			valoriNull++;
		}
		if(input.getPrezzo() == null) {
			valoriNull++;
		}
		if(input.getCategoria() == null) {
			valoriNull++;
		}
		if(valoriNull == 4) {
			query = "SELECT * FROM articolo";
		}
		if(valoriNull == 0) {
			query = "SELECT * FROM articolo WHERE codice like ? "
					+ "AND descrizione like ? "
					+ "AND prezzo = ?"
					+ "AND categoria = ?";
		}
		else if(valoriNull != 4 && valoriNull != 0){
			query = "SELECT * FROM articolo WHERE ";
			valoriAnd=3-valoriNull;
			if(input.getCodice() != "" && valoriNull != 0) {
				query = query.concat("codice like ? ");
				if(valoriAnd != 0) {
					query = query.concat("and ");
					valoriAnd--;
				}
				array[1]=1;
				
			}
			if(input.getDescrizione() != "" && valoriNull != 0) {
				query = query.concat("descrizione like ? ");
				if(valoriAnd != 0) {
					query = query.concat("and ");
					valoriAnd--;
				}
				array[2]=1;
			}
			if(input.getPrezzo() != null && valoriNull != 0) {
				query = query.concat("prezzo = ? ");
				if(valoriAnd != 0) {
					query = query.concat("and ");
					valoriAnd--;
				}
				array[3]=1;
			}
			if(input.getCategoria() != null && valoriNull != 0) {
				query = query.concat("categoria = ? ");
				array[4]=1;
			}
		}
		
		//System.out.println(query);
		
		ResultSet result = null;
		List<Articolo> lista = new ArrayList<Articolo>();
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			int k=1;
			//for(int i=1; i<array.length-1; i++) {
				if(array[1] == 1) {
					if(input.getCodice() != "") {
						ps.setString(k, input.getCodice());
					}
					array[1]=0;
					k++;
				}
				
				if(array[2] == 1) {
					if(input.getDescrizione() != "") {
						ps.setString(k, input.getDescrizione());
					}
					array[2]=0;
					k++;
				}
				
				if(array[3] == 1) {
					if(input.getPrezzo() != null) {
						ps.setInt(k, input.getPrezzo());
					}
					k++;
					array[3]=0;
				}
				
				if(array[4] == 1) {
					if(input.getCategoria() != null) {
						ps.setLong(k, input.getCategoria().getId());
					}
					k++;
					array[4]=0;
				}
			//}
			
			
			
			result = ps.executeQuery();

			while (result.next()) {
				Articolo articolo = new Articolo();
				articolo.setCodice(result.getString("codice"));
				articolo.setDescrizione(result.getString("descrizione"));
				articolo.setPrezzo(result.getInt("prezzo"));
				articolo.setId(result.getLong("id_articolo"));
				Categoria categoria = new Categoria();
				categoria.setId(result.getLong("categoria"));
				articolo.setCategoria(categoria);
				lista.add(articolo);
				
			}
			

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return lista;
	}

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;

	}

}
