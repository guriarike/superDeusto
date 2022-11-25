package es.deusto.prog3.gui;

import java.awt.BorderLayout;
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

import es.deusto.prog3.g01.Seccion;
import es.deusto.prog3.g01.User;

public class VentanaPrincipal extends JFrame{
	private List<User>lUsuarios;
	int mouseRow = -1;
	int mouseCol = -1;
	private DefaultListModel<Seccion> mSeccion = new DefaultListModel<>();
	private JList<Seccion> lSecciones = new JList<>( mSeccion );
	
	private DefaultTableModel mProductos = new DefaultTableModel(
				new Object[] { "Id", "Nombre", "Marca","PrecioProducto","Seccion" }, 0
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
		
		
	}
	

}
