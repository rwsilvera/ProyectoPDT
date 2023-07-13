package com.serviciosProyecto;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.entitiesProyecto.ConvAsistencia;
import com.exceptionProyecto.ServiciosException;


@Stateless
@LocalBean
public class ConvAsistenciaBean implements ConvAsistenciaBeanRemote {

	public ConvAsistenciaBean() {

	}
	@PersistenceContext
	private EntityManager entityManager;

	//-----------------------------Crear ConvAsistencia
	public void crearConvAsistencia(ConvAsistencia convAsistencia) throws ServiciosException {
		try {
			entityManager.persist(convAsistencia);
		} catch (Exception e) {
			throw new ServiciosException("Error al crear 'ConvAsistencia'");
		}
	}
	//-----------------------------Actualizar ConvAsistencia
	public void actualizarConvAsistencia(ConvAsistencia convAsistencia) throws ServiciosException {
		try {
			entityManager.merge(convAsistencia);
		} catch (Exception e) {
			throw new ServiciosException("Error al actualizar el ConvAsistencia");
		}
	}
	public ConvAsistencia obtenerConvAsistencia(int id) {
		return entityManager.find(ConvAsistencia.class, id);
	}
	// ----------------------------Borrar ConvAsistencia
	@Override
	public void borrarConvAsistencia(int id) {
		ConvAsistencia convAsistencia= entityManager.find(ConvAsistencia.class, id);
		if(convAsistencia != null) {
			entityManager.remove(convAsistencia);
		}
	}
	//-----------------------------Listar todos usando el metodo namedQuery
	@Override
	public List<ConvAsistencia> obtenerTodosConvAsistencia() {		
		TypedQuery<ConvAsistencia> query = entityManager.createQuery("SELECT c FROM ConvAsistencia c",ConvAsistencia.class); 
		return query.getResultList();
	}
	//-----------------------------Listar por filtro ConvAsistencia
	public List<ConvAsistencia> listarConvAsistenciaPorFiltro(String filtro) {
		TypedQuery<ConvAsistencia> query = entityManager.createNamedQuery("ConvAsistencia.obtenerPorNombre", ConvAsistencia.class);
		query.setParameter("filtro", "%" + filtro + "%");
		return query.getResultList();
	}
}
