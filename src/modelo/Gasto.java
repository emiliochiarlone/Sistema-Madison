package modelo;

import java.io.Serializable;
import java.util.Date;

public class Gasto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2722144698631684934L;
	private int id;
	private Date fecha;
	private String descripcion;
	private double monto;
	
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Gasto() {
		this.fecha = new Date();
		this.descripcion = "";
		this.monto = 0;
	}
	
	public String dateToString() {
		return Integer.toString(fecha.getDate()) + "/" + Integer.toString(fecha.getMonth()+1) + "/" + Integer.toString(fecha.getYear()+1900);
	}
	
	@Override
	public String toString() {
		return  Integer.toString(fecha.getDate()) + "/" + Integer.toString(fecha.getMonth()+1) + "/" + Integer.toString(fecha.getYear()+1900)+ " " + descripcion + "  $" + Double.toString(monto);
	}
	
	public String dateToString_BD() {
		return Integer.toString(fecha.getYear()+1900) + "-" + Integer.toString(fecha.getMonth()+1) + "-" + Integer.toString(fecha.getDate());
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
