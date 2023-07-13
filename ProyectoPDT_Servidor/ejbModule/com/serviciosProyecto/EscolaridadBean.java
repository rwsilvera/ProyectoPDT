package com.serviciosProyecto;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.entitiesProyecto.Escolaridad;

@Stateless
@LocalBean
public class EscolaridadBean implements EscolaridadBeanRemote {
	
	@PersistenceContext
	private EntityManager entityManager;
   
    public EscolaridadBean() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public List<Escolaridad> obtenerEscolaridadPorIdEstudiante(Long idEstudiante) {
        TypedQuery<Escolaridad> query = entityManager.createQuery("SELECT e FROM Escolaridad e WHERE e.estudiante.idEstudiante = :idEstudiante", Escolaridad.class);
        query.setParameter("idEstudiante", idEstudiante);
        List<Escolaridad> escolaridades = query.getResultList();
        return escolaridades;
    }

}
