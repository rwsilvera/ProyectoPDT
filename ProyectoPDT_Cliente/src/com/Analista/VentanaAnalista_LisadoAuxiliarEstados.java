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

import com.entitiesProyecto.Estado;
import com.entitiesProyecto.VisibilidadR;
import com.entitiesProyecto.Usuario;
import com.exceptionProyecto.ServiciosException;
import com.serviciosProyecto.EstadoBeanRemote;
import com.serviciosProyecto.UsuarioEstadoBeanRemote;
import com.serviciosProyecto.UsuariosBeanRemote;

public class VentanaAnalista_LisadoAuxiliarEstados extends JPanel {

	private JTextField textFieldAgregarEstado;
	private UsuariosBeanRemote usuariosBean;
	private UsuarioEstadoBeanRemote usuariosEstadoBean;
	private EstadoBeanRemote estadoBean;
	private Usuario usuario;
	private Object valor = null;
	private JTable table;
	private DefaultTableModel model;
	private VisibilidadR visibilidadR;

//-----------------------METODO PARA CAMBIAR ENTRE VENTANAS----------------------------------//
	public void cambiarContenido(JPanel panelDestino, JPanel panelFuente) {
		panelDestino.removeAll();
		panelDestino.add(panelFuente, BorderLayout.CENTER);
		panelDestino.revalidate();
		panelDestino.repaint();
	}

	public VentanaAnalista_LisadoAuxiliarEstados(UsuariosBeanRemote usuariosBean, Usuario usuario) {
		this.usuariosBean = usuariosBean;
		this.usuario = usuario;
		try {

			estadoBean = (EstadoBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_Servidor/EstadoBean!com.serviciosProyecto.EstadoBeanRemote");

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

			JLabel lblListado_1 = new JLabel("Listado de Estados");
			lblListado_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblListado_1.setFont(new Font("BIZ UDPGothic", Font.BOLD, 17));
			lblListado_1.setBounds(266, 11, 226, 20);
			panelForm.add(lblListado_1);

			JLabel lblListado_1_1 = new JLabel("Agregar un Estado");
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
						return Estado.class;
					case 1:
						return VisibilidadR.class;
					default:
						return String.class;
					}
				}
			};

			model.addColumn("Lista de Estados");
			model.addColumn("Visibilidad");
			table.setModel(model);
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setBounds(20, 143, 678, 238);
			panelForm.add(scrollPane);

			
//--------------------------AGREGAR UN ESTADO----------------------------------//

			JLabel lblAgregarEstado = new JLabel("Nombre del Estado");
			lblAgregarEstado.setBounds(20, 90, 168, 13);
			panelForm.add(lblAgregarEstado);

			textFieldAgregarEstado = new JTextField();
			textFieldAgregarEstado.setColumns(10);
			textFieldAgregarEstado.setBounds(20, 113, 154, 20);
			panelForm.add(textFieldAgregarEstado);

			//--------VISIBILIDAD DEL ESTADO-------//

			JLabel lblSeleccioneVisibilidad = new JLabel("Visibilidad");
			lblSeleccioneVisibilidad.setBounds(184, 90, 65, 13);
			panelForm.add(lblSeleccioneVisibilidad);

