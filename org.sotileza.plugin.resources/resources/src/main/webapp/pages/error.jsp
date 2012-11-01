<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<%@page import="Var_NOMBREPAKETE.utils.SessionKeys"%>

	
<div class="contenido_app">
	<div class="container_16">
		<div class="grid_16">
			<div id="col_izquierda">
				<h1>Error: <logic:present name="<%=SessionKeys.MSJ_ERROR %>"><bean:write name="<%=SessionKeys.MSJ_ERROR %>"/><% request.getSession().removeAttribute(SessionKeys.MSJ_ERROR); %></logic:present></h1>
				<ul class="mensaje mensaje_error">
					<li class="grande">Se ha producido un error en la aplicacion. Cierre la pestaña de la aplicacion y vuelva a acceder a la misma. Si el problema persiste pongase en contacto con el administrador de la pagina.</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<logic:present name="<%=SessionKeys.EXCEPCION %>">
	<%
		Exception e = (Exception) request.getSession().getAttribute(SessionKeys.EXCEPCION);
		response.getWriter().println(".");	
		response.getWriter().println("<!--");
		e.printStackTrace(response.getWriter());
		response.getWriter().println("-->");
		request.getSession().removeAttribute(SessionKeys.EXCEPCION);
	%>
</logic:present>






