package com.uisarel.institucion.controlador;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uisarel.institucion.modelo.entidades.PerfilOperaciones;
import com.uisarel.institucion.servicio.IMenuServicio;
import com.uisarel.institucion.servicio.IOperacionesServicio;
import com.uisarel.institucion.servicio.IPerfilMenuServicio;
import com.uisarel.institucion.servicio.IPerfilOperacionesServicio;
import com.uisarel.institucion.servicio.IUsuarioServicio;

@RestController
@RequestMapping("/webservice/v1")
public class AjaxSettingController {

	@Autowired
	private IMenuServicio servicioMenu;

	@Autowired
	private IPerfilMenuServicio srvPerfilMenu;

	@Autowired
	private IUsuarioServicio srvuser;

	@Autowired
	private IOperacionesServicio srvOperaciones;

	@Autowired
	private IPerfilOperacionesServicio srvPerfilOperaciones;

	@GetMapping("/buscarSubMenu")
	public ResponseEntity<?> getbuscarSubMenu(@RequestParam("idperfil") int idperfil,
			@RequestParam("idmenu") int idmenu) {
		HashMap<String, Object> response = new HashMap<>();
		response.put("data", servicioMenu.onBuscarSubmenu(idmenu));
		response.put("asigned", servicioMenu.onBuscarMenuusuarioPerfil(idmenu, idperfil));
		return ResponseEntity.ok(response);
	}

	@GetMapping("/saveperfilmenu")
	public ResponseEntity<?> getBuscarDataSubMenu(@RequestParam("idperfil") int idperfil,
			@RequestParam("idmenu") int idmenu, @RequestParam("estado") String status) {

		srvPerfilMenu.onSavePerfilMenu(idperfil, idmenu, status);

		HashMap<String, Object> map = new HashMap<>();
		map.put("data", "estado actualizado!");

		return ResponseEntity.ok(map);
	}

	@PostMapping("/updatepassword")
	public ResponseEntity<?> postUpdatePassword(@RequestParam("password") String password) {
		HashMap<String, Object> response = new HashMap<>();
		response.put("data", srvuser.updatePasswordUser(password));
		return ResponseEntity.ok(response);
	}

	@GetMapping("/listaOperaciones")
	public ResponseEntity<?> getListaOperaciones(@RequestParam("idperfil") int idperfil) {
		HashMap<String, Object> response = new HashMap<>();
		
		response.put("data", srvOperaciones.listaOperaciones());
		response.put("asigned", srvPerfilOperaciones.onBuscarPerfilOperacionAsinados(idperfil));
		return ResponseEntity.ok(response);
	}

	@RequestMapping(value = "/guardarPerfilOperaciones", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<?> pointGuardarPerfilOperaciones(@RequestBody PerfilOperaciones perfilOperaciones) {

		srvPerfilOperaciones.insertarPefilOperaciones(perfilOperaciones);
		HashMap<String, Object> response = new HashMap<>();
		response.put("data", "Estado Actualizado");
		return ResponseEntity.ok(response);
	}
}
