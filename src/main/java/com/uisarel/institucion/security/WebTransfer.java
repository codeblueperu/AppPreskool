package com.uisarel.institucion.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.uisarel.institucion.utils.ConstantApp;

@Configuration
public class WebTransfer implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/fltask/**").addResourceLocations("file:"+ConstantApp.FILE_DIRECTORY+"//");
	}
}
