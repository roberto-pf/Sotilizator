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

public class CampoVO {

	private String nombreEnTabla;
	private String tipoEnTabla;
	private Integer scale;
	private Integer precision;
	
	private String tipoParaHibernate;
	
	private String nombreEnApp;
	private String metodoEnApp;
	private String tipoEnApp;
	private String tipoCompletoEnApp;
	private String tipoEnForm;
	
	private Integer tam;

	public String getNombreEnTabla() {
		return nombreEnTabla;
	}

	public void setNombreEnTabla(String nombreEnTabla) {
		this.nombreEnTabla = nombreEnTabla;
	}

	public String getTipoEnTabla() {
		return tipoEnTabla;
	}

	public void setTipoEnTabla(String tipoEnTabla) {
		this.tipoEnTabla = tipoEnTabla;
	}

	public String getMetodoEnApp() {
		return metodoEnApp;
	}

	public void setMetodoEnApp(String metodoEnApp) {
		this.metodoEnApp = metodoEnApp;
	}

	public String getTipoEnApp() {
		return tipoEnApp;
	}

	public void setTipoEnApp(String tipoEnApp) {
		this.tipoEnApp = tipoEnApp;
	}

	public Integer getTam() {
		return tam;
	}

	public void setTam(Integer tam) {
		this.tam = tam;
	}

	public String getTipoCompletoEnApp() {
		return tipoCompletoEnApp;
	}

	public void setTipoCompletoEnApp(String tipoCompletoEnApp) {
		this.tipoCompletoEnApp = tipoCompletoEnApp;
	}

	public String getNombreEnApp() {
		return nombreEnApp;
	}

	public void setNombreEnApp(String nombreEnApp) {
		this.nombreEnApp = nombreEnApp;
	}

	public String getTipoParaHibernate() {
		return tipoParaHibernate;
	}

	public void setTipoParaHibernate(String tipoParaHibernate) {
		this.tipoParaHibernate = tipoParaHibernate;
	}

	public Integer getScale() {
		return scale==null?new Integer(0):this.scale;
	}

	public void setScale(Integer scale) {
		this.scale = scale;
	}

	public String getTipoEnForm() {
		return tipoEnForm;
	}

	public void setTipoEnForm(String tipoEnForm) {
		this.tipoEnForm = tipoEnForm;
	}

	public Integer getPrecision() {
		return precision;
	}

	public void setPrecision(Integer precision) {
		this.precision = precision;
	}
	
	public Integer getTamOrPrecision() {
		if(this.precision==null || this.precision.intValue()==0)
			return this.tam;
		return precision;
	}

	@Override
	public String toString() {
		return "\tCampoVO [nombreEnTabla=" + nombreEnTabla + ", tipoEnTabla="
				+ tipoEnTabla + ", nombreEnApp=" + nombreEnApp
				+ ", metodoEnApp=" + metodoEnApp + ", tipoEnApp=" + tipoEnApp
				+ ", tipoCompletoEnApp=" + tipoCompletoEnApp + ", tam=" + tam + ", scale=" + scale
				+ ", tipoParaHibernate=" + tipoParaHibernate
				+ "]\n";
	}
	
}
