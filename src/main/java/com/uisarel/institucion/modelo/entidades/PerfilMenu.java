package com.uisarel.institucion.modelo.entidades;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


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
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date fechaCreacionPerMen;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date fechaModificacionPerMen; 
		
	//Relacion PerfilMenu-Perfil
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "fkPefil")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fkPefil", referencedColumnName = "idPerfil", nullable = true)
	private Perfil  fkPerfil;
	
	
	// Relacion PerfilMenu-Menu
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "fkMenu")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fkMenu", referencedColumnName = "idMenu", nullable = true)
	private Menu fkMenu;

}
