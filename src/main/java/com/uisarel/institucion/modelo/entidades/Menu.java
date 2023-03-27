package com.uisarel.institucion.modelo.entidades;

import java.io.Serializable;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Menu implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMenu;
	
	private String nombre;
	private String uRL;
	private String menuIdPadre;
	private String orden;
	private String iconoMenu;
	private String estadoMenu;	
	
	
	//Relacion Menu-PerfilMenu
//	@ToString.Exclude
//	@OneToMany(mappedBy = "fkMenu")
//	private List<PerfilMenu> subMenu = new ArrayList<>();
	
	//Relacion Menu-Menu
//	@OneToMany(mappedBy = "fkMenu")
//	private List<PerfilMenu> listarMenu = new ArrayList<>();
	
	//Relacion Menu-Menu
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "fkMenu")
//	private Perfil fkMenu;

}
