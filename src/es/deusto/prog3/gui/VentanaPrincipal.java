package es.deusto.prog3.gui;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import es.deusto.prog3.g01.GestorBD;
import es.deusto.prog3.g01.Producto;
import es.deusto.prog3.g01.Seccion;
import es.deusto.prog3.g01.Usuario;

public class VentanaPrincipal extends JFrame{
	private List<Usuario>lUsuarios;
	int mouseRow = -1;
	int mouseCol = -1;
	private DefaultListModel<Seccion> mSeccion = new DefaultListModel<>();
	private DefaultListModel<Seccion> mCarrito= new DefaultListModel<>();
	private JList<Seccion> lSecciones = new JList<>( mSeccion );
	private JList<Seccion> lCarrito = new JList<>( mCarrito );
	
	private DefaultTableModel mProductos = new DefaultTableModel(
				new Object[] { "Id", "Nombre", "Marca","PrecioProducto" }, 0
			);
	private JTable tProductos = new JTable( mProductos );
	
	
	
	//CONSTRUCTOR DE LA VENTANA
	public VentanaPrincipal() {
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setSize( 900, 600 );
		setTitle( "Ventana de administración de Deusto BeReal" );
		JPanel pNorte = new JPanel(); // Panel norte
			JButton botonVolver = new JButton("VOLVER");
			JButton botonCuenta = new JButton("CUENTA");
			JButton botonCarrito= new JButton("CARRITO");
			pNorte.add( botonVolver );
			pNorte.add( botonCuenta );
			pNorte.add( botonCarrito);
			getContentPane().add( pNorte, BorderLayout.NORTH );
		JSplitPane pOeste = new JSplitPane( JSplitPane.VERTICAL_SPLIT ); // Listas oeste
			JPanel pSecciones = new JPanel( new BorderLayout() );
			pSecciones.add( new JLabel( "Secciones:" ), BorderLayout.NORTH );
			pSecciones.add( new JScrollPane(lSecciones), BorderLayout.CENTER );
			pSecciones.add( new JLabel( "Productos en tu carrito:" ), BorderLayout.SOUTH);
			pSecciones.add( new JScrollPane(lCarrito), BorderLayout.CENTER );
			pOeste.setTopComponent( pSecciones );
			getContentPane().add(pOeste,BorderLayout.WEST);
		JPanel pPrincipal = new JPanel( new BorderLayout() ); // Panel central (tabla)
			pPrincipal.add( new JLabel( "Productos de la seccion:" ), BorderLayout.NORTH );
			pPrincipal.add( new JScrollPane( tProductos ), BorderLayout.CENTER );
			getContentPane().add( pPrincipal, BorderLayout.CENTER );
		
		JPanel pBotonera = new JPanel(); // Panel inferior (botonera)
		JButton botonComprar = new JButton("Finalizar compra");
			pBotonera.add( botonComprar );
			getContentPane().add( pBotonera, BorderLayout.SOUTH );
		this.setVisible(true);
		
		
		
		// INICIALIZAR CON DATOS
		ArrayList<Producto> todosLosProductos = null;
		try {
			todosLosProductos = GestorBD.todosLosProductos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		for (Producto p : todosLosProductos) {
			this.mProductos.addRow( new Object[] {p.getIdProducto(), p.getNombreProducto(), p.getMarca(), p.getPrecioProducto()} );
		}		
		
	}
	

}
