package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

public class ListaClientes {
	public ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}

	private ArrayList<Cliente> clientes;
	
	public ListaClientes(){
		this.clientes = new ArrayList<Cliente>();
	}
	
	public Cliente getCliente_byID(int id) {
		for (Cliente i : this.clientes) {
			if (i.getId() == id) {
				return i;
			}
		}
		return null;
	}
	
	
	public void cargarClientesDesdeBD() {
		
		this.clientes = new ArrayList<Cliente>();
		MySQLStuff sql = new MySQLStuff();
		
		try {
			sql.setResult(sql.getStatement().executeQuery("SELECT * FROM cliente ORDER BY nombre ASC"));
			
			while (sql.getResult().next()) {
				
				Cliente cliente = new Cliente();
				
				cliente.setId(sql.getResult().getInt(1));
				cliente.setNombre(sql.getResult().getString(2));
				cliente.setDireccion(sql.getResult().getString(3));
				cliente.setNombre_local(sql.getResult().getString(4));
				cliente.setTelefono(sql.getResult().getString(5));
				cliente.setCuit(sql.getResult().getString(6));
				cliente.setFecha_incorp(sql.getResult().getDate(7));
		
				this.clientes.add(cliente);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			sql.close();
		}
		
	}
	
	public void cargarClientesDesdeBD_orderByDireccion() {
		
		this.clientes = new ArrayList<Cliente>();
		MySQLStuff sql = new MySQLStuff();
		
		try {
			sql.setResult(sql.getStatement().executeQuery("SELECT * FROM cliente ORDER BY direccion ASC"));
			
			while (sql.getResult().next()) {
				
				Cliente cliente = new Cliente();
				
				cliente.setId(sql.getResult().getInt(1));
				cliente.setNombre(sql.getResult().getString(2));
				cliente.setDireccion(sql.getResult().getString(3));
				cliente.setNombre_local(sql.getResult().getString(4));
				cliente.setTelefono(sql.getResult().getString(5));
				cliente.setCuit(sql.getResult().getString(6));
				cliente.setFecha_incorp(sql.getResult().getDate(7));
		
				this.clientes.add(cliente);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			sql.close();
		}
		
	}
	
	public void insertarClienteoBD(Cliente cliente) { // Guarda el producto pasado por parametro en la BD
		MySQLStuff sql = new MySQLStuff();
		
		try {
			sql.getStatement().executeUpdate("INSERT INTO cliente VALUES (NULL ,"
											+ (char)34 + cliente.getNombre() + (char)34 + ", " + 
											(char)34 + cliente.getDireccion() + (char)34 + ", " +
											(char)34 + cliente.getNombre_local() + (char)34 + ", "
											+ (char)34 + cliente.getTelefono() + (char)34 + ", "
											+ (char)34 + cliente.getCuit() + (char)34 + ", "
											+ (char)34 + cliente.dateToString_BD() + (char)34 + ")");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sql.close();
		}
	}
	
	public void eliminarClienteBD(int id) {
		MySQLStuff sql = new MySQLStuff();
		
		try {
			sql.getStatement().executeUpdate("DELETE FROM cliente WHERE id=" + String.valueOf(id));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sql.close();
		}
	}
	
	public void modificarClienteBD(Cliente cliente) {
		MySQLStuff sql = new MySQLStuff();
		
		try {
			sql.getStatement().executeUpdate("UPDATE cliente SET nombre="+ (char)34 + cliente.getNombre() + (char)34 + ", direccion=" +
						(char)34 + cliente.getDireccion() + (char)34 + ", nombre_local=" + (char)34 + cliente.getNombre_local() + (char)34 + ", telefono=" +
						(char)34 + cliente.getTelefono() + (char)34 + ", cuit=" + (char)34 + cliente.getCuit() + (char)34 + 
						", fecha_incorp=" + (char)34 + cliente.dateToString_BD() + (char)34 + " WHERE id=" + cliente.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sql.close();
		}
	}
	
}
