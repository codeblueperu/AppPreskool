package com.uisarel.institucion.controlador;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.uisarel.institucion.modelo.entidades.Menu;
import com.uisarel.institucion.servicio.IAdminTemplateService;
import com.uisarel.institucion.servicio.IMenuServicio;


@Controller
public class SubMenuContolador {

	@Autowired
	private IAdminTemplateService srvAdminTemplate;
	
	@Autowired
	private IMenuServicio servicioMenu;

	@GetMapping("/listaSubMenu")
	public String subMenu(Model model) {
		if(!servicioMenu.onValidarRutaPermiso("/listaSubMenu")) {
			return "error/errorPage";
		}

		// MENU DINAMICO
		List<Menu> menu = servicioMenu.listarMenu();
		model.addAttribute("listaMenu", menu);
		// --

		List<Menu> listaSubMenu = servicioMenu.listarSubMenu();
		model.addAttribute("listaSubMenu", listaSubMenu);
		model.addAttribute("titulo", "Sub-Menu");

		return "administracionMenu/subMenu";
	}

	// MetodoRegistrarSubMenu
	@GetMapping("/submenu/nuevo")
	public String registroSubMenu(Model model) {
		if(!servicioMenu.onValidarRutaPermiso("/listaSubMenu")) {
			return "error/errorPage";
		}

		// MENU DINAMICO
		List<Menu> menu = servicioMenu.listarMenu();
		model.addAttribute("listaMenu", menu);
		// --

		Menu subMenu = new Menu();
		model.addAttribute("subMenu", subMenu);

		return "administracionMenu/registroSubMenu";
	}

	@PostMapping("/submenu")
	public String guardarSubMenu(@ModelAttribute("nuevoSubMenu") Menu nuevoSubMenu) {

		try {

			nuevoSubMenu.setFechaCreacionMenu(new Date());
			servicioMenu.insertarMenu(nuevoSubMenu);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.print("Error" + e);

		}
		return "redirect:/listaSubMenu";

	}

	// MetodoActualizarSubMenu
	@GetMapping("/listaSubMenu/editarSubMenu/{idMenu}")
	public String editarSubMenu(@PathVariable(value = "idMenu") int idMenu, Model model) {
		if(!servicioMenu.onValidarRutaPermiso("/listaSubMenu")) {
			return "error/errorPage";
		}
		Menu existe = null;
		if (idMenu > 0) {

			// MENU DINAMICO
			List<Menu> menu = servicioMenu.listarMenu();
			model.addAttribute("listaMenu", menu);
			// --

			existe = servicioMenu.buscarSubMenuId(idMenu);
			model.addAttribute("subMenuEditado", existe);
		}
		return "administracionMenu/editarSubMenu";
	}

	@PostMapping("/listaSubMenu/{idMenu}")
	public String actualizarSubMenu(@ModelAttribute("subMenuEditado") Menu subMenuEditado) {

		subMenuEditado.setFechaActualizacionMenu(new Date());
		servicioMenu.actualizarSubMenu(subMenuEditado);
		return "redirect:/listaSubMenu";
	}
	
	@ModelAttribute
	public void setGenericos( Model model) {		
		model.addAttribute("listaMenu", servicioMenu.onBuscarMenuLogin());
		model.addAttribute("setting", srvAdminTemplate.onMostrarDataTemplateAdmin());
		model.addAttribute("valid", servicioMenu.onOperacionesPerfilMenu(20));
	}
}
