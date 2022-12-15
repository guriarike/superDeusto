package es.deusto.prog3.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import es.deusto.prog3.g01.GestorBD;
import es.deusto.prog3.g01.Producto;
import es.deusto.prog3.g01.Usuario;

public class VentanaCarrito extends JFrame {

	int mouseRow = -1;
	int mouseCol = -1;
	private DefaultTableModel mProductos = new DefaultTableModel(
			new Object[] { "Id", "Nombre", "PrecioProducto", "Cantidad" }, 0);
	private JTable tProductos = new JTable(mProductos);

	public VentanaCarrito(Usuario usuario, Map<Integer, Integer> mapaCarrito, JFrame ventanaAnterior) {
		JFrame ventanaActual = this;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(900, 600);
		setTitle("Ventana de administración de Carrito");

		// Panel centro
		JPanel pOeste = new JPanel(new BorderLayout()); // Lista centro
		pOeste.setBackground(Color.GRAY);
		JLabel l = new JLabel("ESTOS SON LOS PRODUCTOS DE SU CARRITO");
		pOeste.add(l, BorderLayout.NORTH);
		// Añado la tabla en el medio
		JScrollPane scroll = new JScrollPane(tProductos);
		pOeste.add(scroll, BorderLayout.CENTER);
		// Añado un label con el precio Total
		int precioTotal = 0;
		/*
		 * for(int i= 0; i<tProductos.getRowCount();i++) { precioTotal = precioTotal +
		 * (Integer)tProductos.getValueAt(i, 3)* (Integer)tProductos.getValueAt(i, 2); }
		 */
		JLabel labelPrecioTotal = new JLabel("El precio total de su compra es:" + precioTotal);
		labelPrecioTotal.setBackground(Color.red);
		pOeste.add(labelPrecioTotal, BorderLayout.SOUTH);

		getContentPane().add(pOeste, BorderLayout.WEST);

		// AQUI AÑADIMOS OTRAS COSAS DE LA VENTANA
		JPanel pEste = new JPanel(new BorderLayout());
		JTextArea textAviso = new JTextArea("POR FAVOR REVISE SI LOS PRODUCTOS DE SU CARRITO SON LOS" + "CORRECTOS.\n");
		JTextArea textAviso1 = new JTextArea("\"PARA BORRAR UN PRODUCTO DEL CARRITO HAGA CLICK EN EL.");
		JTextArea textAviso2 = new JTextArea("PARA CAMBIAR LA CANTIDAD PULSE 'CONTROL' Y HAGA CLICK ENCIMA.");
		JTextArea textAviso3 = new JTextArea(
				"SI PULSA EL BOTON 'FINALIZAR COMPRA' CREAREMOS UN FICHERO CON UN TICKET EN EL ARCHIVO QUE ESCOJA EN SU ORDENADOR.");
		JTextArea textAviso4 = new JTextArea("¡GRACIAS!");

		JButton botonFinalizar = new JButton("FINALIZAR COMPRA");

		pEste.add(textAviso, BorderLayout.SOUTH);
		pEste.add(textAviso1, BorderLayout.SOUTH);
		pEste.add(textAviso2, BorderLayout.SOUTH);
		pEste.add(textAviso3, BorderLayout.SOUTH);
		pEste.add(textAviso4, BorderLayout.SOUTH);
		pEste.add(botonFinalizar, BorderLayout.SOUTH);
		getContentPane().add(pEste, BorderLayout.EAST);

		/*
		 * 
		 * RECUPERAR TODOS LOS PRODUCTOS DEL CARRITO Y METERLOS EN EL MODELO
		 */
		try {
			for (int id : mapaCarrito.keySet()) {
				Producto p = GestorBD.ProductoPorId(id);
				this.mProductos.addRow(new Object[] { p.getIdProducto(), p.getNombreProducto(), p.getPrecioProducto(),
						mapaCarrito.get(id) });
			}
		} catch (Exception e) {
		}

		// renderers

		DefaultTableCellRenderer numRenderer = new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				JLabel label = new JLabel(value.toString());
				label.setHorizontalAlignment(JLabel.CENTER);

				// Se diferencia el color de fondo en filas pares e impares
				if (row % 2 == 0) {
					label.setBackground(new Color(224, 224, 224));
				} else {
					label.setBackground(Color.WHITE);
				}

				// Si la celda está seleccionada se asocia un color de fondo y letra
				if (mouseRow == row && mouseCol == column) {
					label.setBackground(Color.orange);
					label.setForeground(Color.WHITE);
				}

				// Si la celda está seleccionada se asocia un color de fondo y letra
				if (isSelected) {
					label.setBackground(Color.blue);
					label.setForeground(table.getSelectionForeground());
				}

				// Es necesaria esta sentencia para pintar correctamente el color de fondo
				label.setOpaque(true);

				return label;
			}
		};
		DefaultTableCellRenderer textRenderer = new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				JLabel label = new JLabel(value.toString());
				label.setHorizontalAlignment(JLabel.LEFT);

				// Se diferencia el color de fondo en filas pares e impares
				if (row % 2 == 0) {
					label.setBackground(new Color(224, 224, 224));
				} else {
					label.setBackground(Color.WHITE);
				}

				// Si la celda está seleccionada se asocia un color de fondo y letra
				if (mouseRow == row && mouseCol == column) {
					label.setBackground(Color.orange);
					label.setForeground(Color.WHITE);
				}

				// Si la celda está seleccionada se asocia un color de fondo y letra
				if (isSelected) {
					label.setBackground(table.getSelectionBackground());
					label.setForeground(table.getSelectionForeground());
				}

				// Es necesaria esta sentencia para pintar correctamente el color de fondo
				label.setOpaque(true);

				return label;
			}
		};

		// implementamos render

		this.tProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// Se modifica el Renderer de las columnas
		this.tProductos.getColumnModel().getColumn(0).setCellRenderer(numRenderer); // ID
		this.tProductos.getColumnModel().getColumn(1).setCellRenderer(textRenderer); // NOMBRE
		this.tProductos.getColumnModel().getColumn(2).setCellRenderer(numRenderer); // PRECIO
		this.tProductos.getColumnModel().getColumn(3).setCellRenderer(numRenderer); // CANTIDAD
		
		
		
		
		
		//ACTION LISTENER 
		botonFinalizar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				crearTicket();
				
				
			}
		});

	}

	public void crearTicket() {
		
		// Aqui falta poner un JFileChooser y crear un fichero te texto con 'devolver'
		String devolver = "Ticket SuperDeusto!\n";
		double precioTotal = 0;
		int i = 0;
		
		try {
			for (i = 0; i < tProductos.getRowCount(); i++) {
				Producto p = GestorBD.ProductoPorId((Integer) tProductos.getValueAt(i, 0));
				precioTotal =  precioTotal + p.getPrecioProducto();
				devolver = devolver + "Nombre :"+p.getNombreProducto()+"                   ---- Precio :"+p.getPrecioProducto()+"                            ---- Cantidad :"+tProductos.getValueAt(i, 3)+"\n";
				
				
				
				

			}
		} catch (Exception e) {

		}
		
		devolver = devolver +"\n"+"El precio total de la compra es-> \t"+precioTotal+" Euros";
		System.out.println(devolver);

	}

}
