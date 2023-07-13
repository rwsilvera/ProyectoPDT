package com.Estudiante;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.Analista.VentanaAnalista_FichaReclamo;
import com.entitiesProyecto.Accion;
import com.entitiesProyecto.Reclamo;
import com.exceptionProyecto.ServiciosException;
import com.serviciosProyecto.AccionesBeanRemote;
import com.serviciosProyecto.UsuariosBeanRemote;

public class VentanaEstudiante_ReclamosVerAcciones extends JPanel {
	private Reclamo reclamo; 
	
	private JTable table;
	private DefaultTableModel tableModel;
	private UsuariosBeanRemote usuariosBean;
	
	public void cambiarContenido(JPanel panelDestino, JPanel panelFuente) {
		panelDestino.removeAll();
		panelDestino.add(panelFuente, BorderLayout.CENTER);
		panelDestino.revalidate();
		panelDestino.repaint();
	}

	
	public VentanaEstudiante_ReclamosVerAcciones(UsuariosBeanRemote usuariosBean, Reclamo reclamo) {
		this.usuariosBean = usuariosBean;
		this.reclamo = reclamo; 
		
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
		
		try {
			AccionesBeanRemote accionesBean = (AccionesBeanRemote) InitialContext
					.doLookup("ejb:/ProyectoPDT_Servidor/AccionesBean!com.serviciosProyecto.AccionesBeanRemote");
			
			//------------------------------TITULO------------------------------//
			
			JLabel lblRegistro = new JLabel("Acciones");
			lblRegistro.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblRegistro.setBounds(294, 11, 132, 23);
			panelForm.add(lblRegistro);
			lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);
			
			JLabel lblAsuntoGet = new JLabel("--");
			lblAsuntoGet.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 12));
			lblAsuntoGet.setBounds(320, 45, 182, 13);
			//asignar asunto
			lblAsuntoGet.setText(reclamo.getAsunto()); 
			panelForm.add(lblAsuntoGet);


			//--------------------------TABLA DE ACCIONES-----------------------//
			tableModel = new DefaultTableModel();
			tableModel.addColumn("Fecha");
			tableModel.addColumn("Detalle");
			tableModel.addColumn("Analista");


			ArrayList<Accion> acciones = accionesBean.listarAcciones();

			for (Accion accion : acciones) {
				if(accion.getReclamo().getIdReclamo() == reclamo.getIdReclamo()){

					Object[] rowData = { accion.getFechaHora(), accion.getDetalle(),

							accion.getAnalista().getIdAnalista() };
					tableModel.addRow(rowData);
				}
			}

			table = new JTable(tableModel);

			// Agregar la tabla a un JScrollPane
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setBounds(67, 97, 608, 249);

			panelForm.add(scrollPane);
			
			//----------------------- VOLVER-------------------------------//
			JButton btnVolver = new JButton("Volver");
			btnVolver.setBounds(579, 415, 119, 23);
			panelForm.add(btnVolver);
			btnVolver.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					VentanaEstudiante_ListadoDeReclamos_FichaDeReclamo ficha = new VentanaEstudiante_ListadoDeReclamos_FichaDeReclamo(usuariosBean, reclamo);

					ficha.setBounds(-40, 0, 769, 498);

					cambiarContenido(panelForm, ficha);

					ficha.setVisible(true);

				}
			});
			

		} catch (NamingException | ServiciosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
