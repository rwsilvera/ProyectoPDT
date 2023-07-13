package com.Estudiante;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.entitiesProyecto.EstaReclamo;
import com.entitiesProyecto.Estado;
import com.entitiesProyecto.Estudiante;
import com.entitiesProyecto.Reclamo;
import com.entitiesProyecto.Semestre;
import com.entitiesProyecto.TipoActividad;
import com.entitiesProyecto.Tutor;
import com.entitiesProyecto.Usuario;
import com.exceptionProyecto.ServiciosException;
import com.serviciosProyecto.EstaReclamoBeanRemote;
import com.serviciosProyecto.EstadoBeanRemote;
import com.serviciosProyecto.EstudianteBeanRemote;
import com.serviciosProyecto.ReclamosBeanRemote;
import com.serviciosProyecto.TutoresBeanRemote;
import com.serviciosProyecto.UsuariosBeanRemote;

import net.sourceforge.jdatepicker.DateModel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class VentanaEstudiante_Reportes_NuevoReclamo extends JPanel {


	private Usuario usuario;
	private JTextField textTituloDeReclamo;
	private JTextField textNombreEvento;
	private JTextField textCreditos;
	private List<Tutor>listaTutor;
	private UsuariosBeanRemote usuariosBean;
	public void cambiarContenido(JPanel panelDestino, JPanel panelFuente) {
		panelDestino.removeAll();
		panelDestino.add(panelFuente, BorderLayout.CENTER);
		panelDestino.revalidate();
		panelDestino.repaint();
	}
	
	public VentanaEstudiante_Reportes_NuevoReclamo(UsuariosBeanRemote usuariosBean, Usuario usuario) {
		this.usuariosBean = usuariosBean;
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

		JLabel lblReporte = new JLabel("Reclamo");
		lblReporte.setHorizontalAlignment(SwingConstants.CENTER);
		lblReporte.setFont(new Font("BIZ UDPGothic", Font.BOLD, 17));
		lblReporte.setBounds(266, 11, 226, 20);
		panelForm.add(lblReporte);
		
		//--------------------------------TITULO RECLAMO------------------------------------//	

		JLabel lblNewLabel = new JLabel("Titulo del Reclamo");
		lblNewLabel.setBounds(21, 72, 135, 21);
		panelForm.add(lblNewLabel);
		
		textTituloDeReclamo = new JTextField();
		textTituloDeReclamo.setBounds(21, 97, 140, 20);
		panelForm.add(textTituloDeReclamo);
		textTituloDeReclamo.setColumns(10);


		//--------------------------------DESCRIPCION---------------------------------------//	

		JLabel lblDescripcionDelReclamo = new JLabel("Descripcion del Reclamo");
		lblDescripcionDelReclamo.setBounds(21, 127, 216, 21);
		panelForm.add(lblDescripcionDelReclamo);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true); // Activa el ajuste de línea automático
		textArea.setWrapStyleWord(true); // Ajusta las palabras completas

		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(21, 156, 216, 111);
		panelForm.add(scrollPane);
		

		
		//--------------------------------TIPO DE EVENTO------------------------------------//	

		JLabel lblTipoDeEvento = new JLabel("Tipo de Actividad");
		lblTipoDeEvento.setBounds(21, 273, 140, 21);
		panelForm.add(lblTipoDeEvento);

		JComboBox<String> comboBoxTipoEvento = new JComboBox<>();
		comboBoxTipoEvento.addItem("");
		for (TipoActividad tipo : TipoActividad.values()) {
			comboBoxTipoEvento.addItem(tipo.name());
		}
		comboBoxTipoEvento.setBounds(21, 301, 134, 22);
		panelForm.add(comboBoxTipoEvento);

		//------------------------------NOMBRE DE LA ACTIVIDAD---------------------------//

		JLabel lblNombreDelEvento = new JLabel("Nombre de la Actividad");
		lblNombreDelEvento.setBounds(291, 72, 140, 21);
		panelForm.add(lblNombreDelEvento);
		
		textNombreEvento = new JTextField();
		textNombreEvento.setColumns(10);
		textNombreEvento.setBounds(291, 97, 140, 20);
		panelForm.add(textNombreEvento);

		
		//--------------------------------SEMESTRE--------------------------------------//
		JComboBox comboBoxSemestre = new JComboBox();
		comboBoxSemestre.addItem("");

		for (Semestre semestre : Semestre.values()) {
			comboBoxSemestre.addItem(semestre.name());
		}
		comboBoxSemestre.setBounds(291, 167, 134, 22);
		panelForm.add(comboBoxSemestre);

		JLabel lblSemestre = new JLabel("Semestre");
		lblSemestre.setBounds(291, 139, 140, 21);
		panelForm.add(lblSemestre);

		//-----------------------------FECHA-------------------------------------------//
		
		JDatePanelImpl datePanelImpl = new JDatePanelImpl((DateModel) null);
		datePanelImpl.setBounds(475, 101, 200, 180);
		panelForm.add(datePanelImpl);

		JLabel lblFechaDelReclamo = new JLabel("Fecha de la Actividad");
		lblFechaDelReclamo.setBounds(516, 71, 159, 21);
		panelForm.add(lblFechaDelReclamo);

		JLabel lblDocente = new JLabel("Docente");
		lblDocente.setBounds(291, 211, 140, 21);
		panelForm.add(lblDocente);
		
		//---------------------------DOCENTE------------------------------//
		
		JComboBox comboBoxDocente = new JComboBox();
		comboBoxDocente.addItem("");

		try {
			TutoresBeanRemote tutoresBean = (TutoresBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_Servidor/TutoresBean!com.serviciosProyecto.TutoresBeanRemote");

			listaTutor = tutoresBean.obtenerTodosLosTutores();

			for (Tutor tutor : listaTutor) {
				// con el comando addItem metemos los departamentos en el combobox
				comboBoxDocente.addItem(tutor.getUsuario().getNombres()+ " " + tutor.getUsuario().getApellidos());
			}

		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		comboBoxDocente.setBounds(291, 239, 134, 22);
		panelForm.add(comboBoxDocente);

		//---------------------------------CREDITOS--------------------------//
		
		JLabel lblCreditos = new JLabel("Creditos");
		lblCreditos.setBounds(291, 277, 140, 21);
		panelForm.add(lblCreditos);

		textCreditos = new JTextField();
		textCreditos.setBounds(291, 303, 62, 20);
		panelForm.add(textCreditos);
		textCreditos.setColumns(10);
	
		//----------------------------------CREAR RECLAMO----------------------//
		
		JButton btnCrearReclamo = new JButton("Enviar");
		btnCrearReclamo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tituloReclamo = textTituloDeReclamo.getText();
				String descripcionReclamo = textArea.getText();
				String nombreEvento = textNombreEvento.getText();
				String creditos = textCreditos.getText();
				String tipoEvento = (String) comboBoxTipoEvento.getSelectedItem();
				String semestre = (String) comboBoxSemestre.getSelectedItem();
				String docente = (String) comboBoxDocente.getSelectedItem();

				String campoFaltante = "";

				if (tituloReclamo.isEmpty()) {
					campoFaltante = "Título del Reclamo";
				} else if (descripcionReclamo.isEmpty()) {
					campoFaltante = "Descripción del Reclamo";
				} else if (nombreEvento.isEmpty()) {
					campoFaltante = "Nombre del Evento";
				} else if (creditos.isEmpty()) {
					campoFaltante = "Créditos";
				} else if (tipoEvento.isEmpty()) {
					campoFaltante = "Tipo de Evento";
				} else if (semestre.isEmpty()) {
					campoFaltante = "Semestre";
				} else if (docente.isEmpty()) {
					campoFaltante = "Docente";
				} else if (datePanelImpl.getModel().getValue() == null) {
		            campoFaltante = "Fecha del Evento";
		        }else if (tituloReclamo.length() < 3 || tituloReclamo.length() > 20) {
		            campoFaltante = "Título del Reclamo debe tener entre 3 y 20 caracteres";
		        } else if (nombreEvento.length() < 3 || nombreEvento.length() > 20) {
		            campoFaltante = "Nombre del Evento debe tener entre 3 y 20 caracteres";
		        }else if (descripcionReclamo.length() < 20 || descripcionReclamo.length() > 255) {
		            campoFaltante = "Descripción del evento debe tener entre 20 y 255 caracteres";
		        } else {
		            // Validacion que de tiene que ser numero del 0 al 99
		            if (!creditos.matches("^(?:[0-9]|[1-9][0-9])$")) {
		                campoFaltante = "Créditos inválidos deben ser números del 0 al 99";
		            }
		        }

				// Verificar si algún campo está vacío
				if (!campoFaltante.isEmpty()) {
					String mensaje = "El campo '" + campoFaltante + "' no ha sido completado.";
					JOptionPane.showMessageDialog(null, mensaje, "Campo faltante", JOptionPane.ERROR_MESSAGE);
				} 
				else {

					try {
						EstudianteBeanRemote estudianteBean = (EstudianteBeanRemote) InitialContext
								.doLookup("ejb:/ProyectoPDT_Servidor/EstudianteBean!com.serviciosProyecto.EstudianteBeanRemote");
						ReclamosBeanRemote reclamosBean = (ReclamosBeanRemote) InitialContext
								.doLookup("ejb:/ProyectoPDT_Servidor/ReclamosBean!com.serviciosProyecto.ReclamosBeanRemote");
						EstadoBeanRemote estadoBean = (EstadoBeanRemote) InitialContext
								.doLookup("ejb:/ProyectoPDT_Servidor/EstadoBean!com.serviciosProyecto.EstadoBeanRemote");
						EstaReclamoBeanRemote estaReclamoBean = (EstaReclamoBeanRemote) InitialContext
								.doLookup("ejb:/ProyectoPDT_Servidor/EstaReclamoBean!com.serviciosProyecto.EstaReclamoBeanRemote");
						
						List<Estudiante>listaEstudiantes = estudianteBean.obtenerEstudianteTodos();
						// Del usario logeado sacamos el estudiante como entidad
						Estudiante estudianteSel = null;
						for (Estudiante estudiante : listaEstudiantes) {
							if (estudiante.getUsuario().getIdUsuario()==usuario.getIdUsuario()) {
								estudianteSel = estudiante;
								break;
							}

						}

						Reclamo reclamo = new Reclamo();
						// Agregamos el titulo del Reclamo
						reclamo.setAsunto(textTituloDeReclamo.getText());
						//Agregamos la descripcion del Reclamo
						reclamo.setDetalle(textArea.getText());
						// en fechaActual creamos una variable que contiene el dia que se crea el reclamo
						Date fechaActual = new Date();
						reclamo.setFecha(fechaActual);
						// y con timestamp guardamos la hora del reclamo
						Timestamp timestamp = new Timestamp(fechaActual.getTime());
						reclamo.setHora(timestamp);

						//Agregamos el estudiante al reclamo
						reclamo.setEstudiante(estudianteSel);
						// agregamos el tipo de actividad
						TipoActividad tipoActividad = TipoActividad.valueOf((String) comboBoxTipoEvento.getSelectedItem());
						reclamo.setTipoActividad(tipoActividad);
						//Agregamos el semestre
						Semestre tipoSemestre = Semestre.valueOf((String) comboBoxSemestre.getSelectedItem());
						reclamo.setSemestre(tipoSemestre);
						// Agregamos los creditos
						reclamo.setCredito(textCreditos.getText());
						//Agregamos reclamos por nombre del evento
						reclamo.setNombre(textNombreEvento.getText());

						//Agregamos al tutor
						int selectedIndex = comboBoxDocente.getSelectedIndex();

						Tutor tutorSeleccionado = listaTutor.get(selectedIndex -1);
						reclamo.setTutore(tutorSeleccionado);


						// Agregamos la fecha selecionada  del evento
						DateModel<?> dateModel = datePanelImpl.getModel();
						Calendar selectedCalendar = (Calendar) dateModel.getValue();
						Date selectedDate = selectedCalendar.getTime();
						reclamo.setFechaActividad(selectedDate);
						
						
						//Creamos el nuevo reclamo y lo alamcenamos en la variable nuevo reclamo
						Reclamo nuevoReclamo =reclamosBean.crearReclamo(reclamo);
						
						//buscamos el estado en PROCESO Y LO ALMAZENAMOS en estadoPRoceso
						Estado estadoPRoceso =estadoBean.obtenerEstadoPorNombre("Ingresado");
					
						// creamos el estaREclamo y seteamos el reclamo y el estado
						EstaReclamo nuevoEstado = new EstaReclamo();
						nuevoEstado.setEstado(estadoPRoceso);
						nuevoEstado.setReclamo(nuevoReclamo);
						
						//Creamos el nuevo estado para el reclamo
						estaReclamoBean.crearEstaRec(nuevoEstado);
						

						//Ventana notificando que se creo el reclamo 
						JOptionPane.showMessageDialog(null, "Reclamo creado correctamente", "Notificación", JOptionPane.INFORMATION_MESSAGE); 

						//Limpiamos todos los campos al crear el reclamo  
						textTituloDeReclamo.setText("");
						textArea.setText("");
						textNombreEvento.setText("");
						textCreditos.setText("");
						comboBoxTipoEvento.setSelectedIndex(0);
						comboBoxSemestre.setSelectedIndex(0);
						comboBoxDocente.setSelectedIndex(0);
						datePanelImpl.getModel().setValue(null);

					} catch (NamingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ServiciosException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} 
			}
		});
		btnCrearReclamo.setBounds(580, 376, 119, 23);;
		panelForm.add(btnCrearReclamo);

		//------------------------------VOLVER--------------------------------------///

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
}
