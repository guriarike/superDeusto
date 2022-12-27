package es.deusto.prog3.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import es.deusto.prog3.g01.*;
import es.deusto.prog3.gui.*;

import java.awt.EventQueue;

import java.util.concurrent.ThreadLocalRandom;

public class VentanaPago extends JFrame {
	private JLabel visa;
	private JLabel kutxa;
	private JLabel candado;
	private JFrame frmVentanaPago;
	private JLabel caducidadL;
	private JLabel ejemploL;
	private JLabel cvvL;
	private JLabel cuentaBancariaL;
	private JTextField cuentaBancaria;
	private JTextField caducidad;
	private JTextField cvv;
	private JButton btnPagar;
	private String ticketString;
	

	
	


	public VentanaPago(String ticket) {
		
		ticketString = ticket;
		//Ventana de pago
		
		frmVentanaPago = new JFrame();
		frmVentanaPago.setTitle("Pago");
		frmVentanaPago.getContentPane().setBackground(new Color(0xffffbf));
		//this.setSize(600, 300);
		frmVentanaPago.setBounds(200, 200, 500, 350);
		frmVentanaPago.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frmVentanaPago.getContentPane().setLayout(null);
		
		//Imagen de visa
		
		visa = new JLabel();
		try {
			Image img = ImageIO.read(getClass().getResource("/visa1.png"));
			Image tamanio = img.getScaledInstance(150, 45, Image.SCALE_SMOOTH);
			
			visa.setIcon(new ImageIcon(tamanio));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		visa.setBounds(10, 0, 200, 95);
		frmVentanaPago.getContentPane().add(visa);

		
		//Imagen kutxa
		
		kutxa = new JLabel();
		try {
			Image img = ImageIO.read(getClass().getResource("/kutxabank.png"));
			Image tamanio = img.getScaledInstance(120, 85, Image.SCALE_SMOOTH);
			
			kutxa.setIcon(new ImageIcon(tamanio));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		kutxa.setBounds(300, 0, 200, 85);
		frmVentanaPago.getContentPane().add(kutxa);
		
		
		//Imagen candado
		
		candado = new JLabel();
		try {
			Image img = ImageIO.read(getClass().getResource("/candado.png"));
			Image tamanio = img.getScaledInstance(85, 85, Image.SCALE_SMOOTH);
			
			candado.setIcon(new ImageIcon(tamanio));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		candado.setBounds(320, 0, 200, 340);
		frmVentanaPago.getContentPane().add(candado);
		
		
		//JLabels al lado del cuadro de texto
		
		cuentaBancariaL = new JLabel("Cuenta bancaria:");
		cuentaBancariaL.setBounds(29, 103, 110, 13);
		frmVentanaPago.getContentPane().add(cuentaBancariaL);
		caducidadL = new JLabel("Caducidad:");
		caducidadL.setBounds(62, 149, 79, 13);
		frmVentanaPago.getContentPane().add(caducidadL);
		ejemploL = new JLabel("(ej:mm.aa)");
		ejemploL.setBounds(65, 163, 79, 13);
		frmVentanaPago.getContentPane().add(ejemploL);
		cvvL = new JLabel("CVV:");
		cvvL.setBounds(98, 195, 113, 13);
		frmVentanaPago.getContentPane().add(cvvL);
		
		
		//JTextFields cuadros de texto
		
		cuentaBancaria = new JTextField();
		cuentaBancaria.setBackground(Color.WHITE);
		cuentaBancaria.setBounds(135, 100, 119, 19);
		frmVentanaPago.getContentPane().add(cuentaBancaria);
		cuentaBancaria.setColumns(10);
		
		caducidad = new JTextField();
		caducidad.setBackground(Color.WHITE);
		caducidad.setBounds(135, 146, 119, 19);
		frmVentanaPago.getContentPane().add(caducidad);
		
		cvv = new JTextField();
		cvv.setBackground(Color.WHITE);
		cvv.setBounds(135, 192, 119, 19);
		frmVentanaPago.getContentPane().add(cvv);
		
		
		//pulsador realizar pago
		
		btnPagar = new JButton("Realizar pago");
		btnPagar.setForeground(Color.WHITE);
		btnPagar.setBackground(SystemColor.desktop);
		btnPagar.setBounds(135, 238, 119, 30);
		btnPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chequearPago(ticket);
				
			}
		});
		
		frmVentanaPago.getContentPane().add(btnPagar);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 80, 466, 34);
		frmVentanaPago.getContentPane().add(separator);
		
		frmVentanaPago.setVisible(true);
		
	}
		
		//Generar numeros de pedido aleatorios
		
		int numeroPedido = ThreadLocalRandom.current().nextInt(1000000, 9999999 + 1);
		
		//Metodos para chequear
		
		private void chequearPago(String ticket) {
			
			/*
			ArrayList<String> listaAdministradores = GestorBD.getAdministradores();
			ArrayList<String> listaUsuarios = GestorBD.getClientes();
			ArrayList<Pago> listaPagos = GestorBD.getPagos();
			System.out.println(listaAdministradores);
			*/
			String cuentaT = cuentaBancaria.getText();
			String caducidadT = caducidad.getText();
			String cvvT = cvv.getText();
			/*
			String correoT = GestorBD.cargarCorreoDeUnCliente();
			int idT = GestorBD.cargarIdDeUnCliente();
			String nombreT = GestorBD.cargarNombreDeUnCliente(correoT);
			String apellidoT = GestorBD.cargarApellidoDeUnCliente(correoT);
			String contraseniaT = GestorBD.cargarContraseniaDeUnCliente(correoT);
			*/
			if(cuentaT.equals("") || caducidadT.equals("") || cvvT.equals("")) {
				JOptionPane.showMessageDialog(null, "Rellena todos los campos");
				
			}else if(cuentaT.length() != 16) {
				JOptionPane.showMessageDialog(null, "La cuenta bancaria introducida no existe");
			
			}else if(caducidadT.length() > 5){
				JOptionPane.showMessageDialog(null, "La fecha de caducidad introducida no es correcta");
			
			}else if(cvvT.length() != 3) {
				JOptionPane.showMessageDialog(null, "El CVV introducido no es correcto");
			}else {
				try {
					//GestorBD.crearPago(new Pago(1, correoT, nombreT, apellidoT, contraseniaT, cuentaT, caducidadT, cvvT));
					crearTicket(ticket);
				
				
				
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Su compra se ha realizado correctamente.\n"
						+ "Su numero de pedido es:\n" + numeroPedido);
				System.exit(0);
			}
		}
		public void crearTicket(String ticket) {

			// Creamos un filechooser y guardamos la ruta y el nombre de la carpeta
			JFileChooser guardar = new JFileChooser();
			guardar.showSaveDialog(null);
			guardar.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			File archivo = guardar.getSelectedFile();


			// le pasamos al metodo para crear el fichero el nombre , la ruta y 'devolver'
			guardarFichero(ticket, archivo);

		}
		public void guardarFichero(String texto, File archivo) {

			FileWriter escribir;
			try {

				escribir = new FileWriter(archivo, true);
				escribir.write(texto);
				escribir.close();

			} catch (FileNotFoundException ex) {
				JOptionPane.showMessageDialog(null, "Error al guardar, ponga nombre al archivo");
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(null, "Error al guardar, en la salida");
			}

		}

		
		
		
		

	}
	
	