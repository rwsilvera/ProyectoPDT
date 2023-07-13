package com.entitiesProyecto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="RECLAMOS")
@NamedQuery(name="Reclamo.findAll", query="SELECT r FROM Reclamo r")
public class Reclamo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="seq_reclamos", sequenceName = "seq_reclamos", allocationSize = 1, initialValue = 1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_reclamos")
	@Column(name="ID_RECLAMO", unique=true, nullable=false, precision=38)
	private long idReclamo;

	@Column(nullable=false, length=100)
	private String asunto;
	
	@Column(nullable=false, length=100)
	private String credito;
	
	@Column(nullable=false, length=100)
	private String nombre;
	
	@Column(nullable=false, length=100)
	private String detalle;
	
	@Enumerated(EnumType.STRING)
	private Semestre semestre;
	
	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_RECLAMO", nullable=false)
	private Date fecha;
	
	@Temporal(TemporalType.DATE)
	@Column(name="HORA_RECLAMO",nullable=false)
	private Date hora;
	
	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_ACTIVIDAD", nullable=false)
	private Date fechaActividad;
	
	@Enumerated(EnumType.STRING)
	@Column(name="TIPO_ACTIVIDAD", nullable=false)
	private TipoActividad tipoActividad;

	
	//bi-directional many-to-one association to EstaReclamo
	@OneToMany(mappedBy="reclamo")
	private List<EstaReclamo> estaReclamos;

	//bi-directional many-to-one association to Estudiante
	@ManyToOne
	@JoinColumn(name="ID_ESTUDIANTE", nullable=false)
	private Estudiante estudiante;
	
	//bi-directional many-to-one association to Tutor
	@ManyToOne
	@JoinColumn(name="ID_TUTOR", nullable=false)
	private Tutor tutore;

	public Reclamo() {
	}

	public long getIdReclamo() {
		return this.idReclamo;
	}

	public void setIdReclamo(long idReclamo) {
		this.idReclamo = idReclamo;
	}

	public String getAsunto() {
		return this.asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getDetalle() {
		return this.detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getHora() {
		return this.hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public List<EstaReclamo> getEstaReclamos() {
		return this.estaReclamos;
	}

	public void setEstaReclamos(List<EstaReclamo> estaReclamos) {
		this.estaReclamos = estaReclamos;
	}

	public EstaReclamo addEstaReclamo(EstaReclamo estaReclamo) {
		getEstaReclamos().add(estaReclamo);
		estaReclamo.setReclamo(this);

		return estaReclamo;
	}

	public EstaReclamo removeEstaReclamo(EstaReclamo estaReclamo) {
		getEstaReclamos().remove(estaReclamo);
		estaReclamo.setReclamo(null);

		return estaReclamo;
	}

	public Estudiante getEstudiante() {
		return this.estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public String getCredito() {
		return credito;
	}

	public void setCredito(String credito) {
		this.credito = credito;
	}

	public Semestre getSemestre() {
		return semestre;
	}

	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}

	public Date getFechaActividad() {
		return fechaActividad;
	}

	public void setFechaActividad(Date fechaActividad) {
		this.fechaActividad = fechaActividad;
	}

	public TipoActividad getTipoActividad() {
		return tipoActividad;
	}

	public void setTipoActividad(TipoActividad tipoActividad) {
		this.tipoActividad = tipoActividad;
	}

	public Tutor getTutore() {
		return tutore;
	}

	public void setTutore(Tutor tutore) {
		this.tutore = tutore;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}