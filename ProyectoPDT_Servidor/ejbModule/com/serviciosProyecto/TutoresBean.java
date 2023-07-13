package com.serviciosProyecto;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.entitiesProyecto.Tutor;

@Stateless
@LocalBean
public class TutoresBean implements TutoresBeanRemote {

	@PersistenceContext 
	private EntityManager entityManager;

	//-------------------------------Crear tutor-----------------------------------
	@Override
	public Tutor crearTutor(Tutor tutor) {
		try {
			entityManager.persist(tutor);
			entityManager.flush();
			return tutor;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public Tutor obtenerTutorPorIdUsuario(long idUsuario) {
	    TypedQuery<Tutor> query = entityManager.createQuery(
	        "SELECT e FROM Tutor e WHERE e.usuario.idUsuario = :idUsuario", Tutor.class);
	    query.setParameter("idUsuario", idUsuario);
	    List<Tutor> tutores = query.getResultList();
	    if (!tutores.isEmpty()) {
	        return tutores.get(0);
	    }
	    return null;
	}

	//-----------------------------obtener tutor------------------------------------
	@Override
	public Tutor obtenerTutor(int idTutor) {
		return entityManager.find(Tutor.class, idTutor);
	}

	//-----------------------------actualizar----------------------------------------
	@Override
	public void actualizarTutor(Tutor tutor) {
		entityManager.merge(tutor);
	}
	//-----------------------------eliminar------------------------------------------ 
	@Override
	public void eliminarTutor(Tutor tutor) {
		entityManager.remove(tutor);
	}

	//----------------------------listar todos tutores-------------------------------
	@Override
	public List<Tutor> obtenerTodosLosTutores() {
		return entityManager.createNamedQuery("Tutor.findAll", Tutor.class).getResultList();
	}

	public TutoresBean() {
	
	}

	
}
