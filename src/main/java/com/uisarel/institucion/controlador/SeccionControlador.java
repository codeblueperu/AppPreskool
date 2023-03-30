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

import com.uisarel.institucion.modelo.entidades.PerfilOperaciones;
import com.uisarel.institucion.modelo.entidades.Seccion;
import com.uisarel.institucion.servicio.IPerfilOperacionesServicio;
import com.uisarel.institucion.servicio.ISeccionService;
import com.uisarel.institucion.servicio.impl.ConfiguracionesServiceImp;

@Controller
public class SeccionControlador {
	@Autowired
	private ISeccionService srvSeccion;

	@Autowired
	private ConfiguracionesServiceImp srvSeting;

	@Autowired
	private IPerfilOperacionesServicio srvOperacion;

	@GetMapping("/seccion")
	public String getSeccionAll(Model model) {
		Seccion datax = new Seccion();
		model.addAttribute("data", datax);

		return "mantenimiento/seccion";
	}

	@PostMapping("/guardarseccion")
	public String postGuardarSeccion(Seccion dataseccion, BindingResult result, RedirectAttributes redirectAttrs) {
		if (result.hasErrors()) {
			return "mantenimiento/seccion";
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
		model.addAttribute("data", srvSeccion.onBuscarSeccionID(idseccion));

		return "mantenimiento/seccion";
	}

	@GetMapping("/deleteSeccionID/{id}")
	public String getDeleteSeccionID(@PathVariable("id") int idseccion, Model model) {
		srvSeccion.onEliminarSeccionID(idseccion);
		model.addAttribute("msg", "Seccion eliminado");

		return "redirect:/seccion";
	}

	@ModelAttribute
	public void setGenericos(Authentication auth, Model model) {
		PerfilOperaciones actions = srvOperacion.onBuscarPermidoRolMenu(15, auth);
		model.addAttribute("lstdata", srvSeccion.onListarSeccionAll());
		model.addAttribute("menuLista", srvSeting.onListaMenuPerfil(auth));
		model.addAttribute("cdrSelect", actions.getLeer());
		model.addAttribute("cdrInsert", actions.getCrear());
		model.addAttribute("cdrUpdate", actions.getActualizar());
		model.addAttribute("cdrDelete", actions.getEliminar());
	}
}
