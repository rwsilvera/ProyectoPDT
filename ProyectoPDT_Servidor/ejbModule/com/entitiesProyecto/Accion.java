package com.entitiesProyecto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "ACCIONES")
@NamedQuery(name="Accion.findAll", query="SELECT a FROM Accion a")

public class Accion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "seq_acciones", sequenceName = "seq_acciones", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_acciones")
	@Column(name = "ID_ACCION", unique=true, nullable=false)
	private long idAccion; 

	@ManyToOne
	@JoinColumn(name = "ID_RECLAMO")
	private Reclamo reclamo; 

	@Column(name = "FECHA_HORA")
	private Date fechaHora;

	@Column(name = "DETALLE")
	private String detalle; 

	@ManyToOne
	@JoinColumn(name = "ID_ANALISTA")
	private Analista analista; 

	public Accion() {
		super();
	}

	public long getIdAccion() {
		return idAccion;
	}

	public Reclamo getReclamo() {
		return reclamo;
	}

	public Date getFechaHora() {
		return fechaHora;
	}

	public String getDetalle() {
		return detalle;
	}

	public Analista getAnalista() {
		return analista;
	}

	public void setIdAccion(long idAccion) {
		this.idAccion = idAccion;
	}

	public void setReclamo(Reclamo reclamo) {
		this.reclamo = reclamo;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public void setAnalista(Analista analista) {
		this.analista = analista;
	}

}
