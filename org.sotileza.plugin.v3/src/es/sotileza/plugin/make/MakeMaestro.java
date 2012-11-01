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
import es.sotileza.plugin.vo.ArquetipoVO;
import es.sotileza.plugin.vo.CampoVO;
import es.sotileza.plugin.vo.FkVO;
import es.sotileza.plugin.vo.TablaVO;


public class MakeMaestro {

	public static List<String> makeAction(TablaVO tab){
		List<String> lineas = new LinkedList<String>();
		
		lineas.add("package "+tab.getActionPakete()+";\n\r" );
		
		lineas.add( "import java.util.List;" );
		lineas.add( "import javax.servlet.http.HttpServletRequest;" );
		lineas.add( "import javax.servlet.http.HttpServletResponse;" );		
		lineas.add( "import org.apache.commons.logging.Log;" );
		lineas.add( "import org.apache.commons.logging.LogFactory;" );
		lineas.add( "import org.apache.struts.action.ActionForm;" );
		lineas.add( "import org.apache.struts.action.ActionForward;" );
		lineas.add( "import org.apache.struts.action.ActionMapping;" );
		lineas.add( "import org.apache.struts.action.ActionMessage;" );
		lineas.add( "import org.apache.struts.action.ActionMessages;" );

		lineas.add( "import "+tab.getActionPakete()+"."+tab.getActionClase()+";" );
		lineas.add( "import "+tab.getVoPakete()+".*;" );
		lineas.add( "import "+tab.getDelegatePakete()+".*;" );
		lineas.add( "import "+tab.getPakete()+".utils.*;" );
		lineas.add( "import "+tab.getFormPakete()+".*;" );
		lineas.add( "\n\r" );

		lineas.add("/**\n * Action ("+tab.getActionClase()+") para el maestro del objeto "+tab.getVoClase()+"\n */");
		lineas.add( "public class "+tab.getActionClase()+" extends SotilezaDispatchAction {\n\r" );
		
		String tipoDato = tab.getVoClase();
		String tipoForm = tab.getFormClase();
		String tipoDelegate = tab.getDelegateClase();
		String datoDelegate = tab.getDelegateVariable();
		String datoMetodo = tab.getVoMetodo();
				
		lineas.add( "\tprivate static Log log = LogFactory.getFactory().getInstance("+tab.getActionClase()+".class);");
		lineas.add( "\tprivate "+tipoDelegate+" "+datoDelegate+";\n");
		if(tab.getFks()!=null){
			for(FkVO i:tab.getFks())
				lineas.add( "\tprivate "+i.getVoTablaAjena().getDelegateClase()+" "+i.getVoTablaAjena().getDelegateVariable()+";\n");
			for(FkVO i:tab.getFks()){ 
				lineas.add( "\tpublic "+i.getVoTablaAjena().getDelegateClase()+" get"+i.getVoTablaAjena().getDelegateMetodo()+"(){" );
		    	lineas.add( "\t\treturn this."+i.getVoTablaAjena().getDelegateVariable()+";" );
				lineas.add( "\t}" );
				lineas.add( "\tpublic void set"+i.getVoTablaAjena().getDelegateMetodo()+"( "+i.getVoTablaAjena().getDelegateClase()+" "+i.getVoTablaAjena().getDelegateVariable()+" ){" );
		    	lineas.add( "\t\tthis."+i.getVoTablaAjena().getDelegateVariable()+" = "+i.getVoTablaAjena().getDelegateVariable()+";" );
				lineas.add( "\t}\n\r" );		
			}
		}
    	lineas.add( "\tpublic "+tipoDelegate+" get"+tipoDelegate+"(){" );
    	lineas.add( "\t\treturn this."+datoDelegate+";" );
		lineas.add( "\t}" );
		lineas.add( "\tpublic void set"+tipoDelegate+"( "+tipoDelegate+" "+datoDelegate+" ){" );
    	lineas.add( "\t\tthis."+datoDelegate+" = "+datoDelegate+";" );
		lineas.add( "\t}\n\r" );	
		
		
		
		lineas.add(Utilidades.javadocAction("method inicio - carga en la request una lista de todos los elementos"));
		lineas.add("\tpublic ActionForward inicio(ActionMapping map, ActionForm form, HttpServletRequest req, HttpServletResponse res) {");
		lineas.add("\t\tList<"+tipoDato+"> lista = this."+datoDelegate+".find"+datoMetodo+"(new "+tipoDato+"());");
		if(tab.getFks()!=null){
			for(FkVO i:tab.getFks()){
				lineas.add("\t\t\tList<"+i.getVoTablaAjena().getVoClase()+"> lista"+i.getVoTablaAjena().getVoClase()+" = " +
						"this."+i.getVoTablaAjena().getDelegateVariable()+".find"+i.getVoTablaAjena().getVoMetodo()+"(new "+i.getVoTablaAjena().getVoClase()+"());");
				lineas.add("\t\t\treq.setAttribute(RequestKeys.MAESTRO_LISTA_"+i.getVoTablaAjena().getVoMetodo().toUpperCase()+", " +
						"lista"+i.getVoTablaAjena().getVoClase()+");");
			}
		}
		lineas.add("\t\t(("+tipoForm+")form).reset(map, req);");
		lineas.add("\t\treq.setAttribute(RequestKeys.OPCION_MENU, NavigationKeys.OPCION_MENU_ADMINISTRACION);");
		lineas.add("\t\treq.setAttribute(RequestKeys.MAESTRO_LISTA_"+datoMetodo.toUpperCase()+", lista);");
		lineas.add("\t\tcreaBreadcrumb(req, NavigationKeys.MIGA_MAESTRO_"+tipoDato.toUpperCase()+", true);");
		lineas.add("\t\treturn map.findForward(\"success\");");
		lineas.add("\t}\n\r");
		
		lineas.add(Utilidades.javadocAction("method consulta - carga en la request una lista con los elementos filtrados"));
		lineas.add("\tpublic ActionForward consulta(ActionMapping map, ActionForm form, HttpServletRequest req, HttpServletResponse res) {");
		lineas.add("\t\ttry{");
		lineas.add("\t\t\t"+tipoDato+" x = (("+tipoForm+")form).populate();");
		lineas.add("\t\t\tList<"+tipoDato+"> lista = this."+datoDelegate+".find"+datoMetodo+"(x);");
		lineas.add("\t\t\treq.setAttribute(RequestKeys.MAESTRO_LISTA_"+datoMetodo.toUpperCase()+", lista);");
		if(tab.getFks()!=null){
			for(FkVO i:tab.getFks()){
				lineas.add("\t\t\tList<"+i.getVoTablaAjena().getVoClase()+"> lista"+i.getVoTablaAjena().getVoClase()+" = " +
						"this."+i.getVoTablaAjena().getDelegateVariable()+".find"+i.getVoTablaAjena().getVoMetodo()+"(new "+i.getVoTablaAjena().getVoClase()+"());");
				lineas.add("\t\t\treq.setAttribute(RequestKeys.MAESTRO_LISTA_"+i.getVoTablaAjena().getVoMetodo().toUpperCase()+", " +
						"lista"+i.getVoTablaAjena().getVoClase()+");");
			}
		}
		lineas.add("\t\t\treq.setAttribute(RequestKeys.OPCION_MENU, NavigationKeys.OPCION_MENU_ADMINISTRACION);");
		lineas.add("\t\t\tcreaBreadcrumb(req, NavigationKeys.MIGA_MAESTRO_"+tipoDato.toUpperCase()+", true);");
		lineas.add("\t\t\treturn map.findForward(\"consulta\");");
		lineas.add("\t\t}catch(Exception e){");
		lineas.add("\t\t\treq.getSession().setAttribute(SessionKeys.EXCEPCION, e);");
		lineas.add("\t\t\treq.getSession().setAttribute(SessionKeys.MSJ_ERROR, e.getMessage() );");
		lineas.add("\t\t\treturn map.findForward(\"failure\");");
		lineas.add("\t\t}");	
		lineas.add("\t}\n\r");
						
		lineas.add(Utilidades.javadocAction("method guardar - crea/actualiza el elemento pasado por el form en la BD."));
		lineas.add("\tpublic ActionForward guardar(ActionMapping map, ActionForm form, HttpServletRequest req, HttpServletResponse res) {");
		lineas.add("\t\ttry {");
		lineas.add("\t\t\t"+tipoDato+" x = (("+tipoForm+")form).populate();");
		lineas.add("\t\t\tif(x.get"+tab.getPks().get(0).getMetodoEnApp()+"()==null || x.get"+tab.getPks().get(0).getMetodoEnApp()+"().toString().equals(\"0\"))");
		lineas.add("\t\t\t\tthis."+datoDelegate+".new"+datoMetodo+"(x);");
		lineas.add("\t\t\telse");
		lineas.add("\t\t\t\tthis."+datoDelegate+".edit"+datoMetodo+"(x);");
		lineas.add("\t\t\tActionMessages mensajes = new ActionMessages();");
		lineas.add("\t\t\tmensajes.add(\"info\", new ActionMessage(\"maestros.info.modificada\"));");
		lineas.add("\t\t\tsaveMessages(req, mensajes);");
		lineas.add("\t\t}catch (Exception e) {");
		lineas.add("\t\t\tlog.error(\"Error guardando maestro...\", e);");
		lineas.add("\t\t\tActionMessages errors = new ActionMessages();");
		lineas.add("\t\t\terrors.add(\"errMsg\", new ActionMessage(\"maestros.error.modificada\")); ");
		lineas.add("\t\t\tsaveErrors(req, errors);");
		lineas.add("\t\t\treturn map.findForward(\"failure\");");
		lineas.add("\t\t}");					
		lineas.add("\t\treturn map.findForward(\"consultaForward\");");	
		lineas.add("\t}\n\r");	

		lineas.add(Utilidades.javadocAction("method borrar - elimina el elemento con el id pasado por la request."));
		lineas.add("\tpublic ActionForward borrar(ActionMapping map, ActionForm form, HttpServletRequest req, HttpServletResponse res) {");
		lineas.add("\t\ttry {");	
		lineas.add("\t\t\tString id = req.getParameter(RequestKeys.MAESTRO_ID_"+datoMetodo.toUpperCase()+");");
		if(tab.getPks().get(0).getTipoEnApp().equals("Long"))
			lineas.add("\t\t\tthis."+datoDelegate+".delete"+datoMetodo+"(Long.parseLong(id));");
		else
			lineas.add("\t\t\tthis."+datoDelegate+".delete"+datoMetodo+"(Integer.parseInt(id));");
		lineas.add("\t\t\tActionMessages mensajes = new ActionMessages();");
		lineas.add("\t\t\tmensajes.add(\"info\", new ActionMessage(\"maestros.info.borrada\"));");
		lineas.add("\t\t\tsaveMessages(req, mensajes);");
		lineas.add("\t\t}catch (Exception e) {");
		lineas.add("\t\t\tActionMessages errors = new ActionMessages();");
		lineas.add("\t\t\terrors.add(\"errMsg\", new ActionMessage(\"maestros.error.borrar\"));");
		lineas.add("\t\t\tsaveErrors(req, errors);");
		lineas.add("\t\t}");					
		lineas.add("\t\treturn map.findForward(\"consultaForward\");");	
		lineas.add("\t}\n\r");	

		lineas.add( "}");
		return lineas;
	}
	
