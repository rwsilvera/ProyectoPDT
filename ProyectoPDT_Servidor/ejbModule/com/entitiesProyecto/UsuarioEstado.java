package com.entitiesProyecto;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="USUA_ESTADOS")
@NamedQuery(name="UsuarioEstado.obtenerUEstadoTodos", query="SELECT u FROM UsuarioEstado u")
@NamedQuery(name = "UsuarioEstado.obtenerPorNombre", query = "SELECT ue FROM UsuarioEstado ue WHERE ue.nombre = :nombre")

public class UsuarioEstado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="seq_esta_usuario", sequenceName = "seq_esta_usuario", allocationSize = 1, initialValue = 1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_esta_usuario")
	@Column(name="ID_ESTA_USUARIO", unique=true, nullable=false, precision=38)
	private long idEstaUsuario;

	@Column(nullable=false, length=20)
	private String nombre;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="usuaEstado")
	private List<Usuario> usuarios;

	public UsuarioEstado() {
	}

	public long getIdEstaUsuario() {
		return this.idEstaUsuario;
	}

	public void setIdEstaUsuario(long idEstaUsuario) {
		this.idEstaUsuario = idEstaUsuario;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setUsuaEstado(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setUsuaEstado(null);

		return usuario;
	}
	@Override
	public String toString() {
		return "UsuarioEstado [idEstaUsuario=" + idEstaUsuario + ", nombre=" + nombre + ", usuarios=" + usuarios + "]";
	}
}