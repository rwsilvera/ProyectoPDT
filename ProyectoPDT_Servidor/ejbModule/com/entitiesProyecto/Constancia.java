package com.entitiesProyecto;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="CONSTANCIAS")
@NamedQuery(name="Constancia.findAll", query="SELECT c FROM Constancia c")
public class Constancia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="seq_constancia" , sequenceName = "seq_constancia", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_constancia")
	@Column(name="ID_CONSTANCIA", unique=true, nullable=false, precision=38)
	private long idConstancia;

	@Column(nullable=false, length=100)
	private String detalle;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date fecha;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date hora;

	@Column(nullable=false, length=100)
	private String tipo;

	//bi-directional many-to-one association to Estudiante
	@ManyToOne
	@JoinColumn(name="ID_ESTUDIANTE", nullable=false)
	private Estudiante estudiante;

	//bi-directional many-to-one association to Evento
	@ManyToOne
	@JoinColumn(name="ID_EVENTO", nullable=false)
	private Evento evento;

	//bi-directional many-to-one association to EstaConstancia
	@OneToMany(mappedBy="constancia")
	private List<EstaConstancia> estaConstancias;

	public Constancia() {
	}

	public long getIdConstancia() {
		return this.idConstancia;
	}

	public void setIdConstancia(long idConstancia) {
		this.idConstancia = idConstancia;
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

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	public List<EstaConstancia> getEstaConstancias() {
		return this.estaConstancias;
	}

	public void setEstaConstancias(List<EstaConstancia> estaConstancias) {
		this.estaConstancias = estaConstancias;
	}

	public EstaConstancia addEstaConstancia(EstaConstancia estaConstancia) {
		getEstaConstancias().add(estaConstancia);
		estaConstancia.setConstancia(this);

		return estaConstancia;
	}

	public EstaConstancia removeEstaConstancia(EstaConstancia estaConstancia) {
		getEstaConstancias().remove(estaConstancia);
		estaConstancia.setConstancia(null);

		return estaConstancia;
	}

}