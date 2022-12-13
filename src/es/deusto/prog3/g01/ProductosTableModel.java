package es.deusto.prog3.g01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ProductosTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 1L;
	private final List<String> headers = Arrays.asList( "Id", "Nombre", "Marca", "Precio", "Seccion");
	private ArrayList<Producto> productos;
	public ProductosTableModel(ArrayList<Producto> productos) {
		this.productos = productos;
	}
	@Override
	public String getColumnName(int column) {
		return headers.get(column);
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return productos.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return headers.size(); 
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Producto producto = productos.get(rowIndex);
		switch (columnIndex) {
		case 0: return producto.getIdProducto();
		case 1: return producto.getNombreProducto();
		case 2: return producto.getMarca();
		case 3: return producto.getPrecioProducto();
		case 4: return producto.getSeccion();
		default: return null;
	}
		
	}
	

}
