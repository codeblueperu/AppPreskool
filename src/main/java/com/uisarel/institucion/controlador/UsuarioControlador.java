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
import com.uisarel.institucion.modelo.entidades.Usuario;
import com.uisarel.institucion.modelo.entidades.UsuarioPerfil;
import com.uisarel.institucion.servicio.IAdminTemplateService;
import com.uisarel.institucion.servicio.IMenuServicio;
import com.uisarel.institucion.servicio.IPerfilServicio;
import com.uisarel.institucion.servicio.IUsuarioPerfilServicio;
import com.uisarel.institucion.servicio.IUsuarioServicio;

@Controller
public class UsuarioControlador {

	@Autowired
	private IUsuarioServicio servicioUsuario;
	
	@Autowired
	private IUsuarioPerfilServicio servicioUsuarioPerfil;
	
	@Autowired
	private IPerfilServicio servicioPerfil;

	@Autowired
	private IAdminTemplateService srvAdminTemplate;
	
	@Autowired
	private IMenuServicio servicioMenu;

	// ListarUsuarios
	@GetMapping("/listaUsuarios")
	public String listarUsuarios(Model model) {
		if(!servicioMenu.onValidarRutaPermiso("/listaUsuarios")) {
			return "error/errorPage";
		}
		List<Usuario> listaUsuarios = servicioUsuario.listaUsuario();
		model.addAttribute("titulo", "Usuarios");
		model.addAttribute("listaUsuarios", listaUsuarios);
		return "adminUser/usuarios";
	}

	// MetodoRegistrarUsuario
	@GetMapping("/usuario/nuevo")
	public String registroUsuario(Model modelo) {
		if(!servicioMenu.onValidarRutaPermiso("/listaUsuarios")) {
			return "error/errorPage";
		}
		List<Perfil> listaPerfiles = servicioPerfil.listaPerfil();
		modelo.addAttribute("listaPerfiles", listaPerfiles);
		Usuario usuario = new Usuario();
		modelo.addAttribute("Usuario", usuario);
		return "adminUser/registroUsuario";
	}

	@PostMapping("/usuario")
	public String guardarUsuario(@ModelAttribute("nuevo") Usuario nuevoUsuario, UsuarioPerfil nuevoUsuarioPerfil) {

		try {

			nuevoUsuario.setFechaCreacionUsuario(new Date());
			nuevoUsuarioPerfil.setEstado("Activo");
			nuevoUsuarioPerfil.setFkUsuario(nuevoUsuario);
			nuevoUsuarioPerfil.setFechaCreacionPerfil(new Date());
			servicioUsuario.insertarUsuario(nuevoUsuario);
			servicioUsuarioPerfil.insertarUsuaPerf(nuevoUsuarioPerfil);
		} catch (Exception e) {

			// TODO: handle exception
			System.out.print("Error" + e);

		}
		return "redirect:/listaUsuarios";
	}

	// MetodoActualizarUsuario
	@GetMapping("/listaUsuarios/editar/{idUsuario}")
	public String editarUsuario(@PathVariable(value = "idUsuario") int idUsuario, Model model) {
		Usuario existe = null;
		if (idUsuario > 0) {
			existe = servicioUsuario.buscarUsuarioId(idUsuario);
			model.addAttribute("nuevo", existe);
		}
		return "adminUser/editarUsuario";
	}

	@PostMapping("/listaUsuarios/{idUsuario}")
	public String actualizarUsuario(@ModelAttribute("nuevo") Usuario usuarioEditado) {
		// nuevo.setEstadoUsuario("1");
		usuarioEditado.setFechaModificacionUsuario(new Date());
		servicioUsuario.actualizarUsuario(usuarioEditado);
		return "redirect:/listaUsuarios";
	}

	// EliminarUsuario
	@GetMapping("/listaUsuarios/eliminar/{idUsuario}")
	public String eliminarUsuario(@PathVariable(value = "idUsuario") int idUsuario, Model model) {
		if (idUsuario > 0) {
			servicioUsuario.eliminarUsuario(idUsuario);
		}
		return "redirect:/listaUsuarios";
	}

	@ModelAttribute
	public void setGenericos(Authentication auth, Model model) {
		model.addAttribute("listaMenu", servicioMenu.onBuscarMenuLogin());
		model.addAttribute("setting", srvAdminTemplate.onMostrarDataTemplateAdmin());
		
	}
}
