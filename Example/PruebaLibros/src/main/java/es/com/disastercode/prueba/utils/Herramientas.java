package es.com.disastercode.prueba.utils;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.SimpleHtmlSerializer;
import org.htmlcleaner.TagNode;



/**
 * Utilidades para emplear en la aplicación.
 *
 */
public class Herramientas {

	private static Log log = LogFactory.getFactory().getInstance(Herramientas.class);


	/**
	  * Devuelve un NIF completo a partir de un DNI. Es decir, añade la letra del NIF
	  * @param dni dni al que se quiere añadir la letra del NIF
	  * @return NIF completo.
	  */
	public static String nifFromDni(String dni) {
		final String NIF_STRING_ASOCIATION = "TRWAGMYFPDXBNJZSQVHLCKE";
		
		try{
			int numeroDni=Integer.valueOf(dni);
			return (dni + NIF_STRING_ASOCIATION.charAt(numeroDni % 23));
		}catch(Exception e){
			//Si se produce algún error es probable que sea
			//porque ese DNI ya tiene la letra puesta.
		}
		
		return dni;
	}



	/**
	  * Calcula si un año es bisiesto
	  * @param agno año
	  * @return Devuelve verdadero si un año es bisisesto y falso si no lo es
	  */
	public static boolean bisiesto(int agno){
		Boolean bisiesto=false;
		if (agno%4==0 )
			bisiesto=true;
		if (agno%100==0)
			bisiesto=false;
		if (agno%400==0)
			bisiesto=true;
		
		return bisiesto;
	}


	/**
	  * Calculo de los dias de un mes
	  * @param mes mes
	  * @param year año
	  * @return Devuelve el numero de dias del mes pasado como parametro
	  */
	public static Integer diasMes(int mes, int year){
		int dias=0;
		if (mes==0 || mes==2 ||mes==4 ||mes==6 ||mes==7 || mes==9 ||mes==11){
			dias=31;
		}
		if (mes==3 || mes==5 ||mes==8 ||mes==10){
			dias=30;
		}
		if (mes==1){
			if (bisiesto(year))
				dias=29;
			else
				dias=28;
		}
		return dias;
	}


	/**
	 * Función que devuelve un Double a partir de un String. De producirse algún error
	 * en la conversión devolvera null.
	 * @param valor
	 * @return
	 */
	public static Double pasaStringADouble(String valor){
		Double resultado;
		try{
			resultado = new Double(valor.replace(',', '.'));
		}catch(Exception e){
			resultado = null;
		}
		return resultado;
	}



	/**
	 * Función que pasa una fecha pasada como formato dd-MM-yyyy a un entero con la
	 * forma yyyyMMdd si se produce algún error en la conversión devolvera null.
	 * @param valor
	 * @return
	 */
	public static Integer pasaFechaAInteger(String valor){
		Integer resultado;
		try{
			String[] aux = valor.split("-");
			String aux2 = aux[2] + aux[1] + aux[0];	
			resultado = new Integer(aux2);
		}catch(Exception e){
			resultado = null;
		}
		return resultado;
	}

	public static double redondearHaciaArriba(double d, int decimales) {
		//double factor = Math.pow(10, decimales);
		//return Math.ceil(d * factor) / factor;
		return redondear(d, decimales);
	}
	public static double redondear(double d, int decimales) {
		double factor = Math.pow(10, decimales);
		return Math.rint(d * factor) / factor;
	}




