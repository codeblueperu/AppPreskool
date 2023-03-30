package com.uisarel.institucion.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.uisarel.institucion.servicio.impl.ConfiguracionesServiceImp;
import com.uisarel.institucion.utils.ConstantApp;



@Controller
public class indexControlador {
	
	
	@Autowired
	private ConfiguracionesServiceImp srvSeting;
	
	@GetMapping("/dashboard")
	public String verPrincipal(Authentication auth,Model model) {
		for (GrantedAuthority rol : auth.getAuthorities()) {
			ConstantApp.ROL_LOGIN = 	rol.getAuthority();
			System.err.println(ConstantApp.ROL_LOGIN);
		}
		model.addAttribute("menuLista", srvSeting.onListaMenuPerfil(auth));
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
	
	@PreAuthorize("isAuthenticated()")
	@ModelAttribute
	public void setGenericos(Authentication auth,Model model) {
		
	}
	
}
