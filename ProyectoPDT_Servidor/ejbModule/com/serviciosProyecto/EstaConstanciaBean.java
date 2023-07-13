package com.serviciosProyecto;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.entitiesProyecto.EstaConstancia;
import com.exceptionProyecto.ServiciosException;

@Stateless
@LocalBean
public class EstaConstanciaBean implements EstaConstanciaBeanRemote {

	public EstaConstanciaBean() {
	}
	@PersistenceContext
	private EntityManager entityManager;

	//----------------------------Crear EstaConstancia---------------------------------
	public void crearEstaConstancia(EstaConstancia estaConstancia) throws ServiciosException {
		try {
			entityManager.persist(estaConstancia);
		} catch (Exception e) {
			throw new ServiciosException("Error al crear 'EstaConstancia'");
		}
	}

	//----------------------------Actualizar EstaConstancia----------------------------
	public void actualizarEstaConstancia(EstaConstancia estaConstancia) throws ServiciosException {
		try {
			entityManager.merge(estaConstancia);
		} catch (Exception e) {
			throw new ServiciosException("Error al actualizar EstaConstancia");
		}
	}

	public EstaConstancia obtenerEstaConstancia(int id) {
		return entityManager.find(EstaConstancia.class, id);
	}

	// ---------------------------Borrar EstaConstancia---------------------------------
	@Override
	public void borrarEstaConstancia(int id) {
		EstaConstancia estaConstancia= entityManager.find(EstaConstancia.class, id);
		if(estaConstancia != null) {
			entityManager.remove(estaConstancia);
		}
	}

	//------------------------Listar todos EstaConstancia--------------------------------
	@Override
	public List<EstaConstancia> obtenerTodosEstaConstancia() {		
		TypedQuery<EstaConstancia> query = entityManager.createQuery("SELECT e FROM EstaConstancia e",EstaConstancia.class); 
		return query.getResultList();
	}

	//-----------------------Listar por filtro nombre EstaConstancia ---------------------
	public List<EstaConstancia> listarEstaConstanciaPorFiltro(String filtro) {
		TypedQuery<EstaConstancia> query = entityManager.createNamedQuery("EstaConstancia.obtenerPorNombre", EstaConstancia.class);
		query.setParameter("filtro", "%" + filtro + "%");
		return query.getResultList();
	}

}
