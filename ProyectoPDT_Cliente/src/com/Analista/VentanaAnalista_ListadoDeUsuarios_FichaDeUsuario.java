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

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.entitiesProyecto.Estudiante;
import com.entitiesProyecto.TipoUsuario;
import com.entitiesProyecto.Usuario;
import com.entitiesProyecto.UsuarioEstado;
import com.serviciosProyecto.EstudianteBeanRemote;
import com.serviciosProyecto.TutoresBeanRemote;
import com.serviciosProyecto.UsuarioEstadoBeanRemote;
import com.serviciosProyecto.UsuariosBeanRemote;

public class VentanaAnalista_ListadoDeUsuarios_FichaDeUsuario extends JPanel {

	private UsuariosBeanRemote usuariosBean;
	
	private JLabel lblGeneracion;
	private JLabel lblAreaTutorPertenece;
	private JLabel lblRol;
	private JLabel lblSemestre;
	private JLabel lblDinamicaGeneracion;
	private JLabel lblDinamicaSemestre;
	private JLabel lblDinamicaArea;
	private JLabel lblDinamicaRol;
	
	private String generacionAux;
	private String semestreAux;
	private String areaAux;
	private String rolAux;


	public void cambiarContenido(JPanel panelDestino, JPanel panelFuente) {
		panelDestino.removeAll();
		panelDestino.add(panelFuente, BorderLayout.CENTER);
		panelDestino.revalidate();
		panelDestino.repaint();
	}

