package es.com.disastercode.prueba.web.dwr;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import es.com.disastercode.prueba.business.vo.*;
import es.com.disastercode.prueba.web.delegate.*;

public class MaestrosController {

	private static Log log = LogFactory.getFactory().getInstance(MaestrosController.class);
	private static ApplicationContext contextApplication = new ClassPathXmlApplicationContext(
			new String[] { "beans/business/dao-beans.xml",
			"beans/business/datasource-beans.xml",
			"beans/business/transaction-beans.xml",
			"beans/business/manager-beans.xml",
			"beans/web/delegate-beans.xml"});

	private AutorDelegate autorDelegate = (AutorDelegate)contextApplication.getBean("autorDelegate");

	private GeneroDelegate generoDelegate = (GeneroDelegate)contextApplication.getBean("generoDelegate");

	private LibroDelegate libroDelegate = (LibroDelegate)contextApplication.getBean("libroDelegate");
	public AutorVO getAutor(String id){
		AutorVO rVal = autorDelegate.getAutor(Long.valueOf(id));
		return rVal;
	}
	public GeneroVO getGenero(String id){
		GeneroVO rVal = generoDelegate.getGenero(Long.valueOf(id));
		return rVal;
	}
	public LibroVO getLibro(String id){
		LibroVO rVal = libroDelegate.getLibro(Long.valueOf(id));
		return rVal;
	}

}