package com.Estudiante;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.entitiesProyecto.EstaReclamo;
import com.entitiesProyecto.Reclamo;
import com.entitiesProyecto.Usuario;
import com.itextpdf.text.Font;
import com.serviciosProyecto.AccionesBeanRemote;
import com.serviciosProyecto.EstaReclamoBeanRemote;
import com.serviciosProyecto.EstadoBeanRemote;
import com.serviciosProyecto.ReclamosBeanRemote;
import com.serviciosProyecto.UsuariosBeanRemote;

public class VentanaEstudiante_ListadoDeReclamos_FichaDeReclamo extends JPanel {

	private Reclamo reclamo;
	private UsuariosBeanRemote usuariosBean;
	
	public void cambiarContenido(JPanel panelDestino, JPanel panelFuente) {
		panelDestino.removeAll();
		panelDestino.add(panelFuente, BorderLayout.CENTER);
		panelDestino.revalidate();
		panelDestino.repaint();
	}

	public VentanaEstudiante_ListadoDeReclamos_FichaDeReclamo(UsuariosBeanRemote usuariosBean, Reclamo reclamo) {
		this.usuariosBean = usuariosBean;
		this.reclamo = reclamo; 

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
		
		
		
		try {
			ReclamosBeanRemote reclamosBean = (ReclamosBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_Servidor/ReclamosBean!com.serviciosProyecto.ReclamosBeanRemote");

			EstadoBeanRemote estadoBean = (EstadoBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_Servidor/EstadoBean!com.serviciosProyecto.EstadoBeanRemote");

			EstaReclamoBeanRemote estaReclamoBean = (EstaReclamoBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_Servidor/EstaReclamoBean!com.serviciosProyecto.EstaReclamoBeanRemote");

			AccionesBeanRemote accionesBean = (AccionesBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_Servidor/AccionesBean!com.serviciosProyecto.AccionesBeanRemote");
			
			JLabel lblActividad = new JLabel("Actividad: ");
			lblActividad.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11));
			lblActividad.setBounds(127, 54, 88, 13);
			panelForm.add(lblActividad);

			
			// ---------------------------TITULO------------------------------//

			JLabel lblReporte = new JLabel("Reclamo");
			lblReporte.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 16));
			lblReporte.setHorizontalAlignment(SwingConstants.CENTER);
			lblReporte.setBounds(269, 11, 226, 20);
			panelForm.add(lblReporte);

			JLabel lblAsuntoGet = new JLabel("--");
			lblAsuntoGet.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 12));
			lblAsuntoGet.setBounds(325, 31, 182, 13);
			//asignar asunto
			lblAsuntoGet.setText(reclamo.getAsunto()); 
			panelForm.add(lblAsuntoGet);

			//------------------NOMBRE DE LA ACTIVIDAD--------------------//
			
