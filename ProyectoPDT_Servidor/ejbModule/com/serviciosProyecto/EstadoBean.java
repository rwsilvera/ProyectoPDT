package com.serviciosProyecto;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.entitiesProyecto.Estado;
import com.entitiesProyecto.Itr;
import com.exceptionProyecto.ServiciosException;

@Stateless
@LocalBean
public class EstadoBean implements EstadoBeanRemote {

    public EstadoBean() {
        // TODO Auto-generated constructor stub
    }
	@PersistenceContext
	private EntityManager entityManager;

	//--------------------------Crear Estado----------------------------
	public void crearEstado(Estado estado) throws ServiciosException {
		try {
			entityManager.persist(estado);
		} catch (Exception e) {
			throw new ServiciosException("Error al crear 'Estado'");
		}

	}

	//------------------------Actualizar Estado-------------------------
	public void actualizarEstado(Estado estado) throws ServiciosException {
		try {
			entityManager.merge(estado);
		} catch (Exception e) {
			throw new ServiciosException("Error al actualizar estado");
		}
	}

	public Estado obtenerEstado(int id) {
		return entityManager.find(Estado.class, id);
	}

	//------------------------Borrar Estado------------------------------
	@Override
	public void borrarEstado(int id) {
		Estado estado= entityManager.find(Estado.class, id);
		if(estado != null) {
			entityManager.remove(estado);
		}
	}

	//----------------------Listar todos Estado--------------------------
	@Override
	public List<Estado> obtenerTodosEstado() {		
		TypedQuery<Estado> query = entityManager.createQuery("SELECT e FROM Estado e",Estado.class); 
		return query.getResultList();
	}

	//------------------------Listar por filtro nombre Estado------------------
	public List<Estado> listarEstadoPorFiltro(String filtro) {
		TypedQuery<Estado> query = entityManager.createNamedQuery("Estado.obtenerPorNombre", Estado.class);
		query.setParameter("filtro", "%" + filtro + "%");
		return query.getResultList();
	}

	@Override
	public Estado obtenerEstadoPorNombre(String nombre) {
		TypedQuery<Estado> query = entityManager.createNamedQuery("Estado.obtenerPorNombre", Estado.class);
		query.setParameter("nombre", nombre);
		List<Estado> estado = query.getResultList();
		if (!estado.isEmpty()) {
			return estado.get(0);
		}
		return null;
	}
}
