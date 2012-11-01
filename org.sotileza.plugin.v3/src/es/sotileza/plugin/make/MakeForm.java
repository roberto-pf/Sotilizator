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

import es.sotileza.plugin.vo.CampoVO;
import es.sotileza.plugin.vo.FkVO;
import es.sotileza.plugin.vo.TablaVO;


public class MakeForm {

	public static List<String> makeForm(TablaVO tab){
		List<String> lineas = new LinkedList<String>();
		
		lineas.add("package "+tab.getFormPakete()+";\n\r" );

		lineas.add( "import org.apache.commons.lang.StringUtils;" );		
		lineas.add( "import org.apache.commons.logging.Log;" );
		lineas.add( "import org.apache.commons.logging.LogFactory;" );
		lineas.add( "import org.apache.struts.action.ActionForm;" );
		lineas.add( "import org.apache.struts.action.ActionMapping;" );
		lineas.add( "import javax.servlet.http.HttpServletRequest;" );
		lineas.add( "import org.apache.struts.validator.ValidatorForm;" );
		lineas.add( "import java.text.SimpleDateFormat;" );
		lineas.add( "import java.text.ParseException;" );
		lineas.add( "import java.text.NumberFormat;" );
		lineas.add( "import java.util.Locale;" );
		lineas.add( "import "+tab.getVoPakete()+".*;" );
		lineas.add( "\n\r" );
		
		lineas.add("/**\n * Clase ActionForm - "+tab.getFormClase()+"\n */");
		lineas.add( "public class "+tab.getFormClase()+" extends ValidatorForm {\n\r" );
		
		lineas.add("\tprivate static Log log = LogFactory.getFactory().getInstance("+tab.getFormClase()+".class);");
		lineas.add( "\n" );
		
		
		//PK
		if(tab.getPks()!=null)
			for(CampoVO i:tab.getPks()) lineas.add( "\tprivate "+i.getTipoEnApp()+" "+i.getNombreEnApp()+";" );
		//FK
		if(tab.getFks()!=null)
			for(FkVO i:tab.getFks())	lineas.add( "\tprivate "+i.getTipoEnApp()+" "+i.getNombreEnApp()+";" );
		//Campos
		if(tab.getCampos()!=null)
			for(CampoVO i:tab.getCampos())	lineas.add( "\tprivate "+i.getTipoEnForm()+" "+i.getNombreEnApp()+";" );
		
		lineas.add( "\n" );

		
	    //constructor
	    lineas.add("\tpublic "+tab.getFormClase()+"() {");
	    lineas.add("\t\tsuper();");
	    if(tab.getFks()!=null)
			for(FkVO i:tab.getFks())	lineas.add("\t\t"+i.getNombreEnApp()+" = new "+i.getTipoEnApp()+"();");
	    lineas.add("\t}\n\r");
		
	    
		// GETTER Y SETTER
		//PK
		if(tab.getPks()!=null)
		for(CampoVO i:tab.getPks()){
	    	lineas.add( "\tpublic "+i.getTipoEnApp()+" get"+i.getMetodoEnApp()+"(){" );
	    	lineas.add( "\t\treturn this."+i.getNombreEnApp()+";" );
			lineas.add( "\t}" );
			lineas.add( "\tpublic void set"+i.getMetodoEnApp()+"( "+i.getTipoEnApp()+" "+i.getNombreEnApp()+" ){" );
	    	lineas.add( "\t\tthis."+i.getNombreEnApp()+" = "+i.getNombreEnApp()+";" );
			lineas.add( "\t}\n\r" );
		}	
		//FK
		if(tab.getFks()!=null)
		for(FkVO i:tab.getFks()){
	    	lineas.add( "\tpublic "+i.getTipoEnApp()+" get"+i.getMetodoEnApp()+"(){" );
	    	lineas.add( "\t\treturn this."+i.getNombreEnApp()+";" );
			lineas.add( "\t}" );
			lineas.add( "\tpublic void set"+i.getMetodoEnApp()+"( "+i.getTipoEnApp()+" "+i.getNombreEnApp()+" ){" );
	    	lineas.add( "\t\tthis."+i.getNombreEnApp()+" = "+i.getNombreEnApp()+";" );
			lineas.add( "\t}\n\r" );			
		}
		//Campos
		if(tab.getCampos()!=null)
		for(CampoVO i:tab.getCampos()){
	    	lineas.add( "\tpublic "+i.getTipoEnForm()+" get"+i.getMetodoEnApp()+"(){" );
	    	lineas.add( "\t\treturn this."+i.getNombreEnApp()+";" );
			lineas.add( "\t}" );
			lineas.add( "\tpublic void set"+i.getMetodoEnApp()+"( "+i.getTipoEnForm()+" "+i.getNombreEnApp()+" ){" );
	    	lineas.add( "\t\tthis."+i.getNombreEnApp()+" = "+i.getNombreEnApp()+";" );
			lineas.add( "\t}\n\r" );
		}
		
		lineas.add( "\n" );
		
		
	    //set
	    lineas.add("\tpublic void set("+tab.getVoClase()+" x) {");
		lineas.add("\t\tSimpleDateFormat sdf = new SimpleDateFormat(\"dd-MM-yyyy\");");
		lineas.add("\t\tNumberFormat nf = NumberFormat.getInstance(Locale.GERMANY);\n");
		//PK
		if(tab.getPks()!=null)
			for(CampoVO i:tab.getPks()) lineas.add( "\t\tthis."+i.getNombreEnApp()+" = x.get"+i.getMetodoEnApp()+"();\n" );
		//FK
		if(tab.getFks()!=null)
			for(FkVO i:tab.getFks())	lineas.add( "\t\tthis."+i.getNombreEnApp()+" = x.get"+i.getMetodoEnApp()+"();\n" );
		//Campos
		if(tab.getCampos()!=null)
			for(CampoVO i:tab.getCampos()){
				if(i.getTipoEnApp().equals("String")){
					lineas.add( "\t\tthis."+i.getNombreEnApp()+" = x.get"+i.getMetodoEnApp()+"();\n" );
					
				}else if(i.getTipoEnApp().equals("Double")){
					lineas.add( "\t\tthis."+i.getNombreEnApp()+" = x.get"+i.getMetodoEnApp()+"()!=null?nf.format(x.get"+i.getMetodoEnApp()+"()):\"0\";\n" );

				}else if(i.getTipoEnApp().equals("Integer")){
					lineas.add( "\t\tthis."+i.getNombreEnApp()+" = x.get"+i.getMetodoEnApp()+"().toString();\n" );

				}else if(i.getTipoEnApp().equals("Date")){
					lineas.add( "\t\tthis."+i.getNombreEnApp()+" = x.get"+i.getMetodoEnApp()+"()!=null?sdf.format(x.get"+i.getMetodoEnApp()+"()):null;\n" );
								    	
				}else if(i.getTipoEnApp().equals("Boolean")){
					lineas.add( "\t\tthis."+i.getNombreEnApp()+" = x.get"+i.getMetodoEnApp()+"();\n" );

				}				
			}
		
		lineas.add("\t}\n\r");
		
	    //populate
		lineas.add("\tpublic "+tab.getVoClase()+" populate() throws ParseException{");
		lineas.add("\t\t"+tab.getVoClase()+" x = new "+tab.getVoClase()+"();");
		lineas.add("\t\tSimpleDateFormat sdf = new SimpleDateFormat(\"dd-MM-yyyy\");");
		lineas.add("\t\tNumberFormat nf = NumberFormat.getInstance(Locale.GERMANY);\n");
		
		//PK
		if(tab.getPks()!=null)
			for(CampoVO i:tab.getPks()) lineas.add( "\t\tx.set"+i.getMetodoEnApp()+"(this."+i.getNombreEnApp()+");\n" );
		//FK
		if(tab.getFks()!=null)
			for(FkVO i:tab.getFks()) lineas.add( "\t\tx.set"+i.getMetodoEnApp()+"(this."+i.getNombreEnApp()+");\n" );
		//Campos
		if(tab.getCampos()!=null)
			for(CampoVO i:tab.getCampos()){
				if(i.getTipoEnApp().equals("String")){
					lineas.add( "\t\tx.set"+i.getMetodoEnApp()+"(this."+i.getNombreEnApp()+");\n" );
					
				}else if(i.getTipoEnApp().equals("Double")){
			    	lineas.add( "\t\tif(!StringUtils.isBlank(this."+i.getNombreEnApp()+")) {" );
			    	lineas.add( "\t\t\tx.set"+i.getMetodoEnApp()+"(nf.parse(this."+i.getNombreEnApp()+").doubleValue());" );
			    	lineas.add("\t\t}\n");

				}else if(i.getTipoEnApp().equals("Integer")){
					lineas.add( "\t\tif(!StringUtils.isBlank(this."+i.getNombreEnApp()+")) {");
					lineas.add( "\t\t\tx.set"+i.getMetodoEnApp()+"(new Integer(this."+i.getNombreEnApp()+"));");
					lineas.add( "\t\t}\n");

				}else if(i.getTipoEnApp().equals("Long")){
					lineas.add( "\t\tif(!StringUtils.isBlank(this."+i.getNombreEnApp()+")) {");
					lineas.add( "\t\t\tx.set"+i.getMetodoEnApp()+"(new Long(this."+i.getNombreEnApp()+"));");
					lineas.add( "\t\t}\n");

				}else if(i.getTipoEnApp().equals("Date")){
			    	lineas.add( "\t\tif(!StringUtils.isBlank(this."+i.getNombreEnApp()+")) {" );
			    	lineas.add( "\t\t\tx.set"+i.getMetodoEnApp()+"(sdf.parse(this."+i.getNombreEnApp()+"));" );
			    	lineas.add("\t\t}\n");

				}else if(i.getTipoEnApp().equals("Boolean")){
					lineas.add( "\t\tx.set"+i.getMetodoEnApp()+"(this."+i.getNombreEnApp()+"==null?false:this."+i.getNombreEnApp()+");\n" );

				}				
			}
				
		lineas.add( "\t\treturn x;");
	    lineas.add("\t}\n\r");
	    
	    //reset
		lineas.add("\tpublic void reset(ActionMapping mapping, HttpServletRequest request){");
		//PK
		if(tab.getPks()!=null)
			for(CampoVO i:tab.getPks()) lineas.add( "\t\tthis."+i.getNombreEnApp()+"=null;" );
		//Campos
		if(tab.getCampos()!=null)	
			for(CampoVO i:tab.getCampos()){
				if(i.getTipoEnApp().equals("String")){
					lineas.add( "\t\tthis."+i.getNombreEnApp()+"=\"\";" );		
				}else if(i.getTipoEnApp().equals("Boolean")){
					lineas.add( "\t\tthis."+i.getNombreEnApp()+"=false;" );
				}
			}
		//FK
	    if(tab.getFks()!=null)
			for(FkVO i:tab.getFks())	lineas.add("\t\t"+i.getNombreEnApp()+" = new "+i.getTipoEnApp()+"();");
		lineas.add("\t}\n\r");
	    
		lineas.add( "}");
		return lineas;
	}	
	
	

	public static String makeConfig(TablaVO tab){
		String datoFORM = tab.getFormVariable();
		String pak = tab.getFormPakete()+"."+tab.getFormClase();
		
		return "<form-bean name=\""+datoFORM+"\" type=\""+pak+"\"/>";	  	
	}
	
}
