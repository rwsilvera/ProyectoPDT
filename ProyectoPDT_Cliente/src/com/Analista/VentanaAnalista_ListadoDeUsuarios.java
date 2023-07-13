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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.entitiesProyecto.Analista;
import com.entitiesProyecto.EstadoItr;
import com.entitiesProyecto.Estudiante;
import com.entitiesProyecto.Generacion;
import com.entitiesProyecto.Itr;
import com.entitiesProyecto.TipoUsuario;
import com.entitiesProyecto.Tutor;
import com.entitiesProyecto.Usuario;
import com.entitiesProyecto.UsuarioEstado;
import com.serviciosProyecto.AnalistaBeanRemote;
import com.serviciosProyecto.EstudianteBeanRemote;
import com.serviciosProyecto.ITRBeanRemote;
import com.serviciosProyecto.TutoresBeanRemote;
import com.serviciosProyecto.UsuarioEstadoBeanRemote;
import com.serviciosProyecto.UsuariosBeanRemote;
import javax.swing.JTextField;

public class VentanaAnalista_ListadoDeUsuarios extends JPanel {

	private UsuariosBeanRemote usuariosBean;

	private JTable table;
	private DefaultTableModel model;
	private JComboBox<TipoUsuario> comboBoxTipoUsuario;
	private JComboBox<String> comboBoxEstado;
	private JComboBox<Generacion> comboBoxGeneracion;
	private JComboBox<String> comboBoxItr;
	private Usuario p1;
	private JTextField textField;

	public void cambiarContenido(JPanel panelDestino, JPanel panelFuente) {
		panelDestino.removeAll();
		panelDestino.add(panelFuente, BorderLayout.CENTER);
		panelDestino.revalidate();
		panelDestino.repaint();
	}

	public VentanaAnalista_ListadoDeUsuarios(UsuariosBeanRemote usuariosBean, Usuario usuario) {
		this.usuariosBean = usuariosBean;
		// this.usuario = usuario;

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
		
// ---------------------------------------TITULO------------------------------------------//

		JLabel lblListado_1 = new JLabel("Listado de Usuarios");
		lblListado_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblListado_1.setFont(new Font("BIZ UDPGothic", Font.BOLD, 17));
		lblListado_1.setBounds(267, 0, 226, 20);
		panelForm.add(lblListado_1);

// ---------------------------TABLA LISTADO DE USUARIOS----------------------------------//

		table = new JTable();
		model = new DefaultTableModel();
		model.addColumn("Nombre");
		model.addColumn("Apellido");
		model.addColumn("Correo");
		model.addColumn("Tipo");
		model.addColumn("Itr");
		model.addColumn("Estado");
		model.addColumn("Generacion");

		table.setModel(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(20, 143, 678, 238);
		panelForm.add(scrollPane);


// -------------------------------------FILTRO ITR--------------------------------------//

		JLabel lblLabelITR = new JLabel("ITR");
		lblLabelITR.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblLabelITR.setBounds(155, 85, 46, 14);
		panelForm.add(lblLabelITR);

		comboBoxItr = new JComboBox<>();
		comboBoxItr.setToolTipText("ITR");
		comboBoxItr.setSelectedItem(null);
		comboBoxItr.setBounds(152, 110, 122, 22);
		panelForm.add(comboBoxItr);
		comboBoxItr.addItem("");

		// agregar los datos de la itr desde la base de datos
		ITRBeanRemote itrBean;
		try {
			itrBean = (ITRBeanRemote) InitialContext
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
		comboBoxItr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String itrSeleccionado = (String) comboBoxItr.getSelectedItem();

				if (itrSeleccionado != null && !itrSeleccionado.isEmpty()) {
					try {
						ITRBeanRemote itrBean = (ITRBeanRemote) InitialContext
								.doLookup("ejb:/ProyectoPDT_Servidor/ITRBean!com.serviciosProyecto.ITRBeanRemote");
						UsuariosBeanRemote usuariosBean = (UsuariosBeanRemote) InitialContext.doLookup(
								"ejb:/ProyectoPDT_Servidor/UsuariosBean!com.serviciosProyecto.UsuariosBeanRemote");

						EstudianteBeanRemote estudianteBean = (EstudianteBeanRemote) InitialContext.doLookup(
								"ejb:/ProyectoPDT_Servidor/EstudianteBean!com.serviciosProyecto.EstudianteBeanRemote");

						Itr itr = itrBean.obtenerItrPorNombre(itrSeleccionado);

						if (itr != null) {
							model.setRowCount(0);
							List<Usuario> usuarios = usuariosBean.obtenerUsuariosPorItr(itr);

							for (Usuario usuario : usuarios) {
								String estadoUsuario = usuario.getUsuaEstado().getNombre();
								String itrUsuario = usuario.getItr().getNombre();
								String estado = usuario.getUsuaEstado().getNombre();

								if (usuario.getTipo() == TipoUsuario.ESTUDIANTE) {
									// Obtener la información adicional específica del estudiante
									Estudiante estudiante1 = estudianteBean.obtenerEstudiantePorUsuario(usuario);

									// Acceder a la generación del estudiante
									Generacion generacion = estudiante1.getGeneracion();

									model.addRow(new Object[] { usuario.getNombres(), usuario.getApellidos(),
											usuario.getMail(), usuario.getTipo(), itrUsuario, estadoUsuario,
											generacion });
								} else {
									model.addRow(new Object[] { usuario.getNombres(), usuario.getApellidos(),
											usuario.getMail(), usuario.getTipo(), itrUsuario, estadoUsuario });
								}
							}
						}
					} catch (NamingException ex) {
						ex.printStackTrace();
					}
				}
			}
		});

// -------------------------------------FILTRO PARA TIPO DE USUARIO--------------------------------------//

