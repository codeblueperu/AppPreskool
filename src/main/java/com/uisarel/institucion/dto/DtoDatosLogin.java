package com.uisarel.institucion.dto;

public class DtoDatosLogin {

	private  int idUsuario;

	private  String rolusuario;

	private  String nombreUsuario;

	private  String usernameLogin;

	private static DtoDatosLogin dtodatosLogin;

	private DtoDatosLogin(int idUsuario, String rolusuario, String nombreUsuario, String usernameLogin) {
		this.idUsuario = idUsuario;
		this.rolusuario = rolusuario;
		this.nombreUsuario = nombreUsuario;
		this.usernameLogin = usernameLogin;

	}

	public static DtoDatosLogin getSingletonInstance(int idUsuario, String rolusuario, String nombreUsuario,
			String usernameLogin) {
		if (dtodatosLogin == null) {
			dtodatosLogin = new DtoDatosLogin(idUsuario, rolusuario, nombreUsuario, usernameLogin);
		} else {
			System.out.println("No se puede crear porque ya existe un objeto de la clase DtoDatosLogin");
		}

		return dtodatosLogin;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getRolusuario() {
		return rolusuario;
	}

	public void setRolusuario(String rolusuario) {
		this.rolusuario = rolusuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getUsernameLogin() {
		return usernameLogin;
	}

	public void setUsernameLogin(String usernameLogin) {
		this.usernameLogin = usernameLogin;
	}
}
