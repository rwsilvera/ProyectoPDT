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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ESTUDIANTES")
@NamedQuery(name="Estudiante.findAll", query="SELECT e FROM Estudiante e")
@NamedQuery(name = "Estudiante.obtenerEstudiantePorGeneracion", query = "SELECT e FROM Estudiante e WHERE e.generacion = :generacion")
@NamedQuery(name = "Estudiante.obtenerEstudiantePorUsuario", query = "SELECT e FROM Estudiante e WHERE e.usuario = :usuario")


public class Estudiante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="seq_estudiantes", sequenceName = "seq_estudiantes", allocationSize = 1, initialValue = 1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_estudiantes")
	@Column(name="ID_ESTUDIANTE", unique=true, nullable=false, precision=38)
	private long idEstudiante;

	@Enumerated(EnumType.STRING)
	private Generacion generacion;

	@Enumerated(EnumType.STRING)
	private Semestre semestre;

	//bi-directional many-to-one association to Constancia
	@OneToMany(mappedBy="estudiante")
	private List<Constancia> constancias;

	//bi-directional many-to-one association to ConvAsistencia
	@OneToMany(mappedBy="estudiante")
	private List<ConvAsistencia> convAsistencias;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="ID_USUARIO", nullable=false)
	private Usuario usuario;

	//bi-directional many-to-one association to Justificacion
	@OneToMany(mappedBy="estudiante")
	private List<Justificacion> justificaciones;

	//bi-directional many-to-one association to Reclamo
	@OneToMany(mappedBy="estudiante")
	private List<Reclamo> reclamos;

	public Estudiante() {
	}

	public long getIdEstudiante() {
		return this.idEstudiante;
	}

	public void setIdEstudiante(long idEstudiante) {
		this.idEstudiante = idEstudiante;
	}

	public Generacion getGeneracion() {
		return this.generacion;
	}

	public void setGeneracion(Generacion generacion) {
		this.generacion = generacion;
	}

	public Semestre getSemestre() {
		return this.semestre;
	}

	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}

	public List<Constancia> getConstancias() {
		return this.constancias;
	}

	public void setConstancias(List<Constancia> constancias) {
		this.constancias = constancias;
	}


	public Estudiante(Generacion gen, Semestre semestre, Usuario usuario) {
		super();
		this.generacion = gen;
		this.semestre = semestre;
		this.usuario = usuario;
	}

	public Constancia addConstancia(Constancia constancia) {
		getConstancias().add(constancia);
		constancia.setEstudiante(this);

		return constancia;
	}

	public Constancia removeConstancia(Constancia constancia) {
		getConstancias().remove(constancia);
		constancia.setEstudiante(null);

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
		convAsistencia.setEstudiante(this);

		return convAsistencia;
	}

	public ConvAsistencia removeConvAsistencia(ConvAsistencia convAsistencia) {
		getConvAsistencias().remove(convAsistencia);
		convAsistencia.setEstudiante(null);

		return convAsistencia;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Justificacion> getJustificaciones() {
		return this.justificaciones;
	}

	public void setJustificaciones(List<Justificacion> justificaciones) {
		this.justificaciones = justificaciones;
	}

	public Justificacion addJustificacione(Justificacion justificacione) {
		getJustificaciones().add(justificacione);
		justificacione.setEstudiante(this);

		return justificacione;
	}

	public Justificacion removeJustificacione(Justificacion justificacione) {
		getJustificaciones().remove(justificacione);
		justificacione.setEstudiante(null);

		return justificacione;
	}

	public List<Reclamo> getReclamos() {
		return this.reclamos;
	}

	public void setReclamos(List<Reclamo> reclamos) {
		this.reclamos = reclamos;
	}

	public Reclamo addReclamo(Reclamo reclamo) {
		getReclamos().add(reclamo);
		reclamo.setEstudiante(this);

		return reclamo;
	}

	public Reclamo removeReclamo(Reclamo reclamo) {
		getReclamos().remove(reclamo);
		reclamo.setEstudiante(null);

		return reclamo;
	}

	@Override
	public String toString() {
		return "Estudiante [generacion=" + generacion + ", semestre=" + semestre + ", usuario=" + usuario + "]";
	}


}