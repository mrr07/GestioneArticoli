package it.gestionearticoli.dao.articolo;

import java.util.List;

import it.gestionearticoli.dao.IBaseDAO;
import it.gestionearticoli.model.Articolo;
import it.gestionearticoli.model.Categoria;

public interface ArticoloDAO extends IBaseDAO<Articolo> {

	List<Articolo> getByCategoria(Categoria categoria) throws Exception;
}
