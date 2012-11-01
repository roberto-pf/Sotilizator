var $j = jQuery.noConflict();

$j(function() {
	var loc = document.location.pathname;
	var contextPath = '/' + (loc.split("/")[1]);

	// Scroll al principio del iframe al cargar una p�gina...
	if(parent.document.getElementById('seccion_sup') != null) {
		parent.document.getElementById('seccion_sup').scrollIntoView(true);
	}

	var mensajeOk = $j('.mensaje_ok');
	if (mensajeOk != null) {
		// mensajeOk.hide().show().wait(2000).fadeOut(4000);
	}

	if ($j('.seccion').length > 0) {
		$j('.seccion').each(function() {
			var seccion = $j(this);
			var cabecera = $j('div.seccion_cabecera', seccion);
			var opciones = $j('<ul class="op_seccion"></ul>');
			var op_minimizar = $j('<li class="op_minimizar">minimizar</li>');
			cabecera.append(opciones.fadeTo('', 0.2).append(op_minimizar));
		});
	}

	$j('div.seccion_cabecera').live('mouseover mouseout', function(event) {
		var opciones = $j(this).find('ul.op_seccion');
		if (event.type == 'mouseover') {
			opciones.stop().fadeTo('medium', 1);
		} else {
			opciones.stop().fadeTo('medium', 0.2);
		}
	});
	$j('.op_minimizar', 'div.seccion_cabecera').live(
			'click',
			function() {
				var content = $j(this).parents('.seccion_cabecera').next(
						'.seccion_contenido');
				if (content.is(':visible')) {
					var op = $j(this);
					$j(this).parents('.seccion_cabecera').next(
							'.seccion_contenido').slideUp(300, function() {
						op.addClass('maximizado');
					});
				} else {
					var op = $j(this);
					$j(this).parents('.seccion_cabecera').next(
							'.seccion_contenido').slideDown(300, function() {
						op.removeClass('maximizado');
					});
				}
			});

	$j("#col_derecha_app").sortable( {
		axis : 'y',
		handle : '.seccion_cabecera',
		opacity : 0.8,
		placeholder : 'seccion_sortable',
		forcePlaceholderSize : true,
		update : function() {
			guardaOrden($j(this));
		}

	});

	function getNombreCookie() {
		return document.location.pathname.replace('/', '')
				+ document.location.search.split('&')[0].replace('?', '');
	}

	function guardaOrden(sortable) {
		var nombreCookie = getNombreCookie();
		$j.cookie(nombreCookie, sortable.sortable("toArray"), {
			expires : 7,
			path : "/"
		});
	}

	function restauraOrdenSecciones() {
		var list = $j("#col_derecha_app");
		if (list == null)
			return;

		var cookie = $j.cookie(getNombreCookie());
		if (!cookie)
			return;
		var IDs = cookie.split(",");

		var items = list.sortable("toArray");
		var rebuild = new Array();

		for ( var v = 0, len = items.length; v < len; v++) {
			rebuild[items[v]] = items[v];
		}

		for ( var i = 0, n = IDs.length; i < n; i++) {
			var itemID = IDs[i];
			if (itemID in rebuild) {
				var item = rebuild[itemID];
				var child = list.children("#" + item);
				var savedOrd = list.children("#" + itemID);
				child.remove();
				list.filter(":first").append(savedOrd);
			}
		}

	}

	restauraOrdenSecciones();

	$j("form.validar").validationEngine( {
		scroll : false,
		inlineValidation : false
	});

	$j('input.date_picker').datepicker( {
		dateFormat : 'dd-mm-yy',
		showAnim : 'slideDown'
	});

	$j('input.time_picker').timepicker( {
		showTime : false,
		dateFormat : 'dd-mm-yy',
		timeFormat : 'hh:mm',
		showAnim : 'slideDown',
		timeOnlyTitle : 'Seleccione una hora',
		timeText : 'Hora',
		hourText : 'Hora',
		minuteText : 'Minutos',
		secondText : 'Segundos',
		currentText : 'Ahora',
		hourGrid : 4,
		stepHour : 1,
		minuteGrid : 5,
		stepMinute : 5,
		showButtonPanel : false

	});

	$j('input.date_time_picker').datetimepicker( {
		showTime : false,
		dateFormat : 'dd-mm-yy',
		timeFormat : 'hh:mm',
		showAnim : 'slideDown',
		timeOnlyTitle : 'Seleccione una hora',
		timeText : 'Hora',
		hourText : 'Hora',
		minuteText : 'Minutos',
		secondText : 'Segundos',
		currentText : 'Ahora',
		hourGrid : 4,
		stepHour : 1,
		minuteGrid : 5,
		stepMinute : 5,
		showButtonPanel : false

	});

	var filtrosTablas = $j('div.filtro_tabla');

	filtrosTablas
			.each(function() {
				var obj = $j(this);
				var link = $j('<a class="a_filtro_tabla" href="#"><span>Mostrar filtro</span></a>');
				var contenedorLink = $j('<div></div>').insertBefore(obj)
						.append(link);

				obj.hide();

				link.toggle(function(e) {
					e.preventDefault();
					obj.next('table').find('thead').addClass('filtro_abierto');
					obj.slideDown('slow');
					$j(this).find('span').text('Ocultar filtro').addClass(
							'active');
				}, function(e) {
					e.preventDefault();
					obj.slideUp('slow', function() {
						obj.next('table').find('thead').removeClass(
								'filtro_abierto');
					});
					$j(this).find('span').text('Mostrar filtro').removeClass(
							'active');
				});
			});

	/*
	 * var opacity = 0, toOpacity = 1, duration = 1000; $j('table.pijama tbody
	 * td a').css('opacity',opacity); $j('table.pijama tbody
	 * tr').hover(function() { $j(this).addClass('hover').find('td
	 * a').stop().animate({opacity:toOpacity},duration); }, function() {
	 * $j(this).removeClass('hover').find('td
	 * a').stop().animate({opacity:opacity},0); } );
	 */

	$j(".tip").tipTip( {
		defaultPosition : 'top'
	});

	$j('.tip_options').qtip( {
		content : {
			text : function() {
				return $j('div' + $j(this).attr('rel'));
			}
		},
		show : {
			solo : true
		},
		hide : {
			event : 'mouseleave',
			fixed : true

		},
		position : {
			my : 'right center',
			at : 'left center',
			adjust : {
				screen : true,
				x : -5
			}
		},
		style : {
			width:{
				min: 300,
				max: 300
		},
			classes : 'ui-tooltip-options',
			tip : {
				width : 10,
				height : 20

			}

		},
		events: {
			show: function() { 
					$j(this).css('width','300px'); 
					}
    			}
		
						
	});

	$j("ul.sf-menu").superfish();

	// Link para cerrar dialogs
	$j('a.cierra', 'div.dialog').live('click', function(e) {
		e.preventDefault();
		$j(this).parents('div.dialog').dialog('close');
	});

	$j('a.spinner, button.spinner').addSpinner();

	$j('button.crea_confirm, a.crea_confirm').creaConfirm();

});

