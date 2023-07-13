package com.entitiesProyecto;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="ESTA_JUSTIFICACIONES")
@NamedQuery(name="EstaJustificacion.findAll", query="SELECT e FROM EstaJustificacion e")
public class EstaJustificacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="seq_esta_justificaciones", sequenceName = "seq_esta_justificaciones", allocationSize = 1, initialValue = 1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_esta_justificaciones")
	@Column(name="ID_ESTA_JUSTIFICACION", unique=true, nullable=false, precision=38)
	private long idEstaJustificacion;

	//bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="ID_ESTADO", nullable=false)
	private Estado estado;

	//bi-directional many-to-one association to Justificacion
	@ManyToOne
	@JoinColumn(name="ID_JUSTIFICACION", nullable=false)
	private Justificacion justificacione;

	public EstaJustificacion() {
	}

	public long getIdEstaJustificacion() {
		return this.idEstaJustificacion;
	}

	public void setIdEstaJustificacion(long idEstaJustificacion) {
		this.idEstaJustificacion = idEstaJustificacion;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Justificacion getJustificacione() {
		return this.justificacione;
	}

	public void setJustificacione(Justificacion justificacione) {
		this.justificacione = justificacione;
	}
}