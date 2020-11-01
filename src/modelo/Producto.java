package modelo;

import java.io.Serializable;

public class Producto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3517932917890973544L;
	/**
	 * 
	 */
	private int cant_falta_stock;
	private int codigo;
	private String nombre;
	private double precio_costo;
	private double precio_venta;
	private int cant_stock;
	private int categoria;
	
	public Producto() {
	}
	
	@Override
	public String toString() {
		return (this.codigo + " - " + this.nombre);
		
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPrecio_costo() {
		return precio_costo;
	}

	public void setPrecio_costo(double precio_costo) {
		this.precio_costo = precio_costo;
	}

	public double getPrecio_venta() {
		return precio_venta;
	}

	public void setPrecio_venta(double precio_venta) {
		this.precio_venta = precio_venta;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public void setCant_falta_stock(int cant_falta_stock) {
		this.cant_falta_stock = cant_falta_stock;
	}

	public int getCant_falta_stock() {
		return cant_falta_stock;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public int getCant_stock() {
		return cant_stock;
	}

	public void setCant_stock(int cant_stock) {
		this.cant_stock = cant_stock;
	}
	
	public void sumarStock(int cantidad) {
		this.cant_stock += cantidad;
	}
	
	public Mensaje descontar(int cantidad) {
		if (this.getCant_stock()<cantidad) {
			return Mensaje.nostock;
		}
		this.setCant_stock(this.getCant_stock()-cantidad);
		return Mensaje.listo;
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}
	
}
