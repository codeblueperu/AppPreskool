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

import com.uisarel.institucion.modelo.entidades.PeriodoEscolar;
import com.uisarel.institucion.servicio.IAdminTemplateService;
import com.uisarel.institucion.servicio.IMenuServicio;
import com.uisarel.institucion.servicio.IPeriodoEscolarService;

@Controller
public class PeriodoEscolarControlador {

	@Autowired
	private IPeriodoEscolarService srvPeriodo;

	@Autowired
	private IAdminTemplateService srvAdminTemplate;
	
	@Autowired
	private IMenuServicio servicioMenu;

	@GetMapping("/periodoescolar")
	public String getPeriodoEscolar(Model model) {
		if(!servicioMenu.onValidarRutaPermiso("/periodoescolar")) {
			return "error/errorPage";
		}
		
		PeriodoEscolar datax = new PeriodoEscolar();
		model.addAttribute("data", datax);

		return "configuracion/periodoEscolar";
	}

	@PostMapping("/guardarPeriodoEscolar")
	public String postGuardarPeriodoEscolar(PeriodoEscolar dataperiodo, BindingResult result,
			RedirectAttributes redirectAttrs) {
		if (result.hasErrors()) {
			return "configuracion/periodoEscolar";
		}
		int codeAction = dataperiodo.getIdPeriodoEscolar();
		PeriodoEscolar response = (dataperiodo.getIdPeriodoEscolar() != 0
				? srvPeriodo.onUpdatePeriodoEscolar(dataperiodo)
				: srvPeriodo.onGuardarNuevoPeriodoEscolar(dataperiodo));
		if (response == null) {
			redirectAttrs.addFlashAttribute("mensaje", "Upps! el periodo escolar ya éxiste.").addFlashAttribute("clase",
					"warning");
		} else {
			redirectAttrs
					.addFlashAttribute("mensaje",
							(codeAction != 0 ? "Muy bien! el periodo escolar fue actualizado con éxito."
									: "Muy bien! el periodo escolar se registro con éxisto."))
					.addFlashAttribute("clase", "success");
		}

		return "redirect:/periodoescolar";
	}

	@GetMapping("/buscarPeriodoEscolarID/{id}")
	public String getBuscarPeriodoEscolarID(@PathVariable(value = "id") int idPeriodo, Model model) {
		model.addAttribute("data", srvPeriodo.onBuscarPeriodoEscolarID(idPeriodo));

		return "configuracion/periodoEscolar";
	}

	@GetMapping("/deletePeriodoEscolarID/{id}")
	public String getDeletePeriodoEscolarID(@PathVariable("id") int idPeriodo, Model model) {
		srvPeriodo.onEliminarPeriodoEscolar(idPeriodo);
		model.addAttribute("msg", "Periodo escolar eliminado");

		return "redirect:/periodoescolar";
	}

	@ModelAttribute
	public void setGenericos(Authentication auth, Model model) {		
		model.addAttribute("listaMenu", servicioMenu.onBuscarMenuLogin());
		model.addAttribute("setting",srvAdminTemplate.onMostrarDataTemplateAdmin());
		model.addAttribute("lstdata", srvPeriodo.onListarPeriodoEscolarAll());
	}

}
