package Var_NOMBREPAKETE.utils;


import java.io.Serializable;


public class Breadcrumb implements Serializable, Comparable<Breadcrumb>{

	private String titulo;
	private String url;

	public Breadcrumb(String titulo, String url) {
		super();
		this.titulo = titulo;
		this.url = url;
	}

	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)	return true;
		if (obj == null)	return false;
		if (getClass() != obj.getClass())	return false;
		Breadcrumb other = (Breadcrumb) obj;
		if (titulo == null) {
			if (other.titulo != null) return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}

	public int compareTo(Breadcrumb arg0) {
		if(this.equals(arg0)) 	return 0;
		else  return 1;
	}
}
