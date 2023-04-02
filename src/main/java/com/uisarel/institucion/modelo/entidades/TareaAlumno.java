package com.uisarel.institucion.modelo.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class TareaAlumno {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTareaAlumno;
	
	@Column(length = 2, nullable = false)
	private String estadoPresenta;
	
	@Column(nullable = true)
	private double nota;
	
	@OneToOne
	@JoinColumn(name = "idTarea")
	private AsignarTarea tarea;
	
	@OneToOne
	@JoinColumn(name = "idEstudiante")
	private Estudiante alumno;

}
