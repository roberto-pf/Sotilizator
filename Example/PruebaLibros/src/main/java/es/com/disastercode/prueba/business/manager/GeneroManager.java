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
	 * Método que devuelve un objeto (GeneroVO) tras pasarle como parámetro la clave primaria.
	 * @param id - Integer 
	 * @return GeneroVO
	 */
	public GeneroVO getGenero(Long id){
		return this.generoDAO.getGenero(id);
	}

	/**
	 * Método que inserta un nuevo objeto (GeneroVO) en la BD.
	 * @param genero - GeneroVO 
	 * @return (clave creada) - Integer
	 */
	public Long newGenero(GeneroVO genero){
		return this.generoDAO.newGenero(genero);
	}

	/**
	 * Método que actualiza un objeto (GeneroVO) en la BD.
	 * @param genero - GeneroVO 
	 */
	public void editGenero(GeneroVO genero){
		this.generoDAO.editGenero(genero);
	}

	/**
	 * Método que borra un objeto (GeneroVO) tras pasarle como parámetro la clave primaria.
	 * @param id - Integer 
	 */
	public void deleteGenero(Long id){
		this.generoDAO.deleteGenero(id);
	}

	/**
	 * Método que devuelve una lista de objetos (GeneroVO) que cumplan las condiciones del objeto pasado como parámetro.
	 * @param genero - GeneroVO 
	 * @return (listado obtenido) - List<GeneroVO>
	 */
	public List<GeneroVO> findGenero(GeneroVO genero){
		return this.generoDAO.findGenero(genero);
	}

}
