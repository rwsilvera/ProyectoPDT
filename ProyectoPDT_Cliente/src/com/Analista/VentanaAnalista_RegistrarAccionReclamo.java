package com.Analista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.entitiesProyecto.Accion;
import com.entitiesProyecto.Analista;
import com.entitiesProyecto.Reclamo;
import com.entitiesProyecto.Usuario;
import com.exceptionProyecto.ServiciosException;
import com.serviciosProyecto.AccionesBeanRemote;
import com.serviciosProyecto.AnalistaBeanRemote;
import com.serviciosProyecto.ReclamosBeanRemote;
import com.serviciosProyecto.UsuariosBeanRemote;

public class VentanaAnalista_RegistrarAccionReclamo extends JPanel {
	
	private JTable table;
	private DefaultTableModel tableModel;
//	private Reclamo reclamo;
//	private Usuario usuario;

	// -----------------------METODO PARA CAMBIAR ENTRE VENTANAS----------------------------------//
	public void cambiarContenido(JPanel panelDestino, JPanel panelFuente) {
		panelDestino.removeAll();
		panelDestino.add(panelFuente, BorderLayout.CENTER);
		panelDestino.revalidate();
		panelDestino.repaint();
	}

	public VentanaAnalista_RegistrarAccionReclamo(Reclamo reclamo, Usuario usuario) {
//		this.reclamo = reclamo;
//		this.usuario = usuario;
		setLayout(null);

		JPanel panelMain = new JPanel();
		panelMain.setBackground(SystemColor.controlHighlight);
		panelMain.setBounds(40, 0, 779, 451);
		add(panelMain);
		panelMain.setLayout(null);

		JPanel panelForm = new JPanel();
		panelForm.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		panelForm.setBackground(SystemColor.controlHighlight);
		panelForm.setBounds(21, 0, 748, 440);
		panelMain.add(panelForm);
		panelForm.setLayout(null);

		try {
			AccionesBeanRemote accionesBean = (AccionesBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_Servidor/AccionesBean!com.serviciosProyecto.AccionesBeanRemote");
			ReclamosBeanRemote reclamosBean = (ReclamosBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_Servidor/ReclamosBean!com.serviciosProyecto.ReclamosBeanRemote");
			AnalistaBeanRemote analistaBean = (AnalistaBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_Servidor/AnalistaBean!com.serviciosProyecto.AnalistaBeanRemote");

			// 17 Usuarios
			UsuariosBeanRemote usuariosBean = (UsuariosBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_Servidor/UsuariosBean!com.serviciosProyecto.UsuariosBeanRemote");


			ArrayList<Analista> analistas = (ArrayList<Analista>) analistaBean.obtenerTodosAnalista();
			
			//--------------------TITULO--------------------------//
			JLabel lblRegistro = new JLabel("Acciones");
			lblRegistro.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblRegistro.setBounds(318, 10, 132, 23);
			panelForm.add(lblRegistro);
			lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);

			// ---------------TABLA DE ACCIONES------------------//

			tableModel = new DefaultTableModel();
			tableModel.addColumn("Fecha");
			tableModel.addColumn("Detalle");
			tableModel.addColumn("Analista");


			// Agrega filas al modelo de tabla
			ArrayList<Accion> acciones = accionesBean.listarAcciones();

			for (Accion accion : acciones) {
				if(accion.getReclamo().getIdReclamo() == reclamo.getIdReclamo()){

					Object[] rowData = { accion.getFechaHora(), accion.getDetalle(),

							accion.getAnalista().getIdAnalista() };
					tableModel.addRow(rowData);
				}
			}

			table = new JTable(tableModel);

			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setBounds(66, 38, 608, 143);

			panelForm.add(scrollPane);

			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(66, 248, 420, 169);
			panelForm.add(scrollPane_1);

			JTextArea textArea = new JTextArea();
			scrollPane_1.setViewportView(textArea);
			

			// -------------------------------------REGISTRAR ACCION--------------------------------------//

			JLabel lblNuevaAccion = new JLabel("Ingresar Acción");
			lblNuevaAccion.setHorizontalAlignment(SwingConstants.LEFT);
			lblNuevaAccion.setBounds(69, 215, 112, 22);
			panelForm.add(lblNuevaAccion);

			JButton btnRegistrar = new JButton("Registrar Acción");
			btnRegistrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String detalle = textArea.getText();
					Accion accion = new Accion();
					accion.setDetalle(detalle);
					accion.setFechaHora(new Date());
					accion.setReclamo(reclamo);
					
					 String detalle1 = textArea.getText();

				        // Validación del tamaño del texto
				        if (detalle1.length() > 100) {
				            JOptionPane.showMessageDialog(null, "El detalle no puede tener más de 100 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
				            return; // Detiene la ejecución del método
				        }

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
					
					for (Analista a : analistas) {
						if (a.getUsuario().getIdUsuario() == usuario.getIdUsuario()) {
							Analista analista = a;
							accion.setAnalista(analista);
						}
					}

					try {
						Accion accionCreada = accionesBean.crearAccion(accion);

						JOptionPane.showMessageDialog(null, "Estado modificado. Se envió un mail al estudiante.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);

						// Actualiza la tabla con la nueva acción registrada
						Object[] rowData = { accionCreada.getFechaHora(), accionCreada.getDetalle(),
								accionCreada.getAnalista().getIdAnalista() };
						tableModel.addRow(rowData);

						// Limpia el contenido del textArea
						textArea.setText("");

						try {
				            // Creación del mensaje de correo
				            Message message = new MimeMessage(session);
				            message.setFrom(new InternetAddress("secretaria.lti.minas@gmail.com"));
				            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(reclamo.getEstudiante().getUsuario().getMaiInstitucional()));
				            message.setSubject("Información del reclamo");
				            message.setText("¡Hola " + 
				            		reclamo.getEstudiante().getUsuario().getNombres() +
				            		", espero que te encuentres bien!\n\nTe informo sobre tu reclamo con el ID " +
				            		reclamo.getIdReclamo() +":" + "\n\n" + detalle1 +
				            		"\n\nCualquier consulta no dudes en comunicarte nuevamente.\n\nMuchas gracias, saludos.\n\n" +
				            		usuario.getNombres() + ".");

				            // Envío del mensaje
				            Transport.send(message);

				            System.out.println("Correo electrónico enviado exitosamente.");
				        } catch (MessagingException e2) {
				            e2.printStackTrace();
				        }
						
					} catch (ServiciosException ex) {
						ex.printStackTrace();
					}

				}
			});
			btnRegistrar.setBounds(510, 251, 129, 21);
			panelForm.add(btnRegistrar);

			// -------------------------------------VOLVER--------------------------------------//

			JButton btnVolver = new JButton("Volver");
			btnVolver.setBounds(579, 415, 119, 23);
			panelForm.add(btnVolver);
			btnVolver.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					VentanaAnalista_FichaReclamo volver = new VentanaAnalista_FichaReclamo(usuariosBean, reclamo, usuario);

					volver.setBounds(-40, 0, 769, 498);

					cambiarContenido(panelForm, volver);

					volver.setVisible(true);

				}
			});

		} catch (NamingException | ServiciosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
