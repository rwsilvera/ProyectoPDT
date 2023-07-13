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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.entitiesProyecto.EstadoItr;
import com.entitiesProyecto.Itr;
import com.entitiesProyecto.Usuario;
import com.exceptionProyecto.ServiciosException;
import com.serviciosProyecto.ITRBeanRemote;
import com.serviciosProyecto.UsuarioEstadoBeanRemote;
import com.serviciosProyecto.UsuariosBeanRemote;
import java.awt.Panel;

public class VentanaAnalista_ListadoAuxiliarITRs extends JPanel {


	private JTextField textFieldAgregarITR;
	private Object valor = null;
	private ITRBeanRemote itrBean;
	private EstadoItr estado;
	private JTable table;
	private DefaultTableModel model;
	private UsuariosBeanRemote usuariosBean;

	// -----------------------METODO PARA CAMBIAR ENTRE VENTANAS----------------------------------//
	public void cambiarContenido(JPanel panelDestino, JPanel panelFuente) {
		panelDestino.removeAll();
		panelDestino.add(panelFuente, BorderLayout.CENTER);
		panelDestino.revalidate();
		panelDestino.repaint();
	}

	public VentanaAnalista_ListadoAuxiliarITRs(UsuariosBeanRemote usuariosBean, Usuario usuario) {
		this.usuariosBean = usuariosBean;
		setLayout(null);

		try {

			itrBean = (ITRBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_Servidor/ITRBean!com.serviciosProyecto.ITRBeanRemote");

			JPanel panelMain = new JPanel();
			panelMain.setBackground(SystemColor.controlHighlight);
			panelMain.setBounds(40, 0, 779, 451);
			add(panelMain);
			panelMain.setLayout(null);

			JPanel panelForm = new JPanel();
			panelForm.setBorder(new LineBorder(new Color(0, 0, 0), 0));
			panelForm.setBackground(SystemColor.controlHighlight);
			panelForm.setBounds(21, 0, 748, 470);
			panelMain.add(panelForm);
			panelForm.setLayout(null);
	
	// ---------------------------------------TITULO------------------------------------------//

			JLabel lblListado_1 = new JLabel("Listado de ITRs");
			lblListado_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblListado_1.setFont(new Font("BIZ UDPGothic", Font.BOLD, 17));
			lblListado_1.setBounds(266, 11, 226, 20);
			panelForm.add(lblListado_1);

			JLabel lblListado_1_1 = new JLabel("Agregar un ITR");
			lblListado_1_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblListado_1_1.setFont(new Font("BIZ UDPGothic", Font.BOLD, 13));
			lblListado_1_1.setBounds(61, 59, 226, 20);
			panelForm.add(lblListado_1_1);
			
	//--------------------------TABLA PARA LISTAR ITRS----------------------------------//

			table = new JTable();

			model = new DefaultTableModel() {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}

				@Override
				public Class<?> getColumnClass(int column) {
					switch (column) {
					case 0:
						return Itr.class;
					case 1:
						return EstadoItr.class;
					default:
						return String.class;
					}
				}
			};

			model.addColumn("Lista de ITRs");
			model.addColumn("Estado de ITRs");
			table.setModel(model);
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setBounds(20, 143, 678, 238);
			panelForm.add(scrollPane);

			//--------------------------AGREGAR UN ITR----------------------------------//

			JLabel lblAgregarITR = new JLabel("Nombre del ITR");
			lblAgregarITR.setBounds(20, 90, 168, 13);
			panelForm.add(lblAgregarITR);

			textFieldAgregarITR = new JTextField();
			textFieldAgregarITR.setColumns(10);
			textFieldAgregarITR.setBounds(20, 113, 154, 20);
			panelForm.add(textFieldAgregarITR);

			//--------ESTADO DEL ITR-------//

			JLabel lblSeleccioneUnEstado = new JLabel("Estado");
			lblSeleccioneUnEstado.setBounds(184, 90, 65, 13);
			panelForm.add(lblSeleccioneUnEstado);

			JComboBox<String> comboBoxEstadoITR = new JComboBox<String>();
			comboBoxEstadoITR.setBounds(184, 114, 119, 19);
			panelForm.add(comboBoxEstadoITR);
			comboBoxEstadoITR.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Object selectedItem = comboBoxEstadoITR.getSelectedItem();
					if (selectedItem != null) {
						String estadoStr = selectedItem.toString();
						if (estadoStr.equals("ACTIVO")) {
							estado = EstadoItr.ACTIVO;
						} else {
							estado = EstadoItr.INACTIVO;
						}
					}
				}
			});
			
			comboBoxEstadoITR.setModel(new DefaultComboBoxModel<String>(new String[] { "", "ACTIVO", "INACTIVO" }));
			
			//--------------------------BOTON PARA AGREGAR---------------------------------//
			
			JButton btnAgregarItr = new JButton("Agregar");
			btnAgregarItr.setBounds(317, 112, 119, 23);
			panelForm.add(btnAgregarItr);
			btnAgregarItr.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Crear un nuevo objeto Itr
					Itr itrs = new Itr();
					// Establecer el nombre del ITR
					String auxAgregarITR = textFieldAgregarITR.getText();
					itrs.setNombre(auxAgregarITR);
					itrs.setEstado(estado);
					if (auxAgregarITR.isEmpty()) {
						// Si no se agrega un ITR en el campo de texto, muestra un mensaje de error
						JOptionPane.showMessageDialog(null, "Debes ingresar un ITR", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					} else if (comboBoxEstadoITR.getSelectedItem().equals("")
							&& comboBoxEstadoITR.getSelectedItem() != null) {
						JOptionPane.showMessageDialog(null, "Debes ingresar un estado", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					Itr itrExistente = itrBean.obtenerItrPorNombre(auxAgregarITR);
					if (itrExistente != null) {
						JOptionPane.showMessageDialog(null, "El ITR ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					// Crear el dialogo de confirmacion
					int dialogResult = JOptionPane.showConfirmDialog(null, "¿Deseas agregar el ITR?", "Advertencia",
							JOptionPane.YES_NO_OPTION);
					
					if (dialogResult == JOptionPane.YES_OPTION) {
						// Intentar agregar el Itr
						try {
							itrBean.crearItr(itrs);
							JOptionPane.showMessageDialog(null, "El ITR ha sido añadido exitosamente.", "Confirmación",
									JOptionPane.INFORMATION_MESSAGE);
							actualizarTabla();
							textFieldAgregarITR.setText("");
							comboBoxEstadoITR.setSelectedItem(null);
					
						} catch (ServiciosException e1) {
							JOptionPane.showMessageDialog(null,
									"Ocurrió un error al añadir el ITR. Por favor intenta de nuevo.", "Error",
									JOptionPane.WARNING_MESSAGE);
							// Imprimir el error si no se puede agregar
							e1.printStackTrace();
						}
					
					} else {
						JOptionPane.showMessageDialog(null, "Creación de ITR cancelado", "Cancelación exitosa",
								JOptionPane.WARNING_MESSAGE);
						actualizarTabla();
						textFieldAgregarITR.setText("");
						comboBoxEstadoITR.setSelectedItem(null);

					}
				}
			});


//--------------------------SELECCIONAR UN ITR DE LA TABLA ----------------------------------//

			table.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					// obtener la fila seleccionada
					
					int filaSeleccionada = table.getSelectedRow();
					
					// obtener el objeto Itr de la fila seleccionada
					
					valor = model.getValueAt(filaSeleccionada, 0); // Ahora es un objeto Itr
					System.out.println("Valor seleccionado: " + ((Itr) valor).getNombre() + " "
							+ ((Itr) valor).getIdItr() + " " + ((Itr) valor).getEstado());
				}
			});