	/**
	 * Método que devuelve el número total de días que hay entre dos fechas
	 * @param a - Date con la fecha inicial
	 * @param b - Date con la fecha final
	 * @return int - entero con el número total de días.
	 */
	public static int calculateDifference(Date a, Date b)
	{
	    int tempDifference = 0;
	    int difference = 0;
	    Calendar earlier = Calendar.getInstance();
	    Calendar later = Calendar.getInstance();
	 
	    if (a.compareTo(b) < 0)
	    {
	        earlier.setTime(a);
	        later.setTime(b);
	    }
	    else
	    {
	        earlier.setTime(b);
	        later.setTime(a);
	    }
	 
	    while (earlier.get(Calendar.YEAR) != later.get(Calendar.YEAR))
	    {
	        tempDifference = 365 * (later.get(Calendar.YEAR) - earlier.get(Calendar.YEAR));
	        difference += tempDifference;
	 
	        earlier.add(Calendar.DAY_OF_YEAR, tempDifference);
	    }
	 
	    if (earlier.get(Calendar.DAY_OF_YEAR) != later.get(Calendar.DAY_OF_YEAR))
	    {
	        tempDifference = later.get(Calendar.DAY_OF_YEAR) - earlier.get(Calendar.DAY_OF_YEAR);
	        difference += tempDifference;
	 
	        earlier.add(Calendar.DAY_OF_YEAR, tempDifference);
	    }
	 
	    return difference + 1;
	}



		
	public static void creaBreadcrumb(HttpServletRequest request, String titulo, boolean esPadre) {
		String url = request.getRequestURL().toString() + "?" + request.getQueryString();
		Breadcrumb miga = new Breadcrumb(titulo, url);
		TreeSet<Breadcrumb> migas = (TreeSet<Breadcrumb>)request.getSession().getAttribute(SessionKeys.MIGAS_DE_PAN);
		if(migas==null || esPadre) {
			migas = new TreeSet<Breadcrumb>();
		} 
		migas.add(miga);
		request.getSession().setAttribute(SessionKeys.MIGAS_DE_PAN, migas);
	}


	    
    /**
     * Redondea un valor float al número de decimales (dec) pedidos.
     *
     * @param val - valor a redondear.
     * @param dec - número de decimales.
     * @return val redondeado
     */
    public static float round(float val, int dec) {
    	return (float)redondear((double)val, dec);
    } 



	   
    public static String formatear(Double numero){
    	DecimalFormat formateador = new DecimalFormat("#,##0.00");
    	String texto;
    	texto=String.valueOf(formateador.format(numero));
    	return texto;
    }
   
    
    public static String formatearSinPunto(Double numero){
    	DecimalFormat formateador = new DecimalFormat("#0.00");
    	String texto;
    	texto=String.valueOf(formateador.format(numero));
    	return texto;
    }
    


    public static String eliminaEtiquetaHTML(String texto, String etiqueta, boolean eliminaCierres) {
    	
    	int firstIndex = texto.indexOf("<"+etiqueta);
    	String textoModificado = texto;
    	
    	while(firstIndex != -1) {
	    	int lastIndex = textoModificado.indexOf('>', firstIndex);
	    	
	    	String firstText = textoModificado.substring(0, firstIndex);
	    	String lastText = textoModificado.substring(lastIndex+1, textoModificado.length());
	    	
	    	textoModificado = firstText+lastText;
	    	
	    	firstIndex = textoModificado.indexOf("<"+etiqueta);
    	}
    	
    	if(eliminaCierres){
    		textoModificado = textoModificado.replaceAll("</"+etiqueta+">", "");
    	}
    	
    	return textoModificado;
    }


	public static String eliminaComentariosHTML(String texto) {
    	int firstIndex = texto.indexOf("<!--");
    	String textoModificado = texto;
    	
    	while(firstIndex != -1) {
	    	int lastIndex = textoModificado.indexOf("-->", firstIndex);
	    	
	    	String firstText = textoModificado.substring(0, firstIndex);
	    	String lastText = textoModificado.substring(lastIndex+3, textoModificado.length());
	    	
	    	textoModificado = firstText+lastText;
	    	
	    	firstIndex = textoModificado.indexOf("<!--");
    	}
    	
    	return textoModificado;
    }

    public static String remplazarEtiquetaHTML(String texto, String etiqueta, String remplazo) {
    	
    	int firstIndex = texto.indexOf("<"+etiqueta);
    	String textoModificado = texto;
    	
    	while(firstIndex != -1) {
	    	int lastIndex = textoModificado.indexOf('>', firstIndex);
	    	
	    	String firstText = textoModificado.substring(0, firstIndex);
	    	String lastText = textoModificado.substring(lastIndex+1, textoModificado.length());
	    	
	    	textoModificado = firstText+"<"+remplazo+">"+lastText;
	    	
	    	firstIndex = textoModificado.indexOf("<"+etiqueta, firstText.length()+("<"+remplazo+">").length());
    	}
    	
   		textoModificado = textoModificado.replaceAll("</"+etiqueta+">", "</"+remplazo+">");
    	
    	return textoModificado;
    }
    

    
    public static String limpiarEtiquetaHTML(String texto, String etiqueta) {
    	
    	int firstIndex = texto.indexOf("<"+etiqueta+" ");
    	String textoModificado = texto;
    	
    	while(firstIndex != -1) {
	    	int lastIndex = textoModificado.indexOf('>', firstIndex);
	    	
	    	String firstText = textoModificado.substring(0, firstIndex);
	    	String lastText = textoModificado.substring(lastIndex+1, textoModificado.length());
	    	
	    	textoModificado = firstText+"<"+etiqueta+">"+lastText;
	    	
	    	firstIndex = textoModificado.indexOf("<"+etiqueta+" ", firstText.length()+("<"+etiqueta+">").length());
    	}
    	
    	return textoModificado;
    }



