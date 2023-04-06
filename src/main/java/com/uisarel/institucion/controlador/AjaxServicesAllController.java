package com.uisarel.institucion.controlador;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uisarel.institucion.servicio.ICursosService;
import com.uisarel.institucion.servicio.IEstudianteService;
import com.uisarel.institucion.servicio.IGradoService;
import com.uisarel.institucion.servicio.IPersonalServicio;
import com.uisarel.institucion.servicio.ISeccionService;
import com.uisarel.institucion.servicio.impl.NativeQueryJDBC;

@RestController
@RequestMapping("/api/v1/serices")
public class AjaxServicesAllController {
	
	@Autowired
	private IGradoService srvGrado;
	
	@Autowired
	private ICursosService srvCurso;
	
	@Autowired
	private IEstudianteService srvEstudiante;
	
	@Autowired
	private ISeccionService srvSeccion;
	
	@Autowired
	private IPersonalServicio srvPersona;
	
	@Autowired
	private NativeQueryJDBC srvNative;

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
	
	@GetMapping("/countStudentTeacherSeccionCurse")
	public ResponseEntity<?> pointCountStudentTeacherSeccionCurse(){
		HashMap<String, Object> response = new HashMap<>();
		response.put("estudiante", srvEstudiante.onListarEstuandePeriodoEscolar(2023));
		response.put("cursos", srvCurso.onListarCursos("VIGENTE"));
		response.put("aulas", srvSeccion.onListarSeccionAll());
		response.put("docentes", srvPersona.onListarPersonalAll());
		response.put("grados", srvGrado.onListarGradosAll());
		response.put("groupprimaria", srvNative.onListarGrupoGradoNivelPeriodoEscolar("PRIMARIA"));
		response.put("groupsecundaria", srvNative.onListarGrupoGradoNivelPeriodoEscolar("SECUNDARIA"));
		return ResponseEntity.ok(response);
	}
}
