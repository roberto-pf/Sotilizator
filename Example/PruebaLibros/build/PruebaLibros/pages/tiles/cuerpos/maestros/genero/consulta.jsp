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
				<h1>Genero</h1>
				<jsp:include page="../../../appMensajes.jsp" flush="true"/>
				<p class="botonera right">
					<a id="nuevo_Genero" href="#" class="sexybutton sexymedium spinner"><span><span><span class="add">Nuevo registro</span></span></span></a>
				</p>
				<display:table name="<%= RequestKeys.MAESTRO_LISTA_GENERO %>" id="row" pagesize="20"
						export="false" class="pijama" requestURI="MaestroGeneroAction.do?method=inicio">
					<display:column title="idGenero" property="idGenero" sortable="true"/>
					<display:column title="nombre" property="nombre" sortable="true"/>
					<display:column class="right">
						<a id="<bean:write property="idGenero" name="row"/>" href="#" class="tip editar_Genero" title="Modificar Genero"><img src="<%=request.getContextPath()%>/pages/img/ico/edit.png"/></a>
						<a href="MaestroGeneroAction.do?method=borrar&<%=RequestKeys.MAESTRO_ID_GENERO%>=<bean:write property="idGenero" name="row"/>" class="tip eliminar_registro" title="Eliminar Registro"><img src="<%=request.getContextPath()%>/pages/img/ico/cross.png"/></a>
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
								<html:form action="/MaestroGeneroAction.do?method=consulta" styleClass="form">
									<fieldset>
										<div class="grid-12-12">
											<label>nombre</label>
											<html:text styleId="filtro_nombre" property="nombre" maxlength="30"/>
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
<div id="datos_Genero" class="dialog">
	<h3>Datos</h3>
	<html:form action="/MaestroGeneroAction.do?method=guardar" method="post" styleClass="form">
<html:hidden property="idGenero" styleId="idGenero"/>
	<fieldset>
		<div class="grid-12-12">
			<div class="grid-3-12">
				<label>nombre</label>
				<html:text property="nombre" styleId="nombre" maxlength="30"/>
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

	var dialog = $j('div#datos_Genero').dialog({
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

	$j('#nuevo_Genero').click(function(e){
		e.preventDefault();
		$j(':text,:hidden',dialog).val('');
		$j(':checkbox',dialog).attr('checked',false);
		dialog.dialog('open');
	});

	$j('.editar_Genero').click(function(e){
		e.preventDefault();
		MaestrosController.getGenero($j(this).attr('id'), gotGenero);
	});

	function gotGenero(rVal) {
		$j(':hidden#idGenero',dialog).val(rVal.idGenero);
		$j(':text#nombre',dialog).val(rVal.nombre);
		dialog.dialog('open');
	}

});
</script>
