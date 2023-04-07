package com.uisarel.institucion.servicio.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.uisarel.institucion.modelo.entidades.AdminTemplate;
import com.uisarel.institucion.modelo.repositorio.IAdminTemplateRepository;
import com.uisarel.institucion.servicio.IAdminTemplateService;
import com.uisarel.institucion.utils.ConstantApp;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AdminTemplateServiceImpl implements IAdminTemplateService {

	@Autowired
	private IAdminTemplateRepository repoAdmin;

	@Override
	public String onGuardarDataTemplate(AdminTemplate data, MultipartFile logoApp, MultipartFile iconoApp,
			MultipartFile bannerApp) {
		try {
			AdminTemplate update = repoAdmin.findById(data.getIdProyecto()).get();
			System.err.println(update);
			String directory = ConstantApp.FILE_DIRECTORY + "//img";
//			VALIDAMOS QUE EXITA LA CARPETA
			File file = new File(directory);
			if (!file.exists()) {
				file.mkdir();
			}
//			PROCESO DE IMAGES
			if (!logoApp.isEmpty()) {
				data.setLogoSistema(ConstantApp.guardarArchivo(logoApp, directory));
			} else {
				data.setLogoSistema(update.getLogoSistema());
			}
//			ICONO APP
			if (!iconoApp.isEmpty()) {
				data.setIconoSistema(ConstantApp.guardarArchivo(iconoApp, directory));
			} else {
				data.setIconoSistema(update.getIconoSistema());
			}
			
//			LOGIN BANNER APP
			if (!bannerApp.isEmpty()) {
				data.setBannerLogin(ConstantApp.guardarArchivo(bannerApp, directory));
			} else {
				data.setBannerLogin(update.getBannerLogin());
			}

			repoAdmin.save(data);
		} catch (Exception e) {
			System.err.println(e);
		}
		return "datos guardados correctamente";
	}

	@Override
	public AdminTemplate onMostrarDataTemplateAdmin() {
		return repoAdmin.findById(1).get();
	}

}