			JLabel lbltipoDeActividad = new JLabel("Nombre de la Actividad: ");
			lbltipoDeActividad.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11));
			lbltipoDeActividad.setBounds(127, 74, 148, 13);
			panelForm.add(lbltipoDeActividad);
			
			JLabel lbltipoDeActividadGet = new JLabel("--");
			 lbltipoDeActividadGet.setBounds(248, 74, 182, 13);
			 lbltipoDeActividadGet.setText(reclamo.getNombre());
			panelForm.add( lbltipoDeActividadGet);
			
			//----------------TIPO DE ACTIVIDAD---------------------------//
			
			JLabel lblTipo = new JLabel("Actividad: ");
			lblTipo.setBounds(183, 54, 126, 13);
			lblTipo.setText(reclamo.getTipoActividad().toString());
			panelForm.add(lblTipo);
			
			//--------------------CREDITOS--------------------------------//
			
			JLabel lblCreditos = new JLabel("Créditos: ");
			lblCreditos.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11));
			lblCreditos.setBounds(127, 120, 77, 13);
			panelForm.add(lblCreditos);
			
			JLabel lblCreditosGet = new JLabel((String) null);
			lblCreditosGet.setBounds(183, 120, 70, 13);
			lblCreditosGet.setText(reclamo.getCredito());
			panelForm.add(lblCreditosGet);
			
			//----------------FECHA ACTIVIDAD--------------------------//
			
			JLabel lblFechaActividad = new JLabel("Fecha Actividad: ");
			lblFechaActividad.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11));
			lblFechaActividad.setBounds(127, 146, 118, 13);
			panelForm.add(lblFechaActividad);
			
			JLabel lblFechaActividadGet = new JLabel((String) null);
			lblFechaActividadGet.setBounds(217, 146, 70, 13);
			lblFechaActividadGet.setText(reclamo.getFechaActividad().toString());
			panelForm.add(lblFechaActividadGet);
			
			//----------------FECHA RECLAMO------------------------------//
			
			JLabel lblFechaReclamo = new JLabel("Fecha: ");
			lblFechaReclamo.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11));
			lblFechaReclamo.setBounds(525, 146, 126, 13);
			panelForm.add(lblFechaReclamo);
			
			JLabel lblFechaReclamoGet = new JLabel((String) null);
			lblFechaReclamoGet.setBounds(574, 146, 70, 13);
			lblFechaReclamoGet.setText(reclamo.getFecha().toString());
			panelForm.add(lblFechaReclamoGet);
			
			//-----------------------DOCENTE-----------------------------//
						
			JLabel lblTutore = new JLabel("Docente:");
			lblTutore.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11));
			lblTutore.setBounds(127, 98, 126, 13);
			panelForm.add(lblTutore);
			
			JLabel lblTutor = new JLabel("Docente: ");
			lblTutor.setBounds(183, 98, 191, 13);
			lblTutor.setText(reclamo.getTutore().getUsuario().getNombres() + " " + reclamo.getTutore().getUsuario().getApellidos() );
			panelForm.add(lblTutor);
			
			//----------------------ESTADO DE RECLAMO--------------------//

			JLabel lblDetalle = new JLabel("Detalle: ");
			lblDetalle.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11));
			lblDetalle.setBounds(127, 170, 64, 13);
			panelForm.add(lblDetalle);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(127, 186, 524, 179);
			panelForm.add(scrollPane);

			JTextArea txtAreaDetalleGet = new JTextArea();

			scrollPane.setViewportView(txtAreaDetalleGet);
			txtAreaDetalleGet.setText(reclamo.getDetalle());
			txtAreaDetalleGet.setEditable(false); 

			//----------------------ESTADO DE RECLAMO--------------------//

			JLabel lblEstado = new JLabel("Estado: ");
			lblEstado.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11));
			lblEstado.setBounds(524, 166, 64, 13);
			panelForm.add(lblEstado);

			JLabel lblEstadoReclamo = new JLabel("--");
			lblEstadoReclamo.setFont(new java.awt.Font("Tahoma", java.awt.Font.ITALIC, 11));
			lblEstadoReclamo.setBounds(569, 166, 101, 13);
			panelForm.add(lblEstadoReclamo);

			List<EstaReclamo> estaReclamos = estaReclamoBean.obtenerEstaRecTodos(); 
			for(EstaReclamo eR: estaReclamos) {
				if(eR.getReclamo().getIdReclamo() == reclamo.getIdReclamo()) {
					lblEstadoReclamo.setText(eR.getEstado().getNombre()); 
				}
			}
			
		//---------------------VER ACCIONES SOBRE RECLAMOS----------------------------------//
			
			JButton btnAcciones = new JButton("Acciones");
			btnAcciones.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VentanaEstudiante_ReclamosVerAcciones panelVerAccion = new VentanaEstudiante_ReclamosVerAcciones(usuariosBean, reclamo); 
					
					panelVerAccion.setBounds(-40, 0, 769, 498);
					
					cambiarContenido(panelForm, panelVerAccion);
					panelVerAccion.setVisible(true);
					
				}
			});
			btnAcciones.setBounds(599, 376, 119, 23);
			panelForm.add(btnAcciones);
			
		
		
		// -------------------------------------VOLVER--------------------------------------//

		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(599, 415, 119, 23);
		panelForm.add(btnVolver);
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Usuario usuario = VentanaEstudiante.getUsuario();
				VentanaEstudiante_ListadoDeReclamos volver = new VentanaEstudiante_ListadoDeReclamos(usuariosBean, usuario);

				volver.setBounds(-40, 0, 769, 498);

				cambiarContenido(panelForm, volver);

				volver.setVisible(true);

			}
		});
					



		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}