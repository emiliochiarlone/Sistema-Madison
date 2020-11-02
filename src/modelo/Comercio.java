package modelo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.ArrayList;

import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;

import vista.VistaAgregarProducto;
import vista.VistaAgregarRemito;
import vista.VistaAgregarVendedor;
import vista.VistaArchivoGastos;
import vista.VistaListaClientes;
import vista.VistaListaDePrecios;
import vista.VistaListaVendedores;
import vista.VistaMenuPrincipal;
import vista.VistaResumenContable;
import vista.VistaStockDisponible;


public class Comercio implements Serializable, iAlmacenable{


	private ArchivoRemitos archivoRemitos;
	private ListaDePrecios listaDePrecios;
	private Stock stock;
	private ArchivoGastos archivoGastos;
	private HistorialGanancias historialGanancias;
	private ListaClientes listaClientes;
	private ListaVendedores listaVendedores;
	private String contraseña = "bomberos85";
	
	public Comercio() {
		this.stock = new Stock();
		this.listaVendedores = new ListaVendedores();
		this.listaDePrecios = new ListaDePrecios();
		this.archivoRemitos =  new ArchivoRemitos();
		this.archivoGastos = new ArchivoGastos();
		this.historialGanancias = new HistorialGanancias();
		this.listaClientes = new ListaClientes();
	}
	
	public double calcularGanancia (Remito remito) { // Retorna la ganancia de la factura. (total - (preciocosto * cantidad))
		double total = remito.getTotal();
		ArrayList<Detalle> listaDetalle = remito.getListaDetalle();
		for (Detalle i: listaDetalle) {
			total -= (listaDePrecios.getPrecioCosto(i.getProducto_codigo())*i.getCantidad());
		}
		return total - (total*(5/100) ); //Se le descuenta el 5% que ser�an las comisiones para los vendedores
	}
	


	public ArchivoGastos getArchivoGastos() {
		return archivoGastos;
	}

	public void setArchivoGastos(ArchivoGastos archivoGastos) {
		this.archivoGastos = archivoGastos;
	}

	public void setStock(Stock stock) { 
		this.stock = stock;
	}

	public ListaVendedores getListaVendedores() {
		return listaVendedores;
	}


	public void setListaVendedores(ListaVendedores listaVendedores) {
		this.listaVendedores = listaVendedores;
	}


	public ListaDePrecios getListaDePrecios() {
		return listaDePrecios;
	}



	public HistorialGanancias getHistorialGanancias() {
		return historialGanancias;
	}


	public void setHistorialGanancias(HistorialGanancias historialGanancias) {
		this.historialGanancias = historialGanancias;
	}
	
	
	public void setHistorialVentas(ArchivoRemitos historialventas) {
		this.archivoRemitos = historialventas;
	}

	public Stock getStock() {
		return stock;
	}

	public String getNombreProducto (int codigo) {
		if (codigo<1) {
			return "";
		}
		ArrayList<Producto> productos = listaDePrecios.getProductos();
		for (Producto i: productos) {
			if (codigo == i.getCodigo()) {
				return i.getNombre();
			}
		}
		return "";
	}
	
	
	public ArchivoRemitos getArchivoRemitos() {
		return archivoRemitos;
	}


	public void setArchivoRemitos(ArchivoRemitos archivoRemitos) {
		this.archivoRemitos = archivoRemitos;
	}


	public void setListaDePrecios(ListaDePrecios listaDePrecios) {
		this.listaDePrecios = listaDePrecios;
	}

	public ListaClientes getListaClientes() {
		return listaClientes;
	}


	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public void setListaClientes(ListaClientes listaClientes) {
		this.listaClientes = listaClientes;
	}


	public Mensaje NuevaVenta(Remito remito) {   //Registro de nueva venta
		
		if (!this.stock.ventaEsPosible(remito)) {
			return Mensaje.nostock;
		}
		
		ArrayList<Producto> productos_stock = stock.getProductos();
		ArrayList<Detalle> listaDetalle = remito.getListaDetalle();
		
		for (Detalle i: listaDetalle) {
			for (Producto j: productos_stock) {
				if (i.getProducto_codigo() == j.getCodigo()) {
					if (j.descontar(i.getCantidad()) == Mensaje.nostock){
						return Mensaje.nostock;
					}
				}
			}
		}
		
		Ganancia ganancia = new Ganancia();
		ganancia.setFactura_id(remito.getId());
		ganancia.setFecha(remito.getFecha());
		ganancia.setMonto(this.calcularGanancia(remito));
		
		this.stock.setProductos(productos_stock);
		this.archivoRemitos.agregarRemito(remito);
		this.historialGanancias.agregarGanancia(ganancia);
		
		
		this.stock.guardarStockBD();
		this.archivoRemitos.insertarRemitoBD(remito);
		this.historialGanancias.insertarGananciaBD(ganancia);
		return Mensaje.listo;
	}
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		Comercio comercio = new Comercio();
		
		SwingUtilities.invokeLater(new Runnable() {
	        @Override
	        public void run() {
				new VistaMenuPrincipal(comercio);
	        }
	     });
		
	}

		
}

