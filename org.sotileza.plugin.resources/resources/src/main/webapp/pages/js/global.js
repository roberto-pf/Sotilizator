var $j = jQuery.noConflict();

$j(function(){
	var loc = document.location.pathname;
	var contextPath = '/' + (loc.split("/")[1]);
	
	$j(window).bind('beforeunload', function(){
		// Si es un iframe con un nombre asignado, no mostraremos el mensaje...
		if(window.name.indexOf('app_')!=0) {
			return 'Si refrescas, es posible que pierdas el estado de las aplicaciones abiertas.';
		}
	});
	
	$j('ul#menu_aplicaciones').slideUp();
	
	$j('a#a_menu_app').click(function (e) {
		$j('ul#menu_aplicaciones').slideToggle('fast').hover(function(){$j(this).show();},function(){$j(this).slideUp('fast');});
		e.preventDefault();
	});
	
	$j('#menu_aplicaciones li a').each(function(){
		
		var padding = $j(this).css("paddingLeft");
		
		$j(this).hover(
				function(){$j(this).animate({ paddingLeft: "2em" }, 50 );},
				function(){$j(this).animate({ paddingLeft: padding }, 50 );})
			.bind('click',function(e){
				var id = $j(this).attr('id');
				e.preventDefault();
				appClick(id);
				
		});
		
	});
		
	
	appClick = function (id,idTarea, url) {
		var a = $j('#menu_aplicaciones li a#' + id);
		var href;
		if(url!=null)
			href = url;
		else
			href = a.attr('href');
		
		var nombreApp = a.text();
		
		if(idTarea!=null) {
			href += '&idTarea=' + idTarea;
		}
		if(!$j('ul#main_nav li#' + id).length){
			var tab = $j('<li id="' + id + '" class="last"><a href="#">' + a.attr('rel') + '</a></li>').hide();
			var botonCerrar = '<span><a data-nombre_app="' + nombreApp + '" class="cerrar_tab" href="#"><img style="vertical-align:bottom;" src="' + contextPath + '/pages/img/ico/bullet_cross.png"/></a></span>';
			tab.append(botonCerrar);
			$j('ul#main_nav li.last').removeClass('last').after(tab);
			tab.show("slide", { direction: "down" }, 500, function(){
					var panel_app = $j('<div id="panel_' + $j(this).attr('id') + '" class="panel_app">' +
							'<iframe name="' + id + '" src ="' + href + '" frameborder="no" width="100%" height="100%" scrolling="no"></iframe>' +
					'</div>').hide();
					$j('div#apps_panel').append(panel_app);
					$j('a:first',tab).click();
					
					panel_app.find('iframe').height(1000);
				});
		} else{
			!$j('ul#main_nav li#' + id + ' a:first').click();
		}
	};
	
	$j('ul#main_nav li>a').live('click',function(e){
	    e.preventDefault();
	    var tab = $j(this).parent('li');
	    if(!tab.hasClass('active')) {
			$j('ul#main_nav li.active').removeClass('active');
			tab.addClass('active');
			$j('div#apps_panel .panel_app:visible').fadeOut(250, function(){$j('div#apps_panel div#panel_' + tab.attr('id')).fadeIn(250);});
	    }
	});
	
	
$j('ul#main_nav li a.cerrar_tab').live('click',function(e){
	e.preventDefault();
	var tab = $j(this);
	var app = $j(this).parents('li').attr('id');
	var panel = $j('div#apps_panel div#panel_' + app);
	$j('<div id="dialog-confirm" title="Cerrar aplicación"><ul class="mensaje mensaje_adver"><li>Estás a punto de cerrar esta aplicación, ¿deseas continuar?</li></ul><p class="botonera"></p></div>').dialog({
		resizable: false,
		modal: true,
		width: 500,
		open: function(){
				var dialog = $j(this);
				var botonConfirmar = $j('<button type="button" class="sexybutton"><span><span><span class="accept">Aceptar y continuar</span></span></span></button>').bind({
					click: function(){
							if(tab.data('nombre_app').toLowerCase().indexOf('agenda')!=-1) {
								//Es la agenda asi que llamamos lanzamos la url de cerrar sesion en la agenda
								panel.find('iframe').attr('src', cerrarSesionAgendaURL);
							}
							tab.parents('li').hide("slide", { direction: "down" }, 500, function(){
								$j(this).remove();
								panel.fadeOut(250, function(){
													$j(this).remove();
													$j('ul#main_nav li:last').addClass('last');
													$j('ul#main_nav li.active').removeClass('active');
													$j('ul#main_nav li:first').addClass('active');
													$j('div#apps_panel div#panel_app_0').fadeIn(250);
												});
								
							});
							dialog.dialog('close');
	        			}
		        });
		        var botonCancelar = $j('<button type="button" class="sexybutton"><span><span><span class="cancel">Cancelar</span></span></span></button>').bind({
					click: function(){  dialog.dialog('close'); }
		        });
		        $j(this).find('p.botonera').append(botonConfirmar).append(botonCancelar);
		
			}});
		
	});
	
	$j(".tip").tipTip({
		defaultPosition: 'top'
	});
	
	var dialog = $j('div#datos_usuario_bahia').dialog({
		dialogClass: 'noTitle',
		autoOpen: false,
		resizable: false,
		modal: true,
		width: 600
	});
	
	$j('#muestra_info','div#seccion_sup').click(function(){
		
		UsuariosController.getUsuarioBahia(gotUsuarioBahia);
		
	});
	
	 $j('a.cierra','div.dialog').live('click',function(e){
			e.preventDefault();
			$j(this).parents('div.dialog').dialog('close');
		});
	 
	 function gotUsuarioBahia(rVal) {
		 
		 $j('#bahia_nombre_completo',dialog).text(rVal.nombreApellidos);
		 $j('#bahia_nif',dialog).text(rVal.nif);
		 $j('#bahia_consejeria',dialog).text(rVal.des_consej);
		 $j('#bahia_servicio',dialog).text(rVal.des_organi);
		 
		 dialog.dialog('open');
		 
	 }
	 
	
});
