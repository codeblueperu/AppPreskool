package com.uisarel.institucion.dto;

public class DtoGroupGradoNivel {
	
	private String grado_descripcion;
	private int total;
	public DtoGroupGradoNivel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DtoGroupGradoNivel(String grado_descripcion, int total) {
		super();
		this.grado_descripcion = grado_descripcion;
		this.total = total;
	}
	public String getGrado_descripcion() {
		return grado_descripcion;
	}
	public void setGrado_descripcion(String grado_descripcion) {
		this.grado_descripcion = grado_descripcion;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	@Override
	public String toString() {
		return "DtoGroupGradoNivel [grado_descripcion=" + grado_descripcion + ", total=" + total + "]";
	}
}
