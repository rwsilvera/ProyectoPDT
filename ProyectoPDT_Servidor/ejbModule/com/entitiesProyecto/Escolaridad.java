package com.entitiesProyecto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;


@Entity
@NamedQuery(name="Escolaridad.findAll", query="SELECT e FROM Escolaridad e")
public class Escolaridad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="seq_escolaridad", sequenceName="seq_escolaridad" , allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_escolaridad")
	@Column(name="ID_ESCOLARIDAD", unique = true, nullable = false, precision = 38)
	private long idEscolaridad;

	@Column(name="GRADO_OBTENIDO")
	private String gradoObtenido;

	@Column(name="NOMBRE_ASIGNATURA")
	private String nombreAsignatura;

	@Column(name="NOMNBRE_CARRERA")
	private String nomnbreCarrera;

	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ESTUDIANTE", insertable = false, updatable = false)
    private Estudiante estudiante;
	
	public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

	public Escolaridad() {
	}

	public long getIdEscolaridad() {
		return this.idEscolaridad;
	}

	public void setIdEscolaridad(long idEscolaridad) {
		this.idEscolaridad = idEscolaridad;
	}

	public String getGradoObtenido() {
		return this.gradoObtenido;
	}

	public void setGradoObtenido(String gradoObtenido) {
		this.gradoObtenido = gradoObtenido;
	}

	
	public String getNombreAsignatura() {
		return this.nombreAsignatura;
	}

	public void setNombreAsignatura(String nombreAsignatura) {
		this.nombreAsignatura = nombreAsignatura;
	}

	public String getNomnbreCarrera() {
		return this.nomnbreCarrera;
	}

	public void setNomnbreCarrera(String nomnbreCarrera) {
		this.nomnbreCarrera = nomnbreCarrera;
	}

}