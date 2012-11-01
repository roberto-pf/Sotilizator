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
package es.sotileza.plugin.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import es.sotileza.plugin.vo.ArquetipoVO;
import es.sotileza.plugin.vo.TablaVO;

public class Utilidades {

	public static String calculaTipo(String tipo, Integer tam, Integer scale){
		tipo = tipo.toUpperCase();
		if(tipo.equals("NUMBER")){
			if(scale==null || scale==0){
				if(tam>10) return "Long";
				else if(tam==1) return "Boolean";
				return "Integer";
			}else{
				return "Double";
			}	
		}else if(tipo.equals("VARCHAR2") || tipo.equals("CLOB")){
			return "String";
		}else if(tipo.equals("DATE")){
			return "Date";
		}
		return null;
	}
	

	public static String calculaTipoForm(String tipo, Integer tam, Integer scale){
		tipo = tipo.toUpperCase();
		if(tipo.equals("NUMBER")){
			if(scale==null || scale==0){
				if(tam>10) return "String";
				else if(tam==1) return "Boolean";
				return "String";
			}else{
				return "String";
			}	
		}else if(tipo.equals("VARCHAR2") || tipo.equals("CLOB")){
			return "String";
		}else if(tipo.equals("DATE")){
			return "String";
		}
		return null;
	}
	
	
	public static String calculaTipoHibernate(String tipo, Integer tam, Integer scale){
		tipo = tipo.toUpperCase();
		if(tipo.equals("NUMBER")){
			if(scale==null || scale==0){
				if(tam>10) return "long";
				else if(tam==1) return "java.lang.Boolean";
				return "int";
			}else{
				return "java.lang.Double";
			}
		}else if(tipo.equals("VARCHAR2")){
			return "string";
		}else if(tipo.equals("DATE")){
			return "date";
		}else if(tipo.equals("CLOB")){
			return "text";
		}
		return null;
	}
	
	
	
	public static String sacaPlural(String str){
		if(str.endsWith("a")||str.endsWith("e")||str.endsWith("i")||str.endsWith("o")||str.endsWith("u")){
			return str+"s";
		}
		return str+"es";
	}
	
	
	public static void paintFile(String file, List<String> lineas) throws IOException {
		FileWriter output = new FileWriter(file);
		BufferedWriter writer = new BufferedWriter(output);
		for (String i : lineas)
			writer.write(i + "\n");
		System.out.println("Generado el fichero: "+file);
		writer.flush();
	}
	
	public static void paintMaestroManager(String file, List<String> tr1, List<String> tr2, List<String> tr3, ArquetipoVO arq) throws IOException {
		FileWriter output = new FileWriter(file);
		BufferedWriter writer = new BufferedWriter(output);
		
		writer.write("package "+arq.getPakete()+".business.manager;\n\r" );
		
		writer.write("import "+arq.getPakete()+".business.vo.*;" );
		writer.write("import "+arq.getPakete()+".business.dao.*;" );
		writer.write("import java.util.List;" );
		writer.write("import org.apache.commons.logging.Log;" );
		writer.write("import org.apache.commons.logging.LogFactory;" );
		writer.write("\n\r" );
		
		writer.write("/**\n * Clase Manager - Maestros\n */");
		writer.write("public class MaestrosManager {\n\r" );
		
		writer.write("\tprivate static Log log = LogFactory.getFactory().getInstance(MaestrosManager.class);");
		
		for (String i : tr1)
			writer.write(i + "\n");
		for (String i : tr2)
			writer.write(i + "\n");
		for (String i : tr3)
			writer.write(i + "\n");
		writer.write("}");
		System.out.println("Generado el fichero: "+file);
		writer.flush();
	}
	
