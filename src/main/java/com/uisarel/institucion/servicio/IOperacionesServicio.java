package com.uisarel.institucion.servicio;

import java.util.List;

import com.uisarel.institucion.modelo.entidades.Operaciones;

public interface IOperacionesServicio {
	
	public void insertarOperaciones(Operaciones nuevoOperaciones);
	
	public List<Operaciones> listaOperaciones();
	
	public void actualizarOperaciones(Operaciones operacionesEditado);
	
	public Operaciones buscarOperacionesId(int idOperaciones);
	
	public void eliminarOperaciones(int idOperaciones);

}
