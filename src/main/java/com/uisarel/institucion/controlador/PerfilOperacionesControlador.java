package com.uisarel.institucion.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.uisarel.institucion.modelo.entidades.PerfilOperaciones;
import com.uisarel.institucion.servicio.IAdminTemplateService;
import com.uisarel.institucion.servicio.IMenuServicio;
import com.uisarel.institucion.servicio.IPerfilServicio;
import com.uisarel.institucion.servicio.impl.ConfiguracionesServiceImp;

@Controller
public class PerfilOperacionesControlador {
	
	@Autowired
	private IMenuServicio srvMenu;

	@Autowired
	private IPerfilServicio servicioPerfil;
	
	@Autowired
	private ConfiguracionesServiceImp srvSeting;
	
	@Autowired
	private IAdminTemplateService srvAdminTemplate;

	@GetMapping("/listaPerfilesOperaciones")
	public String listarPerfilOperaciones(Model model) {

		// LISTA DE PERFILES
		model.addAttribute("listaPerfiles", servicioPerfil.listaPerfil());
		
		//MENU PRINCIPALES
		model.addAttribute("mnMain",srvMenu.onListarMenuPrincipales("0","1"));

		return "perfilesOperaciones";
	}

	// MetodoRegistrar
	@GetMapping("/registroPerfilesOperaciones/nuevo")
	public String registroPerfilOperaciones(Model modelo) {

		return "registroPerfilesOperaciones";
	}

	@PostMapping("/perfilOperacion")
	public String guardarUsuario(
			@ModelAttribute("nuevoPerfilesOperaciones") PerfilOperaciones nuevoPerfilesOperaciones) {

		return "redirect:/listaPerfilesOperaciones";

	}
	
	@ModelAttribute
	public void setGenericos(Authentication auth,Model model) {
		model.addAttribute("setting",srvAdminTemplate.onMostrarDataTemplateAdmin());
		model.addAttribute("menuLista", srvSeting.onListaMenuPerfil(auth));
	}

}
