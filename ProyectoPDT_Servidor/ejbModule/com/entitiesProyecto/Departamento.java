package com.entitiesProyecto;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="DEPARTAMENTOS")
@NamedQuery(name="Departamento.findAll", query="SELECT d FROM Departamento d")
@NamedQuery(name = "Departamento.obtenerPorNombre", query = "SELECT d FROM Departamento d WHERE d.nombre = :nombre")
public class Departamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_DEPARTAMENTO", unique=true, nullable=false, precision=38)
	private long idDepartamento;

	@Column(nullable=false, length=20)
	private String nombre;

	//bi-directional many-to-one association to Localidad
	@OneToMany(mappedBy="departamento")
	private List<Localidad> localidades;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="departamento")
	private List<Usuario> usuarios;

	public Departamento() {
	}

	public long getIdDepartamento() {
		return this.idDepartamento;
	}

	public void setIdDepartamento(long idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Localidad> getLocalidades() {
		return this.localidades;
	}

	public void setLocalidades(List<Localidad> localidades) {
		this.localidades = localidades;
	}

	public Localidad addLocalidade(Localidad localidade) {
		getLocalidades().add(localidade);
		localidade.setDepartamento(this);

		return localidade;
	}

	public Localidad removeLocalidade(Localidad localidade) {
		getLocalidades().remove(localidade);
		localidade.setDepartamento(null);

		return localidade;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setDepartamento(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setDepartamento(null);

		return usuario;
	}

}