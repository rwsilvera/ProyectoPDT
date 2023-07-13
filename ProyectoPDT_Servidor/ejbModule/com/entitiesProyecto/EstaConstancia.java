package com.entitiesProyecto;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="ESTA_CONSTANCIAS")
@NamedQuery(name="EstaConstancia.findAll", query="SELECT e FROM EstaConstancia e")
public class EstaConstancia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="seq_esta_constancias", sequenceName = "seq_esta_constancias", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_esta_constancias")
	@Column(name="ID_ESTA_CONSTANCIA", unique=true, nullable=false, precision=38)
	private long idEstaConstancia;

	//bi-directional many-to-one association to Constancia
	@ManyToOne
	@JoinColumn(name="ID_CONSTANCIA", nullable=false)
	private Constancia constancia;

	//bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="ID_ESTADO", nullable=false)
	private Estado estado;

	public EstaConstancia() {
	}

	public long getIdEstaConstancia() {
		return this.idEstaConstancia;
	}

	public void setIdEstaConstancia(long idEstaConstancia) {
		this.idEstaConstancia = idEstaConstancia;
	}

	public Constancia getConstancia() {
		return this.constancia;
	}

	public void setConstancia(Constancia constancia) {
		this.constancia = constancia;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}