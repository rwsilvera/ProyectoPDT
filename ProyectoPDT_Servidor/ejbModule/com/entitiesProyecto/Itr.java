package com.entitiesProyecto;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="ITRS")
@NamedQuery(name="Itr.obtenerItrTodos", query="SELECT i FROM Itr i")
@NamedQuery(name = "Itr.obtenerPorNombre", query = "SELECT i FROM Itr i WHERE i.nombre = :nombre")

public class Itr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="seq_itrs", sequenceName = "seq_itrs", allocationSize = 1, initialValue = 1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_itrs")
	@Column(name="ID_ITR", unique=true, nullable=false, precision=38)
	private long idItr;

	@Column(nullable=false, length=20)
	private String nombre;

	@Enumerated(EnumType.STRING)
	private EstadoItr estado;
	
	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="itr")
	private List<Usuario> usuarios;

	public Itr() {
	}

	public long getIdItr() {
		return this.idItr;
	}

	public void setIdItr(long idItr) {
		this.idItr = idItr;
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
		usuario.setItr(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setItr(null);

		return usuario;
	}


	public EstadoItr getEstado() {
		return estado;
	}

	public void setEstado(EstadoItr estado) {
		this.estado = estado;
	}

	@Override
    public String toString() {
        return nombre; 
    }
	
}