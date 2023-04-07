package com.uisarel.institucion.controlador;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.uisarel.institucion.servicio.IAdminTemplateService;
import com.uisarel.institucion.servicio.IMenuServicio;
import com.uisarel.institucion.servicio.IPerfilServicio;
import com.uisarel.institucion.servicio.impl.ConfiguracionesServiceImp;

@Controller
public class PerfilMenuControlador {

	
	@Autowired
	private IMenuServicio srvMenu;
	
	@Autowired
	private ConfiguracionesServiceImp srvSeting;
	
	@Autowired
	private IPerfilServicio servicioPerfil;
	
	@Autowired
	private IAdminTemplateService srvAdminTemplate;

	@GetMapping("/perfilmenu")
	public String getShowTemplatePerfilMenu(Model model) {
		
		//LISTA DE PERFILES
		model.addAttribute("listaPerfiles",servicioPerfil.listaPerfil());
		//MENU PRINCIPALES
		model.addAttribute("mnMain",srvMenu.onListarMenuPrincipales("0","1"));
		
		return "perfilMenu";
	}
	
//	@GetMapping("/redirectSubMenu")
//	public String getBuscarDataSubMenu(@RequestParam("idperfil") String idperfil,@RequestParam("idmenu") String idmenu,RedirectAttributes model) {
//		System.err.println(idperfil);
//		//MENU PRINCIPALES
//		model.addFlashAttribute("prmidperfil",idperfil);
//		
//		return "redirect:/perfilmenu";
//	}
	
	@ModelAttribute
	public void setGenericos(Authentication auth,Model model) {
		model.addAttribute("setting",srvAdminTemplate.onMostrarDataTemplateAdmin());
		model.addAttribute("menuLista", srvSeting.onListaMenuPerfil(auth));
	}

}
