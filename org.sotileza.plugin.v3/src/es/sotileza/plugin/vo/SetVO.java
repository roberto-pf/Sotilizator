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

public class SetVO {

	private String nombreEnApp;
	private String tipoEnApp;
	private String tipoCompletoEnApp;
	private String metodoEnApp;
	private String nombreEsquema;
	private TablaVO ref;
	private FkVO fkVO;
	
	public String getNombreEnApp() {
		return nombreEnApp;
	}
	public void setNombreEnApp(String nombreEnApp) {
		this.nombreEnApp = nombreEnApp;
	}
	public String getTipoEnApp() {
		return tipoEnApp;
	}
	public void setTipoEnApp(String tipoEnApp) {
		this.tipoEnApp = tipoEnApp;
	}
	public String getTipoCompletoEnApp() {
		return tipoCompletoEnApp;
	}
	public void setTipoCompletoEnApp(String tipoCompletoEnApp) {
		this.tipoCompletoEnApp = tipoCompletoEnApp;
	}
	public String getMetodoEnApp() {
		return metodoEnApp;
	}
	public void setMetodoEnApp(String metodoEnApp) {
		this.metodoEnApp = metodoEnApp;
	}
	public String getNombreEsquema() {
		return nombreEsquema;
	}
	public void setNombreEsquema(String nombreEsquema) {
		this.nombreEsquema = nombreEsquema;
	}
	public TablaVO getRef() {
		return ref;
	}
	public void setRef(TablaVO ref) {
		this.ref = ref;
	}
	public FkVO getFkVO() {
		return fkVO;
	}
	public void setFkVO(FkVO fkVO) {
		this.fkVO = fkVO;
	}
	@Override
	public String toString() {
		return "\tSetVO [nombreEnApp=" + nombreEnApp + ", tipoEnApp=" + tipoEnApp
				+ ", tipoCompletoEnApp=" + tipoCompletoEnApp + ", metodoEnApp="
				+ metodoEnApp + ", nombreEsquema=" + nombreEsquema + "]\n";
	}
	
}
