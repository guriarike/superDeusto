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
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnNewButton_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JButton btnNewButton;

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
		frmVentanaInicioSesion = new JFrame();
		frmVentanaInicioSesion.getContentPane().setBackground(new Color(0xffffbf));
		frmVentanaInicioSesion.setTitle("Inicio sesion");
		frmVentanaInicioSesion.setBounds(200, 200, 500, 300);
		frmVentanaInicioSesion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVentanaInicioSesion.getContentPane().setLayout(null);
		

		JLabel lblNewLabel = new JLabel("Super Deusto");
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Raleway SemiBold", Font.ITALIC, 15));
		lblNewLabel.setBounds(29, 24, 119, 25);
		frmVentanaInicioSesion.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Usuario");
		lblNewLabel_1.setBounds(29, 103, 90, 13);
		frmVentanaInicioSesion.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Contraseña");
		lblNewLabel_2.setBounds(29, 149, 79, 13);
		frmVentanaInicioSesion.getContentPane().add(lblNewLabel_2);

		textField = new JTextField();
		textField.setBackground(Color.WHITE);
		textField.setBounds(105, 100, 119, 19);
		frmVentanaInicioSesion.getContentPane().add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(105, 146, 119, 19);
		frmVentanaInicioSesion.getContentPane().add(passwordField);

		btnNewButton_2 = new JButton("Entrar");
		btnNewButton_2.setBackground(SystemColor.desktop);
		btnNewButton_2.setForeground(Color.GRAY);
		btnNewButton_2.setBounds(105, 198, 119, 32);
		
		btnNewButton_2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				

			}
		});

		frmVentanaInicioSesion.getContentPane().add(btnNewButton_2);

		lblNewLabel_3 = new JLabel("¿Nuevo en Super Deusto? \r\n");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(282, 93, 180, 32);
		frmVentanaInicioSesion.getContentPane().add(lblNewLabel_3);

		lblNewLabel_4 = new JLabel("Reg\u00EDstrate ahora");
		lblNewLabel_4.setBounds(294, 114, 109, 32);
		frmVentanaInicioSesion.getContentPane().add(lblNewLabel_4);

		btnNewButton = new JButton("Crear cuenta");
		btnNewButton.setForeground(Color.GRAY);
		btnNewButton.setBackground(SystemColor.desktop);
		btnNewButton.setBounds(294, 156, 124, 32);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new VentanaRegistro();
			}
		});
		
		
		
		

		frmVentanaInicioSesion.getContentPane().add(btnNewButton);
		
		

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 70, 466, 34);
		frmVentanaInicioSesion.getContentPane().add(separator);
		frmVentanaInicioSesion.setVisible(true);

	}
	
	
}
