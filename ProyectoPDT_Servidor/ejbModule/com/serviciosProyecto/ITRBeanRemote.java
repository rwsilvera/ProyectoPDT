package com.serviciosProyecto;

import java.util.List;
import javax.ejb.Remote;
import com.entitiesProyecto.Itr;
import com.exceptionProyecto.ServiciosException;

@Remote
public interface ITRBeanRemote {
	Itr crearItr(Itr itr) throws ServiciosException;

	void actualizarItr(Itr itr) throws ServiciosException;

	void borrarItr(int id) throws ServiciosException;

	List<Itr> obtenerItrTodos();

	List<Itr> listarItrFiltro(String nombre);

	public Itr obtenerItr(int id);

	Itr obtenerItrPorNombre(String string);
}
