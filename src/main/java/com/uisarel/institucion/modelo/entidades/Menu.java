package com.uisarel.institucion.modelo.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
public class Menu implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMenu;
	
	private String nombre;
	private String url;
	private String orden;
	private String icono;
	private String estado;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date fechaCreacionMenu;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date fechaActualizacionMenu;
	
	
	//Relacion Menu-PerfilMenu
	@ToString.Exclude
	@OneToMany(mappedBy = "fkMenu")
	private List<PerfilMenu> listaPerfilMenu = new ArrayList<>();
	
	//Relacion Menu-Menu
	@OneToMany(mappedBy = "fkPadre")
	private List<Menu> SubMenu = new ArrayList<>();
	
	//Relacion Menu-Menu
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fkPadre")
	private Menu fkPadre = null;


}
