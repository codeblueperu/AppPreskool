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

	@Column(length = 25, nullable = false)
	private String numDocumento;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es-PE", timezone = "America/Lima")
	private String fechaNacimiento;

	@Column(length = 12, nullable = true)
	private String ncelular;

	@Column(length = 70, nullable = true)
	private String direccion;

	@Column(length = 25, nullable = false)
	private String sexo;

	@JoinTable(name = "curso_docente", joinColumns = @JoinColumn(name = "idPersonal", nullable = false), inverseJoinColumns = @JoinColumn(name = "idCurso", nullable = false))
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Cursos> lstcursos;

}
