package com.Tutor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.entitiesProyecto.Escolaridad;
import com.entitiesProyecto.Estudiante;
import com.entitiesProyecto.Usuario;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.serviciosProyecto.EscolaridadBeanRemote;
import com.serviciosProyecto.EstudianteBeanRemote;
import com.serviciosProyecto.UsuariosBeanRemote;

public class VentanaTutor_Reportes extends JPanel {

	private Usuario usuario;
	private JTextArea textArea = new JTextArea();
	private JScrollPane scrollPane = new JScrollPane(textArea);
	private UsuariosBeanRemote usuariosBean;
	public void cambiarContenido(JPanel panelDestino, JPanel panelFuente) {
		panelDestino.removeAll();
		panelDestino.add(panelFuente, BorderLayout.CENTER);
		panelDestino.revalidate();
		panelDestino.repaint();
	}
	
	public VentanaTutor_Reportes(UsuariosBeanRemote usuariosBean, Usuario usuario) {		
		this.usuariosBean = usuariosBean;
		this.usuario = usuario;
		
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

		
		// ---------------------------------------TITULO------------------------------------------//

				JLabel lblReporte = new JLabel("Escolaridad");
				lblReporte.setHorizontalAlignment(SwingConstants.CENTER);
				lblReporte.setFont(new Font("BIZ UDPGothic", Font.BOLD, 17));
				lblReporte.setBounds(266, 11, 226, 20);
				panelForm.add(lblReporte);
		
		// -----------------------------------TEXT AREA--------------------------------------//

		textArea.setMargin(new Insets(10,170,10,10));
		textArea.setEditable(false);  
		
		scrollPane.setBounds(84, 53, 578, 300);  
		panelForm.add(scrollPane);
					
		// -----------------------------------DESCARGAR ESCOLARIDAD--------------------------------------//

		JButton btnDescargarEscolaridad = new JButton("Descargar");
		btnDescargarEscolaridad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
							
				JFileChooser fileChooser = new JFileChooser();
		        
		    	fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		        
		    	int option = fileChooser.showSaveDialog(null);
		        
		        if(option == JFileChooser.APPROVE_OPTION){
		            File file = fileChooser.getSelectedFile();
		            String path = file.getAbsolutePath();
		            
		            try {
		                Document document = new Document();
		                PdfWriter.getInstance(document, new FileOutputStream(path + "/Escolaridad " + usuario.getNombres() + " " + usuario.getApellidos() + ".pdf"));
		                document.open();
		                
		                InitialContext ic = new InitialContext();
						EscolaridadBeanRemote escolaridadBean = (EscolaridadBeanRemote) ic.lookup(
								"ejb:/ProyectoPDT_Servidor/EscolaridadBean!com.serviciosProyecto.EscolaridadBeanRemote");
				
						EstudianteBeanRemote estudianteBean = (EstudianteBeanRemote) InitialContext
								.doLookup("ejb:/ProyectoPDT_Servidor/EstudianteBean!com.serviciosProyecto.EstudianteBeanRemote");
						
						
						Long idUsuario = usuario.getIdUsuario();
						Estudiante estudiante = estudianteBean.obtenerEstudiantePorIdUsuario(idUsuario);
						Long idEstudiante = estudiante.getIdEstudiante();
						List<Escolaridad> escolaridades = escolaridadBean.obtenerEscolaridadPorIdEstudiante(idEstudiante);

		               
		                document.add(new Paragraph("Escolaridad del estudiante"));
		                document.add(new Paragraph(" "));
		                document.add(new Paragraph("Datos personales"));
		                document.add(new Paragraph(" "));
		                document.add(new Paragraph("Nombre: " + usuario.getNombres() + " " + usuario.getApellidos()));
		                document.add(new Paragraph("Documento: " + usuario.getDocumento()));
		                document.add(new Paragraph("Fecha de nacimiento: " + usuario.getFecNacimiento()));
		                document.add(new Paragraph("Género: " + usuario.getGenero()));
		                document.add(new Paragraph("Mail: " + usuario.getMaiInstitucional()));
		                document.add(new Paragraph("Teléfono: " + usuario.getTelefono()));
		                document.add(new Paragraph("Departamento: " + usuario.getDepartamento().getNombre()));
		                document.add(new Paragraph("Localidad: " + usuario.getLocalidade().getNombre()));
		                document.add(new Paragraph("ITR: " + usuario.getItr().getNombre()));
		                document.add(new Paragraph(" "));		          
		                document.add(new Paragraph("Expediente Académico"));
		                document.add(new Paragraph(" "));
		                
		               
		                
		                // Crear un párrafo para cada escolaridad
		                for(Escolaridad escolaridad : escolaridades) {              	
		                    document.add(new Paragraph("Nombre Asignatura: " + escolaridad.getNombreAsignatura()));
		                    document.add(new Paragraph("Calificación: " + escolaridad.getGradoObtenido()));
		                    document.add(new Paragraph("Nombre Carrera: " + escolaridad.getNomnbreCarrera()));
		                    document.add(new Paragraph("------------------------------------------------------"));
		                }
		                document.add(new Paragraph(" "));
		                document.add(new Paragraph("UTEC - Universidad Tecnológica del Uruguay"));
		                document.add(new Paragraph(" "));		             
		                
		                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
		                Date date = new Date(); 
		                String dateString = formatter.format(date);

		                document.add(new Paragraph("Fecha de creación: " + dateString));
		                document.close();
		                JOptionPane.showMessageDialog(null, "PDF generado exitosamente!");
		            } catch (Exception ex) {
		                ex.printStackTrace();
		            }
		        }
			
			
			}
		});
		
		btnDescargarEscolaridad.setBounds(222, 380, 119, 23);  
		panelForm.add(btnDescargarEscolaridad);
		
		// -----------------------------------VER ESCOLARIDAD--------------------------------------//

		JButton btnVerEscolaridad = new JButton("Ver");
		btnVerEscolaridad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					InitialContext ic = new InitialContext();
					EscolaridadBeanRemote escolaridadBean = (EscolaridadBeanRemote) ic.lookup(
							"ejb:/ProyectoPDT_Servidor/EscolaridadBean!com.serviciosProyecto.EscolaridadBeanRemote");
			
					EstudianteBeanRemote estudianteBean = (EstudianteBeanRemote) InitialContext
							.doLookup("ejb:/ProyectoPDT_Servidor/EstudianteBean!com.serviciosProyecto.EstudianteBeanRemote");
					
					
					Long idUsuario = usuario.getIdUsuario();
					Estudiante estudiante = estudianteBean.obtenerEstudiantePorIdUsuario(idUsuario);
					Long idEstudiante = estudiante.getIdEstudiante();
					List<Escolaridad> escolaridades = escolaridadBean.obtenerEscolaridadPorIdEstudiante(idEstudiante);			
				
	             // StringBuilder para almacenar la información a mostrar en pantalla
	                StringBuilder sb = new StringBuilder();

	                // Añadir información personal al sb
	                sb.append("Datos personales\n");
	                sb.append("Nombre: ").append(usuario.getNombres()).append(" ").append(usuario.getApellidos()).append("\n");
	                sb.append("Documento: ").append(usuario.getDocumento()).append("\n");
	                sb.append("Fecha de nacimiento: ").append(usuario.getFecNacimiento()).append("\n");
	                sb.append("Género: ").append(usuario.getGenero()).append("\n");
	                sb.append("Mail: ").append(usuario.getMaiInstitucional()).append("\n");
	                sb.append("Teléfono: ").append(usuario.getTelefono()).append("\n");
	                sb.append("Departamento: ").append(usuario.getDepartamento().getNombre()).append("\n");
	                sb.append("Localidad: ").append(usuario.getLocalidade().getNombre()).append("\n");
	                sb.append("ITR: ").append(usuario.getItr().getNombre()).append("\n");
	                sb.append("\nExpediente Académico\n");

	                // Añadir información de cada escolaridad a sb
	                for (Escolaridad escolaridad : escolaridades) {
	                    sb.append("Nombre Asignatura: ").append(escolaridad.getNombreAsignatura()).append("\n");
	                    sb.append("Calificación: ").append(escolaridad.getGradoObtenido()).append("\n");
	                    sb.append("Nombre Carrera: ").append(escolaridad.getNomnbreCarrera()).append("\n");
	                    sb.append("------------------------------------------------------").append("\n");
	                }
        		 
        		// Actualiza textArea con la información en sb
        		 textArea.setText(sb.toString());
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}});
		
		btnVerEscolaridad.setBounds(84, 380, 119, 23);
		panelForm.add(btnVerEscolaridad);
		
	
		// -------------------------------------VOLVER--------------------------------------//

				JButton btnVolver = new JButton("Volver");
				btnVolver.setBounds(599, 415, 119, 23);
				panelForm.add(btnVolver);
				btnVolver.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						VentanaTutor_ListadoDeUsuarios volver = new VentanaTutor_ListadoDeUsuarios(usuariosBean, usuario);

						volver.setBounds(-40, 0, 769, 498);

						cambiarContenido(panelForm, volver);

						volver.setVisible(true);

					}
				});

	}
}

