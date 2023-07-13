package com.serviciosProyecto;

import java.util.List;
import javax.ejb.Remote;
import com.entitiesProyecto.Justificacion;

@Remote
public interface JustificacionesBeanRemote {

	void crearJustificacion(Justificacion justificacion);

	Justificacion obtenerJustificacionPorId(int justificacionId);

	List<Justificacion> obtenerTodasLasJustificaciones();

	Justificacion actualizarJustificacion(Justificacion justificacion);

	void borrarJustificacion(int justificacionId);

}
