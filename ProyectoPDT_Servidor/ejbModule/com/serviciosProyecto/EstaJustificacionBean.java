package com.serviciosProyecto;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.entitiesProyecto.EstaJustificacion;
import com.exceptionProyecto.ServiciosException;

@Stateless
public class EstaJustificacionBean implements EstaJustificacionBeanRemote {


	@PersistenceContext
	private EntityManager entityManager;

	public EstaJustificacionBean() {

	}

	//---------------------------Crear justificacion --------------------------
	@Override
	public void crearEstaJust(EstaJustificacion estaJust) throws ServiciosException {
		try {
			entityManager.persist(estaJust);
		} catch (Exception e) {
			throw new ServiciosException("Error al crear el estado");
		}
	}

	// ---------------------------Actualizar Justificacion ----------------------------
	@Override
	public void actualizarEstaJust(EstaJustificacion estaJust) throws ServiciosException {
		try {
			entityManager.persist(estaJust);
		} catch (Exception e) {
			throw new ServiciosException("Error al crear el estado");
		}
	}

	//---------------------------Borrar un estado Justificacion ----------------------------
	@Override
	public void borrarEstaJust(int id) throws ServiciosException {
		EstaJustificacion estaJust = entityManager.find(EstaJustificacion.class, id);
		if (estaJust != null) {
			entityManager.remove(estaJust);
		}
	}

	//----------------------------Listar todos Justificacion ----------------------------
	@Override
	public List<EstaJustificacion> obtenerEstaJustTodos() {
		TypedQuery<EstaJustificacion> query = entityManager.createQuery("SELECT e FROM EstadoJustificacion e",
				EstaJustificacion.class);
		return query.getResultList();
	}

	//----------------------------Listar por flitro -- id---------------------------------
	@Override
	public List<EstaJustificacion> listarEstaJustFiltro(String filtro) {
		TypedQuery<EstaJustificacion> query = entityManager.createNamedQuery("EstaJustificacion.obtenerPorId",
				EstaJustificacion.class);
		query.setParameter("filtro", "%" + filtro + "%");
		return query.getResultList();
	}

	@Override
	public EstaJustificacion obtenerEstaJust(int id) {
		return entityManager.find(EstaJustificacion.class, id);
	}

}
