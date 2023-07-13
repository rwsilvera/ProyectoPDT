package com.Analista;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.entitiesProyecto.EstaReclamo;
import com.entitiesProyecto.Estado;
import com.entitiesProyecto.Reclamo;
import com.entitiesProyecto.Usuario;
import com.entitiesProyecto.VisibilidadR;
import com.exceptionProyecto.ServiciosException;
import com.serviciosProyecto.AccionesBeanRemote;
import com.serviciosProyecto.EstaReclamoBeanRemote;
import com.serviciosProyecto.EstadoBeanRemote;
import com.serviciosProyecto.ReclamosBeanRemote;
import com.serviciosProyecto.UsuariosBeanRemote;

public class VentanaAnalista_FichaReclamo extends JPanel {
	private Reclamo reclamo; 
	private Usuario usuario;
	private UsuariosBeanRemote usuariosBean;


	//-----------------------METODO PARA CAMBIAR ENTRE VENTANAS----------------------------------//
	public void cambiarContenido(JPanel panelDestino, JPanel panelFuente) {
		panelDestino.removeAll();
		panelDestino.add(panelFuente, BorderLayout.CENTER);
		panelDestino.revalidate();
		panelDestino.repaint();
	}

	public VentanaAnalista_FichaReclamo(UsuariosBeanRemote usuariosBean, Reclamo reclamo, Usuario usuario) {
		this.usuariosBean = usuariosBean;
		this.reclamo = reclamo; 
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
				
		try {
			ReclamosBeanRemote reclamosBean = (ReclamosBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_Servidor/ReclamosBean!com.serviciosProyecto.ReclamosBeanRemote");

			EstadoBeanRemote estadoBean = (EstadoBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_Servidor/EstadoBean!com.serviciosProyecto.EstadoBeanRemote");

			EstaReclamoBeanRemote estaReclamoBean = (EstaReclamoBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_Servidor/EstaReclamoBean!com.serviciosProyecto.EstaReclamoBeanRemote");

			AccionesBeanRemote accionesBean = (AccionesBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_Servidor/AccionesBean!com.serviciosProyecto.AccionesBeanRemote");



			//---------------------------TITULO------------------------------//
			JLabel lblNewLabel = new JLabel("Reclamo");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(314, 11, 109, 13);
			panelForm.add(lblNewLabel);
			
			//---------------------------DATOS PARA EL RECLAMO-----------------------------//

			JLabel lblNombreEstudiante = new JLabel("Nombre: ");
			lblNombreEstudiante.setBounds(62, 51, 64, 13);
			panelForm.add(lblNombreEstudiante);

			JLabel lblNombreEstudianteGet = new JLabel("--");
			lblNombreEstudianteGet.setBounds(111, 51, 153, 13);
			lblNombreEstudianteGet.setText(reclamo.getEstudiante().getUsuario().getNombres());
			panelForm.add(lblNombreEstudianteGet);

			JLabel lblDocumento = new JLabel("Documento: ");
			lblDocumento.setBounds(62, 84, 89, 13);
			panelForm.add(lblDocumento);

			JLabel lblDocumentoGet = new JLabel("--");
			lblDocumentoGet.setBounds(136, 84, 128, 13);
			long documento = reclamo.getEstudiante().getUsuario().getDocumento();
			String documentoString = String.valueOf(documento);
			lblDocumentoGet.setText(documentoString);
			panelForm.add(lblDocumentoGet);

			JLabel lblAsuntoGet = new JLabel("--");
			lblAsuntoGet.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblAsuntoGet.setBounds(306, 35, 205, 13);
			lblAsuntoGet.setText(reclamo.getAsunto()); 
			panelForm.add(lblAsuntoGet);

			JLabel lblDetalle = new JLabel("Detalle: ");
			lblDetalle.setBounds(62, 108, 64, 13);
			panelForm.add(lblDetalle);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(60, 132, 638, 215);
			panelForm.add(scrollPane);

			JTextArea txtAreaDetalleGet = new JTextArea();
			scrollPane.setViewportView(txtAreaDetalleGet);
			txtAreaDetalleGet.setText(reclamo.getDetalle());
			txtAreaDetalleGet.setEditable(false); 

			
			JLabel lblEstado = new JLabel("Estado");
			lblEstado.setBounds(62, 402, 64, 13);
			panelForm.add(lblEstado);
			
			//------------------------------COMBOBOX ESTADO-----------------------------------//
			
			JComboBox comboEstado = new JComboBox();
			comboEstado.setBounds(115, 398, 101, 21);
			panelForm.add(comboEstado);
			try {
				List<Estado> estados = estadoBean.obtenerTodosEstado();
				
				for (Estado e : estados) {
					if (e.getVisibilidadR().equals(VisibilidadR.ACTIVO)) { 
						comboEstado.addItem(e.getNombre());
					}
				}	
			}catch(Exception e) {
				System.out.println(e.getMessage());

			}

			List<EstaReclamo> estaReclamos = estaReclamoBean.obtenerEstaRecTodos(); 
			for(EstaReclamo eR: estaReclamos) {
				if(eR.getReclamo().getIdReclamo() == reclamo.getIdReclamo()) {
					comboEstado.setSelectedItem(eR.getEstado().getNombre()); 
				}
			}
			
			//---------------------------ACCION SOBRE EL RECLAMO------------------------------//

			JButton btnRegistrarAccion = new JButton("Acciones");
			btnRegistrarAccion.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					VentanaAnalista_RegistrarAccionReclamo accion = new VentanaAnalista_RegistrarAccionReclamo(reclamo, VentanaAnalista.getUsuario());

					accion .setBounds(-40, 0, 769, 498);

					cambiarContenido(panelForm, accion );

					accion .setVisible(true);

				}
			});
			btnRegistrarAccion.setBounds(579, 91, 119, 23);
			panelForm.add(btnRegistrarAccion);

			//------------------------------MODIFICAR EL ESTADO-----------------------------------//


			JButton btnModificar = new JButton("Modificar");
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//obtenemos string del combo
					String nuevoEstado = comboEstado.getSelectedItem().toString();
					 System.out.println("seleccionado " + nuevoEstado);
					 
					//obtenemos el estado con el bean
					Estado estado = estadoBean.obtenerEstadoPorNombre(nuevoEstado);
					 System.out.println("OBTERER POR NOMBRE " + estado.getNombre());

					//actualizamos el estado
					List<EstaReclamo> estaReclamos = estaReclamoBean.obtenerEstaRecTodos(); 
					 System.out.println("LISTADO " + estaReclamos.get(0));

					// Configuración de las propiedades del servidor de correo (Gmail)
				    	Properties properties = new Properties();
				    	properties.put("mail.smtp.host", "smtp.gmail.com");
				    	properties.put("mail.smtp.port", "587");
				    	properties.put("mail.smtp.auth", "true");
				    	properties.put("mail.smtp.starttls.enable", "true");


				        // Autenticación del remitente
				        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
				            protected PasswordAuthentication getPasswordAuthentication() {
				                return new PasswordAuthentication("secretaria.lti.minas@gmail.com", "jjfufzudkxhesykn");
				            }
				        });
					 
					for(EstaReclamo eR: estaReclamos) {
						if(eR.getReclamo().getIdReclamo() == reclamo.getIdReclamo()) {
							eR.setEstado(estado); 
							 System.out.println("FOR " + eR.getEstado());

							//guardar los cambios en la tabla 
							try {
								estaReclamoBean.actualizarEstaRec(eR);
								// Mostrar mensaje de estado modificado
								JOptionPane.showMessageDialog(null, "Estado modificado. Se envió un mail al estudiante.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);

								 try {
							            // Creación del mensaje de correo
							            Message message = new MimeMessage(session);
							            message.setFrom(new InternetAddress("secretaria.lti.minas@gmail.com"));
							            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(reclamo.getEstudiante().getUsuario().getMaiInstitucional()));
							            message.setSubject("Estado del reclamo");
							            message.setText("¡Hola " +
							            		reclamo.getEstudiante().getUsuario().getNombres() + 
							            		", espero que te encuentres bien!\n\nTe informo que tu reclamo con el ID " + 
							            		reclamo.getIdReclamo()+ " está " + nuevoEstado.toLowerCase() + "." + "\n\nMuchas gracias, saludos.\n\n" + 
							            		usuario.getNombres() + ".");

							            // Envío del mensaje
							            Transport.send(message);

							            System.out.println("Correo electrónico enviado exitosamente.");
							        } catch (MessagingException e2) {
							            e2.printStackTrace();
							        }
								
							} catch (ServiciosException e1) {
								System.out.println("No se pudo actualizar el estado " + e1.getMessage());
							}
						}
					}

				}
			});
			
			btnModificar.setBounds(226, 398, 119, 21);
			panelForm.add(btnModificar);

			// -------------------------------------VOLVER--------------------------------------//

			JButton btnVolver = new JButton("Volver");
			btnVolver.setBounds(599, 415, 119, 23);
			panelForm.add(btnVolver);
			btnVolver.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					VentanaAnalista_ListadoDeReclamos volver = new VentanaAnalista_ListadoDeReclamos(usuariosBean, usuario);

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