package com.Estudiante;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
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

import net.sourceforge.jdatepicker.impl.DateComponentFormatter;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class VentanaEstudiante_EditarReclamo extends JPanel {


	private Usuario usuario;
	private JTextField textTituloDeReclamo;
	private JTextField textNombreEvento;
	private JTextField textCreditos;
	private List<Tutor>listaTutor;
	private Reclamo reclamo; 
	private UsuariosBeanRemote usuariosBean;
	
	public void cambiarContenido(JPanel panelDestino, JPanel panelFuente) {
		panelDestino.removeAll();
		panelDestino.add(panelFuente, BorderLayout.CENTER);
		panelDestino.revalidate();
		panelDestino.repaint();
	}

	public VentanaEstudiante_EditarReclamo(UsuariosBeanRemote usuariosBean, Reclamo reclamo, Usuario usuario) {
		this.reclamo = reclamo; 

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
		

		// ---------------------------TITULO------------------------------//

		JLabel lblReporte = new JLabel("Editar Reclamo");
		lblReporte.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 16));
		lblReporte.setHorizontalAlignment(SwingConstants.CENTER);
		lblReporte.setBounds(269, 11, 226, 20);
		panelForm.add(lblReporte);
		
		//--------------------------------TITULO RECLAMO------------------------------------//	


		JLabel lblNewLabel = new JLabel("Titulo del Reclamo");
		lblNewLabel.setBounds(62, 74, 135, 21);
		panelForm.add(lblNewLabel);

		textTituloDeReclamo = new JTextField();
		textTituloDeReclamo.setText(reclamo.getAsunto());
		textTituloDeReclamo.setBounds(62, 99, 140, 20);
		panelForm.add(textTituloDeReclamo);
		textTituloDeReclamo.setColumns(10);
		
		//--------------------------------DESCRIPCION---------------------------------------//	

		JLabel lblDescripcionDelReclamo = new JLabel("Descripcion del Reclamo");
		lblDescripcionDelReclamo.setBounds(62, 129, 140, 21);
		panelForm.add(lblDescripcionDelReclamo);
		
		JTextArea textArea = new JTextArea();
		textArea.setText(reclamo.getDetalle());
		textArea.setLineWrap(true); // Activa el ajuste de línea automático
		textArea.setWrapStyleWord(true); // Ajusta las palabras completas

		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(62, 153, 216, 111);
		panelForm.add(scrollPane);

		//--------------------------------TIPO DE EVENTO------------------------------------//	

		JLabel lblTipoDeEvento = new JLabel("Tipo de Actividad");
		lblTipoDeEvento.setBounds(62, 275, 140, 21);
		panelForm.add(lblTipoDeEvento);

		JComboBox<String> comboBoxTipoEvento = new JComboBox<>();
		comboBoxTipoEvento.addItem("");

		for (TipoActividad tipo : TipoActividad.values()) {
			comboBoxTipoEvento.addItem(tipo.name());
			if (tipo.name().equals( reclamo.getTipoActividad().name())) {
				comboBoxTipoEvento.setSelectedItem(reclamo.getTipoActividad().name());

			}

		}
		comboBoxTipoEvento.setBounds(62, 303, 134, 22);
		panelForm.add(comboBoxTipoEvento);


		//------------------------------NOMBRE DE LA ACTIVIDAD---------------------------//

		textNombreEvento = new JTextField();
		textNombreEvento.setText(reclamo.getNombre());
		textNombreEvento.setColumns(10);
		textNombreEvento.setBounds(332, 99, 140, 20);
		panelForm.add(textNombreEvento);

		JLabel lblNombreDelEvento = new JLabel("Nombre de la Actividad");
		lblNombreDelEvento.setBounds(332, 74, 124, 21);
		panelForm.add(lblNombreDelEvento);
		
		//--------------------------------SEMESTRE--------------------------------------//

		JComboBox comboBoxSemestre = new JComboBox();
		comboBoxSemestre.addItem("");

		for (Semestre semestre : Semestre.values()) {
			comboBoxSemestre.addItem(semestre.name());
			if(reclamo.getSemestre().name().equals(semestre.name())) {
				comboBoxSemestre.setSelectedItem(reclamo.getSemestre().name());

			}

		}

		comboBoxSemestre.setBounds(332, 169, 134, 22);
		panelForm.add(comboBoxSemestre);

		JLabel lblSemestre = new JLabel("Semestre");
		lblSemestre.setBounds(332, 141, 140, 21);
		panelForm.add(lblSemestre);
		
		//-----------------------------FECHA-------------------------------------------//

		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePanelImpl  = new JDatePickerImpl(datePanel, new DateComponentFormatter());
		datePanelImpl.getJFormattedTextField().setToolTipText("Seleccione una fecha");

		UtilDateModel dateModel = (UtilDateModel) datePanelImpl.getModel();
		dateModel.setValue(reclamo.getFecha());
		datePanelImpl.setBounds(516, 103, 159, 23);
		panelForm.add(datePanelImpl);

		JLabel lblFechaDelReclamo = new JLabel("Fecha de la Actividad");
		lblFechaDelReclamo.setBounds(516, 71, 159, 21);
		panelForm.add(lblFechaDelReclamo);

		
		
		JLabel lblDocente = new JLabel("Docente");
		lblDocente.setBounds(332, 213, 140, 21);
		panelForm.add(lblDocente);
		JComboBox comboBoxDocente = new JComboBox();
		comboBoxDocente.addItem("");

		try {
			TutoresBeanRemote tutoresBean = (TutoresBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_Servidor/TutoresBean!com.serviciosProyecto.TutoresBeanRemote");

			listaTutor = tutoresBean.obtenerTodosLosTutores();

			for (Tutor tutor : listaTutor) {
				// con el comando addItem metemos los departamentos en el combobox
				comboBoxDocente.addItem(tutor.getUsuario().getNombres()+ " " + tutor.getUsuario().getApellidos());
				if(reclamo.getTutore().getUsuario().getIdUsuario() ==  tutor.getUsuario().getIdUsuario()) {
					comboBoxDocente.setSelectedItem(tutor.getUsuario().getNombres()+ " " + tutor.getUsuario().getApellidos()) ;
				}

			}


		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		comboBoxDocente.setBounds(332, 241, 134, 22);
		panelForm.add(comboBoxDocente);

		//---------------------------------CREDITOS--------------------------//


		JLabel lblCreditos = new JLabel("Creditos");
		lblCreditos.setBounds(332, 279, 140, 21);
		panelForm.add(lblCreditos);

		textCreditos = new JTextField();
		textCreditos.setText(reclamo.getCredito()); 
		textCreditos.setBounds(332, 305, 62, 20);
		panelForm.add(textCreditos);
		textCreditos.setColumns(10);

		//---------------------------------------ACTUALIZAR RECLAMO----------------------------------------------//
		JButton btnEditarReclamo = new JButton("Modificar");
		btnEditarReclamo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tituloReclamo = textTituloDeReclamo.getText();
				String descripcionReclamo = textArea.getText();
				String nombreEvento = textNombreEvento.getText();
				String creditos = textCreditos.getText();
				String tipoEvento = (String) comboBoxTipoEvento.getSelectedItem();
				String semestre = (String) comboBoxSemestre.getSelectedItem();
				String docente = (String) comboBoxDocente.getSelectedItem();

				boolean valido = false;
				String campoFaltante = "";

				// Validar campos vacíos y almacenar el campo faltante
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
				} else if (tituloReclamo.length() < 3 || tituloReclamo.length() > 20) {
					campoFaltante = "Título del Reclamo debe tener entre 3 y 20 caracteres";
				} else if (nombreEvento.length() < 3 || nombreEvento.length() > 20) {
					campoFaltante = "Nombre del Evento debe tener entre 3 y 20 caracteres";
				} else if (descripcionReclamo.length() < 20 || descripcionReclamo.length() > 255) {
					campoFaltante = "Descripción del evento debe tener entre 20 y 255 caracteres";
				} else {
					// Validacion que de tiene que ser numero del 0 al 99
					if (!creditos.matches("^(?:[0-9]|[1-9][0-9])$")) {
						campoFaltante = "Créditos inválidos deben ser números del 0 al 99";
					} else {
						valido = true;
					}
				}

				// Verificar si algún campo está vacío
				if (!campoFaltante.isEmpty()) {
					String mensaje = "El campo '" + campoFaltante + "' no ha sido completado.";
					JOptionPane.showMessageDialog(null, mensaje, "Campo faltante", JOptionPane.ERROR_MESSAGE);
				} else if (valido) {

					int opcion = JOptionPane.showConfirmDialog(null, "¿Deseas realizar la modificación?",
							"Confirmación", JOptionPane.YES_NO_OPTION);
					if (opcion == JOptionPane.YES_OPTION) {
						try {
							EstudianteBeanRemote estudianteBean = (EstudianteBeanRemote) InitialContext.doLookup(
									"ejb:/ProyectoPDT_Servidor/EstudianteBean!com.serviciosProyecto.EstudianteBeanRemote");
							ReclamosBeanRemote reclamosBean = (ReclamosBeanRemote) InitialContext.doLookup(
									"ejb:/ProyectoPDT_Servidor/ReclamosBean!com.serviciosProyecto.ReclamosBeanRemote");
							EstadoBeanRemote estadoBean = (EstadoBeanRemote) InitialContext.doLookup(
									"ejb:/ProyectoPDT_Servidor/EstadoBean!com.serviciosProyecto.EstadoBeanRemote");
							EstaReclamoBeanRemote estaReclamoBean = (EstaReclamoBeanRemote) InitialContext.doLookup(
									"ejb:/ProyectoPDT_Servidor/EstaReclamoBean!com.serviciosProyecto.EstaReclamoBeanRemote");

							List<Estudiante> listaEstudiantes = estudianteBean.obtenerEstudianteTodos();
							// Del usario logeado sacamos el estudiante como entidad
							Estudiante estudianteSel = null;
							for (Estudiante estudiante : listaEstudiantes) {
								if (estudiante.getUsuario().getIdUsuario() == usuario.getIdUsuario()) {
									estudianteSel = estudiante;
									break;
								}

							}

							Reclamo reclamoMdificar = reclamosBean.obtenerReclamo(reclamo.getIdReclamo());
							if (reclamoMdificar != null) {

								// Agregamos el titulo del Reclamo
								reclamoMdificar.setAsunto(textTituloDeReclamo.getText());
								// Agregamos la descripcion del Reclamo
								reclamoMdificar.setDetalle(textArea.getText());
								// en fechaActual creamos una variable que contiene el dia que se crea el
								// reclamo
								Date fechaActual = new Date();
								reclamoMdificar.setFecha(fechaActual);
								// y con timestamp guardamos la hora del reclamo
								Timestamp timestamp = new Timestamp(fechaActual.getTime());
								reclamoMdificar.setHora(timestamp);

								// Agregamos el estudiante al reclamo
								reclamoMdificar.setEstudiante(estudianteSel);
								// agregamos el tipo de actividad
								TipoActividad tipoActividad = TipoActividad
										.valueOf((String) comboBoxTipoEvento.getSelectedItem());
								reclamoMdificar.setTipoActividad(tipoActividad);
								// Agregamos el semestre
								Semestre tipoSemestre = Semestre.valueOf((String) comboBoxSemestre.getSelectedItem());
								reclamoMdificar.setSemestre(tipoSemestre);
								// Agregamos los creditos
								reclamoMdificar.setCredito(textCreditos.getText());
								// Agregamos reclamos por nombre del evento
								reclamoMdificar.setNombre(textNombreEvento.getText());

								// Agregamos al tutor
								int selectedIndex = comboBoxDocente.getSelectedIndex();

								Tutor tutorSeleccionado = listaTutor.get(selectedIndex - 1);
								reclamoMdificar.setTutore(tutorSeleccionado);

								Date fecha = (Date) datePanelImpl.getModel().getValue();
								reclamoMdificar.setFechaActividad(fecha);

								// actualizamos el reclamo
								reclamosBean.actualizarReclamo(reclamoMdificar);

								// Ventana notificando que se creo el reclamo
								JOptionPane.showMessageDialog(null, "Reclamo modificado correctamente", "Notificación",
										JOptionPane.INFORMATION_MESSAGE);

							}
						} catch (NamingException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btnEditarReclamo.setBounds(599, 374, 119, 23);;
		panelForm.add(btnEditarReclamo);

		//------------------------------VOLVER--------------------------------------///

		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(599, 415, 119, 23);
		panelForm.add(btnVolver);
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
								VentanaEstudiante_ListadoDeReclamos volver = new VentanaEstudiante_ListadoDeReclamos(usuariosBean, usuario);
				
								volver.setBounds(-40, 0, 769, 498);
				
								cambiarContenido(panelForm, volver);
				
								volver.setVisible(true);

			}
		});

	}
}
