package com.uisarel.institucion.controlador;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.uisarel.institucion.dto.DtoMenuLogin;
import com.uisarel.institucion.servicio.IAdminTemplateService;
import com.uisarel.institucion.servicio.IAsignarTareaServicio;
import com.uisarel.institucion.servicio.IConductaService;
import com.uisarel.institucion.servicio.IMenuServicio;
import com.uisarel.institucion.utils.ConstantApp;

@Controller
public class indexControlador {
	
	@Autowired
	private IAsignarTareaServicio srvAsignarTareas;
	
	@Autowired
	private IConductaService srvConducta;
	
	@Autowired
	private IAdminTemplateService srvAdminTemplate;
	
	@Autowired
	private IMenuServicio servicioMenu;
	
	@GetMapping("/dashboard")
	public String verPrincipal(Authentication auth, Model model) {
		for (GrantedAuthority rol : auth.getAuthorities()) {
			ConstantApp.ROL_LOGIN = rol.getAuthority();
		}
		String template = "";
		if (ConstantApp.ROL_LOGIN.compareTo("ADMINISTRADOR") == 0) {
			template = "/dashboard/admindashboard";
		} else if (ConstantApp.ROL_LOGIN.compareTo("ESTUDIANTE") == 0) {
			model.addAttribute("lsttareas", srvAsignarTareas.onListarTareasActivas(2023, 0, "-", new Date()));
			model.addAttribute("lstconducta",srvConducta.onListarConductaEstudiante(2023));
			template = "/dashboard/studentdashboard";
		} else {
			template = "/dashboard/teacherdashboard";
		}

		List<DtoMenuLogin> menu = servicioMenu.onBuscarMenuLogin();
		model.addAttribute("listaMenu", menu);
		//model.addAttribute("menuLista", srvSeting.onListaMenuPerfil(auth));
		return template;
	}

	@GetMapping("/login")
	public String verLogin() {
		return "auth/login";
	}
	
	@GetMapping("/logout")
	public String logout(jakarta.servlet.http.HttpServletRequest request) {

		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

		logoutHandler.logout(request, null, null);
		return "redirect:/";
	}

	@PreAuthorize("isAuthenticated()")
	@ModelAttribute
	public void setGenericos(Authentication auth, Model model) {
		model.addAttribute("setting",srvAdminTemplate.onMostrarDataTemplateAdmin());
	}

}
