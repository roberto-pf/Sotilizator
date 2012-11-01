/** ===== LICENSE =====

Sotilizator by Roberto Pérez Fernández is licensed under a Creative Commons Attribution-Noncommercial-Share Alike 3.0.
Permissions beyond the scope of this license may be available.
The author can be contacted here: http://disastercode.com.es


License details: http://creativecommons.org/licenses/by-nc-sa/3.0/


You are free:

    to Share — to copy, distribute and transmit the work
    to Remix — to adapt the work

Under the following conditions:

    Attribution — You must attribute the work in the manner specified by the author or licensor (but not in any way that suggests 
    			that they endorse you or your use of the work).

    Noncommercial — You may not use this work for commercial purposes.

    Share Alike — If you alter, transform, or build upon this work, you may distribute the resulting work only under the same or 
    			similar license to this one.

With the understanding that:

    Waiver — Any of the above conditions can be waived if you get permission from the copyright holder.
    Public Domain — Where the work or any of its elements is in the public domain under applicable law, that status is in no way 
    			affected by the license.
    Other Rights — In no way are any of the following rights affected by the license:
        Your fair dealing or fair use rights, or other applicable copyright exceptions and limitations;
        The author's moral rights;
        Rights other persons may have either in the work itself or in how the work is used, such as publicity or privacy rights.
    Notice — For any reuse or distribution, you must make clear to others the license terms of this work. The best way to do this 
    	is with a link to this web page.

===== LICENSE ===== */
package es.sotileza.plugin.make;

import java.util.LinkedList;
import java.util.List;

import es.sotileza.plugin.utils.Constantes;
import es.sotileza.plugin.utils.Utilidades;
import es.sotileza.plugin.vo.TablaVO;

public class MakeDelegate {

	public static List<String> makeDelegate(TablaVO tab){
		List<String> lineas = new LinkedList<String>();
		
		lineas.add("package "+tab.getDelegatePakete()+";\n\r" );
		
		lineas.add( "import "+tab.getVoPakete()+".*;" );
		lineas.add( "import "+tab.getManagerPakete()+".*;" );
		lineas.add( "import java.util.List;" );
		lineas.add( "import org.apache.commons.logging.Log;" );
		lineas.add( "import org.apache.commons.logging.LogFactory;" );
		lineas.add( "\n\r" );
		
		lineas.add("/**\n * Clase Delegate - "+tab.getDelegateClase()+"\n */");
		lineas.add( "public class "+tab.getDelegateClase()+" {\n\r" );
		
		String tipoDato = tab.getVoClase();
		String datoMetodo = tab.getVoMetodo();
		String datoVariable = tab.getVoVariable();
		String datoManager = tab.getManagerVariable();
		
		lineas.add( "\tprivate static Log log = LogFactory.getFactory().getInstance("+tab.getDelegateClase()+".class);");
		lineas.add( "\tprivate "+tab.getManagerClase()+" "+datoManager+";\n");
    	lineas.add( "\tpublic "+tab.getManagerClase()+" get"+datoMetodo+Constantes.CAPA_MANAGER+"(){" );
    	lineas.add( "\t\treturn this."+datoManager+";" );
		lineas.add( "\t}" );
		lineas.add( "\tpublic void set"+datoMetodo+Constantes.CAPA_MANAGER+"( "+tab.getManagerClase()+" "+datoManager+" ){" );
    	lineas.add( "\t\tthis."+datoManager+" = "+datoManager+";" );
		lineas.add( "\t}\n\r" );	
		
		
		lineas.add(Utilidades.javadocMethod(Constantes.METODO_GET, tipoDato, datoVariable));
		lineas.add("\tpublic "+tipoDato+" get"+datoMetodo+"("+tab.getPks().get(0).getTipoEnApp()+" id){");
		lineas.add("\t\treturn this."+datoManager+".get"+datoMetodo+"(id);");
		lineas.add("\t}\n\r");
		
		lineas.add(Utilidades.javadocMethod(Constantes.METODO_NEW, tipoDato, datoVariable));
		lineas.add("\tpublic "+tab.getPks().get(0).getTipoEnApp()+" new"+datoMetodo+"("+tipoDato+" "+datoVariable+"){");
		lineas.add("\t\treturn this."+datoManager+".new"+datoMetodo+"("+datoVariable+");");
		lineas.add("\t}\n\r");

		lineas.add(Utilidades.javadocMethod(Constantes.METODO_EDIT, tipoDato, datoVariable));
		lineas.add("\tpublic void edit"+datoMetodo+"("+tipoDato+" "+datoVariable+"){");
		lineas.add("\t\tthis."+datoManager+".edit"+datoMetodo+"("+datoVariable+");");
		lineas.add("\t}\n\r");

		lineas.add(Utilidades.javadocMethod(Constantes.METODO_DELETE, tipoDato, datoVariable));
		lineas.add("\tpublic void delete"+datoMetodo+"("+tab.getPks().get(0).getTipoEnApp()+" id){");
		lineas.add("\t\tthis."+datoManager+".delete"+datoMetodo+"(id);");
		lineas.add("\t}\n\r");
		
		lineas.add(Utilidades.javadocMethod(Constantes.METODO_FIND, tipoDato, datoVariable));
		lineas.add("\tpublic List<"+tipoDato+"> find"+datoMetodo+"("+tipoDato+" "+datoVariable+"){");
		lineas.add("\t\treturn this."+datoManager+".find"+datoMetodo+"("+datoVariable+");");
		lineas.add("\t}\n\r");
		
		lineas.add( "}");
		return lineas;
	}
	
	

	public static List<String> makeConfig(TablaVO tab){
		List<String> lineas = new LinkedList<String>();
		
		String datoManager = tab.getManagerVariable();
		String datoDelegate = tab.getDelegateVariable();
		String pak = tab.getDelegatePakete()+"."+tab.getDelegateClase();
		
		lineas.add("\n\t<!-- Para "+tab.getDelegateClase()+" -->");
		lineas.add("\t<bean id=\""+datoDelegate+"\" class=\""+pak+"\">");
		lineas.add("\t\t<property name=\""+datoManager+"\"><ref bean=\""+datoManager+"\"/></property>");
		lineas.add("\t</bean>");
		
		return lineas;	  	
	}

	public static List<String> makeConfigCommons(String clase){
		List<String> lineas = new LinkedList<String>();
		String aux = clase.substring(0,1).toLowerCase()+clase.substring(1,clase.length());
		lineas.add("\n\t<!-- Para "+clase+"Delegate -->");
		lineas.add("\t<bean id=\""+aux+"Delegate\" class=\"es.satec.sotileza.commons.web.delegate."+clase+"Delegate\">");
		if(clase.equals("EscrTarea"))
			lineas.add("\t\t<property name=\"mng\"><ref bean=\""+aux+"Manager\"/></property>");
		else
			lineas.add("\t\t<property name=\""+aux+"Manager\"><ref bean=\""+aux+"Manager\"/></property>");
		lineas.add("\t</bean>");
		
		return lineas;	  	
	}

}
