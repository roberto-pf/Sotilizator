var $j = jQuery.noConflict();

(function($j) {
	$j.fn.validationEngineLanguage = function() {};
	$j.validationEngineLanguage = {
		newLang: function() {
			$j.validationEngineLanguage.allRules = 	{"required":{    			// Add your regex rules here, you can take telephone as an example
				"regex":"none",
				"alertText":"Este campo es requerido",
				"alertTextCheckboxMultiple":"Por favor escoja una opci\xf3n",
				"alertTextCheckboxe":"Este checkbox es requerido"},
			"length":{
				"regex":"none",
				"alertText":"*Entre ",
				"alertText2":" y ",
				"alertText3": " caracteres permitidos"},
			"maxCheckbox":{
				"regex":"none",
				"alertText":"Checks permitidos excedidos"},	
			"minCheckbox":{
				"regex":"none",
				"alertText":"Por favor seleccione ",
				"alertText2":" opciones"},	
			"confirm":{
				"regex":"none",
				"alertText":"Su campo no es igual al anterior"},		
			"telephone":{
				"regex":"/^[0-9\-\(\)\ ]+$/",
				"alertText":"N\xfamero de tel\xe9fono inv\xe1lido"},	
			"email":{
				"regex":"/^[a-zA-Z0-9_\.\-]+\@([a-zA-Z0-9\-]+\.)+[a-zA-Z0-9]{2,4}$/",
				"alertText":"E-mail inv\xe1lido"},	
			"date":{
                 "regex":"/^[0-9]{4}\-\[0-9]{1,2}\-\[0-9]{1,2}$/",
                 "alertText":"Invalid date, must be in YYYY-MM-DD format"},
     		"hora":{
                 "regex":"/^[0-2]{0,1}\[0-9]{1}\:\[0-5]{1}\[0-9]{1}$/",
                 "alertText":"Hora incorrecta, debe seguir el formato hh:mm"},
     		"fechaHora":{
                 "regex":"/^[0-9]{1,2}\-\[0-9]{1,2}\-\[0-9]{4}\ \[0-2]{0,1}\[0-9]{1}\:\[0-5]{1}\[0-9]{1}$/",
                 "alertText":"Fecha incorrecta, debe seguir el formato dd-MM-yyyy hh:mm"},
      		"fecha":{
                "regex":"/^[0-9]{1,2}\-\[0-9]{1,2}\-\[0-9]{4}$/",
                "alertText":"Fecha incorrecta, debe seguir el formato dd-MM-yyyy"},
			"onlyNumber":{
				"regex":"/^[0-9\ ]+$/",
				"alertText":"S\xf3lo n\xfameros"},	
			"noSpecialCaracters":{
				"regex":"/^[0-9a-zA-Z]+$/",
				"alertText":"Caracteres especiales no permitidos"},	
			"ajaxUser":{
				"file":"validateUser.php",
				"extraData":"name=eric",
				"alertTextOk":"Este usuario est\xe1 disponible",	
				"alertTextLoad":"Cargando... Por favor espere",
				"alertText":"Este usuario ya est\xe1 en uso"},	
			"ajaxName":{
				"file":"validateUser.php",
				"alertText":"Este nombre ya est\xe1 en uso",
				"alertTextOk":"Este nombre est\xe1 disponible",	
				"alertTextLoad":"Cargando, por favor espere"},		
			"onlyLetter":{
				"regex":"/^[a-zA-Z\ \']+$/",
				"alertText":"S\xf3lo letras"},
			"validate2fields":{
				"nname":"validate2fields",
				"alertText":"Usted debe tener Nombre y Apellido"},
			"dosCamposDiferentes":{
				"nname":"dosCamposDiferentes",
				"alertText":"Los campos de clave actual y clave nueva deben ser diferentes"},
			"camposDiferentes":{
				"nname":"camposDiferentes",
				"alertText":"Los tres campos no pueden ser iguales"},
			"validarFormatoImagen":{
				"nname":"validarFormatoImagen",
				"alertText":"Los tres campos no pueden ser iguales"},
			"importes":{
				"regex":"/^[0-9\ \.]+(?:\,[0-9\ ]*)?$/",
                "alertText":"Solo se admiten importes."},
            "comparaDoubles":{
                	"nname":"comparaDoubles",
                	"alertText":"Valor demasiado alto"},
            "comparaDoublesPunto":{
                  	"nname":"comparaDoublesPunto",
                   	"alertText":"Valor demasiado alto"},
			"plazoMenores":{
            	"nname":"validaPlazoMenores",
            	"alertText":"El plazo del contrato no puede superar el a&ntilde;o."},
        	"periodoMenorUnAnio":{
            	"nname":"periodoMenorUnAnio",
            	"alertText":"El plazo del contrato no puede superar el a&ntilde;o."}
			};
		}
	};
})(jQuery);

jQuery(function() {	
	$j.validationEngineLanguage.newLang();
});