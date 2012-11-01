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
import es.sotileza.plugin.vo.SetVO;
import es.sotileza.plugin.vo.TablaVO;


public class MakeVO {

	public static List<String> makeVO(TablaVO tab){
		List<String> lineas = new LinkedList<String>();
		
		lineas.add("package "+tab.getVoPakete()+";\n\r" );
		
		lineas.add( "import org.apache.commons.logging.Log;" );
		lineas.add( "import org.apache.commons.logging.LogFactory;" );
		
		if(tab.getCampos()!=null){
			boolean tiene = false;
			for(CampoVO i:tab.getCampos()) if(i.getTipoEnApp().equals("Date"))	tiene =true;
			if(tiene){
				lineas.add( "import java.util.Date;" );
				lineas.add( "import java.text.SimpleDateFormat;" );
			}			
			tiene = false;
			for(CampoVO i:tab.getCampos()) if(i.getTipoEnApp().equals("Double"))	tiene =true;
			if(tiene){
				lineas.add( "import "+tab.getPakete()+".utils.Herramientas;" );
			}
		}
		if(tab.getSets()!=null && tab.getSets().size()>0) lineas.add( "import java.util.Set;" );
		
		lineas.add( "\n\r" );
		
		lineas.add("/**\n * Clase Value Object - "+tab.getVoClase()+"\n */");
		lineas.add( "public class "+tab.getVoClase()+" {\n\r" );
		
		lineas.add("\tprivate static Log log = LogFactory.getFactory().getInstance("+tab.getVoClase()+".class);");
		lineas.add( "\n" );
		
		//PK
		if(tab.getPks()!=null)
			for(CampoVO i:tab.getPks()) lineas.add( "\tprivate "+i.getTipoEnApp()+" "+i.getNombreEnApp()+";" );
		//FK
		if(tab.getFks()!=null)
			for(FkVO i:tab.getFks())	lineas.add( "\tprivate "+i.getTipoEnApp()+" "+i.getNombreEnApp()+";" );
		//Campos
		if(tab.getCampos()!=null)
			for(CampoVO i:tab.getCampos())	lineas.add( "\tprivate "+i.getTipoEnApp()+" "+i.getNombreEnApp()+";" );
		//Set
		if(tab.getSets()!=null)
			for(SetVO i:tab.getSets())lineas.add( "\tprivate Set<"+i.getTipoEnApp()+"> "+i.getNombreEnApp()+";" );
		
		lineas.add( "\n" );
		
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
	    	lineas.add( "\tpublic "+i.getTipoEnApp()+" get"+i.getMetodoEnApp()+"(){" );
	    	lineas.add( "\t\treturn this."+i.getNombreEnApp()+";" );
			lineas.add( "\t}" );
			lineas.add( "\tpublic void set"+i.getMetodoEnApp()+"( "+i.getTipoEnApp()+" "+i.getNombreEnApp()+" ){" );
	    	lineas.add( "\t\tthis."+i.getNombreEnApp()+" = "+i.getNombreEnApp()+";" );
			lineas.add( "\t}\n\r" );
		}
		//Set
		if(tab.getSets()!=null)
		for(SetVO i:tab.getSets()){
	    	lineas.add( "\tpublic Set<"+i.getTipoEnApp()+"> get"+i.getMetodoEnApp()+"(){" );
	    	lineas.add( "\t\treturn this."+i.getNombreEnApp()+";" );
			lineas.add( "\t}" );
			lineas.add( "\tpublic void set"+i.getMetodoEnApp()+"( Set<"+i.getTipoEnApp()+"> "+i.getNombreEnApp()+" ){" );
	    	lineas.add( "\t\tthis."+i.getNombreEnApp()+" = "+i.getNombreEnApp()+";" );
			lineas.add( "\t}\n\r" );
		}
		
		
		//Otras Ayudas
		if(tab.getCampos()!=null)
			for(CampoVO i:tab.getCampos()){
				if(i.getTipoEnApp().equals("Date")){
			    	lineas.add( "\tpublic String get"+i.getMetodoEnApp()+"Formato(){" );
			    	lineas.add( "\t\tSimpleDateFormat formato =	new SimpleDateFormat(\"dd-MM-yyyy\");" );
			    	lineas.add( "\t\tString rVal = \"\";" );
			    	lineas.add( "\t\tif(this."+i.getNombreEnApp()+"!=null)" );
			    	lineas.add( "\t\t\trVal = formato.format(this."+i.getNombreEnApp()+").toString();" );
			    	lineas.add( "\t\treturn rVal;" );
					lineas.add( "\t}" );
				}else if(i.getTipoEnApp().equals("Double")){
			    	lineas.add( "\tpublic String get"+i.getMetodoEnApp()+"Formato(){" );
			    	lineas.add( "\t\tString rVal = \"0,00\";" );
			    	lineas.add( "\t\tif(this."+i.getNombreEnApp()+"!=null)" );
			    	lineas.add( "\t\t\trVal = Herramientas.formatear(this."+i.getNombreEnApp()+");" );
			    	lineas.add( "\t\treturn rVal;" );
					lineas.add( "\t}" );
				}

			}
		
		lineas.add( "\tpublic String getSelectValue(){" );
		lineas.add( "\t\tString rVal = \"\";" );
		if(tab.getPks()!=null)
			for(CampoVO i:tab.getPks()){
				lineas.add( "\t\trVal += \"["+i.getNombreEnApp()+" = \"+this."+i.getNombreEnApp()+"+\"] \";" );
			}
		if(tab.getCampos()!=null)
			for(CampoVO i:tab.getCampos()){
				if(i.getTipoEnApp().equals("Date") || i.getTipoEnApp().equals("Double")){
			    	lineas.add( "\t\trVal += \"["+i.getNombreEnApp()+" = \"+this."+i.getNombreEnApp()+"+\"] \";" );
				}else if(i.getTipoEnApp().equals("String")){
			    	lineas.add( "\t\trVal += \"["+i.getNombreEnApp()+" = \"+this."+i.getNombreEnApp()+"+\"] \";" );
				}
			}
		lineas.add( "\t\treturn rVal;" );
		lineas.add( "\t}" );

		
		lineas.add( "}");
		return lineas;
	}	
}
