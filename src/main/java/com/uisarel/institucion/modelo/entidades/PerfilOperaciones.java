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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
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
	@Temporal(TemporalType.DATE)
	private Date fechaCreacionPerOpe;
	@Temporal(TemporalType.DATE)
	private Date fechaModificacionPerOpe; 
	
	//Relacion PerfilOperaciones-Perfil
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "fkPerfil")
	private Perfil fkPerfil;
	
	//Relacion PerfilOperaciones-Operaciones
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "fkOperaciones")
	private Operaciones fkOperaciones;
	
	public PerfilOperaciones(int idPerfilOperaciones, Perfil fkPerfil, Operaciones fkOperaciones) {
		super();
		this.idPerfilOperaciones = idPerfilOperaciones;
		this.fkPerfil = fkPerfil;
		this.fkOperaciones = fkOperaciones;
	}
	
	public PerfilOperaciones(Perfil fkPerfil, Operaciones fkOperaciones) {
		super();
		this.fkPerfil = fkPerfil;
		this.fkOperaciones = fkOperaciones;
	}
	
	public void SetPerfil(Perfil perfil) {
		
		this.fkPerfil = perfil;
		
	}
	
	public void setOperaciones(Operaciones operaciones) {
		
		this.fkOperaciones = operaciones;
		
	}
	
	public void setCrear(Integer crear){
		this.crear = crear;	
	}
}
