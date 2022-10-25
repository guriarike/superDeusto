
import java.awt.BorderLayout;

	import java.awt.EventQueue;

	import javax.swing.JFrame;
	import javax.swing.JPanel;
	import javax.swing.JPasswordField;
	import javax.swing.border.EmptyBorder;



	import javax.swing.JButton;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.JTextArea;
	import javax.swing.JTextField;
	import java.awt.event.ActionListener;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;
	import java.text.ParseException;
	import java.util.ArrayList;
	import java.util.TreeMap;
	import java.awt.event.ActionEvent;
	import java.awt.GridLayout;
	import java.awt.SystemColor;
	import java.awt.Color;



public class InicioSesionJaure {
	
	

		private JPanel contentPane;
		private JFrame ventanaActual,ventanaAnterior;
		private JLabel labeliniciosesion;
		private JPanel panelcentro,panelnorte,panelCentral;
		private JButton btnvolverinicio;
		private JLabel lblNewLabel;
		private static JTextField textUsuario;
		private JLabel lblNewLabel_1;
		private JPasswordField textContrasena;
		private JPanel panelSur;
		private JButton btnIniciosesion,btnRegistro;
		private static Connection con; 
		public static String usu;
		

		/**
		 * Launch the application.
		 */
		

		/**
		 * Create the frame.
		 */
		InicioSesionJaure(JFrame va) {
			
			
			ventanaActual = this;
			ventanaAnterior = va;
			setTitle("Ventana incio sesion");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 450, 300);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			setContentPane(contentPane);
			
			panelnorte = new JPanel();
			contentPane.add(panelnorte, BorderLayout.NORTH);
			panelnorte.setLayout(new BorderLayout(0, 0));
			
			panelcentro = new JPanel();
			panelnorte.add(panelcentro, BorderLayout.NORTH);
			panelcentro.setLayout(new BorderLayout(0, 0));
			
			btnvolverinicio = new JButton("volver inicio");
			btnvolverinicio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			panelnorte.add(btnvolverinicio, BorderLayout.EAST);
			
			labeliniciosesion = new JLabel("INICIAR SESION");
			labeliniciosesion.setBackground(SystemColor.inactiveCaptionBorder);
			panelnorte.add(labeliniciosesion, BorderLayout.CENTER);
			
			panelCentral = new JPanel();
			contentPane.add(panelCentral, BorderLayout.CENTER);
			panelCentral.setLayout(new GridLayout(2, 2, 0, 0));
			
			lblNewLabel = new JLabel("DNI");
			lblNewLabel.setBackground(SystemColor.activeCaptionBorder);
			panelCentral.add(lblNewLabel);
			
			textUsuario = new JTextField();
			textUsuario.setBackground(SystemColor.inactiveCaption);
			panelCentral.add(textUsuario);
			textUsuario.setColumns(10);
			
			lblNewLabel_1 = new JLabel("CONTRSE\u00D1A");
			lblNewLabel_1.setBackground(SystemColor.activeCaptionBorder);
			panelCentral.add(lblNewLabel_1);
			
			textContrasena = new JPasswordField();
			textContrasena.setBackground(SystemColor.inactiveCaption);
			panelCentral.add(textContrasena);
			textContrasena.setColumns(10);
			
			panelSur = new JPanel();
			panelSur.setBackground(SystemColor.activeCaption);
			contentPane.add(panelSur, BorderLayout.SOUTH);
			
			btnIniciosesion = new JButton("INICIAR SESION");
			btnIniciosesion.setBackground(SystemColor.window);
			
			panelSur.add(btnIniciosesion);
			
			setVisible(true);
			
			btnRegistro = new JButton("REGISTRO");
			btnRegistro.setBackground(SystemColor.window);
			panelSur.add(btnRegistro);
			btnvolverinicio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ventanaActual.dispose();
					ventanaAnterior.setVisible(true);
				}
			});
			btnRegistro.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
					new VentanaRegistro(ventanaActual);
					ventanaActual.setVisible(false);
				}
			});	
			btnIniciosesion.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							String dni = textUsuario.getText();
							String c = new String(textContrasena.getPassword());
							if(!dni.equals("") && !c.equals("")) {
								con = BD.initBD("hospital.db");
								int valor = comprobarUsuario();
								if(valor == 1) {
									int respuesta =BD.ObtenerMedico(con, dni, c);
									if(respuesta == 1) {
										JOptionPane.showMessageDialog(null, "La contrase�a no es correcta");
										
									}else if(respuesta == 2){
										JOptionPane.showMessageDialog(null, "Ongi etorri!");
										new VentanaConJCalendarMedico(ventanaActual);
										ventanaActual.setVisible(false);	
										
									}
									
							}else {
									int resultado = BD.ObtenerPaciente(con, dni, c);
									if(resultado == 0) {
										JOptionPane.showMessageDialog(null, "No est�s registrado");
										vaciarCampos();
									}else if(resultado==1) {
										JOptionPane.showMessageDialog(null, "La contrase�a no es correcta");
										vaciarContra();
									}else if(resultado == 2){
										JOptionPane.showMessageDialog(null, "Ongi etorri!");
										usu = dni;
										try {
											new VentanaConJCalendarPaciente(ventanaActual);
											ventanaActual.setVisible(false);
										} catch (ParseException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
										
									
									}
								}
									
							}
							
						}
			});
			
			
			
		}
		 /**
		  * metodo que vacia los campos de la contrase�a y el usuario
		  */
		private void vaciarCampos() {
			// TODO Auto-generated method stub
			textContrasena.setText("");
			textUsuario.setText("");
			
		}
		 /**
		  * metodo que vacia los campos de la contrase�a 
		  */
		private void vaciarContra() {
			// TODO Auto-generated method stub
			textContrasena.setText("");
			
			
		}
		/**
		 * metodo que comprueba que el usuario sea medico
		 * @return 1 si es correcto que sea medico sino 0
		 */
		private int comprobarUsuario() {
			ArrayList<String>medicos = BD.ObtenerMedicos(con);
			String dni = textUsuario.getText();
			int resul = 0;
			for (String m : medicos) {
				if(m.equals(dni)) {
					resul = 1;
				}
			}
			return resul;
			
		}
		/**
		 * metodo que obtiene el dni insertado por el usuario
		 * @return devuelve el dni
		 */
		public static String getDni() {
			String dni = textUsuario.getText();
			return dni;
			
		}
	}

	

}
