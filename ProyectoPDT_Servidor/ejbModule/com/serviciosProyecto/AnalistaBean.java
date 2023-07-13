package com.serviciosProyecto;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.entitiesProyecto.Analista;
import com.exceptionProyecto.ServiciosException;

@Stateless
@LocalBean
public class AnalistaBean implements AnalistaBeanRemote {

	public AnalistaBean() {
	}
	@PersistenceContext
	private EntityManager entityManager;

	//-------------------------------Crear analista
	public Analista crearAnalista(Analista analista) throws ServiciosException {
		try {
			entityManager.persist(analista);
			entityManager.flush();
			return analista;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	//-------------------------------Actualizar analista 
	public void actualizarAnalista(Analista analista) throws ServiciosException {
		try {
			entityManager.merge(analista);
		} catch (Exception e) {
			throw new ServiciosException("Error al actualizar el área");
		}
	}
	public Analista obtenerAnalista(int id) {
		return entityManager.find(Analista.class, id);
	}

	//-------------------------------Borrar analista
	@Override
	public void borrarAnalista(int id) {
		Analista analista= entityManager.find(Analista.class, id);
		if(analista != null) {
			entityManager.remove(analista);
		}
	}
	//-------------------------------Listar todos usando el metodo namedQuery
	@Override
	public List<Analista> obtenerTodosAnalista() {		
		TypedQuery<Analista> query = entityManager.createQuery("SELECT a FROM Analista a",Analista.class); 
		return query.getResultList();
	}
	//-------------------------------Listar por filtro  Analista
	public List<Analista> listarAnalistaPorFiltro(String filtro) {
		TypedQuery<Analista> query = entityManager.createNamedQuery("Analista.obtenerPorNombre", Analista.class);
		query.setParameter("filtro", "%" + filtro + "%");
		return query.getResultList();
	}
}
