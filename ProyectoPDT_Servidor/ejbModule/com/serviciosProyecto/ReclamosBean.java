package com.serviciosProyecto;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.Query;

import com.entitiesProyecto.Reclamo;


@Stateless
@LocalBean
public class ReclamosBean implements ReclamosBeanRemote {

	@PersistenceContext
	private EntityManager entityManager;

	//----------------------------Crear reclamo-----------------------------------
	@Override
	public Reclamo crearReclamo(Reclamo reclamo) {
		entityManager.persist(reclamo);
		return reclamo;
	}

	//----------------------------obtener reclamo---------------------------------
	@Override
	public Reclamo obtenerReclamo(long idReclamo) {
		return entityManager.find(Reclamo.class, idReclamo);
		
	}

	//----------------------------actualizar--------------------------------------
	@Override
	public Reclamo actualizarReclamo(Reclamo reclamo) {
		return entityManager.merge(reclamo);
	}

	//-----------------------------borrar------------------------------------------
	@Override
	public void borrarReclamo(long idReclamo) {
		Reclamo reclamo = entityManager.find(Reclamo.class, idReclamo);
		if (reclamo != null) {
			entityManager.remove(reclamo);
		}
	}

	//-----------------------------listar todos-----------------------------------
	@Override
	public List<Reclamo> obtenerTodosReclamos() {
		TypedQuery<Reclamo> query = entityManager.createNamedQuery("Reclamo.findAll", Reclamo.class);
		return query.getResultList();
	}

	 @Override
	    public List<Reclamo> obtenerReclamosPorIdEstudiante(Long idEstudiante) {
	        TypedQuery<Reclamo> query = entityManager.createQuery("SELECT r FROM Reclamo r WHERE r.estudiante.idEstudiante = :idEstudiante", Reclamo.class);
	        query.setParameter("idEstudiante", idEstudiante);
	        List<Reclamo> reclamos = query.getResultList();
	        return reclamos;
	    }

		@Override
		public List<Reclamo> obtenerReclamosPorIdEstudiante() {
			Query query = entityManager.createNamedQuery("Reclamo.obtenerReclamosPorIdEstudiante", Reclamo.class);
		
			return query.getResultList();
			
		}
	public ReclamosBean() {
	
	}

}
