package com.uisarel.institucion.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


import com.uisarel.institucion.modelo.entidades.PerfilOperaciones;
import com.uisarel.institucion.servicio.IPerfilOperacionesServicio;
import com.uisarel.institucion.servicio.IPeriodoEscolarService;
import com.uisarel.institucion.servicio.ISeccionService;
import com.uisarel.institucion.servicio.impl.ConfiguracionesServiceImp;

@Controller
public class EstudianteControlador {
	
	@Autowired
	private ConfiguracionesServiceImp srvSeting;

	@Autowired
	private IPerfilOperacionesServicio srvOperacion;
	
	@Autowired
	private IPeriodoEscolarService srvPeriodoEscolar;
	
	@Autowired
	private ISeccionService srvSeccion;

	@GetMapping("/estudiantes")
	public String getGradoAll(Model model) {
		model.addAttribute("lstperiodo", srvPeriodoEscolar.onListarPeriodoEscolarEstado("APERTURADO"));
		model.addAttribute("lstseccion",srvSeccion.onListarSeccionAll());
		return "mantenimiento/estudiante";
	}

	@GetMapping("/viewcreatestudent")
	public String getViewCreateNewStudiante(Model model) {
		model.addAttribute("lstperiodo", srvPeriodoEscolar.onListarPeriodoEscolarEstado("APERTURADO"));
		model.addAttribute("lstseccion",srvSeccion.onListarSeccionAll());
		return "mantenimiento/newStudent";
	}
	
	@ModelAttribute
	public void setGenericos(Authentication auth, Model model) {
		PerfilOperaciones actions = srvOperacion.onBuscarPermidoRolMenu(16, auth);
		model.addAttribute("menuLista", srvSeting.onListaMenuPerfil(auth));
		model.addAttribute("cdrSelect", actions.getLeer());
		model.addAttribute("cdrInsert", actions.getCrear());
		model.addAttribute("cdrUpdate", actions.getActualizar());
		model.addAttribute("cdrDelete", actions.getEliminar());
	}
}