		JLabel lblLabelRol = new JLabel("Rol");
		lblLabelRol.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblLabelRol.setBounds(284, 85, 46, 14);
		panelForm.add(lblLabelRol);

		comboBoxTipoUsuario = new JComboBox<>(TipoUsuario.values()); //
		comboBoxTipoUsuario.setToolTipText("Rol");
		comboBoxTipoUsuario.setBounds(284, 110, 122, 22);
		comboBoxTipoUsuario.setSelectedItem(null);
		panelForm.add(comboBoxTipoUsuario);

		comboBoxTipoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TipoUsuario tipoSeleccionado = (TipoUsuario) comboBoxTipoUsuario.getSelectedItem();

				if (tipoSeleccionado != null) {
					try {
						UsuariosBeanRemote usuariosBean = (UsuariosBeanRemote) InitialContext.doLookup(
								"ejb:/ProyectoPDT_Servidor/UsuariosBean!com.serviciosProyecto.UsuariosBeanRemote");
						EstudianteBeanRemote estudianteBean = (EstudianteBeanRemote) InitialContext.doLookup(
								"ejb:/ProyectoPDT_Servidor/EstudianteBean!com.serviciosProyecto.EstudianteBeanRemote");

						List<Usuario> usuarios = usuariosBean.obtenerUsuariosPorTipo(tipoSeleccionado);

						model.setRowCount(0); // Limpiar el modelo de la tabla

						// Agregar los usuarios filtrados al modelo de la tabla
						for (Usuario usuario : usuarios) {
							String estadoUsuario = usuario.getUsuaEstado().getNombre();
							String itrUsuario = usuario.getItr().getNombre();
							String estado = usuario.getUsuaEstado().getNombre();

							if (usuario.getTipo() == TipoUsuario.ESTUDIANTE) {
								// Obtener la información adicional específica del estudiante
								Estudiante estudiante1 = estudianteBean.obtenerEstudiantePorUsuario(usuario);

								// Acceder a la generación del estudiante
								Generacion generacion = estudiante1.getGeneracion();

								model.addRow(new Object[] { usuario.getNombres(), usuario.getApellidos(),
										usuario.getMail(), usuario.getTipo(), itrUsuario, estadoUsuario, generacion });
							} else {
								model.addRow(new Object[] { usuario.getNombres(), usuario.getApellidos(),
										usuario.getMail(), usuario.getTipo(), itrUsuario, estadoUsuario });
							}
						}
					}

					catch (NamingException ex) {
						ex.printStackTrace();
					}
				}
			}
		});

