package com.serviciosProyecto;

import java.util.List;
import javax.ejb.Remote;
import com.entitiesProyecto.Analista;
import com.exceptionProyecto.ServiciosException;

@Remote
public interface AnalistaBeanRemote {

	Analista crearAnalista(Analista analista) throws ServiciosException;

	void actualizarAnalista(Analista analista) throws ServiciosException;

	void borrarAnalista(int id) throws ServiciosException;

	List<Analista> obtenerTodosAnalista();

	List<Analista> listarAnalistaPorFiltro(String filtro);

	public Analista obtenerAnalista(int id);
}
