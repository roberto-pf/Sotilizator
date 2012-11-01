package es.com.disastercode.prueba.business.manager;

import es.com.disastercode.prueba.business.vo.*;
import es.com.disastercode.prueba.business.dao.*;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Clase Manager - GeneroManager
 */
public class GeneroManager {

	private static Log log = LogFactory.getFactory().getInstance(GeneroManager.class);
	private GeneroDAO generoDAO;

	private LibroDAO libroDAO;

	public LibroDAO getLibroDAO(){
		return this.libroDAO;
	}
	public void setLibroDAO( LibroDAO libroDAO ){
		this.libroDAO = libroDAO;
	}

	public GeneroDAO getGeneroDAO(){
		return this.generoDAO;
	}
	public void setGeneroDAO( GeneroDAO generoDAO ){
		this.generoDAO = generoDAO;
	}

	/**
	 * M�todo que devuelve un objeto (GeneroVO) tras pasarle como par�metro la clave primaria.
	 * @param id - Integer 
	 * @return GeneroVO
	 */
	public GeneroVO getGenero(Long id){
		return this.generoDAO.getGenero(id);
	}

	/**
	 * M�todo que inserta un nuevo objeto (GeneroVO) en la BD.
	 * @param genero - GeneroVO 
	 * @return (clave creada) - Integer
	 */
	public Long newGenero(GeneroVO genero){
		return this.generoDAO.newGenero(genero);
	}

	/**
	 * M�todo que actualiza un objeto (GeneroVO) en la BD.
	 * @param genero - GeneroVO 
	 */
	public void editGenero(GeneroVO genero){
		this.generoDAO.editGenero(genero);
	}

	/**
	 * M�todo que borra un objeto (GeneroVO) tras pasarle como par�metro la clave primaria.
	 * @param id - Integer 
	 */
	public void deleteGenero(Long id){
		this.generoDAO.deleteGenero(id);
	}

	/**
	 * M�todo que devuelve una lista de objetos (GeneroVO) que cumplan las condiciones del objeto pasado como par�metro.
	 * @param genero - GeneroVO 
	 * @return (listado obtenido) - List<GeneroVO>
	 */
	public List<GeneroVO> findGenero(GeneroVO genero){
		return this.generoDAO.findGenero(genero);
	}

}