// -------------------------------------FILTRO ESTADO DE USUARIO--------------------------------------//

		JLabel lblLabelEstado = new JLabel("Estado del Usuario");
		lblLabelEstado.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblLabelEstado.setBounds(20, 88, 95, 14);
		panelForm.add(lblLabelEstado);

		comboBoxEstado = new JComboBox<>();
		comboBoxEstado.setSelectedItem(null);
		comboBoxEstado.setToolTipText("Estado");
		comboBoxEstado.setBounds(20, 110, 122, 22);
		panelForm.add(comboBoxEstado);
		comboBoxEstado.addItem("");

		UsuarioEstadoBeanRemote usuarioEstadoBean;
		try {
			usuarioEstadoBean = (UsuarioEstadoBeanRemote) InitialContext.doLookup(
					"ejb:/ProyectoPDT_Servidor/UsuarioEstadoBean!com.serviciosProyecto.UsuarioEstadoBeanRemote");

			List<UsuarioEstado> usE = usuarioEstadoBean.obtenerUEstadoTodos();

			for (UsuarioEstado estado : usE) {
				comboBoxEstado.addItem(estado.getNombre());
			}
		} catch (NamingException e1) {
			e1.printStackTrace();
		}

		comboBoxEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String estadoSeleccionado = (String) comboBoxEstado.getSelectedItem();

				if (!estadoSeleccionado.isEmpty()) {
					try {
						UsuarioEstadoBeanRemote usuarioEstadoBean = (UsuarioEstadoBeanRemote) InitialContext.doLookup(
								"ejb:/ProyectoPDT_Servidor/UsuarioEstadoBean!com.serviciosProyecto.UsuarioEstadoBeanRemote");
						UsuariosBeanRemote usuariosBean = (UsuariosBeanRemote) InitialContext.doLookup(
								"ejb:/ProyectoPDT_Servidor/UsuariosBean!com.serviciosProyecto.UsuariosBeanRemote");
						EstudianteBeanRemote estudianteBean = (EstudianteBeanRemote) InitialContext.doLookup(
								"ejb:/ProyectoPDT_Servidor/EstudianteBean!com.serviciosProyecto.EstudianteBeanRemote");

						UsuarioEstado estado = usuarioEstadoBean.obtenerEstadoPorNombre(estadoSeleccionado);

						if (estado != null) {
							model.setRowCount(0);

							List<Usuario> usuarios = usuariosBean.obtenerUsuariosPorEstado(estado);

							for (Usuario usuario : usuarios) {
								String estadoUsuario = usuario.getUsuaEstado().getNombre();
								String itrUsuario = usuario.getItr().getNombre();
								String estado1 = usuario.getUsuaEstado().getNombre();

								if (usuario.getTipo() == TipoUsuario.ESTUDIANTE) {
									// Obtener la información adicional específica del estudiante
									Estudiante estudiante1 = estudianteBean.obtenerEstudiantePorUsuario(usuario);

									// Acceder a la generación del estudiante
									Generacion generacion = estudiante1.getGeneracion();

									model.addRow(new Object[] { usuario.getNombres(), usuario.getApellidos(),
											usuario.getMail(), usuario.getTipo(), itrUsuario, estadoUsuario,
											generacion });
								} else {
									model.addRow(new Object[] { usuario.getNombres(), usuario.getApellidos(),
											usuario.getMail(), usuario.getTipo(), itrUsuario, estadoUsuario });
								}
							}
						}
					} catch (NamingException ex) {
						ex.printStackTrace();
					}
				}
			}
		});

// -------------------------------------BUSCAR POR NOMBRE-------------------------------------//

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(20, 44, 155, 23);
		panelForm.add(textField);
		
		JButton btnBuscarNombreUsuario = new JButton("Buscar");
		btnBuscarNombreUsuario.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String nombreUsuario = textField.getText().trim();
		        buscarUsuarioPorNombre(nombreUsuario);
		    }
		});
		btnBuscarNombreUsuario.setBounds(185, 44, 122, 23);
		panelForm.add(btnBuscarNombreUsuario);
		
