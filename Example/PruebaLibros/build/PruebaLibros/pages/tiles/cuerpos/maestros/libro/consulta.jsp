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
				<h1>Libro</h1>
				<jsp:include page="../../../appMensajes.jsp" flush="true"/>
				<p class="botonera right">
					<a id="nuevo_Libro" href="#" class="sexybutton sexymedium spinner"><span><span><span class="add">Nuevo registro</span></span></span></a>
				</p>
				<display:table name="<%= RequestKeys.MAESTRO_LISTA_LIBRO %>" id="row" pagesize="20"
						export="false" class="pijama" requestURI="MaestroLibroAction.do?method=inicio">
					<display:column title="idLibro" property="idLibro" sortable="true"/>
					<display:column title="isbn" property="isbn" sortable="true"/>
					<display:column title="titulo" property="titulo" sortable="true"/>
					<display:column title="descripcion" property="descripcion" sortable="true"/>
					<display:column title="resumen" property="resumen" sortable="true"/>
					<display:column title="importeRecaudado" property="importeRecaudadoFormato" sortable="true"/>
					<display:column class="right">
						<a id="<bean:write property="idLibro" name="row"/>" href="#" class="tip editar_Libro" title="Modificar Libro"><img src="<%=request.getContextPath()%>/pages/img/ico/edit.png"/></a>
						<a href="MaestroLibroAction.do?method=borrar&<%=RequestKeys.MAESTRO_ID_LIBRO%>=<bean:write property="idLibro" name="row"/>" class="tip eliminar_registro" title="Eliminar Registro"><img src="<%=request.getContextPath()%>/pages/img/ico/cross.png"/></a>
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
								<html:form action="/MaestroLibroAction.do?method=consulta" styleClass="form">
									<fieldset>
										<div class="grid-12-12">
											<label>isbn</label>
											<html:text styleId="filtro_isbn" property="isbn" maxlength="30"/>
										</div>
										<div class="grid-12-12">
											<label>titulo</label>
											<html:text styleId="filtro_titulo" property="titulo" maxlength="100"/>
										</div>
										<div class="grid-12-12">
											<label>descripcion</label>
											<html:text styleId="filtro_descripcion" property="descripcion" maxlength="255"/>
										</div>
										<div class="grid-12-12">
											<label>resumen</label>
											<html:text styleId="filtro_resumen" property="resumen" maxlength="4000"/>
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
<div id="datos_Libro" class="dialog">
	<h3>Datos</h3>
	<html:form action="/MaestroLibroAction.do?method=guardar" method="post" styleClass="form">
<html:hidden property="idLibro" styleId="idLibro"/>
	<fieldset>
		<div class="grid-12-12">
			<div class="grid-3-12">
				<label>isbn</label>
				<html:text property="isbn" styleId="isbn" maxlength="30"/>
			</div>
			<div class="grid-3-12">
				<label>titulo</label>
				<html:text property="titulo" styleId="titulo" maxlength="100"/>
			</div>
			<div class="grid-3-12">
				<label>descripcion</label>
				<html:text property="descripcion" styleId="descripcion" maxlength="255"/>
			</div>
			<div class="grid-12-12">
				<label>resumen</label>
				<html:textarea property="resumen" styleId="resumen"></html:textarea>
			</div>
			<div class="grid-3-12">
				<label>importeRecaudado</label>
				<html:text property="importeRecaudado" styleId="importeRecaudado" styleClass="right"/>
			</div>
			<div class="grid-3-12">
				<label>autor</label>
				<html:select property="autor.idAutor" styleId="autor"><html:options collection='<%=RequestKeys.MAESTRO_LISTA_AUTOR%>' property='idAutor' labelProperty='selectValue'/></html:select>
			</div>
			<div class="grid-3-12">
				<label>genero</label>
				<html:select property="genero.idGenero" styleId="genero"><html:options collection='<%=RequestKeys.MAESTRO_LISTA_GENERO%>' property='idGenero' labelProperty='selectValue'/></html:select>
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

	var dialog = $j('div#datos_Libro').dialog({
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

	$j('#nuevo_Libro').click(function(e){
		e.preventDefault();
		$j(':text,:hidden',dialog).val('');
		$j(':checkbox',dialog).attr('checked',false);
		dialog.dialog('open');
	});

	$j('.editar_Libro').click(function(e){
		e.preventDefault();
		MaestrosController.getLibro($j(this).attr('id'), gotLibro);
	});

	function gotLibro(rVal) {
		$j(':hidden#idLibro',dialog).val(rVal.idLibro);
		$j(':text#isbn',dialog).val(rVal.isbn);
		$j(':text#titulo',dialog).val(rVal.titulo);
		$j(':text#descripcion',dialog).val(rVal.descripcion);
		$j('textArea#resumen',dialog).val(rVal.resumen);
		$j(':text#importeRecaudado',dialog).val(rVal.importeRecaudadoFormato);
		$j('select#autor',dialog).val(rVal.autor.idAutor);
		$j('select#genero',dialog).val(rVal.genero.idGenero);
		dialog.dialog('open');
	}

});
</script>
