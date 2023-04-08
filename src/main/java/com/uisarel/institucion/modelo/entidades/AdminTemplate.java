package com.uisarel.institucion.modelo.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class AdminTemplate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProyecto;
	
	private String nameSistema;
	
	private String logoSistema;
	
	private String iconoSistema;
	
	private String bannerLogin;
	
	private String coloresButtons;
	
	private String colorButtonMenu;
	
	private String coloresButtonsCancel;
	
	private String linkAcceso;
	
}
