package com.uisarel.institucion.servicio.impl;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Service;

import com.uisarel.institucion.servicio.INotificacionesService;

@Service
public class NotificacionesServiceImpl implements INotificacionesService {
	


	@Override
	public void sendMensajeChasqui(String asunto_mensaje, String mensaje_contexto, String destinatario_chasqui,
			boolean isAdjuntaFile, String nameFileAdjunta) {
		try {
			// DEFINIMOS LOS DATOS DEL SERVICIO
			Properties prop = new Properties();
			prop.setProperty("mail.host", "chasqui.ejercito.mil.pe");
			prop.setProperty("mail.transport.protocol", "smtp");
			prop.setProperty("mail.smtp.port", "587");
			prop.setProperty("mail.smtp.auth", "true");

			// 1, CREAR SESION CON EL SERVIDOR
			Session session = Session.getInstance(prop);

			// HABILITAMOS EL DEBUG PARA VER COMO FUNCIONA
			session.setDebug(true);

			// 2, OBTENEMOS EL OBJECT
			Transport ts = session.getTransport();

			// 3, CONECTAMOS AL SERVIDOR
			ts.connect("chasqui.ejercito.mil.pe", "detel-copere@ep.mil.pe", "Detelnuevo2022**");

			// 4, CREAMOS EL CORREO
			Message message = crearCuerpodelCorreo(session, asunto_mensaje, mensaje_contexto, destinatario_chasqui, nameFileAdjunta, isAdjuntaFile);

			// 5, ENVIAMOS EL CORREO
			ts.sendMessage(message, message.getAllRecipients());
			ts.close();

		} catch (Exception e) {
			System.out.println("ERROR AL ENVIAR CORREO => " + e);
			throw new InternalError(e);
		}

	}
	
	public static MimeMessage crearCuerpodelCorreo(Session session, String titulo, String charreo, String destinatario,
			String nameFile, boolean fileSend) throws Exception {
		MimeMessage message = new MimeMessage(session);

		// ARMAMOS EL CUERPO DEL CORREO
		// USER SERVIDOR
		message.setFrom(new InternetAddress("detel-copere@ejercito.mil.pe"));
		// AQUIEN ENVIAMOS
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));

		// TITULO DEL CORREO
		message.setSubject(titulo);

		// EMPEZAMOS CON EL CUERPO HABILITAR LOS TYPES DE ACCETPS
		MimeBodyPart text = new MimeBodyPart();
		text.setContent(charreo, "text / html; charset = UTF-8");

		// ALISTAMOS EL ARCHIVO A REMITIR Y VALIDAMOS SI BIENE ALGUN ARCHIVO
		MimeBodyPart attach = new MimeBodyPart();
		if (fileSend) {
			DataHandler dh = new DataHandler(new FileDataSource(nameFile));
			attach.setDataHandler(dh);
			attach.setFileName(dh.getName());
		}

		// CREAMOS EL CONTENEDOR PARA LA RELACION DE DATOS
		MimeMultipart mp = new MimeMultipart();
		mp.addBodyPart(text);
		if (fileSend) {
			mp.addBodyPart(attach);
		}

		mp.setSubType("mixed");

		message.setContent(mp);
		message.saveChanges();

		// GUARDAMOS EL CORREO ENVIADO
		// message.writeTo(new FileOutputStream("E:\\mi_correo_generado_JMI.eml"));

		// DEVOLVEMOS EL CORREO GENERADO
		return message;
	}


}
