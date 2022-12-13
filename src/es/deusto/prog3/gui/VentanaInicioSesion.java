package es.deusto.prog3.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import es.deusto.prog3.g01.*;

import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JSeparator;
import javax.swing.JComboBox;

public class VentanaInicioSesion extends JFrame {

	private JFrame frmVentanaInicioSesion;
	private JTextField textUsuario;
	private JPasswordField passwordField;
	private JButton btnEntrar;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JButton btnCrear;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInicioSesion window = new VentanaInicioSesion();
					window.frmVentanaInicioSesion.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaInicioSesion() {
		GestorBD.crearBBDD();
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		JFrame ventanaActual = this;
		frmVentanaInicioSesion = new JFrame();
		frmVentanaInicioSesion.getContentPane().setBackground(new Color(0xffffbf));
		frmVentanaInicioSesion.setTitle("Inicio sesion");
		frmVentanaInicioSesion.setBounds(200, 200, 500, 300);
		frmVentanaInicioSesion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVentanaInicioSesion.getContentPane().setLayout(null);

		JLabel lblSuperDeusto = new JLabel("Super Deusto");
		lblSuperDeusto.setBackground(new Color(0, 0, 0));
		lblSuperDeusto.setFont(new Font("Raleway SemiBold", Font.ITALIC, 15));
		lblSuperDeusto.setBounds(29, 24, 119, 25);
		frmVentanaInicioSesion.getContentPane().add(lblSuperDeusto);

		JLabel lblUsuario = new JLabel("Correo");
		lblUsuario.setBounds(29, 103, 90, 13);
		frmVentanaInicioSesion.getContentPane().add(lblUsuario);

		JLabel lblContraseña = new JLabel("Contraseña");
		lblContraseña.setBounds(29, 149, 79, 13);
		frmVentanaInicioSesion.getContentPane().add(lblContraseña);

		textUsuario = new JTextField();
		textUsuario.setBackground(Color.WHITE);
		textUsuario.setBounds(105, 100, 119, 19);
		frmVentanaInicioSesion.getContentPane().add(textUsuario);
		textUsuario.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(105, 146, 119, 19);
		frmVentanaInicioSesion.getContentPane().add(passwordField);

		btnEntrar = new JButton("Entrar");
		btnEntrar.setBackground(SystemColor.desktop);
		btnEntrar.setForeground(Color.GRAY);
		btnEntrar.setBounds(105, 198, 119, 32);

		frmVentanaInicioSesion.getContentPane().add(btnEntrar);

		lblNewLabel_3 = new JLabel("¿Nuevo en Super Deusto? \r\n");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(282, 93, 180, 32);
		frmVentanaInicioSesion.getContentPane().add(lblNewLabel_3);

		lblNewLabel_4 = new JLabel("Registrate ahora");
		lblNewLabel_4.setBounds(294, 114, 109, 32);
		frmVentanaInicioSesion.getContentPane().add(lblNewLabel_4);

		btnCrear = new JButton("Crear cuenta");
		btnCrear.setForeground(Color.GRAY);
		btnCrear.setBackground(SystemColor.desktop);
		btnCrear.setBounds(294, 156, 124, 32);
		frmVentanaInicioSesion.getContentPane().add(btnCrear);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 70, 466, 34);
		frmVentanaInicioSesion.getContentPane().add(separator);
		frmVentanaInicioSesion.setVisible(true);

		// BOTONES

		btnEntrar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					if(GestorBD.existeUsuarioEnBBDD(textUsuario.getText(), passwordField.getText())) {
						VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(GestorBD.usuarioPorCorreo(textUsuario.getText()), ventanaActual);
						ventanaPrincipal.setVisible(true);
					}else {
						System.out.println("Error en el correo o contraseña");
					}
					
				}catch(Exception ee) {
					
				}
				
				
				

			}
		});

		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new VentanaRegistro();
			}
		});

	}

}
