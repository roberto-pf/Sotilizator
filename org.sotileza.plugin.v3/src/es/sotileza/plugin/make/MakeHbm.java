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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import es.sotileza.plugin.vo.CampoVO;
import es.sotileza.plugin.vo.FkVO;
import es.sotileza.plugin.vo.SetVO;
import es.sotileza.plugin.vo.TablaVO;

public class MakeHbm {
	
	
	public static List<String> makeHbms(TablaVO tab){
		List<String> lineas = new LinkedList<String>();

		//Cabecera
		lineas.add("<?xml version=\"1.0\"?>");
		lineas.add("<!DOCTYPE hibernate-mapping PUBLIC \"-//Hibernate/Hibernate Mapping DTD 3.0//EN\" ");
		lineas.add("\"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd\">");
		lineas.add("<hibernate-mapping>");
		lineas.add("\t<class name=\""+tab.getVoPaketeCompleto()+"\" table=\""+tab.getNombreTabla()+"\" schema=\""+tab.getNombreEsquema()+"\">");
	    
		//PK
		if(tab.getPks()!=null)
		for(CampoVO i:tab.getPks()){
			lineas.add("\t\t<id name=\""+i.getNombreEnApp()+"\" type=\""+i.getTipoParaHibernate()+"\" unsaved-value=\"0\">");
			lineas.add("\t\t\t<column name=\""+i.getNombreEnTabla()+"\" precision=\""+i.getTamOrPrecision().intValue()+"\" " +
					"scale=\""+i.getScale().intValue()+"\"/>");
			if(tab.getNombreSecuencia()!=null){
				lineas.add("\t\t\t<generator class=\"sequence\">");
				lineas.add("\t\t\t\t<param name=\"sequence\">"+tab.getNombreEsquema()+"."+tab.getNombreSecuencia()+"</param>");
				lineas.add("\t\t\t</generator>");
			}else{
				lineas.add("\t\t\t<generator class=\"assigned\"/>");
			}
			lineas.add("\t\t</id>");	
		}
		
		//FK
		if(tab.getFks()!=null)
		for(FkVO i:tab.getFks()){
			lineas.add("\t\t<many-to-one name=\""+i.getNombreEnApp()+"\" class=\""+i.getTipoCompletoEnApp()+"\" fetch=\"select\" lazy=\"false\">");
			lineas.add("\t\t\t<column name=\""+i.getNombreEnTabla()+"\" precision=\""+i.getTamOrPrecision().intValue()+"\" " +
					"scale=\""+i.getScale().intValue()+"\" not-null=\"true\"/>");
			lineas.add("\t\t</many-to-one>");	
		}
		
		//Campos
		if(tab.getCampos()!=null)
		for(CampoVO i:tab.getCampos()){
			lineas.add("\t\t<property name=\""+i.getNombreEnApp()+"\" type=\""+i.getTipoParaHibernate()+"\">");
			if(i.getTipoParaHibernate().equals("string") || i.getTipoParaHibernate().equals("text")){
				lineas.add("\t\t\t<column name=\""+i.getNombreEnTabla()+"\" length=\""+i.getTam().intValue()+"\"/>");
			}else if(i.getTipoParaHibernate().equals("date")||i.getTipoParaHibernate().equals("java.lang.Boolean")){
				lineas.add("\t\t\t<column name=\""+i.getNombreEnTabla()+"\"/>");
			}else{
				lineas.add("\t\t\t<column name=\""+i.getNombreEnTabla()+"\" precision=\""+i.getTamOrPrecision().intValue()+"\" " +
					"scale=\""+i.getScale().intValue()+"\"/>");
			}
			lineas.add("\t\t</property>");	
		}
		
		//Set
		if(tab.getSets()!=null)
		for(SetVO i:tab.getSets()){
			lineas.add("\t\t<set name=\""+i.getNombreEnApp()+"\" table=\""+i.getRef().getNombreTabla()+"\" inverse=\"true\" lazy=\"false\" fetch=\"select\" schema=\""+tab.getNombreEsquema()+"\" " +
					"cascade=\"delete\">");
			lineas.add("\t\t\t<key>");
			lineas.add("\t\t\t\t<column name=\""+i.getFkVO().getNombreEnTabla()+"\" precision=\""+i.getFkVO().getTam().intValue()+"\" " +
					"scale=\""+i.getFkVO().getScale().intValue()+"\" not-null=\"true\"/>");
			lineas.add("\t\t\t</key>");
			lineas.add("\t\t\t<one-to-many class=\""+i.getTipoCompletoEnApp()+"\" />");
			lineas.add("\t\t</set>");
		}    
		
		//Final
	    lineas.add("\t</class>");
	    lineas.add("</hibernate-mapping>");

		return lineas;	  	
	}
	
	
	public static void addXml(String xml, List<String> lineas) throws IOException {		
		FileWriter output = new FileWriter(xml, true);
		BufferedWriter writer = new BufferedWriter(output);
		for (String i : lineas)
			writer.write(i + "\n");
		writer.flush();
	}
	
}
