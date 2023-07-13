package com.Interfaz;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import com.Analista.VentanaAnalista;
import com.entitiesProyecto.Analista;
import com.entitiesProyecto.Area;
import com.entitiesProyecto.Departamento;
import com.entitiesProyecto.EstadoItr;
import com.entitiesProyecto.Estudiante;
import com.entitiesProyecto.Generacion;
import com.entitiesProyecto.Genero;
import com.entitiesProyecto.Itr;
import com.entitiesProyecto.Localidad;
import com.entitiesProyecto.RolTutor;
import com.entitiesProyecto.Semestre;
import com.entitiesProyecto.TipoUsuario;
import com.entitiesProyecto.Tutor;
import com.entitiesProyecto.Usuario;
import com.entitiesProyecto.UsuarioEstado;
import com.serviciosProyecto.AnalistaBeanRemote;
import com.serviciosProyecto.DepartamentoBeanRemote;
import com.serviciosProyecto.EstudianteBeanRemote;
import com.serviciosProyecto.ITRBeanRemote;
import com.serviciosProyecto.LocalidadesBeanRemote;
import com.serviciosProyecto.TutoresBeanRemote;
import com.serviciosProyecto.UsuarioEstadoBeanRemote;
import com.serviciosProyecto.UsuariosBeanRemote;

