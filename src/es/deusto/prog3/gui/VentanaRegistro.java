package es.deusto.prog3.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import es.deusto.prog3.gui.*;
import es.deusto.prog3.g01.*;

public class VentanaRegistro extends JFrame {

	private JPanel contentPane;

	private JFrame frmVentanaRegistro;
	private JTextField textField;
	private JButton btnNewButton_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JButton btnNewButton;
	private JTextField textField_1;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JPasswordField passwordField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	
	public VentanaRegistro() {
		
		GestorBD.crearBBDD();
		initialize();
	}

	
	private void initialize() {
		getContentPane().setBackground(new Color(0xffffbf));
		setTitle("Registro");
		setBounds(200, 200, 500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Super Deusto");
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Raleway SemiBold", Font.ITALIC, 20));
		lblNewLabel.setBounds(29, 24, 150, 25);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Correo");
		lblNewLabel_1.setBounds(30, 91, 45, 13);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Nombre");
		lblNewLabel_2.setBounds(29, 117, 79, 13);
		getContentPane().add(lblNewLabel_2);

		textField = new JTextField();
		textField.setBackground(Color.WHITE);
		getContentPane().add(textField);
		textField.setColumns(10);

		btnNewButton_2 = new JButton("Registrarse");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				chequearUsuario();

			}
		});
		btnNewButton_2.setBackground(SystemColor.desktop);
		btnNewButton_2.setForeground(Color.GRAY);
		btnNewButton_2.setBounds(294, 221, 120, 32);
		getContentPane().add(btnNewButton_2);

		lblNewLabel_3 = new JLabel("\u00BFYa est\u00E1s registrado? ");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(282, 93, 151, 32);
		getContentPane().add(lblNewLabel_3);

		lblNewLabel_4 = new JLabel(" Entra ahora");
		lblNewLabel_4.setBounds(294, 114, 109, 32);
		getContentPane().add(lblNewLabel_4);

		btnNewButton = new JButton("Iniciar sesión");
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new VentanaInicioSesion();
			}
			
			
		});
		btnNewButton.setForeground(Color.GRAY);
		btnNewButton.setBackground(SystemColor.desktop);
		btnNewButton.setBounds(294, 156, 124, 32);
		getContentPane().add(btnNewButton);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 70, 466, 34);
		getContentPane().add(separator);

		textField_1 = new JTextField();
		getContentPane().add(textField_1);
		textField_1.setColumns(10);

		lblNewLabel_5 = new JLabel("Apellidos");
		lblNewLabel_5.setBounds(29, 146, 66, 13);
		getContentPane().add(lblNewLabel_5);

		lblNewLabel_6 = new JLabel("Contraseña");
		lblNewLabel_6.setBounds(29, 175, 75, 13);
		getContentPane().add(lblNewLabel_6);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(105, 172, 119, 19);
		getContentPane().add(passwordField_1);

		textField_2 = new JTextField();
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		
		textField.setBounds(105, 88, 119, 19);
		textField_2.setBounds(105, 114, 119, 19);
		textField_1.setBounds(105, 143, 119, 19);
		textField_3.setBounds(105, 200, 119, 19);
		
		lblNewLabel_3 = new JLabel("Telefono");
		lblNewLabel_3.setBounds(29, 204, 66, 13);
		getContentPane().add(lblNewLabel_3);
				
		
		setVisible(true);

	}
	
	private void chequearUsuario(){
		ArrayList<String> listaUsuarios = GestorBD.getUsuarios();
		
		if(textField.getText().equals("") || textField_1.getText().equals("") || textField_2.getText().equals("") || passwordField_1.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Rellena todos los campos"); // Si alguno de los campos esta vacio
		}else {
			if(listaUsuarios.contains(textField.getText())) {
				JOptionPane.showMessageDialog(null, "Error, este usuario ya existe");
			}else {
				String correo = textField.getText();
				String nombre = textField_2.getText();
				String apellido = textField_1.getText();
				String contrasena = passwordField_1.getText();
				int telefono = Integer.parseInt(textField_3.getText());
				try {
					GestorBD.crearUsuario(new Usuario(1, correo, nombre, apellido, contrasena));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
	}
		
		
	
	

}

