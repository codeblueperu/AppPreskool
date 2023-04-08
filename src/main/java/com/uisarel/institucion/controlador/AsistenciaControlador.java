package com.uisarel.institucion.controlador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.uisarel.institucion.modelo.entidades.Menu;
import com.uisarel.institucion.servicio.IAdminTemplateService;
import com.uisarel.institucion.servicio.IMenuServicio;
import com.uisarel.institucion.servicio.IPeriodoEscolarService;
import com.uisarel.institucion.servicio.IPersonalServicio;
import com.uisarel.institucion.servicio.ISeccionService;

@Controller
public class AsistenciaControlador {

	@Autowired
	private IPeriodoEscolarService srvPeriodoEscolar;

	@Autowired
	private ISeccionService srvSeccion;
	
	@Autowired
	private IPersonalServicio srvDocente;
	
	@Autowired
	private IAdminTemplateService srvAdminTemplate;
	
	@Autowired
	private IMenuServicio servicioMenu;

	List<Menu> lstMenuAcceso = new ArrayList<>();

	@GetMapping("/addasistencia")
	public String getCursoAll(Model model) {
		model.addAttribute("lstdocente",srvDocente.onListarPersonalAll());
		model.addAttribute("lstperiodo", srvPeriodoEscolar.onListarPeriodoEscolarEstado("APERTURADO"));
		model.addAttribute("lstseccion",srvSeccion.onListarSeccionAll());
		return "asistencia/asistencia";
	}

	@ModelAttribute
	public void setGenericos(Authentication auth, Model model) {
		lstMenuAcceso = servicioMenu.listarMenu();
		model.addAttribute("listaMenu", lstMenuAcceso);
		model.addAttribute("setting",srvAdminTemplate.onMostrarDataTemplateAdmin());
	}
}
