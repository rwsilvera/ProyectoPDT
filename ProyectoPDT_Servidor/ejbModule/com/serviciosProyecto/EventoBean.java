package com.serviciosProyecto;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.entitiesProyecto.Evento;
import com.exceptionProyecto.ServiciosException;


@Stateless
public class EventoBean implements EventoBeanRemote {


	@PersistenceContext
	private EntityManager entityManager;

	public EventoBean() {

	}

	//-----------------------------------Crear Evento------------------------------
	@Override
	public void crearEvento(Evento evento) throws ServiciosException {
		try {
			entityManager.persist(evento);
		} catch (Exception e) {
			throw new ServiciosException("Error al crear el evento");
		}
	}

	//--------------------------- ---Actualizar evento ---------------------------
	@Override
	public void actualizarEvento(Evento evento) throws ServiciosException {
		try {
			entityManager.persist(evento);
		} catch (Exception e) {
			throw new ServiciosException("Error al crear el evento");
		}
	}

	//-------------------------------Borrar un evento------------------------------
	@Override
	public void borrarEvento(int id) throws ServiciosException {
		Evento evento = entityManager.find(Evento.class, id);
		if (evento != null) {
			entityManager.remove(evento);
		}
	}

	//----------------------- Listar todos evento----------------------------------
	@Override
	public List<Evento> obtenerEventoTodos() {
		TypedQuery<Evento> query = entityManager.createQuery("SELECT u FROM Evento u", Evento.class);
		return query.getResultList();
	}

	//-------------------------Listar por flitro --Nombre---------------------------
	@Override
	public List<Evento> listarEventoFiltro(String filtro) {
		TypedQuery<Evento> query = entityManager.createNamedQuery("Eventos.obtenerPorNombre", Evento.class);
		query.setParameter("filtro", "%" + filtro + "%");
		return query.getResultList();
	}

	@Override
	public Evento obtenerEvento(int id) {
		return entityManager.find(Evento.class, id);
	}

}