$j.fn.wait = function(time, type) {
	time = time || 1000;
	type = type || "fx";
	return this.queue(type, function() {
		var self = this;
		setTimeout(function() {
			$j(self).dequeue();
		}, time);
	});
};

$j.fn.addSpinner = function(options) {
	return this.each(function() {
		if ($j(this).hasClass('sexybutton')) {
			$j(this).click(
					function() {
						var span = $j(this).find('span span span');
						if (span.length > 0) {
							var claseOriginal = span.attr('class');
							span.data('clase_original', claseOriginal)
									.removeClass(claseOriginal).addClass(
											'spinner');
						}
					});
		}
	});
};

$j.fn.removeSpinner = function(options) {
	return this.each(function() {
		if ($j(this).hasClass('spinner')) {
			var span = $j(this).find('span span span.spinner');
			if (span.length > 0) {
				var claseOriginal = span.data('clase_original');
				span.addClass(claseOriginal).removeClass('spinner');
			}
		}
	});
};

$j(window).load(function() {
	redimensionaFrame();
});

function redimensionaFrame(dialog) {
	
	var altura = $j('div#container').height();
	
	//Hay dialog abierto
	if(dialog!=null) {
		var alturaDialog = dialog.innerHeight();
		if(alturaDialog > altura) {
			altura = (alturaDialog + 100);
		}
	}
	
	
	if (parseInt(altura, 10) < 300) {
		altura = 500;
	}
	$j('iframe:visible', parent.document).height(altura);
}

