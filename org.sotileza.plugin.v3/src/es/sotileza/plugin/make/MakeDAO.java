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
import es.sotileza.plugin.vo.CampoVO;
import es.sotileza.plugin.vo.FkVO;
import es.sotileza.plugin.vo.TablaVO;

public class MakeDAO {

	public static List<String> makeDAO(TablaVO tab){
		List<String> lineas = new LinkedList<String>();
		
		lineas.add("package "+tab.getDaoPakete()+";\n\r" );
		
		lineas.add( "import java.util.List;" );
		lineas.add( "import "+tab.getVoPaketeCompleto()+";" );
		lineas.add( "\n\r" );
		
		lineas.add("/**\n * Interface DAO - "+tab.getDaoClase()+"\n */");
		lineas.add( "public interface "+tab.getDaoClase()+" {\n\r" );
		
		String tipoDato = tab.getVoClase();
		String datoMetodo = tab.getVoMetodo();
		String datoVariable = tab.getVoVariable();
		
		lineas.add(Utilidades.javadocMethod(Constantes.METODO_GET, tipoDato, datoVariable));
		lineas.add("\tpublic "+tipoDato+" get"+datoMetodo+"("+tab.getPks().get(0).getTipoEnApp()+" id);\n\r");
		
		lineas.add(Utilidades.javadocMethod(Constantes.METODO_NEW, tipoDato, datoVariable));
		lineas.add("\tpublic "+tab.getPks().get(0).getTipoEnApp()+" new"+datoMetodo+"("+tipoDato+" "+datoVariable+");\n\r");

		lineas.add(Utilidades.javadocMethod(Constantes.METODO_EDIT, tipoDato, datoVariable));
		lineas.add("\tpublic void edit"+datoMetodo+"("+tipoDato+" "+datoVariable+");\n\r");

		lineas.add(Utilidades.javadocMethod(Constantes.METODO_DELETE, tipoDato, datoVariable));
		lineas.add("\tpublic void delete"+datoMetodo+"("+tab.getPks().get(0).getTipoEnApp()+" id);\n\r");
		
		lineas.add(Utilidades.javadocMethod(Constantes.METODO_FIND, tipoDato, datoVariable));
		lineas.add("\tpublic List<"+tipoDato+"> find"+datoMetodo+"("+tipoDato+" "+datoVariable+");\n\r");
		
		lineas.add( "}");
		return lineas;
	}
	
	
	public static List<String> makeDAOImpl(TablaVO tab){
		List<String> lineas = new LinkedList<String>();
		
		lineas.add("package "+tab.getDaoPakete()+";\n\r" );
		
		lineas.add( "import java.util.List;" );
		lineas.add( "import org.apache.commons.logging.Log;" );
		lineas.add( "import org.apache.commons.logging.LogFactory;" );
		lineas.add( "import org.apache.commons.lang.StringUtils;" );
		lineas.add( "import org.hibernate.Criteria;" );
		lineas.add( "import org.hibernate.criterion.Criterion;" );
		lineas.add( "import org.hibernate.criterion.MatchMode;" );
		lineas.add( "import org.hibernate.criterion.Restrictions;" );
		lineas.add( "import org.springframework.orm.hibernate3.support.HibernateDaoSupport;" );
		lineas.add( "import "+tab.getVoPaketeCompleto()+";" );
		lineas.add( "\n\r" );
		
		String claseDAO = tab.getDaoClase();
		String tipoDato = tab.getVoClase();
		String datoMetodo = tab.getVoMetodo();
		String datoVariable = tab.getVoVariable();
		
		
		lineas.add("/**\n * Clase "+claseDAO+"Impl - Implementación del interface "+claseDAO+" \n */");
		lineas.add( "public class "+claseDAO+"Impl extends HibernateDaoSupport implements "+claseDAO+"{\n\r" );
		
		lineas.add("\tprivate static Log log = LogFactory.getFactory().getInstance("+claseDAO+"Impl.class);");
		lineas.add("\tpublic "+tipoDato+" get"+datoMetodo+"("+tab.getPks().get(0).getTipoEnApp()+" id){");
		lineas.add("\t\treturn ("+tipoDato+") getHibernateTemplate().get("+tipoDato+".class,id);");
		lineas.add("\t}\n\r");
		
		
		lineas.add("\tpublic "+tab.getPks().get(0).getTipoEnApp()+" new"+datoMetodo+"("+tipoDato+" "+datoVariable+"){");
		lineas.add("\t\t"+tab.getPks().get(0).getTipoEnApp()+" id = new "+tab.getPks().get(0).getTipoEnApp()+"(getHibernateTemplate().save("+datoVariable+").toString());");
		lineas.add("\t\treturn id;");
		lineas.add("\t}\n\r");

		
		lineas.add("\tpublic void edit"+datoMetodo+"("+tipoDato+" "+datoVariable+"){");
		lineas.add("\t\tgetHibernateTemplate().update("+datoVariable+");");
		lineas.add("\t}\n\r");

		
		lineas.add("\tpublic void delete"+datoMetodo+"("+tab.getPks().get(0).getTipoEnApp()+" id){");
		lineas.add("\t\tgetHibernateTemplate().delete(this.get"+datoMetodo+"(id));");
		lineas.add("\t}\n\r");
		
		
		lineas.add("\tpublic List<"+tipoDato+"> find"+datoMetodo+"("+tipoDato+" "+datoVariable+"){");
		lineas.add("\t\tCriteria criteria = getSession().createCriteria("+tipoDato+".class);");
		
		
		//PK
		if(tab.getPks()!=null)
		for(CampoVO i:tab.getPks()){
    		lineas.add("\t\tif( "+datoVariable+".get"+i.getMetodoEnApp()+"()!=null){");
    		lineas.add("\t\t\tcriteria.add(Restrictions.eq(\""+i.getNombreEnApp()+"\", "+datoVariable+".get"+i.getMetodoEnApp()+"()));");
    		lineas.add("\t\t}");
		}
		
		//FK
		if(tab.getFks()!=null)
		for(FkVO i:tab.getFks()){
    		lineas.add("\t\tif( "+datoVariable+".get"+i.getMetodoEnApp()+"()!=null &&" +
    				" "+datoVariable+".get"+i.getMetodoEnApp()+"().get"+i.getVoTablaAjena().getPks().get(0).getMetodoEnApp()+"()!=null){");
    		lineas.add("\t\t\tcriteria.add(Restrictions.eq(\""+i.getNombreEnApp()+"."+i.getVoTablaAjena().getPks().get(0).getNombreEnApp()+"\", " +
    				""+datoVariable+".get"+i.getMetodoEnApp()+"().get"+i.getVoTablaAjena().getPks().get(0).getMetodoEnApp()+"()));");
    		lineas.add("\t\t}");
		}
		
		//Campos
		if(tab.getCampos()!=null)
		for(CampoVO i:tab.getCampos()){
			if(i.getTipoEnApp().equals("String")){
	    		lineas.add("\t\tif(!StringUtils.isBlank( "+datoVariable+".get"+i.getMetodoEnApp()+"())) {");
	    		lineas.add("\t\t\tcriteria.add(Restrictions.like(\""+i.getNombreEnApp()+"\", "+datoVariable+".get"+i.getMetodoEnApp()+"(),MatchMode.ANYWHERE));");
	    		lineas.add("\t\t}");
	    	}else if(i.getTipoEnApp().equals("Integer") || i.getTipoEnApp().equals("Double") || i.getTipoEnApp().equals("Long")
	    			|| i.getTipoEnApp().equals("Date") ){
	    		lineas.add("\t\tif( "+datoVariable+".get"+i.getMetodoEnApp()+"()!=null){");
	    		lineas.add("\t\t\tcriteria.add(Restrictions.eq(\""+i.getNombreEnApp()+"\", "+datoVariable+".get"+i.getMetodoEnApp()+"()));");
	    		lineas.add("\t\t}");
	    	}else if(i.getTipoEnApp().equals("Boolean")){
	    		lineas.add("\t\tif( "+datoVariable+".get"+i.getMetodoEnApp()+"()!=null && "+datoVariable+".get"+i.getMetodoEnApp()+"() ){");
	    		lineas.add("\t\t\tcriteria.add(Restrictions.eq(\""+i.getNombreEnApp()+"\", "+datoVariable+".get"+i.getMetodoEnApp()+"()));");
	    		lineas.add("\t\t}");
	    	}	
		}
		
		
		lineas.add("\t\tList<"+tipoDato+"> rVal =  (List<"+tipoDato+">) criteria.list();");
		lineas.add("\t\treturn rVal;");
		lineas.add("\t}\n\r");
		
		lineas.add( "}");
		return lineas;
	}	

	
	public static List<String> makeConfig(TablaVO tab){
		List<String> lineas = new LinkedList<String>();
		
		String datoDAO = tab.getDaoVariable();
		String pak = tab.getDaoPakete()+"."+tab.getDaoImplClase();
		
		lineas.add("\n\t<!-- Para "+tab.getDaoClase()+" -->");
		lineas.add("\t<bean id=\""+datoDAO+"\" class=\""+pak+"\">");
		lineas.add("\t\t<property name=\"sessionFactory\"><ref bean=\"sessionFactory\"/></property>");
		lineas.add("\t</bean>");
		
		return lineas;	  	
	}
	
	public static List<String> makeConfigCommons(String clase){
		List<String> lineas = new LinkedList<String>();		
		lineas.add("\n\t<!-- Para "+clase+"DAO -->");
		lineas.add("\t<bean id=\""+clase.substring(0,1).toLowerCase()+clase.substring(1,clase.length())+"DAO\" class=\"es.satec.sotileza.commons.business.dao."+clase+"DAOImpl\">");
		lineas.add("\t\t<property name=\"sessionFactory\"><ref bean=\"sessionFactory\"/></property>");
		lineas.add("\t</bean>");
		return lineas;	  	
	}
	
}
