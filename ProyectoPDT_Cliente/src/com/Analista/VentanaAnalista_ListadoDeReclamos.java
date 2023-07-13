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
import java.util.Set;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.entitiesProyecto.EstaReclamo;
import com.entitiesProyecto.Estado;
import com.entitiesProyecto.EstadoItr;
import com.entitiesProyecto.Itr;
import com.entitiesProyecto.Reclamo;
import com.entitiesProyecto.Usuario;
import com.entitiesProyecto.VisibilidadR;
import com.serviciosProyecto.EstaReclamoBeanRemote;
import com.serviciosProyecto.EstadoBeanRemote;
import com.serviciosProyecto.EstudianteBeanRemote;
import com.serviciosProyecto.ReclamosBeanRemote;
import com.serviciosProyecto.UsuarioEstadoBeanRemote;
import com.serviciosProyecto.UsuariosBeanRemote;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class VentanaAnalista_ListadoDeReclamos extends JPanel {

	private UsuariosBeanRemote usuariosBean;
	private EstudianteBeanRemote estudianteBean;
	private EstaReclamoBeanRemote estaReclamoBean;
	private JTable table;
	private DefaultTableModel model;
	private UsuarioEstadoBeanRemote usuariosEstadoBean;
	private JTextField textIdUsario;
	public static Usuario usuario; 
	
	public void cambiarContenido(JPanel panelDestino, JPanel panelFuente) {
		panelDestino.removeAll();
		panelDestino.add(panelFuente, BorderLayout.CENTER);
		panelDestino.revalidate();
		panelDestino.repaint();
	}

	public VentanaAnalista_ListadoDeReclamos(UsuariosBeanRemote usuariosBean, Usuario usuario) {
		this.usuariosBean = usuariosBean;
		this.usuario = usuario;

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

				JLabel lblListado_1 = new JLabel("Listado de Reclamos");
				lblListado_1.setHorizontalAlignment(SwingConstants.CENTER);
				lblListado_1.setFont(new Font("BIZ UDPGothic", Font.BOLD, 17));
				lblListado_1.setBounds(267, 21, 226, 20);
				panelForm.add(lblListado_1);
	
		
		// -------------------------------------TABLA LISTADO DE USUARIOS--------------------------------------//

		table = new JTable();
		model = new DefaultTableModel();
		model.addColumn("ID Reclamo");
		model.addColumn("Documento");
		model.addColumn("Nombre");
		model.addColumn("Apellido");
		model.addColumn("Asunto");
		model.addColumn("Estado");

		table.setModel(model);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(20, 132, 678, 249);
		panelForm.add(scrollPane, BorderLayout.CENTER);



	//----------------------------FICHA DE RECLAMO-----------------------------------------//
		Reclamo reclamo = null; 
		
		JButton btnFichaReclamo = new JButton("Ficha del reclamo");
		btnFichaReclamo.setBounds(20, 392, 148, 21);
		panelForm.add(btnFichaReclamo);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					int selectedRow = table.getSelectedRow();
					if (selectedRow != -1) {
						long idReclamo = (long) model.getValueAt(selectedRow, 0);

						try {
							ReclamosBeanRemote reclamosBean = (ReclamosBeanRemote) InitialContext
									.doLookup("ejb:/ProyectoPDT_Servidor/ReclamosBean!com.serviciosProyecto.ReclamosBeanRemote");

							Reclamo reclamo = reclamosBean.obtenerReclamo(idReclamo); 
							reclamosBean.actualizarReclamo(reclamo); 

							// Acción del botón btnFichaReclamo
							btnFichaReclamo.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseClicked(MouseEvent e) {
									VentanaAnalista_FichaReclamo volver = new VentanaAnalista_FichaReclamo(usuariosBean, reclamo, usuario);

									volver.setBounds(-40, 0, 769, 498);

									cambiarContenido(panelForm, volver);

									volver.setVisible(true);

								}
							});

							// Acción del botón btnFichaReclamo sin selección previa
							btnFichaReclamo.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									if (table.getSelectedRow() == -1) {
										// No se ha seleccionado ninguna fila, mostrar un mensaje o realizar alguna acción
										return;
									}

									// Obtener el valor de la primera columna de la fila seleccionada (idReclamo)
					                long idReclamo = (long) model.getValueAt(table.getSelectedRow(), 0);

					                try {
					                    ReclamosBeanRemote reclamosBean = (ReclamosBeanRemote) InitialContext
					                            .doLookup("ejb:/ProyectoPDT_Servidor/ReclamosBean!com.serviciosProyecto.ReclamosBeanRemote");

					                    Reclamo reclamo = reclamosBean.obtenerReclamo(idReclamo);

					                    VentanaAnalista_FichaReclamo ventanaFicha = new VentanaAnalista_FichaReclamo(usuariosBean, reclamo, usuario);

					                    ventanaFicha.setBounds(-40, 0, 769, 498);

					                    cambiarContenido(panelForm, ventanaFicha);

					                    ventanaFicha.setVisible(true);
					                } catch (NamingException e1) {
					                    e1.printStackTrace();
					                }
					            }
					        });
							
						} catch (NamingException e1) {
							e1.printStackTrace();
						}


					}
				}
			}
		});

		
		mostrarListaReclamosEstudiante();	

		

