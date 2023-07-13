package com.entitiesProyecto;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="ANALISTAS")
@NamedQuery(name="Analista.findAll", query="SELECT a FROM Analista a")
public class Analista implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "seq_analista", sequenceName = "seq_analista", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_analista")
	@Column(name="ID_ANALISTA", unique=true, nullable=false, precision=38)
	private long idAnalista;


	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="ID_USUARIO", nullable=false)
	private Usuario usuario;

	//bi-directional many-to-one association to Evento
	@OneToMany(mappedBy="analista")
	private List<Evento> eventos;

	public Analista() {
	}

	public long getIdAnalista() {
		return this.idAnalista;
	}

	public void setIdAnalista(long idAnalista) {
		this.idAnalista = idAnalista;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Evento> getEventos() {
		return this.eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}


	public Analista(Usuario usuario) {
		super();
		this.usuario = usuario;
	}

	public Evento addEvento(Evento evento) {
		getEventos().add(evento);
		evento.setAnalista(this);

		return evento;
	}

	public Evento removeEvento(Evento evento) {
		getEventos().remove(evento);
		evento.setAnalista(null);
		return evento;
	}

	@Override
	public String toString() {
		return "Analista [idAnalista=" + idAnalista + ", usuario=" + usuario + ", eventos=" + eventos + "]";
	}
}