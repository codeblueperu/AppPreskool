package com.uisarel.institucion.controlador;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
public class MenuContolador {

	@Autowired
	private IMenuServicio servicioMenu;
	
	@Autowired
	private IAdminTemplateService srvAdminTemplate;

	@GetMapping("/listaMenu")
	public String index(Model model) {

		// MENU DINAMICO
		List<Menu> menu = servicioMenu.listarMenu();
		model.addAttribute("listaMenu", menu);
		// --

		List<Menu> listaMenu = servicioMenu.listarMenu();
		model.addAttribute("listaMenu", listaMenu);
		model.addAttribute("titulo", "Menu");

		return "administracionMenu/menus";
	}

	// MetodoRegistrarMenu
	@GetMapping("/menu/nuevo")
	public String registroMenu(Model model) {

		// MENU DINAMICO
		List<Menu> menu = servicioMenu.listarMenu();
		model.addAttribute("listaMenu", menu);
		// --

		List<Menu> listaMenu = servicioMenu.listarMenu();
		model.addAttribute("listaMenu", listaMenu);

		return "administracionMenu/registroMenu";
	}

	@PostMapping("/menu")
	public String guardarMenu(@ModelAttribute("nuevoMenu") Menu nuevoMenu) {

		try {

			nuevoMenu.setFechaCreacionMenu(new Date());
			servicioMenu.insertarMenu(nuevoMenu);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.print("Error" + e);

		}
		return "redirect:/listaMenu";
	}

	// MetodoActualizarMenu
	@GetMapping("/listaMenu/editar/{idMenu}")
	public String editarMenu(@PathVariable(value = "idMenu") int idMenu, Model model) {

		// MENU DINAMICO
		List<Menu> menu = servicioMenu.listarMenu();
		model.addAttribute("listaMenu", menu);
		// --

		Menu existe = null;
		if (idMenu > 0) {

			existe = servicioMenu.buscarMenuId(idMenu);
			model.addAttribute("menuEditado", existe);
		}
		return "administracionMenu/editarMenu";
	}

	@PostMapping("/listaMenu/{idMenu}")
	public String actualizarMenu(@ModelAttribute("menuEditado") Menu menuEditado) {

		menuEditado.setFechaActualizacionMenu(new Date());
		servicioMenu.actualizarMenu(menuEditado);
		return "redirect:/listaMenu";
	}

	// EliminarMenu
	@GetMapping("/listaMenu/eliminar/{idMenu}")
	public String eliminarMenu(@PathVariable(value = "idMenu") int idMenu, Model model) {
		if (idMenu > 0) {
			servicioMenu.eliminarMenu(idMenu);
		}
		return "redirect:/listaMenu";
	}
	
	@ModelAttribute
	public void setGenericos(Authentication auth, Model model) {
		model.addAttribute("listaMenu", servicioMenu.onBuscarMenuLogin());
		model.addAttribute("setting", srvAdminTemplate.onMostrarDataTemplateAdmin());
	}
}
