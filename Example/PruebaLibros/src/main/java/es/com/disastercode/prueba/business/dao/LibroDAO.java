package es.com.disastercode.prueba.business.dao;

import java.util.List;
import es.com.disastercode.prueba.business.vo.LibroVO;


/**
 * Interface DAO - LibroDAO
 */
public interface LibroDAO {

	/**
	 * M�todo que devuelve un objeto (LibroVO) tras pasarle como par�metro la clave primaria.
	 * @param id - Integer 
	 * @return LibroVO
	 */
	public LibroVO getLibro(Long id);

	/**
	 * M�todo que inserta un nuevo objeto (LibroVO) en la BD.
	 * @param libro - LibroVO 
	 * @return (clave creada) - Integer
	 */
	public Long newLibro(LibroVO libro);

	/**
	 * M�todo que actualiza un objeto (LibroVO) en la BD.
	 * @param libro - LibroVO 
	 */
	public void editLibro(LibroVO libro);

	/**
	 * M�todo que borra un objeto (LibroVO) tras pasarle como par�metro la clave primaria.
	 * @param id - Integer 
	 */
	public void deleteLibro(Long id);

	/**
	 * M�todo que devuelve una lista de objetos (LibroVO) que cumplan las condiciones del objeto pasado como par�metro.
	 * @param libro - LibroVO 
	 * @return (listado obtenido) - List<LibroVO>
	 */
	public List<LibroVO> findLibro(LibroVO libro);

}
