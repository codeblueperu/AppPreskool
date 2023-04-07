package com.uisarel.institucion.modelo.entidades;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class Asistencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAsistencia;

	private String estadoAsistencia;

	private String observacion;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es-PE", timezone = "America/Lima")
	@Column(nullable = false, columnDefinition = "DATE")
	private Date fecha;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", locale = "es-PE", timezone = "America/Lima")
	@Column(columnDefinition = "TIME")
	private Date hora;

	@OneToOne
	@JoinColumn(name = "idEstudiante")
	private Estudiante estudiante;
	
	@OneToOne
	@JoinColumn(name = "idCurso")
	private Cursos curso;

}