/*$j.fn.log = function(msg) {
	console.log("%s: %o", msg, this);
	return this;
};*/

$j.fn.creaConfirm = function(options) {

	var defaults = {
		mensaje : 'Estás a punto de realizar una operación que no se podrá deshacer ¿Deseas continuar?'
	};

	var options = $j.extend(defaults, options);

	return this.each(function() {
		var obj = $j(this);
		var tipoObj = obj[0].tagName;
		obj.click(function(e) {
			var avisado = obj.data('avisado');
			if (avisado == undefined || avisado == null || avisado == false) {
				if ($j("#confirm").length == 0) {
					e.preventDefault();
					var mensaje = '<ul class="mensaje mensaje_adver"><li class="left">' + options.mensaje + '</li></ul><p class="botonera right"></p>';
					$j("body").append('<div id="confirm">' + mensaje + '</div>');
					
					var botonConfirmar = $j(
						'<button type="button" class="sexybutton"><span><span><span class="accept">Aceptar y continuar</span></span></span></button>')
							.bind({click : function() {
								$j("#confirm").dialog('close');
								obj.data('avisado',	true);
								if (tipoObj == 'BUTTON'	|| tipoObj == 'INPUT') {
									obj.trigger('click');
								} else if (tipoObj == 'A') {
									document.location = obj.attr('href');
								}
							}
						});
					var botonCancelar = $j(
							'<button type="button" class="sexybutton"><span><span><span class="cancel">Cancelar</span></span></span></button>')
							.bind({click : function() {
								$j("#confirm").dialog('close');
								obj.data('avisado',false);
							}
						});
					
					$j("#confirm").dialog({
						modal : true,
						dialogClass : 'noTitle',
						resizable : false,
						width : 500,
						open : function(event,ui) {
							$j(".ui-dialog-titlebar-close").hide();
							$j(".ui-dialog-content").css("text-align","center");
							$j('.ui-dialog p.botonera').append(botonConfirmar).append(botonCancelar);									;
						},
						close : function() {
							$j(this).remove();
						}
					});
				} else {
					$j("#confirm").html(prompt);
					$j("#confirm").dialog('open');
				}
			}
		});
	});
};

$j.fn.muestraOculta = function(options) {

	var defaults = {
		textoLinkMostrar : 'Mostrar',
		textoLinkOcultar : 'Ocultar'
	};

	var options = $j.extend(defaults, options);

	return this.each(function() {
				var panel = $j(this);

				var isHidden = panel.is(':hidden');
				var link = $j('<a href="#" class="a_muestra_oculta">' + (isHidden ? options.textoLinkMostrar
						: options.textoLinkOcultar) + '</a>');
				var linkWrapper = $j('<div class="muestra_oculta_wrapper"></div>');
				linkWrapper.append(link);

				panel.before(linkWrapper);

				link.click(function(e) {
					e.preventDefault();
					if (panel.is(':hidden')) {
						panel.slideDown('medium', function(){redimensionaFrame();});
						$j(this).text(options.textoLinkOcultar);
					} else {
						panel.slideUp('medium', function(){redimensionaFrame();});
						$j(this).text(options.textoLinkMostrar);
					}
				});
			});
};
