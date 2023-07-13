package com.serviciosProyecto;

import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
import com.entitiesProyecto.Generacion;
import com.entitiesProyecto.Itr;
import com.entitiesProyecto.TipoUsuario;
import com.entitiesProyecto.Usuario;
import com.entitiesProyecto.UsuarioEstado;


@Remote
public interface UsuariosBeanRemote {

	Usuario crearUsuario(Usuario usuario);

	Usuario obtenerUsuario(int documento);

	void actualizarUsuario(Usuario usuario);

	void eliminarUsuario(Usuario usuario);

	List<Usuario> obtenerTodosLosUsuarios();

	boolean validarUsuario(String correoElectronico, String contrasenia);

	Usuario obtenerUsuarioDesdeBaseDeDatos(String mail);
	
	boolean validarNombreUsuario(String nomUsuario, String contrasenia);

	Usuario obtenerUsuarioDesdeBaseDeDatosNombre(String nomUsuario);

	List<Usuario> obtenerUsuariosPorEstado(UsuarioEstado estado);

	List<Usuario> obtenerUsuariosPorItr(Itr itr);

	List<Usuario> obtenerUsuariosPorTipo(TipoUsuario tipo);
	
	boolean existeCorreoP (String mail);

	boolean existeDocumento(Long documento);

	Date obtenerFechaNacimiento(long documento);

}
