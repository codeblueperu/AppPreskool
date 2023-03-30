package com.uisarel.institucion.modelo.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ApoderadoEstudiante {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idApoderado;
	
	@Column(length = 100, nullable = false)
	private String nombre;
	
	@Column(length = 70, nullable = false)
	private String apmaterno;
	
	@Column(length = 70, nullable = false)
	private String appaterno;
	
	@Column(length = 25, nullable = false)
	private String numDocumento;
	
	@Column(length = 12, nullable = true)
	private String celular;
	
	@Column(length = 80, nullable = true)
	private String email;
	
	@Column(length = 250, nullable = true)
	private String direccion;
}
