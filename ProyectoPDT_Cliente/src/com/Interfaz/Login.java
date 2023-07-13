package com.Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.naming.InitialContext;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.Analista.VentanaAnalista;
import com.Estudiante.VentanaEstudiante;
import com.Tutor.VentanaTutor;
import com.clienteProyecto.MostrarPanel;
import com.entitiesProyecto.TipoUsuario;
import com.entitiesProyecto.Usuario;
import com.entitiesProyecto.UsuarioEstado;
import com.serviciosProyecto.EstudianteBeanRemote;
import com.serviciosProyecto.UsuariosBean;
import com.serviciosProyecto.UsuariosBeanRemote;

public class Login implements MostrarPanel {
    private UsuariosBeanRemote usuariosBean;
    private JDialog frame;
    private JPanel panelContent;
    private JTextField textCorreo;
    private JPasswordField textContraseña;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UsuariosBeanRemote usuariosBean = (UsuariosBeanRemote) InitialContext.doLookup(
                            "ejb:/ProyectoPDT_Servidor/UsuariosBean!com.serviciosProyecto.UsuariosBeanRemote");
                    EstudianteBeanRemote estudianteBean = (EstudianteBeanRemote) InitialContext
                            .doLookup("ejb:/ProyectoPDT_Servidor/EstudianteBean!com.serviciosProyecto.EstudianteBeanRemote");

                    Login window = new Login(usuariosBean);
                    window.mostrarVentana();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void mostrarPanelContent(JPanel panel) {
        panelContent.setBackground(SystemColor.activeCaption);
        panelContent.removeAll();
        panelContent.add(panel, BorderLayout.CENTER);
        panelContent.revalidate();
        panelContent.repaint();
    }

    public Login(UsuariosBeanRemote usuariosBean) {
        this.usuariosBean = usuariosBean;
        initialize();
    }

    private void initialize() {
        frame = new JDialog();
        frame.setResizable(false);
        frame.setModalityType(ModalityType.APPLICATION_MODAL);
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setSize(745, 550);
        frame.setLocationRelativeTo(null);


        JPanel panelHeader = new JPanel();
        panelHeader.setBounds(0, 0, 862, 65);
        frame.getContentPane().add(panelHeader);
        panelHeader.setBackground(UIManager.getColor("ProgressBar.foreground"));
        panelHeader.setLayout(null);

        // --------------TITULO-------------------//
        JLabel lblTitulo = new JLabel("Gestión LTI");
        lblTitulo.setBounds(-20, 11, 764, 35);
        panelHeader.add(lblTitulo);
        lblTitulo.setForeground(new Color(0, 0, 0));
        lblTitulo.setBackground(SystemColor.textHighlight);
        lblTitulo.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 20));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel panelMain = new JPanel();
        panelMain.setBackground(UIManager.getColor("PasswordField.selectionBackground"));
        panelMain.setBounds(-22, 11, 769, 498);
        frame.getContentPane().add(panelMain);
        panelMain.setLayout(null);

        panelContent = new JPanel();
        panelContent.setBounds(21, 54, 748, 444);
        panelContent.setLayout(null);
        panelMain.add(panelContent);

        textCorreo = new JTextField();
        textCorreo.setBounds(269, 153, 197, 21);
        panelContent.add(textCorreo);
        textCorreo.setColumns(10);

        textContraseña = new JPasswordField();
        textContraseña.setBounds(269, 207, 197, 21);
        panelContent.add(textContraseña);
        textContraseña.setColumns(10);

        // ---------------------------------------INICIO DE SESION------------------------------------------//

        JLabel lblCorreo = new JLabel("Nombre de Usuario");
        lblCorreo.setBounds(314, 121, 117, 21);
        panelContent.add(lblCorreo);

        JLabel lblContraseña = new JLabel("Contraseña");
        lblContraseña.setBounds(331, 185, 117, 21);
        panelContent.add(lblContraseña);

        JButton btnIniciarSesion = new JButton("Iniciar Sesión");
        btnIniciarSesion.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnIniciarSesion.setBounds(284, 263, 160, 21);
        panelContent.add(btnIniciarSesion);
        btnIniciarSesion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String nomUsuario = textCorreo.getText();
                String contrasenia = textContraseña.getText();

                boolean usuarioValido = usuariosBean.validarNombreUsuario(nomUsuario, contrasenia);

                if (usuarioValido) {
                    // Obtener el usuario de la base de datos
                    Usuario usuario = usuariosBean.obtenerUsuarioDesdeBaseDeDatosNombre(nomUsuario);
                    System.out.println("Usuario: " + usuario.getNombres());

                    // Verificar el estado del usuario
                    UsuarioEstado estadoUsuario = usuario.getUsuaEstado();

                    if (estadoUsuario.getIdEstaUsuario() == 1) {
                        // El usuario está activo, permitir el inicio de sesión
                        // Verificar el tipo de usuario y abrir la ventana correspondiente

                        if (usuario.getTipo() == TipoUsuario.ANALISTA) {

                            VentanaAnalista analista = new VentanaAnalista(usuariosBean,usuario);
                            analista.setBounds(-40, 0, 769, 498);
                            mostrarPanelContent(analista);

                        } else if (usuario.getTipo() == TipoUsuario.ESTUDIANTE) {
                            // Abrir ventana de Estudiante

                            VentanaEstudiante estudiante = new VentanaEstudiante(usuariosBean, usuario);
                            estudiante.setBounds(-40, 0, 769, 498);
                            mostrarPanelContent(estudiante);

                        } else if (usuario.getTipo() == TipoUsuario.TUTOR) {
                            // Abrir ventana de Tutor

                            VentanaTutor tutor = new VentanaTutor(usuariosBean ,usuario);
                            tutor.setBounds(-40, 0, 769, 498);
                            mostrarPanelContent(tutor);
                        }

                    } else {
                        // Mostrar mensaje de estado del usuario
                        JOptionPane.showMessageDialog(null,
                                "Su usuario aún no ha sido activado. Estado: " + estadoUsuario.getNombre());
                    }
                } else {
                    // El usuario no existe en la base de datos
                    JOptionPane.showMessageDialog(null, "Credenciales inválidas");
                }
            }
        });

        // -------------------------------------------- REGISTRARSE -------------------------------------------//
        JLabel lblRegistrarse = new JLabel("¿No tienes cuenta? Registrate");
        lblRegistrarse.setBounds(284, 325, 182, 21);
        panelContent.add(lblRegistrarse);

        JButton btnRegistro = new JButton("Registrarse");
        btnRegistro.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                VentanaRegistro gestionRegistro = new VentanaRegistro();
                gestionRegistro.setBounds(-40, 0, 769, 498);
                mostrarPanelContent(gestionRegistro);
            }
        });
        btnRegistro.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnRegistro.setBounds(284, 357, 160, 21);
        panelContent.add(btnRegistro);

        JLabel lblIniciarSesin = new JLabel("Iniciar Sesión");
        lblIniciarSesin.setHorizontalAlignment(SwingConstants.CENTER);
        lblIniciarSesin.setForeground(Color.BLACK);
        lblIniciarSesin.setFont(new Font("Segoe UI Symbol", Font.BOLD, 23));
        lblIniciarSesin.setBackground(SystemColor.textHighlight);
        lblIniciarSesin.setBounds(-16, 40, 764, 35);
        panelContent.add(lblIniciarSesin);

        // Set the frame to appear in the center of the screen
        frame.setLocationRelativeTo(null);
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }
}
