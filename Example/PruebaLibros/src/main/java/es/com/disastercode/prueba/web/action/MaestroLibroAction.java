package es.com.disastercode.prueba.web.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import es.com.disastercode.prueba.web.action.MaestroLibroAction;
import es.com.disastercode.prueba.business.vo.*;
import es.com.disastercode.prueba.web.delegate.*;
import es.com.disastercode.prueba.utils.*;
import es.com.disastercode.prueba.web.form.*;


/**
 * Action (MaestroLibroAction) para el maestro del objeto LibroVO
 */
public class MaestroLibroAction extends SotilezaDispatchAction {

	private static Log log = LogFactory.getFactory().getInstance(MaestroLibroAction.class);
	private LibroDelegate libroDelegate;

	private AutorDelegate autorDelegate;

	private GeneroDelegate generoDelegate;

	public AutorDelegate getAutorDelegate(){
		return this.autorDelegate;
	}
	public void setAutorDelegate( AutorDelegate autorDelegate ){
		this.autorDelegate = autorDelegate;
	}

	public GeneroDelegate getGeneroDelegate(){
		return this.generoDelegate;
	}
	public void setGeneroDelegate( GeneroDelegate generoDelegate ){
		this.generoDelegate = generoDelegate;
	}

	public LibroDelegate getLibroDelegate(){
		return this.libroDelegate;
	}
	public void setLibroDelegate( LibroDelegate libroDelegate ){
		this.libroDelegate = libroDelegate;
	}


	/**
	 * method inicio - carga en la request una lista de todos los elementos 
	 * 
	 * @param map - ActionMapping 
	 * @param form - ActionForm 
	 * @param req - HttpServletRequest 
	 * @param res - HttpServletResponse 
	 * @return ActionForward 
	 */
	public ActionForward inicio(ActionMapping map, ActionForm form, HttpServletRequest req, HttpServletResponse res) {
		List<LibroVO> lista = this.libroDelegate.findLibro(new LibroVO());
			List<AutorVO> listaAutorVO = this.autorDelegate.findAutor(new AutorVO());
			req.setAttribute(RequestKeys.MAESTRO_LISTA_AUTOR, listaAutorVO);
			List<GeneroVO> listaGeneroVO = this.generoDelegate.findGenero(new GeneroVO());
			req.setAttribute(RequestKeys.MAESTRO_LISTA_GENERO, listaGeneroVO);
		((LibroForm)form).reset(map, req);
		req.setAttribute(RequestKeys.OPCION_MENU, NavigationKeys.OPCION_MENU_ADMINISTRACION);
		req.setAttribute(RequestKeys.MAESTRO_LISTA_LIBRO, lista);
		creaBreadcrumb(req, NavigationKeys.MIGA_MAESTRO_LIBROVO, true);
		return map.findForward("success");
	}


	/**
	 * method consulta - carga en la request una lista con los elementos filtrados 
	 * 
	 * @param map - ActionMapping 
	 * @param form - ActionForm 
	 * @param req - HttpServletRequest 
	 * @param res - HttpServletResponse 
	 * @return ActionForward 
	 */
	public ActionForward consulta(ActionMapping map, ActionForm form, HttpServletRequest req, HttpServletResponse res) {
		try{
			LibroVO x = ((LibroForm)form).populate();
			List<LibroVO> lista = this.libroDelegate.findLibro(x);
			req.setAttribute(RequestKeys.MAESTRO_LISTA_LIBRO, lista);
			List<AutorVO> listaAutorVO = this.autorDelegate.findAutor(new AutorVO());
			req.setAttribute(RequestKeys.MAESTRO_LISTA_AUTOR, listaAutorVO);
			List<GeneroVO> listaGeneroVO = this.generoDelegate.findGenero(new GeneroVO());
			req.setAttribute(RequestKeys.MAESTRO_LISTA_GENERO, listaGeneroVO);
			req.setAttribute(RequestKeys.OPCION_MENU, NavigationKeys.OPCION_MENU_ADMINISTRACION);
			creaBreadcrumb(req, NavigationKeys.MIGA_MAESTRO_LIBROVO, true);
			return map.findForward("consulta");
		}catch(Exception e){
			req.getSession().setAttribute(SessionKeys.EXCEPCION, e);
			req.getSession().setAttribute(SessionKeys.MSJ_ERROR, e.getMessage() );
			return map.findForward("failure");
		}
	}


	/**
	 * method guardar - crea/actualiza el elemento pasado por el form en la BD. 
	 * 
	 * @param map - ActionMapping 
	 * @param form - ActionForm 
	 * @param req - HttpServletRequest 
	 * @param res - HttpServletResponse 
	 * @return ActionForward 
	 */
	public ActionForward guardar(ActionMapping map, ActionForm form, HttpServletRequest req, HttpServletResponse res) {
		try {
			LibroVO x = ((LibroForm)form).populate();
			if(x.getIdLibro()==null || x.getIdLibro().toString().equals("0"))
				this.libroDelegate.newLibro(x);
			else
				this.libroDelegate.editLibro(x);
			ActionMessages mensajes = new ActionMessages();
			mensajes.add("info", new ActionMessage("maestros.info.modificada"));
			saveMessages(req, mensajes);
		}catch (Exception e) {
			log.error("Error guardando maestro...", e);
			ActionMessages errors = new ActionMessages();
			errors.add("errMsg", new ActionMessage("maestros.error.modificada")); 
			saveErrors(req, errors);
			return map.findForward("failure");
		}
		return map.findForward("consultaForward");
	}


	/**
	 * method borrar - elimina el elemento con el id pasado por la request. 
	 * 
	 * @param map - ActionMapping 
	 * @param form - ActionForm 
	 * @param req - HttpServletRequest 
	 * @param res - HttpServletResponse 
	 * @return ActionForward 
	 */
	public ActionForward borrar(ActionMapping map, ActionForm form, HttpServletRequest req, HttpServletResponse res) {
		try {
			String id = req.getParameter(RequestKeys.MAESTRO_ID_LIBRO);
			this.libroDelegate.deleteLibro(Long.parseLong(id));
			ActionMessages mensajes = new ActionMessages();
			mensajes.add("info", new ActionMessage("maestros.info.borrada"));
			saveMessages(req, mensajes);
		}catch (Exception e) {
			ActionMessages errors = new ActionMessages();
			errors.add("errMsg", new ActionMessage("maestros.error.borrar"));
			saveErrors(req, errors);
		}
		return map.findForward("consultaForward");
	}

}
