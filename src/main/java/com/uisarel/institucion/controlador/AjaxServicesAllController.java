package com.uisarel.institucion.controlador;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uisarel.institucion.servicio.ICursosService;
import com.uisarel.institucion.servicio.IGradoService;

@RestController
@RequestMapping("/api/v1/serices")
public class AjaxServicesAllController {
	
	@Autowired
	private IGradoService srvGrado;
	
	@Autowired
	private ICursosService srvCurso;

	@GetMapping("/listagraodonivel")
	public ResponseEntity<?> pointListarGradoNivel(@RequestParam("nivel")String nivel){
		HashMap<String, Object> response = new HashMap<>();
		response.put("data", srvGrado.onListarGradoNivel(nivel));
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/listarcursosAll")
	public ResponseEntity<?> pointListarCursosAll(){
		HashMap<String, Object> response = new HashMap<>();
		response.put("data", srvCurso.onListarCursos("VIGENTE"));
		return ResponseEntity.ok(response);
	}
}
