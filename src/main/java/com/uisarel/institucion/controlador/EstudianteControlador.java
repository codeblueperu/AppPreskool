package com.uisarel.institucion.controlador;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.uisarel.institucion.servicio.IAdminTemplateService;
import com.uisarel.institucion.servicio.IMenuServicio;
import com.uisarel.institucion.servicio.IPeriodoEscolarService;
import com.uisarel.institucion.servicio.ISeccionService;

@Controller
public class EstudianteControlador {
		
	@Autowired
	private IPeriodoEscolarService srvPeriodoEscolar;
	
	@Autowired
	private ISeccionService srvSeccion;
	
	@Autowired
	private IAdminTemplateService srvAdminTemplate;
	
	@Autowired
	private IMenuServicio servicioMenu;

	@GetMapping("/estudiantes")
	public String getGradoAll(Model model) {
		if(!servicioMenu.onValidarRutaPermiso("/estudiantes")) {
			return "error/errorPage";
		}
		model.addAttribute("lstperiodo", srvPeriodoEscolar.onListarPeriodoEscolarEstado("APERTURADO"));
		model.addAttribute("lstseccion",srvSeccion.onListarSeccionAll());
		return "estudiantes/estudiante";
	}

	@GetMapping("/viewcreatestudent")
	public String getViewCreateNewStudiante(Model model) {
		if(!servicioMenu.onValidarRutaPermiso("/estudiantes")) {
			return "error/errorPage";
		}
		model.addAttribute("lstperiodo", srvPeriodoEscolar.onListarPeriodoEscolarEstado("APERTURADO"));
		model.addAttribute("lstseccion",srvSeccion.onListarSeccionAll());
		return "estudiantes/newStudent";
	}
	
	@GetMapping("/vieweditstudent")
	public String getViewEditStudiante(@RequestParam("student") int idestudiante,@RequestParam("periodo") int periodo, Model model) {
		if(!servicioMenu.onValidarRutaPermiso("/estudiantes")) {
			return "error/errorPage";
		}
		model.addAttribute("lstperiodo", srvPeriodoEscolar.onListarPeriodoEscolarEstado("APERTURADO"));
		model.addAttribute("lstseccion",srvSeccion.onListarSeccionAll());
		return "estudiantes/newStudent";
	}
	
	@ModelAttribute
	public void setGenericos(Authentication auth, Model model) {
		
		model.addAttribute("listaMenu", servicioMenu.onBuscarMenuLogin());
		model.addAttribute("setting", srvAdminTemplate.onMostrarDataTemplateAdmin());
	}
}
