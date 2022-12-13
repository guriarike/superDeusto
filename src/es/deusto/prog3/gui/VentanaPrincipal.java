package es.deusto.prog3.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import es.deusto.prog3.g01.GestorBD;
import es.deusto.prog3.g01.Producto;
import es.deusto.prog3.g01.ProductosTableModel;
import es.deusto.prog3.g01.Seccion;
import es.deusto.prog3.g01.Usuario;

public class VentanaPrincipal extends JFrame {
	private List<Usuario> lUsuarios;
	int mouseRow = -1;
	int mouseCol = -1;
	private DefaultListModel<Seccion> mSeccion = new DefaultListModel<>();

	private DefaultTableModel mProductos = new DefaultTableModel(
			new Object[] { "Id", "Nombre", "Marca", "PrecioProducto", "Seccion" }, 0);
	private JTable tProductos = new JTable(mProductos);

	private JTextField textFiltroSeccion = new JTextField();
	private JComboBox<String> comboFiltro = new JComboBox<>();

	// CONSTRUCTOR DE LA VENTANA
	public VentanaPrincipal(Usuario u, JFrame ventanaAnterior) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(900, 600);
		setTitle("Ventana de administración de Deusto BeReal");
		JPanel pNorte = new JPanel(); // Panel norte
		JButton botonVolver = new JButton("VOLVER");
		JButton botonCuenta = new JButton("CUENTA");
		JButton botonCarrito = new JButton("CARRITO");
		pNorte.add(botonVolver);
		pNorte.add(botonCuenta);
		pNorte.add(botonCarrito);
		getContentPane().add(pNorte, BorderLayout.NORTH);
		JSplitPane pOeste = new JSplitPane(JSplitPane.VERTICAL_SPLIT); // Listas oeste
		JPanel pFiltro = new JPanel(new BorderLayout());
		pFiltro.add(new JLabel("HOLA ," + u.getNombre()), BorderLayout.NORTH);

		JPanel panelFiltros = new JPanel(new FlowLayout());
		panelFiltros.add(new JLabel("Filtrar por-->"));
		comboFiltro.addItem("Seccion");
		comboFiltro.addItem("Marca");
		comboFiltro.addItem("Nombre");

		panelFiltros.add(comboFiltro);

		pFiltro.add(panelFiltros, BorderLayout.CENTER);
		pFiltro.add(textFiltroSeccion, BorderLayout.SOUTH);
		pOeste.setTopComponent(pFiltro);
		getContentPane().add(pOeste, BorderLayout.WEST);
		JPanel pPrincipal = new JPanel(new BorderLayout()); // Panel central (tabla)
		pPrincipal.add(new JLabel("Productos de la seccion:"), BorderLayout.NORTH);
		pPrincipal.add(new JScrollPane(tProductos), BorderLayout.CENTER);
		getContentPane().add(pPrincipal, BorderLayout.CENTER);

		JPanel pBotonera = new JPanel(); // Panel inferior (botonera)
		JButton botonComprar = new JButton("Finalizar compra");
		pBotonera.add(botonComprar);
		getContentPane().add(pBotonera, BorderLayout.SOUTH);
		this.setVisible(true);

		// INICIALIZAR CON DATOS
		ArrayList<Producto> todosLosProductos = null;

		try {
			todosLosProductos = GestorBD.todosLosProductos();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;
		for (Producto p : todosLosProductos) {
			this.mProductos.addRow(new Object[] { p.getIdProducto(), p.getNombreProducto(), p.getMarca(),
					p.getPrecioProducto(), p.getSeccion() });
		}

		// configuracion del filtrado

		// esto para tener listener de lo que se esta escribiendo
		textFiltroSeccion.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				// selectRows(textFiltroSeccion.getText());

				tProductos.repaint();
			}

			public void removeUpdate(DocumentEvent e) {
				// selectRows(textFiltroSeccion.getText());
				tProductos.repaint();
			}

