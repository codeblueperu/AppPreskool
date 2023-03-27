package com.uisarel.institucion.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class controladorRecuperarPassword {
	
	@GetMapping("/recuperar")
	public String recuoerarPAssword() {
		return"recuperarPassword";
	}

}
