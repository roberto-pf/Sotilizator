package es.com.disastercode.prueba.business.manager;

import es.com.disastercode.prueba.business.vo.*;
import es.com.disastercode.prueba.business.dao.*;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Clase Manager - LibroManager
 */
public class LibroManager {

	private static Log log = LogFactory.getFactory().getInstance(LibroManager.class);
	private LibroDAO libroDAO;

	public LibroDAO getLibroDAO(){
		return this.libroDAO;
	}
	public void setLibroDAO( LibroDAO libroDAO ){
		this.libroDAO = libroDAO;
	}

	/**
	 * Método que devuelve un objeto (LibroVO) tras pasarle como parámetro la clave primaria.
	 * @param id - Integer 
	 * @return LibroVO
	 */
	public LibroVO getLibro(Long id){
		return this.libroDAO.getLibro(id);
	}

	/**
	 * Método que inserta un nuevo objeto (LibroVO) en la BD.
	 * @param libro - LibroVO 
	 * @return (clave creada) - Integer
	 */
	public Long newLibro(LibroVO libro){
		return this.libroDAO.newLibro(libro);
	}

	/**
	 * Método que actualiza un objeto (LibroVO) en la BD.
	 * @param libro - LibroVO 
	 */
	public void editLibro(LibroVO libro){
		this.libroDAO.editLibro(libro);
	}

	/**
	 * Método que borra un objeto (LibroVO) tras pasarle como parámetro la clave primaria.
	 * @param id - Integer 
	 */
	public void deleteLibro(Long id){
		this.libroDAO.deleteLibro(id);
	}

	/**
	 * Método que devuelve una lista de objetos (LibroVO) que cumplan las condiciones del objeto pasado como parámetro.
	 * @param libro - LibroVO 
	 * @return (listado obtenido) - List<LibroVO>
	 */
	public List<LibroVO> findLibro(LibroVO libro){
		return this.libroDAO.findLibro(libro);
	}

}
