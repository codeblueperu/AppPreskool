package com.uisarel.institucion.servicio;

public interface INotificacionesService {

	/**
	 * 
	 * @param asunto_mensaje
	 * @param mensaje_contexto
	 * @param destinatario_chasqui
	 * @param isAdjuntaFile
	 * @param nameFileAdjunta
	 */
	void sendMensajeChasqui(String asunto_mensaje, String mensaje_contexto,
			String destinatario_chasqui, boolean isAdjuntaFile, String nameFileAdjunta);
}
