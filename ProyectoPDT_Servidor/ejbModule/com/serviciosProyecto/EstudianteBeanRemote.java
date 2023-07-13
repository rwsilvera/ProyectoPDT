package com.serviciosProyecto;

import java.util.List;

import javax.ejb.Remote;

import com.entitiesProyecto.Estudiante;
import com.entitiesProyecto.Generacion;
import com.entitiesProyecto.Usuario;
import com.exceptionProyecto.ServiciosException;

@Remote
public interface EstudianteBeanRemote {
	Estudiante crearEstudiante(Estudiante estudiante) throws ServiciosException;

	void actualizarEstudiante(Estudiante estudiante) throws ServiciosException;

	void borrarEstudiante(long id) throws ServiciosException;

	List<Estudiante> obtenerEstudianteTodos();

	List<Estudiante> listarEstudianteFiltro(String filtro);
	
	public Estudiante obtenerEstudiantePorUsuario(Usuario usuario);
	
	public Estudiante obtenerEstudiantePorIdUsuario(long id);
	
	public Estudiante getEstudiantePorIdUsuario(Long id);
	
	List<Estudiante> obtenerEstudiantePorGeneracion(Generacion generacion);

}
