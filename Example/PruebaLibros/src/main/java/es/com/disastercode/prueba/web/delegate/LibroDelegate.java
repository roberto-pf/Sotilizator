package es.com.disastercode.prueba.web.delegate;

import es.com.disastercode.prueba.business.vo.*;
import es.com.disastercode.prueba.business.manager.*;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Clase Delegate - LibroDelegate
 */
public class LibroDelegate {

	private static Log log = LogFactory.getFactory().getInstance(LibroDelegate.class);
	private LibroManager libroManager;

	public LibroManager getLibroManager(){
		return this.libroManager;
	}
	public void setLibroManager( LibroManager libroManager ){
		this.libroManager = libroManager;
	}

	/**
	 * M�todo que devuelve un objeto (LibroVO) tras pasarle como par�metro la clave primaria.
	 * @param id - Integer 
	 * @return LibroVO
	 */
	public LibroVO getLibro(Long id){
		return this.libroManager.getLibro(id);
	}

	/**
	 * M�todo que inserta un nuevo objeto (LibroVO) en la BD.
	 * @param libro - LibroVO 
	 * @return (clave creada) - Integer
	 */
	public Long newLibro(LibroVO libro){
		return this.libroManager.newLibro(libro);
	}

	/**
	 * M�todo que actualiza un objeto (LibroVO) en la BD.
	 * @param libro - LibroVO 
	 */
	public void editLibro(LibroVO libro){
		this.libroManager.editLibro(libro);
	}

	/**
	 * M�todo que borra un objeto (LibroVO) tras pasarle como par�metro la clave primaria.
	 * @param id - Integer 
	 */
	public void deleteLibro(Long id){
		this.libroManager.deleteLibro(id);
	}

	/**
	 * M�todo que devuelve una lista de objetos (LibroVO) que cumplan las condiciones del objeto pasado como par�metro.
	 * @param libro - LibroVO 
	 * @return (listado obtenido) - List<LibroVO>
	 */
	public List<LibroVO> findLibro(LibroVO libro){
		return this.libroManager.findLibro(libro);
	}

}
