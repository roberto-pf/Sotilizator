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

import es.sotileza.plugin.utils.Constantes;

public class TablaVO {

	private List<CampoVO> campos;
	private List<CampoVO> pks;
	private List<FkVO> fks;
	private List<SetVO> sets;
	private String nombreSecuencia;
	private String nombreTabla;
	private String nombreApp;
	private String nombreEsquema;
	private String nivel;
	private String haySets;
	private String pakete;
	private String nombreVO;
	
	
	public List<CampoVO> getCampos() {
		return campos;
	}
	public void setCampos(List<CampoVO> campos) {
		this.campos = campos;
	}
	public List<CampoVO> getPks() {
		return pks;
	}
	public void setPks(List<CampoVO> pks) {
		this.pks = pks;
	}
	public List<SetVO> getSets() {
		return sets;
	}
	public void setSets(List<SetVO> sets) {
		this.sets = sets;
	}
	public String getNombreSecuencia() {
		return nombreSecuencia;
	}
	public void setNombreSecuencia(String nombreSecuencia) {
		this.nombreSecuencia = nombreSecuencia;
	}
	public String getNombreTabla() {
		return nombreTabla;
	}
	public void setNombreTabla(String nombreTabla) {
		this.nombreTabla = nombreTabla;
	}
	public String getNombreApp() {
		return nombreApp;
	}
	public void setNombreApp(String nombreApp) {
		this.nombreApp = nombreApp;
	}
	public String getNombreEsquema() {
		return nombreEsquema;
	}
	public void setNombreEsquema(String nombreEsquema) {
		this.nombreEsquema = nombreEsquema;
	}

	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	public String getPakete() {
		return pakete;
	}
	public void setPakete(String pakete) {
		this.pakete = pakete;
	}
	public List<FkVO> getFks() {
		return fks;
	}
	public void setFks(List<FkVO> fks) {
		this.fks = fks;
	}
	public String getHaySets() {
		return haySets;
	}
	public void setHaySets(String haySets) {
		this.haySets = haySets;
	}
	public String getNombreVO() {
		return nombreVO;
	}
	public void setNombreVO(String nombreVO) {
		this.nombreVO = nombreVO;
	}
	
	
	public String getVoClase() {
		if(nombreVO==null)
			return this.nombreApp+Constantes.CAPA_VO;
		return this.nombreVO;
	}
	public String getVoVariable() {
		return this.nombreApp.substring(0,1).toLowerCase()+this.nombreApp.substring(1);
	}
	public String getVoMetodo() {
		return this.nombreApp;
	}
	public String getVoPakete() {
		return this.pakete+".business.vo";
	}
	public String getVoPaketeCompleto() {
		return this.pakete+".business.vo."+this.getVoClase();
	}
	
	public String getDaoClase() {
		return this.nombreApp+Constantes.CAPA_DAO;
	}
	public String getDaoVariable() {
		return this.nombreApp.substring(0,1).toLowerCase()+this.getDaoClase().substring(1);
	}
	public String getDaoMetodo() {
		return this.getDaoClase();
	}
	public String getDaoPakete() {
		return this.pakete+".business.dao";
	}
	
	public String getDaoImplClase() {
		return this.getDaoClase()+"Impl";
	}
	public String getDaoImplVariable() {
		return this.getDaoVariable()+"Impl";
	}
	public String getDaoImplMetodo() {
		return this.getDaoMetodo()+"Impl";
	}
	public String getDaoImplPakete() {
		return this.getDaoPakete()+"Impl";
	}
	
	public String getManagerClase() {
		return this.nombreApp+Constantes.CAPA_MANAGER;
	}
	public String getManagerVariable() {
		return this.nombreApp.substring(0,1).toLowerCase()+this.getManagerClase().substring(1);
	}
	public String getManagerMetodo() {
		return this.getManagerClase();
	}
	public String getManagerPakete() {
		return this.pakete+".business.manager";
	}
	
	public String getDelegateClase() {
		return this.nombreApp+Constantes.CAPA_DELEGATE;
	}
	public String getDelegateVariable() {
		return this.nombreApp.substring(0,1).toLowerCase()+this.getDelegateClase().substring(1);
	}
	public String getDelegateMetodo() {
		return this.getDelegateClase();
	}
	public String getDelegatePakete() {
		return this.pakete+".web.delegate";
	}
	
	public String getFormClase() {
		return this.nombreApp+Constantes.CAPA_FORM;
	}
	public String getFormVariable() {
		return this.nombreApp.substring(0,1).toLowerCase()+this.getFormClase().substring(1);
	}
	public String getFormMetodo() {
		return this.getFormClase();
	}
	public String getFormPakete() {
		return this.pakete+".web.form";
	}
	
	public String getActionClase() {
		return "Maestro"+this.nombreApp+Constantes.CAPA_ACTION;
	}
	public String getActionVariable() {
		return "m"+this.getActionClase().substring(1);
	}
	public String getActionMetodo() {
		return this.getActionClase();
	}
	public String getActionPakete() {
		return this.pakete+".web.action";
	}
	
	public String getDwrClase() {
		return this.nombreApp+"Controller";
	}
	public String getDwrVariable() {
		return this.nombreApp.substring(0,1).toLowerCase()+this.getDwrClase().substring(1);
	}
	public String getDwrMetodo() {
		return this.getDwrClase();
	}
	public String getDwrPakete() {
		return this.pakete+".web.dwr";
	}
	
	
	@Override
	public String toString() {
		return "TablaVO [\nnombreSecuencia=" + nombreSecuencia
				+ ", \nnombreTabla=" + nombreTabla + ", \nnombreApp=" + nombreApp
				+ ", \nnombreEsquema=" + nombreEsquema 
				+ ", \npakete=" + pakete + ", \ncampos=" + campos + ", \npks=" + pks + ", \nfks=" + fks
				+ ", \nsets=" + sets + "]";
	}
	
}
