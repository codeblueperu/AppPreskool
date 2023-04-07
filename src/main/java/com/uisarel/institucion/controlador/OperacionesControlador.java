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

import com.uisarel.institucion.modelo.entidades.Operaciones;
import com.uisarel.institucion.servicio.IAdminTemplateService;
import com.uisarel.institucion.servicio.IOperacionesServicio;
import com.uisarel.institucion.servicio.impl.ConfiguracionesServiceImp;

@Controller
public class OperacionesControlador {
	
	@Autowired
	private IOperacionesServicio servicioOperaciones;
	
	@Autowired
	private ConfiguracionesServiceImp srvSeting;
	
	@Autowired
	private IAdminTemplateService srvAdminTemplate;
	
	//ListarPerfiles
	@GetMapping("/listaOperaciones")
	public String listarOperaciones(Model model) {
		List<Operaciones> listaOperaciones = servicioOperaciones.listaOperaciones();
		model.addAttribute("titulo", "Operaciones");
		model.addAttribute("listaOperaciones", listaOperaciones);
		return "operaciones";
	}
	
	//MetodoRegistrarPerfiles
	@GetMapping("/operacion/nuevo")
	public String registroOperacion(Model modelo) {
		Operaciones operacion = new Operaciones();
		modelo.addAttribute("Operaciones", operacion);
		return "registroOperaciones";
		}	
	
	@PostMapping("/operaciones")
	public String guardarPerfil(@ModelAttribute("nuevoOperaciones") Operaciones nuevoOperacion) {
		try {
			nuevoOperacion.setFechaCreacionOperacion(new Date());
			servicioOperaciones.insertarOperaciones(nuevoOperacion);
					
				} catch (Exception e) {	
					// TODO: handle exception
					System.out.print("Error"+e);
				}return "redirect:/listaOperaciones";
			}
			
	//MetodoActualizarPErfil
	@GetMapping("/listaOperaciones/editar/{idOperaciones}")
	public String editarOperaciones(@PathVariable(value="idOperaciones") int idOperaciones,  Model model) {
		Operaciones existe=null;
		if(idOperaciones>0) {
			existe= servicioOperaciones.buscarOperacionesId(idOperaciones); 
			model.addAttribute("operacionesActualizada",existe);
		
		}return "editarOperaciones";
		}
	@PostMapping("/listaOperaciones/{idOperaciones}")
	public String actualizarOperaciones(@ModelAttribute("operacionesActualizada") Operaciones operacionesEditado) {
		operacionesEditado.setFechaModificacionOperacion(new Date());
		servicioOperaciones.actualizarOperaciones(operacionesEditado);
		return "redirect:/listaOperaciones";
		}
	
	//EliminarPerfil
	@GetMapping("/listaOperaciones/eliminar/{idOperaciones}")
	public String eliminarOperaciones(@PathVariable(value = "idOperaciones") int idOperaciones, Model model) {
		if(idOperaciones > 0) {
			servicioOperaciones.eliminarOperaciones(idOperaciones);
			}
		return "redirect:/listaOperaciones";
		}
	
	@ModelAttribute
	public void setGenericos(Authentication auth,Model model) {
		model.addAttribute("setting",srvAdminTemplate.onMostrarDataTemplateAdmin());
		model.addAttribute("menuLista", srvSeting.onListaMenuPerfil(auth));
	}

}