	public static List<String> makeManagerDAOS(TablaVO tab){
		String datoDAO = tab.getDaoVariable();
		List<String> lineas = new LinkedList<String>();
		lineas.add( "\tprivate "+tab.getDaoClase()+" "+datoDAO+";");
		return lineas;
	}
	public static List<String> makeManagerGetSet(TablaVO tab){
		String datoDAO = tab.getDaoVariable();
		List<String> lineas = new LinkedList<String>();
    	lineas.add( "\tpublic "+tab.getDaoClase()+" get"+tab.getDaoMetodo()+"(){" );
    	lineas.add( "\t\treturn this."+datoDAO+";" );
		lineas.add( "\t}" );
		lineas.add( "\tpublic void set"+tab.getDaoMetodo()+"( "+tab.getDaoClase()+" "+datoDAO+" ){" );
    	lineas.add( "\t\tthis."+datoDAO+" = "+datoDAO+";" );
		lineas.add( "\t}\n\r" );	
		return lineas;
	}
	public static List<String> makeManagerMetodos(TablaVO tab){
		String tipoDato = tab.getVoClase();
		String datoMetodo = tab.getVoMetodo();
		String datoVariable = tab.getVoVariable();
		String datoDAO = tab.getDaoVariable();
		List<String> lineas = new LinkedList<String>();
		lineas.add(Utilidades.javadocMethod(Constantes.METODO_GET, tipoDato, datoVariable));
		lineas.add("\tpublic "+tipoDato+" get"+datoMetodo+"("+tab.getPks().get(0).getTipoEnApp()+" id){");
		lineas.add("\t\treturn this."+datoDAO+".get"+datoMetodo+"(id);");
		lineas.add("\t}\n\r");
		
		lineas.add(Utilidades.javadocMethod(Constantes.METODO_NEW, tipoDato, datoVariable));
		lineas.add("\tpublic "+tab.getPks().get(0).getTipoEnApp()+" new"+datoMetodo+"("+tipoDato+" "+datoVariable+"){");
		lineas.add("\t\treturn this."+datoDAO+".new"+datoMetodo+"("+datoVariable+");");
		lineas.add("\t}\n\r");

		lineas.add(Utilidades.javadocMethod(Constantes.METODO_EDIT, tipoDato, datoVariable));
		lineas.add("\tpublic void edit"+datoMetodo+"("+tipoDato+" "+datoVariable+"){");
		lineas.add("\t\tthis."+datoDAO+".edit"+datoMetodo+"("+datoVariable+");");
		lineas.add("\t}\n\r");

		lineas.add(Utilidades.javadocMethod(Constantes.METODO_DELETE, tipoDato, datoVariable));
		lineas.add("\tpublic void delete"+datoMetodo+"("+tab.getPks().get(0).getTipoEnApp()+" id){");
		lineas.add("\t\tthis."+datoDAO+".delete"+datoMetodo+"(id);");
		lineas.add("\t}\n\r");
		
		lineas.add(Utilidades.javadocMethod(Constantes.METODO_FIND, tipoDato, datoVariable));
		lineas.add("\tpublic List<"+tipoDato+"> find"+datoMetodo+"("+tipoDato+" "+datoVariable+"){");
		lineas.add("\t\treturn this."+datoDAO+".find"+datoMetodo+"("+datoVariable+");");
		lineas.add("\t}\n\r");
		return lineas;
	}
	
	
	public static List<String> makeDelegateMetodos(TablaVO tab){
		String tipoDato = tab.getVoClase();
		String datoMetodo = tab.getVoMetodo();
		String datoVariable = tab.getVoVariable();
		String datoManager = "maestrosManager";
		List<String> lineas = new LinkedList<String>();
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
		return lineas;
	}
	
	
	public static List<String> makeActionBeans(TablaVO tab){
		List<String> lineas = new LinkedList<String>();
		String tipoAction = tab.getActionClase();
		String datoDelegate = tab.getDelegateVariable();
		String pak = tab.getActionPakete()+"."+tab.getActionClase();
		
		lineas.add("\n\r\t<!-- Action del maestro de "+tab.getVoClase()+" -->");
		lineas.add("\t<bean name=\"/"+tipoAction+"\" class=\""+pak+"\" singleton=\"false\">");
		lineas.add("\t\t<property name=\""+datoDelegate+"\"><ref bean=\""+datoDelegate+"\"/></property>");
		
		if(tab.getFks()!=null){
			for(FkVO i:tab.getFks())
				lineas.add("\t\t<property name=\""+i.getVoTablaAjena().getDelegateVariable()+"\">" +
						"<ref bean=\""+i.getVoTablaAjena().getDelegateVariable()+"\"/></property>");
		}
		
		lineas.add("\t</bean>\n\r");
		

		
		return lineas;
	}
	
	
	public static String makeMapeos(TablaVO tab){
		String tipoAction = tab.getActionClase();
		String datoMetodo = tab.getVoMetodo();
		String datoForm = tab.getFormVariable();
		
		return "\n\t\t<action path=\"/"+tipoAction+"\""+
					"\n\t\t\ttype=\"org.springframework.web.struts.DelegatingActionProxy\""+
					"\n\t\t\tscope=\"request\""+
					"\n\t\t\tname=\""+datoForm+"\""+
					"\n\t\t\tparameter=\"method\">"+
					"\n\t\t\t<forward name=\"success\" path=\"app.maestros."+datoMetodo.toLowerCase()+"\" />"+
					"\n\t\t\t<forward name=\"consulta\" path=\"app.maestros."+datoMetodo.toLowerCase()+"\" />"+
					"\n\t\t\t<forward name=\"failure\" path=\"/"+tipoAction+".do\" />"+
					"\n\t\t\t<forward name=\"consultaForward\" path=\"/"+tipoAction+".do?method=inicio\" />"+		
				"\n\t\t</action>\n\r";
	}
		
	
	public static String makeTiles(TablaVO tab){
		String datoMetodo = tab.getVoMetodo();	
		
		return "\n\t<definition name=\"app.maestros."+datoMetodo.toLowerCase()+"\" extends=\"app.layout\">"+
			"\n\t\t<put name=\"app.cuerpo\" value=\"../cuerpos/maestros/"+datoMetodo.toLowerCase()+"/consulta.jsp\" />"+
			"\n\t</definition>";
	}
		
	
	public static String makeNavigationKeys(TablaVO tab){
		String tipoDato = tab.getVoClase();
		String datoMetodo = tab.getVoMetodo();	
		return "\tpublic final static String MIGA_MAESTRO_"+tipoDato.toUpperCase()+" = \""+datoMetodo+"\";";  
	}
		
	
	public static String makeRequestKeys(TablaVO tab){
		String datoMetodo = tab.getVoMetodo();	
		return "\tpublic final static String MAESTRO_LISTA_"+datoMetodo.toUpperCase()+" = \"maestroLista"+datoMetodo+"\";"+
				"\n\tpublic final static String MAESTRO_ID_"+datoMetodo.toUpperCase()+" = \"id"+datoMetodo+"\";";
	}
	

	
	public static List<String> makeRequestKeysClase(List<String> vars, ArquetipoVO tab){
		List<String> lineas = new LinkedList<String>();
		lineas.add("package "+tab.getPakete()+".utils;\n\r" );		
		lineas.add("\npublic class RequestKeys {\n\r");
		lineas.add("\tpublic final static String OPCION_MENU = \"opcionMenu\";\n\r");
		for(String i:vars)
			lineas.add(i);
		lineas.add("}");
		return lineas;
	}
	

