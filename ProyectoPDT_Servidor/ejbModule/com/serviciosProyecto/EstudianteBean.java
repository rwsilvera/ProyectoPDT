package com.serviciosProyecto;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.entitiesProyecto.Estudiante;
import com.entitiesProyecto.Generacion;
import com.entitiesProyecto.Usuario;
import com.exceptionProyecto.ServiciosException;

@Stateless
public class EstudianteBean implements EstudianteBeanRemote {

	@PersistenceContext
	private EntityManager entityManager;

	public EstudianteBean() {
	}

	//-------------------------------- Crear Estudiante---------------------------------
	@Override
	public Estudiante crearEstudiante(Estudiante estudiante) {
		try {
			entityManager.persist(estudiante);
			entityManager.flush();
			return estudiante;

		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	//---------------------------------Actualizar Estudiante---------------------------------
	@Override
	public void actualizarEstudiante(Estudiante estudiante) throws ServiciosException {
		try {
			entityManager.merge(estudiante);
		} catch (Exception e) {
			throw new ServiciosException("Error al crear el estudiante");
		}
	}

	//-------------------------------- Borrar un estudiante-------------------------------
	@Override
	public void borrarEstudiante(long id) throws ServiciosException {
		Estudiante estudiante = entityManager.find(Estudiante.class, id);
		if (estudiante != null) {
			entityManager.remove(estudiante);
		}
	}

	// ------------------------Listar todos estudiantes---------------------------------------
	@Override
	public List<Estudiante> obtenerEstudianteTodos() {
		TypedQuery<Estudiante> query = entityManager.createQuery("SELECT e FROM Estudiante e", Estudiante.class);
		return query.getResultList();
	}

	//----------------------------Listar por flitro -- generacion-----------------------------
	@Override
	public List<Estudiante> listarEstudianteFiltro(String filtro) {
		TypedQuery<Estudiante> query = entityManager.createNamedQuery("Estudiante.obtenerPorGeneracion",
				Estudiante.class);
		query.setParameter("filtro", "%" + filtro + "%");
		return query.getResultList();
	}

	public Estudiante getEstudiantePorIdUsuario(Long idUsuario) {
	    TypedQuery<Estudiante> query = entityManager.createQuery("SELECT u.estudiante FROM Usuario u WHERE u.idUsuario = :idUsuario", Estudiante.class);
	    query.setParameter("idUsuario", idUsuario);
	    Estudiante estudiante = query.getSingleResult();
	    return estudiante;
	}
	
	public Estudiante obtenerEstudiantePorIdUsuario(long idUsuario) {
	    TypedQuery<Estudiante> query = entityManager.createQuery(
	        "SELECT e FROM Estudiante e WHERE e.usuario.idUsuario = :idUsuario", Estudiante.class);
	    query.setParameter("idUsuario", idUsuario);
	    List<Estudiante> estudiantes = query.getResultList();
	    if (!estudiantes.isEmpty()) {
	        return estudiantes.get(0);
	    }
	    return null;
	}

	public Estudiante obtenerEstudiantePorUsuario(Usuario usuario) {
	    TypedQuery<Estudiante> query = entityManager.createQuery(
	        "SELECT e FROM Estudiante e WHERE e.usuario = : usuario", Estudiante.class);
	    query.setParameter("usuario", usuario);
	    List<Estudiante> estudiantes = query.getResultList();
	    if (!estudiantes.isEmpty()) {
	        return estudiantes.get(0);
	    }
	    return null;
	}

	// ---------------------Método para obtener por generacion--------------------------------------
		public List<Estudiante> obtenerEstudiantePorGeneracion(Generacion generacion) {
			Query query = entityManager.createNamedQuery("Estudiante.obtenerEstudiantePorGeneracion", Estudiante.class);
			query.setParameter("generacion", generacion);
			return query.getResultList();
		}
}
