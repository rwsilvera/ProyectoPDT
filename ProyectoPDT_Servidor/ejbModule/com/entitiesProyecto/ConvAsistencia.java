package com.entitiesProyecto;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="CONV_ASISTENCIAS")
@NamedQuery(name="ConvAsistencia.findAll", query="SELECT c FROM ConvAsistencia c")
public class ConvAsistencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="seq_conv_asistencias" , sequenceName = "seq_conv_asistencias", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_conv_asistencias")
	@Column(name="ID_CONVO_ASISTENCIA", unique=true, nullable=false, precision=38)
	private long idConvoAsistencia;

	@Column(nullable=false, precision=38)
	private BigDecimal asistencia;

	@Column(nullable=false, precision=38)
	private BigDecimal calificacion;

	//bi-directional many-to-one association to Estudiante
	@ManyToOne
	@JoinColumn(name="ID_ESTUDIANTE", nullable=false)
	private Estudiante estudiante;

	//bi-directional many-to-one association to Evento
	@ManyToOne
	@JoinColumn(name="ID_EVENTO", nullable=false)
	private Evento evento;

	public ConvAsistencia() {
	}

	public long getIdConvoAsistencia() {
		return this.idConvoAsistencia;
	}

	public void setIdConvoAsistencia(long idConvoAsistencia) {
		this.idConvoAsistencia = idConvoAsistencia;
	}

	public BigDecimal getAsistencia() {
		return this.asistencia;
	}

	public void setAsistencia(BigDecimal asistencia) {
		this.asistencia = asistencia;
	}

	public BigDecimal getCalificacion() {
		return this.calificacion;
	}

	public void setCalificacion(BigDecimal calificacion) {
		this.calificacion = calificacion;
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