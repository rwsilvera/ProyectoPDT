package com.serviciosProyecto;

import java.util.List;
import javax.ejb.Remote;
import com.entitiesProyecto.Constancia;
import com.exceptionProyecto.ServiciosException;

@Remote
public interface ConstanciaBeanRemote {

	void crearConstancia(Constancia constancia) throws ServiciosException;

	void actualizarConstancia(Constancia constancia) throws ServiciosException;

	void borrarConstancia(int id) throws ServiciosException;

	List<Constancia> obtenerTodosConstancia();

	List<Constancia> listarConstanciaPorFiltro(String filtro);

	public Constancia obtenerConstancia(int id);
}
