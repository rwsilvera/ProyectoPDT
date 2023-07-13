package com.serviciosProyecto;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.entitiesProyecto.Generacion;
import com.entitiesProyecto.Itr;
import com.entitiesProyecto.TipoUsuario;
import com.entitiesProyecto.Usuario;
import com.entitiesProyecto.UsuarioEstado;

@Stateless
public class UsuariosBean implements UsuariosBeanRemote {

	@PersistenceContext
	private EntityManager entityManager;

	//------------------------------------crear usuario-------------------------------
	@Override
	public Usuario crearUsuario(Usuario usuario) {
		try {
			entityManager.persist(usuario);
			entityManager.flush();
			return usuario;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	//-----------------------------------obtener un usuario--------------------------
	@Override
	public Usuario obtenerUsuario(int documento) {
		return entityManager.find(Usuario.class, documento);
	}

	//-----------------------------------actualizar usuario--------------------------
	@Override
	public void actualizarUsuario(Usuario usuario) {
		entityManager.merge(usuario);
	}

	//-----------------------------------eliminar usuario----------------------------
	@Override
	public void eliminarUsuario(Usuario usuario) {
		entityManager.remove(usuario);
	}

	//-----------------------------------listar todo---------------------------------
	@Override
	public List<Usuario> obtenerTodosLosUsuarios() {
		return entityManager.createNamedQuery("Usuario.obtenerTodosLosUsuarios", Usuario.class).getResultList();
	}

	//-----------------------------------validar usuario----------------------------
	public boolean validarUsuario(String mail, String contrasenia) {
		// Realizar la consulta para verificar si el usuario existe en la base de datos
		long count = (Long) entityManager
				.createQuery("SELECT COUNT(u) FROM Usuario u WHERE u.mail = :mail AND u.contrasenia = :contrasenia")
				.setParameter("mail", mail).setParameter("contrasenia", contrasenia).getSingleResult();

		return count > 0;
	}

	public UsuariosBean() {
	
	}

	//-----------------------------------obtener mail ----------------------------------
	public Usuario obtenerUsuarioDesdeBaseDeDatos(String mail) {
		try {
			Query query = entityManager.createNamedQuery("Usuario.ObtenerMailUsuario");
			query.setParameter("correo", mail);

			List<Usuario> usuarios = query.getResultList();

			if (!usuarios.isEmpty()) {
				return usuarios.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	//---------------------------------- obtener nombre-----------------------------------
	public Usuario obtenerUsuarioDesdeBaseDeDatosNombre(String nomUsuario) {
		try {
			Query query = entityManager.createNamedQuery("Usuario.obtenerUsuarioDesdeBaseDeDatosNombre");
			query.setParameter("nomUsuario", nomUsuario);

			List<Usuario> usuarios = query.getResultList();

			if (!usuarios.isEmpty()) {
				return usuarios.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// validar usuario
	public boolean validarNombreUsuario(String nomUsuario, String contrasenia) {
		// Realizar la consulta para verificar si el usuario existe en la base de datos
		long count = (Long) entityManager
				.createQuery("SELECT COUNT(u) FROM Usuario u WHERE u.nomUsuario = :nomUsuario AND u.contrasenia = :contrasenia")
				.setParameter("nomUsuario", nomUsuario).setParameter("contrasenia", contrasenia).getSingleResult();

		return count > 0;
	}

	// Método para obtener usuarios por estado
	public List<Usuario> obtenerUsuariosPorEstado(UsuarioEstado estado) {
		Query query = entityManager.createQuery("SELECT u FROM Usuario u WHERE u.usuaEstado = :estado", Usuario.class);
		query.setParameter("estado", estado);
		return query.getResultList();
	}

	// Método para obtener usuarios por itr
	public List<Usuario> obtenerUsuariosPorItr(Itr itr) {
		Query query = entityManager.createQuery("SELECT u FROM Usuario u WHERE u.itr = :itr", Usuario.class);
		query.setParameter("itr", itr); // Corrección: "itr" en lugar de "Itr"
		return query.getResultList();
	}

	// Método para obtener usuarios por tipo
	public List<Usuario> obtenerUsuariosPorTipo(TipoUsuario tipo) {
		Query query = entityManager.createNamedQuery("Usuario.obtenerUsuariosPorTipo", Usuario.class);
		query.setParameter("tipo", tipo);
		return query.getResultList();
	}


	// Obtener fecha de nacimiento en formato java.util.Date
	@Override
	public Date obtenerFechaNacimiento(long documento) {
		TypedQuery<Date> query = entityManager
				.createQuery("SELECT u.fecNacimiento FROM Usuario u WHERE u.documento = :documento", Date.class);
		query.setParameter("documento", documento);
		return query.getSingleResult();

	}
// existe documento
	@Override
	public boolean existeDocumento(Long documento) {
	    TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(u) FROM Usuario u WHERE u.documento = :documento", Long.class);
	    query.setParameter("documento", documento);
	    Long count = query.getSingleResult();
	    return count > 0;
	}
	
//existe correo
	@Override
	public boolean existeCorreoP(String mail) {
	TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(u)     FROM Usuario u WHERE u.mail = :mail", Long.class);
	query.setParameter("mail", mail);
		 Long count = query.getSingleResult();
		 return count > 0;
	}


}
