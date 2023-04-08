package com.uisarel.institucion.servicio.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.uisarel.institucion.servicio.INotificacionesService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class NotificacionesServiceImpl implements INotificacionesService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void sendMensajeChasqui(String asunto_mensaje, String mensaje_contexto, String destinatario_chasqui,
			boolean isAdjuntaFile, String nameFileAdjunta) {
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
//	            REMITENTE
			helper.setFrom("jhony13.more@gmail.com");
//	            DESTINATARIO
			helper.setTo(destinatario_chasqui);
//			ASUNTO EMAIL
			helper.setSubject(asunto_mensaje);
//			CUERPO MENSAJE
			helper.setText(mensaje_contexto, true);
			
//			VALIDAR SI SE ENVIARA UN FILE
			if (isAdjuntaFile) {
//				RUTEAR EL FILE
				FileSystemResource file = new FileSystemResource(new File(nameFileAdjunta));
				helper.addAttachment("ArchivoAdjunto.pdf", file);
			}
			
//			ENVIAR MENSAJE EMAIL
			javaMailSender.send(mimeMessage);
			
		} catch (MessagingException e) {
			throw new IllegalStateException("Error al enviar mensaje servidor de correos.");
		}
	}
}
