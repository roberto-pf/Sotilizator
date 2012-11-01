package es.com.disastercode.prueba.business.vo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import es.com.disastercode.prueba.utils.Herramientas;


/**
 * Clase Value Object - LibroVO
 */
public class LibroVO {

	private static Log log = LogFactory.getFactory().getInstance(LibroVO.class);


	private Long idLibro;
	private AutorVO autor;
	private GeneroVO genero;
	private String isbn;
	private String titulo;
	private String descripcion;
	private String resumen;
	private Double importeRecaudado;


	public Long getIdLibro(){
		return this.idLibro;
	}
	public void setIdLibro( Long idLibro ){
		this.idLibro = idLibro;
	}

	public AutorVO getAutor(){
		return this.autor;
	}
	public void setAutor( AutorVO autor ){
		this.autor = autor;
	}

	public GeneroVO getGenero(){
		return this.genero;
	}
	public void setGenero( GeneroVO genero ){
		this.genero = genero;
	}

	public String getIsbn(){
		return this.isbn;
	}
	public void setIsbn( String isbn ){
		this.isbn = isbn;
	}

	public String getTitulo(){
		return this.titulo;
	}
	public void setTitulo( String titulo ){
		this.titulo = titulo;
	}

	public String getDescripcion(){
		return this.descripcion;
	}
	public void setDescripcion( String descripcion ){
		this.descripcion = descripcion;
	}

	public String getResumen(){
		return this.resumen;
	}
	public void setResumen( String resumen ){
		this.resumen = resumen;
	}

	public Double getImporteRecaudado(){
		return this.importeRecaudado;
	}
	public void setImporteRecaudado( Double importeRecaudado ){
		this.importeRecaudado = importeRecaudado;
	}

	public String getImporteRecaudadoFormato(){
		String rVal = "0,00";
		if(this.importeRecaudado!=null)
			rVal = Herramientas.formatear(this.importeRecaudado);
		return rVal;
	}
	public String getSelectValue(){
		String rVal = "";
		rVal += "[idLibro = "+this.idLibro+"] ";
		rVal += "[isbn = "+this.isbn+"] ";
		rVal += "[titulo = "+this.titulo+"] ";
		rVal += "[descripcion = "+this.descripcion+"] ";
		rVal += "[resumen = "+this.resumen+"] ";
		rVal += "[importeRecaudado = "+this.importeRecaudado+"] ";
		return rVal;
	}
}
