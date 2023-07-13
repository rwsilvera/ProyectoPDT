package com.serviciosProyecto;

import java.util.List;
import javax.ejb.Remote;
import com.entitiesProyecto.EstaReclamo;
import com.exceptionProyecto.ServiciosException;

@Remote
public interface EstaReclamoBeanRemote {
	void crearEstaRec(EstaReclamo estaRec) throws ServiciosException;

	void actualizarEstaRec(EstaReclamo estaRec) throws ServiciosException;

	void borrarEstaRec(long id) throws ServiciosException;

	List<EstaReclamo> obtenerEstaRecTodos();

	public EstaReclamo obtenerEstaRec(long id);

}
