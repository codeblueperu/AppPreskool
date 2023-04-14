package com.uisarel.institucion.utils;

import java.io.File;
import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

public class ConstantApp {

	public static String ROL_LOGIN = "";

	public static String FILE_DIRECTORY = "c://appinstitucion";

	public static final String USER_SERVICE_EMAIL = "jmiserver13@gmail.com";
	public static final String PASS_SERVICE_EMAIL = "ngcmfnfhgekxtsdc";
	public static final String HOST_SERVICE_EMAIL = "smtp.gmail.com";
	public static final int PORT_SERVICE_EMAIL = 587;

	public static String guardarArchivo(MultipartFile multiPart, String ruta) {
		String nombreOriginal = multiPart.getOriginalFilename();
		nombreOriginal.replace(" ", "_");
		String nombreRandom = nombreOriginal;
		try {

			File imageFile = new File(ruta + "//" + nombreRandom);

			multiPart.transferTo(imageFile);
			return nombreRandom;
		} catch (IOException e) {
			System.out.println("Error " + e.getMessage());
			return null;
		}
	}

	public static String getuserLogin() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails user = (UserDetails) auth.getPrincipal();
		return user.getUsername();
	}

	public static String getuRolUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		for (GrantedAuthority rol : auth.getAuthorities()) {
			ConstantApp.ROL_LOGIN = rol.getAuthority().toUpperCase();
		}
		return ConstantApp.ROL_LOGIN;
	}

}
