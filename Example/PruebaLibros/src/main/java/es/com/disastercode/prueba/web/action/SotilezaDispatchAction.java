package es.com.disastercode.prueba.web.action;

import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import es.com.disastercode.prueba.utils.Breadcrumb;
import es.com.disastercode.prueba.utils.SessionKeys;

public class SotilezaDispatchAction extends DispatchAction {
	
	private static Log log = LogFactory.getFactory().getInstance(SotilezaDispatchAction.class);
	
	protected String getMethodName(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response,
            String parameter) throws Exception {

		// Identify the method name to be dispatched to.
		// dispatchMethod() will call unspecified() if name is null
		String methodName = super.getMethodName(mapping, form, request, response, parameter);
		log.info("METHOD NAME (DispatchAction):"+methodName);
		return request.getParameter(parameter);
	}
	
	public ActionForward execute(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
					
		String name = request.getParameter("tipo");
		String method = request.getParameter("method");
		if(method==null) {
			method = "execute";
		}
		String nameClase = this.getClass().getName();
		
		//obtener url invocada
		if(method != null && method.length() != 0)
			name=method;
		log.info("   ");
		log.info("Ejecutando la clase: "+nameClase.substring(nameClase.lastIndexOf(".")+1,nameClase.length())+", Método: "+name);
		// Invoke the named method, and return the result
		return dispatchMethod(mapping, form, request, response, name);
	}
	
	/**
	 * Función para crear las migas de pan. A partir de la request, se genera el paso actual, 
	 * añadiendolo a una colección de la sesión. La colección se resetea cuando la miga que 
	 * se le pasa es el padre.
	 * @param request
	 * @param titulo de la miga actual
	 * @param esPadre, si es true, resetea la colección para salir en primer lugar...
	 */
	protected void creaBreadcrumb(HttpServletRequest request, String titulo, boolean esPadre) {
		String url = request.getRequestURL().toString() + "?" + request.getQueryString();
		Breadcrumb miga = new Breadcrumb(titulo, url);
		TreeSet<Breadcrumb> migas = (TreeSet<Breadcrumb>)request.getSession().getAttribute(SessionKeys.MIGAS_DE_PAN);
		if(migas==null || esPadre) {
			migas = new TreeSet<Breadcrumb>();
		} 
		migas.add(miga);
		request.getSession().setAttribute(SessionKeys.MIGAS_DE_PAN, migas);
	}

}
