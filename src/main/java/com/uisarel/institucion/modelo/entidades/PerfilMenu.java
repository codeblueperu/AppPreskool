package com.uisarel.institucion.modelo.entidades;

import java.io.Serializable;
import java.util.Date;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
public class PerfilMenu implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPerfilMenu;

	private String estado;
	
	@Temporal(TemporalType.DATE)
	private Date fechaCreacionPerMen;
	
	@Temporal(TemporalType.DATE)
	private Date fechaModificacionPerMen; 
		
	//Relacion PerfilMenu-Perfil
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fkPefil")
	private Perfil  fkPerfil;
	
	// Relacion PerfilMenu-Menu
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fkMenu")
	private Menu fkMenu;

	
	public void SetPerfil(Perfil perfil) {
		
		this.fkPerfil = perfil;
		
	}
	
	public void setMenu(Menu menu) {
		
		this.fkMenu = menu;
		
	}

}
