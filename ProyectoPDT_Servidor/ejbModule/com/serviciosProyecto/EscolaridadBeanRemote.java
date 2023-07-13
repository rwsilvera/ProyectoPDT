package com.serviciosProyecto;

import java.util.List;

import javax.ejb.Remote;

import com.entitiesProyecto.Escolaridad;

@Remote
public interface EscolaridadBeanRemote {
	List<Escolaridad> obtenerEscolaridadPorIdEstudiante(Long idEstudiante);

}
