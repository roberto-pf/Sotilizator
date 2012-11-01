package es.com.disastercode.prueba.web.delegate;

import es.com.disastercode.prueba.business.vo.*;
import es.com.disastercode.prueba.business.manager.*;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Clase Delegate - GeneroDelegate
 */
public class GeneroDelegate {

	private static Log log = LogFactory.getFactory().getInstance(GeneroDelegate.class);
	private GeneroManager generoManager;

	public GeneroManager getGeneroManager(){
		return this.generoManager;
	}
	public void setGeneroManager( GeneroManager generoManager ){
		this.generoManager = generoManager;
	}

	/**
	 * M�todo que devuelve un objeto (GeneroVO) tras pasarle como par�metro la clave primaria.
	 * @param id - Integer 
	 * @return GeneroVO
	 */
	public GeneroVO getGenero(Long id){
		return this.generoManager.getGenero(id);
	}

	/**
	 * M�todo que inserta un nuevo objeto (GeneroVO) en la BD.
	 * @param genero - GeneroVO 
	 * @return (clave creada) - Integer
	 */
	public Long newGenero(GeneroVO genero){
		return this.generoManager.newGenero(genero);
	}

	/**
	 * M�todo que actualiza un objeto (GeneroVO) en la BD.
	 * @param genero - GeneroVO 
	 */
	public void editGenero(GeneroVO genero){
		this.generoManager.editGenero(genero);
	}

	/**
	 * M�todo que borra un objeto (GeneroVO) tras pasarle como par�metro la clave primaria.
	 * @param id - Integer 
	 */
	public void deleteGenero(Long id){
		this.generoManager.deleteGenero(id);
	}

	/**
	 * M�todo que devuelve una lista de objetos (GeneroVO) que cumplan las condiciones del objeto pasado como par�metro.
	 * @param genero - GeneroVO 
	 * @return (listado obtenido) - List<GeneroVO>
	 */
	public List<GeneroVO> findGenero(GeneroVO genero){
		return this.generoManager.findGenero(genero);
	}

}
