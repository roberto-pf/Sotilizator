<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<%@page import="es.com.disastercode.prueba.utils.*;"%>

<div class="contenido_app">
	<div class="container_16">
		<div class="grid_12">
			<div id="col_izquierda">
				<jsp:include page="../../../appMigas.jsp" flush="true"/>
				<h1>Autor</h1>
				<jsp:include page="../../../appMensajes.jsp" flush="true"/>
				<p class="botonera right">
					<a id="nuevo_Autor" href="#" class="sexybutton sexymedium spinner"><span><span><span class="add">Nuevo registro</span></span></span></a>
				</p>
				<display:table name="<%= RequestKeys.MAESTRO_LISTA_AUTOR %>" id="row" pagesize="20"
						export="false" class="pijama" requestURI="MaestroAutorAction.do?method=inicio">
					<display:column title="idAutor" property="idAutor" sortable="true"/>
					<display:column title="nombre" property="nombre" sortable="true"/>
					<display:column title="apellidos" property="apellidos" sortable="true"/>
					<display:column title="fechaNacimiento" property="fechaNacimientoFormato" sortable="true"/>
					<display:column title="telefono" property="telefono" sortable="true"/>
					<display:column title="mujer" sortProperty="mujer" sortable="true">
						<logic:equal name="row" property="mujer" value="true">
							<img src="<%=request.getContextPath()%>/pages/img/ico/tick.png"/>
						</logic:equal>
					</display:column>
					<display:column title="hombre" sortProperty="hombre" sortable="true">
						<logic:equal name="row" property="hombre" value="true">
							<img src="<%=request.getContextPath()%>/pages/img/ico/tick.png"/>
						</logic:equal>
					</display:column>
					<display:column class="right">
						<a id="<bean:write property="idAutor" name="row"/>" href="#" class="tip editar_Autor" title="Modificar Autor"><img src="<%=request.getContextPath()%>/pages/img/ico/edit.png"/></a>
						<a href="MaestroAutorAction.do?method=borrar&<%=RequestKeys.MAESTRO_ID_AUTOR%>=<bean:write property="idAutor" name="row"/>" class="tip eliminar_registro" title="Eliminar Registro"><img src="<%=request.getContextPath()%>/pages/img/ico/cross.png"/></a>
					</display:column>
				</display:table>
			</div>
		</div>

		<div class="grid_4">
			<div id="col_derecha_app">
				<div class="seccion">
					<div class="seccion_cabecera">
						<h3>Filtro</h3>
					</div>
					<div class="seccion_contenido">
						<ul>
							<li>
								<html:form action="/MaestroAutorAction.do?method=consulta" styleClass="form">
									<fieldset>
										<div class="grid-12-12">
											<label>nombre</label>
											<html:text styleId="filtro_nombre" property="nombre" maxlength="50"/>
										</div>
										<div class="grid-12-12">
											<label>apellidos</label>
											<html:text styleId="filtro_apellidos" property="apellidos" maxlength="100"/>
										</div>
										<div class="grid-12-12">
											<label>fechaNacimiento</label>
											<html:text styleId="filtro_fechaNacimiento" property="fechaNacimiento" readonly="true" styleClass="date_picker"/>
										</div>
										<div class="grid-12-12">
											<label>telefono</label>
											<html:text styleId="filtro_telefono" property="telefono" maxlength="30"/>
										</div>
										<div class="grid-12-12">
											<label>mujer</label>
											<html:checkbox styleId="filtro_mujer" property="mujer"/>
										</div>
										<div class="grid-12-12">
											<label>hombre</label>
											<html:checkbox styleId="filtro_hombre" property="hombre"/>
										</div>
									</fieldset>
									<p class="botonera">
										<button type="submit" class="sexybutton"><span><span><span class="search">Buscar</span></span></span></button>
									</p>
								</html:form>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="datos_Autor" class="dialog">
	<h3>Datos</h3>
	<html:form action="/MaestroAutorAction.do?method=guardar" method="post" styleClass="form">
<html:hidden property="idAutor" styleId="idAutor"/>
	<fieldset>
		<div class="grid-12-12">
			<div class="grid-3-12">
				<label>nombre</label>
				<html:text property="nombre" styleId="nombre" maxlength="50"/>
			</div>
			<div class="grid-3-12">
				<label>apellidos</label>
				<html:text property="apellidos" styleId="apellidos" maxlength="100"/>
			</div>
			<div class="grid-3-12">
				<label>fechaNacimiento</label>
				<html:text property="fechaNacimiento" styleId="fechaNacimiento" readonly="true" styleClass="date_picker"/>
			</div>
			<div class="grid-3-12">
				<label>telefono</label>
				<html:text property="telefono" styleId="telefono" maxlength="30"/>
			</div>
			<div class="grid-2-12">
				<label>mujer</label>
				<html:checkbox property="mujer" styleId="mujer"/>
			</div>
			<div class="grid-2-12">
				<label>hombre</label>
				<html:checkbox property="hombre" styleId="hombre"/>
			</div>
		</div>
	</fieldset>
	<p class="botonera"><button class="sexybutton" type="submit"><span><span><span class="save">Guardar</span></span></span></button>ó <a href="#" class="cierra">Cancelar</a>
	</p>
	</html:form>
</div>

<script type="text/javascript">
$j(function(){
	$j('a.eliminar_registro').creaConfirm();

	var dialog = $j('div#datos_Autor').dialog({
		dialogClass: 'noTitle',
		autoOpen: false,
		resizable: false,
		modal: true,
		width: 700,
		open: function() {
			redimensionaFrame();
			$j('button.spinner, a.spinner').removeSpinner();
		},
		close: function(){
			$j.validationEngine.closePrompt('.formError',true);
		}
	});

	$j('#nuevo_Autor').click(function(e){
		e.preventDefault();
		$j(':text,:hidden',dialog).val('');
		$j(':checkbox',dialog).attr('checked',false);
		dialog.dialog('open');
	});

	$j('.editar_Autor').click(function(e){
		e.preventDefault();
		MaestrosController.getAutor($j(this).attr('id'), gotAutor);
	});

	function gotAutor(rVal) {
		$j(':hidden#idAutor',dialog).val(rVal.idAutor);
		$j(':text#nombre',dialog).val(rVal.nombre);
		$j(':text#apellidos',dialog).val(rVal.apellidos);
		$j(':text#fechaNacimiento',dialog).val(rVal.fechaNacimientoFormato);
		$j(':text#telefono',dialog).val(rVal.telefono);
		$j(':checkbox#mujer',dialog).attr('checked',rVal.mujer);
		$j(':checkbox#hombre',dialog).attr('checked',rVal.hombre);
		dialog.dialog('open');
	}

});
</script>
