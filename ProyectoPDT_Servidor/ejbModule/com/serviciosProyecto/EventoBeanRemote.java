package com.serviciosProyecto;

import java.util.List;
import javax.ejb.Remote;
import com.entitiesProyecto.Evento;
import com.exceptionProyecto.ServiciosException;

@Remote
public interface EventoBeanRemote {
		void crearEvento(Evento evento) throws ServiciosException;

	    void actualizarEvento(Evento evento) throws ServiciosException;

	    void borrarEvento(int id) throws ServiciosException;

	    List<Evento> obtenerEventoTodos();

	    List<Evento> listarEventoFiltro(String filtro);
		
		public Evento obtenerEvento(int id);

}
