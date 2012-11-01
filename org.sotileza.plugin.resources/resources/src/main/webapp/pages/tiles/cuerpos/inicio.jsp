<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<div class="contenido_app">
	<div class="container_16">
		<div class="grid_16">
			<div id="col_izquierda">
				<h1><bean:message key="welcome.heading"/></h1>
				<jsp:include page="../appMensajes.jsp" flush="true"/>
			
			</div>
		</div>
	</div>
</div>
