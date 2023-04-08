package com.uisarel.institucion.controlador;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.multipart.MultipartFile;

import com.uisarel.institucion.dto.DtoMenuLogin;
import com.uisarel.institucion.modelo.entidades.AsignarTarea;
import com.uisarel.institucion.servicio.IAdminTemplateService;
import com.uisarel.institucion.servicio.IAsignarTareaServicio;
import com.uisarel.institucion.servicio.IMenuServicio;
import com.uisarel.institucion.servicio.IPeriodoEscolarService;
import com.uisarel.institucion.servicio.IPersonalServicio;
import com.uisarel.institucion.servicio.ISeccionService;
import com.uisarel.institucion.utils.ConstantApp;

@Controller
public class AsignarTareaController {

	@Autowired
	private IAsignarTareaServicio srvAsignarTarea;

	@Autowired
	private IPersonalServicio srvDocente;

	@Autowired
	private IPeriodoEscolarService srvPeriodoEscolar;

	@Autowired
	private ISeccionService srvSeccion;
	
	@Autowired
	private IAdminTemplateService srvAdminTemplate;
	
	@Autowired
	private IMenuServicio servicioMenu;

	List<DtoMenuLogin> lstMenuAcceso = new ArrayList<>();
	
	
	@GetMapping("/taskstudent")
	public String getViewTareasEstudiantesAll(Model model) {
		if(!servicioMenu.onValidarRutaPermiso("/taskstudent")) {
			return "error/errorPage";
		}
		model.addAttribute("lstdocente", srvDocente.onListarPersonalAll());
		model.addAttribute("lstperiodo", srvPeriodoEscolar.onListarPeriodoEscolarEstado("APERTURADO"));
		model.addAttribute("lstseccion", srvSeccion.onListarSeccionAll());
		return "obligaciones/taskstudent";
	}

	@GetMapping("/listTask")
	public String getListarTareasAsignadasPeriodoEscolarAperturado(Model model) {
		if(!servicioMenu.onValidarRutaPermiso("/listTask")) {
			return "error/errorPage";
		}
		model.addAttribute("dttareas", srvAsignarTarea.onListarTareaPeriodoEscolarAperturado(0));
		return "obligaciones/tareas";
	}

	@GetMapping("/formaddtask")
	public String getTemplateCrearNuevaTareasAsignadasPeriodoEscolarAperturado(Model model) {
		if(!servicioMenu.onValidarRutaPermiso("/listTask")) {
			return "error/errorPage";
		}
		AsignarTarea tarea = new AsignarTarea();
		model.addAttribute("data", tarea);
		model.addAttribute("dttarea", tarea);
		model.addAttribute("lstdocente", srvDocente.onListarPersonalAll());
		return "obligaciones/nuevaTarea";
	}

	@GetMapping("/viewtaskedit")
	public String getTemplateEditTareasAsignadasPeriodoEscolarAperturado(@RequestParam("id") int idtarea, Model model) {
		if(!servicioMenu.onValidarRutaPermiso("/listTask")) {
			return "error/errorPage";
		}
		AsignarTarea tarea = new AsignarTarea();
		model.addAttribute("data", tarea);
		model.addAttribute("dttarea", srvAsignarTarea.onBuscarTareaIdPeriodoAprturado(idtarea));
		model.addAttribute("lstdocente", srvDocente.onListarPersonalAll());
		return "obligaciones/nuevaTarea";
	}

	@PostMapping("/addtask")
	public String getCrearNuevaTareasAsignadasPeriodoEscolarAperturado(AsignarTarea datos,
			@RequestParam("architarea") MultipartFile multiPart, @RequestParam("fechaPresenta") String fechaPresenta)
			throws ParseException, IOException {

		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Date converFecha = formato.parse(fechaPresenta);

		datos.setFechaPresentacion(converFecha);

		if (!multiPart.isEmpty()) {
			String directory = ConstantApp.FILE_DIRECTORY + "//task";
			File file = new File(directory);
			if (!file.exists()) {
				file.mkdir();
			}

			byte[] bytes = multiPart.getBytes();
			Path path = Paths.get(directory + "//" + multiPart.getOriginalFilename());
			Files.write(path, bytes);
			datos.setNameDocumento(multiPart.getOriginalFilename().replace(" ", "_").replace("-", "_"));
		}

		srvAsignarTarea.onGuardarTareaNuevaPeriodoEscolar(datos);
		return "redirect:/listTask";
	}

	@ModelAttribute
	public void setGenericos(Authentication auth, Model model) {
		lstMenuAcceso = servicioMenu.onBuscarMenuLogin();
		model.addAttribute("listaMenu", lstMenuAcceso);
		model.addAttribute("setting",srvAdminTemplate.onMostrarDataTemplateAdmin());
	}
}