	public static List<String> makeNavigationKeysClase(List<String> vars, ArquetipoVO tab){
		List<String> lineas = new LinkedList<String>();
		lineas.add("package "+tab.getPakete()+".utils;\n\r" );		
		lineas.add("\npublic class NavigationKeys {\n\r");
		lineas.add("\tpublic final static String OPCION_MENU_INICIO = \"Inicio\";");
		lineas.add("\tpublic final static String OPCION_MENU_ADMINISTRACION = \"Administracion\";\n\r");
		lineas.add("\tpublic final static String MIGA_INICIO = \"Inicio\";");
		lineas.add("\tpublic static final String MIGA_MAESTRO_ADMINISTRACION = \"Administración\";");
		for(String i:vars)
			lineas.add(i);
		lineas.add("}");		
		return lineas;
	}

	public static List<String> makeContextKeys(ArquetipoVO tab){
		List<String> lineas = new LinkedList<String>();
		lineas.add("package "+tab.getPakete()+".utils;\n\r" );

		lineas.add("public class ContextKeys {");
		lineas.add("\n\tpublic final static String APPESCRITORIO_NOMBRE = \"appEscritorio_nombre\";\n\r");
		
		lineas.add("\tpublic final static String LISTADO_MESES = \"listadoMeses\";");
		lineas.add("\tpublic final static String LISTADO_AGNOS = \"listadoAnyos\";\n\r}");
		
		return lineas;
	}
	