//--------------------------MODIFICAR UN ITR----------------------------------//

			JButton btnModificar = new JButton("Modificar");
			btnModificar.setBounds(20, 392, 119, 23);
			panelForm.add(btnModificar);
			
			btnModificar.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	int selectedRow = table.getSelectedRow();
					if (selectedRow == -1) {
						JOptionPane.showMessageDialog(null, "Por favor, selecciona un ITR primero.");
					}
			    	// Obtener el objeto seleccionado de la lista
			        Itr itrSeleccionado = (Itr) valor;

			        // Mostrar los datos del ITR seleccionado
			        System.out.println("Nombre del ITR seleccionado: " + itrSeleccionado.getNombre());
			        System.out.println("ID del ITR seleccionado: " + itrSeleccionado.getIdItr());

			        // Mostrar un cuadro de diálogo para modificar el nombre del ITR
			        String nuevoNombre = JOptionPane.showInputDialog(null, "Introduce el nuevo nombre para el ITR", itrSeleccionado.getNombre());
			        if (nuevoNombre == null) {
			            // El usuario canceló la modificación
			            return;
			        }

			        // Mostrar un cuadro de diálogo para seleccionar el nuevo estado del ITR
			        JComboBox<String> estadoComboBox = new JComboBox<String>(new String[] { "", "ACTIVO", "INACTIVO" });
			        estadoComboBox.setSelectedItem(itrSeleccionado.getEstado().toString());
			      
			        int result = JOptionPane.showConfirmDialog(null, estadoComboBox, "Selecciona el nuevo estado para el ITR",
			                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			       
			        if (result == JOptionPane.OK_OPTION) {
			            String seleccion = (String) estadoComboBox.getSelectedItem();
			            if (seleccion != null && !seleccion.isEmpty()) {
			                EstadoItr nuevoEstado = null;
			                if (seleccion.equals("ACTIVO")) {
			                    nuevoEstado = EstadoItr.ACTIVO;
			                } else if (seleccion.equals("INACTIVO")) {
			                    nuevoEstado = EstadoItr.INACTIVO;
			                }
			                // Realizar la modificación del ITR con el nuevo nombre y estado
			                itrSeleccionado.setNombre(nuevoNombre);
			                itrSeleccionado.setEstado(nuevoEstado);

			                // Guardar los cambios en la base de datos
			                try {
			                    itrBean.actualizarItr(itrSeleccionado);
			                    JOptionPane.showMessageDialog(null, "El ITR ha sido modificado exitosamente.", "Confirmación",
			                            JOptionPane.INFORMATION_MESSAGE);
			                    actualizarTabla();
			                } catch (ServiciosException e1) {
			                    JOptionPane.showMessageDialog(null,
			                            "Ocurrió un error al modificar el ITR. Por favor intenta de nuevo.", "Error",
			                            JOptionPane.WARNING_MESSAGE);
			                    e1.printStackTrace();
			                }
			            } else {
			                JOptionPane.showMessageDialog(null, "Debes seleccionar un estado", "Error",
			                        JOptionPane.ERROR_MESSAGE);
			            }
			        } else {
			            JOptionPane.showMessageDialog(null, "Modificación de ITR cancelada", "Cancelación",
			                    JOptionPane.INFORMATION_MESSAGE);
			        }
			    }
			});



//--------------------------LISTAR LOS ITR EN LA TABLA----------------------------------//
			
			List<Itr> itrs = itrBean.obtenerItrTodos();
			for (Itr Itrs : itrs) {
				model.addRow(new Object[] { Itrs, Itrs.getEstado() });
			}
			


//--------------------------VOLVER----------------------------------//
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(579, 415, 119, 23);
		panelForm.add(btnVolver);
				
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				VentanaAnalista volver = new VentanaAnalista(usuariosBean,usuario);

				volver.setBounds(-40, 0, 769, 498);

				cambiarContenido(panelForm, volver);

				volver.setVisible(true);
				System.out.println(usuario);

			}
		});

		
	
//--------------------------METODO PARA ACTUALIZAR LA TABLA----------------------------------//
		} catch (NamingException e1) {
			e1.printStackTrace();
		}
}
	private void actualizarTabla() {
		
		model.setRowCount(0); // Limpiar la tabla
		
		List<Itr> itrs = itrBean.obtenerItrTodos(); // Obtenemos todos los ITRs
		for (Itr Itrs : itrs) {
			model.addRow(new Object[] { Itrs, Itrs.getEstado() }); // Agregamos cada ITR a la tabla
	
		}
	}
}
