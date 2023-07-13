package com.serviciosProyecto;

import java.util.List;
import javax.ejb.Remote;
import com.entitiesProyecto.Localidad;

@Remote
public interface LocalidadesBeanRemote {

	void crearLocalidad(Localidad localidad);

	Localidad obtenerLocalidadPorId(int localidadId);

	List<Localidad> obtenerTodasLocalidades();

	Localidad actualizarLocalidad(Localidad localidad);

	List<Localidad> listarLocalidadFiltro(String nombre);

	void borrarLocalidad(int localidadId);

	Localidad obtenerLocalidadPorNombre(String nombre);
}
