package com.Tutor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import com.Analista.VentanaAnalista;
import com.Analista.VentanaAnalista_ListadoDeUsuarios;
import com.Interfaz.Login;
import com.entitiesProyecto.Estudiante;
import com.entitiesProyecto.Tutor;
import com.entitiesProyecto.Usuario;
import com.serviciosProyecto.EstudianteBeanRemote;
import com.serviciosProyecto.TutoresBeanRemote;
import com.serviciosProyecto.UsuarioEstadoBeanRemote;
import com.serviciosProyecto.UsuariosBeanRemote;

public class VentanaTutor extends JPanel {

	private JFrame frame;
	private UsuariosBeanRemote usuariosBean;
	private UsuarioEstadoBeanRemote usuariosEstadoBean;
	public static Usuario usuario;
	private Tutor tutor;
	
	public void cambiarContenido(JPanel panelDestino, JPanel panelFuente) {
		panelDestino.removeAll();
		panelDestino.add(panelFuente, BorderLayout.CENTER);
		panelDestino.revalidate();
		panelDestino.repaint();
	}

	  public VentanaTutor(UsuariosBeanRemote usuariosBean, Usuario usuario) {
	        this.usuariosBean = usuariosBean;
	        this.usuario = usuario;

	        setLayout(null);

			JPanel panelMain = new JPanel();
			panelMain.setBackground(SystemColor.controlHighlight);
			panelMain.setBounds(10, 0, 779, 451);
			add(panelMain);
			panelMain.setLayout(null);

			JPanel panelForm = new JPanel();
			panelForm.setBorder(new LineBorder(new Color(0, 0, 0), 0));
			panelForm.setBackground(SystemColor.controlHighlight);
			panelForm.setBounds(10, 0, 748, 474);
			panelMain.add(panelForm);
			panelForm.setLayout(null);


			JLabel lblNewLabel = new JLabel(usuario.getNombres() + " " + usuario.getApellidos());
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel.setBounds(27, 11, 302, 34);
			panelForm.add(lblNewLabel);
		
		
		//-------------------------TRAER EL TUTOR--------------------------------//
		try {
			TutoresBeanRemote tutoresBean = (TutoresBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_Servidor/TutoresBean!com.serviciosProyecto.TutoresBeanRemote");
	
		
		List<Tutor> listaTutores = tutoresBean.obtenerTodosLosTutores();
				
		tutor = null;
		
		for (Tutor tutorT : listaTutores) {
			
			if (tutorT.getUsuario().getIdUsuario() == usuario.getIdUsuario() ) {
				
				tutor = tutorT;
				
				break;
			}
		}
		
		} catch (NamingException e) {
			System.out.println("no funciona");
			e.printStackTrace();
		}
	
		//-------------------------------- LISTADO DE USUARIOS ---------------------------------------------//

				JButton btnListadoDeUsuarios = new JButton("Listado de Usuarios");
				btnListadoDeUsuarios.setBounds(310, 134, 157, 23);
				panelForm.add(btnListadoDeUsuarios);

				btnListadoDeUsuarios.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						VentanaTutor_ListadoDeUsuarios tListado = new VentanaTutor_ListadoDeUsuarios(usuariosBean, usuario);
						tListado.setBounds(-40, 0, 769, 498);

						cambiarContenido(panelForm, tListado);

						tListado.setVisible(true);
					}
				});
		
		//-------------------------------EDITAR DATOS PERSONALES --------------------------------------------//

		JButton btnEditarDatosPersonales = new JButton("Editar datos Personales");
		btnEditarDatosPersonales.setBounds(27, 49, 174, 23);
		panelForm.add(btnEditarDatosPersonales);
		btnEditarDatosPersonales.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				VentanaTutor_EditarDatosPersonales tutorDatos = new VentanaTutor_EditarDatosPersonales(usuario, tutor);
				
				tutorDatos.setBounds(-40, 0, 769, 498);

				cambiarContenido(panelForm, tutorDatos);

				tutorDatos.setVisible(true);
			}

		});

		//----------------------------------------CERRAR SESION -------------------------------------//

		JButton btnCerrarSesion = new JButton("Cerrar sesión");
		btnCerrarSesion.setBounds(599, 415, 119, 23);
		panelForm.add(btnCerrarSesion);
		btnCerrarSesion.addMouseListener(new MouseAdapter() {
			@Override
		    public void mouseClicked(MouseEvent e) {
		        // Cierra la ventana actual.
		        SwingUtilities.getWindowAncestor(btnCerrarSesion).dispose();

		        // Elimina la referencia al usuario actual.
		        VentanaTutor.setUsuario(null);

		        // Inicia una nueva sesión.
		        Login login = new Login(usuariosBean);
		        login.mostrarVentana();
				
			}
		});

	}
	//--------------------------------Usuario getters y setters-------------------------//
		public static Usuario getUsuario() {
			return usuario;
		}

		public static void setUsuario(Usuario usuario) {
			VentanaTutor.usuario = usuario;
		}
	}