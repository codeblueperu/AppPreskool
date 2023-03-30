package com.uisarel.institucion.modelo.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Cursos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCurso;
	
	private String nombreCurso;
	
	private String estadoCurso;
}
