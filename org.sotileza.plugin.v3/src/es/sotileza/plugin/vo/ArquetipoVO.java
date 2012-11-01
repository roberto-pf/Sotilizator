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
package es.sotileza.plugin.vo;

import java.util.List;

public class ArquetipoVO {
	
	private String ruta;
	private String proyecto;
	private String pakete;
	private String ldap;
	private String ds;
	
	private List<String> hbms;
	private List<String> roles;
	private List<String> forms;
	private List<String> mapAction;
	private List<String> managerBeansMaestro;
	private List<String> menuMaestros;
	
	
	public List<String> getRoles() {
		return roles;
	}
	public String getRolesStr() {
		String linea = ""; 
		if(this.getRoles()!=null){
			for(String i:this.getRoles())
				linea += "ROLE_"+i+",";
			linea = linea.substring(0, linea.length()-1);
		}
		return linea;
	}
	
	
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	public List<String> getHbms() {
		return hbms;
	}
	public void setHbms(List<String> hbms) {
		this.hbms = hbms;
	}
	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	public String getProyecto() {
		return proyecto;
	}
	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}
	public String getPakete() {
		return pakete;
	}
	public void setPakete(String pakete) {
		this.pakete = pakete;
	}
	public String getLdap() {
		return ldap;
	}
	public void setLdap(String ldap) {
		this.ldap = ldap;
	}
	public String getDs() {
		return ds;
	}
	public void setDs(String ds) {
		this.ds = ds;
	}
	public List<String> getForms() {
		return forms;
	}
	public void setForms(List<String> forms) {
		this.forms = forms;
	}
	
	public List<String> getMapAction() {
		return mapAction;
	}
	public void setMapAction(List<String> mapAction) {
		this.mapAction = mapAction;
	}
	public List<String> getManagerBeansMaestro() {
		return managerBeansMaestro;
	}
	public void setManagerBeansMaestro(List<String> managerBeansMaestro) {
		this.managerBeansMaestro = managerBeansMaestro;
	}
	public List<String> getMenuMaestros() {
		return menuMaestros;
	}
	public void setMenuMaestros(List<String> menuMaestros) {
		this.menuMaestros = menuMaestros;
	}
	@Override
	public String toString() {
		return "ArquetipoVO [ldap=" + ldap + ", pakete=" + pakete
				+ ", proyecto=" + proyecto + ", ruta=" + ruta + "]";
	}
}
