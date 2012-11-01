package es.com.disastercode.prueba.business.dao;

import java.util.List;
import es.com.disastercode.prueba.business.vo.GeneroVO;


/**
 * Interface DAO - GeneroDAO
 */
public interface GeneroDAO {

	/**
	 * Método que devuelve un objeto (GeneroVO) tras pasarle como parámetro la clave primaria.
	 * @param id - Integer 
	 * @return GeneroVO
	 */
	public GeneroVO getGenero(Long id);

	/**
	 * Método que inserta un nuevo objeto (GeneroVO) en la BD.
	 * @param genero - GeneroVO 
	 * @return (clave creada) - Integer
	 */
	public Long newGenero(GeneroVO genero);

	/**
	 * Método que actualiza un objeto (GeneroVO) en la BD.
	 * @param genero - GeneroVO 
	 */
	public void editGenero(GeneroVO genero);

	/**
	 * Método que borra un objeto (GeneroVO) tras pasarle como parámetro la clave primaria.
	 * @param id - Integer 
	 */
	public void deleteGenero(Long id);

	/**
	 * Método que devuelve una lista de objetos (GeneroVO) que cumplan las condiciones del objeto pasado como parámetro.
	 * @param genero - GeneroVO 
	 * @return (listado obtenido) - List<GeneroVO>
	 */
	public List<GeneroVO> findGenero(GeneroVO genero);

}
