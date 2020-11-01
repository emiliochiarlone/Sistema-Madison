package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

public class ListaVendedores {
	

	private ArrayList<Vendedor> vendedores;
	
	public ArrayList<Vendedor> getVendedores() {
		return vendedores;
	}

	public void setVendedores(ArrayList<Vendedor> vendedores) {
		this.vendedores = vendedores;
	}
	
	public Vendedor getVendedor_byID(int id) {
		for (Vendedor i : vendedores) {
			if (i.getId() == id) {
				return i;
			}
		}
		return null;

	}
	
	public void cargarVendedoresDesdeBD() {
			this.vendedores = new ArrayList<Vendedor>();
			MySQLStuff sql = new MySQLStuff();
			
			try {
				sql.setResult(sql.getStatement().executeQuery("SELECT * FROM vendedor ORDER BY nombre ASC"));
				
				while (sql.getResult().next()) {
					Vendedor vendedor = new Vendedor();
					vendedor.setId(sql.getResult().getInt(1));
					vendedor.setNombre(sql.getResult().getString(2));
			
					this.vendedores.add(vendedor);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				sql.close();
			}
			
	}
	
	public void insertarVendedorBD(Vendedor vendedor) {
		if (this.vendedores != null) {
			this.vendedores.add(vendedor);
		}
		MySQLStuff sql = new MySQLStuff();
		
		try {
			sql.getStatement().executeUpdate("INSERT INTO vendedor VALUES (NULL, "+ (char)34 + vendedor.getNombre() + (char)34 + ")");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sql.close();
		}
	}
	
	public void eliminarVendedorBD(Vendedor vendedor) {
		if (this.vendedores != null) {
			this.vendedores.remove(vendedor);
		}
		MySQLStuff sql = new MySQLStuff();
		
		try {
			sql.getStatement().executeUpdate("DELETE FROM vendedor WHERE id=" + String.valueOf(vendedor.getId()));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sql.close();
		}
	}
	
	public void modificarVendedorBD(Vendedor vendedor) {
		MySQLStuff sql = new MySQLStuff();
		
		try {
			sql.getStatement().executeUpdate("UPDATE vendedor SET nombre="+ (char)34 + vendedor.getNombre() + (char)34 + " WHERE id=" + vendedor.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sql.close();
		}
	}
	

}
