package com.Estudiante;

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
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.entitiesProyecto.EstaReclamo;
import com.entitiesProyecto.Estado;
import com.entitiesProyecto.Estudiante;
import com.entitiesProyecto.Reclamo;
import com.entitiesProyecto.Usuario;
import com.entitiesProyecto.VisibilidadR;
import com.exceptionProyecto.ServiciosException;
import com.serviciosProyecto.EstaReclamoBeanRemote;
import com.serviciosProyecto.EstadoBeanRemote;
import com.serviciosProyecto.EstudianteBeanRemote;
import com.serviciosProyecto.ReclamosBeanRemote;
import com.serviciosProyecto.UsuarioEstadoBeanRemote;
import com.serviciosProyecto.UsuariosBeanRemote;
import javax.swing.SwingConstants;

public class VentanaEstudiante_ListadoDeReclamos extends JPanel {

	private UsuariosBeanRemote usuariosBean;
	private JTable table;
	private DefaultTableModel model;
	private Usuario usuario;

	public void cambiarContenido(JPanel panelDestino, JPanel panelFuente) {
		panelDestino.removeAll();
		panelDestino.add(panelFuente, BorderLayout.CENTER);
		panelDestino.revalidate();
		panelDestino.repaint();
	}
	
	public VentanaEstudiante_ListadoDeReclamos(UsuariosBeanRemote usuariosBean, Usuario usuario) {
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
		model.addColumn("ID");
		model.addColumn("Nombre Reclamo");
		model.addColumn("Estado");
		table.setModel(model);
		
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(20, 132, 678, 249);
		panelForm.add(scrollPane, BorderLayout.CENTER);

		//----------------------------FICHA DE RECLAMO-----------------------------------------//

		JButton btnFicha = new JButton("Ficha de Reclamo");
		btnFicha.setBounds(20, 392, 142, 21);
		panelForm.add(btnFicha);

		btnFicha.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Por favor, selecciona un reclamo primero.");
				}
			

				long idReclamo = (long) model.getValueAt(table.getSelectedRow(), 0);

				try {
					ReclamosBeanRemote reclamosBean = (ReclamosBeanRemote) InitialContext.doLookup(
							"ejb:/ProyectoPDT_Servidor/ReclamosBean!com.serviciosProyecto.ReclamosBeanRemote");

					Reclamo reclamo = reclamosBean.obtenerReclamo(idReclamo);

					VentanaEstudiante_ListadoDeReclamos_FichaDeReclamo ventanaFicha = new VentanaEstudiante_ListadoDeReclamos_FichaDeReclamo(usuariosBean, 
							reclamo);

					ventanaFicha.setBounds(-40, 0, 769, 498);

					cambiarContenido(panelForm, ventanaFicha);

					ventanaFicha.setVisible(true);
				} catch (NamingException e1) {
					e1.printStackTrace();
				}
			}
		});
		ReclamosBeanRemote reclamosBean;
		mostrarListaReclamosEstudiante();

		// ------------------------------BORRAR UN RECLAMO---------------------------//

		JButton btnBorrar = new JButton("Borrar Reclamo");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					int selectedRow = table.getSelectedRow();
					if (selectedRow == -1) {
						JOptionPane.showMessageDialog(null, "Por favor, selecciona un reclamo primero.");
					}
				// tomamos el id de la seleccion de la lista
				String idStringReclamo = table.getValueAt(table.getSelectedRow(), 0).toString();
				// pasamos el string a long para poder usarlo en el metodo
				long idReclamo = Long.parseLong(idStringReclamo);
				usuario.getIdUsuario();

				try {
					EstaReclamoBeanRemote estaReclamoBean = (EstaReclamoBeanRemote) InitialContext.doLookup(
							"ejb:/ProyectoPDT_Servidor/EstaReclamoBean!com.serviciosProyecto.EstaReclamoBeanRemote");
					ReclamosBeanRemote reclamosBean = (ReclamosBeanRemote) InitialContext.doLookup(
							"ejb:/ProyectoPDT_Servidor/ReclamosBean!com.serviciosProyecto.ReclamosBeanRemote");
					List<EstaReclamo> listadoestadoDelReclamo = estaReclamoBean.obtenerEstaRecTodos();
					
					EstaReclamo estadoDelReclamo = null;
					for (EstaReclamo estaReclamo : listadoestadoDelReclamo) {

						if (estaReclamo.getReclamo().getIdReclamo() == idReclamo) {
							estadoDelReclamo = estaReclamo;
							break;

						}

					}
		
					if (estadoDelReclamo.getEstado().getNombre().equals("Ingresado")) {

						int confirmDialogResult = JOptionPane.showOptionDialog(null,
							    "¿Estás seguro de que deseas borrar el reclamo?", idStringReclamo,
							    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

							if (confirmDialogResult == JOptionPane.YES_OPTION) {
							    // Código para borrar el reclamo
							    estaReclamoBean.borrarEstaRec(estadoDelReclamo.getIdEstaReclamo());
							    reclamosBean.borrarReclamo(idReclamo);
							    
							    JOptionPane.showMessageDialog(null, "El reclamo se borró exitosamente");
							    mostrarListaReclamosEstudiante();
							}

					} else {
						JOptionPane.showMessageDialog(null,
								"El estado de su reclamo tiene que estar en 'Ingresado' para ser borrado");
					}

				} catch (NamingException e1) {
					e1.printStackTrace();
					
				} catch (ServiciosException e1) {
					e1.printStackTrace();
				}

			}
		});

		btnBorrar.setBounds(312, 392, 132, 21);
		panelForm.add(btnBorrar);

