package com.uisarel.institucion.dto;

import java.util.List;

import com.uisarel.institucion.modelo.entidades.Menu;

public class DtoListaMenuPerfil {

	private int idMenu;
	private String nombre;
	private String uRL;
	private String menuIdPadre;
	private String orden;
	private String iconoMenu;
	private String estadoMenu;
	private List<Menu> subMenus;

	public DtoListaMenuPerfil() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DtoListaMenuPerfil(int idMenu, String nombre, String uRL, String menuIdPadre, String orden, String iconoMenu,
			String estadoMenu, List<Menu> subMenus) {
		super();
		this.idMenu = idMenu;
		this.nombre = nombre;
		this.uRL = uRL;
		this.menuIdPadre = menuIdPadre;
		this.orden = orden;
		this.iconoMenu = iconoMenu;
		this.estadoMenu = estadoMenu;
		this.subMenus = subMenus;
	}

	public int getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(int idMenu) {
		this.idMenu = idMenu;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getuRL() {
		return uRL;
	}

	public void setuRL(String uRL) {
		this.uRL = uRL;
	}

	public String getMenuIdPadre() {
		return menuIdPadre;
	}

	public void setMenuIdPadre(String menuIdPadre) {
		this.menuIdPadre = menuIdPadre;
	}

	public String getOrden() {
		return orden;
	}

	public void setOrden(String orden) {
		this.orden = orden;
	}

	public String getIconoMenu() {
		return iconoMenu;
	}

	public void setIconoMenu(String iconoMenu) {
		this.iconoMenu = iconoMenu;
	}

	public String getEstadoMenu() {
		return estadoMenu;
	}

	public void setEstadoMenu(String estadoMenu) {
		this.estadoMenu = estadoMenu;
	}

	public List<Menu> getSubMenus() {
		return subMenus;
	}

	public void setSubMenus(List<Menu> subMenus) {
		this.subMenus = subMenus;
	}

	@Override
	public String toString() {
		return "DtoListaMenuPerfil [idMenu=" + idMenu + ", nombre=" + nombre + ", uRL=" + uRL + ", menuIdPadre="
				+ menuIdPadre + ", orden=" + orden + ", iconoMenu=" + iconoMenu + ", estadoMenu=" + estadoMenu
				+ ", subMenus=" + subMenus + "]";
	}

}