			JComboBox<String> comboBoxVisibilidad = new JComboBox<String>();
			comboBoxVisibilidad.setBounds(184, 114, 119, 19);
			panelForm.add(comboBoxVisibilidad);
			comboBoxVisibilidad.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Object selectedItem = comboBoxVisibilidad.getSelectedItem();
					if (selectedItem != null) {
						String visibilidadRStr = selectedItem.toString();
						if (visibilidadRStr.equals("ACTIVO")) {
							visibilidadR = VisibilidadR.ACTIVO;
						} else {
							visibilidadR = VisibilidadR.INACTIVO;
						}
					}
				}
			});
			comboBoxVisibilidad.setModel(new DefaultComboBoxModel<String>(new String[] { "", "ACTIVO", "INACTIVO" }));

		
			//--------------------------BOTON PARA AGREGAR---------------------------------//

			JButton btnAgregarEstado = new JButton("Agregar");
			btnAgregarEstado.setBounds(313, 112, 119, 23);
			panelForm.add(btnAgregarEstado);
			btnAgregarEstado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Crear un nuevo objeto Estado
					Estado estado = new Estado();
					// Establecer el nombre del Estado
					String auxAgregarEstado = textFieldAgregarEstado.getText();
					estado.setNombre(auxAgregarEstado);
					estado.setVisibilidadR(visibilidadR);
					if (auxAgregarEstado.isEmpty()) {
						// Si no se agrega un estado en el campo de texto, muestra un mensaje de error
						JOptionPane.showMessageDialog(null, "Debes ingresar un Estado", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					} else if (comboBoxVisibilidad.getSelectedItem().equals("")
							&& comboBoxVisibilidad.getSelectedItem() != null) {
						JOptionPane.showMessageDialog(null, "Debes ingresar la visibilidad", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					Estado estadoExistente = estadoBean.obtenerEstadoPorNombre(auxAgregarEstado);
					if (estadoExistente != null) {
						JOptionPane.showMessageDialog(null, "El Estado ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					// Crear el dialogo de confirmacion
					int dialogResult = JOptionPane.showConfirmDialog(null, "¿Deseas agregar el Estado?", "Advertencia",
							JOptionPane.YES_NO_OPTION);
					if (dialogResult == JOptionPane.YES_OPTION) {
						// Intentar agregar el estado
						try {
							estadoBean.crearEstado(estado);
							JOptionPane.showMessageDialog(null, "El Estado ha sido añadido exitosamente.",
									"Confirmación", JOptionPane.INFORMATION_MESSAGE);
							actualizarTabla();
							textFieldAgregarEstado.setText("");
							comboBoxVisibilidad.setSelectedItem(null);
						} catch (ServiciosException e1) {
							JOptionPane.showMessageDialog(null,
									"Ocurrió un error al añadir el Estado. Por favor intenta de nuevo.", "Error",
									JOptionPane.WARNING_MESSAGE);
							// Imprimir el error si no se puede agregar
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Creación de Estado cancelado", "Cancelación exitosa",
								JOptionPane.WARNING_MESSAGE);
						actualizarTabla();
						textFieldAgregarEstado.setText("");
						comboBoxVisibilidad.setSelectedItem(null);

					}
				}
			});

		
	//--------------------------SELECCIONAR UN ITR DE LA TABLA ----------------------------------//
			
			table.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					// obtener la fila seleccionada
					int filaSeleccionada = table.getSelectedRow();
					// obtener el objeto estado de la fila seleccionada
					
					valor = model.getValueAt(filaSeleccionada, 0); // Ahora es un objeto estado
					System.out.println("Valor seleccionado: " + ((Estado) valor).getNombre() + " "
							+ ((Estado) valor).getIdEstado() + " " + ((Estado) valor).getVisibilidadR());
				}
			});

	//--------------------------MODIFICAR UN ITR----------------------------------//

			JButton btnModificar = new JButton("Modificar");
			btnModificar.setBounds(20, 395, 119, 23);
			panelForm.add(btnModificar);
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int selectedRow = table.getSelectedRow();
					if (selectedRow == -1) {
						JOptionPane.showMessageDialog(null, "Por favor, selecciona un estado primero.");
					}
					
					// Obtener el estado seleccionado de la tabla
					Estado estadoSeleccionado = (Estado) valor;

					// Mostrar un cuadro de diálogo para modificar los campos
					String nuevoNombre = JOptionPane.showInputDialog(null, "Introduce el nuevo nombre para el Estado",
							estadoSeleccionado.getNombre());
					if (nuevoNombre == null) {
						// El usuario canceló la modificación
						return;
					}

					// Mostrar un cuadro de diálogo para seleccionar la nueva visibilidad
					String nuevaVisibilidad = (String) JOptionPane.showInputDialog(null,
							"Selecciona la nueva visibilidad para el Estado", "Visibilidad",
							JOptionPane.QUESTION_MESSAGE, null, new String[] { "ACTIVO", "INACTIVO" },
							estadoSeleccionado.getVisibilidadR().toString());
					if (nuevaVisibilidad == null) {
						// El usuario canceló la modificación
						return;
					}

					// Actualizar el estado con los nuevos valores
					estadoSeleccionado.setNombre(nuevoNombre);
					estadoSeleccionado.setVisibilidadR(VisibilidadR.valueOf(nuevaVisibilidad));
					// Crear el dialogo de confirmacion
					int dialogResult = JOptionPane.showConfirmDialog(null, "¿Estás seguro de modificar el Estado?",
							"Advertencia", JOptionPane.YES_NO_OPTION);
					if (dialogResult == JOptionPane.YES_OPTION) {

						// Intentar actualizar el estado en la base de datos
						try {
							estadoBean.actualizarEstado(estadoSeleccionado);
							JOptionPane.showMessageDialog(null, "El Estado ha sido modificado exitosamente.",
									"Modificación exitosa", JOptionPane.INFORMATION_MESSAGE);
							actualizarTabla();

						} catch (ServiciosException e2) {
							e2.printStackTrace();
							JOptionPane.showMessageDialog(null,
									"Ocurrió un error al modificar el Estado. Por favor intenta de nuevo.", "Error",
									JOptionPane.WARNING_MESSAGE);

						}
					} else {
						JOptionPane.showMessageDialog(null, "Creación de Estado cancelado", "Cancelación exitosa",
								JOptionPane.WARNING_MESSAGE);
						actualizarTabla();
						textFieldAgregarEstado.setText("");
						comboBoxVisibilidad.setSelectedItem(null);

					}

				}
			});


//--------------------------LISTAR LOS ITR EN LA TABLA----------------------------------//

			List<Estado> estado = estadoBean.obtenerTodosEstado();
			for (Estado Estado : estado) {
				model.addRow(new Object[] { Estado, Estado.getVisibilidadR() });
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

				}
			});

		} catch (NamingException e1) {
			e1.printStackTrace();
		}
	}
	
//--------------------------METODO PARA ACTUALIZAR LA TABLA----------------------------------//

	private void actualizarTabla() {
		model.setRowCount(0); // Limpiar la tabla
		List<Estado> estado = estadoBean.obtenerTodosEstado(); // Obtenemos todos los estados
		for (Estado Estado : estado) {
			model.addRow(new Object[] { Estado, Estado.getVisibilidadR() }); // Agregamos cada estado a la tabla
		}
	}
}
