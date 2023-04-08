package com.uisarel.institucion.controlador;

import java.util.ArrayList;
import java.util.List;

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

import com.uisarel.institucion.modelo.entidades.Cursos;
import com.uisarel.institucion.modelo.entidades.Menu;
import com.uisarel.institucion.servicio.IAdminTemplateService;
import com.uisarel.institucion.servicio.ICursosService;
import com.uisarel.institucion.servicio.IMenuServicio;

@Controller
public class CursoControlador {

	@Autowired
	private ICursosService srvCurso;
	
	@Autowired
	private IAdminTemplateService srvAdminTemplate;
	
	@Autowired
	private IMenuServicio servicioMenu;

	List<Menu> lstMenuAcceso = new ArrayList<>();

	@GetMapping("/curso")
	public String getCursoAll(Model model) {
		Cursos datax = new Cursos();
		model.addAttribute("data", datax);
		return "docentes/curso";
	}

	@PostMapping("/guardarcurso")
	public String postGuardaCurso(Cursos datacurso, BindingResult result, RedirectAttributes redirectAttrs) {
		if (result.hasErrors()) {
			return "docentes/curso";
		}
		int codeAction = datacurso.getIdCurso();
		
		if (codeAction != 0) {
			srvCurso.onUpdateCurso(datacurso);
		} else {
			srvCurso.onGuardarCursoNuevo(datacurso);
		}

		redirectAttrs.addFlashAttribute("mensaje", (codeAction != 0 ? "Muy bien! el curso fue actualizado con éxito."
				: "Muy bien! el curso se registro con éxito.")).addFlashAttribute("clase", "success");

		return "redirect:/curso";
	}

	@GetMapping("/buscarCursorID/{id}")
	public String getBuscarGradoID(@PathVariable(value = "id") int idcurso, Model model) {
		model.addAttribute("data", srvCurso.onBuscarCursoID(idcurso));

		return "docentes/curso";
	}

	@GetMapping("/deleteCursoID/{id}")
	public String getDeleteGradoID(@PathVariable("id") int idcurso, Model model) {
		srvCurso.onEliminarCurso(idcurso);
		model.addAttribute("msg", "Curso eliminado");

		return "redirect:/curso";
	}

	@ModelAttribute
	public void setGenericos(Authentication auth, Model model) {
		lstMenuAcceso = servicioMenu.listarMenu();
		model.addAttribute("listaMenu", lstMenuAcceso);
		model.addAttribute("setting",srvAdminTemplate.onMostrarDataTemplateAdmin());
		model.addAttribute("lstdata", srvCurso.onListarCursos("ALL"));
	}
}
