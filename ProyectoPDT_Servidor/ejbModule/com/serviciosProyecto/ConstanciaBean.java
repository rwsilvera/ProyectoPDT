package com.serviciosProyecto;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.entitiesProyecto.Constancia;
import com.exceptionProyecto.ServiciosException;

@Stateless
@LocalBean
public class ConstanciaBean implements ConstanciaBeanRemote {

    public ConstanciaBean() {
      
    }
    @PersistenceContext
	private EntityManager entityManager;
    
	//-----------------------------Crear constancia
	public void crearConstancia(Constancia constancia) throws ServiciosException {
		try {
			entityManager.persist(constancia);
		} catch (Exception e) {
			throw new ServiciosException("Error al crear 'Constancia'");
		}
	}
	//-----------------------------Actualizar constancia 
	public void actualizarConstancia(Constancia constancia) throws ServiciosException {
		try {
			entityManager.merge(constancia);
		} catch (Exception e) {
			throw new ServiciosException("Error al actualizar constancia");
		}
	}
	public Constancia obtenerConstancia(int id) {
		return entityManager.find(Constancia.class, id);
	}
	//-----------------------------Borrar Constancia
	@Override
	public void borrarConstancia(int id) {
		Constancia constancia= entityManager.find(Constancia.class, id);
		if(constancia != null) {
			entityManager.remove(constancia);
		}
	}
	//-----------------------------Listar todos usando el metodo namedQuery
	@Override
	public List<Constancia> obtenerTodosConstancia() {		
		TypedQuery<Constancia> query = entityManager.createQuery("SELECT c FROM Constancia c",Constancia.class); 
		return query.getResultList();
	}
	//------------------------------Listar por filtro  Constancia
	public List<Constancia> listarConstanciaPorFiltro(String filtro) {
		TypedQuery<Constancia> query = entityManager.createNamedQuery("Constancia.obtenerPorNombre", Constancia.class);
		query.setParameter("filtro", "%" + filtro + "%");
		return query.getResultList();
	}
}
