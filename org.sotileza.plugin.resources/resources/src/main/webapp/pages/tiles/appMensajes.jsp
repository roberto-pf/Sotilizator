<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<%@page import="Var_NOMBREPAKETE.utils.*"%>


<html:errors />
<logic:messagesPresent property="info" message="true">
	<ul class="mensaje mensaje_ok">
		<html:messages property="info" id="infoMsg" message="true">
			<li>
				<span><bean:write name="infoMsg" /></span>
			</li>
		</html:messages>
	</ul>
</logic:messagesPresent>




<logic:present name="<%=SessionKeys.MSG_INFO %>" scope="session">
	<logic:notEmpty name="<%=SessionKeys.MSG_INFO %>" scope="session">
		<ul class="mensaje mensaje_ok">
			<li>
				<span><bean:write name="<%=SessionKeys.MSG_INFO %>" scope="session"/></span>
			</li>
		</ul>
		<% request.getSession().removeAttribute(SessionKeys.MSG_INFO); %>
	</logic:notEmpty>
</logic:present>

<logic:present name="<%=SessionKeys.MSG_ERROR %>" scope="session">
	<logic:notEmpty name="<%=SessionKeys.MSG_ERROR %>" scope="session">
		<ul class="mensaje mensaje_error">
			<li>
				<span><bean:write name="<%=SessionKeys.MSG_ERROR %>" scope="session"/></span>
			</li>
		</ul>	
		<% request.getSession().removeAttribute(SessionKeys.MSG_ERROR); %>
	</logic:notEmpty>
</logic:present>