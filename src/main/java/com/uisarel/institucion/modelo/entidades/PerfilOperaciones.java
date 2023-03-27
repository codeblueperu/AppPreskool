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
public class PerfilOperaciones implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPerfilOperaciones;
	
	private int crear;
	private int leer;
	private int actualizar;
	private int eliminar;
	private String estado;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date fechaCreacionPerOpe;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date fechaModificacionPerOpe; 
	
	//Relacion PerfilOperaciones-Perfil
//	@ManyToOne(cascade = CascadeType.REFRESH)
//	@JoinColumn(name = "fkPerfil")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fkPerfil", referencedColumnName = "idPerfil", nullable = true)
	private Perfil fkPerfil;
	
	//Relacion PerfilOperaciones-Operaciones
//	@ManyToOne(cascade = CascadeType.REFRESH)
//	@JoinColumn(name = "fkOperaciones")
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "fkOperaciones", referencedColumnName = "idOperaciones", nullable = true)
//	private Operaciones fkOperaciones;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fkMenu", referencedColumnName = "idMenu", nullable = true)
	private Menu fkMenu;

}
