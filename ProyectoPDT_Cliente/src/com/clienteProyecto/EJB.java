package com.clienteProyecto;

import java.util.Date;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import com.entitiesProyecto.Analista;
import com.entitiesProyecto.Estudiante;
import com.entitiesProyecto.Generacion;
import com.entitiesProyecto.Semestre;
import com.entitiesProyecto.TipoUsuario;
import com.entitiesProyecto.Usuario;
import com.entitiesProyecto.UsuarioEstado;
import com.exceptionProyecto.ServiciosException;
import com.serviciosProyecto.AnalistaBeanRemote;
import com.serviciosProyecto.ConstanciaBeanRemote;
import com.serviciosProyecto.ConvAsistenciaBeanRemote;
import com.serviciosProyecto.DepartamentoBeanRemote;
import com.serviciosProyecto.EscolaridadBeanRemote;
import com.serviciosProyecto.EstaConstanciaBeanRemote;
import com.serviciosProyecto.EstaJustificacionBeanRemote;
import com.serviciosProyecto.EstaReclamoBeanRemote;
import com.serviciosProyecto.EstadoBeanRemote;
import com.serviciosProyecto.EstudianteBeanRemote;
import com.serviciosProyecto.EventoBeanRemote;
import com.serviciosProyecto.ITRBeanRemote;
import com.serviciosProyecto.JustificacionesBeanRemote;
import com.serviciosProyecto.LocalidadesBeanRemote;
import com.serviciosProyecto.ReclamosBeanRemote;
import com.serviciosProyecto.TutoresBeanRemote;
import com.serviciosProyecto.UsuarioEstadoBeanRemote;
import com.serviciosProyecto.UsuariosBeanRemote;

public class EJB {

	
	public static void main(String[] args) throws NamingException, ServiciosException {

		try {
			// 1 Analista
			AnalistaBeanRemote analistaBean = (AnalistaBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_Servidor/AnalistaBean!com.serviciosProyecto.AnalistaBeanRemote");
			// 2 Constancia
			ConstanciaBeanRemote constanciaBean = (ConstanciaBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_Servidor/ConstanciaBean!com.serviciosProyecto.ConstanciaBeanRemote");
			// 3 ConvAsistencia
			ConvAsistenciaBeanRemote convAsistenciaBean = (ConvAsistenciaBeanRemote) InitialContext.doLookup(
					"ejb:/ProyectoPDT_Servidor/ConvAsistenciaBean!com.serviciosProyecto.ConvAsistenciaBeanRemote");
			// 4 Departamento
			DepartamentoBeanRemote departamentoBean = (DepartamentoBeanRemote) InitialContext.doLookup(
					"ejb:/ProyectoPDT_Servidor/DepartamentoBean!com.serviciosProyecto.DepartamentoBeanRemote");
			// 5 EstaConstancia
			EstaConstanciaBeanRemote estaConstanciaBean = (EstaConstanciaBeanRemote) InitialContext.doLookup(
					"ejb:/ProyectoPDT_Servidor/EstaConstanciaBean!com.serviciosProyecto.EstaConstanciaBeanRemote");
			// 6 Estado
			EstadoBeanRemote estadoBean = (EstadoBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_Servidor/EstadoBean!com.serviciosProyecto.EstadoBeanRemote");
			// 7 Esta Justificaciones
			EstaJustificacionBeanRemote estajustificacionBean = (EstaJustificacionBeanRemote) InitialContext.doLookup(
					"ejb:/ProyectoPDT_Servidor/EstaJustificacionBean!com.serviciosProyecto.EstaJustificacionBeanRemote");
			// 8 Esta Reclamo
			EstaReclamoBeanRemote estaReclamoBean = (EstaReclamoBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_Servidor/EstaReclamoBean!com.serviciosProyecto.EstaReclamoBeanRemote");
			// 9 Estudiante
			EstudianteBeanRemote estudianteBean = (EstudianteBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_Servidor/EstudianteBean!com.serviciosProyecto.EstudianteBeanRemote");
			// 10 Evento
			EventoBeanRemote eventoBean = (EventoBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_Servidor/EventoBean!com.serviciosProyecto.EventoBeanRemote");
			// 11 ITR
			ITRBeanRemote itrBean = (ITRBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_Servidor/ITRBean!com.serviciosProyecto.ITRBeanRemote");
			// 12 Justificaciones
			JustificacionesBeanRemote justificacionesBean = (JustificacionesBeanRemote) InitialContext.doLookup(
					"ejb:/ProyectoPDT_Servidor/JustificacionesBean!com.serviciosProyecto.JustificacionesBeanRemote");
			// 13 Localidades
			LocalidadesBeanRemote localidadesBean = (LocalidadesBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_Servidor/LocalidadesBean!com.serviciosProyecto.LocalidadesBeanRemote");
			// 14 Reclamos
			ReclamosBeanRemote reclamosBean = (ReclamosBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_Servidor/ReclamosBean!com.serviciosProyecto.ReclamosBeanRemote");
			// 15 Tutores
			TutoresBeanRemote tutoresBean = (TutoresBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_Servidor/TutoresBean!com.serviciosProyecto.TutoresBeanRemote");
			// 16 UsuarioEstado
			UsuarioEstadoBeanRemote usuarioEstadoBean = (UsuarioEstadoBeanRemote) InitialContext.doLookup(
					"ejb:/ProyectoPDT_Servidor/UsuarioEstadoBean!com.serviciosProyecto.UsuarioEstadoBeanRemote");
			// 17 Usuarios
			UsuariosBeanRemote usuariosBean = (UsuariosBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_Servidor/UsuariosBean!com.serviciosProyecto.UsuariosBeanRemote");
			//18 Escolaridades
			EscolaridadBeanRemote escolaridadBean = (EscolaridadBeanRemote) InitialContext.doLookup(
					"ejb:/ProyectoPDT_Servidor/EscolaridadBean!com.serviciosProyecto.EscolaridadBeanRemote");
					



		} catch (NamingException e) {
			System.out.println("Error al obtener la referencia del bean remoto: " + e.getMessage());
		}
	}
}
