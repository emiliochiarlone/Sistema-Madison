package modelo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class HistorialGanancias {
	
	private ArrayList<Ganancia> ganancias;

	public ArrayList<Ganancia> getGanancias() {
		return ganancias;
	}

	public void setGanancias(ArrayList<Ganancia> ganancias) {
		this.ganancias = ganancias;
	}
	
	public HistorialGanancias() {
		this.ganancias = new ArrayList<Ganancia>();
	}
	
	public void agregarGanancia(Ganancia ganancia) {
		this.ganancias.add(ganancia);
	}

	public double calcularGananciaTotal (){
		double total = 0;
		for (Ganancia i: this.ganancias) {
			total += i.getMonto();
		}
		return total;
	}
	
	public double calcularGananciaEntreFechas (Date fechaDesde, Date fechaHasta) {
		double total = 0;
		for (Ganancia i: this.ganancias) {
			if (i.getFecha().after(fechaDesde) && i.getFecha().before(fechaHasta)){
				total += i.getMonto();
			}
		}
		return total;
	}
	
	public void cargarGananciasDesdeBD() {
			
		this.ganancias = new ArrayList<Ganancia>();
		MySQLStuff sql = new MySQLStuff();
		
		try {
			sql.setResult(sql.getStatement().executeQuery("SELECT * FROM ganancia ORDER BY fecha ASC"));
			
			while (sql.getResult().next()) {
				Ganancia ganancia = new Ganancia();
				
				ganancia.setMonto(sql.getResult().getDouble(1));
				ganancia.setFactura_id(sql.getResult().getInt(2));
				ganancia.setFecha(sql.getResult().getDate(3));
				
				this.ganancias.add(ganancia);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			sql.close();
		}
	}
	
	public void insertarGananciaBD(Ganancia ganancia) { // Guarda el producto pasado por parametro en la BD
		MySQLStuff sql = new MySQLStuff();
		
		try {
			sql.getStatement().executeUpdate("INSERT INTO ganancia VALUES (" + String.valueOf(ganancia.getMonto()) + ", " + 
											String.valueOf(ganancia.getFactura_id()) + ", " + 
											(char)34 + String.valueOf(ganancia.dateToString_BD()) + (char) 34 + ")");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sql.close();
		}
	}
}
