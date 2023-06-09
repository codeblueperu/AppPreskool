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

import com.uisarel.institucion.modelo.entidades.Perfil;
import com.uisarel.institucion.servicio.IAdminTemplateService;
import com.uisarel.institucion.servicio.IMenuServicio;
import com.uisarel.institucion.servicio.IPerfilServicio;

@Controller
public class PerfilControlador {

	@Autowired
	private IPerfilServicio servicioPerfil;
		
	@Autowired
	private IAdminTemplateService srvAdminTemplate;
	
	@Autowired
	private IMenuServicio servicioMenu;
	
	//ListarPerfiles
	@GetMapping("/listaPerfiles")
	public String listarPErfiles(Model model) {
		if(!servicioMenu.onValidarRutaPermiso("/listaPerfiles")) {
			return "error/errorPage";
		}
		List<Perfil> listaPerfiles = servicioPerfil.listaPerfil();
		model.addAttribute("titulo", "Perfiles");
		model.addAttribute("listaPerfiles", listaPerfiles);
		return "adminPerfiles/perfiles";
	}
	
	//MetodoRegistrarPerfiles
		@GetMapping("/perfil/nuevo")
		public String registroPerfil(Model modelo) {
			if(!servicioMenu.onValidarRutaPermiso("/listaPerfiles")) {
				return "error/errorPage";
			}
			Perfil perfil = new Perfil();
			modelo.addAttribute("Perfil", perfil);
			return "adminPerfiles/registroPerfil";
		}	

		@PostMapping("/perfil")
		public String guardarPerfil(@ModelAttribute("nuevoPerfil") Perfil nuevoPerfil) {
			
			try {
				nuevoPerfil.setFechaCreacionPerfil(new Date());
				servicioPerfil.insertarPerfil(nuevoPerfil);
				
			} catch (Exception e) {
				
				// TODO: handle exception
				System.out.print("Error"+e);
			
			}return "redirect:/listaPerfiles";
		}
		
		//MetodoActualizarPErfil
		@GetMapping("/listaPerfiles/editar/{idPerfil}")
		public String editarPerfil(@PathVariable(value="idPerfil") int idPerfil,  Model model) {
			if(!servicioMenu.onValidarRutaPermiso("/listaPerfiles")) {
				return "error/errorPage";
			}
			Perfil existe=null;
			if(idPerfil>0) {
				existe= servicioPerfil.buscarPerfilId(idPerfil);
				model.addAttribute("nuevo",existe);
			}
			return "adminPerfiles/editarPerfil";
		}
		
		@PostMapping("/listaPerfiles/{idPerfil}")
		public String actualizarPerfil(@ModelAttribute("nuevo") Perfil perfilEditado) {
		
			servicioPerfil.actualizarPerfil(perfilEditado);
			return "redirect:/listaPerfiles";
		}
		
		//EliminarPerfil
		@GetMapping("/listaPerfiles/eliminar/{idPerfil}")
		public String eliminarPerfil(@PathVariable(value = "idPerfil") int idPerfil, Model model) {
			if(idPerfil > 0) {
				servicioPerfil.eliminarPerfil(idPerfil);
				}
			return "redirect:/listaPerfiles";
			}
		
		@ModelAttribute
		public void setGenericos(Authentication auth,Model model) {
			model.addAttribute("listaMenu", servicioMenu.onBuscarMenuLogin());
			model.addAttribute("setting",srvAdminTemplate.onMostrarDataTemplateAdmin());	
			model.addAttribute("valid", servicioMenu.onOperacionesPerfilMenu(16));
		}
}
