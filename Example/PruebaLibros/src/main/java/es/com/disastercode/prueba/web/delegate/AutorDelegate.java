package es.com.disastercode.prueba.web.delegate;

import es.com.disastercode.prueba.business.vo.*;
import es.com.disastercode.prueba.business.manager.*;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Clase Delegate - AutorDelegate
 */
public class AutorDelegate {

	private static Log log = LogFactory.getFactory().getInstance(AutorDelegate.class);
	private AutorManager autorManager;

	public AutorManager getAutorManager(){
		return this.autorManager;
	}
	public void setAutorManager( AutorManager autorManager ){
		this.autorManager = autorManager;
	}

	/**
	 * Método que devuelve un objeto (AutorVO) tras pasarle como parámetro la clave primaria.
	 * @param id - Integer 
	 * @return AutorVO
	 */
	public AutorVO getAutor(Long id){
		return this.autorManager.getAutor(id);
	}

	/**
	 * Método que inserta un nuevo objeto (AutorVO) en la BD.
	 * @param autor - AutorVO 
	 * @return (clave creada) - Integer
	 */
	public Long newAutor(AutorVO autor){
		return this.autorManager.newAutor(autor);
	}

	/**
	 * Método que actualiza un objeto (AutorVO) en la BD.
	 * @param autor - AutorVO 
	 */
	public void editAutor(AutorVO autor){
		this.autorManager.editAutor(autor);
	}

	/**
	 * Método que borra un objeto (AutorVO) tras pasarle como parámetro la clave primaria.
	 * @param id - Integer 
	 */
	public void deleteAutor(Long id){
		this.autorManager.deleteAutor(id);
	}

	/**
	 * Método que devuelve una lista de objetos (AutorVO) que cumplan las condiciones del objeto pasado como parámetro.
	 * @param autor - AutorVO 
	 * @return (listado obtenido) - List<AutorVO>
	 */
	public List<AutorVO> findAutor(AutorVO autor){
		return this.autorManager.findAutor(autor);
	}

}
