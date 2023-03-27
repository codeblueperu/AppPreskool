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
public class Operaciones implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idOperaciones;
	
	private String nombre;
	private String estado;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date fechaCreacionOperacion;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date fechaModificacionOperacion; 
	
	//Relacion Operaciones-PerfilOperaciones
//	@ToString.Exclude
//	@OneToMany(mappedBy = "fkOperaciones", cascade = CascadeType.REFRESH)
//	private List<PerfilOperaciones> listaPerfilOperaciones = new ArrayList<>(); 

}
