package com.Tutor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.entitiesProyecto.Area;
import com.entitiesProyecto.Departamento;
import com.entitiesProyecto.EstadoItr;
import com.entitiesProyecto.Generacion;
import com.entitiesProyecto.Genero;
import com.entitiesProyecto.Itr;
import com.entitiesProyecto.Localidad;
import com.entitiesProyecto.RolTutor;
import com.entitiesProyecto.Semestre;
import com.entitiesProyecto.Tutor;
import com.entitiesProyecto.Usuario;
import com.serviciosProyecto.DepartamentoBeanRemote;
import com.serviciosProyecto.ITRBeanRemote;
import com.serviciosProyecto.LocalidadesBeanRemote;
import com.serviciosProyecto.TutoresBeanRemote;
import com.serviciosProyecto.UsuarioEstadoBeanRemote;
import com.serviciosProyecto.UsuariosBeanRemote;

import net.sourceforge.jdatepicker.impl.DateComponentFormatter;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class VentanaTutor_EditarDatosPersonales extends JPanel {


	private JTextField textFieldCorreoP;
	private JTextField textFieldCorreoI;
	private JTextField textFieldTelefono;
	private JPasswordField passwordFieldContrasenia;
	private JTextField textFieldNombres;
	private JTextField textFieldApellidos;
	private JComboBox<Area> comboBoxArea;
	private JComboBox<RolTutor> comboBoxRol;
	
	private Usuario usuario;
	private UsuariosBeanRemote usuariosBean;
	private UsuarioEstadoBeanRemote usuariosEstadoBean;
	private TutoresBeanRemote tutoresBean;
    private JComboBox<Genero> comboBoxGenero;



	public void cambiarContenido(JPanel panelDestino, JPanel panelFuente) {
		panelDestino.removeAll();
		panelDestino.add(panelFuente, BorderLayout.CENTER);
		panelDestino.revalidate();
		panelDestino.repaint();
	}

	public VentanaTutor_EditarDatosPersonales(Usuario usuario, Tutor tutor) {
		this.usuario = usuario;

		try {
			UsuariosBeanRemote usuariosBean = (UsuariosBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_Servidor/UsuariosBean!com.serviciosProyecto.UsuariosBeanRemote");
			TutoresBeanRemote tutoresBean = (TutoresBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_Servidor/TutoresBean!com.serviciosProyecto.TutoresBeanRemote");
	
			
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


			JLabel lblFormularioDeRegistro = new JLabel("Editar Datos Personales");
			lblFormularioDeRegistro.setFont(new Font("BIZ UDPGothic", Font.BOLD, 15));
			lblFormularioDeRegistro.setHorizontalAlignment(SwingConstants.CENTER);
			lblFormularioDeRegistro.setBounds(257, 11, 226, 20);
			panelForm.add(lblFormularioDeRegistro);

			// --------------------------TIPO DE USUARIO---------------------------------/
			JComboBox<String> comboBoxTipoDeUsuario = new JComboBox<String>();
			comboBoxTipoDeUsuario.setModel(new DefaultComboBoxModel<>(new String[] {"Tutor"}));

			comboBoxTipoDeUsuario.setToolTipText("Seleccione el tipo de usuario");
			comboBoxTipoDeUsuario.setEnabled(false);
			comboBoxTipoDeUsuario.setBounds(517, 67, 125, 20);
			panelForm.add(comboBoxTipoDeUsuario);

			JLabel lblTipoDeUsuario = new JLabel("Tipo de Usuario");
			lblTipoDeUsuario.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblTipoDeUsuario.setBounds(517, 43, 158, 13);
			panelForm.add(lblTipoDeUsuario);
			comboBoxTipoDeUsuario.setSelectedItem("Tutor");

			// -------------------------NOMBRE PARA MODIFICAR-------------------------//
			JLabel lblNombres = new JLabel("Nombres ");
			lblNombres.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNombres.setBounds(41, 43, 73, 13);
			panelForm.add(lblNombres);

			textFieldNombres = new JTextField();
			textFieldNombres.setText("<dynamic>");
			textFieldNombres.setColumns(10);
			textFieldNombres.setBounds(41, 66, 125, 19);
			panelForm.add(textFieldNombres);
			String nombres = usuario.getNombres();
			textFieldNombres.setText(nombres);

			// --------------------------APELLIDO PARA MODIFICAR---------------------------------/

			JLabel lblApellidos = new JLabel("Apellidos ");
			lblApellidos.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblApellidos.setBounds(41, 106, 73, 13);
			panelForm.add(lblApellidos);

			textFieldApellidos = new JTextField();
			textFieldApellidos.setText("<dynamic>");
			textFieldApellidos.setColumns(10);
			textFieldApellidos.setBounds(41, 129, 125, 19);
			panelForm.add(textFieldApellidos);
			String apellidos = usuario.getApellidos();
			textFieldApellidos.setText(apellidos);

			// --------------------------CONTRASEÑA PARA MODIFICAR---------------------------------/
			JLabel lblContrasea = new JLabel("Contraseña ");
			lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblContrasea.setBounds(41, 166, 86, 13);
			panelForm.add(lblContrasea);

			passwordFieldContrasenia = new JPasswordField();
			passwordFieldContrasenia.setColumns(10);
			passwordFieldContrasenia.setBounds(41, 190, 125, 19);
			panelForm.add(passwordFieldContrasenia);
			String contraseña = usuario.getContrasenia();
			passwordFieldContrasenia.setText(contraseña);

			// --------------------------DOCUMENTO NO SE MODIFICA---------------------------------/

			JLabel lblDocumento = new JLabel("Documento");
			lblDocumento.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblDocumento.setBounds(41, 220, 76, 14);
			panelForm.add(lblDocumento);

			JLabel lblDocumento2 = new JLabel((String) null);
			lblDocumento2.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblDocumento2.setBounds(41, 240, 179, 25);
			panelForm.add(lblDocumento2);
			lblDocumento2.setText(String.valueOf(usuario.getDocumento()));

			// --------------------------FECHA PARA MODIFICAR---------------------------------/

			JLabel lblFechaDeNacimiento1 = new JLabel("Fecha de Nacimiento ");
			lblFechaDeNacimiento1.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblFechaDeNacimiento1.setBounds(41, 282, 155, 13);
			panelForm.add(lblFechaDeNacimiento1);

			UtilDateModel model = new UtilDateModel();
			JDatePanelImpl datePanel = new JDatePanelImpl(model);
			JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
			datePicker.getJFormattedTextField().setSize(152, 19);
			datePicker.getJFormattedTextField().setToolTipText("Seleccione una fecha");
			datePicker.setBounds(41, 306, 143, 21);
			panelForm.add(datePicker);
			UtilDateModel dateModel = (UtilDateModel) datePicker.getModel();
			dateModel.setValue(usuario.getFecNacimiento());

			// --------------------------CORREO PERSONAL PARA MODIFICAR---------------------------------/

			JLabel lblCorreoElectrnicoPersonal = new JLabel("Correo electrónico personal");
			lblCorreoElectrnicoPersonal.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblCorreoElectrnicoPersonal.setBounds(279, 221, 170, 13);
			panelForm.add(lblCorreoElectrnicoPersonal);

			textFieldCorreoP = new JTextField();
			textFieldCorreoP.setText("<dynamic>");
			textFieldCorreoP.setColumns(10);
			textFieldCorreoP.setBounds(279, 242, 179, 19);
			panelForm.add(textFieldCorreoP);
			String mailPersonal = usuario.getMail();
			textFieldCorreoP.setText(mailPersonal);

			// ---------------------------TELEFONO PARA MODIFICAR---------------------------------/

			JLabel lblTelfonoDeContacto = new JLabel("Teléfono de contacto ");
			lblTelfonoDeContacto.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblTelfonoDeContacto.setBounds(41, 344, 143, 13);
			panelForm.add(lblTelfonoDeContacto);

			textFieldTelefono = new JTextField();
			textFieldTelefono.setText("<dynamic>");
			textFieldTelefono.setColumns(10);
			textFieldTelefono.setBounds(41, 367, 125, 19);
			panelForm.add(textFieldTelefono);
			String telefono = usuario.getTelefono();
			textFieldTelefono.setText(telefono);

			// ---------------------------DEPARTAMENTO PARA MODIFICAR---------------------------------/

			JLabel lblDepartamento = new JLabel("Departamento");
			lblDepartamento.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblDepartamento.setBounds(279, 42, 106, 13);
			panelForm.add(lblDepartamento);

			JComboBox comboBoxDepartamento = new JComboBox();
			DepartamentoBeanRemote departamentoBean;
			try {
				departamentoBean = (DepartamentoBeanRemote) InitialContext.doLookup(
						"ejb:/ProyectoPDT_Servidor/DepartamentoBean!com.serviciosProyecto.DepartamentoBeanRemote");
				List<Departamento> dep = departamentoBean.obtenerTodosDepartamento();

				for (Departamento departamento : dep) {
					comboBoxDepartamento.addItem(departamento.getNombre());
				}
			} catch (NamingException e1) {
				e1.printStackTrace();
			}

			comboBoxDepartamento.setToolTipText("Seleccione un Departamento");
			comboBoxDepartamento.setBounds(279, 65, 125, 21);
			panelForm.add(comboBoxDepartamento);
			String departamento = usuario.getDepartamento().getNombre();
			comboBoxDepartamento.setSelectedItem(departamento);

			// ---------------------------LOCALIDAD PARA MODIFICAR---------------------------------/

			JLabel lblLocalidad = new JLabel("Localidad");
			lblLocalidad.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblLocalidad.setBounds(279, 106, 73, 13);
			panelForm.add(lblLocalidad);

			JComboBox<Object> comboBoxLocalidad = new JComboBox<Object>();
			LocalidadesBeanRemote localidadBean;
			try {
				localidadBean = (LocalidadesBeanRemote) InitialContext.doLookup(
						"ejb:/ProyectoPDT_Servidor/LocalidadesBean!com.serviciosProyecto.LocalidadesBeanRemote");
				List<Localidad> dep = localidadBean.obtenerTodasLocalidades();

				for (Localidad localidad : dep) {
					comboBoxLocalidad.addItem(localidad.getNombre());

				}
			} catch (NamingException e1) {
				e1.printStackTrace();
			}
			comboBoxLocalidad.setBounds(279, 130, 125, 19);
			panelForm.add(comboBoxLocalidad);
			String localidad = usuario.getLocalidade().getNombre();
			comboBoxLocalidad.setSelectedItem(localidad);

			// ---------------------------ITR PARA MODIFICAR---------------------------------/

			JLabel lblItr = new JLabel("ITR");
			lblItr.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblItr.setBounds(279, 166, 86, 13);
			panelForm.add(lblItr);

			JComboBox comboBox_Itr = new JComboBox();
			try {
				ITRBeanRemote itrBean = (ITRBeanRemote) InitialContext
						.doLookup("ejb:/ProyectoPDT_Servidor/ITRBean!com.serviciosProyecto.ITRBeanRemote");

				List<Itr> itr = itrBean.obtenerItrTodos();

				for (Itr itrListado : itr) {
					if (itrListado.getEstado().equals(EstadoItr.ACTIVO)) { // Verifica el estado del TIR
						comboBox_Itr.addItem(itrListado.getNombre());
					}
				}
			} catch (NamingException e1) {
				e1.printStackTrace();
			}

			comboBox_Itr.setToolTipText("Seleccione un ITR");
			comboBox_Itr.setBounds(279, 188, 125, 21);
			panelForm.add(comboBox_Itr);
			String itrs = usuario.getItr().getNombre();
			comboBox_Itr.setSelectedItem(itrs);

			// ---------------CORREO INSTITUCIONAL PARA MODIFICA-----------------------------//

			JLabel lblCorreoElectronico = new JLabel("Correo electrónico institucional ");
			lblCorreoElectronico.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblCorreoElectronico.setBounds(279, 282, 220, 13);
			panelForm.add(lblCorreoElectronico);

			textFieldCorreoI = new JTextField();
			textFieldCorreoI.setText("<dynamic>");
			textFieldCorreoI.setColumns(10);
			textFieldCorreoI.setBounds(279, 305, 179, 19);
			panelForm.add(textFieldCorreoI);
			String correoInstitucional = usuario.getMaiInstitucional();
			textFieldCorreoI.setText(correoInstitucional);

			// -------------------------GENERO PARA MODIFICAR-------------------------------//

			comboBoxGenero = new JComboBox<>(Genero.values());
			comboBoxGenero.setBounds(279, 366, 125, 20);
			panelForm.add(comboBoxGenero);
			Genero genero = usuario.getGenero();
			comboBoxGenero.setSelectedItem(genero);

			JLabel lblGenero = new JLabel("Género");
			lblGenero.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblGenero.setBounds(283, 342, 46, 14);
			panelForm.add(lblGenero);
			
			// ------------------------TUTOR AREA-------------------------------//
			
			JLabel lblSemestre = new JLabel("Area");
			lblSemestre.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblSemestre.setHorizontalAlignment(SwingConstants.LEFT);
			lblSemestre.setBounds(517, 169, 158, 13);
			panelForm.add(lblSemestre);

			comboBoxArea = new JComboBox<>(Area.values());// 
			comboBoxArea.setBounds(517, 190, 125, 21);
			panelForm.add(comboBoxArea);
			Area area = tutor.getArea();
			comboBoxArea.setSelectedItem(area);
			
		   // -------------------------TUTOR ROL-------------------------------//
			
			JLabel lblGeneracion = new JLabel("Rol");
			lblGeneracion.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblGeneracion.setHorizontalAlignment(SwingConstants.LEFT);
			lblGeneracion.setBounds(517, 106, 182, 13);
			panelForm.add(lblGeneracion);

			comboBoxRol = new JComboBox<>(RolTutor.values());// 
			comboBoxRol.setBounds(517, 128, 125, 21);
			panelForm.add(comboBoxRol);
			RolTutor rol = tutor.getTipo();
			comboBoxRol.setSelectedItem(rol);
			
			
			// -------------------------MODIFICAR-------------------------------//

			JButton btnEnviar = new JButton("Modificar");
			btnEnviar.addActionListener(new ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e) {
					boolean valido = false;
					char[] password = passwordFieldContrasenia.getPassword();
					String passwordString = new String(password);
					String correoP = textFieldCorreoP.getText();
					
					
					while (!valido) {
						if (textFieldNombres.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío");
							break;
						} else if (!textFieldNombres.getText().matches("[a-zA-Z]+( [a-zA-Z]+)?")) {
							JOptionPane.showMessageDialog(null, "El nombre solo debe contener letras");
							break;
						} else if (textFieldNombres.getText().length() < 3 || textFieldNombres.getText().length() > 20) {
							JOptionPane.showMessageDialog(null, "El nombre debe tener entre 3 y 20 caracteres");
							break;
						} else if (textFieldApellidos.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "El apellido no puede estar vacío");
							break;
						} else if (!textFieldApellidos.getText().matches("[a-zA-Z]+( [a-zA-Z]+)?")) {
							JOptionPane.showMessageDialog(null, "El apellido solo debe contener letras");
							break;
						} else if (textFieldApellidos.getText().length() < 3
								|| textFieldApellidos.getText().length() > 20) {
							JOptionPane.showMessageDialog(null, "El apellido debe tener entre 3 y 20 caracteres");
							break;
						} else if (password.length == 0) {
							JOptionPane.showMessageDialog(null, "La contraseña no puede estar vacía");
							break;
						} else if (password.length < 8) {
							JOptionPane.showMessageDialog(null, "La contraseña debe tener más de 8 caracteres");
							break;
						} else if (!passwordString.matches(".*\\d.*")) {
							JOptionPane.showMessageDialog(null, "La contraseña debe contener al menos un número");
							break;
						} else if (datePicker.getModel().getValue() == null) {
							JOptionPane.showMessageDialog(null, "La fecha de nacimiento no puede estar vacía");
							break;
						} else if (textFieldCorreoP.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "El correo electrónico personal no puede estar vacío");
							break;
						} else if (!textFieldCorreoP.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
							JOptionPane.showMessageDialog(null,
									"El correo electrónico personal no tiene un formato válido");
							break;
						} else if (textFieldCorreoP.getText().length() > 100) {
							JOptionPane.showMessageDialog(null,
									"El correo electrónico personal debe contener menos de 100 caracteres");
							break;
						} else if (textFieldTelefono.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "El teléfono de contacto no puede estar vacío");
							break;
						} else if (!textFieldTelefono.getText().matches("\\d+")) {
							JOptionPane.showMessageDialog(null, "El teléfono de contacto debe contener números únicamente");
							break;
						} else if (textFieldTelefono.getText().length() < 8 || textFieldTelefono.getText().length() > 10) {
							JOptionPane.showMessageDialog(null, "El teléfono de contacto debe tener 9 números");
							break;
						} else if (textFieldCorreoI.getText().isEmpty()) {
						    JOptionPane.showMessageDialog(null, "El correo electrónico institucional no puede estar vacío");
						    break;
						} else if (!textFieldCorreoI.getText().matches("^\\w+\\.\\w+@(?:utec\\.edu\\.uy|estudiantes\\.utec\\.edu\\.uy)$")) {
						    JOptionPane.showMessageDialog(null, "El correo electrónico institucional no tiene un formato válido");
						    break;
						} else if (textFieldCorreoI.getText().length() > 100) {
						    JOptionPane.showMessageDialog(null, "El correo electrónico institucional debe contener menos de 100 caracteres");
						    break;
						} else if (comboBoxGenero.getSelectedItem() == null) {
							JOptionPane.showMessageDialog(null, "El genero no puede estar vacío");
							break;
						} else if (comboBoxDepartamento.getSelectedItem() == null) {
							JOptionPane.showMessageDialog(null, "Debe seleccionar unDepartamento");
							break;
						} else if (comboBoxLocalidad.getSelectedItem() == null) {
							JOptionPane.showMessageDialog(null, "Debe seleccionar una Localidad");
							break;
						} else if (comboBox_Itr.getSelectedItem() == null) {
							JOptionPane.showMessageDialog(null, "Debe seleccionar un Itr");
							break;
						} else {

							valido = true;
						}
					}
					
					if (valido) {
						Object[] opciones = { "Sí", "No" };
						int respuesta = JOptionPane.showOptionDialog(null, "¿Seguro de modificar?", "Confirmar modificación",
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
						if (respuesta == JOptionPane.YES_OPTION) {
							Departamento oDepartamentoElegido;
							DepartamentoBeanRemote departamentoBean1;
							try {
								departamentoBean1 = (DepartamentoBeanRemote) InitialContext.doLookup(
										"ejb:/ProyectoPDT_Servidor/DepartamentoBean!com.serviciosProyecto.DepartamentoBeanRemote");
								oDepartamentoElegido = departamentoBean1.obtenerDepartamentoPorNombre(
										comboBoxDepartamento.getSelectedItem().toString());
							} catch (NamingException e1) {
								oDepartamentoElegido = null;
							}

							// localidad
							Localidad oLocalidadElegido = null;
							LocalidadesBeanRemote localidadBean1;
							try {
								localidadBean1 = (LocalidadesBeanRemote) InitialContext.doLookup(
										"ejb:/ProyectoPDT_Servidor/LocalidadesBean!com.serviciosProyecto.LocalidadesBeanRemote");
								oLocalidadElegido = localidadBean1
										.obtenerLocalidadPorNombre(comboBoxLocalidad.getSelectedItem().toString());
							} catch (NamingException e1) {
								oLocalidadElegido = null;
							}

							// itr
							Itr oItrElegido = null;
							ITRBeanRemote itrBean;
							try {
								itrBean = (ITRBeanRemote) InitialContext.doLookup(
										"ejb:/ProyectoPDT_Servidor/ITRBean!com.serviciosProyecto.ITRBeanRemote");
								String nombreItr = comboBox_Itr.getSelectedItem().toString();
								oItrElegido = itrBean.obtenerItrPorNombre(nombreItr);
							} catch (NamingException e1) {
								oItrElegido = null;
							}
							try {

								// Modificar datos de la instancia "usuario" en Java
								usuario.setNombres(textFieldNombres.getText());
								usuario.setApellidos(textFieldApellidos.getText());
								String contrasenia = new String(passwordFieldContrasenia.getPassword());
								usuario.setContrasenia(contrasenia);
								usuario.setMail(textFieldCorreoP.getText());
								usuario.setTelefono(textFieldTelefono.getText());
								Date fecha = (Date) datePicker.getModel().getValue();
								usuario.setFecNacimiento(fecha);
						        Genero generoSeleccionado = (Genero) comboBoxGenero.getSelectedItem();
						        usuario.setGenero(generoSeleccionado);
								usuario.setMaiInstitucional(textFieldCorreoI.getText());
								usuario.setDepartamento(oDepartamentoElegido);
								usuario.setLocalidade(oLocalidadElegido);
								usuario.setItr(oItrElegido);

								// Actualizar datos en la base de datos
								usuariosBean.actualizarUsuario(usuario);

								try {
									
									Area nuevaArea = (Area) comboBoxArea.getSelectedItem();
									RolTutor nuevoRol = (RolTutor) comboBoxRol.getSelectedItem();
									
									tutor.setArea(nuevaArea);
									tutor.setTipo(nuevoRol);
									
									tutoresBean.actualizarTutor(tutor);
									JOptionPane.showMessageDialog(null, "Tutor modificado"); // Mensaje

								} catch (Exception e2) {
									JOptionPane.showMessageDialog(null, "Error al modificar el tutor");
								}	
								
							} catch (Exception e2) {
								e2.printStackTrace();
								JOptionPane.showMessageDialog(null, "Error al modificar usuario");
							}

						}

						// si el usuario clickea "no", no se hace nada y se queda en la misma ventana
					}

				}
			});
			btnEnviar.setBounds(579, 339, 119, 21);
			panelForm.add(btnEnviar);


			// -------------------------CANCELAR-------------------------------//

			JButton btnCancelar = new JButton("Cancelar");
			btnCancelar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Object[] opciones = { "Sí", "No" };
					int respuesta = JOptionPane.showOptionDialog(null,
							"Estás seguro que deseas cancelar  la modificación?", "Confirmar cancelación",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
					if (respuesta == JOptionPane.YES_OPTION) {
						JOptionPane.showMessageDialog(null, "Modificación Cancelada");
						
						
						VentanaTutor tutor = new VentanaTutor(usuariosBean, usuario);

						tutor.setBounds(-40, 0, 769, 498);

						cambiarContenido(panelForm, tutor);

						tutor.setVisible(true);

					}
				}
			});

			btnCancelar.setBounds(579, 367, 119, 21);
			panelForm.add(btnCancelar);


//------------------------------VOLVER--------------------------------------///

			JButton btnVolver = new JButton("Volver");
			btnVolver.setBounds(579, 415, 119, 23);
			panelForm.add(btnVolver);
			btnVolver.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					VentanaTutor volver = new VentanaTutor(usuariosBean, usuario);

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


