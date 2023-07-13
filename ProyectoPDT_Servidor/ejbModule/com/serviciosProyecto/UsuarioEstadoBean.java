package com.serviciosProyecto;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.entitiesProyecto.UsuarioEstado;
import com.exceptionProyecto.ServiciosException;


@Stateless
public class UsuarioEstadoBean implements UsuarioEstadoBeanRemote {

	@PersistenceContext
	private EntityManager entityManager;

	public UsuarioEstadoBean() {
		// TODO Auto-generated constructor stub
	}

	//-------------------------------------Crear estado--------------------------------
	@Override
	public UsuarioEstado crearUEstado(UsuarioEstado usuaEstado) throws ServiciosException {
		try {
			entityManager.persist(usuaEstado);
		} catch (Exception e) {
			throw new ServiciosException("Error al crear el estado");
		}
		return usuaEstado;

	}


	//-------------------------------------Actualizar estado--------------------------------
	@Override
	public void actualizarUEstado(UsuarioEstado usuarioEstado) throws ServiciosException {
		try {
			entityManager.persist(usuarioEstado);
		} catch (Exception e) {
			throw new ServiciosException("Error al crear el estado");
		}
	}

	//--------------------------------Borrar un estado de usuario -------------------------
	@Override
	public void borrarUEstado(int id) throws ServiciosException {
		UsuarioEstadoBean usuaEsado= entityManager.find(UsuarioEstadoBean.class, id);
		if(usuaEsado != null) {
			entityManager.remove(usuaEsado);
		}
	}

	//--------------------------Listar todos estados----------------------------------------
	@Override
	public List<UsuarioEstado> obtenerUEstadoTodos() {
		TypedQuery<UsuarioEstado> query = entityManager.createQuery("SELECT u FROM UsuarioEstado u", UsuarioEstado.class); 
		return query.getResultList();
	}

	//---------------------------Listar por flitro --Nombre---------------------------------
	@Override
	public List<UsuarioEstado> listarUEstadoFiltro(String filtro) {
		TypedQuery<UsuarioEstado> query = entityManager.createQuery("SELECT u FROM UsuarioEstado u", UsuarioEstado.class);
		query.setParameter("filtro", "%" + filtro + "%");
		return query.getResultList();
	}

	@Override
	public UsuarioEstado obtenerUEstado(int id) {
		return entityManager.find(UsuarioEstado.class, id);
	}

	@Override
	public UsuarioEstado obtenerEstadoPorNombre(String nombre) {
		TypedQuery<UsuarioEstado> query = entityManager.createNamedQuery("UsuarioEstado.obtenerPorNombre", UsuarioEstado.class);
		query.setParameter("nombre", nombre);
		List<UsuarioEstado> estados = query.getResultList();
		if (!estados.isEmpty()) {
			return estados.get(0);
		}
		return null;
	}
}
