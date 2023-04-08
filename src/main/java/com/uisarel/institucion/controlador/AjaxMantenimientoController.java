package com.uisarel.institucion.controlador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uisarel.institucion.modelo.entidades.Asistencia;
import com.uisarel.institucion.modelo.entidades.Conducta;
import com.uisarel.institucion.modelo.entidades.Estudiante;
import com.uisarel.institucion.modelo.entidades.Personal;
import com.uisarel.institucion.modelo.entidades.TareaAlumno;
import com.uisarel.institucion.servicio.IAsignarTareaServicio;
import com.uisarel.institucion.servicio.IAsistenciaServicio;
import com.uisarel.institucion.servicio.IConductaService;
import com.uisarel.institucion.servicio.IEstudianteService;
import com.uisarel.institucion.servicio.INotificacionesService;
import com.uisarel.institucion.servicio.IPersonalServicio;
import com.uisarel.institucion.servicio.ITareaAlumnoServicio;

@RestController
@RequestMapping("/api/v1/mantenimiento")
public class AjaxMantenimientoController {

	@Autowired
	private IEstudianteService srvEstudiante;

	@Autowired
	private IPersonalServicio srvPersona;

	@Autowired
	private IAsistenciaServicio srvAsistencia;

	@Autowired
	private IAsignarTareaServicio srvAsignarTarea;

	@Autowired
	private ITareaAlumnoServicio srvTareaAlumno;

	@Autowired
	private IConductaService srvConducta;
	
	@Autowired
	private INotificacionesService srvNoti;

//	RESCONTROLLER ESTUDIANTES

