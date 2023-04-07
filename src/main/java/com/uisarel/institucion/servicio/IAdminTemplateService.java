package com.uisarel.institucion.servicio;

import org.springframework.web.multipart.MultipartFile;

import com.uisarel.institucion.modelo.entidades.AdminTemplate;

public interface IAdminTemplateService {

	/**
	 * @author CodeBluePeru
	 * @param data
	 * @param logoApp
	 * @param iconoApp
	 * @param bannerApp
	 * @return
	 */
	String onGuardarDataTemplate(AdminTemplate data,MultipartFile logoApp,MultipartFile iconoApp, MultipartFile bannerApp);

	/**
	 * @author CodeBluePeru
	 * @return
	 */
	AdminTemplate onMostrarDataTemplateAdmin();
}
