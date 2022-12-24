package es.deusto.prog3.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

import es.deusto.prog3.g01.Compra;
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
		JLabel labelPrecioTotal = new JLabel("El precio total de su compra es:" + calcularPrecioTotal());
		labelPrecioTotal.setBackground(Color.red);
		pOeste.add(labelPrecioTotal, BorderLayout.SOUTH);

		getContentPane().add(pOeste, BorderLayout.WEST);

		// AQUI AÑADIMOS OTRAS COSAS DE LA VENTANA
		JScrollPane scrollPane = new JScrollPane();
		JTextArea textArea = new JTextArea(
				"Si pulsa el botón 'Finalizar compra' crearemos un fichero con un ticket en el archivo que escoja en su ordenador.\n"
						+ " - Si hace click encima de un producto podra eliminarlo de la compra. \n"
						//S+ " - Si hace click mientras mantiene la tecla 'CNTRL' sera capaz de cambiar la cantidad."
						);
		scrollPane.setViewportView(textArea);

		JButton botonFinalizar = new JButton("FINALIZAR COMPRA");

		getContentPane().add(scrollPane, BorderLayout.CENTER);
		JPanel pBotonera = new JPanel();
		pBotonera.add(botonFinalizar);
		getContentPane().add(pBotonera, BorderLayout.SOUTH);

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
					label.setBackground(Color.blue);
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

		// Mouse Listeners
		tProductos.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				int row = tProductos.rowAtPoint(e.getPoint());
				int col = tProductos.columnAtPoint(e.getPoint());

				System.out.println(String.format("Se ha salido de la fila %d, columna %d", e.getButton(), row, col));

				// Cuando el ratón sale de la tabla, se resetea la columna/fila sobre la que
				// está el ratón
				mouseRow = -1;
				mouseCol = -1;
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				
				int row = tProductos.rowAtPoint(e.getPoint());
				// id del producto
				int id = (int) tProductos.getValueAt(row, 0);
				String decision = JOptionPane.showInputDialog(null, "Desea borrar este producto de su compra?(S/N)");
				if (decision == "S" || decision == "s") {
					tProductos.removeRowSelectionInterval(id - 1, id + 1);
					tProductos.repaint();
				} else {

				}
				/*
				 * try { if(!mapaCarrito.keySet().contains((Integer)id)) { mapaCarrito.put(id,
				 * number); System.out.println(GestorBD.ProductoPorId(id).getNombreProducto()
				 * +"- cantidad ="+number); }else { mapaCarrito.put(id, number); }
				 * }catch(SQLException ee) {
				 * 
				 * }
				 */

			}
		});

	
		// ACTION LISTENER
		botonFinalizar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				crearTicket();

			}
		});

	}

	public void crearTicket() {

		// Creamos un filechooser y guardamos la ruta y el nombre de la carpeta
		JFileChooser guardar = new JFileChooser();
		guardar.showSaveDialog(null);
		guardar.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		File archivo = guardar.getSelectedFile();

		// contenido del ticket
		String devolver = "Ticket SuperDeusto!\n";
		double precioTotal = 0;
		int i = 0;

		try {
			for (i = 0; i < tProductos.getRowCount(); i++) {
				Producto p = GestorBD.ProductoPorId((Integer) tProductos.getValueAt(i, 0));
				precioTotal = precioTotal + p.getPrecioProducto();
				devolver = devolver + "Nombre :" + p.getNombreProducto() + "                   ---- Precio :"
						+ p.getPrecioProducto() + "                            ---- Cantidad :"
						+ tProductos.getValueAt(i, 3) + "\n";

			}
		} catch (Exception e) {

		}

		devolver = devolver + "\n" + "El precio total de la compra es-> \t" + precioTotal + " Euros";
		System.out.println(devolver);

		// le pasamos al metodo para crear el fichero el nombre , la ruta y 'devolver'
		guardarFichero(devolver, archivo);

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

	public double calcularPrecioTotal() {
		double precioTotal = 0;
		int i = 0;
		try {
			for (i = 0; i < tProductos.getRowCount(); i++) {
				Producto p = GestorBD.ProductoPorId((Integer) tProductos.getValueAt(i, 0));
				precioTotal = precioTotal + p.getPrecioProducto();

			}

		} catch (Exception e) {

		}

		return precioTotal;
	}
	public void crearCompra() {
		Compra compra = new Compra();
		ArrayList<Producto> productosCompra = new ArrayList<>();
		int i = 0;
		try {
			for (i = 0; i < tProductos.getRowCount(); i++) {
				Producto p = GestorBD.ProductoPorId((Integer) tProductos.getValueAt(i, 0));
				productosCompra.add(p);

			}

		} catch (Exception e) {

		}
		
		Date myDate = new Date(System.currentTimeMillis());
		compra.setFechaCompra(myDate);
		compra.setListaProductosDeCompra(productosCompra);
		compra.setPrecioCompra(calcularPrecioTotal());
		
		
	}
}
