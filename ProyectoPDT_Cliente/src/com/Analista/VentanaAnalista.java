package com.Analista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import com.Interfaz.Login;
import com.entitiesProyecto.Usuario;
import com.serviciosProyecto.UsuariosBeanRemote;


public class VentanaAnalista extends JPanel {

	
	private UsuariosBeanRemote usuariosBean;
	public static Usuario usuario;
	
	public void cambiarContenido(JPanel panelDestino, JPanel panelFuente) {
		panelDestino.removeAll();
		panelDestino.add(panelFuente, BorderLayout.CENTER);
		panelDestino.revalidate();
		panelDestino.repaint();
	}
	
	public VentanaAnalista(UsuariosBeanRemote usuariosBean, Usuario usuario) {
		
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
		
		//-------------------------------- LISTADO DE USUARIOS --------------------------------------------//

		JButton btnListadoDeUsuarios = new JButton("Listado de Usuarios");
		btnListadoDeUsuarios.setBounds(310, 134, 157, 23);
		panelForm.add(btnListadoDeUsuarios);

		btnListadoDeUsuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				VentanaAnalista_ListadoDeUsuarios analistaListado = new VentanaAnalista_ListadoDeUsuarios(usuariosBean, usuario);
				analistaListado.setBounds(-40, 0, 769, 498);

				cambiarContenido(panelForm, analistaListado);

				analistaListado.setVisible(true);
			}
		});

		//-------------------------------EDITAR DATOS PERSONALES --------------------------------------------//

		JButton btnEditarDatosPersonales = new JButton("Editar datos Personales");
		btnEditarDatosPersonales.setBounds(27, 49, 174, 23);
		panelForm.add(btnEditarDatosPersonales);
		btnEditarDatosPersonales.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				VentanaAnalista_EditarDatosPersonales analistaDatos = new VentanaAnalista_EditarDatosPersonales(usuario);
				
				analistaDatos.setBounds(-40, 0, 769, 498);

				cambiarContenido(panelForm, analistaDatos);

				analistaDatos.setVisible(true);
			}

		});

		
		//--------------------------------MANTENIMIENTO DE LISTAS AUXILIARES --------------------------------------------//

		JLabel lblListasAuxiliares = new JLabel("Listas Auxiliares");
		lblListasAuxiliares.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblListasAuxiliares.setBounds(342, 225, 229, 34);
		panelForm.add(lblListasAuxiliares);		
		
		
		//--------------------------------LISTA ESTADOS DE RECLAMOS ------------------------------------------//


		JButton btnMantenimientoListaAuxilarEstados = new JButton("Mantenimiento de lista auxiliar de Estados");
		btnMantenimientoListaAuxilarEstados.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				VentanaAnalista_LisadoAuxiliarEstados listaAuxiliarEstados = new VentanaAnalista_LisadoAuxiliarEstados(usuariosBean, usuario);

				//
				listaAuxiliarEstados.setBounds(-40, 0, 769, 498);

				cambiarContenido(panelForm, listaAuxiliarEstados);
				// Hacer visible la ventana
				listaAuxiliarEstados.setVisible(true);

			}
		});

		btnMantenimientoListaAuxilarEstados.setBounds(244, 266, 295, 21);
		panelForm.add(btnMantenimientoListaAuxilarEstados);

//--------------------------------LISTA ITRS ------------------------------------------//

		JButton btnMantenimientoListaAuxilarITRs = new JButton("Mantenimiento de lista auxiliar de ITRs");
		btnMantenimientoListaAuxilarITRs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				VentanaAnalista_ListadoAuxiliarITRs listaAuxiliar = new VentanaAnalista_ListadoAuxiliarITRs(usuariosBean, usuario);

				//
				listaAuxiliar.setBounds(-40, 0, 769, 498);

				cambiarContenido(panelForm, listaAuxiliar);
				listaAuxiliar.setVisible(true);

			}

		});

		btnMantenimientoListaAuxilarITRs.setBounds(244, 298, 295, 21);
		panelForm.add(btnMantenimientoListaAuxilarITRs);
		
//------------------------------------LISTADO DE RECLAMOS------------------------------------------//
		
		JButton btnReclamos = new JButton("Listado de Reclamos");
		btnReclamos.setBounds(310, 168, 157, 23);
		panelForm.add(btnReclamos);

		btnReclamos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				VentanaAnalista_ListadoDeReclamos listadoReclamos = new VentanaAnalista_ListadoDeReclamos(usuariosBean, usuario);

				listadoReclamos.setBounds(-40, 0, 769, 498);

				cambiarContenido(panelForm, listadoReclamos);

				listadoReclamos.setVisible(true);
			}
		});
		
		
//--------------------------------------CERRAR SESION ----------------------------------//
	
		JButton btnCerrarSesion = new JButton("Cerrar sesión");
		btnCerrarSesion.setBounds(599, 415, 119, 23);
		panelForm.add(btnCerrarSesion);
		btnCerrarSesion.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        // Cierra la ventana actual.
		        SwingUtilities.getWindowAncestor(btnCerrarSesion).dispose();

		        // Elimina la referencia al usuario actual.
		        VentanaAnalista.setUsuario(null);

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
		VentanaAnalista.usuario = usuario;
	}
}
