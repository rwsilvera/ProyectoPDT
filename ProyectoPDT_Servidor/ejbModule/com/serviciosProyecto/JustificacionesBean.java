package com.serviciosProyecto;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.entitiesProyecto.Justificacion;

@Stateless
@LocalBean
public class JustificacionesBean implements JustificacionesBeanRemote {

	@PersistenceContext
	private EntityManager entityManager;

	//--------------------------crear Justificacion-------------------------------
	@Override
	public void crearJustificacion(Justificacion justificacion) {
		entityManager.persist(justificacion);

	}
	//------------------------obtener por id Justificacion-------------------------------
	@Override
	public Justificacion obtenerJustificacionPorId(int justificacionId) {
		return entityManager.find(Justificacion.class, justificacionId);
	}

	//-------------------------obtener todos Justificacion-------------------------------
	@Override
	public List<Justificacion> obtenerTodasLasJustificaciones() {
		TypedQuery<Justificacion> query = entityManager.createNamedQuery("Justificacion.findAll", Justificacion.class);
		return query.getResultList();
	}

	//-------------------------actualizar Justificacion-------------------------------
	@Override
	public Justificacion actualizarJustificacion(Justificacion justificacion) {
		justificacion = entityManager.merge(justificacion);
		return justificacion;
	}

	//---------------------------borrar Justificacion-----------------------------------
	@Override
	public void borrarJustificacion(int justificacionId) {
		Justificacion justificacion = entityManager.find(Justificacion.class, justificacionId);
		if (justificacion != null) {
			entityManager.remove(justificacion);
		}
	}
	public JustificacionesBean() {

	}

}
