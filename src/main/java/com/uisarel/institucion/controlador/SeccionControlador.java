package com.uisarel.institucion.controlador;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uisarel.institucion.modelo.entidades.Seccion;
import com.uisarel.institucion.servicio.IAdminTemplateService;
import com.uisarel.institucion.servicio.IMenuServicio;
import com.uisarel.institucion.servicio.ISeccionService;

@Controller
public class SeccionControlador {
	@Autowired
	private ISeccionService srvSeccion;

	@Autowired
	private IAdminTemplateService srvAdminTemplate;
	
	@Autowired
	private IMenuServicio servicioMenu;

	@GetMapping("/seccion")
	public String getSeccionAll(Model model) {
		if(!servicioMenu.onValidarRutaPermiso("/seccion")) {
			return "error/errorPage";
		}
		Seccion datax = new Seccion();
		model.addAttribute("data", datax);

		return "estudiantes/seccion";
	}

	@PostMapping("/guardarseccion")
	public String postGuardarSeccion(Seccion dataseccion, BindingResult result, RedirectAttributes redirectAttrs) {
		if (result.hasErrors()) {
			return "estudiantes/seccion";
		}
		int codeAction = dataseccion.getIdSeccion();
		if (codeAction != 0) {
			srvSeccion.onUpdateSeccion(dataseccion);
		} else {
			srvSeccion.onGuardarSeccionNuevo(dataseccion);
		}

		redirectAttrs.addFlashAttribute("mensaje", (codeAction != 0 ? "Muy bien! la seccion fue actualizado con éxito."
				: "Muy bien! la seccion se registro con éxisto.")).addFlashAttribute("clase", "success");

		return "redirect:/seccion";
	}

	@GetMapping("/buscarSeccionID/{id}")
	public String getBuscarSeccionID(@PathVariable(value = "id") int idseccion, Model model) {
		if(!servicioMenu.onValidarRutaPermiso("/seccion")) {
			return "error/errorPage";
		}
		model.addAttribute("data", srvSeccion.onBuscarSeccionID(idseccion));

		return "estudiantes/seccion";
	}

	@GetMapping("/deleteSeccionID/{id}")
	public String getDeleteSeccionID(@PathVariable("id") int idseccion, Model model) {
		
		srvSeccion.onEliminarSeccionID(idseccion);
		model.addAttribute("msg", "Seccion eliminado");

		return "redirect:/seccion";
	}

	@ModelAttribute
	public void setGenericos(Authentication auth, Model model) {
		model.addAttribute("listaMenu", servicioMenu.onBuscarMenuLogin());
		model.addAttribute("setting",srvAdminTemplate.onMostrarDataTemplateAdmin());
		model.addAttribute("lstdata", srvSeccion.onListarSeccionAll());
	}
}
