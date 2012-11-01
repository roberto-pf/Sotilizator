<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
     "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>     
<%@ taglib prefix="tiles" uri="http://struts.apache.org/tags-tiles"%>
<html:html xhtml="true">
    <!-- head de la pagina -->		
	<tiles:insert attribute="app.head" ignore="true" />
	<body>
		<div id="container">               
			<!-- Cabecera de la pagina -->		
			<tiles:insert attribute="app.cabecera" ignore="true" />	
            <div id="content">
                <!-- Cuerpo principal de la página -->
                <tiles:insert attribute="app.cuerpo" ignore="true" />	

            </div>
		</div>
	
	</body>
</html:html>