    public static String formateaTextoHTML(String texto) {
    	
    	if(texto==null)
    		return "";
    	else {
    		
    		CleanerProperties cleanerProperties = new CleanerProperties();
    		
    		cleanerProperties.setOmitComments(true);
    		cleanerProperties.setOmitDoctypeDeclaration(true);
    		cleanerProperties.setOmitUnknownTags(true);
    		cleanerProperties.setOmitXmlDeclaration(true);
    		
	    	HtmlCleaner cleaner = new HtmlCleaner(cleanerProperties);
	    	TagNode tagNode = cleaner.clean(texto);
	    	
	    	SimpleHtmlSerializer serializer = new SimpleHtmlSerializer(cleaner.getProperties());
	    	
	    	try {
				texto = serializer.getAsString(tagNode);
			} catch (IOException e) {
				texto = "";
			}
	    	
    		texto = eliminaComentariosHTML(texto);
    		
    		texto = eliminaEtiquetaHTML(texto, "ul", true);
    		texto = eliminaEtiquetaHTML(texto, "p", false);
    		texto = eliminaEtiquetaHTML(texto, "span", true);
    		texto = eliminaEtiquetaHTML(texto, "a", true);
    		texto = eliminaEtiquetaHTML(texto, "h1", true);
    		texto = eliminaEtiquetaHTML(texto, "h2", true);
    		texto = eliminaEtiquetaHTML(texto, "h3", true);
    		texto = eliminaEtiquetaHTML(texto, "h4", true);
    		texto = eliminaEtiquetaHTML(texto, "h5", true);
    		texto = eliminaEtiquetaHTML(texto, "h6", true);
    		texto = eliminaEtiquetaHTML(texto, "h7", true);
    		texto = eliminaEtiquetaHTML(texto, "style", true);
    		texto = eliminaEtiquetaHTML(texto, "meta", true);
    		texto = eliminaEtiquetaHTML(texto, "blockquote", true);
    		texto = eliminaEtiquetaHTML(texto, "div", true);
    		
    		texto = eliminaEtiquetaHTML(texto, "table", true);
    		texto = eliminaEtiquetaHTML(texto, "tbody", true);
    		texto = eliminaEtiquetaHTML(texto, "td", false);
    		
    		texto = eliminaEtiquetaHTML(texto, "?xml", false);
    		texto = eliminaEtiquetaHTML(texto, "html", true);
    		texto = eliminaEtiquetaHTML(texto, "head", true);
    		texto = eliminaEtiquetaHTML(texto, "body", true);
    		
    		texto = eliminaEtiquetaHTML(texto, "tr", true);
    		
    		texto = limpiarEtiquetaHTML(texto, "b");
    		texto = limpiarEtiquetaHTML(texto, "i");
    		texto = limpiarEtiquetaHTML(texto, "u");
    		texto = limpiarEtiquetaHTML(texto, "li");
    		
    		texto = remplazarEtiquetaHTML(texto, "tr", "li");
    		texto = remplazarEtiquetaHTML(texto, "em", "i");
    		texto = remplazarEtiquetaHTML(texto, "strong", "b");
    		    		
	    	texto = texto.replaceAll("<br>", "<br/>").
	    			replaceAll("<br />", "<br/>").
	    			replaceAll("</p>", "<br/>").
	    			replaceAll("</td>", "   ").
	    			replaceAll("\r\n", " ");
	    	
	    	return texto;
    	}
    }
    
    
    public static boolean campoVacioSinEtiquetas(String texto){
    	texto = eliminaEtiquetaHTML(texto, "p", true);
    	texto = eliminaEtiquetaHTML(texto, "br", false);
    	texto = eliminaEtiquetaHTML(texto, "blockquote", true);
    	texto = eliminaEtiquetaHTML(texto, "ul", true);
    	texto = eliminaEtiquetaHTML(texto, "li", true);

    	if(texto.trim().equals("")) return true;
    	return false;
    }


