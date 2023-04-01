package com.uisarel.institucion.modelo.entidades;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Personal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPersonal;

	@Column(length = 100, nullable = false)
	private String nombre;

	@Column(length = 100, nullable = false)
	private String apellidos;

	@Column(length = 25, nullable = false, unique = true)
	private String numDocumento;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es-PE", timezone = "America/Lima")
	private String fechaNacimiento;

	@Column(length = 12, nullable = true)
	private String ncelular;

	@Column(length = 70, nullable = true)
	private String direccion;

	@Column(length = 25, nullable = false)
	private String sexo;
	
	@Column(length = 70, nullable = true)
	private String email;
	
	@Column(length = 15, nullable = true)
	private String nivelAcademico;

	@JoinTable(name = "curso_docente", joinColumns = @JoinColumn(name = "idPersonal", nullable = false), inverseJoinColumns = @JoinColumn(name = "idCurso", nullable = false))
	@ManyToMany(cascade = CascadeType.REFRESH)
	private List<Cursos> lstcursos;
	
	@JoinTable(name = "grados_docente", joinColumns = @JoinColumn(name = "idPersonal", nullable = false), inverseJoinColumns = @JoinColumn(name = "idGrado", nullable = false))
	@ManyToMany(cascade = CascadeType.REFRESH)
	private List<Grado> lstGrado;
	
	@JoinTable(name = "seccion_docente", joinColumns = @JoinColumn(name = "idPersonal", nullable = false), inverseJoinColumns = @JoinColumn(name = "idSeccion", nullable = false))
	@ManyToMany(cascade = CascadeType.REFRESH)
	private List<Seccion> lstSeccion;


}
