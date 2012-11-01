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
package es.sotileza.plugin.arquetipo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.channels.FileChannel;
import java.util.LinkedList;
import java.util.List;

import es.sotileza.plugin.vo.ArquetipoVO;

public class Arquetipo {

	
	
	public static void generaArquetipoFinal(ArquetipoVO arq) {
		String ruta = arq.getRuta()+"\\"+arq.getProyecto();
		String[] pakete = arq.getPakete().split("\\.");
		String auxiliar = ruta+"\\src\\main\\java";
		for(int i=0; i<pakete.length; i++)
			auxiliar += "\\" + pakete[i];
		
		//File dir = new File(ruta);	
		File raiz = new File("src/resourcesClases");
		if(raiz.listFiles()==null)
			//TODO quitar 
			raiz = new File("plugins/org.sotileza.plugin.resources/resourcesClases");
			//raiz = new File("C:/Documents and Settings/Administrador/Escritorio/eclipse/plugins/org.sotileza.plugin.resources/resourcesClases");
		
		for(File i:raiz.listFiles()){
			if(i.isDirectory()){
				File dir2 = new File(auxiliar+"\\"+i.getName());
				recorreCarpetasFinal(arq, i, dir2);
			}else{
				generaFichero(arq, i, auxiliar+"\\"+i.getName()+"a");
			}
		}	
	}
	
	
	private static void recorreCarpetasFinal(ArquetipoVO arq, File orig, File dest){	
		for(File i:orig.listFiles()){
			if(i.isDirectory()){
				File dir = new File(dest.getAbsolutePath()+"\\"+i.getName());
				recorreCarpetasFinal(arq, i, dir);				
			}else{
				generaFichero(arq, i, dest.getAbsolutePath()+"\\"+i.getName()+"a");
			}
		}
	}
	
	
	
	
	
	
	public static void generaArquetipo(ArquetipoVO arq) {
		File dir = new File(arq.getRuta()+"\\"+arq.getProyecto());
		dir.mkdir();
		System.out.println("Generado directorio: "+dir.getAbsolutePath());
		
		File raiz = new File("src/resources");
		if(raiz.listFiles()==null)
			//TODO quitar 
			raiz = new File("plugins/org.sotileza.plugin.resources/resources");
			//raiz = new File("C:/Documents and Settings/Administrador/Escritorio/eclipse/plugins/org.sotileza.plugin.resources/resources");
		
		System.out.println("Generado directorio: "+raiz.getAbsolutePath());
		for(File i:raiz.listFiles()){
			if(i.isDirectory()){
				File dir2 = new File(dir.getAbsolutePath()+"\\"+i.getName());
				dir2.mkdir();
				System.out.println("Generado directorio: "+dir2.getAbsolutePath());
				recorreCarpetas(arq, i, dir2);
			}else{
				if(i.getName().toLowerCase().contains(".png") || i.getName().toLowerCase().contains(".ico") ||  
						i.getName().toLowerCase().contains(".gif") || i.getName().toLowerCase().contains(".jpg") )
					copia(i.getAbsolutePath(), dir.getAbsolutePath()+"\\"+i.getName());
				else
					generaFichero(arq, i, dir.getAbsolutePath()+"\\"+i.getName());
			}
		}
		
		
		String[] pakete = arq.getPakete().split("\\.");
		String auxiliar = dir.getAbsolutePath()+"\\src\\main\\java";
		for(int i=0; i<pakete.length; i++){
			auxiliar += "\\" + pakete[i];
			generaDirectorio(auxiliar);	
		}
		generaDirectorio(auxiliar+"\\business");
		generaDirectorio(auxiliar+"\\business\\dao");
		generaDirectorio(auxiliar+"\\business\\manager");
		generaDirectorio(auxiliar+"\\business\\vo");
		generaDirectorio(auxiliar+"\\utils");
		generaDirectorio(auxiliar+"\\validator");
		generaDirectorio(auxiliar+"\\web");
		generaDirectorio(auxiliar+"\\web\\action");
		generaDirectorio(auxiliar+"\\web\\delegate");
		generaDirectorio(auxiliar+"\\web\\dwr");
		generaDirectorio(auxiliar+"\\web\\filter");
		generaDirectorio(auxiliar+"\\web\\form");
		generaDirectorio(auxiliar+"\\web\\listener");
	}
	
	
	
		
	private static void recorreCarpetas(ArquetipoVO arq, File orig, File dest){	
		for(File i:orig.listFiles()){
			if(i.isDirectory()){
				File dir = new File(dest.getAbsolutePath()+"\\"+i.getName());
				dir.mkdir();
				System.out.println("Generado directorio: "+dir.getAbsolutePath());
				recorreCarpetas(arq, i, dir);				
			}else{
				if(i.getName().toLowerCase().contains(".png") || i.getName().toLowerCase().contains(".ico") ||  
						i.getName().toLowerCase().contains(".gif") || i.getName().toLowerCase().contains(".jpg") )
					copia(i.getAbsolutePath(), dest.getAbsolutePath()+"\\"+i.getName());
				else
					generaFichero(arq, i, dest.getAbsolutePath()+"\\"+i.getName());
			}
		}
	}	
		
	
	
