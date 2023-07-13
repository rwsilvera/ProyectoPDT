package com.entitiesProyecto;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="LOCALIDADES")
@NamedQuery(name="Localidad.obtenerTodasLocalidades", query="SELECT l FROM Localidad l")
@NamedQuery(name = "Localidad.obtenerPorNombre", query = "SELECT l FROM Localidad l WHERE l.nombre = :nombre")

public class Localidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_LOCALIDAD", unique=true, nullable=false, precision=38)
	private long idLocalidad;

	@Column(nullable=false, length=20)
	private String nombre;

	//bi-directional many-to-one association to Departamento
	@ManyToOne
	@JoinColumn(name="ID_DEPARTAMENTO", nullable=false)
	private Departamento departamento;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="localidade")
	private List<Usuario> usuarios;

	public Localidad() {
	}

	public long getIdLocalidad() {
		return this.idLocalidad;
	}

	public void setIdLocalidad(long idLocalidad) {
		this.idLocalidad = idLocalidad;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Departamento getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setLocalidade(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setLocalidade(null);

		return usuario;
	}

}