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

import com.uisarel.institucion.modelo.entidades.Grado;
import com.uisarel.institucion.servicio.IAdminTemplateService;
import com.uisarel.institucion.servicio.IGradoService;
import com.uisarel.institucion.servicio.IMenuServicio;

@Controller
public class GradoController {
	@Autowired
	private IGradoService srvGrado;
	
	@Autowired
	private IAdminTemplateService srvAdminTemplate;
	
	@Autowired
	private IMenuServicio servicioMenu;

	@GetMapping("/grado")
	public String getGradoAll(Model model) {
		if(!servicioMenu.onValidarRutaPermiso("/grado")) {
			return "error/errorPage";
		}
		Grado datax = new Grado();
		model.addAttribute("data", datax);

		return "estudiantes/grado";
	}

	@PostMapping("/guardargrado")
	public String postGuardarGrado(Grado datagrado, BindingResult result, RedirectAttributes redirectAttrs) {
		if (result.hasErrors()) {
			return "estudiantes/grado";
		}
		int codeAction = datagrado.getIdGrado();
		//datagrado.setGrado("ero");
		if (codeAction != 0) {
			srvGrado.onUpdateGrado(datagrado);
		} else {
			System.err.println(datagrado);
			srvGrado.onGuardarGradoNuevo(datagrado);
		}

		redirectAttrs.addFlashAttribute("mensaje", (codeAction != 0 ? "Muy bien! el grado fue actualizado con éxito."
				: "Muy bien! el grado se registro con éxito.")).addFlashAttribute("clase", "success");

		return "redirect:/grado";
	}

	@GetMapping("/buscarGradoID/{id}")
	public String getBuscarGradoID(@PathVariable(value = "id") int idGrado, Model model) {
		
		model.addAttribute("data", srvGrado.onBuscarGradoId(idGrado));

		return "estudiantes/grado";
	}

	@GetMapping("/deleteGradoID/{id}")
	public String getDeleteGradoID(@PathVariable("id") int idGrado, Model model) {
		srvGrado.onEliminarGrado(idGrado);
		model.addAttribute("msg", "Grado eliminado");

		return "redirect:/grado";
	}

	@ModelAttribute
	public void setGenericos(Authentication auth, Model model) {
		model.addAttribute("listaMenu",  servicioMenu.onBuscarMenuLogin());
		model.addAttribute("setting",srvAdminTemplate.onMostrarDataTemplateAdmin());
		model.addAttribute("valid", servicioMenu.onOperacionesPerfilMenu(6));
		model.addAttribute("lstdata", srvGrado.onListarGradosAll());
	}
}