	public static void generaDirectorio(String dir){
		File directorio = new File(dir);
		directorio.mkdir();
		System.out.println("Generado directorio: "+dir);
	}

	
	public static void generaFichero(ArquetipoVO arq, File origen, String fichFinal){
		File destino = new File(fichFinal);
		try {
			BufferedReader br = new BufferedReader(new FileReader(origen));
			BufferedWriter bw = new BufferedWriter(new FileWriter(destino));
			
			String linea = "";
			while ((linea = br.readLine())!=null) {
				linea = linea.replaceAll("Var_NOMBREAPP", arq.getProyecto());
				linea = linea.replaceAll("Var_NOMBREDS", arq.getDs());
				linea = linea.replaceAll("Var_NOMBREPAKETE", arq.getPakete());			
				bw.write(linea+"\n"); 
			}
			
			bw.close();
			br.close();
			
			System.out.println("Generado fichero: "+fichFinal);
		}catch(Exception e){
			System.out.println("Error generando fichero: "+fichFinal);
			e.printStackTrace();
		}
	}
	
	
	
	
	public static void modificaFichero(ArquetipoVO arq, String fichFinal){
		File destino = new File(fichFinal);
		try {
			BufferedReader br = new BufferedReader(new FileReader(destino));
			
			List<String> linea = new LinkedList<String>();
			String str = "";
			while ((str = br.readLine())!=null) {
				boolean encontrado = false;
				if(str.contains("VAR_MANAGERBEANSMAESTRO") && arq.getManagerBeansMaestro()!=null && arq.getManagerBeansMaestro().size()>0){
					encontrado = true;
					for(String i:arq.getManagerBeansMaestro())	linea.add(i);
				}else if(str.contains("VAR_MANAGERBEANSMAESTRO") ){
					encontrado = true;
					linea.add("");
				}
				if(str.contains("VAR_MENUMAESTROS") && arq.getMenuMaestros()!=null && arq.getMenuMaestros().size()>0){
					encontrado = true;
					for(String i:arq.getMenuMaestros())	linea.add(i);
				}else if(str.contains("VAR_MENUMAESTROS") ){
					encontrado = true;
					linea.add("");
				}
				if(str.contains("VAR_LISTAFORMS") && arq.getForms()!=null && arq.getForms().size()>0){
					encontrado = true;
					for(String i:arq.getForms())	linea.add("\n\t\t"+i);
				}
				if(str.contains("VAR_MAPEOSMAESTROS") && arq.getMapAction()!=null && arq.getMapAction().size()>0){
					encontrado = true;
					for(String i:arq.getMapAction())	linea.add(i);
				}else if(str.contains("VAR_MAPEOSMAESTROS") ){
					encontrado = true;
					linea.add("");
				}
				if(str.contains("VAR_LISTAMAPEOS") && arq.getHbms()!=null && arq.getHbms().size()>0){
					encontrado = true;
					for(String i:arq.getHbms())	linea.add("\n\t\t\t\t"+i);
				}
				if(!encontrado)
					linea.add(str);
			}
			br.close();
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(destino));
			for(String i:linea)
				bw.write(i+"\n"); 
			bw.close();
			
			System.out.println("Generado fichero: "+fichFinal);
		}catch(Exception e){
			System.out.println("Error generando fichero: "+fichFinal);
			e.printStackTrace();
		}
	}
	
	
	
	public static void copia(String nombreFuente, String nombreDestino) {
		try {
			FileInputStream fis = new FileInputStream(nombreFuente);
		     FileOutputStream fos = new FileOutputStream(nombreDestino);
		     FileChannel canalFuente = fis.getChannel();
		     FileChannel canalDestino = fos.getChannel();
		     canalFuente.transferTo(0, canalFuente.size(), canalDestino);
		     fis.close();
		     fos.close();
		     System.out.println("Generada imagen: "+nombreDestino);
		} catch (Exception e) {
			System.out.println("Error copiando imagen: "+nombreDestino);
			e.printStackTrace();
		}
	} 
}
