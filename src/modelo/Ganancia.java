package modelo;

import java.util.Date;

public class Ganancia {
	
	private int factura_id;
	private double monto;
	private Date fecha;
	
	public int getFactura_id() {
		return factura_id;
	}
	public void setFactura_id(int factura_id) {
		this.factura_id = factura_id;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(java.util.Date date) {
		this.fecha = date;
	}
	
	public String dateToString() {
		return Integer.toString(fecha.getDate()) + "/" + Integer.toString(fecha.getMonth()+1) + "/" + Integer.toString(fecha.getYear()+1900);
	}
	
	public String dateToString_BD() {
		return Integer.toString(fecha.getYear()+1900) + "-" + Integer.toString(fecha.getMonth()+1) + "-" + Integer.toString(fecha.getDate());
	}
}
