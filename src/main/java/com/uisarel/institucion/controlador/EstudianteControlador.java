package com.uisarel.institucion.controlador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.uisarel.institucion.modelo.entidades.Menu;
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

	List<Menu> lstMenuAcceso = new ArrayList<>();

	@GetMapping("/estudiantes")
	public String getGradoAll(Model model) {
		model.addAttribute("lstperiodo", srvPeriodoEscolar.onListarPeriodoEscolarEstado("APERTURADO"));
		model.addAttribute("lstseccion",srvSeccion.onListarSeccionAll());
		return "estudiantes/estudiante";
	}

	@GetMapping("/viewcreatestudent")
	public String getViewCreateNewStudiante(Model model) {
		model.addAttribute("lstperiodo", srvPeriodoEscolar.onListarPeriodoEscolarEstado("APERTURADO"));
		model.addAttribute("lstseccion",srvSeccion.onListarSeccionAll());
		return "estudiantes/newStudent";
	}
	
	@GetMapping("/vieweditstudent")
	public String getViewEditStudiante(@RequestParam("student") int idestudiante,@RequestParam("periodo") int periodo, Model model) {
		model.addAttribute("lstperiodo", srvPeriodoEscolar.onListarPeriodoEscolarEstado("APERTURADO"));
		model.addAttribute("lstseccion",srvSeccion.onListarSeccionAll());
		return "estudiantes/newStudent";
	}
	
	@ModelAttribute
	public void setGenericos(Authentication auth, Model model) {
		lstMenuAcceso = servicioMenu.listarMenu();
		model.addAttribute("listaMenu", lstMenuAcceso);
		model.addAttribute("setting", srvAdminTemplate.onMostrarDataTemplateAdmin());
	}
}
