package es.com.disastercode.prueba.business.dao;

import java.util.List;
import es.com.disastercode.prueba.business.vo.AutorVO;


/**
 * Interface DAO - AutorDAO
 */
public interface AutorDAO {

	/**
	 * M�todo que devuelve un objeto (AutorVO) tras pasarle como par�metro la clave primaria.
	 * @param id - Integer 
	 * @return AutorVO
	 */
	public AutorVO getAutor(Long id);

	/**
	 * M�todo que inserta un nuevo objeto (AutorVO) en la BD.
	 * @param autor - AutorVO 
	 * @return (clave creada) - Integer
	 */
	public Long newAutor(AutorVO autor);

	/**
	 * M�todo que actualiza un objeto (AutorVO) en la BD.
	 * @param autor - AutorVO 
	 */
	public void editAutor(AutorVO autor);

	/**
	 * M�todo que borra un objeto (AutorVO) tras pasarle como par�metro la clave primaria.
	 * @param id - Integer 
	 */
	public void deleteAutor(Long id);

	/**
	 * M�todo que devuelve una lista de objetos (AutorVO) que cumplan las condiciones del objeto pasado como par�metro.
	 * @param autor - AutorVO 
	 * @return (listado obtenido) - List<AutorVO>
	 */
	public List<AutorVO> findAutor(AutorVO autor);

}
