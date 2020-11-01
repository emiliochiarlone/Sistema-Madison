package modelo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class ArchivoRemitos {
	
	private ArrayList<Remito> remitos = new ArrayList<Remito>();

	

	public ArchivoRemitos() {
		this.remitos = new ArrayList<Remito>();
	}


	public ArrayList<Remito> getRemitos() {
		return remitos;
	}
	public void setRemitos(ArrayList<Remito> remitos) {
		this.remitos = remitos;
	}
	
	public void agregarRemito (Remito remito) {
		this.remitos.add(remito);
	}
	
	public int getCantidadVentas() {
		return this.remitos.size();
	}
	
	public double getDineroTotal() {
		double total = 0;
		for (Remito i : this.remitos) {
			total = total+i.getTotal();
		}
		return total;
	}
	
	public int cantidadVentasEntreFechas (Date fechaDesde, Date fechaHasta) {
		int total = 0;
		for (Remito i: this.remitos) {
			if (i.getFecha().after(fechaDesde) && i.getFecha().before(fechaHasta)){
				total = total + 1;
			}
		}
		return total;
	}
	
	public double getDineroTotalEntreFechas(Date fechaDesde, Date fechaHasta) {
		double total = 0;
		for (Remito i: this.remitos) {
			if (i.getFecha().after(fechaDesde) && i.getFecha().before(fechaHasta)){
				total = total + i.getTotal();
			}
		}
		return total;
	}
	
	
	public void cargarRemitosDesdeBD() {
		
		this.remitos = new ArrayList<Remito>();
		MySQLStuff sql = new MySQLStuff();
		MySQLStuff sql2 = new MySQLStuff();
		
		try {
			sql.setResult(sql.getStatement().executeQuery("SELECT * FROM remito ORDER BY fecha DESC"));
			
			while (sql.getResult().next()) {
				Remito remito = new Remito();
				double subtotal = 0;
				
				remito.setId(sql.getResult().getInt(1));
				remito.setFecha(sql.getResult().getDate(2));
				remito.setDescuento(sql.getResult().getInt(3));
				remito.setId_cliente(sql.getResult().getInt(4));
				remito.setId_vendedor(sql.getResult().getInt(5));
				try {
					sql2.setResult(sql2.getStatement().executeQuery("SELECT * FROM detalle WHERE remito_id=" + remito.getId() + " ORDER BY producto_codigo ASC"));
						
					while(sql2.getResult().next()) {
						Detalle detalle = new Detalle();
						
						detalle.setCantidad(sql2.getResult().getInt(1));
						detalle.setPrecio(sql2.getResult().getDouble(2));
						detalle.setFactura_id(sql2.getResult().getInt(3));
						detalle.setProducto_codigo(sql2.getResult().getInt(4));
						detalle.setDescuento(sql2.getResult().getInt(5));
						
						subtotal += detalle.getTotal();
						remito.agregarDetalle(detalle);
					}
					
					remito.setSubtotal(subtotal);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				this.remitos.add(remito);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("error sql");
		} finally {
			sql.close();
		}
	}
	
	public void cargarRemitosDesdeBD_limit(int limit) {
		
		this.remitos = new ArrayList<Remito>();
		MySQLStuff sql = new MySQLStuff();
		MySQLStuff sql2 = new MySQLStuff();
		
		try {
			sql.setResult(sql.getStatement().executeQuery("SELECT * FROM remito ORDER BY fecha DESC LIMIT " + String.valueOf(limit)));
			
			while (sql.getResult().next()) {
				Remito remito = new Remito();
				double subtotal = 0;
				
				remito.setId(sql.getResult().getInt(1));
				remito.setFecha(sql.getResult().getDate(2));
				remito.setDescuento(sql.getResult().getInt(3));
				remito.setId_cliente(sql.getResult().getInt(4));
				remito.setId_vendedor(sql.getResult().getInt(5));
				try {
					sql2.setResult(sql2.getStatement().executeQuery("SELECT * FROM detalle WHERE remito_id=" + remito.getId() + " ORDER BY producto_codigo ASC"));
						
					while(sql2.getResult().next()) {
						Detalle detalle = new Detalle();
						
						detalle.setCantidad(sql2.getResult().getInt(1));
						detalle.setPrecio(sql2.getResult().getDouble(2));
						detalle.setFactura_id(sql2.getResult().getInt(3));
						detalle.setProducto_codigo(sql2.getResult().getInt(4));
						detalle.setDescuento(sql2.getResult().getInt(5));
						
						subtotal += detalle.getTotal();
						remito.agregarDetalle(detalle);
					}
					
					remito.setSubtotal(subtotal);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				this.remitos.add(remito);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("error sql");
		} finally {
			sql.close();
		}
	}
	
	public void insertarRemitoBD(Remito remito) { // Guarda el producto pasado por parametro en la BD
		MySQLStuff sql = new MySQLStuff();
		MySQLStuff sql2 = new MySQLStuff();
		
		try {
			sql.getStatement().executeUpdate("INSERT INTO remito VALUES ("+ String.valueOf(remito.getId()) + " ,"
											+ (char)34 + remito.dateToString_BD() + (char)34 + ", " + 
											String.valueOf(remito.getDescuento()) + ", " + 
											String.valueOf(remito.getId_cliente()) + ", " +
											String.valueOf(remito.getId_vendedor()) + ")");
			
			ArrayList<Detalle> listaDetalle = remito.getListaDetalle();
			for (Detalle i : listaDetalle) {
				sql2.getStatement().executeUpdate("INSERT INTO detalle VALUES (" + String.valueOf(i.getCantidad()) + ", " +
							String.valueOf(i.getPrecio()) + ", " + String.valueOf(i.getRemito_id()) + ", " + 
							String.valueOf(i.getProducto_codigo()) + ", " +
							String.valueOf(i.getDescuento()) + ") ");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sql.close();
		}
	}
	
	public void cargarRemitos_ClienteBD(int id_cliente) {
		
		this.remitos = new ArrayList<Remito>();
		MySQLStuff sql = new MySQLStuff();
		MySQLStuff sql2 = new MySQLStuff();
		
		try {
			sql.setResult(sql.getStatement().executeQuery("SELECT * FROM remito WHERE cliente_id=" + String.valueOf(id_cliente) + " ORDER BY fecha ASC"));
			
			while (sql.getResult().next()) {
				Remito remito = new Remito();
				double subtotal = 0;
				
				remito.setId(sql.getResult().getInt(1));
				remito.setFecha(sql.getResult().getDate(2));
				remito.setDescuento(sql.getResult().getInt(3));
				remito.setId_cliente(sql.getResult().getInt(4));
				remito.setId_vendedor(sql.getResult().getInt(5));
				try {
					sql2.setResult(sql2.getStatement().executeQuery("SELECT * FROM detalle WHERE remito_id=" + remito.getId() + " ORDER BY producto_codigo ASC"));
						
					while(sql2.getResult().next()) {
						Detalle detalle = new Detalle();
						
						detalle.setCantidad(sql2.getResult().getInt(1));
						detalle.setPrecio(sql2.getResult().getDouble(2));
						detalle.setFactura_id(sql2.getResult().getInt(3));
						detalle.setProducto_codigo(sql2.getResult().getInt(4));
						detalle.setDescuento(sql2.getResult().getInt(5));
						
						subtotal += detalle.getTotal();
						remito.agregarDetalle(detalle);
					}
					
					remito.setSubtotal(subtotal);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				this.remitos.add(remito);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("error sql");
		} finally {
			sql.close();
		}
	}
	
	public void cargarRemitos_VendedorBD(int id_vendedor) {
	
		this.remitos = new ArrayList<Remito>();
		MySQLStuff sql = new MySQLStuff();
		MySQLStuff sql2 = new MySQLStuff();
		
		try {
			sql.setResult(sql.getStatement().executeQuery("SELECT * FROM remito WHERE vendedor_id=" + String.valueOf(id_vendedor) + " ORDER BY fecha ASC"));
			
			while (sql.getResult().next()) {
				Remito remito = new Remito();
				double subtotal = 0;
				
				remito.setId(sql.getResult().getInt(1));
				remito.setFecha(sql.getResult().getDate(2));
				remito.setDescuento(sql.getResult().getInt(3));
				remito.setId_cliente(sql.getResult().getInt(4));
				remito.setId_vendedor(sql.getResult().getInt(5));
				try {
					sql2.setResult(sql2.getStatement().executeQuery("SELECT * FROM detalle WHERE remito_id=" + remito.getId() + " ORDER BY producto_codigo ASC"));
						
					while(sql2.getResult().next()) {
						Detalle detalle = new Detalle();
						
						detalle.setCantidad(sql2.getResult().getInt(1));
						detalle.setPrecio(sql2.getResult().getDouble(2));
						detalle.setFactura_id(sql2.getResult().getInt(3));
						detalle.setProducto_codigo(sql2.getResult().getInt(4));
						detalle.setDescuento(sql2.getResult().getInt(5));
						
						subtotal += detalle.getTotal();
						remito.agregarDetalle(detalle);
					}
					
					remito.setSubtotal(subtotal);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				this.remitos.add(remito);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("error sql");
		} finally {
			sql.close();
		}
	}
	
	public void cargarRemitos_porFecha(Date date) {
		
		this.remitos = new ArrayList<Remito>();
		MySQLStuff sql = new MySQLStuff();
		MySQLStuff sql2 = new MySQLStuff();
		
		try {
			sql.setResult(sql.getStatement().executeQuery("SELECT * FROM remito WHERE fecha=" + (char)34 + (date.getYear()+1900) + "-" + (date.getMonth()+1) + "-" + date.getDate()  + (char)34 + " ORDER BY fecha ASC"));
			
			while (sql.getResult().next()) {
				Remito remito = new Remito();
				double subtotal = 0;
				
				remito.setId(sql.getResult().getInt(1));
				remito.setFecha(sql.getResult().getDate(2));
				remito.setDescuento(sql.getResult().getInt(3));
				remito.setId_cliente(sql.getResult().getInt(4));
				remito.setId_vendedor(sql.getResult().getInt(5));
				try {
					sql2.setResult(sql2.getStatement().executeQuery("SELECT * FROM detalle WHERE remito_id=" + remito.getId() + " ORDER BY producto_codigo ASC"));
						
					while(sql2.getResult().next()) {
						Detalle detalle = new Detalle();
						
						detalle.setCantidad(sql2.getResult().getInt(1));
						detalle.setPrecio(sql2.getResult().getDouble(2));
						detalle.setFactura_id(sql2.getResult().getInt(3));
						detalle.setProducto_codigo(sql2.getResult().getInt(4));
						detalle.setDescuento(sql2.getResult().getInt(5));
						
						subtotal += detalle.getTotal();
						remito.agregarDetalle(detalle);
					}
					
					remito.setSubtotal(subtotal);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				this.remitos.add(remito);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("error sql");
		} finally {
			sql.close();
		}
	}

	public void eliminarRemitoBD(int id) {
		MySQLStuff sql = new MySQLStuff();
		
		try {
			sql.getStatement().executeUpdate("DELETE FROM remito WHERE id=" + String.valueOf(id));
			sql.getStatement().executeUpdate("DELETE FROM detalle WHERE remito_id=" + String.valueOf(id));
			sql.getStatement().executeUpdate("DELETE FROM ganancia WHERE remito_id=" + String.valueOf(id));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sql.close();
		}
	}
	
	@SuppressWarnings("finally")
	public int ultimoIdRemito() throws SQLException {
		MySQLStuff sql = new MySQLStuff();
		int resultado = 0;
		try {
			sql.setResult(sql.getStatement().executeQuery("SELECT * FROM remito ORDER BY id DESC LIMIT 1"));
			if(sql.getResult().next()) {
				resultado = sql.getResult().getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sql.close();
			return resultado;
		}
			
	}
	
}
