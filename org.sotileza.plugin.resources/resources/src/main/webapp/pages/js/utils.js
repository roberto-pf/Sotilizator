function validaImportes(importe) {
	if(!/^[0-9\ \.]+(?:\,[0-9\ ]*)?$/.test(importe)) {
		return false;
	} else {
		var numeroFechaCaptura = convierteNumero(importe);

		if(numeroFechaCaptura >= 100000000) {
			return false;
		}
	}
	return true;
}

function validaEnteros(numero) {
	return /^[0-9\ \.]*$/.test(numero); 
}

function convierteNumero(numero) {
	while(numero.lastIndexOf('.') != -1) {
		numero = numero.replace('.', '');
	}
	numero = numero.replace(',', '.');

	return parseFloat(numero);
}

function convierteDouble(numero) {
	while(numero.lastIndexOf('.') != -1) {
		numero = numero.replace('.', ',');
	}
	return numero;
}

function convierteEntero(numero) {
	while(numero.lastIndexOf('.') != -1) {
		numero = numero.replace('.', '');
	}

	return parseInt(numero, 10);
}

function comparaFechas(fecha, fecha2) //true si fecha es menor o igual que fecha2
{ 
  var xMonth=fecha.substring(3, 5); 
  var xDay=fecha.substring(0, 2); 
  var xYear=fecha.substring(6,10); 
  var yMonth=fecha2.substring(3, 5); 
  var yDay=fecha2.substring(0, 2); 
  var yYear=fecha2.substring(6,10); 

  if (xYear < yYear) 
      return(true);

  if (xYear == yYear)
  { 
      if (xMonth < yMonth) 
          return(true);
      if (xMonth == yMonth && xDay <= yDay)
            return(true); 
  }
      
  return (false);
}



