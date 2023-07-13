package com.serviciosProyecto;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.entitiesProyecto.Departamento;
import com.exceptionProyecto.ServiciosException;


@Stateless
@LocalBean
public class DepartamentoBean implements DepartamentoBeanRemote {

    public DepartamentoBean() {
      
    }
    @PersistenceContext
	private EntityManager entityManager;

	//--------------------------------Crear Departamento--------------------------------
	public void crearDepartamento(Departamento departamento) throws ServiciosException {
		try {
			entityManager.persist(departamento);
		} catch (Exception e) {
			throw new ServiciosException("Error al crear 'Departamento'");
		}
	}
	//-------------------------------Actualizar Departamento ---------------------------
	public void actualizarDepartamento(Departamento departamento) throws ServiciosException {
		try {
			entityManager.merge(departamento);
		} catch (Exception e) {
			throw new ServiciosException("Error al actualizar Departamento");
		}
	}

	public Departamento obtenerDepartamento(int id) {
		return entityManager.find(Departamento.class, id);
	}

	//--------------------------------Borrar Departamento--------------------------------
	@Override
	public void borrarDepartamento(int id) {
		Departamento departamento= entityManager.find(Departamento.class, id);
		if(departamento != null) {
			entityManager.remove(departamento);
		}
	}
	//--------------------------------Listar todos los Departamentos------------
	@Override
	public List<Departamento> obtenerTodosDepartamento() {		
		TypedQuery<Departamento> query = entityManager.createQuery("SELECT d FROM Departamento d",Departamento.class); 
		return query.getResultList();
	}
	//--------------------------------Listar Departamentos por nombre ------------------------
	public List<Departamento> listarDepartamentoPorFiltro(String nombre) {
	    TypedQuery<Departamento> query = entityManager.createNamedQuery("Departamento.obtenerPorNombre", Departamento.class);
	    query.setParameter("nombre", "%" + nombre + "%");
	    return query.getResultList();
	}
	public Departamento obtenerDepartamentoPorNombre(String nombre) {
	    TypedQuery<Departamento> query = entityManager.createNamedQuery("Departamento.obtenerPorNombre", Departamento.class);
	    query.setParameter("nombre", nombre);
	    List<Departamento> departamentos = query.getResultList();
	    if (!departamentos.isEmpty()) {
	        return departamentos.get(0);
	    }
	    return null;
	}
}
