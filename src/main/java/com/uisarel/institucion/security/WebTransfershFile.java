package com.uisarel.institucion.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebTransfershFile implements WebMvcConfigurer{

	public void addResourceHandlers(ResourceHandlerRegistry register) {
		register.addResourceHandler("/documents/**").addResourceLocations("file:c:/filePreskool/");
	}
}
