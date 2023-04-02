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
public class Conducta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idConducta;
	
	@Column(length = 500, nullable = false)
	private String descripcion;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es-PE", timezone = "America/Lima")
	@Column(nullable = false, columnDefinition = "DATE")
	private Date fechaRegistra;
	
	@Column(length = 2, nullable = false)
	private String notificarAPoderadoEmail;
	
	@OneToOne
	@JoinColumn(name = "idEstudiante")
	private Estudiante estudiante;
	
	@OneToOne
	@JoinColumn(name = "idPersonal")
	private Personal personal;
}
