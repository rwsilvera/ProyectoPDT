package com.Estudiante;

import java.awt.BorderLayout;
import java.awt.Color;
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
import com.Interfaz.Login;
import com.entitiesProyecto.Estudiante;
import com.entitiesProyecto.Usuario;
import com.serviciosProyecto.EstudianteBeanRemote;
import com.serviciosProyecto.UsuarioEstadoBeanRemote;
import com.serviciosProyecto.UsuariosBeanRemote;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaEstudiante extends JPanel {

	private JFrame frame;
	private UsuariosBeanRemote usuariosBean;
	private UsuarioEstadoBeanRemote usuariosEstadoBean;

	public static Estudiante estudiante;
	public static Usuario usuario;
	
	public void cambiarContenido(JPanel panelDestino, JPanel panelFuente) {
		panelDestino.removeAll();
		panelDestino.add(panelFuente, BorderLayout.CENTER);
		panelDestino.revalidate();
		panelDestino.repaint();
	}

	public VentanaEstudiante(UsuariosBeanRemote usuariosBean, Usuario usuario) {
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
		
		//-------------------------TRAER EL ESTUDIANTE--------------------------------//
			EstudianteBeanRemote estudianteBean;
			try {
				estudianteBean = (EstudianteBeanRemote) InitialContext
						.doLookup("ejb:/ProyectoPDT_Servidor/EstudianteBean!com.serviciosProyecto.EstudianteBeanRemote");
			
			
			List<Estudiante> listaEstudiantes = estudianteBean.obtenerEstudianteTodos();
			
			estudiante = null;
			
			for (Estudiante estudianteE : listaEstudiantes) {
				
				if (estudianteE.getUsuario().getIdUsuario() == usuario.getIdUsuario() ) {
					
					estudiante = estudianteE;
					
					break;
				}
			}
			
			} catch (NamingException e) {
				System.out.println("no funciona");
				e.printStackTrace();
			}
		

		// -------------------------------EDITAR DATOS PERSONALES --------------------------------------------//

		JButton btnEditarDatosPersonales = new JButton("Editar datos Personales");
		btnEditarDatosPersonales.setBounds(27, 49, 174, 23);
		panelForm.add(btnEditarDatosPersonales);
		btnEditarDatosPersonales.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				VentanaEstudiante_EditarDatosPersonales estudianteDatos = new VentanaEstudiante_EditarDatosPersonales(
						usuario, estudiante);

				estudianteDatos.setBounds(-40, 0, 769, 498);

				cambiarContenido(panelForm, estudianteDatos);

				estudianteDatos.setVisible(true);
			}

		});

		// ---------------------------------------LISTADO DE RECLAMOS-----------------------------------------//

		JLabel lblNewLabel_1 = new JLabel("Reclamos");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(365, 131, 79, 14);
		panelForm.add(lblNewLabel_1);
		
		JButton btnReclamos = new JButton("Listado de Reclamos");
		btnReclamos.setBounds(311, 162, 174, 23);
		panelForm.add(btnReclamos);
		btnReclamos.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {

			VentanaEstudiante_ListadoDeReclamos listadoReclamos = new VentanaEstudiante_ListadoDeReclamos(usuariosBean, usuario);

			listadoReclamos.setBounds(-40, 0, 769, 498);
			cambiarContenido(panelForm, listadoReclamos);

			listadoReclamos.setVisible(true);
		}
	});

		// -----------------------------------ALTA DE RECLAMOS-----------------------------------------//

		JButton btnAltaReclamo = new JButton("Nuevo Reclamo");
		btnAltaReclamo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				VentanaEstudiante_Reportes_NuevoReclamo reclamo = new VentanaEstudiante_Reportes_NuevoReclamo(usuariosBean, usuario);

				reclamo.setBounds(-40, 0, 769, 498);
				cambiarContenido(panelForm, reclamo);

				reclamo.setVisible(true);
			}
		});

		btnAltaReclamo.setBounds(311, 198, 174, 23);
		panelForm.add(btnAltaReclamo);

		// -------------------------------------REPORTES-ESCOLARIDAD------------------------------------//
		
		JLabel lblNewLabel_1_1 = new JLabel("Escolaridad");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1.setBounds(365, 275, 79, 14);
		panelForm.add(lblNewLabel_1_1);
		
		JButton btnNewButton = new JButton("Reportes");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				VentanaEstudiante_Reportes reporte = new VentanaEstudiante_Reportes(usuariosBean, usuario);

				reporte.setBounds(-40, 0, 769, 498);
				cambiarContenido(panelForm, reporte);

				reporte.setVisible(true);
			}
		});
		btnNewButton.setBounds(311, 300, 174, 23);
		panelForm.add(btnNewButton);
		
		// ----------------------------------------CERRAR SESION -------------------------------------//

		JButton btnCerrarSesion = new JButton("Cerrar sesión");
		btnCerrarSesion.setBounds(599, 415, 119, 23);
		panelForm.add(btnCerrarSesion);
		btnCerrarSesion.addMouseListener(new MouseAdapter() {
			@Override
		    public void mouseClicked(MouseEvent e) {
		        // Cierra la ventana actual.
		        SwingUtilities.getWindowAncestor(btnCerrarSesion).dispose();

		        // Elimina la referencia al usuario actual.
		        VentanaEstudiante.setUsuario(null);

		        // Inicia una nueva sesión.
		        Login login = new Login(usuariosBean);
		        login.mostrarVentana();
				
			}
		});
		
		
	}
	public static Estudiante getEstudiante() {
		return estudiante;
	}

	public static void setEstudiante(Estudiante estudiante) {
		VentanaEstudiante.estudiante = estudiante;
	}

	public static Usuario getUsuario() {
		return usuario;
	}

	public static void setUsuario(Usuario usuario) {
		VentanaEstudiante.usuario = usuario;
	}

}
