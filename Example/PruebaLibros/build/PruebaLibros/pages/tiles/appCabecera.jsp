<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<%@page import="es.com.disastercode.prueba.utils.*"%>
<%@page import="es.com.disastercode.prueba.business.vo.*"%>

<div id="seccion_sup">
	<div class="container_16">
		<div class="grid_8">
			<h1>Sotilizator</h1>
		</div>
		<div class="grid_8 right relative">
			<a href="http://creativecommons.org/licenses/by-nc-sa/3.0/" target="_blank" title="Licensed under a Creative Commons Attribution-Noncommercial-Share Alike 3.0">
				<img src="<%=request.getContextPath()%>/pages/img/by-nc-sa.eu_petit.png"/>
			</a>
		</div>
	</div>
</div>

<div class="nav_app">
	<div class="container_16">
		<div class="grid_16">
			<ul class="sf-menu">
				
				<li<logic:equal name="opcionMenu" value="<%=NavigationKeys.OPCION_MENU_INICIO%>"> class="active"</logic:equal>><html:link action="Welcome.do">Inicio</html:link></li>
					
				<li<logic:equal name="opcionMenu" value="<%=NavigationKeys.OPCION_MENU_ADMINISTRACION%>"> class="active"</logic:equal>><a href="#">Administración</a>
					<ul>

						<li><html:link action="MaestroAutorAction.do?method=inicio">Autor</html:link></li>

						<li><html:link action="MaestroGeneroAction.do?method=inicio">Genero</html:link></li>

						<li><html:link action="MaestroLibroAction.do?method=inicio">Libro</html:link></li>
					</ul>
				</li>

			</ul>
		</div>
	</div>
</div>
	
