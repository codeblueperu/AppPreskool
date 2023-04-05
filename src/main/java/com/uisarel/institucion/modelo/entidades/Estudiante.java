package com.uisarel.institucion.modelo.entidades;

import java.util.Date;


import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Estudiante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEstudiante;

	@Column(length = 100, nullable = true)
	private String colegioProcencia;

	@Column(length = 250, nullable = true)
	private String observacion;

	@Column(length = 100, nullable = false)
	private String nombreEstudiante;

	@Column(length = 70, nullable = false)
	private String apPaterno;

	@Column(length = 70, nullable = false)
	private String apMaterno;

	@Column(length = 25, nullable = false)
	private String numDocumento;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es-PE", timezone = "America/Lima")
	@Column(nullable = false, columnDefinition = "DATE")
	private Date fnacimiento;

	@Column(length = 12, nullable = true)
	private String ncelular;

	@Column(length = 30, nullable = false)
	private String nivelEscolar;

	@Column(length = 30, nullable = false)
	private String turno;

	@Column(length = 25, nullable = false)
	private String sexoEstudiante;
	
	@Column(length = 70, nullable = false)
	private String direccionEstudiante;
	
	@Column(length = 70, nullable = false)
	private String emailEstudiante;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IdGrado", referencedColumnName = "IdGrado", nullable = false)
	private Grado gradoAlumno;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idSeccion", referencedColumnName = "idSeccion", nullable = false)
	private Seccion seccionAlumno;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idPeriodoEscolar", referencedColumnName = "idPeriodoEscolar", nullable = false)
	private PeriodoEscolar periodoEscolar;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idApoderado")
	private ApoderadoEstudiante apoderadoEstudiante;


}
