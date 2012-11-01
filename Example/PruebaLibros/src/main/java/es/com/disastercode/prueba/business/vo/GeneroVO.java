package es.com.disastercode.prueba.business.vo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.Set;


/**
 * Clase Value Object - GeneroVO
 */
public class GeneroVO {

	private static Log log = LogFactory.getFactory().getInstance(GeneroVO.class);


	private Long idGenero;
	private String nombre;
	private Set<LibroVO> libros;


	public Long getIdGenero(){
		return this.idGenero;
	}
	public void setIdGenero( Long idGenero ){
		this.idGenero = idGenero;
	}

	public String getNombre(){
		return this.nombre;
	}
	public void setNombre( String nombre ){
		this.nombre = nombre;
	}

	public Set<LibroVO> getLibros(){
		return this.libros;
	}
	public void setLibros( Set<LibroVO> libros ){
		this.libros = libros;
	}

	public String getSelectValue(){
		String rVal = "";
		rVal += "[idGenero = "+this.idGenero+"] ";
		rVal += "[nombre = "+this.nombre+"] ";
		return rVal;
	}
}
