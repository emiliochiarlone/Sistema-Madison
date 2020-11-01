package modelo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class ArchivoGastos{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2471620284882205488L;
	private ArrayList<Gasto> gastos;
	
	public ArrayList<Gasto> getGastos() {
		return gastos;
	}
	public void setGastos(ArrayList<Gasto> gastos) {
		this.gastos = gastos;
	}
	
	public ArchivoGastos() {
		this.gastos = new ArrayList<Gasto>();
	}
	
	public double getMontoEntreFechas(Date fechaDesde, Date fechaHasta) {
		double total = 0;
		for (Gasto i:this.gastos) {
			if (i.getFecha().after(fechaDesde) && i.getFecha().before(fechaHasta)) {
				total = total + i.getMonto();
			}
		}
		return total;
	}
	
	public double getGastoTotal() {
		double total = 0;
		for (Gasto i:this.gastos) {
			total = total + i.getMonto();
		}
		return total;
	}
	
	
	public void cargarGastosDesdeBD() {
		
		this.gastos = new ArrayList<Gasto>();
		MySQLStuff sql = new MySQLStuff();
		
		try {
			sql.setResult(sql.getStatement().executeQuery("SELECT * FROM gasto ORDER BY fecha ASC"));
			
			while (sql.getResult().next()) {
				Gasto gasto = new Gasto();
				gasto.setId(sql.getResult().getInt(1));
				gasto.setDescripcion(sql.getResult().getString(2));
				gasto.setFecha(sql.getResult().getDate(3));
				gasto.setMonto(sql.getResult().getDouble(4));
		
				this.gastos.add(gasto);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			sql.close();
		}
	}
	
	public void insertarGastoBD(Gasto gasto) { // Guarda el producto pasado por parametro en la BD
		MySQLStuff sql = new MySQLStuff();
		
		try {
			sql.getStatement().executeUpdate("INSERT INTO gasto VALUES (NULL ,"
											+ (char)34 + gasto.getDescripcion() + (char)34 + ", " + 
											(char)34 + gasto.dateToString_BD() + (char)34 + ", " + 
											String.valueOf(gasto.getMonto()) + ")");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sql.close();
		}
	}
		
	public void eliminarGastoBD(Gasto gasto) {
		MySQLStuff sql = new MySQLStuff();
		
		try {
			sql.getStatement().executeUpdate("DELETE FROM gasto WHERE id=" + String.valueOf(gasto.getId()));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sql.close();
		}
	}
		

}
