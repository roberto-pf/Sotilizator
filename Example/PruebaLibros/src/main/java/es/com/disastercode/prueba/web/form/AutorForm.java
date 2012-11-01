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
 * Clase ActionForm - AutorForm
 */
public class AutorForm extends ValidatorForm {

	private static Log log = LogFactory.getFactory().getInstance(AutorForm.class);


	private Long idAutor;
	private String nombre;
	private String apellidos;
	private String fechaNacimiento;
	private String telefono;
	private Boolean mujer;
	private Boolean hombre;


	public AutorForm() {
		super();
	}

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

	public String getFechaNacimiento(){
		return this.fechaNacimiento;
	}
	public void setFechaNacimiento( String fechaNacimiento ){
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



	public void set(AutorVO x) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		NumberFormat nf = NumberFormat.getInstance(Locale.GERMANY);

		this.idAutor = x.getIdAutor();

		this.nombre = x.getNombre();

		this.apellidos = x.getApellidos();

		this.fechaNacimiento = x.getFechaNacimiento()!=null?sdf.format(x.getFechaNacimiento()):null;

		this.telefono = x.getTelefono();

		this.mujer = x.getMujer();

		this.hombre = x.getHombre();

	}

	public AutorVO populate() throws ParseException{
		AutorVO x = new AutorVO();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		NumberFormat nf = NumberFormat.getInstance(Locale.GERMANY);

		x.setIdAutor(this.idAutor);

		x.setNombre(this.nombre);

		x.setApellidos(this.apellidos);

		if(!StringUtils.isBlank(this.fechaNacimiento)) {
			x.setFechaNacimiento(sdf.parse(this.fechaNacimiento));
		}

		x.setTelefono(this.telefono);

		x.setMujer(this.mujer==null?false:this.mujer);

		x.setHombre(this.hombre==null?false:this.hombre);

		return x;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request){
		this.idAutor=null;
		this.nombre="";
		this.apellidos="";
		this.telefono="";
		this.mujer=false;
		this.hombre=false;
	}

}
