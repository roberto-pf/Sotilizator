package es.com.disastercode.prueba.business.dao;

import java.util.List;
import es.com.disastercode.prueba.business.vo.AutorVO;


/**
 * Interface DAO - AutorDAO
 */
public interface AutorDAO {

	/**
	 * Método que devuelve un objeto (AutorVO) tras pasarle como parámetro la clave primaria.
	 * @param id - Integer 
	 * @return AutorVO
	 */
	public AutorVO getAutor(Long id);

	/**
	 * Método que inserta un nuevo objeto (AutorVO) en la BD.
	 * @param autor - AutorVO 
	 * @return (clave creada) - Integer
	 */
	public Long newAutor(AutorVO autor);

	/**
	 * Método que actualiza un objeto (AutorVO) en la BD.
	 * @param autor - AutorVO 
	 */
	public void editAutor(AutorVO autor);

	/**
	 * Método que borra un objeto (AutorVO) tras pasarle como parámetro la clave primaria.
	 * @param id - Integer 
	 */
	public void deleteAutor(Long id);

	/**
	 * Método que devuelve una lista de objetos (AutorVO) que cumplan las condiciones del objeto pasado como parámetro.
	 * @param autor - AutorVO 
	 * @return (listado obtenido) - List<AutorVO>
	 */
	public List<AutorVO> findAutor(AutorVO autor);

}
