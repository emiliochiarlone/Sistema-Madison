package modelo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ListaDePrecios {
	
	private ArrayList<Producto> productos;
	private ArrayList<Categoria> categorias;
	
	public ListaDePrecios () {
		this.productos = new ArrayList<Producto>();
	}
	
	public ArrayList<Producto> getProductos() {
		return productos;
	}
	
	public void setProductos(ArrayList<Producto> productos) throws FileNotFoundException, IOException {
		this.productos = productos;
	}
	
	public Producto getProducto_byCodigo (int codigo_producto) {
		if (codigo_producto < 1) {
			return null;
		}
		for (Producto i : this.productos) {
			if (codigo_producto == i.getCodigo()) {
				return i;
			}
		}
		return null;
	}
	

	public int cantidadProductos() {
		return this.productos.size();
	}
	
	public double getPrecioCosto (int codigo) {
		if (getProducto_byCodigo(codigo) == null) {
			return 0;
		}
		return getProducto_byCodigo(codigo).getPrecio_costo();
	}

	public ArrayList<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(ArrayList<Categoria> categorias) {
		this.categorias = categorias;
	}
	
	public int getIdCategoria_byNombre(String nombre) {
		for (Categoria i : this.categorias) {
			if (i.getNombre().equals(nombre)) {
				return i.getId();
			}
		}
		return -1;
	}
	
	public String getNombreCategoria_byId(int id) {
		for (Categoria i : this.categorias) {
			if (i.getId() == id) {
				return i.getNombre();
			}
		}
		
		return "";
	}
	
	public void cargarCategoriasDesdeBD() {
		
		this.categorias = new ArrayList<Categoria>();
		MySQLStuff sql = new MySQLStuff();
		
		try {
			sql.setResult(sql.getStatement().executeQuery("SELECT * FROM categoria ORDER BY nombre ASC"));
			
			while (sql.getResult().next()) {
				Categoria categoria = new Categoria();
				categoria.setId(sql.getResult().getInt(1));
				categoria.setNombre(sql.getResult().getString(2));
				this.categorias.add(categoria);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			sql.close();
		}
		
	}
	
	public void agregarCategoriaBD(Categoria categoria) {
		MySQLStuff sql = new MySQLStuff();
		
		try {
			sql.getStatement().executeUpdate("INSERT INTO categoria VALUES (NULL , " 
											+ (char)34 + categoria.getNombre() + (char)34 + ")");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sql.close();
		}
	}
	
	public void cargarProductosDesdeBD() {
		
		this.productos = new ArrayList<Producto>();
		MySQLStuff sql = new MySQLStuff();
		
		try {
			sql.setResult(sql.getStatement().executeQuery("SELECT * FROM producto ORDER BY nombre ASC"));
			
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
	
public void cargarProductosDesdeBD_orderbyCat() {
		
		this.productos = new ArrayList<Producto>();
		MySQLStuff sql = new MySQLStuff();
		
		try {
			sql.setResult(sql.getStatement().executeQuery("SELECT * FROM producto ORDER BY categoria ASC, nombre ASC"));
			
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
	
	public void insertarProductoBD(Producto producto) { // Guarda el producto pasado por parametro en la BD
		MySQLStuff sql = new MySQLStuff();
		
		try {
			sql.getStatement().executeUpdate("INSERT INTO producto (codigo, nombre, cant_falta_stock, cant_stock, precio_costo, precio_venta, categoria)"
											+ " VALUES (" + String.valueOf(producto.getCodigo()) + ", " 
											+ (char)34 + producto.getNombre() + (char)34 + ", " + 
											String.valueOf(producto.getCant_falta_stock()) + ", " + 
											String.valueOf(producto.getCant_stock()) + ", " + String.valueOf(producto.getPrecio_costo()) + ", " +
											String.valueOf(producto.getPrecio_venta()) + ", "+ String.valueOf(producto.getCategoria()) + ")");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sql.close();
		}
	}
	
	public void modificarProductoBD(Producto producto) { //
		MySQLStuff sql = new MySQLStuff();
							
		try {
				sql.getStatement().executeUpdate("UPDATE producto SET nombre="+ (char)34 + producto.getNombre() + (char)34 + ", cant_falta_stock=" + 
							String.valueOf(producto.getCant_falta_stock()) + ", cant_stock=" + 
							String.valueOf(producto.getCant_stock()) + ", precio_costo= " + String.valueOf(producto.getPrecio_costo()) + ", precio_venta=" +
							String.valueOf(producto.getPrecio_venta()) + ", categoria=" + String.valueOf(producto.getCategoria()) + " WHERE codigo=" + String.valueOf(producto.getCodigo()));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sql.close();
		}
	}
	
	public void eliminarProductoBD(int codigo) {
		MySQLStuff sql = new MySQLStuff();
		
		try {
			sql.getStatement().executeUpdate("DELETE FROM producto WHERE codigo=" + String.valueOf(codigo));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sql.close();
		}
	}
	
	public void eliminarCategoriarBD(int id) {
		MySQLStuff sql = new MySQLStuff();
		
		try {
			sql.getStatement().executeUpdate("DELETE FROM categoria WHERE id=" + String.valueOf(id));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sql.close();
		}
	}
	
}
