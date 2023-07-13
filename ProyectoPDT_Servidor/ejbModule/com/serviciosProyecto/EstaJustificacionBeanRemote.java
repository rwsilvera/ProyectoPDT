package com.serviciosProyecto;

import java.util.List;
import javax.ejb.Remote;
import com.entitiesProyecto.EstaJustificacion;
import com.exceptionProyecto.ServiciosException;

@Remote
public interface EstaJustificacionBeanRemote {
	void crearEstaJust(EstaJustificacion estaJust) throws ServiciosException;

	void actualizarEstaJust(EstaJustificacion estaJust) throws ServiciosException;

	void borrarEstaJust(int id) throws ServiciosException;

	List<EstaJustificacion> obtenerEstaJustTodos();

	List<EstaJustificacion> listarEstaJustFiltro(String filtro);

	public EstaJustificacion obtenerEstaJust(int id);

}
