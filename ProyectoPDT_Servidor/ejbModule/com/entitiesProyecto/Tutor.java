package com.entitiesProyecto;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="TUTORES")
@NamedQuery(name="Tutor.findAll", query="SELECT t FROM Tutor t")
public class Tutor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="seq_tutores", sequenceName = "seq_tutores", allocationSize = 1, initialValue = 1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_tutores")
	@Column(name="ID_TUTOR", unique=true, nullable=false, precision=38)
	private long idTutor;

	@Enumerated(EnumType.STRING)
	private Area area;

	@Enumerated(EnumType.STRING)
	private RolTutor tipo;

	//bi-directional many-to-one association to Evento
	@OneToMany(mappedBy="tutore")
	private List<Evento> eventos;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="ID_USUARIO", nullable=false)
	private Usuario usuario;

	public Tutor() {
	}

	public long getIdTutor() {
		return this.idTutor;
	}

	public void setIdTutor(long idTutor) {
		this.idTutor = idTutor;
	}

	public Area getArea() {
		return this.area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public RolTutor getTipo() {
		return this.tipo;
	}

	public void setTipo(RolTutor rolTutor) {
		this.tipo = rolTutor;
	}

	public List<Evento> getEventos() {
		return this.eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}


	public Tutor(Area area, RolTutor tipo, List<Evento> eventos, Usuario usuario) {
		super();
		this.area = area;
		this.tipo = tipo;
		this.eventos = eventos;
		this.usuario = usuario;
	}

	public Tutor(Area area, RolTutor tipo, Usuario usuario) {
		super();
		this.area = area;
		this.tipo = tipo;
		this.usuario = usuario;
	}

	public Evento addEvento(Evento evento) {
		getEventos().add(evento);
		evento.setTutore(this);

		return evento;
	}

	public Evento removeEvento(Evento evento) {
		getEventos().remove(evento);
		evento.setTutore(null);

		return evento;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Tutor [idTutor=" + idTutor + ", area=" + area + ", tipo=" + tipo + ", eventos=" + eventos + ", usuario="
				+ usuario + "]";
	}

}