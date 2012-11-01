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
package es.sotileza.plugin.handler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import java.util.LinkedList;
import es.sotileza.plugin.arquetipo.Arquetipo;
import es.sotileza.plugin.make.MakeDAO;
import es.sotileza.plugin.make.MakeDelegate;
import es.sotileza.plugin.make.MakeForm;
import es.sotileza.plugin.make.MakeHbm;
import es.sotileza.plugin.make.MakeMaestro;
import es.sotileza.plugin.make.MakeManager;
import es.sotileza.plugin.make.MakeVO;
import es.sotileza.plugin.service.Service;
import es.sotileza.plugin.utils.Utilidades;
import es.sotileza.plugin.vo.ArquetipoVO;
import es.sotileza.plugin.vo.TablaVO;


public class Generator extends AbstractHandler {
	
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);		
		IStructuredSelection selection = (IStructuredSelection) HandlerUtil.getActiveMenuSelection(event);
		
		if(MessageDialog.openConfirm(window.getShell(), "Confirmación",  "Está a punto de sotilizar está aplicación. Está seguro?")){
				
			Object[] element = selection.toArray();	
			String resultado = "";
			for(Object o:element){
				if (o instanceof IFile) {
					IFile f = (IFile) o;
					if(validaFichero(f)){	
						try{
							generadorFinal(f);
							resultado += "\nAplicación Sotilizada.";
						}catch(Exception e){
							resultado += "\nAplicación sotilizada con errores. "+e.getMessage();
						}
					}else{
						resultado += "\nLa aplicación no se ha podido sotilizar.";
					}
				}else{
					resultado += "\nFichero no sotilizable.";
				}
			}			
			MessageDialog.openInformation(window.getShell(), "Operación Finalizada",  resultado);
		}
		
		return null;
	}
	
	
	private boolean validaFichero(IFile f){
		try{			
			String test = f.getName();
			String[] name = test.split("\\.");
			if(name==null || name.length!=2) return false;
			else if(name[1].toLowerCase().equals("xml")) return true;
		}catch(Exception e){}
		return false;
	}	
	
	
	private File pasaAFile(InputStream in){
		File destino = new File("plugins/org.sotileza.plugin.resources/GeneratorAuxiliarXML.xml");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			BufferedWriter bw = new BufferedWriter(new FileWriter(destino));
			String linea = "";
			while ((linea = br.readLine())!=null) {
					bw.write(linea+"\n\r"); 
			}
			bw.close();
			br.close();
		}catch(Exception e){
			System.out.println("Error leyendo el fichero.");
		}
		return destino;
	}
	
	
	private void generadorFinal(IFile f) throws Exception {
		Service serv;

			File file = pasaAFile(f.getContents());
			serv = new Service(file);
			ArquetipoVO arq = serv.devuelveArquetipo();
			System.out.println(arq);
			Arquetipo.generaArquetipo(arq);
			
			String dir = arq.getRuta()+"\\"+arq.getProyecto();
			String[] pakete = arq.getPakete().split("\\.");
			String auxiliar = dir+"\\src\\main\\java";
			for(int i=0; i<pakete.length; i++)
				auxiliar += "\\" + pakete[i];
			
			serv = new Service(file);
			List<TablaVO> tablas = serv.devuelveTablas();
			//for(TablaVO tab:tablas)
				//System.out.println( tab + "\n\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
			 
			
			List<String> hbms = new LinkedList<String>();
			for(String i:arq.getHbms())	{
				Utilidades.addXml(dir+"\\src\\main\\resources\\beans\\business\\dao-beans.xml", MakeDAO.makeConfigCommons(i));
				Utilidades.addXml(dir+"\\src\\main\\resources\\beans\\business\\manager-beans.xml", MakeManager.makeConfigCommons(i));
				Utilidades.addXml(dir+"\\src\\main\\resources\\beans\\web\\delegate-beans.xml", MakeDelegate.makeConfigCommons(i));
				hbms.add("<value>hibernate/"+i+".hbm.xml</value>");
			}
			
			
			//generacion de los hbms
			for(TablaVO tab:tablas){
				hbms.add("<value>hibernate/"+tab.getVoClase()+".hbm.xml</value>");
				Utilidades.paintFile(dir+"\\src\\main\\resources\\hibernate\\"+tab.getVoClase()+".hbm.xml", MakeHbm.makeHbms(tab));
			}
			arq.setHbms(hbms);
			
			//generacion de los VOs
			for(TablaVO tab:tablas){
				Utilidades.paintFile(auxiliar+"\\business\\vo\\"+tab.getVoClase()+".java", MakeVO.makeVO(tab));
			}
			
			//generacion de los DAOs
			for(TablaVO tab:tablas){
				Utilidades.paintFile(auxiliar+"\\business\\dao\\"+tab.getDaoClase()+".java", MakeDAO.makeDAO(tab));
				Utilidades.paintFile(auxiliar+"\\business\\dao\\"+tab.getDaoImplClase()+".java", MakeDAO.makeDAOImpl(tab));
				Utilidades.addXml(dir+"\\src\\main\\resources\\beans\\business\\dao-beans.xml", MakeDAO.makeConfig(tab));
			}
			Utilidades.addXml(dir+"\\src\\main\\resources\\beans\\business\\dao-beans.xml", "</beans>");
			
			//generacion de los Managers
			for(TablaVO tab:tablas){
				if(!tab.getNivel().equals("dao")){
					Utilidades.paintFile(auxiliar+"\\business\\manager\\"+tab.getManagerClase()+".java", MakeManager.makeManager(tab));
					Utilidades.addXml(dir+"\\src\\main\\resources\\beans\\business\\manager-beans.xml", MakeManager.makeConfig(tab));
				}
			}
			Utilidades.addXml(dir+"\\src\\main\\resources\\beans\\business\\manager-beans.xml", "</beans>");
			
			
			//generacion de los Delegates
			for(TablaVO tab:tablas){
				if(!tab.getNivel().equals("dao") && !tab.getNivel().equals("manager")){
					Utilidades.paintFile(auxiliar+"\\web\\delegate\\"+tab.getDelegateClase()+".java", MakeDelegate.makeDelegate(tab));
					Utilidades.addXml(dir+"\\src\\main\\resources\\beans\\web\\delegate-beans.xml", MakeDelegate.makeConfig(tab));
				}
			}
			Utilidades.addXml(dir+"\\src\\main\\resources\\beans\\web\\delegate-beans.xml", "</beans>");
			
			
			//generacion de los Forms
			List<String> forms = new LinkedList<String>();
			for(TablaVO tab:tablas){
				if(!tab.getNivel().equals("dao") && !tab.getNivel().equals("manager")){
					Utilidades.paintFile(auxiliar+"\\web\\form\\"+tab.getFormClase()+".java", MakeForm.makeForm(tab));
					forms.add( MakeForm.makeConfig(tab));
				}
			}
			arq.setForms(forms);
			
			
			//generacion de los Maestros
			List<String> mapActions = new LinkedList<String>();
			List<String> mapNavig = new LinkedList<String>();
			List<String> mapReq = new LinkedList<String>();
			List<String> managerDAOS = new LinkedList<String>();
			List<String> managerGetSet = new LinkedList<String>();
			List<String> managerMetodos = new LinkedList<String>();
			List<String> delegateMetodos = new LinkedList<String>();
			List<String> dwrMetodos = new LinkedList<String>();
			List<String> menuMaestros = new LinkedList<String>();
			for(TablaVO tab:tablas){
				if(tab.getNivel().equals("maestro")){
					Utilidades.paintFile(auxiliar+"\\web\\action\\"+tab.getActionClase()+".java", MakeMaestro.makeAction(tab));
					mapActions.add(MakeMaestro.makeMapeos(tab));
					Utilidades.addXml(dir+"\\src\\main\\resources\\beans\\web\\action-beans.xml", MakeMaestro.makeActionBeans(tab));
					Utilidades.addXml(dir+"\\src\\main\\webapp\\WEB-INF\\tiles-defs.xml", MakeMaestro.makeTiles(tab));
					mapNavig.add(MakeMaestro.makeNavigationKeys(tab));
					mapReq.add(MakeMaestro.makeRequestKeys(tab));
					managerDAOS.addAll( MakeMaestro.makeManagerDAOS(tab) );
					managerGetSet.addAll( MakeMaestro.makeManagerGetSet(tab) );
					managerMetodos.addAll( MakeMaestro.makeManagerMetodos(tab) );
					delegateMetodos.addAll( MakeMaestro.makeDelegateMetodos(tab) );
					dwrMetodos.addAll(MakeMaestro.makeDwr(tab));
					menuMaestros.add("\n\t\t\t\t\t\t<li><html:link action=\""+tab.getActionClase()+".do?method=inicio\">"+tab.getVoMetodo()+"</html:link></li>");
					Arquetipo.generaDirectorio(dir+"\\src\\main\\webapp\\pages\\tiles\\cuerpos\\maestros\\"+tab.getVoVariable().toLowerCase());
					Utilidades.paintFile(dir+"\\src\\main\\webapp\\pages\\tiles\\cuerpos\\maestros\\"+tab.getVoVariable().toLowerCase()+"\\consulta.jsp", MakeMaestro.makeJsp(tab));
				}
			}
			arq.setMenuMaestros(menuMaestros);
			arq.setMapAction(mapActions);
			Utilidades.addXml(dir+"\\src\\main\\resources\\beans\\web\\action-beans.xml", "</beans>");
			Utilidades.addXml(dir+"\\src\\main\\webapp\\WEB-INF\\tiles-defs.xml", "</tiles-definitions>");
			Utilidades.paintFile(auxiliar+"\\utils\\RequestKeys.java", MakeMaestro.makeRequestKeysClase(mapReq, arq));
			Utilidades.paintFile(auxiliar+"\\utils\\NavigationKeys.java", MakeMaestro.makeNavigationKeysClase(mapNavig, arq));
			//Utilidades.paintFile(auxiliar+"\\utils\\ContextKeys.java", MakeMaestro.makeContextKeys(arq));
			//Utilidades.paintMaestroManager(auxiliar+"\\business\\manager\\MaestrosManager.java", managerDAOS, managerGetSet, managerMetodos, arq);
			//Utilidades.paintMaestroDelegate(auxiliar+"\\web\\delegate\\MaestrosDelegate.java", delegateMetodos, arq);
			Utilidades.paintMaestroDWR(auxiliar+"\\web\\dwr\\MaestrosController.java", dwrMetodos, arq, tablas);
			
			//Añadir las clases java fijas
			Arquetipo.generaArquetipoFinal(arq);
			//repaso de algunos ficheros
			Arquetipo.modificaFichero( arq, dir+"\\src\\main\\resources\\beans\\business\\manager-beans.xml");
			Arquetipo.modificaFichero( arq, dir+"\\src\\main\\webapp\\pages\\tiles\\appCabecera.jsp");
			Arquetipo.modificaFichero( arq, dir+"\\src\\main\\webapp\\WEB-INF\\struts-config.xml");
			Arquetipo.modificaFichero( arq, dir+"\\src\\main\\resources\\beans\\business\\datasource-beans.xml");
			Arquetipo.modificaFichero( arq, dir+"\\despliegues\\desarrollo\\datasource-beans.xml");
			Arquetipo.modificaFichero( arq, dir+"\\despliegues\\preproduccion\\datasource-beans.xml");
			Arquetipo.modificaFichero( arq, dir+"\\despliegues\\produccion\\datasource-beans.xml");
			Arquetipo.modificaFichero( arq, dir+"\\src\\test\\resources\\beans\\business\\datasource-test-beans.xml");
			
			
			
			//Para actualizar el resto de ficheros
			//Arquetipo.GeneraDataSources(arq);	
			System.out.println("FIN");

	}
}

