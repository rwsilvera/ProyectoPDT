package com.entitiesProyecto;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="EVENTOS")
@NamedQuery(name="Evento.findAll", query="SELECT e FROM Evento e")
public class Evento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="seq_eventos", sequenceName = "seq_eventos", allocationSize = 1, initialValue = 1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_eventos")
	@Column(name="ID_EVENTO", unique=true, nullable=false, precision=38)
	private long idEvento;

	@Temporal(TemporalType.DATE)
	@Column(name="FEC_FINAL", nullable=false)
	private Date fecFinal;

	@Temporal(TemporalType.DATE)
	@Column(name="FEC_INICIO", nullable=false)
	private Date fecInicio;

	@Temporal(TemporalType.DATE)
	@Column(name="HOR_FINAL", nullable=false)
	private Date horFinal;

	@Temporal(TemporalType.DATE)
	@Column(name="HOR_INICIO", nullable=false)
	private Date horInicio;

	@Column(nullable=false, length=100)
	private String informacion;

	@Column(nullable=false, length=100)
	private String titulo;

	//bi-directional many-to-one association to Constancia
	@OneToMany(mappedBy="evento")
	private List<Constancia> constancias;

	//bi-directional many-to-one association to ConvAsistencia
	@OneToMany(mappedBy="evento")
	private List<ConvAsistencia> convAsistencias;

	//bi-directional many-to-one association to Analista
	@ManyToOne
	@JoinColumn(name="ID_ANALISTA", nullable=false)
	private Analista analista;

	//bi-directional many-to-one association to Tutor
	@ManyToOne
	@JoinColumn(name="ID_TUTOR", nullable=false)
	private Tutor tutore;

	//bi-directional many-to-one association to Justificacion
	@OneToMany(mappedBy="evento")
	private List<Justificacion> justificaciones;

	public Evento() {
	}

	public long getIdEvento() {
		return this.idEvento;
	}

	public void setIdEvento(long idEvento) {
		this.idEvento = idEvento;
	}

	public Date getFecFinal() {
		return this.fecFinal;
	}

	public void setFecFinal(Date fecFinal) {
		this.fecFinal = fecFinal;
	}

	public Date getFecInicio() {
		return this.fecInicio;
	}

	public void setFecInicio(Date fecInicio) {
		this.fecInicio = fecInicio;
	}

	public Date getHorFinal() {
		return this.horFinal;
	}

	public void setHorFinal(Date horFinal) {
		this.horFinal = horFinal;
	}

	public Date getHorInicio() {
		return this.horInicio;
	}

	public void setHorInicio(Date horInicio) {
		this.horInicio = horInicio;
	}

	public String getInformacion() {
		return this.informacion;
	}

	public void setInformacion(String informacion) {
		this.informacion = informacion;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Constancia> getConstancias() {
		return this.constancias;
	}

	public void setConstancias(List<Constancia> constancias) {
		this.constancias = constancias;
	}

	public Constancia addConstancia(Constancia constancia) {
		getConstancias().add(constancia);
		constancia.setEvento(this);

		return constancia;
	}

	public Constancia removeConstancia(Constancia constancia) {
		getConstancias().remove(constancia);
		constancia.setEvento(null);

		return constancia;
	}

	public List<ConvAsistencia> getConvAsistencias() {
		return this.convAsistencias;
	}

	public void setConvAsistencias(List<ConvAsistencia> convAsistencias) {
		this.convAsistencias = convAsistencias;
	}

	public ConvAsistencia addConvAsistencia(ConvAsistencia convAsistencia) {
		getConvAsistencias().add(convAsistencia);
		convAsistencia.setEvento(this);

		return convAsistencia;
	}

	public ConvAsistencia removeConvAsistencia(ConvAsistencia convAsistencia) {
		getConvAsistencias().remove(convAsistencia);
		convAsistencia.setEvento(null);

		return convAsistencia;
	}

	public Analista getAnalista() {
		return this.analista;
	}

	public void setAnalista(Analista analista) {
		this.analista = analista;
	}

	public Tutor getTutore() {
		return this.tutore;
	}

	public void setTutore(Tutor tutore) {
		this.tutore = tutore;
	}

	public List<Justificacion> getJustificaciones() {
		return this.justificaciones;
	}

	public void setJustificaciones(List<Justificacion> justificaciones) {
		this.justificaciones = justificaciones;
	}

	public Justificacion addJustificacione(Justificacion justificacione) {
		getJustificaciones().add(justificacione);
		justificacione.setEvento(this);

		return justificacione;
	}

	public Justificacion removeJustificacione(Justificacion justificacione) {
		getJustificaciones().remove(justificacione);
		justificacione.setEvento(null);

		return justificacione;
	}

}