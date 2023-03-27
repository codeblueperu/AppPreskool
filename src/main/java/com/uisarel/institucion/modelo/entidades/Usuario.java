package com.uisarel.institucion.modelo.entidades;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;


@Entity
@Data
public class Usuario implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUsuario;
	
	private String nombres;
	private String apellidos;
	private String CI;
	private String usuarioCorreo;
	private String contrasenia;
	private String sexo;
	private String estadoU;
	private String direccion;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date fechaCreacionUsuario;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date fechaModificacionUsuario; 
	//java.util.Date
	
	//Relation  Usuario-UsuarioPerfil
	
//	@OneToMany(mappedBy = "fkUsuario")
//	private Set<UsuarioPerfil> listarUsuarioPerfil;
//	
//	//Relacion Usuario-Sesion
//	@ToString.Exclude
//	@OneToMany(mappedBy = "fkUsuario", cascade = CascadeType.REFRESH)
//	private List<Sesion> listarSesion = new ArrayList<>();
	

	
	/*import jakarta.persistence.Temporal;
	import jakarta.persistence.TemporalType;
	import java.util.Date;
	import org.springframework.format.annotation.DateTimeFormat;*/
	
	/*@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	private Date fechaNaciminetoUsuario;*/
}
