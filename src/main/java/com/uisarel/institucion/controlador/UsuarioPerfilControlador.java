package com.uisarel.institucion.controlador;

import java.util.ArrayList;
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
import com.uisarel.institucion.modelo.entidades.Perfil;
import com.uisarel.institucion.modelo.entidades.Usuario;
import com.uisarel.institucion.modelo.entidades.UsuarioPerfil;
import com.uisarel.institucion.servicio.IAdminTemplateService;
import com.uisarel.institucion.servicio.IMenuServicio;
import com.uisarel.institucion.servicio.IPerfilServicio;
import com.uisarel.institucion.servicio.IUsuarioPerfilServicio;
import com.uisarel.institucion.servicio.IUsuarioServicio;

@Controller
public class UsuarioPerfilControlador {

	@Autowired
	private IUsuarioPerfilServicio servicioUsuarioPerfil;
	
	@Autowired
	private IPerfilServicio servicioPerfil;
	
	@Autowired
	private IUsuarioServicio servicioUsuario;
	
	@Autowired
	private IAdminTemplateService srvAdminTemplate;

	@Autowired
	private IMenuServicio servicioMenu;
	
	List<Menu> lstMenuAcceso = new ArrayList<>();

	// Listar
	@GetMapping("/listaUsuariosPerfiles")
	public String listarUsuariosPerfiles(Model model) {
		List<UsuarioPerfil> listaUsuariosPerfiles = servicioUsuarioPerfil.listaUsuarioPerfil();
		model.addAttribute("titulo", "UsuariosPerfiles");
		model.addAttribute("listaUsuariosPerfiles", listaUsuariosPerfiles);
		return "adminUser/usuariosPerfiles";
	}

	// MetodoRegistrarUsuarioPerfiles
	@GetMapping("/usarioPerfil/nuevo")
	public String registroUsuarioPerfil(Model modelo) {
		List<Perfil> listaPerfiles = servicioPerfil.listaPerfil();
		modelo.addAttribute("listaPerfiles", listaPerfiles);

		List<Usuario> listaUsuarios = servicioUsuario.listaUsuario();
		modelo.addAttribute("listaUsuarios", listaUsuarios);

		UsuarioPerfil usuarioperfil = new UsuarioPerfil();
		modelo.addAttribute("UsuarioPerfil", usuarioperfil);
		return "adminUser/registroUsuarioPerfil";
	}

	@PostMapping("/usuarioperfil")
	public String guardarUsuarioPerfil(@ModelAttribute("nuevoUsuarioPerfil") UsuarioPerfil nuevoUsuarioPerfil) {

		try {
			nuevoUsuarioPerfil.setFechaCreacionPerfil(new Date());
			servicioUsuarioPerfil.insertarUsuaPerf(nuevoUsuarioPerfil);

		} catch (Exception e) {

			// TODO: handle exception
			System.out.print("Error" + e);

		}
		return "redirect:/listaUsuariosPerfiles";
	}

	// MetodoActualizarPErfil
	@GetMapping("/listaUsuariosPerfiles/editar/{idUsuarioPerfil}")
	public String editarUsuarioPerfil(@PathVariable(value = "idUsuarioPerfil") int idUsuarioPerfil, Model modelo) {
		UsuarioPerfil existe = null;
		if (idUsuarioPerfil > 0) {

			List<Usuario> listaUsuarios = servicioUsuario.listaUsuario();
			modelo.addAttribute("listaUsuarios", listaUsuarios);

			List<Perfil> listaPerfiles = servicioPerfil.listaPerfil();
			modelo.addAttribute("listaPerfiles", listaPerfiles);

			existe = servicioUsuarioPerfil.buscarUsuarioPerfilId(idUsuarioPerfil);
			modelo.addAttribute("UsuarioPerfilActualizado", existe);
		}
		return "adminUser/editarUsuarioPerfil";

	}

	@PostMapping("/listaUsuariosPerfiles/{idUsuarioPerfil}")
	public String actualizarUsuarioPerfil(
			@ModelAttribute("UsuarioPerfilActualizado") UsuarioPerfil usuarioPerfilEditado) {

		usuarioPerfilEditado.setFechaModificacionPerfil(new Date());
		servicioUsuarioPerfil.actualizarUsuarioPerfil(usuarioPerfilEditado);
		return "redirect:/listaUsuariosPerfiles";
	}

	// EliminarUsuarioPerfil
	@GetMapping("/listaUsuariosPerfiles/eliminar/{idUsuarioPerfil}")
	public String eliminarUsuarioPerfil(@PathVariable(value = "idUsuarioPerfil") int idUsuarioPerfil, Model model) {
		if (idUsuarioPerfil > 0) {
			servicioUsuarioPerfil.eliminarUsuarioPerfil(idUsuarioPerfil);
		}
		return "redirect:/listaUsuariosPerfiles";
	}

	@ModelAttribute
	public void setGenericos(Authentication auth, Model model) {
		lstMenuAcceso = servicioMenu.listarMenu();
		model.addAttribute("listaMenu", lstMenuAcceso);
		model.addAttribute("setting", srvAdminTemplate.onMostrarDataTemplateAdmin());		
	}

}
