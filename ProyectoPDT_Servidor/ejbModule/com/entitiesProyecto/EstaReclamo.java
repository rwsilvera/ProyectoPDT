package com.entitiesProyecto;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="ESTA_RECLAMOS")
@NamedQuery(name="EstaReclamo.findAll", query="SELECT e FROM EstaReclamo e")
public class EstaReclamo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="seq_esta_reclamos", sequenceName = "seq_esta_reclamos", allocationSize = 1, initialValue = 1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_esta_reclamos")
	@Column(name="ID_ESTA_RECLAMO", unique=true, nullable=false, precision=38)
	private long idEstaReclamo;

	//bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="ID_ESTADO", nullable=false)
	private Estado estado;

	//bi-directional many-to-one association to Reclamo
	@ManyToOne
	@JoinColumn(name="ID_RECLAMO", nullable=false)
	private Reclamo reclamo;

	public EstaReclamo() {
	}

	public long getIdEstaReclamo() {
		return this.idEstaReclamo;
	}

	public void setIdEstaReclamo(long idEstaReclamo) {
		this.idEstaReclamo = idEstaReclamo;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Reclamo getReclamo() {
		return this.reclamo;
	}

	public void setReclamo(Reclamo reclamo) {
		this.reclamo = reclamo;
	}

}