	public static Date stringToDate(String fecha) throws ParseException{
	   Date resultado = null;
	   
	   if(StringUtils.isNotBlank(fecha)){
		   SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
		   resultado = formateador.parse(fecha);
	   }
	   
	   return resultado;
   }
   
   public static String numeroToString(Double n){
	   return String.valueOf(getNumberFormat(true).format(n)); 
   }
   
   public static String numeroToString(Float n){
	   return String.valueOf(getNumberFormat(true).format(n)); 
   }
      
   public static String numeroToString(Double n, boolean decimales){
	   return String.valueOf(getNumberFormat(decimales).format(n)); 
   }
   
   public static String numeroToString(Float n, boolean decimales){
	   return String.valueOf(getNumberFormat(decimales).format(n)); 
   }


   
   private static NumberFormat getNumberFormat(boolean decimales) {
	   log.info(" ");
	   log.info("Entro al getNumberFormat - decimales:"+decimales);
	   NumberFormat numForm = NumberFormat.getInstance(Locale.GERMANY);
	   if(decimales) {
		   numForm.setMaximumFractionDigits(2);
		   numForm.setMinimumFractionDigits(2);
	   }
	   log.info("numForm:"+numForm);
	   log.info("numForm.toString():"+numForm.toString());
	   return numForm;
   }
   
   public static Number stringToNumber(String numero) throws ParseException{
	   log.info(" ");
	   log.info("Entro al stringToNumber - numero:"+numero);
	   log.info("getNumberFormat(false).parse("+numero+"):"+getNumberFormat(false).parse(numero));
	   return getNumberFormat(false).parse(numero);
   }



   private static final String[] UNIDADES = { "", "UN ", "DOS ", "TRES ",
       "CUATRO ", "CINCO ", "SEIS ", "SIETE ", "OCHO ", "NUEVE ", "DIEZ ",
       "ONCE ", "DOCE ", "TRECE ", "CATORCE ", "QUINCE ", "DIECISEIS",
       "DIECISIETE", "DIECIOCHO", "DIECINUEVE", "VEINTE" };

   private static final String[] DECENAS = { "VENTI", "TREINTA ", "CUARENTA ",
       "CINCUENTA ", "SESENTA ", "SETENTA ", "OCHENTA ", "NOVENTA ",
       "CIEN " };

   private static final String[] CENTENAS = { "CIENTO ", "DOSCIENTOS ",
       "TRESCIENTOS ", "CUATROCIENTOS ", "QUINIENTOS ", "SEISCIENTOS ",
       "SETECIENTOS ", "OCHOCIENTOS ", "NOVECIENTOS " };

   /**
    * Convierte a letras un numero de la forma $123,456.32 (StoreMath)
    * <p>
    * Creation date 5/06/2006 - 10:20:52 AM
    *
    * @param number
    *            Numero en representacion texto
    * @return Numero en letras
    * @since 1.0
    */
   public static String convertNumberToLetter(double number) {
	   Locale l = Locale.getDefault();
	   Locale.setDefault(Locale.US);
	   String s = convertNumberToLetter(doubleToString(number));
	   Locale.setDefault(l);
	   return s;
   }


   /**
    * Convertimos el numero double a String, agregando formato para que sea
    * procesado El numero de decimales esta determinado por %.2f ej. %10.2f (10
    * posiciones enteras y 2 decimales) si no se pone el primer valor por
    * default toma el valor entero completo
    **/
   private static String doubleToString(double numero) {
	   return String.format("%.2f", numero);
   }


