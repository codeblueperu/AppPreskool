package com.uisarel.institucion.controlador;



import java.util.Date;
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
import com.uisarel.institucion.modelo.entidades.Perfil;
import com.uisarel.institucion.modelo.entidades.PerfilMenu;
import com.uisarel.institucion.servicio.IAdminTemplateService;
import com.uisarel.institucion.servicio.IMenuServicio;
import com.uisarel.institucion.servicio.IPerfilMenuServicio;
import com.uisarel.institucion.servicio.IPerfilServicio;

@Controller
public class PerfilMenuControlador {
	
	@Autowired
	private IPerfilMenuServicio servicoPerfilMenu;
	@Autowired
	private IPerfilServicio servicioPerfil;
	@Autowired
	private IMenuServicio servicioMenu;
	@Autowired
	private IAdminTemplateService srvAdminTemplate;

	//LISTAR_PERFIL_MENU
	@GetMapping("/listaPerfilMenu")
	public String listarPerfilMenu(Model model) {
		if(!servicioMenu.onValidarRutaPermiso("/listaPerfilMenu")) {
			return "error/errorPage";
		}
		//MENU DINAMICO
		List<Menu> menu = servicioMenu.listarMenu();
		model.addAttribute("listaMenu", menu);
		//--
		
		List<PerfilMenu> listaPerfilMenu = servicoPerfilMenu.listaPerfilMenu();
		System.err.println(listaPerfilMenu);
		model.addAttribute("listaPerfiles", listaPerfilMenu);
		model.addAttribute("titulo", "Perfil-Menu");
		
		return "adminPerfiles/perfilesOperaciones";
		
	}
		
	//REGISTRAR_PERFIL_MENU
	@GetMapping("/registroPerfilMenu/nuevo")
	public String registroPerfileMenu(Model model) {
				
		//MENU DINAMICO
//		List<Menu> menu = servicioMenu.listarMenu();
//		model.addAttribute("listaMenu", menu);
//		//--
//		
//		//PERFIL OPERACIONES GUARDAR
		List<Perfil> listaPerfiles = servicioPerfil.listaPerfil();
		model.addAttribute("listaPerfiles", listaPerfiles);
//			
		List<Menu> listaMenus = servicioMenu.listarMenuPrincipales();
		model.addAttribute("listaMenus", listaMenus);
//				
//		PerfilMenu perfileMenu = new PerfilMenu();
//		model.addAttribute("PerfilMenu", perfileMenu);
//		
//		model.addAllAttributes(listaMenus);
//		model.addAllAttributes(listaPerfiles);
//			
//		model.addAttribute("perfil", new Perfil());
//		model.addAttribute("menu", new Menu());
	
		
		return "adminPerfiles/registroPerfilMenu";
		}	
	
	
	@PostMapping("/perfilMenu")
	public String guardarPerfilMenu(@ModelAttribute("nuevoPerfilMenu") @RequestParam("fkPerfil") int idPerfil, @RequestParam("fkMenu") List<Integer> idMenu
			,@RequestParam("estado") List<String> estado ) {	
		try {    	
			//servicioPerfil.insertarPl(perfil);
			
			for (Integer Menuid : idMenu) {

				Perfil perfil = servicioPerfil.buscarPerfilId(idPerfil);
				Menu menu = servicioMenu.buscarMenuId(Menuid);
				if (idPerfil != 0) {
					PerfilMenu perfilMenu = new PerfilMenu();
					perfilMenu.setMenu(menu);
					perfilMenu.SetPerfil(perfil);
					perfilMenu.setFechaCreacionPerMen(new Date());
					for (String est : estado) {
						perfilMenu.setEstado(est);
					}
					
					servicoPerfilMenu.insertarPefilMenu(perfilMenu);
					//perfiloperaciones.setCrear(crear);

				}
				
			}
			
								
			} catch (Exception e) {
			// TODO: handle exception
				System.out.print("Error"+e);

		}return "redirect:/registroPerfilMenu/nuevo";
		
	}
	
	@ModelAttribute
	public void setGenericos(Authentication auth, Model model) {
		model.addAttribute("listaMenu", servicioMenu.onBuscarMenuLogin());
		model.addAttribute("setting", srvAdminTemplate.onMostrarDataTemplateAdmin());
		model.addAttribute("valid", servicioMenu.onOperacionesPerfilMenu(17));
	}
}
