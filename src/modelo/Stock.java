package modelo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;


public class Stock implements Serializable, iAlmacenable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4845375424214968056L;
	/**
	 * 
	 */
	private ArrayList<Producto> productos;
	
	
	public Stock() {
		this.productos = new ArrayList<Producto>();
		
	}
	
	public Mensaje NuevaCompra(int codigo, int cantidad) {
		
		Producto producto = this.getProducto_byCodigo(codigo);
		if (producto != null) {
			producto.sumarStock(cantidad);
			if (this.enFalta(codigo)) {
				return Mensaje.enfalta;
			}
			return Mensaje.listo;
		}
		return Mensaje.noexiste;
	}
	

	public ArrayList<Producto> getProductos() {
		return productos;
	}

	public void setProductos(ArrayList<Producto> productos) {
		this.productos = productos;
	}
	
	private boolean enFalta(int codigo) {
		Producto producto = this.getProducto_byCodigo(codigo);
		if (producto.getCant_stock() <= producto.getCant_falta_stock()) {
			return true;
		}
		return false;
	}
	
	
	public int getCantidadProducto (int codigo) {
		
		if (codigo<1) {
			return -1;
		}
		
		Producto producto = this.getProducto_byCodigo(codigo);
		if (producto != null) {
			return producto.getCant_stock();
		}
		return -1;
	}
	
	public boolean ventaEsPosible (Remito remito) {   // Mensaje para saber si hay stock para registrar dicha venta.
		ArrayList<Detalle> listadetalle_factura = remito.getListaDetalle();
		if (listadetalle_factura.isEmpty()) {
			return false;
		}
		for (Detalle i : listadetalle_factura) {
			for (Producto j : this.productos) {
				if (i.getProducto_codigo() == j.getCodigo()) {
						if (i.getCantidad()>j.getCant_stock()) {
							return false;
					}
				}
			}
		}
		return true;
	}
	
	public ArrayList<Producto> calcularFaltas() { // retorna array con los productos en falta
		ArrayList<Producto> retorno = new ArrayList<Producto>();
		for (Producto i: this.productos) {
			if (this.enFalta(i.getCodigo())) {
				retorno.add(i);
			}
		}
		return retorno;
	}
	


	public double getValorTotal() { // Retorna el valor total de stock (precios de compra*cantidad)
		double total = 0;
		for (Producto i : this.productos) {
			total += (i.getPrecio_costo()) * (this.getCantidadProducto(i.getCodigo()));
		}
		return total;
	}
	
	public boolean faltaStock () { // Si alguno de los productos está en falta, retorna true
		for (Producto i : this.productos) {
			if (enFalta(i.getCodigo())) { // Si hay menos cantidad en stock que la cantidad de aviso, se terminará la función retornando true
				return true;
			}
		}
		return false;
	}
	
	public boolean productoRegistradoEnStock(int codigo) { //Si se maneja stock del producto, retorna true. No importa si la cantidad del producto en stock es cero.
		if (this.getProducto_byCodigo(codigo) != null) { 
			return true;
		}
		return false;
	} 
	
	public Producto getProducto_byCodigo(int codigo) {
		if (codigo<1) { // valida parámetro
			return null;
		}
		
		for (Producto i: this.productos) {
			if (i.getCodigo() == codigo) {
				return i;
			}
		}
		return null;
	}
	
	public void cargarStockDesdeBD() {
		
		this.productos = new ArrayList<Producto>();
		MySQLStuff sql = new MySQLStuff();
		
		try {
			sql.setResult(sql.getStatement().executeQuery("SELECT * FROM producto WHERE cant_stock>-1 ORDER BY nombre ASC"));
			
			while (sql.getResult().next()) {
				Producto producto = new Producto();
				producto.setCodigo(sql.getResult().getInt(1));
				producto.setNombre(sql.getResult().getString(2));
				producto.setCant_falta_stock(sql.getResult().getInt(3));
				producto.setCant_stock(sql.getResult().getInt(4));
				producto.setPrecio_costo(sql.getResult().getDouble(5));
				producto.setPrecio_venta(sql.getResult().getDouble(6));
				producto.setCategoria(sql.getResult().getInt(7));
		
				this.productos.add(producto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			sql.close();
		}
		
	}
	
	public void guardarStockBD() {
		MySQLStuff sql = new MySQLStuff();
		
		try {
			if (this.productos != null) {
				for (Producto i: this.productos) {
					sql.getStatement().executeUpdate("UPDATE producto set cant_stock=" + i.getCant_stock() + " WHERE codigo=" + String.valueOf(i.getCodigo()));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sql.close();
		}
	}
	
	public void eliminarProductoDeStockBD(int codigo) {
		MySQLStuff sql = new MySQLStuff();
		
		try {
			sql.getStatement().executeUpdate("UPDATE producto set cant_falta_stock=-1, cant_stock=-1 WHERE codigo=" + String.valueOf(codigo));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sql.close();
			this.cargarStockDesdeBD();
		}
	}
	
	public void agregarProductoAStockBD(int codigo) {
		MySQLStuff sql = new MySQLStuff();
		
		try {
			sql.getStatement().executeUpdate("UPDATE producto set cant_stock=0 WHERE codigo=" + String.valueOf(codigo));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sql.close();
			this.cargarStockDesdeBD();
		}
	}
	
}
