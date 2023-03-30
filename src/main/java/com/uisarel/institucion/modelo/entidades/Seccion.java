package com.uisarel.institucion.modelo.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Seccion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSeccion;
	
	private String descripcionSeccion;
	
	@Column(name = "estado_seccion")
	private boolean estadoSeccion;
}
