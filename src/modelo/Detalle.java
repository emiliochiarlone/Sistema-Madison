package modelo;

public class Detalle {
	
	private int producto_codigo;
	private int remito_id;
	private int cantidad;
	private double precio;
	private double descuento;
	
	
	public int getProducto_codigo() {
		return producto_codigo;
	}

	public void setProducto_codigo(int codigo_producto) {
		this.producto_codigo = codigo_producto;
	}

	public int getRemito_id() {
		return remito_id;
	}

	public void setFactura_id(int remito_id) {
		this.remito_id = remito_id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}
	
	public double getTotal() {
		if (this.descuento>0) {
			double subtotal = this.precio*this.cantidad;
			
			double descuento = subtotal*(this.descuento/100);
			return (subtotal-descuento);
		}
		else {
			return this.precio*this.cantidad;
		}
	}
	
	public String toString() {
		if (descuento>0) {
			return "Código=" + producto_codigo + "   Cantidad=" + cantidad + "   P.Unitario= " + precio + "   Monto=" + this.getTotal() + "  Descuento=" + descuento + "%";
		}
		return "Código=" + producto_codigo + "   Cantidad=" + cantidad + "   P.Unitario= " + precio + "   Monto=" + this.getTotal();
	}

}
	
