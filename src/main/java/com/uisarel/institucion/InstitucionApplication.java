package com.uisarel.institucion;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.uisarel.institucion.utils.ConstantApp;



@SpringBootApplication
public class InstitucionApplication {

	public static void main(String[] args) {		
	
		SpringApplication.run(InstitucionApplication.class, args);
		
		String directory = ConstantApp.FILE_DIRECTORY;
		 File file = new File(directory);
		 if(!file.exists()) {
			 file.mkdir();
		 }
		
	}

}
