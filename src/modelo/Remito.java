
package modelo;


import java.util.Date;
import java.util.ArrayList;


public class Remito {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4849454323778765413L;
	private int id;
	private double subtotal;
	private Date fecha;
	private int id_cliente;
	private double descuento;
	private int id_vendedor;
	private ArrayList<Detalle> listaDetalle;
	
	public Remito() {
		this.listaDetalle = new ArrayList<Detalle>();
	}
	
	public void agregarDetalle(Detalle detalle) {
		this.listaDetalle.add(detalle);
		subtotal += detalle.getTotal();
	}
	
	public void eliminarDetalle (Detalle detalle) {
		for (Detalle i : listaDetalle) {
			if (i.toString().equals(detalle.toString())) {
				listaDetalle.remove(i);
				subtotal -= detalle.getTotal();
				break;
			}
		}
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}


	public double getTotal() { // Retorna el total de la factura con el descuento incluido
		if (descuento>0) {
			return (subtotal-(subtotal*(descuento/100)));
		}
		else {
			return subtotal;
		}
	}

	public Date getFecha() {
		return fecha;
	}


	public void setFecha(java.util.Date date) {
		this.fecha = (Date) date;
	}
	
	
	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public int getId_vendedor() {
		return id_vendedor;
	}

	public void setId_vendedor(int id_vendedor) {
		this.id_vendedor = id_vendedor;
	}

	public ArrayList<Detalle> getListaDetalle() {
		return listaDetalle;
	}

	public void setListaDetalle(ArrayList<Detalle> listaDetalle) {
		this.listaDetalle = listaDetalle;
	}

	@SuppressWarnings("deprecation")
	@Override
	
	public String toString() {
		return Integer.toString(fecha.getDate()) + "/" + Integer.toString(fecha.getMonth()+1) + "/" + Integer.toString(fecha.getYear()+1900) + " Cliente " + id_cliente+ "  $" + Double.toString(this.getTotal());
	}

	
	public String dateToString() {
		return Integer.toString(fecha.getDate()) + "/" + Integer.toString(fecha.getMonth()+1) + "/" + Integer.toString(fecha.getYear()+1900);
	}
	
	public String dateToString_BD() {
		return Integer.toString(fecha.getYear()+1900) + "-" + Integer.toString(fecha.getMonth()+1) + "-" + Integer.toString(fecha.getDate());
	}
	
}

