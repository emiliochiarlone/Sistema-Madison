package modelo;

import java.util.Date;

public class Cliente {
	private int id;
	private String nombre;
	private String direccion;
	private String nombre_local;
	private String telefono;
	private String cuit;
	private Date fecha_incorp;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getNombre_local() {
		return nombre_local;
	}
	public void setNombre_local(String nombre_local) {
		this.nombre_local = nombre_local;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	public Date getFecha_incorp() {
		return fecha_incorp;
	}
	public void setFecha_incorp(java.util.Date fecha) {
		this.fecha_incorp = fecha;
	}
	
	public String dateToString() {
		return Integer.toString(fecha_incorp.getDate()) + "/" + Integer.toString(fecha_incorp.getMonth()+1) + "/" + Integer.toString(fecha_incorp.getYear()+1900);
	}
	
	public String dateToString_BD() {
		return Integer.toString(fecha_incorp.getYear()+1900) + "-" + Integer.toString(fecha_incorp.getMonth()+1) + "-" + Integer.toString(fecha_incorp.getDate());
	}
	
	public String toString() {
		return nombre + " - " + nombre_local + " - " + id;
		
	}
}