	public static void paintMaestroDelegate(String file, List<String> tr1, ArquetipoVO arq) throws IOException {
		FileWriter output = new FileWriter(file);
		BufferedWriter writer = new BufferedWriter(output);
		
		writer.write("package "+arq.getPakete()+".web.delegate;\n\r" );
		
		writer.write("import "+arq.getPakete()+".business.vo.*;" );
		writer.write("import "+arq.getPakete()+".business.manager.*;" );
		writer.write("import java.util.List;" );
		writer.write("import org.apache.commons.logging.Log;" );
		writer.write("import org.apache.commons.logging.LogFactory;" );
		writer.write("\n\r" );
		
		writer.write("/**\n * Clase Delegate - Maestros\n */");
		writer.write("public class MaestrosDelegate {\n\r" );
		
		writer.write("\tprivate static Log log = LogFactory.getFactory().getInstance(MaestrosDelegate.class);");
		writer.write( "\n\tprivate MaestrosManager maestrosManager;\n");
		writer.write( "\n\tpublic MaestrosManager getMaestrosManager(){" );
		writer.write( "\n\t\treturn this.maestrosManager;" );
		writer.write( "\n\t}" );
		writer.write( "\n\tpublic void setMaestrosManager( MaestrosManager maestrosManager ){" );
		writer.write( "\n\t\tthis.maestrosManager = maestrosManager;" );
		writer.write( "\n\t}\n\r" );
		
		for (String i : tr1)
			writer.write(i + "\n");
		writer.write("}");
		System.out.println("Generado el fichero: "+file);
		writer.flush();
	}
	
	
	public static void paintMaestroDWR(String file, List<String> tr1, ArquetipoVO arq, List<TablaVO> tablas) throws IOException {
		FileWriter output = new FileWriter(file);
		BufferedWriter writer = new BufferedWriter(output);
		
		writer.write("package "+arq.getPakete()+".web.dwr;\n\r" );
		
		writer.write( "\nimport org.apache.commons.logging.Log;" );
		writer.write( "\nimport org.apache.commons.logging.LogFactory;" );
		writer.write( "\nimport org.springframework.context.ApplicationContext;" );
		writer.write( "\nimport org.springframework.context.support.ClassPathXmlApplicationContext;" );
		writer.write( "\nimport "+arq.getPakete()+".business.vo.*;" );
		writer.write( "\nimport "+arq.getPakete()+".web.delegate.*;" );
		writer.write( "\n\r" );

		writer.write("\npublic class MaestrosController {\n\r");
		writer.write("\n\tprivate static Log log = LogFactory.getFactory().getInstance(MaestrosController.class);");
		writer.write("\n\tprivate static ApplicationContext contextApplication = new ClassPathXmlApplicationContext(");
		writer.write("\n\t\t\tnew String[] { \"beans/business/dao-beans.xml\",");
		writer.write("\n\t\t\t\"beans/business/datasource-beans.xml\",");
		writer.write("\n\t\t\t\"beans/business/transaction-beans.xml\",");
		writer.write("\n\t\t\t\"beans/business/manager-beans.xml\",");
		writer.write("\n\t\t\t\"beans/web/delegate-beans.xml\"});\n\r");
		
		for(TablaVO tab:tablas){
			if(tab.getNivel().equals("maestro")){
				writer.write("\n\tprivate "+tab.getDelegateClase()+" "+tab.getDelegateVariable()+" = " +
						"("+tab.getDelegateClase()+")contextApplication.getBean(\""+tab.getDelegateVariable()+"\");\n");
			}
		}
		for (String i : tr1)
			writer.write(i + "\n");
		writer.write("\n}");
		System.out.println("Generado el fichero: "+file);
		writer.flush();
	}
	
	
	
	public static void addXml(String xml, List<String> lineas) throws IOException {		
		FileWriter output = new FileWriter(xml, true);
		BufferedWriter writer = new BufferedWriter(output);
		for (String i : lineas)
			writer.write(i + "\n");
		writer.flush();
	}
	
	public static void addXml(String xml, String linea) throws IOException {		
		FileWriter output = new FileWriter(xml, true);
		BufferedWriter writer = new BufferedWriter(output);
		writer.write(linea + "\n");
		writer.flush();
	}
	
	
	public static String javadocMethod(int metodo, String tipoDato, String datoVariable){
		String aux = "";
		switch(metodo){
			case Constantes.METODO_GET:
				aux  = "\t/**\n\t * Método que devuelve un objeto ("+tipoDato+") tras pasarle como parámetro la clave primaria.\n";
				aux += "\t * @param id - Integer \n\t * @return "+tipoDato+"\n\t */";
				break;
			case Constantes.METODO_NEW:
				aux  = "\t/**\n\t * Método que inserta un nuevo objeto ("+tipoDato+") en la BD.\n";
				aux += "\t * @param "+datoVariable+" - "+tipoDato+" \n\t * @return (clave creada) - Integer\n\t */";
				break;
			case Constantes.METODO_EDIT:
				aux  = "\t/**\n\t * Método que actualiza un objeto ("+tipoDato+") en la BD.\n";
				aux += "\t * @param "+datoVariable+" - "+tipoDato+" \n\t */";
				break;
			case Constantes.METODO_DELETE:
				aux  = "\t/**\n\t * Método que borra un objeto ("+tipoDato+") tras pasarle como parámetro la clave primaria.\n";
				aux += "\t * @param id - Integer \n\t */";
				break;
			case Constantes.METODO_FIND:
				aux  = "\t/**\n\t * Método que devuelve una lista de objetos ("+tipoDato+") que cumplan las condiciones del objeto pasado como parámetro.\n";
				aux += "\t * @param "+datoVariable+" - "+tipoDato+" \n\t * @return (listado obtenido) - List<"+tipoDato+">\n\t */";
				break;
		}
		return aux;
	}
	

	public static String javadocAction(String mensaje){
		return "\n\t/**\n\t * "+mensaje+" \n\t * \n\t * @param map - ActionMapping \n\t * @param form - ActionForm \n\t * @param req - HttpServletRequest \n\t" +
				" * @param res - HttpServletResponse \n\t * @return ActionForward \n\t */";
	}
	
}
