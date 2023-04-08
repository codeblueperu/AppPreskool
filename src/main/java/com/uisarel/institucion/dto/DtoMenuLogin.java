package com.uisarel.institucion.dto;

import java.util.List;

public class DtoMenuLogin {

	private String nombre;
	
	private String icono;

	private String url;

	private String orden;	

	private String estado;

	private String grupo;

	private String perfil;
	
	private int idMenu;

	private List<DtoMenuLogin> subMenu;	

	public DtoMenuLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DtoMenuLogin(String nombre, String icono, String url, String orden, String estado, String grupo,
			String perfil, int idMenu, List<DtoMenuLogin> subMenu) {
		super();
		this.nombre = nombre;
		this.icono = icono;
		this.url = url;
		this.orden = orden;
		this.estado = estado;
		this.grupo = grupo;
		this.perfil = perfil;
		this.idMenu = idMenu;
		this.subMenu = subMenu;
	}

	public DtoMenuLogin(String nombre, String icono, String url, String orden, String estado, String grupo,
			String perfil, int idMenu) {
		super();
		this.nombre = nombre;
		this.icono = icono;
		this.url = url;
		this.orden = orden;
		this.estado = estado;
		this.grupo = grupo;
		this.perfil = perfil;
		this.idMenu = idMenu;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOrden() {
		return orden;
	}

	public void setOrden(String orden) {
		this.orden = orden;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public int getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(int idMenu) {
		this.idMenu = idMenu;
	}

	public List<DtoMenuLogin> getSubMenu() {
		return subMenu;
	}

	public void setSubMenu(List<DtoMenuLogin> subMenu) {
		this.subMenu = subMenu;
	}

	@Override
	public String toString() {
		return "DtoMenuLogin [nombre=" + nombre + ", icono=" + icono + ", url=" + url + ", orden=" + orden + ", estado="
				+ estado + ", grupo=" + grupo + ", perfil=" + perfil + ", idMenu=" + idMenu + ", subMenu=" + subMenu
				+ "]";
	}
}
