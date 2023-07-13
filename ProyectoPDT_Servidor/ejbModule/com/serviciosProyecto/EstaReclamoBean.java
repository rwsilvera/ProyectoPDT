package com.serviciosProyecto;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.entitiesProyecto.EstaReclamo;
import com.exceptionProyecto.ServiciosException;


@Stateless
public class EstaReclamoBean implements EstaReclamoBeanRemote {


	@PersistenceContext
	private EntityManager entityManager;

	public EstaReclamoBean() {
		// TODO Auto-generated constructor stub
	}

	//--------------------------------------Crear Reclamo ------------------------------
	@Override
	public void crearEstaRec(EstaReclamo estaRec) throws ServiciosException {
		try {
			entityManager.persist(estaRec);
		} catch (Exception e) {
			throw new ServiciosException("Error al crear el estado");
		}
	}

	//-------------------------------------Actualizar----------------------------------
	@Override
	public void actualizarEstaRec(EstaReclamo estaRec) throws ServiciosException {
		try {
			entityManager.merge(estaRec);
		} catch (Exception e) {
			throw new ServiciosException("Error al crear el estado");
		}
	}

	//----------------------------------Borrar un estado---------------------------------
	@Override
	public void borrarEstaRec(long id) throws ServiciosException {
		EstaReclamo estaRec = entityManager.find(EstaReclamo.class, id);
		if (estaRec != null) {
			entityManager.remove(estaRec);
		}
	}

	//----------------------------------Listar todos EstaReclamo---------------------------
	@Override
	public List<EstaReclamo> obtenerEstaRecTodos() {
		TypedQuery<EstaReclamo> query = entityManager.createQuery("SELECT e FROM EstaReclamo e", EstaReclamo.class);
		return query.getResultList();
	}

	@Override
	public EstaReclamo obtenerEstaRec(long id) {
		return entityManager.find(EstaReclamo.class, id);
	}
}