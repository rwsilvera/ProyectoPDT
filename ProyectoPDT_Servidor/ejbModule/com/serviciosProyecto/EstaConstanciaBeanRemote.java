package com.serviciosProyecto;

import java.util.List;
import javax.ejb.Remote;
import com.entitiesProyecto.EstaConstancia;
import com.exceptionProyecto.ServiciosException;

@Remote
public interface EstaConstanciaBeanRemote {

	void crearEstaConstancia(EstaConstancia estaConstancia) throws ServiciosException;

	void actualizarEstaConstancia(EstaConstancia estaConstancia) throws ServiciosException;

	void borrarEstaConstancia(int id) throws ServiciosException;

	List<EstaConstancia> obtenerTodosEstaConstancia();

	List<EstaConstancia> listarEstaConstanciaPorFiltro(String filtro);

	public EstaConstancia obtenerEstaConstancia(int id);
}
