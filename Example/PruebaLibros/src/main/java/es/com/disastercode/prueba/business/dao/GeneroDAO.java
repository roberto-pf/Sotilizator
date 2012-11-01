package es.com.disastercode.prueba.business.dao;

import java.util.List;
import es.com.disastercode.prueba.business.vo.GeneroVO;


/**
 * Interface DAO - GeneroDAO
 */
public interface GeneroDAO {

	/**
	 * M�todo que devuelve un objeto (GeneroVO) tras pasarle como par�metro la clave primaria.
	 * @param id - Integer 
	 * @return GeneroVO
	 */
	public GeneroVO getGenero(Long id);

	/**
	 * M�todo que inserta un nuevo objeto (GeneroVO) en la BD.
	 * @param genero - GeneroVO 
	 * @return (clave creada) - Integer
	 */
	public Long newGenero(GeneroVO genero);

	/**
	 * M�todo que actualiza un objeto (GeneroVO) en la BD.
	 * @param genero - GeneroVO 
	 */
	public void editGenero(GeneroVO genero);

	/**
	 * M�todo que borra un objeto (GeneroVO) tras pasarle como par�metro la clave primaria.
	 * @param id - Integer 
	 */
	public void deleteGenero(Long id);

	/**
	 * M�todo que devuelve una lista de objetos (GeneroVO) que cumplan las condiciones del objeto pasado como par�metro.
	 * @param genero - GeneroVO 
	 * @return (listado obtenido) - List<GeneroVO>
	 */
	public List<GeneroVO> findGenero(GeneroVO genero);

}