   /**
    * Convierte un numero en representacion numerica a uno en representacion de
    * texto. El numero es valido si esta entre 0 y 999'999.999
    * <p>
    * Creation date 3/05/2006 - 05:37:47 PM
    *
    * @param number
    *            Numero a convertir
    * @return Numero convertido a texto
    * @throws NumberFormatException
    *             Si el numero esta fuera del rango
    * @since 1.0
    */
   public static String convertNumberToLetter(String number) throws NumberFormatException 
   {
	   log.info(" ");
	   log.info("Entro al convertNumberToLetter");
	   
	   log.info("number:"+number);
	   
	   String converted = new String();

	   // Validamos que sea un numero legal
	   /*
	    * double doubleNumber = Double.parseDouble(number.replace(".","")); if
	    * (doubleNumber > 999999999) throw new NumberFormatException(
	    * "El numero es mayor de 999'999.999, " + "no es posible convertirlo");
	    */

	   String splitNumber[] = number.replace('.', '#').split("#");

	   // Descompone el trio de millones - ¡SGT!
	   int millon = Integer.parseInt(String.valueOf(getDigitAt(splitNumber[0],
               8))
               + String.valueOf(getDigitAt(splitNumber[0], 7))
               + String.valueOf(getDigitAt(splitNumber[0], 6)));
	   if (millon == 1)
       converted = "UN MILLON ";
	   if (millon > 1)
       converted = convertNumber(String.valueOf(millon)) + " MILLONES ";

	   log.info("converted hasta millones:"+converted);
	   
	   // Descompone el trio de miles - ¡SGT!
	   int miles = Integer.parseInt(String.valueOf(getDigitAt(splitNumber[0],
               5))
               + String.valueOf(getDigitAt(splitNumber[0], 4))
               + String.valueOf(getDigitAt(splitNumber[0], 3)));
	   if (miles == 1)
		   converted += "MIL ";
	   if (miles > 1)
		   converted += convertNumber(String.valueOf(miles)) + " MIL ";

	   log.info("converted hasta miles:"+converted);
	   
	   // Descompone el ultimo trio de unidades - ¡SGT!
	   int cientos = Integer.parseInt(String.valueOf(getDigitAt(
               splitNumber[0], 2))
               + String.valueOf(getDigitAt(splitNumber[0], 1))
               + String.valueOf(getDigitAt(splitNumber[0], 0)));
	if (cientos == 1)
       converted += "UN ";

	if (millon + miles + cientos == 0)
       converted += "CERO ";
	if (cientos > 1)
       converted += convertNumber(String.valueOf(cientos));

	converted += "EUROS";

	log.info("converted hasta unidades:"+converted);
	
	// Descompone los centavos - Camilo
	int centavos = 0;
	if (splitNumber.length > 1) {
       centavos = Integer.parseInt(String.valueOf(getDigitAt(
                       splitNumber[1], 2))
                       + String.valueOf(getDigitAt(splitNumber[1], 1))
                       + String.valueOf(getDigitAt(splitNumber[1], 0)));
	}
	if (centavos == 1)
       converted += " CON UN CENTIMO";
	if (centavos > 1)
       converted += " CON " + convertNumber(String.valueOf(centavos))
                       + " CENTIMOS";

	log.info("converted hasta céntimos:"+converted);
	return converted;
   }


   /**
 	* Convierte los trios de numeros que componen las unidades, las decenas y
	* las centenas del numero.
	* <p>
	* Creation date 3/05/2006 - 05:33:40 PM
	*
	* @param number
	*            Numero a convetir en digitos
	* @return Numero convertido en letras
	* @since 1.0
	*/
   private static String convertNumber(String number) {
	if (number.length() > 3)
       throw new NumberFormatException("La longitud maxima debe ser 3 digitos");

	String output = new String();
	if (getDigitAt(number, 2) != 0)
       output = CENTENAS[getDigitAt(number, 2) - 1];

	int k = Integer.parseInt(String.valueOf(getDigitAt(number, 1))
               + String.valueOf(getDigitAt(number, 0)));

	if (k <= 20)
       output += UNIDADES[k];
	else {
       if (k > 30 && getDigitAt(number, 0) != 0)
               output += DECENAS[getDigitAt(number, 1) - 2] + "Y "
                               + UNIDADES[getDigitAt(number, 0)];
       else
               output += DECENAS[getDigitAt(number, 1) - 2]
                               + UNIDADES[getDigitAt(number, 0)];
	}

	// Caso especial con el 100
	if (getDigitAt(number, 2) == 1 && k == 0)
       output = "CIEN";

	return output;
	}

  	/**
  	 * Retorna el digito numerico en la posicion indicada de derecha a izquierda
  	 * <p>
  	 * Creation date 3/05/2006 - 05:26:03 PM
  	 *
  	 * @param origin
  	 *            Cadena en la cual se busca el digito
  	 * @param position
  	 *            Posicion de derecha a izquierda a retornar
  	 * @return Digito ubicado en la posicion indicada
  	 * @since 1.0
  	 */
	private static int getDigitAt(String origin, int position) {
			if (origin.length() > position && position >= 0)
					return origin.charAt(origin.length() - position - 1) - 48;
			return 0;
	}


}
