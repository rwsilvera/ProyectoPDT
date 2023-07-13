package com.serviciosProyecto;

import java.util.List;

import javax.ejb.Remote;

import com.entitiesProyecto.Estado;
import com.exceptionProyecto.ServiciosException;

@Remote
public interface EstadoBeanRemote {
	
	void crearEstado(Estado estado) throws ServiciosException;

	void actualizarEstado(Estado estado) throws ServiciosException;

	void borrarEstado(int id) throws ServiciosException;

	List<Estado> obtenerTodosEstado();

	List<Estado> listarEstadoPorFiltro(String filtro);

	public Estado obtenerEstado(int id);
	
	Estado obtenerEstadoPorNombre(String string);
}
