package com.serviciosProyecto;

import java.util.List;
import javax.ejb.Remote;
import com.entitiesProyecto.Tutor;

@Remote
public interface TutoresBeanRemote {

	Tutor crearTutor(Tutor tutor) ;

	Tutor obtenerTutor(int idTutor);

	void actualizarTutor(Tutor tutor);

	void eliminarTutor(Tutor tutor);
	
	public Tutor obtenerTutorPorIdUsuario(long id);

	List<Tutor> obtenerTodosLosTutores();
}
