package es.com.disastercode.prueba.business.vo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Set;


/**
 * Clase Value Object - AutorVO
 */
public class AutorVO {

	private static Log log = LogFactory.getFactory().getInstance(AutorVO.class);


	private Long idAutor;
	private String nombre;
	private String apellidos;
	private Date fechaNacimiento;
	private String telefono;
	private Boolean mujer;
	private Boolean hombre;
	private Set<LibroVO> libros;


	public Long getIdAutor(){
		return this.idAutor;
	}
	public void setIdAutor( Long idAutor ){
		this.idAutor = idAutor;
	}

	public String getNombre(){
		return this.nombre;
	}
	public void setNombre( String nombre ){
		this.nombre = nombre;
	}

	public String getApellidos(){
		return this.apellidos;
	}
	public void setApellidos( String apellidos ){
		this.apellidos = apellidos;
	}

	public Date getFechaNacimiento(){
		return this.fechaNacimiento;
	}
	public void setFechaNacimiento( Date fechaNacimiento ){
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getTelefono(){
		return this.telefono;
	}
	public void setTelefono( String telefono ){
		this.telefono = telefono;
	}

	public Boolean getMujer(){
		return this.mujer;
	}
	public void setMujer( Boolean mujer ){
		this.mujer = mujer;
	}

	public Boolean getHombre(){
		return this.hombre;
	}
	public void setHombre( Boolean hombre ){
		this.hombre = hombre;
	}

	public Set<LibroVO> getLibros(){
		return this.libros;
	}
	public void setLibros( Set<LibroVO> libros ){
		this.libros = libros;
	}

	public String getFechaNacimientoFormato(){
		SimpleDateFormat formato =	new SimpleDateFormat("dd-MM-yyyy");
		String rVal = "";
		if(this.fechaNacimiento!=null)
			rVal = formato.format(this.fechaNacimiento).toString();
		return rVal;
	}
	public String getSelectValue(){
		String rVal = "";
		rVal += "[idAutor = "+this.idAutor+"] ";
		rVal += "[nombre = "+this.nombre+"] ";
		rVal += "[apellidos = "+this.apellidos+"] ";
		rVal += "[fechaNacimiento = "+this.fechaNacimiento+"] ";
		rVal += "[telefono = "+this.telefono+"] ";
		return rVal;
	}
}
