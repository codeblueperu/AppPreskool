package com.uisarel.institucion.modelo.entidades;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
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
public class UsuarioPerfil implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUsuarioPerfil;
	
	private String estado;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date fechaCreacionPerfil;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date fechaModificacionPerfil; 
	
	//Relation UsuarioPerfil-Usuario
	
	//@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "fkUsuario")
	private Usuario fkUsuario;
	
	//Relacion UsuarioPerfil-Perfil
	//@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "fkPerfil")
	private Perfil fkPerfil;
	
}
