package com.uisarel.institucion.controlador;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.uisarel.institucion.servicio.IAdminTemplateService;
import com.uisarel.institucion.servicio.IMenuServicio;
import com.uisarel.institucion.servicio.IPersonalServicio;

@Controller
public class ConductaController {
	
	@Autowired
	private IPersonalServicio srvDocente;
	
	@Autowired
	private IAdminTemplateService srvAdminTemplate;
	
	@Autowired
	private IMenuServicio servicioMenu;
	
	@GetMapping("/addconduct")
	public String getCursoAll(Model model) {
		if(!servicioMenu.onValidarRutaPermiso("/addconduct")) {
			return "error/errorPage";
		}
		model.addAttribute("lstdocente",srvDocente.onListarPersonalAll());
		return "conducta/addconduct";
	}
	
	
	@ModelAttribute
	public void setGenericos(Authentication auth, Model model) {
	
		model.addAttribute("listaMenu", servicioMenu.onBuscarMenuLogin());
		model.addAttribute("setting",srvAdminTemplate.onMostrarDataTemplateAdmin());
	}

}
