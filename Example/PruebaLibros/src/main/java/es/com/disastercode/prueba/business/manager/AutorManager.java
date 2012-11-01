package es.com.disastercode.prueba.business.manager;

import es.com.disastercode.prueba.business.vo.*;
import es.com.disastercode.prueba.business.dao.*;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Clase Manager - AutorManager
 */
public class AutorManager {

	private static Log log = LogFactory.getFactory().getInstance(AutorManager.class);
	private AutorDAO autorDAO;

	private LibroDAO libroDAO;

	public LibroDAO getLibroDAO(){
		return this.libroDAO;
	}
	public void setLibroDAO( LibroDAO libroDAO ){
		this.libroDAO = libroDAO;
	}

	public AutorDAO getAutorDAO(){
		return this.autorDAO;
	}
	public void setAutorDAO( AutorDAO autorDAO ){
		this.autorDAO = autorDAO;
	}

	/**
	 * M�todo que devuelve un objeto (AutorVO) tras pasarle como par�metro la clave primaria.
	 * @param id - Integer 
	 * @return AutorVO
	 */
	public AutorVO getAutor(Long id){
		return this.autorDAO.getAutor(id);
	}

	/**
	 * M�todo que inserta un nuevo objeto (AutorVO) en la BD.
	 * @param autor - AutorVO 
	 * @return (clave creada) - Integer
	 */
	public Long newAutor(AutorVO autor){
		return this.autorDAO.newAutor(autor);
	}

	/**
	 * M�todo que actualiza un objeto (AutorVO) en la BD.
	 * @param autor - AutorVO 
	 */
	public void editAutor(AutorVO autor){
		this.autorDAO.editAutor(autor);
	}

	/**
	 * M�todo que borra un objeto (AutorVO) tras pasarle como par�metro la clave primaria.
	 * @param id - Integer 
	 */
	public void deleteAutor(Long id){
		this.autorDAO.deleteAutor(id);
	}

	/**
	 * M�todo que devuelve una lista de objetos (AutorVO) que cumplan las condiciones del objeto pasado como par�metro.
	 * @param autor - AutorVO 
	 * @return (listado obtenido) - List<AutorVO>
	 */
	public List<AutorVO> findAutor(AutorVO autor){
		return this.autorDAO.findAutor(autor);
	}

}
