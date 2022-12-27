package es.deusto.prog3.gui;

import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import es.deusto.prog3.g01.Compra;
import es.deusto.prog3.g01.GestorBD;
import es.deusto.prog3.g01.Usuario;

public class VentanaCuenta extends JFrame {

	// Compras del usuario
	private List<Compra> compras;
	// Elementos de la ventana
	private JTable tCompras;
	private DefaultTableModel mCompras =  new DefaultTableModel(
			new Object[] { "Fecha", "Precio" }, 0);
	
	
	private JTextArea areaDetalles;
	private JScrollPane scrollCompras;
	private JScrollPane scrollDetalles;
	// Valores para implementar la modificación del renderizado
	// cuando el ratón pasa sobre una celda de la tabla
	private int mouseRow = -1;
	private int mouseCol = -1;
	
	
	
	// constructor
	
	public VentanaCuenta(Usuario u) {
		// Cargamos las compras del usuario
		try {
			compras = GestorBD.comprasDeUnUsuario(u);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Elementos
		scrollCompras = new JScrollPane(tCompras);
		scrollDetalles = new JScrollPane(areaDetalles);
		this.getContentPane().setLayout(new GridLayout(2, 1));
		this.getContentPane().add(scrollCompras);
		this.getContentPane().add(scrollDetalles);
		
		this.setTitle("Ventana Cuenta");		
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setVisible(true);	
		
		//Inicializamos la tabla
				initTabla();
	}
		
		
		
		
		
		
		
		
	
	public void initTabla() {
		for(Compra c:compras) {
			this.mCompras.addRow(new Object[] { c.getFechaCompra(), c.getPrecioCompra() });
		}
		
		
	}

}
