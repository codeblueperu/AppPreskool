package com.uisarel.institucion.controlador;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uisarel.institucion.modelo.entidades.Menu;
import com.uisarel.institucion.modelo.entidades.Perfil;
import com.uisarel.institucion.modelo.entidades.PerfilOperaciones;
import com.uisarel.institucion.servicio.IMenuServicio;
import com.uisarel.institucion.servicio.IPerfilMenuServicio;
import com.uisarel.institucion.servicio.IPerfilOperacionesServicio;

@RestController
@RequestMapping("/webservice/v1")
public class AjaxSettingController {

	@Autowired
	private IMenuServicio srvMenu;

	@Autowired
	private IPerfilMenuServicio srvPerfilMenu;
	
	@Autowired
	private IPerfilOperacionesServicio srvPerfilOperaciones;

	@GetMapping("/redirectSubMenu")
	public ResponseEntity<?> getBuscarDataSubMenu(@RequestParam("idperfil") int idperfil,
			@RequestParam("idmenu") int idmenu) {

		HashMap<String, Object> map = new HashMap<>();
		map.put("data", srvMenu.onListarSubMenu(idmenu));
		map.put("asigned", srvPerfilMenu.onSearchPerfilMenuAsigned(idperfil, idmenu));

		return ResponseEntity.ok(map);
	}

	@GetMapping("/saveperfilmenu")
	public ResponseEntity<?> getBuscarDataSubMenu(@RequestParam("idperfil") int idperfil,
			@RequestParam("idmenu") int idmenu, @RequestParam("estado") String status) {

		srvPerfilMenu.onSavePerfilMenu(idperfil, idmenu, status);

		HashMap<String, Object> map = new HashMap<>();
		map.put("data", "estado actualizado!");

		return ResponseEntity.ok(map);
	}

	@GetMapping("/searchOperaciones")
	public ResponseEntity<?> getBuscarOperaciones(@RequestParam("idperfil") int idperfil,@RequestParam("idmenu") int idmenu) {
// srvOperaciones.listaOperaciones()
		HashMap<String, Object> map = new HashMap<>();
		map.put("data", srvMenu.onListarSubMenu(idmenu));
		map.put("asigned", srvPerfilOperaciones.onSearchPerfilOperationAsigned(idperfil,idmenu));

		return ResponseEntity.ok(map);
	}

	@GetMapping("/savePerfilOperation")
	public ResponseEntity<?> postPerfilOperacionGuardar(@RequestParam("idoperacion") int idmenu,
			@RequestParam("idperfil") int idperfil, @RequestParam("onselect") int onselect,
			@RequestParam("oninsert") int oninsert, @RequestParam("onupdate") int onupdate,
			@RequestParam("ondelete") int ondelete) {

//		ANTES DE REGISTRAR ELIMINAMOS PARA EVITAR DUPLICADOS
		srvPerfilOperaciones.onSearchPerfilOperacionAllDelete(idmenu, idperfil);
		
//		UNA VEZ ELIMINADO RECIEN GUARDAMOS
		PerfilOperaciones data = new PerfilOperaciones();
		data.setCrear(oninsert);
		data.setActualizar(onupdate);
		data.setLeer(onselect);
		data.setEliminar(ondelete);
		Menu mnu = new Menu();
		mnu.setIdMenu(idmenu);
		data.setFkMenu(mnu);
		Perfil perf = new Perfil();
		perf.setIdPerfil(idperfil);
		data.setFkPerfil(perf);
		data.setFechaCreacionPerOpe(new Date());
		data.setFechaModificacionPerOpe(new Date());
		
		
		srvPerfilOperaciones.insertarPefilOperaciones(data);

		HashMap<String, Object> map = new HashMap<>();
		map.put("data", "estado actualizado!");

		return ResponseEntity.ok(map);
	}
}
