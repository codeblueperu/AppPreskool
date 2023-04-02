package com.uisarel.institucion.controlador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.uisarel.institucion.modelo.entidades.AsignarTarea;
import com.uisarel.institucion.modelo.entidades.PerfilOperaciones;
import com.uisarel.institucion.servicio.IAsignarTareaServicio;
import com.uisarel.institucion.servicio.ICursosService;
import com.uisarel.institucion.servicio.IPerfilOperacionesServicio;
import com.uisarel.institucion.servicio.IPeriodoEscolarService;
import com.uisarel.institucion.servicio.IPersonalServicio;
import com.uisarel.institucion.servicio.ISeccionService;
import com.uisarel.institucion.servicio.impl.ConfiguracionesServiceImp;
import com.uisarel.institucion.utils.ConstantApp;

@Controller
public class AsignarTareaController {

	@Autowired
	private IAsignarTareaServicio srvAsignarTarea;

	@Autowired
	private ConfiguracionesServiceImp srvSeting;

	@Autowired
	private IPerfilOperacionesServicio srvOperacion;

	@Autowired
	private IPersonalServicio srvDocente;

	@Autowired
	private ICursosService srvCursos;

	@Autowired
	private IPeriodoEscolarService srvPeriodoEscolar;

	@Autowired
	private ISeccionService srvSeccion;
	

	@GetMapping("/taskstudent")
	public String getViewTareasEstudiantesAll(Model model) {
		model.addAttribute("lstdocente",srvDocente.onListarPersonalAll());
		model.addAttribute("lstperiodo", srvPeriodoEscolar.onListarPeriodoEscolarEstado("APERTURADO"));
		model.addAttribute("lstseccion",srvSeccion.onListarSeccionAll());
		return "obligaciones/taskstudent";
	}

	@GetMapping("/listTask")
	public String getListarTareasAsignadasPeriodoEscolarAperturado(Model model) {
		model.addAttribute("lstdocente", srvDocente.onListarPersonalAll());
		model.addAttribute("lstcursos", srvCursos.onListarCursos("VIGENTE"));
		model.addAttribute("dttareas", srvAsignarTarea.onListarTareaPeriodoEscolarAperturado(0));
		return "obligaciones/tareas";
	}

	@GetMapping("/formaddtask")
	public String getTemplateCrearNuevaTareasAsignadasPeriodoEscolarAperturado(Model model) {
		AsignarTarea tarea = new AsignarTarea();
		model.addAttribute("data", tarea);
		model.addAttribute("lstdocente", srvDocente.onListarPersonalAll());
		return "obligaciones/nuevaTarea";
	}

	@PostMapping("/addtask")
	public String getCrearNuevaTareasAsignadasPeriodoEscolarAperturado(AsignarTarea datos,
			@RequestParam("architarea") MultipartFile multiPart, @RequestParam("fechaPresenta") String fechaPresenta)
			throws ParseException {

		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Date converFecha = formato.parse(fechaPresenta);
		
		datos.setFechaPresentacion(converFecha);
		
		if (!multiPart.isEmpty()) {
			String ruta = "c:/filePreskool/"; 
			String nombreImagen = ConstantApp.guardarArchivo(multiPart, ruta);
			if (nombreImagen != null) {
				datos.setNameDocumento(nombreImagen);
			}
		}

		srvAsignarTarea.onGuardarTareaNuevaPeriodoEscolar(datos);
		return "redirect:/listTask";
	}

	@ModelAttribute
	public void setGenericos(Authentication auth, Model model) {
		PerfilOperaciones actions = srvOperacion.onBuscarPermidoRolMenu(18, auth);
		model.addAttribute("menuLista", srvSeting.onListaMenuPerfil(auth));
		model.addAttribute("cdrSelect", actions.getLeer());
		model.addAttribute("cdrInsert", actions.getCrear());
		model.addAttribute("cdrUpdate", actions.getActualizar());
		model.addAttribute("cdrDelete", actions.getEliminar());
	}
}
