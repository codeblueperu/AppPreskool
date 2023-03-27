package com.uisarel.institucion.modelo.entidades;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
public class Perfil implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPerfil;
	
	private String nombre;
	private String estado;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date fechaCreacionPerfil;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date fechaActualizacionPerfil;
	
	
	
	//Relacion Perfil-UsuarioPerfil
//	@ToString.Exclude
//	@OneToMany(mappedBy = "fkPerfil", cascade = CascadeType.REFRESH)
//	private List<UsuarioPerfil> listaUsuarioPerfil = new ArrayList<>();
	
	//Relacion Perfil-PerfilOperaciones
//	@ToString.Exclude
//	@OneToMany(mappedBy = "fkPerfil", cascade = CascadeType.REFRESH)
//	private List<PerfilOperaciones> listaPerfilOperaciones = new ArrayList<>();
	
	//Relacion Perfil-PerfilMenu
//	@ToString.Exclude
//	@OneToMany(mappedBy = "fkPerfil")
//	private List<PerfilMenu> listaPerfilMenu= new ArrayList<>();	

}