import Atxy2k.CustomTextField.RestrictedTextField;
import net.sourceforge.jdatepicker.impl.DateComponentFormatter;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class VentanaRegistro extends JPanel {

	private UsuariosBeanRemote usuariosBean;
	private JTextField textFieldNombres;
	private JTextField textFieldApellidos;
	private JTextField textFieldDocumento;
	private JTextField textFieldCorreoP;
	private JTextField textFieldCorreoI;
	private JPasswordField passwordFieldContrasenia;
	private JTextField textFieldTelefono;
	private JTextField textFieldArea;
	
	private Long documentoAuxiliar;

	private JLabel lblGeneracion;
	private JLabel lblArea;
	private JLabel lblRol;
	private JLabel lblSemestre;

	private JComboBox<TipoUsuario> comboBoxTipoUsuario;
	private JComboBox<Generacion> comboBoxGeneracion;
	private JComboBox<Semestre> comboBoxSemestre;
	private JComboBox<Genero> comboBoxGenero;
	private JComboBox<RolTutor> comboBoxRol;
	private JComboBox<Area> comboBoxArea;
	JFrame frame;

	public VentanaRegistro() {
		setLayout(null);

		setLayout(null);

		JPanel panelMain = new JPanel();
		panelMain.setBackground(SystemColor.controlHighlight);
		panelMain.setBounds(40, 0, 779, 451);
		add(panelMain);
		panelMain.setLayout(null);

		JPanel panelForm = new JPanel();
		panelForm.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		panelForm.setBackground(SystemColor.controlHighlight);
		panelForm.setBounds(21, 0, 817, 440);
		panelMain.add(panelForm);
		panelForm.setLayout(null);

//--------------------------------CAMPOS DE REGISTRO-----------------------------------------//

		JLabel lblNombres = new JLabel("Nombres*");
		lblNombres.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombres.setBounds(29, 21, 76, 14);
		panelForm.add(lblNombres);

		textFieldNombres = new JTextField();
		textFieldNombres.setBounds(29, 41, 179, 25);
		panelForm.add(textFieldNombres);
		textFieldNombres.setColumns(10);

		JLabel lblApellido = new JLabel("Apellidos*");
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblApellido.setBounds(29, 77, 76, 19);
		panelForm.add(lblApellido);

		textFieldApellidos = new JTextField();
		textFieldApellidos.setColumns(10);
		textFieldApellidos.setBounds(29, 97, 179, 25);
		panelForm.add(textFieldApellidos);

		passwordFieldContrasenia = new JPasswordField();
		passwordFieldContrasenia.setBounds(29, 166, 179, 25);
		panelForm.add(passwordFieldContrasenia);

		JLabel lblContrasenia = new JLabel("Contraseña*");
		lblContrasenia.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblContrasenia.setBounds(29, 136, 76, 19);
		panelForm.add(lblContrasenia);

		JLabel lblFechaDeNacimiento1 = new JLabel("Fecha de Nacimiento *");
		lblFechaDeNacimiento1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFechaDeNacimiento1.setBounds(281, 22, 143, 13);
		panelForm.add(lblFechaDeNacimiento1);

		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
		datePicker.getJFormattedTextField().setToolTipText("Seleccione una fecha");
		datePicker.setBounds(281, 41, 179, 25);
		panelForm.add(datePicker);

		JLabel lblCorreoElectrnicoPersonal = new JLabel("Correo electrónico personal *");
		lblCorreoElectrnicoPersonal.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCorreoElectrnicoPersonal.setBounds(29, 277, 189, 13);
		panelForm.add(lblCorreoElectrnicoPersonal);

		textFieldCorreoP = new JTextField();
		textFieldCorreoP.setColumns(10);
		textFieldCorreoP.setBounds(29, 304, 179, 25);
		panelForm.add(textFieldCorreoP);

		JLabel lblCorreoElectrnicoInstitucional = new JLabel("Correo electrónico institucional *");
		lblCorreoElectrnicoInstitucional.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCorreoElectrnicoInstitucional.setBounds(29, 352, 189, 13);
		panelForm.add(lblCorreoElectrnicoInstitucional);

		textFieldCorreoI = new JTextField();
		textFieldCorreoI.setColumns(10);
		textFieldCorreoI.setBounds(29, 379, 179, 25);
		panelForm.add(textFieldCorreoI);

		JLabel lblDocumento = new JLabel("Documento*");
		lblDocumento.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDocumento.setBounds(29, 202, 76, 14);
		panelForm.add(lblDocumento);

		textFieldDocumento = new JTextField();
		textFieldDocumento.setColumns(10);
		textFieldDocumento.setBounds(29, 227, 179, 25);
		panelForm.add(textFieldDocumento);
		
		RestrictedTextField r1;
		r1 = new RestrictedTextField(textFieldDocumento);
        r1.setLimit(8);
        
        try {
			usuariosBean = (UsuariosBeanRemote) InitialContext.doLookup(
        		"ejb:/ProyectoPDT_Servidor/UsuariosBean!com.serviciosProyecto.UsuariosBeanRemote");
        } catch (NamingException e1) {
        	e1.printStackTrace();
        }
		
		JLabel lblTelfono = new JLabel("Teléfono*");
		lblTelfono.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTelfono.setBounds(281, 77, 76, 14);
		panelForm.add(lblTelfono);

		textFieldTelefono = new JTextField();
		textFieldTelefono.setColumns(10);
		textFieldTelefono.setBounds(281, 97, 179, 25);
		panelForm.add(textFieldTelefono);

		JLabel lblGenero = new JLabel("Género*");
		lblGenero.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGenero.setBounds(281, 138, 76, 14);
		panelForm.add(lblGenero);

		comboBoxGenero = new JComboBox<>(Genero.values());//
		comboBoxGenero.setSelectedItem(null);
		comboBoxGenero.setBounds(281, 166, 179, 24);
		panelForm.add(comboBoxGenero);

		// ---------------------------DEPARTAMENTO-----------------------//

		JLabel lblDepartamento = new JLabel("Departamento*");
		lblDepartamento.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDepartamento.setBounds(281, 202, 115, 14);
		panelForm.add(lblDepartamento);
		DepartamentoBeanRemote departamentoBean;

		JComboBox<String> comboBoxDepartamento = new JComboBox<String>();
		comboBoxDepartamento.setModel(new DefaultComboBoxModel<String>(new String[] { " " }));
		comboBoxDepartamento.setSelectedItem(null);
		comboBoxDepartamento.setBounds(281, 228, 179, 24);
		panelForm.add(comboBoxDepartamento);
		try {
			departamentoBean = (DepartamentoBeanRemote) InitialContext.doLookup(
					"ejb:/ProyectoPDT_Servidor/DepartamentoBean!com.serviciosProyecto.DepartamentoBeanRemote");
			List<Departamento> dep = departamentoBean.obtenerTodosDepartamento();
			for (Departamento departamento : dep) {
				// con el comando addItem metemos los departamentos en el combobox
				comboBoxDepartamento.addItem(departamento.getNombre());
			}
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// ---------------------------LOCALIDAD-----------------------//

		JLabel lblLocalidad = new JLabel("Localidad*");
		lblLocalidad.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLocalidad.setBounds(281, 280, 115, 14);
		panelForm.add(lblLocalidad);

		JComboBox<String> comboBoxLocalidad = new JComboBox<String>();
		comboBoxLocalidad.setModel(new DefaultComboBoxModel<String>(new String[] { " " }));
		comboBoxLocalidad.setSelectedItem(null);
		comboBoxLocalidad.setBounds(281, 304, 179, 24);
		panelForm.add(comboBoxLocalidad);
		LocalidadesBeanRemote localidadBean;
		try {
			localidadBean = (LocalidadesBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_Servidor/LocalidadesBean!com.serviciosProyecto.LocalidadesBeanRemote");
			List<Localidad> dep = localidadBean.obtenerTodasLocalidades();

			for (Localidad localidad : dep) {
				// con el comando addItem metemos los departamentos en el combobox
				comboBoxLocalidad.addItem(localidad.getNombre());

			}
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// ---------------------------ITR-----------------------//

		JLabel lblItr = new JLabel("ITR*");
		lblItr.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblItr.setBounds(281, 351, 115, 14);
		panelForm.add(lblItr);

		JComboBox<String> comboBoxItr = new JComboBox<String>();
		comboBoxItr.setModel(new DefaultComboBoxModel<String>(new String[] { " " }));
		comboBoxItr.setSelectedItem(null);
		comboBoxItr.setBounds(281, 376, 179, 25);
		panelForm.add(comboBoxItr);
		try {
			ITRBeanRemote itrBean = (ITRBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_Servidor/ITRBean!com.serviciosProyecto.ITRBeanRemote");

			List<Itr> itr = itrBean.obtenerItrTodos();

			for (Itr itrListado : itr) {
				if (itrListado.getEstado().equals(EstadoItr.ACTIVO)) { // Verifica el estado del TIR
					comboBoxItr.addItem(itrListado.getNombre());
				}
			}

		} catch (NamingException e1) {
			e1.printStackTrace();
		}

		// ---------------------------TIPO DE USUARIO-----------------------//

		JLabel lblTipoUsuario = new JLabel("Tipo de Usuario*");
		lblTipoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTipoUsuario.setBounds(523, 21, 115, 14);
		panelForm.add(lblTipoUsuario);

		comboBoxTipoUsuario = new JComboBox<>(TipoUsuario.values());//
		comboBoxTipoUsuario.setBounds(523, 41, 179, 24);
		comboBoxTipoUsuario.setSelectedItem(null); // Establecer selección inicial en null
		panelForm.add(comboBoxTipoUsuario);
		comboBoxTipoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					        TipoUsuario tipoSeleccionado = (TipoUsuario) comboBoxTipoUsuario.getSelectedItem();

					        if (lblGeneracion != null) {
					            panelForm.remove(lblGeneracion);
					            lblGeneracion = null;
					        }
					        if (comboBoxGeneracion != null) {
					            panelForm.remove(comboBoxGeneracion);
					            comboBoxGeneracion = null;
					        }
					        if (lblSemestre != null) {
					            panelForm.remove(lblSemestre);
					            lblSemestre = null;
					        }
					        if (comboBoxSemestre != null) {
					            panelForm.remove(comboBoxSemestre);
					            comboBoxSemestre = null;
					        }
					        if (lblArea != null) {
					            panelForm.remove(lblArea);
					            lblArea = null;
					        }
					        if (comboBoxArea != null) {
					            panelForm.remove(comboBoxArea);
					            comboBoxArea = null;
					        }
					        if (lblRol != null) {
					            panelForm.remove(lblRol);
					            lblRol = null;
					        }
					        if (comboBoxRol != null) {
					            panelForm.remove(comboBoxRol);
					            comboBoxRol = null;
					        }

					        if (tipoSeleccionado.equals(TipoUsuario.ESTUDIANTE)) {
					        	
					        	lblGeneracion = new JLabel("Generación *");
						lblGeneracion.setFont(new Font("Tahoma", Font.PLAIN, 13));
						lblGeneracion.setHorizontalAlignment(SwingConstants.LEFT);
						lblGeneracion.setBounds(523, 80, 182, 13);
						panelForm.add(lblGeneracion);
								
					        	comboBoxGeneracion = new JComboBox<>(Generacion.values());// 
						comboBoxGeneracion.setBounds(523, 97, 179, 25);
						comboBoxGeneracion.setSelectedItem(null);
						panelForm.add(comboBoxGeneracion);
								
						lblSemestre = new JLabel("Semestre *");
						lblSemestre.setFont(new Font("Tahoma", Font.PLAIN, 13));
						lblSemestre.setHorizontalAlignment(SwingConstants.LEFT);
						lblSemestre.setBounds(523, 139, 158, 13);
						panelForm.add(lblSemestre);
								
						comboBoxSemestre = new JComboBox<>(Semestre.values());// 
						comboBoxSemestre.setSelectedItem(null);
						comboBoxSemestre.setBounds(523, 166, 179, 25);
						panelForm.add(comboBoxSemestre);
								
					        } else if (tipoSeleccionado.equals(TipoUsuario.TUTOR)) {
					        		comboBoxArea = new JComboBox<>(Area.values());// 
							comboBoxArea.setSelectedItem(null);
							comboBoxArea.setBounds(523, 97, 179, 25);
							panelForm.add(comboBoxArea);
								
							lblArea = new JLabel("Área *");
							lblArea.setFont(new Font("Tahoma", Font.PLAIN, 13));
							lblArea.setHorizontalAlignment(SwingConstants.LEFT);
							lblArea.setBounds(523, 80, 182, 13);
							panelForm.add(lblArea);
								
							comboBoxRol = new JComboBox<>(RolTutor.values());// 
							comboBoxRol.setSelectedItem(null);
							comboBoxRol.setBounds(523, 168, 179, 25);
							panelForm.add(comboBoxRol);
								
							lblRol = new JLabel("Rol *");
							lblRol.setFont(new Font("Tahoma", Font.PLAIN, 13));
							lblRol.setHorizontalAlignment(SwingConstants.LEFT);
							lblRol.setBounds(523, 139, 158, 13);
							panelForm.add(lblRol);
					        }

					        panelForm.revalidate();
					        panelForm.repaint();
					    }
					});


		// --------------------------ENVIAR REGISTRO + VALIDACIONES----------------------//

		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean valido = false;
				char[] password = passwordFieldContrasenia.getPassword();
				String passwordString = new String(password);
				String correoP = textFieldCorreoP.getText();
				String documento = textFieldDocumento.getText().trim();
				
				if (documento != null && !documento.isEmpty()) {
				    try {
				        documentoAuxiliar = Long.parseLong(documento);
				    } catch (NumberFormatException nfe) {
				        JOptionPane.showMessageDialog(null, "El campo documento debe contener solo números.");
				        textFieldDocumento.setText("");
				        return;
				    }
				}
				
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
					} else if (textFieldDocumento.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "El documento no puede estar vacío");
						break;
					} else if (!validarCedula(documento)) {
					    JOptionPane.showMessageDialog(null, "El documento ingresado " + documento + " no es válido. Por favor, intenta nuevamente.");
					    textFieldDocumento.setText("");
					    break; 	
					}else if (usuariosBean.existeDocumento(documentoAuxiliar)) {
						JOptionPane.showMessageDialog(null, "El documento " + documento + " ya está en uso. Por favor, intenta nuevamente.");
						textFieldDocumento.setText("");
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
					} else if (usuariosBean.existeCorreoP(correoP)) {
						JOptionPane.showMessageDialog(null,
								"El correo electrónico personal " + correoP + " ya está en uso. Por favor, intenta nuevamente.");
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
					} else if (comboBoxItr.getSelectedItem() == null) {
						JOptionPane.showMessageDialog(null, "Debe seleccionar un Itr");
						break;
					} else if (comboBoxTipoUsuario.getSelectedItem() == null) {
						JOptionPane.showMessageDialog(null, "Debe seleccionar un Tipo de usuario");
						break;
					} else {

						valido = true;
					}
				}
				
				if (comboBoxTipoUsuario.getSelectedItem() == TipoUsuario.ESTUDIANTE) {
					if (comboBoxGeneracion.getSelectedItem() == null) {
						JOptionPane.showMessageDialog(null, "Debe seleccionar una generación");
						return;
					}else if(comboBoxSemestre.getSelectedItem() == null) {
						JOptionPane.showMessageDialog(null, "Debe seleccionar un semestre");
						return;
					}
				}
				
				if (comboBoxTipoUsuario.getSelectedItem() == TipoUsuario.TUTOR) {
					if (comboBoxArea.getSelectedItem() == null) {
						JOptionPane.showMessageDialog(null, "Debe seleccionar un área");
						return;
					}else if(comboBoxRol.getSelectedItem() == null) {
						JOptionPane.showMessageDialog(null, "Debe seleccionar un rol");
						return;
					}
				}
				
				if (valido) {
					Object[] opciones = { "Sí", "No" };
					int respuesta = JOptionPane.showOptionDialog(null, "¿Seguro de enviar?", "Confirmar enviar",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

					if (respuesta == JOptionPane.YES_OPTION) {
						Departamento oDepartamentoElegido;
						DepartamentoBeanRemote departamentoBean1;
						try {
							departamentoBean1 = (DepartamentoBeanRemote) InitialContext.doLookup(
									"ejb:/ProyectoPDT_Servidor/DepartamentoBean!com.serviciosProyecto.DepartamentoBeanRemote");
							oDepartamentoElegido = departamentoBean1
									.obtenerDepartamentoPorNombre(comboBoxDepartamento.getSelectedItem().toString());
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
							itrBean = (ITRBeanRemote) InitialContext
									.doLookup("ejb:/ProyectoPDT_Servidor/ITRBean!com.serviciosProyecto.ITRBeanRemote");
							String nombreItr = comboBoxItr.getSelectedItem().toString();
							oItrElegido = itrBean.obtenerItrPorNombre(nombreItr);
						} catch (NamingException e1) {
							oItrElegido = null;
						}

						UtilDateModel dateModel = (UtilDateModel) datePicker.getModel();

						String nombreAux = textFieldNombres.getText();
						String apellidoAux = textFieldApellidos.getText();
						Long documentoAux = Long.parseLong(textFieldDocumento.getText());
						Date fechaAux = dateModel.getValue();
						String correoAux = textFieldCorreoP.getText();
						String contraseniaAux = new String(passwordFieldContrasenia.getPassword());
						String telefonoAux = textFieldTelefono.getText();
						String correoInstAux = textFieldCorreoI.getText();
						Genero generoAux = (Genero) comboBoxGenero.getSelectedItem();
						String nomUsuario = correoInstAux.substring(0, correoInstAux.indexOf("@"));
						TipoUsuario tipoaux = (TipoUsuario) comboBoxTipoUsuario.getSelectedItem();

						try {
							UsuariosBeanRemote usuariosBean = (UsuariosBeanRemote) InitialContext.doLookup(
									"ejb:/ProyectoPDT_Servidor/UsuariosBean!com.serviciosProyecto.UsuariosBeanRemote");
							UsuarioEstadoBeanRemote usuEstadoBean = (UsuarioEstadoBeanRemote) InitialContext.doLookup(
									"ejb:/ProyectoPDT_Servidor/UsuarioEstadoBean!com.serviciosProyecto.UsuarioEstadoBeanRemote");
							
							UsuarioEstado sinValidar = new UsuarioEstado();
							sinValidar.setIdEstaUsuario(2);
							Usuario usuarioNuevo = new Usuario(documentoAux, apellidoAux, contraseniaAux, fechaAux,
									generoAux, correoInstAux, correoAux, nomUsuario, nombreAux, telefonoAux, tipoaux,
									// null,
									oDepartamentoElegido, oItrElegido, oLocalidadElegido, sinValidar);
							usuarioNuevo.setDepartamento(oDepartamentoElegido);
							usuarioNuevo.setLocalidade(oLocalidadElegido);
							usuarioNuevo.setItr(oItrElegido);

							
							try {
								System.out.println("El usuario de java: ");
								System.out.println(usuarioNuevo.toString());

								Usuario usuarioCreado = usuariosBean.crearUsuario(usuarioNuevo);

								if (usuarioNuevo.getTipo() == TipoUsuario.ANALISTA) {

									// Crear un nuevo Analista y asignar valores adicionales

									Analista analistaNuevo = new Analista();
									analistaNuevo.setUsuario(usuarioCreado);

									AnalistaBeanRemote analistaBean = (AnalistaBeanRemote) InitialContext.doLookup(
											"ejb:/ProyectoPDT_Servidor/AnalistaBean!com.serviciosProyecto.AnalistaBeanRemote");
									// Guardar el analista en el servicio correspondiente

									analistaBean.crearAnalista(analistaNuevo);

								} else if (usuarioNuevo.getTipo() == TipoUsuario.ESTUDIANTE) {

									// Crear un nuevo Estudiante y asignar valores adicionales
									Estudiante estudianteNuevo = new Estudiante();

									Semestre semaux = (Semestre) comboBoxSemestre.getSelectedItem();
									Generacion generacionaux = (Generacion) comboBoxGeneracion.getSelectedItem();
									estudianteNuevo.setUsuario(usuarioCreado);
									estudianteNuevo.setSemestre(semaux);
									estudianteNuevo.setGeneracion(generacionaux);

									EstudianteBeanRemote estudianteBean = (EstudianteBeanRemote) InitialContext
											.doLookup(
													"ejb:/ProyectoPDT_Servidor/EstudianteBean!com.serviciosProyecto.EstudianteBeanRemote");

									// Guardar el estudiante en el servicio correspondiente
									estudianteBean.crearEstudiante(estudianteNuevo);

								} else if (usuarioNuevo.getTipo() == TipoUsuario.TUTOR) {

									// Crear un nuevo Tutor y asignar valores adicionales
									Tutor tutorNuevo = new Tutor();

									RolTutor rTutorAux = (RolTutor) comboBoxRol.getSelectedItem();
									Area areaAux = (Area) comboBoxArea.getSelectedItem();
									tutorNuevo.setUsuario(usuarioCreado);
									tutorNuevo.setArea(areaAux);
									tutorNuevo.setTipo(rTutorAux);

									TutoresBeanRemote tutoresBean = (TutoresBeanRemote) InitialContext.doLookup(
											"ejb:/ProyectoPDT_Servidor/TutoresBean!com.serviciosProyecto.TutoresBeanRemote");

									// Guardar el Tutor en el servicio correspondiente
									tutoresBean.crearTutor(tutorNuevo);

								}

								if (usuarioCreado == null) {
									System.out.println("No se creao bien el usuario");
								}

							} catch (Exception e2) {
								System.out.println();
							}

						} catch (Exception e2) {
							System.out.println("error al crear el usuario");
						}

						JOptionPane.showMessageDialog(null,
								"Su registro será verificado por el Analista. ¡Muchas gracias!");
					}

				}

			}
		});
		btnEnviar.setBounds(552, 358, 138, 25);
		panelForm.add(btnEnviar);

		// --------------------------CANCELAR REGISTRO----------------------//

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(552, 394, 138, 25);
		panelForm.add(btnCancelar);
		
		JLabel lblNewLabel = new JLabel("* Campos Obligatorios");
		lblNewLabel.setBounds(552, 329, 150, 14);
		panelForm.add(lblNewLabel);

		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object[] opciones = { "Sí", "No" };
				int respuesta = JOptionPane.showOptionDialog(null, "Deseas cancelar el registro?",
						"Confirmar cancelación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
						opciones, opciones[0]);

				if (respuesta == JOptionPane.YES_OPTION) {
					
					// Cierra la ventana actual.
			        SwingUtilities.getWindowAncestor(btnCancelar).dispose();

					Login login = new Login(usuariosBean);
					login.mostrarVentana();
					frame.setVisible(false);
					frame.dispose();
				}

			}
		});

	}
	public static boolean validarCedula(String ced) {
        int correcto = 0;
        int[] cedula;
        int[] factor = {8, 1, 2, 3, 4, 7, 6, 0};
        cedula = new int[8];
        int suma = 0;

        for (int i = 0; i < ced.length(); i++) {
            if (Character.isDigit(ced.charAt(i))) {
                correcto++;
                cedula[i] = Integer.parseInt("" + ced.charAt(i));
                suma = suma + (cedula[i] * factor[i]);
            }
        }

        if (correcto != 8) {
            System.out.println("Debe ingresar solo números o le faltaron dígitos");
            return false;
        } else {
            int resto = suma % 10;
            if (resto == cedula[7]) {
                System.out.println("Correcto");
                return true;
            } else {
                System.out.println("No coincide el dígito verificador : " + resto + " --> Dígito ingresado :" + cedula[7]);
                return false;
            }
        }
    }
}
