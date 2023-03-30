package com.uisarel.institucion.controlador;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uisarel.institucion.modelo.entidades.Estudiante;
import com.uisarel.institucion.servicio.IEstudianteService;

@RestController
@RequestMapping("/api/v1/mantenimiento")
public class AjaxMantenimientoController {

	@Autowired
	private IEstudianteService srvEstudiante;

	@RequestMapping(value = "/guardarstudentdata", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<?> pointGuardarDataEstudiante(@RequestBody Estudiante datastudent) {
		System.err.println(datastudent);
		HashMap<String, Object> response = new HashMap<>();
		response.put("data", srvEstudiante.onGuardarEstudianteNuevo(datastudent));
		return ResponseEntity.ok(response);
	}

	@GetMapping("/buscarEstudiantePeriodoNivelGradoSeccion")
	public ResponseEntity<?> pointBuscarEstudianteGradoNivelSeccion(@RequestParam("periodo") int periodo,
			@RequestParam("nivel") String nivel, @RequestParam("grado") int grado,
			@RequestParam("seccion") int seccion) {

		HashMap<String, Object> response = new HashMap<>();
		response.put("data", srvEstudiante.onListarEstuandePeriodoEscolarGradoSeccionNivel(periodo, nivel, grado, seccion));
		return ResponseEntity.ok(response);
	}

}
