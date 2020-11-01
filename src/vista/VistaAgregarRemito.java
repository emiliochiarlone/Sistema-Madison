package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.mysql.fabric.xmlrpc.base.Array;

import imprimibles.RemitoImp;
import imprimibles.RemitoImp_onlyTable;
import modelo.Cliente;
import modelo.Comercio;
import modelo.Detalle;
import modelo.Producto;
import modelo.Remito;
import modelo.Vendedor;
import javax.swing.SpinnerNumberModel;

public class VistaAgregarRemito extends JFrame {
	private Remito remito;
	private JTextField tfCodigo;
	private JTextField tfBuscador;
	private JTextField tfDescuento;
	private JTextField tfSubtotal;
	private JTextField tfDescuentoRemito;
	private JTextField tfTotal;
	private ArrayList<Vendedor> vendedores;
	private ArrayList<Cliente> clientes;
	private ArrayList<Detalle> detalle;
	private ArrayList<Producto> productos;

	/**
	 * Create the frame.
	 * @param <T>
	 */
	public VistaAgregarRemito(Comercio comercio, JButton label) {
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
		setTitle("Nuevo remito");
		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setBounds(100, 100, 739, 720);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nuevo remito");
		lblNewLabel.setIcon(new ImageIcon(VistaAgregarRemito.class.getResource("/imagenes/my_invoices.png")));
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 23));
		lblNewLabel.setBounds(22, 11, 239, 35);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Fecha:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(22, 95, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		JSpinner sFecha = new JSpinner();
		sFecha.setModel(new SpinnerDateModel(new Date(1603594800000L), null, null, Calendar.DAY_OF_YEAR));
		sFecha.setBounds(126, 88, 135, 27);
		getContentPane().add(sFecha);
		
		JLabel lblNewLabel_2 = new JLabel("C\u00F3digo:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(22, 171, 63, 17);
		getContentPane().add(lblNewLabel_2);
		
		tfCodigo = new JTextField();
		tfCodigo.setEditable(false);
		tfCodigo.setBounds(150, 165, 111, 27);
		getContentPane().add(tfCodigo);
		tfCodigo.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("Cliente:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2_1.setBounds(22, 245, 60, 17);
		getContentPane().add(lblNewLabel_2_1);
		
		JComboBox<String> cbCliente = new JComboBox<String>();
		cbCliente.setBounds(80, 240, 259, 27);
		getContentPane().add(cbCliente);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Vendedor:");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2_1_1.setBounds(21, 325, 74, 17);
		getContentPane().add(lblNewLabel_2_1_1);
		
		JComboBox<String> cbVendedor = new JComboBox<String>();
		cbVendedor.setBounds(94, 320, 248, 27);
		getContentPane().add(cbVendedor);
		
		JLabel lResultados = new JLabel("No se han encontrado resultados para la b\u00FAsqueda.");
		lResultados.setForeground(Color.RED);
		lResultados.setBackground(Color.RED);
		lResultados.setBounds(383, 198, 315, 14);
		getContentPane().add(lResultados);
		lResultados.setVisible(false);
		
		JLabel lblNewLabel_3 = new JLabel("Agregar productos al detalle");
		lblNewLabel_3.setIcon(new ImageIcon(VistaAgregarRemito.class.getResource("/imagenes/new_doc.png")));
		lblNewLabel_3.setFont(new Font("Microsoft YaHei", Font.PLAIN, 19));
		lblNewLabel_3.setBounds(383, 24, 324, 39);
		getContentPane().add(lblNewLabel_3);
		
		tfBuscador = new JTextField();
		tfBuscador.setBounds(424, 88, 214, 27);
		getContentPane().add(tfBuscador);
		tfBuscador.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Buscador de productos");
		lblNewLabel_4.setIcon(null);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(443, 65, 248, 20);
		getContentPane().add(lblNewLabel_4);
		
		JButton bAgregarProducto = new JButton("AGREGAR AL DETALLE");
		bAgregarProducto.setIcon(new ImageIcon(VistaAgregarRemito.class.getResource("/imagenes/dot.png")));
		bAgregarProducto.setBounds(422, 312, 226, 42);
		getContentPane().add(bAgregarProducto);
		
		JLabel lblNewLabel_5 = new JLabel("Cantidad:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(443, 234, 65, 14);
		getContentPane().add(lblNewLabel_5);
		
		JComboBox<String> cbBusqueda = new JComboBox<String>();
		cbBusqueda.setBounds(371, 159, 336, 27);
		getContentPane().add(cbBusqueda);
		
		JLabel lblNewLabel_4_1 = new JLabel("Resultados de la b\u00FAsqueda");
		lblNewLabel_4_1.setIcon(null);
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4_1.setBounds(434, 127, 248, 20);
		getContentPane().add(lblNewLabel_4_1);
		
		JSpinner sCantidad = new JSpinner();
		sCantidad.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		sCantidad.setBounds(520, 224, 53, 27);
		getContentPane().add(sCantidad);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBackground(Color.GRAY);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(351, 26, 8, 354);
		getContentPane().add(separator);
		
		JComboBox<String> cbDetalle = new JComboBox<String>();
		cbDetalle.setBounds(32, 434, 660, 27);
		getContentPane().add(cbDetalle);
		
		JButton bEliminarProducto = new JButton("Eliminar producto seleeccionado");
		bEliminarProducto.setIcon(new ImageIcon(VistaAgregarRemito.class.getResource("/imagenes/error.png")));
		bEliminarProducto.setSelectedIcon(new ImageIcon(VistaAgregarRemito.class.getResource("/imagenes/error.png")));
		bEliminarProducto.setBounds(202, 473, 264, 37);
		getContentPane().add(bEliminarProducto);
		
		JLabel lblNewLabel_5_1 = new JLabel("Descuento (%):");
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5_1.setBounds(424, 277, 97, 14);
		getContentPane().add(lblNewLabel_5_1);
		
		tfDescuento = new JTextField();
		tfDescuento.setText("0");
		tfDescuento.setBounds(520, 270, 53, 27);
		getContentPane().add(tfDescuento);
		tfDescuento.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(VistaAgregarRemito.class.getResource("/imagenes/search - copia.png")));
		lblNewLabel_6.setBounds(401, 88, 23, 23);
		getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_3_1 = new JLabel("Detalle del remito");
		lblNewLabel_3_1.setIcon(new ImageIcon(VistaAgregarRemito.class.getResource("/imagenes/redtick_document.png")));
		lblNewLabel_3_1.setFont(new Font("Microsoft YaHei", Font.PLAIN, 19));
		lblNewLabel_3_1.setBounds(227, 392, 293, 39);
		getContentPane().add(lblNewLabel_3_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.LIGHT_GRAY);
		separator_2.setBackground(Color.GRAY);
		separator_2.setBounds(68, 522, 577, 2);
		getContentPane().add(separator_2);
		
		JLabel lblNewLabel_2_2 = new JLabel("Subtotal ($):");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2_2.setBounds(110, 547, 85, 17);
		getContentPane().add(lblNewLabel_2_2);
		
		tfSubtotal = new JTextField();
		tfSubtotal.setEditable(false);
		tfSubtotal.setColumns(10);
		tfSubtotal.setBounds(227, 541, 105, 27);
		getContentPane().add(tfSubtotal);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Descuento (%):");
		lblNewLabel_2_2_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2_2_1.setBounds(94, 587, 94, 17);
		getContentPane().add(lblNewLabel_2_2_1);
		
		tfDescuentoRemito = new JTextField();
		tfDescuentoRemito.setText("0");
		tfDescuentoRemito.setColumns(10);
		tfDescuentoRemito.setBounds(227, 581, 105, 27);
		getContentPane().add(tfDescuentoRemito);
		
		tfTotal = new JTextField();
		tfTotal.setEditable(false);
		tfTotal.setColumns(10);
		tfTotal.setBounds(476, 541, 105, 27);
		getContentPane().add(tfTotal);
		
		JLabel lblNewLabel_2_2_1_1 = new JLabel("Total ($):");
		lblNewLabel_2_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2_2_1_1.setBounds(401, 547, 63, 17);
		getContentPane().add(lblNewLabel_2_2_1_1);
		
		JButton bCalcularTotal = new JButton("Calcular total");
		bCalcularTotal.setIcon(new ImageIcon(VistaAgregarRemito.class.getResource("/imagenes/dot.png")));
		bCalcularTotal.setBounds(145, 622, 129, 42);
		getContentPane().add(bCalcularTotal);
		
		JButton bArchivarRemito = new JButton("ARCHIVAR REMITO");
		bArchivarRemito.setIcon(new ImageIcon(VistaAgregarRemito.class.getResource("/imagenes/button_ok.png")));
		bArchivarRemito.setBounds(424, 598, 263, 60);
		getContentPane().add(bArchivarRemito);
		
		sFecha.setValue(new Date());
		comercio.getListaClientes().cargarClientesDesdeBD();
		comercio.getListaVendedores().cargarVendedoresDesdeBD();
		comercio.getListaDePrecios().cargarProductosDesdeBD();
		comercio.getStock().cargarStockDesdeBD();
		vendedores = comercio.getListaVendedores().getVendedores();
		clientes = comercio.getListaClientes().getClientes();
		productos = comercio.getListaDePrecios().getProductos();
		detalle = new ArrayList<Detalle>();
		remito = new Remito();
		remito.setSubtotal(0);
		DecimalFormat formateador = new DecimalFormat("###,###.##");
		
		for (Cliente i : clientes) {
			cbCliente.addItem(i.toString());
		}
		
		for (Vendedor i : vendedores) {
			cbVendedor.addItem(i.toString());
		}
		
		for (Producto i : productos) {
			cbBusqueda.addItem(i.toString());
		}
		
		try {
			remito.setId(comercio.getArchivoRemitos().ultimoIdRemito()+1);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		tfCodigo.setText(Integer.toString(remito.getId()));
		
		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setForeground(Color.LIGHT_GRAY);
		separator_2_1.setBackground(Color.GRAY);
		separator_2_1.setBounds(22, 378, 660, 2);
		getContentPane().add(separator_2_1);
	
		
		tfBuscador.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cbBusqueda.removeAllItems();
				for (Producto i : productos) {
					if ( i.toString().contains(tfBuscador.getText()) || i.toString().contains(tfBuscador.getText().toLowerCase()) || i.toString().contains(tfBuscador.getText().toUpperCase())) {
						cbBusqueda.addItem(i.toString());
					}
				}
				lResultados.setVisible(false);
				if (cbBusqueda.getSelectedItem() == null) {
					lResultados.setVisible(true);
					Timer timer = new Timer(4000, new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							lResultados.setVisible(false);
							
						}
					});
					timer.start();
					timer.setRepeats(false);
					
				}
			}
			
		});
		
		bAgregarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Detalle> listaDetalle = remito.getListaDetalle();
				Producto producto = new Producto();
				boolean flag = false;
				for (Producto i : productos) {
					if (i.toString().equals(cbBusqueda.getSelectedItem())) {
						producto = i;
						break;
					}
				}
				for (Detalle i : listaDetalle) {
					if (i.getProducto_codigo() == producto.getCodigo()){
						JOptionPane.showMessageDialog(null, "Este producto ya se encuentra en el detalle");
						flag = true;
						break;
					}
				}
				String sMonto = "";
				if (tfDescuento.getText().equals("") || tfDescuento.getText().length()>2) {
					JOptionPane.showMessageDialog(null, "Ingrese un número del 0 al 99 en descuento");
					flag = true;
				} else {
					sMonto = tfDescuento.getText();
					for (int i = 0; i< sMonto.length(); i++) {
						if (sMonto.charAt(i)<48 || sMonto.charAt(i)>57) {
							JOptionPane.showMessageDialog(null, "Ingrese un número del 0 al 99 en descuento");
							flag = true;
							break;
						}
					}
					if (!flag) {
						Detalle detalle = new Detalle();
						for (Producto i : productos) {
							if (i.toString().equals(cbBusqueda.getSelectedItem())) {
								detalle.setProducto_codigo(i.getCodigo());
								detalle.setCantidad((int) sCantidad.getValue());
								detalle.setDescuento(Integer.parseInt(tfDescuento.getText()));
								detalle.setFactura_id(remito.getId());
								detalle.setPrecio(i.getPrecio_venta());
								
								remito.agregarDetalle(detalle);
								cbDetalle.addItem(detalle.toString());
								
								tfSubtotal.setText(formateador.format(remito.getSubtotal()));
								break;
							}
						}
					}
				}
			}
		});
		
		bEliminarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Detalle> listaDetalle = remito.getListaDetalle();
				for (Detalle i : listaDetalle) {
					if (cbDetalle.getSelectedItem().equals(i.toString())) {
						remito.eliminarDetalle(i);
						cbDetalle.removeItem(i.toString());
						tfSubtotal.setText(formateador.format(remito.getSubtotal()));
						tfTotal.setText(formateador.format(remito.getTotal()));
						break;
					}
				}
			}
		});
		
		bCalcularTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean flag = false;
				String sMonto = "";
				if (tfDescuentoRemito.getText().equals("") || tfDescuentoRemito.getText().length()>2) {
					JOptionPane.showMessageDialog(null, "Ingrese un número del 0 al 99 en descuento");
					flag = true;
				} else {
					sMonto = tfDescuentoRemito.getText();
					for (int i = 0; i< sMonto.length(); i++) {
						if (sMonto.charAt(i)<48 || sMonto.charAt(i)>57) {
							JOptionPane.showMessageDialog(null, "Ingrese un número del 0 al 99 en descuento");
							flag = true;
							break;
						}
					}
					if (!flag) {
					remito.setDescuento(Double.parseDouble(tfDescuentoRemito.getText()));
					tfTotal.setText(formateador.format(remito.getTotal()));
					}
				}
			}
		});
		
		bArchivarRemito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "¿Está seguro/a que desea registrar el remito?", "Confirmar remito", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					boolean flag = false;
					String sMonto = "";
					if (tfDescuentoRemito.getText().equals("") || tfDescuentoRemito.getText().length()>2) {
						JOptionPane.showMessageDialog(null, "Ingrese un número del 0 al 99 en descuento");
						flag = true;
					} else {
						sMonto = tfDescuentoRemito.getText();
						for (int i = 0; i< sMonto.length(); i++) {
							if (sMonto.charAt(i)<48 || sMonto.charAt(i)>57) {
								JOptionPane.showMessageDialog(null, "Ingrese un número del 0 al 99 en descuento");
								flag = true;
								break;
							}
						}
						if (!flag) {
							if (remito.getListaDetalle().isEmpty()) {
								JOptionPane.showMessageDialog(null, "Debe ingresar productos en el detalle primero");
							}
						}
						if (!flag) {
							if (!comercio.getStock().ventaEsPosible(remito)) {
								JOptionPane.showMessageDialog(null, "No dispone del stock suficiente para realizar la venta");
							} else {
								for (Cliente i : clientes) {
									if (i.toString().equals(cbCliente.getSelectedItem())) {
										remito.setId_cliente(i.getId());
									}
								}
								for (Vendedor i : vendedores) {
									if (i.toString().equals(cbVendedor.getSelectedItem())) {
										remito.setId_vendedor(i.getId());
									}
								}
								remito.setDescuento(Double.parseDouble(tfDescuentoRemito.getText()));
								remito.setFecha((java.util.Date) sFecha.getValue());
								comercio.NuevaVenta(remito);
								if (comercio.getStock().calcularFaltas().isEmpty()) {
									label.setIcon(new ImageIcon(VistaAgregarRemito.class.getResource("/imagenes/accept1.png")));
								} else {
									label.setIcon(new ImageIcon(VistaAgregarRemito.class.getResource("/imagenes/faltastock.png")));
								}
								dispose();
								if (JOptionPane.showConfirmDialog(null, "Remito archivado correctamente. ¿Desea imprimir el remito?", "Imprimir remito", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
									SwingUtilities.invokeLater(new Runnable() {			
										@Override
										public void run() {
											RemitoImp remitoimprimible = new RemitoImp(comercio, remito);
											PrinterJob gap = PrinterJob.getPrinterJob();
											gap.setPrintable(remitoimprimible);
											boolean top = gap.printDialog();
											if (top) {
												try {
													gap.print();
												} catch (PrinterException e) {
													JOptionPane.showMessageDialog(null, "ERROR " + e);
												}
											}
											if(remito.getListaDetalle().size()>23) {
												if (remito.getListaDetalle().size()>30) {
													ArrayList<Detalle> detalles_mas30 = new ArrayList<Detalle>();
													for (int i = 30; i<remito.getListaDetalle().size(); i++) {
														detalles_mas30.add(remito.getListaDetalle().get(i));
													}
													SwingUtilities.invokeLater(new Runnable() {			
														@Override
														public void run() {
															new RemitoImp_onlyTable(comercio, remito, detalles_mas30);
														}
													});
												}else {
													SwingUtilities.invokeLater(new Runnable() {			
														@Override
														public void run() {
															new RemitoImp_onlyTable(comercio, remito, null);
														}
													});
												}
											}
										}
									});
								}
							}
						}
					}
				}
			}
		});
		
		this.setVisible(true);
	}
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
