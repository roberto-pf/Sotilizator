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
 * Clase ActionForm - GeneroForm
 */
public class GeneroForm extends ValidatorForm {

	private static Log log = LogFactory.getFactory().getInstance(GeneroForm.class);


	private Long idGenero;
	private String nombre;


	public GeneroForm() {
		super();
	}

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



	public void set(GeneroVO x) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		NumberFormat nf = NumberFormat.getInstance(Locale.GERMANY);

		this.idGenero = x.getIdGenero();

		this.nombre = x.getNombre();

	}

	public GeneroVO populate() throws ParseException{
		GeneroVO x = new GeneroVO();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		NumberFormat nf = NumberFormat.getInstance(Locale.GERMANY);

		x.setIdGenero(this.idGenero);

		x.setNombre(this.nombre);

		return x;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request){
		this.idGenero=null;
		this.nombre="";
	}

}
