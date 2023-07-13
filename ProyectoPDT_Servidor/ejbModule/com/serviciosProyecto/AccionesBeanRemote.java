package com.serviciosProyecto;

import java.util.ArrayList;

import javax.ejb.Remote;

import com.entitiesProyecto.Accion;
import com.exceptionProyecto.ServiciosException;

@Remote
public interface AccionesBeanRemote {

	Accion crearAccion(Accion accion) throws ServiciosException;

	void actualizarAccion(Accion accion) throws ServiciosException;

	void borrarAccion(Long idAccion) throws ServiciosException;

	ArrayList<Accion> listarAcciones() throws ServiciosException;

}