//--------------------------FILTRO ESTADO----------------------------------------------------//	
		
		JLabel lblLabelEstado = new JLabel("Estado");
		lblLabelEstado.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblLabelEstado.setBounds(424, 66, 95, 14);
		panelForm.add(lblLabelEstado);
		
		JComboBox comboBoxEstado = new JComboBox();
		comboBoxEstado.setSelectedItem(null);
		comboBoxEstado.addItem(" ");
		// mostramos los estados en el combo box
		try {
			EstadoBeanRemote estadoBean = (EstadoBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_Servidor/EstadoBean!com.serviciosProyecto.EstadoBeanRemote");

			List<Estado> estados = estadoBean.obtenerTodosEstado();
			
			for (Estado e : estados) {
				if (e.getVisibilidadR().equals(VisibilidadR.ACTIVO)) { 
					comboBoxEstado.addItem(e.getNombre());
				}
			}	
			
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
		
		comboBoxEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String estadoSeleccionado = comboBoxEstado.getSelectedItem().toString();

				filtrarReclamosPorEstado(estadoSeleccionado);
			}
		});
		
		
		comboBoxEstado.setBounds(424, 85, 105, 22);
		panelForm.add(comboBoxEstado);

	
//-------------------------------BUSCAR POR USUARIO-------------------------------------------//
		
		JButton btnBuscarNombreUsuario = new JButton("Buscar Usuario");
		btnBuscarNombreUsuario.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String nombreUsuario = textIdUsario.getText(); 
		        
		        if (nombreUsuario.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Por favor, ingrese un nombre de usuario.", "Campo vacío", JOptionPane.WARNING_MESSAGE);
		        } else {
		            try {
		                EstaReclamoBeanRemote estaReclamoBean = (EstaReclamoBeanRemote) InitialContext
		                        .doLookup("ejb:/ProyectoPDT_Servidor/EstaReclamoBean!com.serviciosProyecto.EstaReclamoBeanRemote");
		                List<EstaReclamo> listaEstaReclamo = estaReclamoBean.obtenerEstaRecTodos();

		                model.setRowCount(0); // Limpiar la tabla

		                boolean usuarioEncontrado = false;
		                
		                for (EstaReclamo estaReclamo : listaEstaReclamo) {
		                    long idUsuarioReclamo = estaReclamo.getReclamo().getEstudiante().getUsuario().getDocumento();
		                    String nombreUsuarioReclamo = estaReclamo.getReclamo().getEstudiante().getUsuario().getNombres();
		                    String apellidoUsuarioReclamo = estaReclamo.getReclamo().getEstudiante().getUsuario().getApellidos();
		                    
		                    if (nombreUsuarioReclamo.contains(nombreUsuario) || apellidoUsuarioReclamo.contains(nombreUsuario)) {
		                        Object[] fila = {
		                                estaReclamo.getReclamo().getIdReclamo(),
		                                estaReclamo.getReclamo().getEstudiante().getUsuario().getDocumento(),
		                                nombreUsuarioReclamo,
		                                apellidoUsuarioReclamo,
		                                estaReclamo.getReclamo().getAsunto(),
		                                estaReclamo.getEstado().getNombre()
		                        };
		                        model.addRow(fila);
		                        usuarioEncontrado = true;
		                    }
		                }

		                if (!usuarioEncontrado) {
		                    JOptionPane.showMessageDialog(null, "No se encontraron reclamos para el nombre o apellido ingresado.", "Usuario no encontrado", JOptionPane.INFORMATION_MESSAGE);
		                }

		            } catch (NamingException e1) {
		                e1.printStackTrace();
		            }
		        }
		    }
		});

		btnBuscarNombreUsuario.setBounds(204, 86, 148, 21);
		panelForm.add(btnBuscarNombreUsuario);


		textIdUsario = new JTextField();
		textIdUsario.setBounds(20, 87, 175, 20);
		panelForm.add(textIdUsario);
		textIdUsario.setColumns(10);
		
		
