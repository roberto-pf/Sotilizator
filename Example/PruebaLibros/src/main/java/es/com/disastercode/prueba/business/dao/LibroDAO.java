package es.com.disastercode.prueba.business.dao;

import java.util.List;
import es.com.disastercode.prueba.business.vo.LibroVO;


/**
 * Interface DAO - LibroDAO
 */
public interface LibroDAO {

	/**
	 * Método que devuelve un objeto (LibroVO) tras pasarle como parámetro la clave primaria.
	 * @param id - Integer 
	 * @return LibroVO
	 */
	public LibroVO getLibro(Long id);

	/**
	 * Método que inserta un nuevo objeto (LibroVO) en la BD.
	 * @param libro - LibroVO 
	 * @return (clave creada) - Integer
	 */
	public Long newLibro(LibroVO libro);

	/**
	 * Método que actualiza un objeto (LibroVO) en la BD.
	 * @param libro - LibroVO 
	 */
	public void editLibro(LibroVO libro);

	/**
	 * Método que borra un objeto (LibroVO) tras pasarle como parámetro la clave primaria.
	 * @param id - Integer 
	 */
	public void deleteLibro(Long id);

	/**
	 * Método que devuelve una lista de objetos (LibroVO) que cumplan las condiciones del objeto pasado como parámetro.
	 * @param libro - LibroVO 
	 * @return (listado obtenido) - List<LibroVO>
	 */
	public List<LibroVO> findLibro(LibroVO libro);

}
