package com.serviciosProyecto;

import java.util.List;
import javax.ejb.Remote;
import com.entitiesProyecto.Departamento;
import com.exceptionProyecto.ServiciosException;

@Remote
public interface DepartamentoBeanRemote {

	void crearDepartamento(Departamento departamento) throws ServiciosException;

	void actualizarDepartamento(Departamento departamento) throws ServiciosException;

	void borrarDepartamento(int id) throws ServiciosException;

	List<Departamento> obtenerTodosDepartamento();

	List<Departamento> listarDepartamentoPorFiltro(String string);

	public Departamento obtenerDepartamento(int id);

	Departamento obtenerDepartamentoPorNombre(String nombre);
}
