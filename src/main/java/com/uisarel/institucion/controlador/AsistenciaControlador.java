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
import com.uisarel.institucion.servicio.IPersonalServicio;
import com.uisarel.institucion.servicio.ISeccionService;
import com.uisarel.institucion.servicio.impl.ConfiguracionesServiceImp;

@Controller
public class AsistenciaControlador {

	@Autowired
	private IPerfilOperacionesServicio srvOperacion;

	@Autowired
	private ConfiguracionesServiceImp srvSeting;

	@Autowired
	private IPeriodoEscolarService srvPeriodoEscolar;

	@Autowired
	private ISeccionService srvSeccion;
	
	@Autowired
	private IPersonalServicio srvDocente;

	@GetMapping("/addasistencia")
	public String getCursoAll(Model model) {
		model.addAttribute("lstdocente",srvDocente.onListarPersonalAll());
		model.addAttribute("lstperiodo", srvPeriodoEscolar.onListarPeriodoEscolarEstado("APERTURADO"));
		model.addAttribute("lstseccion",srvSeccion.onListarSeccionAll());
		return "asistencia/asistencia";
	}

	@ModelAttribute
	public void setGenericos(Authentication auth, Model model) {
		PerfilOperaciones actions = srvOperacion.onBuscarPermidoRolMenu(18, auth);
		model.addAttribute("menuLista", srvSeting.onListaMenuPerfil(auth));
		model.addAttribute("cdrSelect", actions.getLeer());
		model.addAttribute("cdrInsert", actions.getCrear());
		model.addAttribute("cdrUpdate", actions.getActualizar());
		model.addAttribute("cdrDelete", actions.getEliminar());
	}
}
