package com.serviciosProyecto;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.entitiesProyecto.Localidad;


@Stateless
@LocalBean
public class LocalidadesBean implements LocalidadesBeanRemote {


	@PersistenceContext
	private EntityManager entityManager;

	//------------------------crear Localidad ------------------------------
	@Override
	public void  crearLocalidad(Localidad localidad) {
		entityManager.persist(localidad);
	}

	//------------------------obtener localidad  ------------------------------
	@Override
	public Localidad obtenerLocalidadPorId(int localidadId) {
		return entityManager.find(Localidad.class, localidadId);
	}

	//----------------------- listar todas  ------------------------------
	@Override
	public List<Localidad> obtenerTodasLocalidades() {
		TypedQuery<Localidad> query = entityManager.createNamedQuery("Localidad.obtenerTodasLocalidades", Localidad.class);
		return query.getResultList();
	}

	//-------------------------actualizar  ------------------------------
	@Override
	public Localidad actualizarLocalidad(Localidad localidad) {
		localidad = entityManager.merge(localidad);
		return localidad;
	}
	//borrar
	@Override
	public void borrarLocalidad(int localidadId) {
		Localidad localidad = entityManager.find(Localidad.class, localidadId);
		if (localidad != null) {
			entityManager.remove(localidad);
		}
	}
	//-------------------------------Listar por filtro-------------------------------------
	public List<Localidad> listarLocalidadFiltro(String nombre) {
		TypedQuery<Localidad> query = entityManager.createNamedQuery("Localidad.obtenerPorNombre", Localidad.class);
		query.setParameter("nombre", "%" + nombre + "%");
		return query.getResultList();
	}

	public Localidad obtenerLocalidadPorNombre(String nombre) {
		TypedQuery<Localidad> query = entityManager.createNamedQuery("Localidad.obtenerPorNombre", Localidad.class);
		query.setParameter("nombre", nombre);
		List<Localidad> localidades = query.getResultList();
		if (!localidades.isEmpty()) {
			return localidades.get(0);
		}
		return null;
	}

	public LocalidadesBean() {
	}


}
