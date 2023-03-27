package com.uisarel.institucion.modelo.entidades;

import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Sesion implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idLogin;  
	
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private java.util.Date InicioSecion;
		
	// Relacion Login-Usuario
	@ManyToOne(cascade = CascadeType.REFRESH)
	//@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "fkUsuario")
	private Usuario fkUsuario;
	
}
