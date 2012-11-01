package Var_NOMBREPAKETE.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import Var_NOMBREPAKETE.utils.*;

public class InicioAction extends Action{

	private static Log log = LogFactory.getFactory().getInstance(InicioAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		Herramientas.creaBreadcrumb(request, NavigationKeys.MIGA_INICIO, true);
		request.setAttribute(RequestKeys.OPCION_MENU, NavigationKeys.OPCION_MENU_INICIO);
		return mapping.findForward("success");
	}
}
