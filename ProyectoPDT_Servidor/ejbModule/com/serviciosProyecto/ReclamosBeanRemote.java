package com.serviciosProyecto;

import java.util.List;
import javax.ejb.Remote;
import com.entitiesProyecto.Reclamo;

@Remote
public interface ReclamosBeanRemote {

	Reclamo crearReclamo(Reclamo reclamo);

	Reclamo obtenerReclamo(long idReclamo);

	Reclamo actualizarReclamo(Reclamo reclamo);

	void borrarReclamo(long idReclamo);

	List<Reclamo> obtenerTodosReclamos();

	List<Reclamo> obtenerReclamosPorIdEstudiante(Long idEstudiante);

	List<Reclamo> obtenerReclamosPorIdEstudiante();
}
