package com.entitiesProyecto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ESTADOS")
@NamedQuery(name="Estado.findAll", query="SELECT e FROM Estado e")
@NamedQuery(name = "Estado.obtenerPorNombre", query = "SELECT e FROM Estado e WHERE e.nombre = :nombre")

public class Estado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="seq_estados", sequenceName = "seq_estados", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_estados")
	@Column(name="ID_ESTADO", unique=true, nullable=false, precision=38)
	private long idEstado;

	@Column(nullable=false, length=100)
	private String nombre;

	@Enumerated(EnumType.STRING)
	private VisibilidadR visibilidadR;

	//bi-directional many-to-one association to EstaConstancia
	@OneToMany(mappedBy="estado")
	private List<EstaConstancia> estaConstancias;

	//bi-directional many-to-one association to EstaJustificacion
	@OneToMany(mappedBy="estado")
	private List<EstaJustificacion> estaJustificaciones;

	//bi-directional many-to-one association to EstaReclamo
	@OneToMany(mappedBy="estado")
	private List<EstaReclamo> estaReclamos;

	public Estado() {
	}

	public long getIdEstado() {
		return this.idEstado;
	}

	public void setIdEstado(long idEstado) {
		this.idEstado = idEstado;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<EstaConstancia> getEstaConstancias() {
		return this.estaConstancias;
	}

	public void setEstaConstancias(List<EstaConstancia> estaConstancias) {
		this.estaConstancias = estaConstancias;
	}

	public EstaConstancia addEstaConstancia(EstaConstancia estaConstancia) {
		getEstaConstancias().add(estaConstancia);
		estaConstancia.setEstado(this);

		return estaConstancia;
	}

	public EstaConstancia removeEstaConstancia(EstaConstancia estaConstancia) {
		getEstaConstancias().remove(estaConstancia);
		estaConstancia.setEstado(null);

		return estaConstancia;
	}

	public List<EstaJustificacion> getEstaJustificaciones() {
		return this.estaJustificaciones;
	}

	public void setEstaJustificaciones(List<EstaJustificacion> estaJustificaciones) {
		this.estaJustificaciones = estaJustificaciones;
	}

	public EstaJustificacion addEstaJustificacione(EstaJustificacion estaJustificacione) {
		getEstaJustificaciones().add(estaJustificacione);
		estaJustificacione.setEstado(this);

		return estaJustificacione;
	}

	public EstaJustificacion removeEstaJustificacione(EstaJustificacion estaJustificacione) {
		getEstaJustificaciones().remove(estaJustificacione);
		estaJustificacione.setEstado(null);

		return estaJustificacione;
	}

	public List<EstaReclamo> getEstaReclamos() {
		return this.estaReclamos;
	}

	public void setEstaReclamos(List<EstaReclamo> estaReclamos) {
		this.estaReclamos = estaReclamos;
	}

	public EstaReclamo addEstaReclamo(EstaReclamo estaReclamo) {
		getEstaReclamos().add(estaReclamo);
		estaReclamo.setEstado(this);

		return estaReclamo;
	}

	public EstaReclamo removeEstaReclamo(EstaReclamo estaReclamo) {
		getEstaReclamos().remove(estaReclamo);
		estaReclamo.setEstado(null);

		return estaReclamo;
	}

	public VisibilidadR getVisibilidadR() {
		return visibilidadR;
	}

	public void setVisibilidadR(VisibilidadR visibilidadR) {
		this.visibilidadR = visibilidadR;
	}

    public String toString() {
        return nombre; 
    }

}