//----------------------------------LIMPIAR FILTRO-----------------------------//
				JButton btnLimpiarFiltro = new JButton("Limpiar Filtro");
				btnLimpiarFiltro.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
								
								mostrarListaReclamosEstudiante();
								textIdUsario.setText("");
								comboBoxEstado.setSelectedItem(null);
					}
				});
				
				btnLimpiarFiltro.setBounds(550, 86, 148, 21);
				panelForm.add(btnLimpiarFiltro);
		
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
	

	//-------------------------------METODO PARA FILTRAR RECLAMO POR ESTADO-------------------------------------------//
	
	private void filtrarReclamosPorEstado(String estado) {
		model.setRowCount(0); // Limpiar la tabla

		try {
			EstaReclamoBeanRemote estaReclamoBean = (EstaReclamoBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_Servidor/EstaReclamoBean!com.serviciosProyecto.EstaReclamoBeanRemote");

			List<EstaReclamo> listaEstaReclamo = estaReclamoBean.obtenerEstaRecTodos();

			for (EstaReclamo estaReclamo : listaEstaReclamo) {
				if (estaReclamo.getEstado().getNombre().equals(estado)) {
					Object[] fila = {estaReclamo.getReclamo().getIdReclamo(),estaReclamo.getReclamo().getEstudiante().getUsuario().getDocumento(),
							estaReclamo.getReclamo().getEstudiante().getUsuario().getNombres(),
							estaReclamo.getReclamo().getEstudiante().getUsuario().getApellidos(),estaReclamo.getReclamo().getAsunto(),
							estaReclamo.getEstado().getNombre()};
					model.addRow(fila); 
				}
			}
		} catch (NamingException ex) {
			ex.printStackTrace();
		}
	}
	

	//-------------------------------METODO PARA MOSTAR LA LISTA DE RECLAMOS-------------------------------------------//

	private void mostrarListaReclamosEstudiante() {
		try {
			EstaReclamoBeanRemote estaReclamoBean = (EstaReclamoBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_Servidor/EstaReclamoBean!com.serviciosProyecto.EstaReclamoBeanRemote");
			
			model.setRowCount(0);


			List<EstaReclamo> listaEstaReclamo = estaReclamoBean.obtenerEstaRecTodos();

			for (EstaReclamo estaReclamo : listaEstaReclamo) {
				Object[] fila = {estaReclamo.getReclamo().getIdReclamo(),estaReclamo.getReclamo().getEstudiante().getUsuario().getDocumento(),
						estaReclamo.getReclamo().getEstudiante().getUsuario().getNombres(),
						estaReclamo.getReclamo().getEstudiante().getUsuario().getApellidos(),estaReclamo.getReclamo().getAsunto(),
						estaReclamo.getEstado().getNombre()};
				model.addRow(fila); 
			}

		} catch (NamingException ex) {
			ex.printStackTrace();
		}

	}
}


