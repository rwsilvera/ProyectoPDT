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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "USUARIOS")
@NamedQuery(name = "Usuario.obtenerTodosLosUsuarios", query = "SELECT u FROM Usuario u")
@NamedQuery(name = "Usuario.validarUsuario", query = "SELECT COUNT(u) FROM Usuario u WHERE u.mail = :correo AND u.contrasenia = :contrasenia")
@NamedQuery(name = "Usuario.obtenerUsuarioDesdeBaseDeDatos", query = "SELECT u FROM Usuario u WHERE u.mail = :correo")
@NamedQuery(name = "Usuario.ObtenerMailUsuario", query = "SELECT u FROM Usuario u WHERE u.mail = :correo")
@NamedQuery(name = "Usuario.obtenerUsuarioDesdeBaseDeDatosNombre", query = "SELECT u FROM Usuario u WHERE u.nomUsuario = :nomUsuario")
@NamedQuery(name = "Usuario.validarNombreUsuario", query = "SELECT COUNT(u) FROM Usuario u WHERE u.nomUsuario = :nomUsuario AND u.contrasenia = :contrasenia")
@NamedQuery(name = "Usuario.ObtenerNombreUsuario", query = "SELECT u FROM Usuario u WHERE u.nomUsuario = :nomUsuario")
@NamedQuery(name = "Usuario.obtenerPorEstado", query = "SELECT u FROM Usuario u WHERE u.usuaEstado = :estado")
@NamedQuery(name = "Usuario.obtenerUsuariosPorTipo", query = "SELECT u FROM Usuario u WHERE u.tipo = :tipo")
@NamedQuery(name = "Usuario.obtenerUsuariosItr", query = "SELECT u FROM Usuario u WHERE u.itr = :itr")
@NamedQuery(name = "Usuario.obtenerUsuariosPorTipoEstado", query = "SELECT u FROM Usuario u WHERE u.tipo = :tipo AND u.usuaEstado = :estado")