//------------------------------COMBOBOX ESTADO----------------------------//
		JComboBox comboEstado = new JComboBox();
		comboEstado.setBounds(404, 333, 101, 21);
		panelForm.add(comboEstado);

		// traer estados al combobx
		try {
			EstadoBeanRemote estadoBean = (EstadoBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_Servidor/EstadoBean!com.serviciosProyecto.EstadoBeanRemote");

			List<Estado> estados = estadoBean.obtenerTodosEstado();
			
			for (Estado e : estados) {
				if (e.getVisibilidadR().equals(VisibilidadR.ACTIVO)) { 
					comboEstado.addItem(e.getNombre());
				}
			}	
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
		
//--------------------------------EDITAR RECLAMO----------------------------------------//
		JButton btnEditarReclamo = new JButton("Editar Reclamo");
		btnEditarReclamo.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = table.getSelectedRow();
		        if (selectedRow == -1) {
		            JOptionPane.showMessageDialog(null, "Por favor, selecciona un reclamo primero.");
		            return;
		        }

		        long idReclamo = (long) model.getValueAt(table.getSelectedRow(), 0);

		        try {
		            ReclamosBeanRemote reclamosBean = (ReclamosBeanRemote) InitialContext
		                    .doLookup("ejb:/ProyectoPDT_Servidor/ReclamosBean!com.serviciosProyecto.ReclamosBeanRemote");

		            Reclamo reclamo = reclamosBean.obtenerReclamo(idReclamo);


		                VentanaEstudiante_EditarReclamo ventanaEditar = new VentanaEstudiante_EditarReclamo(usuariosBean, reclamo, usuario);
		                ventanaEditar.setBounds(-40, 0, 769, 498);
		                cambiarContenido(panelForm, ventanaEditar);
		                ventanaEditar.setVisible(true);
		           
		        } catch (NamingException e1) {
		            e1.printStackTrace();
		        }
		    }
		});

		btnEditarReclamo.setBounds(172, 392, 132, 21);
		panelForm.add(btnEditarReclamo);


//--------------------------FILTRO ESTADO----------------------------------------------------//	

		JLabel lblLabelEstado = new JLabel("Estado");
		lblLabelEstado.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblLabelEstado.setBounds(20, 80, 95, 14);
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
		
		
		comboBoxEstado.setBounds(20, 99, 105, 22);
		panelForm.add(comboBoxEstado);

//----------------------------------LIMPIAR FILTRO-----------------------------//
		JButton btnLimpiarFiltro = new JButton("Limpiar Filtro");
		btnLimpiarFiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						
						mostrarListaReclamosEstudiante();
						comboBoxEstado.setSelectedItem(null);
			}
		});
		
		btnLimpiarFiltro.setBounds(146, 100, 132, 21);
		panelForm.add(btnLimpiarFiltro);
		
		
// ------------------------------VOLVER--------------------------------------///

		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(579, 415, 119, 23);
		panelForm.add(btnVolver);
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				VentanaEstudiante volver = new VentanaEstudiante(usuariosBean, usuario);

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
					model.addRow(new Object[] { estaReclamo.getReclamo().getIdReclamo(),
							estaReclamo.getReclamo().getAsunto(), estaReclamo.getEstado().getNombre() });
				}
			}
		} catch (NamingException ex) {
			ex.printStackTrace();
		}
	}

	//-------------------------------METODO PARA MOSTAR LA LISTA DE RECLAMOS-------------------------------------------//

	private void mostrarListaReclamosEstudiante() {
		try {
			EstudianteBeanRemote estudianteBean2 = (EstudianteBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_Servidor/EstudianteBean!com.serviciosProyecto.EstudianteBeanRemote");
			
			model.setRowCount(0);

			List<Estudiante> listaEstudiantes = estudianteBean2.obtenerEstudianteTodos();

			Estudiante estudianteSel = null;
			for (Estudiante estudiante : listaEstudiantes) {
				if (estudiante.getUsuario().getIdUsuario() == usuario.getIdUsuario()) {
					estudianteSel = estudiante;
					break;
				}
			}

			ReclamosBeanRemote reclamosBean = (ReclamosBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_Servidor/ReclamosBean!com.serviciosProyecto.ReclamosBeanRemote");

			EstaReclamoBeanRemote estaReclamoBean = (EstaReclamoBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_Servidor/EstaReclamoBean!com.serviciosProyecto.EstaReclamoBeanRemote");

			List<EstaReclamo> listaEstaReclamo = estaReclamoBean.obtenerEstaRecTodos();

			for (EstaReclamo estaReclamo : listaEstaReclamo) {
				if (estaReclamo.getReclamo().getEstudiante().getIdEstudiante() == estudianteSel.getIdEstudiante()) {
					model.addRow(new Object[] { estaReclamo.getReclamo().getIdReclamo(),
							estaReclamo.getReclamo().getAsunto(), estaReclamo.getEstado().getNombre()

					});
				}
			}
		} catch (NamingException ex) {
			ex.printStackTrace();
		}

	}
}
