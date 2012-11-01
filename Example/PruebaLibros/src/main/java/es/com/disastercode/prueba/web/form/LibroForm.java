package es.com.disastercode.prueba.web.form;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.validator.ValidatorForm;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.text.NumberFormat;
import java.util.Locale;
import es.com.disastercode.prueba.business.vo.*;


/**
 * Clase ActionForm - LibroForm
 */
public class LibroForm extends ValidatorForm {

	private static Log log = LogFactory.getFactory().getInstance(LibroForm.class);


	private Long idLibro;
	private AutorVO autor;
	private GeneroVO genero;
	private String isbn;
	private String titulo;
	private String descripcion;
	private String resumen;
	private String importeRecaudado;


	public LibroForm() {
		super();
		autor = new AutorVO();
		genero = new GeneroVO();
	}

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

	public String getImporteRecaudado(){
		return this.importeRecaudado;
	}
	public void setImporteRecaudado( String importeRecaudado ){
		this.importeRecaudado = importeRecaudado;
	}



	public void set(LibroVO x) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		NumberFormat nf = NumberFormat.getInstance(Locale.GERMANY);

		this.idLibro = x.getIdLibro();

		this.autor = x.getAutor();

		this.genero = x.getGenero();

		this.isbn = x.getIsbn();

		this.titulo = x.getTitulo();

		this.descripcion = x.getDescripcion();

		this.resumen = x.getResumen();

		this.importeRecaudado = x.getImporteRecaudado()!=null?nf.format(x.getImporteRecaudado()):"0";

	}

	public LibroVO populate() throws ParseException{
		LibroVO x = new LibroVO();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		NumberFormat nf = NumberFormat.getInstance(Locale.GERMANY);

		x.setIdLibro(this.idLibro);

		x.setAutor(this.autor);

		x.setGenero(this.genero);

		x.setIsbn(this.isbn);

		x.setTitulo(this.titulo);

		x.setDescripcion(this.descripcion);

		x.setResumen(this.resumen);

		if(!StringUtils.isBlank(this.importeRecaudado)) {
			x.setImporteRecaudado(nf.parse(this.importeRecaudado).doubleValue());
		}

		return x;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request){
		this.idLibro=null;
		this.isbn="";
		this.titulo="";
		this.descripcion="";
		this.resumen="";
		autor = new AutorVO();
		genero = new GeneroVO();
	}

}
