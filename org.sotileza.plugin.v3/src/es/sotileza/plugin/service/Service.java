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
package es.sotileza.plugin.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import es.sotileza.plugin.utils.Utilidades;
import es.sotileza.plugin.vo.ArquetipoVO;
import es.sotileza.plugin.vo.CampoVO;
import es.sotileza.plugin.vo.FkVO;
import es.sotileza.plugin.vo.SetVO;
import es.sotileza.plugin.vo.TablaVO;

public class Service {

	private Connection cn;
	private InputStream fichero;
	
	public Connection getCn() {
		return cn;
	}
	public void setCn(Connection cn) {
		this.cn = cn;
	}
	public InputStream getFichero() {
		return fichero;
	}
	public void setFichero(InputStream fichero) {
		this.fichero = fichero;
	}
	
	
	public Service(InputStream fichero) throws Exception{
		this.fichero = fichero;
	}
	public Service(File fichero) throws Exception{
		this.fichero = new FileInputStream (fichero);
	}
	
	private Connection setConnection(String u, String p, String h) throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		return DriverManager.getConnection(h, u, p);
	}


	
	public ArquetipoVO devuelveArquetipo(){
		ArquetipoVO arq = new ArquetipoVO();
		arq.setHbms(new LinkedList<String>());
		try{
			SAXBuilder builder = new SAXBuilder(false);
			Document doc = builder.build(this.fichero);
			List<Element> child = doc.getRootElement().getChildren();
			for ( Element i: child ){
				if ( i.getName().equals("workspace") )	arq.setRuta(i.getValue());
				else if ( i.getName().equals("nombreApp") )	arq.setProyecto(i.getValue());
				else if ( i.getName().equals("pakete") )	arq.setPakete(i.getValue());
				else if ( i.getName().equals("ds") )	arq.setDs(i.getValue());
				arq.setHbms(new LinkedList<String>());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return arq;
	}
	
	
	public List<TablaVO> devuelveTablas(){
		List<TablaVO> tablas = new ArrayList<TablaVO>();
		try{
			SAXBuilder builder = new SAXBuilder(false);
			Document doc = builder.build(this.fichero);
			List<Element> child = doc.getRootElement().getChildren();
			String host="", usuario="", password="", pakete="";
			for ( Element i: child ){
				if ( i.getName().equals("host") )	host = i.getValue();
				else if ( i.getName().equals("user") )	usuario = i.getValue();
				else if ( i.getName().equals("pass") )	password = i.getValue();
				else if ( i.getName().equals("pakete") )	pakete = i.getValue();
			}
			this.cn = this.setConnection(usuario, password, host);
			
			for ( Element i: child ){
				if ( i.getName().equals("tablas") ){
					
					List<Element> children = i.getChildren("tabla");
					for (Element j: children){
						TablaVO tabla = new TablaVO();
						tabla.setPakete( pakete );
												
						tabla.setNombreTabla( j.getAttributeValue("nombre") );
						tabla.setNombreSecuencia( j.getAttributeValue("secuencia") );
						tabla.setNombreEsquema( j.getAttributeValue("esquema") );
						
						List<Element> children2 = j.getChildren();
						for (Element k: children2){
							if ( k.getName().equals("objeto") )	tabla.setNombreApp( k.getValue() );
							else if ( k.getName().equals("nivel") )	tabla.setNivel( k.getValue().toLowerCase() );
							else if ( k.getName().equals("set") )	tabla.setHaySets( k.getValue() );
							else if ( k.getName().equals("pakete") )	tabla.setPakete( k.getValue() );
							else if ( k.getName().equals("vo") )	tabla.setNombreVO( k.getValue() );
						}
						tablas.add(generaTabla(tabla));
					}
				}
			}
			tablas = repasarFKs(tablas);		
			tablas = repasarSets(tablas);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return tablas;
	}
	
	
	private TablaVO generaTabla(TablaVO tab){
		try {
			//Un objeto ResultSet, almacena los datos de resultados de una consulta
			//--select * from all_tables where table_name = 'VPL_PRINCIPAL' 
			/*
			 * Saca los campos.
			 * select COLUMN_NAME, DATA_TYPE, DATA_LENGTH from all_tab_columns where table_name ='VPL_PRINCIPAL'
			 * 
			 * Saca las constraints
			 * SELECT CONSTRAINT_NAME, CONSTRAINT_TYPE, R_CONSTRAINT_NAME FROM all_constraints WHERE TABLE_NAME='VPL_PRINCIPAL';
			 * 
			 * select TABLE_NAME, COLUMN_NAME FROM USER_CONS_COLUMNS where CONSTRAINT_NAME='VPL_FK_1';
			 * select TABLE_NAME, COLUMN_NAME FROM USER_CONS_COLUMNS where CONSTRAINT_NAME='VPL_PKEY_1';
			 * 
			 * select TABLE_NAME, COLUMN_NAME FROM USER_CONS_COLUMNS where CONSTRAINT_NAME='VPL_PKEY_2';
			 * 
			 * 
			Type Code   Type Description  Acts On Level 
			C   Check on a table Column 
			O   Read Only on a view Object 
			P   Primary Key Object 
			R   Referential AKA Foreign Key Column 
			U   Unique Key Column 
			V   Check Option on a view Object 
			*/
			
			Statement smt = this.cn.createStatement();
			ResultSet tabla = smt.executeQuery("select COLUMN_NAME, DATA_TYPE, DATA_LENGTH, DATA_SCALE, DATA_PRECISION from all_tab_columns " +
					"where table_name ='"+tab.getNombreTabla()+"' AND OWNER='"+tab.getNombreEsquema()+"'");
			List<CampoVO> campos = new ArrayList<CampoVO>();
			while(tabla.next()){
				CampoVO c = new CampoVO();
				c.setNombreEnTabla(tabla.getString(1).toLowerCase());
				c.setTipoEnTabla(tabla.getString(2));
				c.setTam(tabla.getInt(3));
				c.setScale(tabla.getInt(4));
				c.setPrecision(tabla.getInt(5));
				
				String aux = "";
				for(int i=0; i<c.getNombreEnTabla().length(); i++){
					if(c.getNombreEnTabla().charAt(i)=='_'){
						i++;
						if(i!=c.getNombreEnTabla().length())
							aux += (c.getNombreEnTabla().charAt(i)+"").toUpperCase();
					}else{
						aux += c.getNombreEnTabla().charAt(i);
					}
				}
				c.setNombreEnApp(aux);
				
				c.setMetodoEnApp( c.getNombreEnApp().substring(0,1).toUpperCase() + c.getNombreEnApp().substring(1) );
				c.setTipoEnApp( Utilidades.calculaTipo( c.getTipoEnTabla(), c.getTamOrPrecision(), c.getScale()));
				c.setTipoParaHibernate( Utilidades.calculaTipoHibernate( c.getTipoEnTabla(), c.getTamOrPrecision(), c.getScale()));
				c.setTipoEnForm( Utilidades.calculaTipoForm( c.getTipoEnTabla(), c.getTamOrPrecision(), c.getScale() ) );
				campos.add(c);
			}
			tabla.close();
			
			
			tabla = smt.executeQuery("SELECT CONSTRAINT_NAME, CONSTRAINT_TYPE, R_CONSTRAINT_NAME FROM all_constraints " +
					"WHERE TABLE_NAME='"+tab.getNombreTabla()+"' AND OWNER='"+tab.getNombreEsquema()+"'");
			List<CampoVO> pks = new ArrayList<CampoVO>();
			List<FkVO> fks = new ArrayList<FkVO>();
			while(tabla.next()){
				Statement smt2 = this.cn.createStatement();
				ResultSet res2 = smt2.executeQuery("select TABLE_NAME, COLUMN_NAME FROM USER_CONS_COLUMNS where CONSTRAINT_NAME='"+tabla.getString(1)+"'");

				if(tabla.getString(2).equals("P")){	//	PK
					if(res2.next())
						for(int i=0; i<campos.size(); i++)
							if(campos.get(i).getNombreEnTabla().equals(res2.getString(2).toLowerCase())){
								pks.add( campos.get(i) );
								campos.remove(i);
							}
				}else if(tabla.getString(2).equals("R")){	// FK
					if(res2.next())
						for(int i=0; i<campos.size(); i++)
							if(campos.get(i).getNombreEnTabla().equals(res2.getString(2).toLowerCase())){
								fks.add( calculaFK(tabla, res2, campos.get(i)) );
								campos.remove(i);
							}
				}
				res2.close();
				smt2.close();
			}
			tabla.close();
			smt.close();
			
			tab.setCampos(campos);
			tab.setPks(pks);
			tab.setFks(fks);
			
		}catch(Exception e){ 
			e.printStackTrace();
			System.out.println(e); 
		}
		return tab;
	}
	
	
	private List<TablaVO> repasarFKs(List<TablaVO> tablas){
		//repasar las fk
		for(int i=0; i<tablas.size(); i++){
			for(int j=0; j<tablas.get(i).getFks().size(); j++){
				for(int k=0; k<tablas.size(); k++){
					if(tablas.get(k).getNombreTabla().equals( tablas.get(i).getFks().get(j).getTablaAjena() )){
						tablas.get(i).getFks().get(j).setTipoEnApp( tablas.get(k).getVoClase() );
						tablas.get(i).getFks().get(j).setTipoCompletoEnApp( tablas.get(k).getVoPaketeCompleto());
						tablas.get(i).getFks().get(j).setNombreEnApp( tablas.get(k).getVoVariable() );
						tablas.get(i).getFks().get(j).setMetodoEnApp( tablas.get(k).getVoMetodo() );
						tablas.get(i).getFks().get(j).setVoTablaAjena(tablas.get(k));
					}
				}
			}
		}
		return tablas;
	}
	
	
	private List<TablaVO> repasarSets(List<TablaVO> tablas){
		//repasar los set
		for(int i=0; i<tablas.size(); i++){
			if(tablas.get(i).getHaySets()!=null && tablas.get(i).getHaySets().equals("true")){
				List<SetVO> sets = new ArrayList<SetVO>();
				for(int j=0; j<tablas.size(); j++){
					if(i!=j){
						for(int k=0; k<tablas.get(j).getFks().size(); k++){
							if(tablas.get(j).getFks().get(k).getTablaAjena().equals(tablas.get(i).getNombreTabla())){
								SetVO xxx = new SetVO();
								xxx.setMetodoEnApp(Utilidades.sacaPlural(tablas.get(j).getVoMetodo()) );
								xxx.setNombreEnApp(Utilidades.sacaPlural(tablas.get(j).getVoVariable()));
								xxx.setTipoCompletoEnApp( tablas.get(j).getVoPaketeCompleto() );
								xxx.setTipoEnApp( tablas.get(j).getVoClase() );
								xxx.setNombreEsquema( tablas.get(j).getNombreEsquema() );
								xxx.setRef( tablas.get(j) );
								xxx.setFkVO( tablas.get(j).getFks().get(k) );
								sets.add(xxx);
							}
						}
					}
				}
				tablas.get(i).setSets(sets);
			}
		}
		return tablas;
	}
	
	
	
	private FkVO calculaFK(ResultSet tabla, ResultSet res2, CampoVO campo) throws Exception{
		FkVO f = new FkVO();
		f.setNombreEnTabla(res2.getString(2).toLowerCase());
		f.setTipoEnTabla( campo.getTipoEnTabla() );
		f.setTam( campo.getTam() );
		f.setNombreEnApp( campo.getNombreEnApp() );
		f.setMetodoEnApp( campo.getMetodoEnApp() );
		f.setScale( campo.getScale() );
		f.setPrecision( campo.getPrecision() );
		Statement smt3 = this.cn.createStatement();
		ResultSet res3 = smt3.executeQuery("select TABLE_NAME, COLUMN_NAME FROM USER_CONS_COLUMNS where CONSTRAINT_NAME='"+tabla.getString(3)+"'");
		while(res3.next()){
			f.setTablaAjena( res3.getString(1) );
			f.setNombreEnTablaAjena( res3.getString(2).toLowerCase() );
		}
		res3.close();
		smt3.close();
		return f;
	}
	
}
