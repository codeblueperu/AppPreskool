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
public class AsignarTarea {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTarea;
	
	@Column(length = 50, nullable = false)
	private String tema;
	
	@Column(length = 250, nullable = true)
	private String observacion;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es-PE", timezone = "America/Lima")
	@Column(nullable = false, columnDefinition = "DATE")
	private Date fechaAsignacion;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es-PE", timezone = "America/Lima")
	@Column(nullable = false, columnDefinition = "DATE")
	private Date fechaPresentacion;
	
	@Column(length = 180, nullable = true)
	private String nameDocumento;
	
	private String nivelEscolar;
	
	@OneToOne
	@JoinColumn(name = "idGrado")
	private Grado grado;
	
	@OneToOne
	@JoinColumn(name = "idSeccion")
	private Seccion seccion;
	
	@OneToOne
	@JoinColumn(name="idCurso")
	private Cursos curso;
	
	@OneToOne
	@JoinColumn(name = "idPersonal")
	private Personal personal;
	
	@OneToOne
	@JoinColumn(name="idPeriodoEscolar")
	private PeriodoEscolar periodoEscolar;

}