// -------------------------------------FILTRO GENERACION-------------------------------------//

		JLabel lblLabeGeneracion = new JLabel("Generación");
		lblLabeGeneracion.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblLabeGeneracion.setBounds(416, 85, 102, 14);
		panelForm.add(lblLabeGeneracion);

		comboBoxGeneracion = new JComboBox<>(Generacion.values());//
		comboBoxGeneracion.setToolTipText("Generacion");
		comboBoxGeneracion.setSelectedItem(null); 
		comboBoxGeneracion.setBounds(416, 110, 122, 22);
		panelForm.add(comboBoxGeneracion);

		comboBoxGeneracion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Generacion generacionSeleccionada = (Generacion) comboBoxGeneracion.getSelectedItem();

				if (generacionSeleccionada != null) {
					try {
						EstudianteBeanRemote estudianteBean = (EstudianteBeanRemote) InitialContext.doLookup(
								"ejb:/ProyectoPDT_Servidor/EstudianteBean!com.serviciosProyecto.EstudianteBeanRemote");

						// Obtener los estudiantes de la generación seleccionada
						List<Estudiante> estudiantes = estudianteBean
								.obtenerEstudiantePorGeneracion(generacionSeleccionada);

						// Limpiar el modelo de la tabla
						model.setRowCount(0);

						for (Estudiante estudiante : estudiantes) {
							Usuario usuario = estudiante.getUsuario();
							String estadoUsuario = usuario.getUsuaEstado().getNombre();
							String itrUsuario = usuario.getItr().getNombre();
							String estado = usuario.getUsuaEstado().getNombre();
							Generacion generacion = estudiante.getGeneracion();

							model.addRow(new Object[] { usuario.getNombres(), usuario.getApellidos(), usuario.getMail(),
									usuario.getTipo(), itrUsuario, estadoUsuario, generacion });
						}
					} catch (NamingException ex) {
						ex.printStackTrace();
					}
				}
			}
		});


// -------------------------------------LIMPIAR FILTROS-------------------------------------//

		JButton btnLimpiarFiltro = new JButton("Limpiar Filtro");
		btnLimpiarFiltro.setBounds(566, 109, 132, 23);
		panelForm.add(btnLimpiarFiltro);
		btnLimpiarFiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarFiltros();
			}
		});

// -------------------------------------USUSARIO SELECCIONADO DE LA TABLA--------------------------------------//

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					int selectedRow = table.getSelectedRow();
					if (selectedRow != -1) {
						String correoUsuario = (String) model.getValueAt(selectedRow, 2);

						try {
							UsuariosBeanRemote usuariosBean = (UsuariosBeanRemote) InitialContext.doLookup(
									"ejb:/ProyectoPDT_Servidor/UsuariosBean!com.serviciosProyecto.UsuariosBeanRemote");

							p1 = usuariosBean.obtenerUsuarioDesdeBaseDeDatos(correoUsuario);

							usuariosBean.actualizarUsuario(p1);

						} catch (NamingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				}
			}
		});

// -------------------------------------IR A LA FICHA DE USUARIO-------------------------------------//

		JButton btnFicha = new JButton("Ficha Usuario");
		btnFicha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Por favor, selecciona un usuario primero.");
				}
			}
		});
		btnFicha.setBounds(161, 392, 132, 23);
		panelForm.add(btnFicha);

		btnFicha.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				VentanaAnalista_ListadoDeUsuarios_FichaDeUsuario ficha = new VentanaAnalista_ListadoDeUsuarios_FichaDeUsuario(usuariosBean,
						p1);

				ficha.setBounds(-40, 0, 769, 498);

				cambiarContenido(panelForm, ficha);

				ficha.setVisible(true);

			}
		});

		mostrarListaCompletaUsuarios();

// ------------------------------REPORTES ESCOLARIDAD------------------------------------//

		JButton btnReportes = new JButton("Reportes");
		btnReportes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Por favor, selecciona un usuario primero.");
				}
			}
		});
		btnReportes.setBounds(20, 392, 122, 23);
		panelForm.add(btnReportes);
		btnReportes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				VentanaAnalista_Reportes reportes = new VentanaAnalista_Reportes(usuariosBean, p1);

				reportes.setBounds(-40, 0, 769, 498);

				cambiarContenido(panelForm, reportes);

				reportes.setVisible(true);

			}
		});

