package com.serviciosProyecto;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entitiesProyecto.Accion;
import com.exceptionProyecto.ServiciosException;

@Stateless
@LocalBean
public class AccionesBean implements AccionesBeanRemote {

    public AccionesBean() {
    
    }
    
    @PersistenceContext
    EntityManager entityManager;
    
    //--------------------------------CREAR ACCION--------------------------------------------
    @Override
    public Accion crearAccion(Accion accion)throws ServiciosException{
    	try {
    		entityManager.persist(accion);
    		entityManager.flush();
    		System.out.println("Accion creada");
    		return accion; 
    	}catch(Exception e){
    		throw new ServiciosException("ERROR AL CREAR LA ACCION " + e.getMessage());
    	}
    }
    
    //--------------------------------ACTUALIZAR ACCION----------------------------------------
    @Override 
    public void actualizarAccion(Accion accion)throws ServiciosException{
    	try {
    		entityManager.merge(accion); 
    		entityManager.flush();
    		System.out.println("Acción actualizada");
    	}catch(PersistenceException e) {
    		throw new ServiciosException("ERROR AL ACTUALIZAR ACCIÓN " + e.getMessage());
    	}
    }
    
    //---------------------------------BORRAR ACCIÓN--------------------------------------------
    @Override
    public void borrarAccion(Long idAccion) throws ServiciosException{
    	try {
    		Accion accion = entityManager.find(Accion.class, idAccion); 
    		entityManager.remove(accion);
    		entityManager.flush();
    		System.out.println("Acción eliminada");
    	}catch(PersistenceException e) {
			throw new ServiciosException("ERROR AL ELIMINAR ACCIÓN " + e.getMessage());
		}
    }
    
    //---------------------------------LISTAR ACCIONES--------------------------------------------
    @Override
    public ArrayList<Accion> listarAcciones() throws ServiciosException{
    	try {
    		TypedQuery<Accion> query = entityManager.createQuery("SELECT a FROM Accion a", Accion.class); 
    		ArrayList<Accion> lista = (ArrayList<Accion>) query.getResultList();
    		return lista;
    	}catch(PersistenceException e) {
			throw new ServiciosException("ERROR AL LISTAR LAS ACCIONES " + e.getMessage());
		}
    }

}
