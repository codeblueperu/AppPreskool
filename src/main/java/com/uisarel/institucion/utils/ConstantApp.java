package com.uisarel.institucion.utils;

import java.io.File;
import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

public class ConstantApp {

	public static String ROL_LOGIN = "";
	
	public static String USERNAME_LOGIN = "";
	
	public static String guardarArchivo(MultipartFile multiPart, String ruta) {
		String nombreOriginal = multiPart.getOriginalFilename();
		nombreOriginal.replace(" ", "_");
		String nombreRandom = nombreOriginal;
		try {
			
			File imageFile = new File(ruta + nombreRandom);
			
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
	
}