public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_usuario")
	@Column(name = "ID_USUARIO", unique = true, nullable = false, precision = 38)
	private Long idUsuario;

	
	@Column(nullable = false, precision = 38)
	private Long documento;

	@Column(nullable = false, length = 20)
	private String apellidos;

	@Column(nullable = false, length = 30)
	private String contrasenia;

	@Temporal(TemporalType.DATE)
	@Column(name = "FEC_NACIMIENTO", nullable = false)
	private Date fecNacimiento;

	@Enumerated(EnumType.STRING)
	private Genero genero;

	@Column(name = "MAI_INSTITUCIONAL", nullable = false, length = 100)
	private String maiInstitucional;

	@Column(nullable = false, length = 100)
	private String mail;

	@Column(name = "NOM_USUARIO", nullable = false, length = 20)
	private String nomUsuario;

	@Column(nullable = false, length = 20)
	private String nombres;

	@Column(nullable = false, length = 20)
	private String telefono;

	@Enumerated(EnumType.STRING)
	private TipoUsuario tipo;

	// bi-directional many-to-one association to Analista
	@OneToMany(mappedBy = "usuario")
	private List<Analista> analistas;

	// bi-directional many-to-one association to Estudiante
	@OneToMany(mappedBy = "usuario")
	private List<Estudiante> estudiantes;

	// bi-directional many-to-one association to Tutor
	@OneToMany(mappedBy = "usuario")
	private List<Tutor> tutores;

	// bi-directional many-to-one association to Departamento
	@ManyToOne
	@JoinColumn(name = "ID_DEPARTAMENTO", nullable = false)
	private Departamento departamento;

	// bi-directional many-to-one association to Itr
	@ManyToOne
	@JoinColumn(name = "ID_ITR", nullable = false)
	private Itr itr;

	// bi-directional many-to-one association to Localidad
	@ManyToOne
	@JoinColumn(name = "ID_LOCALIDAD", nullable = false)
	private Localidad localidade;

	// bi-directional many-to-one association to UsuarioEstado
	@ManyToOne
	@JoinColumn(name = "ID_ESTA_USUARIO", nullable = false)
	private UsuarioEstado usuaEstado;

	public Usuario() {
	}

	public Usuario(Long documento, String apellidos, String contrasenia, Date fecNacimiento, Genero genero,
			String maiInstitucional, String mail, String nomUsuario, String nombres, String telefono, TipoUsuario tipo,
			Departamento departamento, Itr itr, Localidad localidade, UsuarioEstado usuaEstado) {
		super();
		this.documento = documento;
		this.apellidos = apellidos;
		this.contrasenia = contrasenia;
		this.fecNacimiento = fecNacimiento;
		this.genero = genero;
		this.maiInstitucional = maiInstitucional;
		this.mail = mail;
		this.nomUsuario = nomUsuario;
		this.nombres = nombres;
		this.telefono = telefono;
		this.tipo = tipo;
		this.departamento = departamento;
		this.itr = itr;
		this.localidade = localidade;
		this.usuaEstado = usuaEstado;
	}
	
	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public long getDocumento() {
		return this.documento;
	}

	public void setDocumento(Long documento) {
		this.documento = documento;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getContrasenia() {
		return this.contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public Date getFecNacimiento() {
		return this.fecNacimiento;
	}

	public void setFecNacimiento(Date fecNacimiento) {
		this.fecNacimiento = fecNacimiento;
	}

	public String getMaiInstitucional() {
		return this.maiInstitucional;
	}

	public void setMaiInstitucional(String maiInstitucional) {
		this.maiInstitucional = maiInstitucional;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNomUsuario() {
		return this.nomUsuario;
	}

	public void setNomUsuario(String nomUsuario) {
		this.nomUsuario = nomUsuario;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Analista> getAnalistas() {
		return this.analistas;
	}

	public void setAnalistas(List<Analista> analistas) {
		this.analistas = analistas;
	}

	public Analista addAnalista(Analista analista) {
		getAnalistas().add(analista);
		analista.setUsuario(this);

		return analista;
	}

	public Analista removeAnalista(Analista analista) {
		getAnalistas().remove(analista);
		analista.setUsuario(null);

		return analista;
	}

	public List<Estudiante> getEstudiantes() {
		return this.estudiantes;
	}

	public void setEstudiantes(List<Estudiante> estudiantes) {
		this.estudiantes = estudiantes;
	}

	public List<Tutor> getTutores() {
		return this.tutores;
	}

	public void setTutores(List<Tutor> tutores) {
		this.tutores = tutores;
	}

	public Tutor addTutore(Tutor tutore) {
		getTutores().add(tutore);
		tutore.setUsuario(this);

		return tutore;
	}

	public Tutor removeTutore(Tutor tutore) {
		getTutores().remove(tutore);
		tutore.setUsuario(null);

		return tutore;
	}

	public Departamento getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Itr getItr() {
		return this.itr;
	}

	public void setItr(Itr itr) {
		this.itr = itr;
	}

	public Localidad getLocalidade() {
		return this.localidade;
	}

	public void setLocalidade(Localidad localidade) {
		this.localidade = localidade;
	}

	public UsuarioEstado getUsuaEstado() {
		return this.usuaEstado;
	}

	public void setUsuaEstado(UsuarioEstado usuaEstado) {
		this.usuaEstado = usuaEstado;
	}

	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Override
	public String toString() {
		return "Usuario [documento=" + documento + ", apellidos=" + apellidos + ", contrasenia=" + contrasenia
				+ ", fecNacimiento=" + fecNacimiento + ", genero=" + genero + ", maiInstitucional=" + maiInstitucional
				+ ", mail=" + mail + ", nomUsuario=" + nomUsuario + ", nombres=" + nombres + ", telefono=" + telefono
				+ ", tipo=" + tipo + ", analistas=" + analistas + ", estudiantes="
				+ estudiantes + ", tutores=" + tutores + ", departamento=" + departamento + ", itr=" + itr
				+ ", localidade=" + localidade + ", usuaEstado=" + usuaEstado + "]";
	}


}