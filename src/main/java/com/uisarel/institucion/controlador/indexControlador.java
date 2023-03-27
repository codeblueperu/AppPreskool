package com.uisarel.institucion.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.uisarel.institucion.servicio.impl.ConfiguracionesServiceImp;



@Controller
public class indexControlador {
	
	
	@Autowired
	private ConfiguracionesServiceImp srvSeting;
	
	//@PreAuthorize("isAuthenticated() and (hasRole('ADMINISTRADOR') or hasRole('OTHERS'))")
	@GetMapping("/dashboard")
	public String verPrincipal(Authentication auth,Model model) {	
		//System.err.println(ConstantApp.ROL_LOGIN);
		return"/plantilla/plantilla";
	}
	
	@GetMapping("/login")
	public String verLogin() {
		return "Login";
	}
	
	@GetMapping("/logout")
	public String logout(jakarta.servlet.http.HttpServletRequest request) {
		
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		
		logoutHandler.logout(request, null, null);
		return "redirect:/";
	}
	
	@ModelAttribute
	public void setGenericos(Authentication auth,Model model) {
		model.addAttribute("menuLista", srvSeting.onListaMenuPerfil(auth));
	}
	
}
