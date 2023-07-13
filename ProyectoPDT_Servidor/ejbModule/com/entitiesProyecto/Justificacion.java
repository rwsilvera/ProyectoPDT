package com.entitiesProyecto;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="JUSTIFICACIONES")
@NamedQuery(name="Justificacion.findAll", query="SELECT j FROM Justificacion j")
public class Justificacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="seq_justicaciones", sequenceName = "seq_justicaciones", allocationSize = 1, initialValue = 1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_justicaciones")
	@Column(name="ID_JUSTIFICACION", unique=true, nullable=false, precision=38)
	private long idJustificacion;

	@Column(nullable=false, length=100)
	private String detalle;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date fecha;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date hora;

	//bi-directional many-to-one association to EstaJustificacion
	@OneToMany(mappedBy="justificacione")
	private List<EstaJustificacion> estaJustificaciones;

	//bi-directional many-to-one association to Estudiante
	@ManyToOne
	@JoinColumn(name="ID_ESTUDIANTE", nullable=false)
	private Estudiante estudiante;

	//bi-directional many-to-one association to Evento
	@ManyToOne
	@JoinColumn(name="ID_EVENTO", nullable=false)
	private Evento evento;

	public Justificacion() {
	}

	public long getIdJustificacion() {
		return this.idJustificacion;
	}

	public void setIdJustificacion(long idJustificacion) {
		this.idJustificacion = idJustificacion;
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

	public List<EstaJustificacion> getEstaJustificaciones() {
		return this.estaJustificaciones;
	}

	public void setEstaJustificaciones(List<EstaJustificacion> estaJustificaciones) {
		this.estaJustificaciones = estaJustificaciones;
	}

	public EstaJustificacion addEstaJustificacione(EstaJustificacion estaJustificacione) {
		getEstaJustificaciones().add(estaJustificacione);
		estaJustificacione.setJustificacione(this);

		return estaJustificacione;
	}

	public EstaJustificacion removeEstaJustificacione(EstaJustificacion estaJustificacione) {
		getEstaJustificaciones().remove(estaJustificacione);
		estaJustificacione.setJustificacione(null);

		return estaJustificacione;
	}

	public Estudiante getEstudiante() {
		return this.estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public Evento getEvento() {
		return this.evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

}