// -------------------------------------VOLVER--------------------------------------//

		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(579, 415, 119, 23);
		panelForm.add(btnVolver);
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				VentanaAnalista volver = new VentanaAnalista(usuariosBean, VentanaAnalista.getUsuario());

				volver.setBounds(-40, 0, 769, 498);

				cambiarContenido(panelForm, volver);

				volver.setVisible(true);

			}
		});
	}
	
	//---------------------------------------BUSCAR USUARIO POR NOMBRE---------------------------------------//
	
	private void buscarUsuarioPorNombre(String nombre) {
		try {
			UsuariosBeanRemote usuariosBean = (UsuariosBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_Servidor/UsuariosBean!com.serviciosProyecto.UsuariosBeanRemote");
			EstudianteBeanRemote estudianteBean = (EstudianteBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_Servidor/EstudianteBean!com.serviciosProyecto.EstudianteBeanRemote");
			if (nombre.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese un nombre de usuario.", "Campo vacío",
						JOptionPane.WARNING_MESSAGE);
			} else {

				// Obtener la lista de usuarios
				List<Usuario> usuarios = usuariosBean.obtenerTodosLosUsuarios();

				// Limpiar el modelo de la tabla
				model.setRowCount(0);

				// Buscar usuarios que coincidan con el nombre o apellido
				for (Usuario usuario : usuarios) {
					if (usuario.getNombres().toLowerCase().contains(nombre.toLowerCase())
							|| usuario.getApellidos().toLowerCase().contains(nombre.toLowerCase())) {
						String estadoUsuario = usuario.getUsuaEstado().getNombre();
						String itrUsuario = usuario.getItr().getNombre();

						if (usuario.getTipo() == TipoUsuario.ESTUDIANTE) {
							// Obtener la información adicional específica del estudiante
							Estudiante estudiante = estudianteBean.obtenerEstudiantePorUsuario(usuario);
							Generacion generacion = estudiante.getGeneracion();
							model.addRow(new Object[] { usuario.getNombres(), usuario.getApellidos(), usuario.getMail(),
									usuario.getTipo(), itrUsuario, estadoUsuario, generacion });
						} else {
							model.addRow(new Object[] { usuario.getNombres(), usuario.getApellidos(), usuario.getMail(),
									usuario.getTipo(), itrUsuario, estadoUsuario });
						}
					}
				}
			}
		} catch (NamingException ex) {
			ex.printStackTrace();
		}

	}

// -------------------------------------METODO PARA BORRAR FILTROS--------------------------------------//

	private void borrarFiltros() {
		comboBoxEstado.setSelectedIndex(0);

		comboBoxTipoUsuario.setSelectedItem(null);

		comboBoxGeneracion.setSelectedItem(null);

		comboBoxItr.setSelectedIndex(0);
		
		textField.setText("");


		// Mostrar la lista completa de usuarios nuevamente
		mostrarListaCompletaUsuarios();
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					int selectedRow = table.getSelectedRow();
					if (selectedRow != -1) {
						// Obtener el correo del usuario seleccionado
						String correoUsuario = (String) model.getValueAt(selectedRow, 2);

						try {
							UsuariosBeanRemote usuariosBean = (UsuariosBeanRemote) InitialContext.doLookup(
									"ejb:/ProyectoPDT_Servidor/UsuariosBean!com.serviciosProyecto.UsuariosBeanRemote");

							p1 = usuariosBean.obtenerUsuarioDesdeBaseDeDatos(correoUsuario);

							usuariosBean.actualizarUsuario(p1);

						} catch (NamingException e1) {
							e1.printStackTrace();
						}

					}
				}
			}
		});
		mostrarListaCompletaUsuarios();

	}

// -------------------------------------METODO PARA MOSTRAR LA LISTA COMPLETA DE USUARIOS--------------------------------------//

	private void mostrarListaCompletaUsuarios() {
		try {
			UsuariosBeanRemote usuariosBean = (UsuariosBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_Servidor/UsuariosBean!com.serviciosProyecto.UsuariosBeanRemote");
			EstudianteBeanRemote estudianteBean = (EstudianteBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_Servidor/EstudianteBean!com.serviciosProyecto.EstudianteBeanRemote");

			List<Usuario> usuarios = usuariosBean.obtenerTodosLosUsuarios();

			// Limpiar el modelo de la tabla
			model.setRowCount(0);

			// Agregar los usuarios al modelo de la tabla
			for (Usuario usuario : usuarios) {
				String estadoUsuario = usuario.getUsuaEstado().getNombre();
				String itrUsuario = usuario.getItr().getNombre();
				String estado = usuario.getUsuaEstado().getNombre();

				if (usuario.getTipo() == TipoUsuario.ESTUDIANTE) {
					// Obtener la información adicional específica del estudiante
					Estudiante estudiante1 = estudianteBean.obtenerEstudiantePorUsuario(usuario);

					// Acceder a la generación del estudiante
					Generacion generacion = estudiante1.getGeneracion();

					model.addRow(new Object[] { usuario.getNombres(), usuario.getApellidos(), usuario.getMail(),
							usuario.getTipo(), itrUsuario, estadoUsuario, generacion });
				} else {
					model.addRow(new Object[] { usuario.getNombres(), usuario.getApellidos(), usuario.getMail(),
							usuario.getTipo(), itrUsuario, estadoUsuario });
				}
			}
		} catch (NamingException ex) {
			ex.printStackTrace();
		}

	}
}
