package com.uisarel.institucion.modelo.entidades;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class PeriodoEscolar implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPeriodoEscolar;
	
	private String descripcionPeriodo;
	
	@Column(name = "anio_escolar",unique = true)
	private int anioEscolar;
	
}