	public VentanaAnalista_ListadoDeUsuarios_FichaDeUsuario(UsuariosBeanRemote usuariosBean, Usuario p1) {
		this.usuariosBean = usuariosBean;
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

		
		// ----------------------DATOS NO MODIFICABLES---------------------------------/
				
		//----------------------NOMBRES--------------------------------//

				JLabel lblNombres = new JLabel("Nombres");
				lblNombres.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblNombres.setBounds(29, 25, 76, 14);
				panelForm.add(lblNombres);

				
				JLabel lblNombre = new JLabel((String) null);
				lblNombre.setBounds(29, 45, 179, 25);
				Font font = new Font("Arial", Font.PLAIN, 12);
				lblNombre.setFont(font); 
				panelForm.add(lblNombre);


		//----------------------APELLIDOS--------------------------------//

				JLabel lblApellido1 = new JLabel("Apellidos");
				lblApellido1.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblApellido1.setBounds(29, 81, 76, 19);
				panelForm.add(lblApellido1);

				JLabel lblApellido = new JLabel((String) null);
				lblApellido.setBounds(29, 101, 179, 25);
				Font font1 = new Font("Arial", Font.PLAIN, 12);
				lblApellido.setFont(font1); 
				panelForm.add(lblApellido);

		//---------------------FECHA DE NACIMIENTO------------------------------//
				
				JLabel lblFechaDeNacimiento1 = new JLabel("Fecha de Nacimiento *");
				lblFechaDeNacimiento1.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblFechaDeNacimiento1.setBounds(29, 354, 143, 13);
				panelForm.add(lblFechaDeNacimiento1);

				JLabel lblFechaN = new JLabel((String) null);
				Font font2 = new Font("Arial", Font.PLAIN, 12);
				lblFechaN.setFont(font2); 
				lblFechaN.setBounds(29, 373, 179, 25);
				panelForm.add(lblFechaN);
		//----------------------CORREO PERSONAL-------------------------------//

				JLabel lblCorreoElectrnicoPersonal = new JLabel("Correo electrónico personal ");
				lblCorreoElectrnicoPersonal.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblCorreoElectrnicoPersonal.setBounds(29, 206, 189, 13);
				panelForm.add(lblCorreoElectrnicoPersonal);

				JLabel lblCorreo = new JLabel((String) null);
				Font font3 = new Font("Arial", Font.PLAIN, 12);
				lblCorreo.setFont(font3);
				lblCorreo.setBounds(29, 230, 179, 25);
				panelForm.add(lblCorreo);

		//--------------------CORREO INSTITUCIONAL------------------------//

				JLabel lblCorreoElectrnicoInstitucional = new JLabel("Correo electrónico institucional");
				lblCorreoElectrnicoInstitucional.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblCorreoElectrnicoInstitucional.setBounds(29, 273, 225, 13);
				panelForm.add(lblCorreoElectrnicoInstitucional);

				JLabel lblCorreoInstitu = new JLabel((String) null);
				Font font4 = new Font("Arial", Font.PLAIN, 12);
				lblCorreoInstitu.setFont(font4);
				lblCorreoInstitu.setBounds(29, 297, 225, 25);
				panelForm.add(lblCorreoInstitu);
	     
		//----------------------DOCUMENTO--------------------------------//

				JLabel lblDocumento = new JLabel("Documento");
				lblDocumento.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblDocumento.setBounds(29, 141, 76, 14);
				panelForm.add(lblDocumento);

				JLabel lblDocumento2 = new JLabel((String) null);
				lblDocumento2.setBounds(29, 164, 179, 25);
				Font font5 = new Font("Arial", Font.PLAIN, 12);
				lblDocumento2.setFont(font5);
				panelForm.add(lblDocumento2);
	     
		//----------------------TELEFONO--------------------------------//

				JLabel lblTelfono = new JLabel("Teléfono");
				lblTelfono.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblTelfono.setBounds(282, 25, 76, 14);
				panelForm.add(lblTelfono);

				JLabel lblTelefono = new JLabel((String) null);
				lblTelefono.setBounds(282, 45, 179, 25);
				Font font6 = new Font("Arial", Font.PLAIN, 12);
				lblTelefono.setFont(font6);
				panelForm.add(lblTelefono);
				
		//----------------------GÉNERO-------------------------------//

				JLabel lblGenero = new JLabel("Género");
				lblGenero.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblGenero.setBounds(282, 86, 76, 14);
				panelForm.add(lblGenero);

				JLabel lblGene = new JLabel((String) null);
				lblGene.setBounds(282, 114, 115, 14);
				Font font7 = new Font("Arial", Font.PLAIN, 12);
				lblGene.setFont(font7);
				panelForm.add(lblGene);

		//----------------------DEPARTAMENTO--------------------------//

				JLabel lblDepartamento = new JLabel("Departamento");
				lblDepartamento.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblDepartamento.setBounds(282, 168, 115, 14);
				panelForm.add(lblDepartamento);

				JLabel lblDepartamento2 = new JLabel((String) null);
				lblDepartamento2.setBounds(282, 194, 179, 24);
				Font font8 = new Font("Arial", Font.PLAIN, 12);
				lblDepartamento2.setFont(font8);
				panelForm.add(lblDepartamento2);

	   //----------------------LOCALIDAD----------------------------//
		
				JLabel lblLocalidad = new JLabel("Localidad");
				lblLocalidad.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblLocalidad.setBounds(282, 246, 115, 14);
				panelForm.add(lblLocalidad);

				JLabel lblLocalidad2 = new JLabel((String) null);
				lblLocalidad2.setBounds(282, 270, 179, 24);
				Font font9 = new Font("Arial", Font.PLAIN, 12);
				lblLocalidad2.setFont(font9);
				panelForm.add(lblLocalidad2);
				
		//----------------------ITR--------------------------------//
				JLabel lblItr = new JLabel("ITR");
				lblItr.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblItr.setBounds(282, 317, 115, 14);
				panelForm.add(lblItr);

				JLabel lblITR2 = new JLabel((String) null);
				lblITR2.setBounds(282, 342, 179, 25);
				Font font10 = new Font("Arial", Font.PLAIN, 12);
				lblITR2.setFont(font10);
				panelForm.add(lblITR2);


		//-----------------------------------TRAER DATOS A LA TABLA----------------------------------------------//

				lblNombre.setText(p1.getNombres());
				lblApellido.setText(p1.getApellidos());
				lblDocumento2.setText(String.valueOf(p1.getDocumento()));
				lblCorreo.setText(p1.getMail());
				lblTelefono.setText(p1.getTelefono());
				lblDepartamento2.setText(p1.getDepartamento().getNombre());
				lblLocalidad2.setText(p1.getLocalidade().getNombre());
				lblITR2.setText(p1.getItr().getNombre());
				lblCorreoInstitu.setText(p1.getMaiInstitucional());
				lblGene.setText(p1.getGenero().toString());
				lblFechaN.setText(String.valueOf(p1.getFecNacimiento()));
				
				
				if (p1.getTipo() == TipoUsuario.ESTUDIANTE) {
					
					try {
						EstudianteBeanRemote estudianteBean = (EstudianteBeanRemote) InitialContext
							.doLookup("ejb:/ProyectoPDT_Servidor/EstudianteBean!com.serviciosProyecto.EstudianteBeanRemote");
						generacionAux = estudianteBean.obtenerEstudiantePorIdUsuario(p1.getIdUsuario()).getGeneracion().toString();
						semestreAux = estudianteBean.obtenerEstudiantePorIdUsuario(p1.getIdUsuario()).getSemestre().toString();	
				
						//----------------------GENERACION-------------------------------//
		
						lblDinamicaGeneracion = new JLabel((String) null);
						lblDinamicaGeneracion.setBounds(523, 103, 179, 25);
						Font font13 = new Font("Arial", Font.PLAIN, 12);
						lblDinamicaGeneracion.setFont(font13);
						
						panelForm.add(lblDinamicaGeneracion);
						lblGeneracion = new JLabel("Generación");
						lblGeneracion.setFont(new Font("Tahoma", Font.BOLD, 13));
						lblGeneracion.setHorizontalAlignment(SwingConstants.LEFT);
						lblGeneracion.setBounds(523, 80, 182, 13);
						panelForm.add(lblGeneracion);
						lblDinamicaGeneracion.setText(generacionAux);
						
						//----------------------SEMESTRE-------------------------------//

						lblDinamicaSemestre = new JLabel((String) null);
						Font font11 = new Font("Arial", Font.PLAIN, 12);
						lblDinamicaSemestre.setFont(font11);
						lblDinamicaSemestre.setBounds(523, 166, 179, 25);
						
						panelForm.add(lblDinamicaSemestre);
						lblSemestre = new JLabel("Semestre");
						lblSemestre.setFont(new Font("Tahoma", Font.BOLD, 13));
						lblSemestre.setHorizontalAlignment(SwingConstants.LEFT);
						lblSemestre.setBounds(523, 139, 158, 13);
						panelForm.add(lblSemestre);
			
						lblDinamicaSemestre.setText(semestreAux);

					} catch (NamingException ex) {
					ex.printStackTrace();
					}
				
				} 
				
				if (p1.getTipo() == TipoUsuario.TUTOR) {
					
					try {
						TutoresBeanRemote tutoresBean = (TutoresBeanRemote) InitialContext
							.doLookup("ejb:/ProyectoPDT_Servidor/TutoresBean!com.serviciosProyecto.TutoresBeanRemote");
						areaAux = tutoresBean.obtenerTutorPorIdUsuario(p1.getIdUsuario()).getArea().toString();
						rolAux = tutoresBean.obtenerTutorPorIdUsuario(p1.getIdUsuario()).getTipo().toString();			
					
					} catch (NamingException ex1) {
						ex1.printStackTrace();
					}
				
					//----------------------AREA--------------------------------//

					lblDinamicaArea = new JLabel((String) null);
					lblDinamicaArea.setBounds(523, 103, 179, 25);
					Font font13 = new Font("Arial", Font.PLAIN, 12);
					lblDinamicaArea.setFont(font13);
					panelForm.add(lblDinamicaArea);
				
					lblAreaTutorPertenece = new JLabel("Área");
					lblAreaTutorPertenece.setFont(new Font("Tahoma", Font.BOLD, 13));
					lblAreaTutorPertenece.setHorizontalAlignment(SwingConstants.LEFT);
					lblAreaTutorPertenece.setBounds(523, 80, 182, 13);
					panelForm.add(lblAreaTutorPertenece);
				
					lblDinamicaArea.setText(areaAux);
					
					//----------------------ROL-------------------------------//

					lblDinamicaRol = new JLabel((String) null);
					lblDinamicaRol.setBounds(523, 166, 179, 25);
					Font font14 = new Font("Arial", Font.PLAIN, 12);
					lblDinamicaRol.setFont(font14);
					panelForm.add(lblDinamicaRol);
				
					lblRol = new JLabel("Rol");
					lblRol.setFont(new Font("Tahoma", Font.BOLD, 13));
					lblRol.setHorizontalAlignment(SwingConstants.LEFT);
					lblRol.setBounds(523, 139, 158, 13);
					panelForm.add(lblRol);
					
					lblDinamicaRol.setText(rolAux);

				}
				
		//----------------------------------------DATOS MODIFICABLES-------------------------------------------//

				// ----------------TIPO DE USUARIO----------------------//
				JLabel lblTipoUsuario = new JLabel("Tipo de Usuario*");
				lblTipoUsuario.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblTipoUsuario.setBounds(523, 21, 115, 14);
				panelForm.add(lblTipoUsuario);

				JComboBox comboBoxTipo = new JComboBox(TipoUsuario.values());
				comboBoxTipo.setBounds(523, 46, 138, 25);
				comboBoxTipo.setSelectedItem(p1.getTipo());
				panelForm.add(comboBoxTipo);
				

				comboBoxTipo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						TipoUsuario tipoSeleccionado = (TipoUsuario) comboBoxTipo.getSelectedItem();

						if (tipoSeleccionado != null) {
							try {
								UsuariosBeanRemote usuariosBean = (UsuariosBeanRemote) InitialContext.doLookup(
										"ejb:/ProyectoPDT_Servidor/UsuariosBean!com.serviciosProyecto.UsuariosBeanRemote");
								List<Usuario> usuarios = usuariosBean.obtenerUsuariosPorTipo(tipoSeleccionado);

							} catch (NamingException ex) {
								ex.printStackTrace();
							}
						}
					}
				});

				// ----------------ESTADO DEL USUARIO----------------------//

				JLabel lblEstado = new JLabel("Estado*");
				lblEstado.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblEstado.setBounds(523, 202, 115, 14);
				panelForm.add(lblEstado);

				JComboBox comboBoxEstado = new JComboBox();
				comboBoxEstado.setBounds(523, 228, 138, 25);
				panelForm.add(comboBoxEstado);

				try {
					UsuarioEstadoBeanRemote usuarioEstadoBean = (UsuarioEstadoBeanRemote) InitialContext.doLookup(
							"ejb:/ProyectoPDT_Servidor/UsuarioEstadoBean!com.serviciosProyecto.UsuarioEstadoBeanRemote");
					List<UsuarioEstado> usE = usuarioEstadoBean.obtenerUEstadoTodos();

					for (UsuarioEstado estado : usE) {
						comboBoxEstado.addItem(estado.getNombre());
					}
				} catch (NamingException e1) {
					e1.printStackTrace();
				}
				String estado = p1.getUsuaEstado().getNombre();
				comboBoxEstado.setSelectedItem(estado);

		//-----------------------------------MODIFICAR--------------------------------------------//

				JButton btnModificar = new JButton("Modificar");
				btnModificar.setBounds(599, 381, 119, 23);
				panelForm.add(btnModificar);
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Usuario modificado = p1;
						TipoUsuario tipomodifi = (TipoUsuario) comboBoxTipo.getSelectedItem();
						modificado.setTipo(tipomodifi);

						UsuarioEstado oEstadoElegido;
						UsuarioEstadoBeanRemote usuaEstado;
						try {
							usuaEstado = (UsuarioEstadoBeanRemote) InitialContext.doLookup(
									"ejb:/ProyectoPDT_Servidor/UsuarioEstadoBean!com.serviciosProyecto.UsuarioEstadoBeanRemote");
							oEstadoElegido = usuaEstado.obtenerEstadoPorNombre(comboBoxEstado.getSelectedItem().toString());
						} catch (Exception e2) {
							oEstadoElegido = null;
						}

						boolean valido = true;
						if (valido) {
							Object[] opciones = { "Sí", "No" };
							int respuesta = JOptionPane.showOptionDialog(null, "¿Seguro de modificar?", "Confirmar modificar",
									JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

							if (respuesta == JOptionPane.YES_OPTION) {
								try {
									UsuariosBeanRemote usuariosBean = (UsuariosBeanRemote) InitialContext.doLookup(
											"ejb:/ProyectoPDT_Servidor/UsuariosBean!com.serviciosProyecto.UsuariosBeanRemote");
									modificado.setUsuaEstado(oEstadoElegido);
									usuariosBean.actualizarUsuario(modificado);

								} catch (NamingException e1) {
									e1.printStackTrace();
								}
								JOptionPane.showMessageDialog(null, "Modificación realizada con éxito");
							}
						}
					}
				});

				// -----------------------------------VOLVER--------------------------------------------//

				JButton btnVolver = new JButton("Volver");
				btnVolver.setBounds(599, 415, 119, 23);
				panelForm.add(btnVolver);

				btnVolver.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						VentanaAnalista_ListadoDeUsuarios volver = new VentanaAnalista_ListadoDeUsuarios(usuariosBean,
								VentanaAnalista.getUsuario());

						volver.setBounds(-40, 0, 769, 498);

						cambiarContenido(panelForm, volver);

						volver.setVisible(true);

					}
				});

			}
		}
