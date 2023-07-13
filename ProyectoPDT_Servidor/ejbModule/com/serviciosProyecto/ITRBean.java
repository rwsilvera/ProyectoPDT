package com.serviciosProyecto;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.entitiesProyecto.Itr;
import com.exceptionProyecto.ServiciosException;


@Stateless
public class ITRBean implements ITRBeanRemote {

	@PersistenceContext
	private EntityManager entityManager;


	public ITRBean() {
	}

	//--------------------------------- Crear ITR----------------------------
	@Override
	public Itr crearItr(Itr itr) throws ServiciosException {
		try {
			entityManager.persist(itr);
			entityManager.flush();
			return itr;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	//-------------------------------- Actualizar ITR----------------------------
	@Override
	public void actualizarItr(Itr itr) throws ServiciosException {
		try {
			entityManager.merge(itr);
		} catch (Exception e) {
			throw new ServiciosException("Error al crear el itr");
		}
	}

	//--------------------------------Borrar un Itr -----------------------------
	@Override
	public void borrarItr(int id) throws ServiciosException {
		Long idNuevo = Long.valueOf(id);
		Itr itr = entityManager.find(Itr.class, idNuevo);
		System.out.println(itr.getNombre());
		if (itr != null) {
			entityManager.remove(itr);
			entityManager.flush();
		}
	}

	//---------------------------------Listar todos -Itr-----------------------------------
	@Override
	public List<Itr> obtenerItrTodos() {
		TypedQuery<Itr> query = entityManager.createQuery("SELECT i FROM Itr i", Itr.class);
		return query.getResultList();
	}

	//----------------------------------Listar por flitro --Nombre-------------------------
	@Override
	public List<Itr> listarItrFiltro(String nombre) {
		TypedQuery<Itr> query = entityManager.createNamedQuery("Itr.obtenerPorNombre", Itr.class);
		query.setParameter("nombre", "%" + nombre + "%");
		return query.getResultList();
	}

	@Override
	public Itr obtenerItr(int id) {
		return entityManager.find(Itr.class, id);
	}

	@Override
	public Itr obtenerItrPorNombre(String nombre) {
		TypedQuery<Itr> query = entityManager.createNamedQuery("Itr.obtenerPorNombre", Itr.class);
		query.setParameter("nombre", nombre);
		List<Itr> itr = query.getResultList();
		if (!itr.isEmpty()) {
			return itr.get(0);
		}
		return null;
	}

}
