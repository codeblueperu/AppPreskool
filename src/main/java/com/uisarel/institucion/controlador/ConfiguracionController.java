package com.uisarel.institucion.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.uisarel.institucion.modelo.entidades.AdminTemplate;
import com.uisarel.institucion.servicio.IAdminTemplateService;
import com.uisarel.institucion.servicio.impl.ConfiguracionesServiceImp;

@Controller
public class ConfiguracionController {

	@Autowired
	private ConfiguracionesServiceImp srvSeting;

	@Autowired
	private IAdminTemplateService srvAdminTemplate;

	@GetMapping("/setting")
	public String getAdminTemplate(Model model) {
		model.addAttribute("data", srvAdminTemplate.onMostrarDataTemplateAdmin());
		return "/configuracion/setting";
	}

	@PostMapping("/guardarsetting")
	public String getGuardarDatosTemplate(AdminTemplate data, @RequestParam("logoApp") MultipartFile logoApp,
			 @RequestParam("iconoApp")MultipartFile iconoApp,  @RequestParam("bannerApp")MultipartFile bannerApp) {

		srvAdminTemplate.onGuardarDataTemplate(data, logoApp, iconoApp, bannerApp);
		return "redirect:/setting";
	}

	@ModelAttribute
	public void setGenericos(Authentication auth, Model model) {
		model.addAttribute("setting", srvAdminTemplate.onMostrarDataTemplateAdmin());
		model.addAttribute("menuLista", srvSeting.onListaMenuPerfil(auth));
	}
}