			public void insertUpdate(DocumentEvent e) {
				// selectRows(textFiltroSeccion.getText());
				tProductos.repaint();
			}
		});

		/*
		 * if (textFiltroSeccion.getText() != null &&
		 * !textFiltroSeccion.getText().isEmpty()) { if (comboFiltro.getSelectedItem()
		 * == "Seccion") { for (Producto prod : todosLosProductos) { if
		 * (prod.getSeccion().startsWith(textFiltroSeccion.getText())) { // starts with
		 * productosFiltrados.add(prod); } } llenarModelo(productosFiltrados);
		 * 
		 * } if (comboFiltro.getSelectedItem() == "Marca") { for (Producto prod :
		 * todosLosProductos) { if
		 * (prod.getMarca().startsWith(textFiltroSeccion.getText())) {
		 * productosFiltrados.add(prod); } } llenarModelo(productosFiltrados);
		 * 
		 * } if (comboFiltro.getSelectedItem() == "Nombre") { for (Producto prod :
		 * todosLosProductos) { if
		 * (prod.getNombreProducto().startsWith(textFiltroSeccion.getText())) {
		 * productosFiltrados.add(prod); } } llenarModelo(productosFiltrados);
		 * 
		 * } }else { llenarModelo(todosLosProductos); }
		 */

		/*
		 * 
		 * 
		 * 
		 * CAMBIOS EN LA TABLA PRODUCTOS ( RENDERER , SELECTION MODE ETC)
		 * 
		 * 
		 * 
		 */
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
					label.setBackground(table.getSelectionBackground());
					label.setForeground(table.getSelectionForeground());
				}

				// filtro
				ArrayList<Producto> todosLosProductos = null;

				try {
					todosLosProductos = GestorBD.todosLosProductos();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				;
				if (textFiltroSeccion.getText() != null && !textFiltroSeccion.getText().isEmpty()) {
					if (comboFiltro.getSelectedItem() == "Seccion") {
						String sec = (String) tProductos.getValueAt(row, 4);

						if (sec.startsWith(textFiltroSeccion.getText())) {
							// starts with
							label.setBackground(Color.red);
						}

					}
					if (comboFiltro.getSelectedItem() == "Marca") {
						String marc = (String)tProductos.getValueAt(row, 2);
							if (marc.startsWith(textFiltroSeccion.getText())) {
								label.setBackground(Color.red);
							}
						

					}
					if (comboFiltro.getSelectedItem() == "Nombre") {
						String nom = (String)tProductos.getValueAt(row, 1);
						
							if (nom.startsWith(textFiltroSeccion.getText())) {
								label.setBackground(Color.red);
							}
						

					}
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
				// filtro
				if (textFiltroSeccion.getText() != null && !textFiltroSeccion.getText().isEmpty()) {
					if (comboFiltro.getSelectedItem() == "Seccion") {
						String sec = (String) tProductos.getValueAt(row, 4);

						if (sec.startsWith(textFiltroSeccion.getText())) {
							// starts with
							label.setBackground(Color.red);
						}

					}
					if (comboFiltro.getSelectedItem() == "Marca") {
						String marc = (String)tProductos.getValueAt(row, 2);
							if (marc.startsWith(textFiltroSeccion.getText())) {
								label.setBackground(Color.red);
							}
						

					}
					if (comboFiltro.getSelectedItem() == "Nombre") {
						String nom = (String)tProductos.getValueAt(row, 1);
						
							if (nom.startsWith(textFiltroSeccion.getText())) {
								label.setBackground(Color.red);
							}
						

					}
				}
				// Es necesaria esta sentencia para pintar correctamente el color de fondo
				label.setOpaque(true);

				return label;
			}
		};

		this.tProductos.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				// Se obtiene la fila/columna sobre la que están el ratón mientras se mueve
				int row = tProductos.rowAtPoint(e.getPoint());
				int col = tProductos.columnAtPoint(e.getPoint());

				// Cuando el ratón se mueve sobre tabla, actualiza la fila/columna sobre la que
				// está el ratón
				// de esta forma se puede modificar el color de renderizado de la celda.
				mouseRow = row;
				mouseCol = col;

				// Se fuerza el redibujado de la tabla para modificar el color de la celda sobre
				// la que está el ratón.
				tProductos.repaint();
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				int row = tProductos.rowAtPoint(e.getPoint());
				int col = tProductos.columnAtPoint(e.getPoint());

				System.out.println(
						String.format("Se está arrastrando con el botón %d pulsado sobre la fila %d, columna %d",
								e.getButton(), row, col));
			}
		});

		this.tProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// Se modifica el Renderer de las columnas
		this.tProductos.getColumnModel().getColumn(0).setCellRenderer(numRenderer); // ID
		this.tProductos.getColumnModel().getColumn(1).setCellRenderer(textRenderer); // NOMBRE
		this.tProductos.getColumnModel().getColumn(2).setCellRenderer(textRenderer); // MARCA
		this.tProductos.getColumnModel().getColumn(3).setCellRenderer(numRenderer); // PRECIO
		this.tProductos.getColumnModel().getColumn(4).setCellRenderer(textRenderer);// Seccion

	}

	private void llenarModelo(ArrayList<Producto> lista) {
		for (int i = 0; i < tProductos.getRowCount(); i++) {
			mProductos.removeRow(i);
			i -= 1;
		}
		tProductos.setModel(new ProductosTableModel(lista));

	}

}