	public static List<String> makeDwr(TablaVO tab){
		List<String> lineas = new LinkedList<String>();
			
		String datoMetodo = tab.getVoMetodo();
		String tipoDato = tab.getVoClase();
		lineas.add("\tpublic "+tipoDato+" get"+datoMetodo+"(String id){");
		if(tab.getPks().get(0).getTipoEnApp().equals("Long"))
			lineas.add("\t\t"+tipoDato+" rVal = "+tab.getDelegateVariable()+".get"+datoMetodo+"(Long.valueOf(id));");
		else
			lineas.add("\t\t"+tipoDato+" rVal = "+tab.getDelegateVariable()+".get"+datoMetodo+"(Integer.valueOf(id));");
		lineas.add("\t\treturn rVal;");
		lineas.add("\t}");

		return lineas;
	}
	
	
	
	public static List<String> makeJsp(TablaVO tab){
		List<String> lineas = new LinkedList<String>();
		
		String tipoAction = tab.getActionClase();
		String datoMetodo = tab.getVoMetodo();
		
		lineas.add("<%@ taglib uri=\"http://struts.apache.org/tags-bean\" prefix=\"bean\" %>");
		lineas.add("<%@ taglib uri=\"http://struts.apache.org/tags-html\" prefix=\"html\" %>");
		lineas.add("<%@ taglib uri=\"http://struts.apache.org/tags-logic\" prefix=\"logic\" %>");
		lineas.add("<%@ taglib uri=\"http://displaytag.sf.net\" prefix=\"display\" %>");
		lineas.add("\n<%@page import=\""+tab.getPakete()+".utils.*;"+"\"%>");
		
		lineas.add("\n<div class=\"contenido_app\">\n\r\t<div class=\"container_16\">");
		lineas.add("\t\t<div class=\"grid_12\">\n\r\t\t\t<div id=\"col_izquierda\">");
		lineas.add("\t\t\t\t<jsp:include page=\"../../../appMigas.jsp\" flush=\"true\"/>");
		lineas.add("\t\t\t\t<h1>"+datoMetodo+"</h1>");
		lineas.add("\t\t\t\t<jsp:include page=\"../../../appMensajes.jsp\" flush=\"true\"/>");
		lineas.add("\t\t\t\t<p class=\"botonera right\">");
		lineas.add("\t\t\t\t\t<a id=\"nuevo_"+datoMetodo+"\" href=\"#\" class=\"sexybutton sexymedium spinner\"><span><span><span class=\"add\">Nuevo registro</span></span></span></a>");
		lineas.add("\t\t\t\t</p>");
		
		lineas.add("\t\t\t\t<display:table name=\"<%= RequestKeys.MAESTRO_LISTA_"+datoMetodo.toUpperCase()+" %>\" id=\"row\" pagesize=\"20\"");
		lineas.add("\t\t\t\t\t\texport=\"false\" class=\"pijama\" requestURI=\""+tipoAction+".do?method=inicio\">");
		if(tab.getPks()!=null)
			for(CampoVO i:tab.getPks())
				lineas.add("\t\t\t\t\t<display:column title=\""+i.getNombreEnApp()+"\" property=\""+i.getNombreEnApp()+"\" sortable=\"true\"/>");		
		if(tab.getCampos()!=null)
			for(CampoVO i:tab.getCampos()){
				if(i.getTipoEnApp().equals("Boolean")){
					lineas.add("\t\t\t\t\t<display:column title=\""+i.getNombreEnApp()+"\" sortProperty=\""+i.getNombreEnApp()+"\" sortable=\"true\">");
					lineas.add("\t\t\t\t\t\t<logic:equal name=\"row\" property=\""+i.getNombreEnApp()+"\" value=\"true\">");
					lineas.add("\t\t\t\t\t\t\t<img src=\"<%=request.getContextPath()%>/pages/img/ico/tick.png\"/>");
					lineas.add("\t\t\t\t\t\t</logic:equal>");
					lineas.add("\t\t\t\t\t</display:column>");
				}else if( i.getTipoEnApp().equals("Double") || i.getTipoEnApp().equals("Date") ){
					lineas.add("\t\t\t\t\t<display:column title=\""+i.getNombreEnApp()+"\" property=\""+i.getNombreEnApp()+"Formato\" sortable=\"true\"/>");
				}else {
					lineas.add("\t\t\t\t\t<display:column title=\""+i.getNombreEnApp()+"\" property=\""+i.getNombreEnApp()+"\" sortable=\"true\"/>");
				}
			}
			
		lineas.add("\t\t\t\t\t<display:column class=\"right\">");
		lineas.add("\t\t\t\t\t\t<a id=\"<bean:write property=\""+tab.getPks().get(0).getNombreEnApp()+"\" name=\"row\"/>\" href=\"#\" class=\"tip editar_"+datoMetodo+"\" title=\"Modificar "+datoMetodo+"\"><img src=\"<%=request.getContextPath()%>/pages/img/ico/edit.png\"/></a>");
		lineas.add("\t\t\t\t\t\t<a href=\""+tipoAction+".do?method=borrar&<%=RequestKeys.MAESTRO_ID_"+datoMetodo.toUpperCase()+"%>=<bean:write property=\""+tab.getPks().get(0).getNombreEnApp()+"\" name=\"row\"/>\" class=\"tip eliminar_registro\" title=\"Eliminar Registro\"><img src=\"<%=request.getContextPath()%>/pages/img/ico/cross.png\"/></a>");
		lineas.add("\t\t\t\t\t</display:column>");
		lineas.add("\t\t\t\t</display:table>\n\r\t\t\t</div>\n\r\t\t</div>");
		
		
		lineas.add("\n\r\t\t<div class=\"grid_4\">\n\r\t\t\t<div id=\"col_derecha_app\">");
		lineas.add("\t\t\t\t<div class=\"seccion\">");
		lineas.add("\t\t\t\t\t<div class=\"seccion_cabecera\">");
		lineas.add("\t\t\t\t\t\t<h3>Filtro</h3>");
		lineas.add("\t\t\t\t\t</div>");
		lineas.add("\t\t\t\t\t<div class=\"seccion_contenido\">\n\r\t\t\t\t\t\t<ul>\n\r\t\t\t\t\t\t\t<li>");
		lineas.add("\t\t\t\t\t\t\t\t<html:form action=\"/"+tab.getActionClase()+".do?method=consulta\" styleClass=\"form\">");
		lineas.add("\t\t\t\t\t\t\t\t\t<fieldset>");
		
		if(tab.getCampos()!=null)
			for(CampoVO i:tab.getCampos()){
				if(i.getTipoEnApp().equals("Boolean")){
					lineas.add("\t\t\t\t\t\t\t\t\t\t<div class=\"grid-12-12\">");
					lineas.add("\t\t\t\t\t\t\t\t\t\t\t<label>"+i.getNombreEnApp()+"</label>");
					lineas.add("\t\t\t\t\t\t\t\t\t\t\t<html:checkbox styleId=\"filtro_"+i.getNombreEnApp()+"\" property=\""+i.getNombreEnApp()+"\"/>");
					lineas.add("\t\t\t\t\t\t\t\t\t\t</div>");
				}else if(i.getTipoEnApp().equals("String")){
					lineas.add("\t\t\t\t\t\t\t\t\t\t<div class=\"grid-12-12\">");
					lineas.add("\t\t\t\t\t\t\t\t\t\t\t<label>"+i.getNombreEnApp()+"</label>");
					lineas.add("\t\t\t\t\t\t\t\t\t\t\t<html:text styleId=\"filtro_"+i.getNombreEnApp()+"\" property=\""+i.getNombreEnApp()+"\" maxlength=\""+i.getTam()+"\"/>");
					lineas.add("\t\t\t\t\t\t\t\t\t\t</div>");
				}else if(i.getTipoEnApp().equals("Date")){
					lineas.add("\t\t\t\t\t\t\t\t\t\t<div class=\"grid-12-12\">");
					lineas.add("\t\t\t\t\t\t\t\t\t\t\t<label>"+i.getNombreEnApp()+"</label>");
					lineas.add("\t\t\t\t\t\t\t\t\t\t\t<html:text styleId=\"filtro_"+i.getNombreEnApp()+"\" property=\""+i.getNombreEnApp()+"\" readonly=\"true\" styleClass=\"date_picker\"/>");
					lineas.add("\t\t\t\t\t\t\t\t\t\t</div>");
				}
			}
		
		lineas.add("\t\t\t\t\t\t\t\t\t</fieldset>");
		lineas.add("\t\t\t\t\t\t\t\t\t<p class=\"botonera\">");
		lineas.add("\t\t\t\t\t\t\t\t\t\t<button type=\"submit\" class=\"sexybutton\"><span><span><span class=\"search\">Buscar</span></span></span></button>");
		lineas.add("\t\t\t\t\t\t\t\t\t</p>");
		lineas.add("\t\t\t\t\t\t\t\t</html:form>");
		lineas.add("\t\t\t\t\t\t\t</li>\n\r\t\t\t\t\t\t</ul>\n\r\t\t\t\t\t</div>");
		lineas.add("\t\t\t\t</div>");
		
		lineas.add("\t\t\t</div>\n\r\t\t</div>\n\r\t</div>\n\r</div>");
	
		
		//DIALOG
		lineas = construyeDialog(lineas, tab);
		
		
		//JAVASCRIPT
		lineas.add("\n\r<script type=\"text/javascript\">\n\r$j(function(){");
		lineas.add("\t$j('a.eliminar_registro').creaConfirm();\n");
		
		lineas.add("\tvar dialog = $j('div#datos_"+datoMetodo+"').dialog({");
		lineas.add("\t\tdialogClass: 'noTitle',");
		lineas.add("\t\tautoOpen: false,");
		lineas.add("\t\tresizable: false,");
		lineas.add("\t\tmodal: true,");
		lineas.add("\t\twidth: 700,");
		lineas.add("\t\topen: function() {");
		lineas.add("\t\t\tredimensionaFrame();");
		lineas.add("\t\t\t$j('button.spinner, a.spinner').removeSpinner();");
		lineas.add("\t\t},");
		lineas.add("\t\tclose: function(){");
		lineas.add("\t\t\t$j.validationEngine.closePrompt('.formError',true);");
		lineas.add("\t\t}");
		lineas.add("\t});\n");
		
		lineas.add("\t$j('#nuevo_"+datoMetodo+"').click(function(e){");
		lineas.add("\t\te.preventDefault();");
		lineas.add("\t\t$j(':text,:hidden',dialog).val('');");
		lineas.add("\t\t$j(':checkbox',dialog).attr('checked',false);");
		lineas.add("\t\tdialog.dialog('open');");
		lineas.add("\t});\n");
		
		lineas.add("\t$j('.editar_"+datoMetodo+"').click(function(e){");
		lineas.add("\t\te.preventDefault();");
		lineas.add("\t\tMaestrosController.get"+datoMetodo+"($j(this).attr('id'), got"+datoMetodo+");");
		lineas.add("\t});\n");
		
		
		lineas.add("\tfunction got"+datoMetodo+"(rVal) {");
		if(tab.getPks()!=null)
			for(CampoVO i:tab.getPks())
				lineas.add("\t\t$j(':hidden#"+i.getNombreEnApp()+"',dialog).val(rVal."+i.getNombreEnApp()+");");
		if(tab.getCampos()!=null)
			for(CampoVO i:tab.getCampos()){
				if(i.getTipoEnApp().equals("Boolean")){
					lineas.add("\t\t$j(':checkbox#"+i.getNombreEnApp()+"',dialog).attr('checked',rVal."+i.getNombreEnApp()+");");
				}else if(i.getTipoEnApp().equals("Date") || i.getTipoEnApp().equals("Double")){
					lineas.add("\t\t$j(':text#"+i.getNombreEnApp()+"',dialog).val(rVal."+i.getNombreEnApp()+"Formato);");
				}else if(i.getTam()<=255){
					lineas.add("\t\t$j(':text#"+i.getNombreEnApp()+"',dialog).val(rVal."+i.getNombreEnApp()+");");
				}else{
					lineas.add("\t\t$j('textArea#"+i.getNombreEnApp()+"',dialog).val(rVal."+i.getNombreEnApp()+");");
				}
			}			
		
		if(tab.getFks()!=null){
			for(FkVO i:tab.getFks()){
				lineas.add("\t\t$j('select#"+i.getNombreEnApp()+"',dialog).val(rVal."
						+i.getNombreEnApp()+"."+i.getVoTablaAjena().getPks().get(0).getNombreEnApp()+");");
			}
		}	
		lineas.add("\t\tdialog.dialog('open');");
		lineas.add("\t}\n");
		lineas.add("});");
		lineas.add("</script>");

		return lineas;
	}

	
	private static List<String> construyeDialog(List<String> lineas, TablaVO tab){
		String tipoAction = tab.getActionClase();
		String datoMetodo = tab.getVoMetodo();
		
		lineas.add("<div id=\"datos_"+datoMetodo+"\" class=\"dialog\">");
		lineas.add("\t<h3>Datos</h3>");
		lineas.add("\t<html:form action=\"/"+tipoAction+".do?method=guardar\" method=\"post\" styleClass=\"form\">");
		if(tab.getPks()!=null)
			for(CampoVO i:tab.getPks())
				lineas.add("<html:hidden property=\""+i.getNombreEnApp()+"\" styleId=\""+i.getNombreEnApp()+"\"/>");
		
		lineas.add("\t<fieldset>\n\r\t\t<div class=\"grid-12-12\">");
		if(tab.getCampos()!=null)
			for(CampoVO i:tab.getCampos()){
				if(i.getTipoEnApp().equals("Boolean")){
					lineas.add("\t\t\t<div class=\"grid-2-12\">\n\t\t\t\t<label>"+i.getNombreEnApp()+"</label>");
					lineas.add("\t\t\t\t<html:checkbox property=\""+i.getNombreEnApp()+"\" styleId=\""+i.getNombreEnApp()+"\"/>\n\t\t\t</div>");
				}else if(i.getTipoEnApp().equals("Date")){
					lineas.add("\t\t\t<div class=\"grid-3-12\">\n\t\t\t\t<label>"+i.getNombreEnApp()+"</label>");
					lineas.add("\t\t\t\t<html:text property=\""+i.getNombreEnApp()+"\" styleId=\""+i.getNombreEnApp()+"\" readonly=\"true\" styleClass=\"date_picker\"/>\n\t\t\t</div>");
				}else if(i.getTipoEnApp().equals("Double")){
					lineas.add("\t\t\t<div class=\"grid-3-12\">\n\t\t\t\t<label>"+i.getNombreEnApp()+"</label>");
					lineas.add("\t\t\t\t<html:text property=\""+i.getNombreEnApp()+"\" styleId=\""+i.getNombreEnApp()+"\" styleClass=\"right\"/>\n\t\t\t</div>");
				}else if(i.getTam()<=255){
					lineas.add("\t\t\t<div class=\"grid-3-12\">\n\t\t\t\t<label>"+i.getNombreEnApp()+"</label>");
					lineas.add("\t\t\t\t<html:text property=\""+i.getNombreEnApp()+"\" styleId=\""+i.getNombreEnApp()+"\" maxlength=\""+i.getTam()+"\"/>\n\t\t\t</div>");
				}else{
					lineas.add("\t\t\t<div class=\"grid-12-12\">\n\t\t\t\t<label>"+i.getNombreEnApp()+"</label>");
					lineas.add("\t\t\t\t<html:textarea property=\""+i.getNombreEnApp()+"\" styleId=\""+i.getNombreEnApp()+"\"></html:textarea>\n\t\t\t</div>");
				}
			}
		if(tab.getFks()!=null){
			for(FkVO i:tab.getFks()){
				lineas.add("\t\t\t<div class=\"grid-3-12\">\n\t\t\t\t<label>"+i.getNombreEnApp()+"</label>");
				lineas.add("\t\t\t\t<html:select property=\""+i.getNombreEnApp()+"."+i.getVoTablaAjena().getPks().get(0).getNombreEnApp()
						+"\" styleId=\""+i.getNombreEnApp()+"\">" +
				"<html:options collection='<%=RequestKeys.MAESTRO_LISTA_"+i.getVoTablaAjena().getVoMetodo().toUpperCase()+"%>' property='"+i.getVoTablaAjena().getPks().get(0).getNombreEnApp()+"' " +
						"labelProperty='selectValue'/>" +
				"</html:select>\n\t\t\t</div>");
			}
		}		
		
		
		lineas.add("\t\t</div>\n\t</fieldset>");
		lineas.add("\t<p class=\"botonera\"><button class=\"sexybutton\" type=\"submit\"><span><span><span class=\"save\">Guardar</span></span></span></button>ó <a href=\"#\" class=\"cierra\">Cancelar</a>\n\t</p>");
		lineas.add("\t</html:form>\n</div>");
		return lineas;
	}
}
