package com.serviciosProyecto;

import java.util.List;
import javax.ejb.Remote;
import com.entitiesProyecto.ConvAsistencia;
import com.exceptionProyecto.ServiciosException;

@Remote
public interface ConvAsistenciaBeanRemote {

	void crearConvAsistencia(ConvAsistencia convAsistencia) throws ServiciosException;

	void actualizarConvAsistencia(ConvAsistencia convAsistencia) throws ServiciosException;

	void borrarConvAsistencia(int id) throws ServiciosException;

	List<ConvAsistencia> obtenerTodosConvAsistencia();

	List<ConvAsistencia> listarConvAsistenciaPorFiltro(String filtro);

	public ConvAsistencia obtenerConvAsistencia(int id);
}
