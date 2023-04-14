package com.uisarel.institucion.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uisarel.institucion.modelo.entidades.Menu;
import com.uisarel.institucion.modelo.entidades.Operaciones;
import com.uisarel.institucion.modelo.entidades.Perfil;
import com.uisarel.institucion.modelo.entidades.PerfilOperaciones;
import com.uisarel.institucion.servicio.IAdminTemplateService;
import com.uisarel.institucion.servicio.IMenuServicio;
import com.uisarel.institucion.servicio.IOperacionesServicio;
import com.uisarel.institucion.servicio.IPerfilOperacionesServicio;
import com.uisarel.institucion.servicio.IPerfilServicio;

@Controller
public class PerfilOperacionesControlador {

	@Autowired
	private IPerfilOperacionesServicio servicoPerfilOperaciones;
	
	@Autowired
	private IPerfilServicio servicioPerfil;
	
	@Autowired
	private IOperacionesServicio servicioOperaciones;
	
	@Autowired
	private IAdminTemplateService srvAdminTemplate;
	
	@Autowired
	private IMenuServicio servicioMenu;

	// Listar_PerfilOperaciones
	@GetMapping("/listaPerfilesOperaciones")
	public String listarPerfilOperaciones(Model model) {
		if(!servicioMenu.onValidarRutaPermiso("/listaPerfilesOperaciones")) {
			return "error/errorPage";
		}

		// MENU DINAMICO
		List<Menu> menu = servicioMenu.listarMenu();
		model.addAttribute("listaMenu", menu);
		// --

		List<PerfilOperaciones> listaPerfilOperaciones = servicoPerfilOperaciones.listaPefilOperaciones();
		model.addAttribute("titulo", "PerfilOperaciones");
		model.addAttribute("listaPerfilOperaciones", listaPerfilOperaciones);

		List<Perfil> listaPerfiles = servicioPerfil.listaPerfil();
		model.addAttribute("listaPerfiles", listaPerfiles);

		return "adminOperaciones/perfilesOperaciones";
	}

	// MetodoRegistrarUsuario
	@GetMapping("/registroPerfilesOperaciones/nuevo")
	public String registroPerfilOperaciones(Model model) {
		// MENU DINAMICO
//		List<Menu> menu = servicioMenu.listarMenu();
//		model.addAttribute("listaMenu", menu);
		// --

		// PERFIL OPERACIONES GUARDAR
		List<Perfil> listaPerfiles = servicioPerfil.listaPerfil();
		model.addAttribute("listaPerfiles", listaPerfiles);

//		List<Operaciones> listaOperaciones = servicioOperaciones.listaOperaciones();
//		model.addAttribute("listaOperaciones", listaOperaciones);

//		PerfilOperaciones perfilOperaciones = new PerfilOperaciones();
//		model.addAttribute("PerfilOperaciones", perfilOperaciones);
//
//		model.addAttribute("perfil", new Perfil());
//		model.addAttribute("operaciones", new Operaciones());
//
//		List<Menu> listaMenu = servicioMenu.listarMenu();
//		model.addAttribute("listaMenu", listaMenu);

		return "adminOperaciones/registroPerfilesOperaciones";
	}

	@PostMapping("/perfilOperacion")
	public String guardarPefilOperaciones(
			@ModelAttribute("nuevoPerfilesOperaciones") @RequestParam("fkPerfil") int idPerfil,
			@RequestParam("fkOperaciones") List<Integer> idOperaciones) {
		try {

			System.err.println(idOperaciones);
			for (Integer Operacioensid : idOperaciones) {

				Perfil perfil = servicioPerfil.buscarPerfilId(idPerfil);
				Operaciones operaciones = servicioOperaciones.buscarOperacionesId(Operacioensid);
				
				// Menu menu = servicioMenu.buscarMenuId(Operacioensid);

				if (idPerfil != 0) {

					PerfilOperaciones perfiloperaciones = new PerfilOperaciones();
					perfiloperaciones.setOperaciones(operaciones);
					perfiloperaciones.SetPerfil(perfil);
					// perfiloperaciones.setCrear(Operacioensid)
					/*
					 * for (Integer integer : crear) { perfiloperaciones.setCrear(integer);
					 * 
					 * }
					 */

					servicoPerfilOperaciones.insertarPefilOperaciones(perfiloperaciones);

				}

			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.print("Error" + e);

		}
		return "redirect:/registroPerfilesOperaciones/nuevo";

	}
	
	@ModelAttribute
	public void setGenericos(Authentication auth, Model model) {
		model.addAttribute("listaMenu", servicioMenu.onBuscarMenuLogin());
		model.addAttribute("setting", srvAdminTemplate.onMostrarDataTemplateAdmin());
		model.addAttribute("valid", servicioMenu.onOperacionesPerfilMenu(18));
	}

}