	@RequestMapping(value = "/guardarstudentdata", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<?> pointGuardarDataEstudiante(@RequestBody Estudiante datastudent) {
		// System.err.println(datastudent);

		Estudiante estudiante = new Estudiante();
		String message = "";
		if (datastudent.getIdEstudiante() != 0) {
//			UPDATE 
			estudiante = srvEstudiante.onUpdateEstudiantePeriodo(datastudent);
			message = "Los datos del estudiante fuerón actualizados con éxito.";
		} else {
//			INSERT
			estudiante = srvEstudiante.onGuardarEstudianteNuevo(datastudent);
			message = "Excelente el estudiante fue registrado con éxito";
		}
		HashMap<String, Object> response = new HashMap<>();
		response.put("data", estudiante);
		response.put("message", message);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/buscarEstudiantePeriodoNivelGradoSeccion")
	public ResponseEntity<?> pointBuscarEstudianteGradoNivelSeccion(@RequestParam("periodo") int periodo,
			@RequestParam("nivel") String nivel, @RequestParam("grado") int grado,
			@RequestParam("seccion") int seccion) {

		HashMap<String, Object> response = new HashMap<>();
		response.put("data",
				srvEstudiante.onListarEstuandePeriodoEscolarGradoSeccionNivel(2023, nivel, grado, seccion));
		return ResponseEntity.ok(response);
	}

	@GetMapping("/buscarEstudinatePeriodoEscolar")
	public ResponseEntity<?> pointBuscarEstudinatePeriodoEscolar(@RequestParam("periodo") int periodo,
			@RequestParam("idestudiante") int idestudiante) {

		HashMap<String, Object> response = new HashMap<>();
		response.put("data", srvEstudiante.onBuscarEstudiantePeriodoEscolarId(idestudiante, periodo));
		return ResponseEntity.ok(response);
	}

//	PERSONAL RESPCONTROLLER

	@RequestMapping(value = "/guardarpersonal", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<?> pointGuardarDataPersonal(@RequestBody Personal personal) {
		System.err.println(personal);
		Personal person = new Personal();
		String message = "";
		if (personal.getIdPersonal() != 0) {
			person = srvPersona.onUpdateDataPersonal(personal);
			message = "Los datos del docente fueron modificados correctamente.";
		} else {
			person = srvPersona.onGuardarDatosPersonal(personal);
			message = "Los datos del docente fueron registrados correctamente.";
		}
		HashMap<String, Object> response = new HashMap<>();
		response.put("data", person);
		response.put("message", message);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/listapersonalAll")
	public ResponseEntity<?> pointListarPersonal() {

		HashMap<String, Object> response = new HashMap<>();
		response.put("data", srvPersona.onListarPersonalAll());
		return ResponseEntity.ok(response);
	}

	@GetMapping("/buscarPersonalDocenteID")
	public ResponseEntity<?> pointBuscarPersonalDocenteID(@RequestParam("person") int idpersona) {

		HashMap<String, Object> response = new HashMap<>();
		response.put("data", srvPersona.onBuscarPersonalId(idpersona));
		return ResponseEntity.ok(response);
	}

//	RESTCONTROLLER DOCENTE

	@GetMapping("/buscardatosdocente")
	public ResponseEntity<?> endPointBuscarDatosDocente(@RequestParam("iddocente") int iddocente) {

		HashMap<String, Object> response = new HashMap<>();
		response.put("data", srvPersona.onBuscarPersonalGradoSeccionCursoId(iddocente));
		return ResponseEntity.ok(response);
	}

//	RESCONTROLLER ALUMNO

	@GetMapping("/buscarAlumnosGradoNivelSeccionPeriodo")
	public ResponseEntity<?> endPointBuscarAlumnosGradoNivelSeccionPeriodo(@RequestParam("idperiodo") int idperiodo,
			@RequestParam("nivel") String nivel, @RequestParam("idgrado") int idgrado,
			@RequestParam("idsecion") int idseccion, @RequestParam("idcurso") int idcurso,
			@RequestParam("fecha") String fecha) throws ParseException {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Date converFecha = formato.parse(fecha);
		HashMap<String, Object> response = new HashMap<>();
		response.put("asistencia", srvAsistencia.onListarAsistenciaCursoFecha(idcurso, idseccion, converFecha));
		response.put("data",
				srvEstudiante.onListarEstuandePeriodoEscolarGradoSeccionNivel(2023, nivel, idgrado, idseccion));
		return ResponseEntity.ok(response);
	}

//	RESCONTROLLER ASISTENCIA

	@RequestMapping(value = "/guardarasistencia", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<?> pointGuardarAsistencia(@RequestBody Asistencia asistencia) {
		System.err.println(asistencia);
		HashMap<String, Object> response = new HashMap<>();
		response.put("data", srvAsistencia.onGuardarAsistenciaCursoFecha(asistencia));
		return ResponseEntity.ok(response);
	}

//	RESCONTROLLER ASIGNARTAREA

	@GetMapping("/buscarTareaDocente")
	public ResponseEntity<?> endPointBuscarTareaDocente(@RequestParam("idgrado") int idgrado,
			@RequestParam("idsecion") int idseccion, @RequestParam("idcurso") int idcurso,
			@RequestParam("iddocente") int iddocente) {

		HashMap<String, Object> response = new HashMap<>();
		response.put("data", srvAsignarTarea.onListarTareaPeriodoEscolarAperturadoDocenteCursoGradoSeccion(iddocente,
				idcurso, idgrado, idseccion));
		return ResponseEntity.ok(response);
	}

	@GetMapping("/buscarAlumnosGradoNivelSeccionTarea")
	public ResponseEntity<?> endPointBuscarAlumnosGradoNivelSeccionTarea(@RequestParam("nivel") String nivel,
			@RequestParam("idgrado") int idgrado, @RequestParam("idsecion") int idseccion,
			@RequestParam("idcurso") int idcurso, @RequestParam("idtarea") int idtarea) {

		HashMap<String, Object> response = new HashMap<>();
		response.put("presentaron", srvTareaAlumno.onBuscarTareaAlumno(idgrado, idseccion, idcurso, idtarea));
		response.put("data",
				srvEstudiante.onListarEstuandePeriodoEscolarGradoSeccionNivel(2023, nivel, idgrado, idseccion));
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/buscarDatosTareaId")
	public ResponseEntity<?> endPointBuscarDatosTareaId(@RequestParam("idtarea") int idtarea) {

		HashMap<String, Object> response = new HashMap<>();
		
		response.put("data",srvAsignarTarea.onBuscarTareaIdPeriodoAprturado(idtarea));
		return ResponseEntity.ok(response);
	}

	@RequestMapping(value = "/guardarTareaAlumno", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<?> pointGuardarTareaAlumno(@RequestBody TareaAlumno tareaaluno) {
		HashMap<String, Object> response = new HashMap<>();
		response.put("data", srvTareaAlumno.onGuardarTareaALumno(tareaaluno));
		return ResponseEntity.ok(response);
	}

//	RESCONTROLLER CONDUCTA

	@RequestMapping(value = "/guardarConductaAlumno", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<?> pointGuardarConductaAlumno(@RequestBody Conducta dataconducta) {

		Conducta conducta = new Conducta();
		HashMap<String, Object> response = new HashMap<>();
		String message = "";
		if (dataconducta.getIdConducta() != 0) {
			conducta = srvConducta.onUpdateConductaEstudiante(dataconducta);
			message = "Datos Actualizados correctamente";
		} else {
			conducta = srvConducta.onGuardarConducta(dataconducta);
			message = "El informe de conducta fue registrado correctamente";
		}

		response.put("message", message);
		response.put("data", conducta);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/buscarConductaAlumnoGradoSeccionNivel")
	public ResponseEntity<?> endPointBuscarConductaAlumnoGradoSeccionNivel(@RequestParam("idcurso") int idcurso,
			@RequestParam("idalumno") int idalumno, @RequestParam("iddocente") int iddocente) {

		HashMap<String, Object> response = new HashMap<>();
		response.put("data", srvConducta.onBuscarConductaAlumnoID(idalumno,idcurso,iddocente));
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/eliminarConductaAlumnoGradoSeccionNivel")
	public ResponseEntity<?> endPointEliminarConductaAlumnoGradoSeccionNivel(@RequestParam("idconducta") int idconducta) {
		srvConducta.onEliminarConducta(idconducta);
		HashMap<String, Object> response = new HashMap<>();
		response.put("message", "registro eliminado correctamente");
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/senemail")
	public ResponseEntity<?> email() {
		String charreao = "<div style='width:50%;padding:.3rem;border-left:4px solid #3d5ee1;font-family: Courier, monospace;'> <h4>¡Hola! Bienvenid@ a PRESKOOL  </h4>"
				+ "<br>Estimado <b>Jhony More inga</b>, <br> se realizo la creacion de su cuenta a la plataforma escolar"
				+ " para el AF-2023.<br> sus credenciales de acceso son:<br><br>"
				+ "<div><b>Usuario:</b> Jhony13.more@gmail.com<br>"
				+ "<b>Contraseña:</b> 48588250"
				+ "</div>"
				+ "</div>";
		srvNoti.sendMensajeChasqui("CREACION DE CUENTA - PRESKOOL AF-2023", charreao, "jmiserver13@gmail.com", false, null);
		
		HashMap<String, Object> response = new HashMap<>();
		response.put("data", "ok");
		return ResponseEntity.ok(response);
	}

}
