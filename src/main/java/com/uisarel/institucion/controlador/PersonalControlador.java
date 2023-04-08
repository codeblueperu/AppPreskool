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
import com.uisarel.institucion.servicio.ISeccionService;

@Controller
public class PersonalControlador {

	@Autowired
	private ISeccionService srvSeccion;

	@Autowired
	private IAdminTemplateService srvAdminTemplate;
	
	@Autowired
	private IMenuServicio servicioMenu;

	List<Menu> lstMenuAcceso = new ArrayList<>();

	@GetMapping("/personal")
	public String getGradoAll(Model model) {

		return "docentes/docente";
	}

	@GetMapping("/adddocente")
	public String getNuevoDocenteTemplate(Model model) {
		model.addAttribute("lstsecciones", srvSeccion.onListarSeccionAll());
		return "docentes/adddocente";
	}

	@GetMapping("/vieweditpersonal")
	public String getEditarDatosPersonalDocente(@RequestParam("person") int idpersona, Model model) {
		model.addAttribute("lstsecciones", srvSeccion.onListarSeccionAll());
		return "docentes/adddocente";
	}

	@ModelAttribute
	public void setGenericos(Authentication auth, Model model) {
		lstMenuAcceso = servicioMenu.listarMenu();
		model.addAttribute("listaMenu", lstMenuAcceso);
		model.addAttribute("setting",srvAdminTemplate.onMostrarDataTemplateAdmin());
	}
}
