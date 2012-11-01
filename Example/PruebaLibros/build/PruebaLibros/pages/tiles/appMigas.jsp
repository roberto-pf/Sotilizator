<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<%@page import="es.com.disastercode.prueba.utils.SessionKeys"%>
<logic:present name="<%= SessionKeys.MIGAS_DE_PAN %>" scope="session"> 
	<div class="migas_de_pan media">
		<logic:iterate name="<%= SessionKeys.MIGAS_DE_PAN %>" id="miga" indexId="i">
			<logic:notEqual name="i" value="0"> >> </logic:notEqual>
			<a href="<bean:write name="miga" property="url"/>"><bean:write name="miga" property="titulo"/></a>
		</logic:iterate> 
	</div>
</logic:present>
