package com.uisarel.institucion.dto;

public class DtoPermisos {

	private int leer;
	private int crear;
	private int update;
	private int delete;
	private int idoperacion;

	public DtoPermisos() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DtoPermisos(int leer, int crear, int update, int delete, int idoperacion) {
		super();
		this.leer = leer;
		this.crear = crear;
		this.update = update;
		this.delete = delete;
		this.idoperacion = idoperacion;
	}

	public int getLeer() {
		return leer;
	}

	public void setLeer(int leer) {
		this.leer = leer;
	}

	public int getCrear() {
		return crear;
	}

	public void setCrear(int crear) {
		this.crear = crear;
	}

	public int getUpdate() {
		return update;
	}

	public void setUpdate(int update) {
		this.update = update;
	}

	public int getDelete() {
		return delete;
	}

	public void setDelete(int delete) {
		this.delete = delete;
	}

	public int getIdoperacion() {
		return idoperacion;
	}

	public void setIdoperacion(int idoperacion) {
		this.idoperacion = idoperacion;
	}

	@Override
	public String toString() {
		return "DtoPermisos [leer=" + leer + ", crear=" + crear + ", update=" + update + ", delete=" + delete
				+ ", idoperacion=" + idoperacion + "]";
	}

}
