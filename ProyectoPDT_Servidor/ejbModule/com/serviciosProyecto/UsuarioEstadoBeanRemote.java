package com.serviciosProyecto;

import java.util.List;
import javax.ejb.Remote;
import com.entitiesProyecto.UsuarioEstado;
import com.exceptionProyecto.ServiciosException;

@Remote
public interface UsuarioEstadoBeanRemote {

	UsuarioEstado crearUEstado(UsuarioEstado usuaEstado) throws ServiciosException;

	void actualizarUEstado(UsuarioEstado usuaEstado) throws ServiciosException;

	void borrarUEstado(int id) throws ServiciosException;

	List<UsuarioEstado> obtenerUEstadoTodos();

	List<UsuarioEstado> listarUEstadoFiltro(String filtro);

	public UsuarioEstado obtenerUEstado(int id);

	UsuarioEstado obtenerEstadoPorNombre(String string);
}
