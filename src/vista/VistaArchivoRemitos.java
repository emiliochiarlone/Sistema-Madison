package vista;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.mysql.fabric.xmlrpc.base.Array;

import imprimibles.RemitoImp;
import imprimibles.RemitoImp_onlyTable;
import modelo.Comercio;
import modelo.Detalle;
import modelo.Producto;
import modelo.Remito;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;

public class VistaArchivoRemitos {

	private JFrame frmArchivoDeRemitos;
	private JTextField tfNombre;
	private JTextField tfFecha;
	private Remito remito;
	private ArrayList<Remito> remitos;
	private JTextField tfSubtotal;
	private JTextField tfTotal;
	private JTextField tfDescuento;
	private JTextField tfCodigo;
	private JTextField tfVendedor;
	private JTextField tfBuscador;

	/**
	 * Create the application.
	 */
	public VistaArchivoRemitos(Comercio comercio, JButton label) {
		initialize(comercio, label);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Comercio comercio, JButton label) {
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
		
		frmArchivoDeRemitos = new JFrame();
		frmArchivoDeRemitos.setResizable(false);
		frmArchivoDeRemitos.setTitle("Archivo de remitos");
		frmArchivoDeRemitos.setBounds(100, 100, 1135, 425);
		frmArchivoDeRemitos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmArchivoDeRemitos.getContentPane().setLayout(null);
		
		remito = new Remito();
		comercio.getArchivoRemitos().cargarRemitosDesdeBD();
		comercio.getListaVendedores().cargarVendedoresDesdeBD();
		comercio.getListaClientes().cargarClientesDesdeBD();
		remitos = comercio.getArchivoRemitos().getRemitos();
		DecimalFormat formateador = new DecimalFormat("###,###.##");
		
		JLabel lResultados = new JLabel("No se han encontrado resultados para la b\u00FAsqueda.");
		lResultados.setForeground(Color.RED);
		lResultados.setBackground(Color.RED);
		lResultados.setBounds(80, 232, 315, 14);
		frmArchivoDeRemitos.getContentPane().add(lResultados);
		lResultados.setVisible(false);
		
		JLabel lblHistorialDeVentas = new JLabel("Remitos");
		lblHistorialDeVentas.setIcon(new ImageIcon(VistaArchivoRemitos.class.getResource("/imagenes/my_invoices.png")));
		lblHistorialDeVentas.setFont(new Font("Calibri", Font.PLAIN, 24));
		lblHistorialDeVentas.setBounds(23, 14, 145, 32);
		frmArchivoDeRemitos.getContentPane().add(lblHistorialDeVentas);
		lblHistorialDeVentas.setHorizontalTextPosition(SwingConstants.LEFT);
		
		JLabel lblDatosDeLa = new JLabel("DATOS DE LA VENTA");
		lblDatosDeLa.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblDatosDeLa.setBounds(629, 20, 174, 26);
		frmArchivoDeRemitos.getContentPane().add(lblDatosDeLa);
		
		tfNombre = new JTextField();
		tfNombre.setEditable(false);
		tfNombre.setBounds(611, 101, 215, 27);
		frmArchivoDeRemitos.getContentPane().add(tfNombre);
		tfNombre.setColumns(10);
		
		JLabel label1 = new JLabel("Nombre del cliente:");
		label1.setBounds(485, 104, 123, 14);
		frmArchivoDeRemitos.getContentPane().add(label1);
		
		JLabel label_4 = new JLabel("Fecha:");
		label_4.setBounds(485, 134, 123, 14);
		frmArchivoDeRemitos.getContentPane().add(label_4);
		
		tfFecha = new JTextField();
		tfFecha.setEditable(false);
		tfFecha.setColumns(10);
		tfFecha.setBounds(611, 132, 215, 27);
		frmArchivoDeRemitos.getContentPane().add(tfFecha);
		
		JComboBox<String> cbDetalle = new JComboBox<String>();
		cbDetalle.setBounds(551, 207, 562, 27);
		frmArchivoDeRemitos.getContentPane().add(cbDetalle);
		frmArchivoDeRemitos.setVisible(true);
		
		JComboBox<String> cbBusqueda = new JComboBox<String>();
		cbBusqueda.setFont(new Font("SansSerif", Font.PLAIN, 22));
		cbBusqueda.setBounds(25, 181, 373, 40);
		frmArchivoDeRemitos.getContentPane().add(cbBusqueda);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.DARK_GRAY);
		separator.setBackground(Color.GRAY);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(436, 57, 9, 303);
		frmArchivoDeRemitos.getContentPane().add(separator);
		
		tfSubtotal = new JTextField();
		tfSubtotal.setEditable(false);
		tfSubtotal.setColumns(10);
		tfSubtotal.setBounds(629, 268, 106, 27);
		frmArchivoDeRemitos.getContentPane().add(tfSubtotal);
		
		tfTotal = new JTextField();
		tfTotal.setEditable(false);
		tfTotal.setColumns(10);
		tfTotal.setBounds(629, 330, 106, 27);
		frmArchivoDeRemitos.getContentPane().add(tfTotal);
		
		tfDescuento = new JTextField();
		tfDescuento.setEditable(false);
		tfDescuento.setColumns(10);
		tfDescuento.setBounds(629, 299, 39, 27);
		frmArchivoDeRemitos.getContentPane().add(tfDescuento);
		
		tfCodigo = new JTextField();
		tfCodigo.setEditable(false);
		tfCodigo.setColumns(10);
		tfCodigo.setBounds(611, 70, 215, 27);
		frmArchivoDeRemitos.getContentPane().add(tfCodigo);
		
		tfVendedor = new JTextField();
		tfVendedor.setEditable(false);
		tfVendedor.setColumns(10);
		tfVendedor.setBounds(611, 163, 215, 27);
		frmArchivoDeRemitos.getContentPane().add(tfVendedor);
		
		JLabel lblSubtotal = new JLabel("Subtotal ($):");
		lblSubtotal.setBounds(529, 275, 123, 14);
		frmArchivoDeRemitos.getContentPane().add(lblSubtotal);
		
		JLabel lblTotal = new JLabel("Total ($):");
		lblTotal.setBounds(529, 337, 123, 14);
		frmArchivoDeRemitos.getContentPane().add(lblTotal);
		
		JLabel lblVendedor = new JLabel("Vendedor:");
		lblVendedor.setBounds(485, 166, 123, 14);
		frmArchivoDeRemitos.getContentPane().add(lblVendedor);
		
		JLabel lblCdigoDeRemito = new JLabel("C\u00F3digo de remito:");
		lblCdigoDeRemito.setBounds(485, 73, 123, 14);
		frmArchivoDeRemitos.getContentPane().add(lblCdigoDeRemito);
		
		JLabel lblDescuento = new JLabel("Descuento (%):");
		lblDescuento.setBounds(529, 306, 123, 14);
		frmArchivoDeRemitos.getContentPane().add(lblDescuento);
		
		JLabel lblDetalle = new JLabel("DETALLE");
		lblDetalle.setFont(new Font("Sitka Display", Font.PLAIN, 20));
		lblDetalle.setBounds(457, 209, 123, 26);
		frmArchivoDeRemitos.getContentPane().add(lblDetalle);
		
		JButton btnNewButton = new JButton("Imprimir remito");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "¿Desea imprimir el remito?", "Imprimir remito", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
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
											RemitoImp_onlyTable remito_onlytable = new RemitoImp_onlyTable(comercio, remito, detalles_mas30);
											gap.setPrintable(remito_onlytable);
											boolean top = gap.printDialog();
											if (top) {
												try {
													gap.print();
												} catch (PrinterException e) {
													JOptionPane.showMessageDialog(null, "ERROR " + e);
												}
											}
										}
									});
								}else {
									SwingUtilities.invokeLater(new Runnable() {			
										@Override
										public void run() {
											RemitoImp_onlyTable remito_onlytable = new RemitoImp_onlyTable(comercio, remito, null);
											gap.setPrintable(remito_onlytable);
											boolean top = gap.printDialog();
											if (top) {
												try {
													gap.print();
												} catch (PrinterException e) {
													JOptionPane.showMessageDialog(null, "ERROR " + e);
												}
											}
										}
									});
								}
							}
						}
					});
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(VistaArchivoRemitos.class.getResource("/imagenes/print.png")));
		btnNewButton.setBounds(834, 259, 230, 46);
		frmArchivoDeRemitos.getContentPane().add(btnNewButton);
		
		JButton btnEliminarRemito = new JButton("Eliminar remito");
		btnEliminarRemito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar el remito " + remito.toString() + "?", "Eliminar remito", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					comercio.getArchivoRemitos().eliminarRemitoBD(remito.getId());
					comercio.getArchivoRemitos().cargarRemitosDesdeBD();
					comercio.getStock().cargarStockDesdeBD();
					ArrayList<Detalle> detalles = remito.getListaDetalle();
					for (Detalle i : detalles) {
						comercio.getStock().NuevaCompra(i.getProducto_codigo(), i.getCantidad());
					}
					comercio.getStock().guardarStockBD();
					remitos = comercio.getArchivoRemitos().getRemitos();
				}
			}
		});
		btnEliminarRemito.setIcon(new ImageIcon(VistaArchivoRemitos.class.getResource("/imagenes/error.png")));
		btnEliminarRemito.setBounds(834, 314, 230, 46);
		frmArchivoDeRemitos.getContentPane().add(btnEliminarRemito);
		
		tfBuscador = new JTextField();
		tfBuscador.setBounds(39, 101, 327, 27);
		frmArchivoDeRemitos.getContentPane().add(tfBuscador);
		tfBuscador.setColumns(10);
		
		JLabel lblBuscadorDe = new JLabel(">> Buscador de remitos<<");
		lblBuscadorDe.setFont(new Font("Serif", Font.PLAIN, 18));
		lblBuscadorDe.setBounds(93, 57, 249, 21);
		frmArchivoDeRemitos.getContentPane().add(lblBuscadorDe);
		
		JLabel lblNewLabel = new JLabel("(se puede buscar por n\u00FAmero de cliente, monto o fecha)");
		lblNewLabel.setBounds(60, 76, 321, 14);
		frmArchivoDeRemitos.getContentPane().add(lblNewLabel);
		
		JLabel lblResultadosDeLa = new JLabel("Resultados de la b\u00FAsqueda");
		lblResultadosDeLa.setIcon(null);
		lblResultadosDeLa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblResultadosDeLa.setBounds(109, 140, 247, 46);
		frmArchivoDeRemitos.getContentPane().add(lblResultadosDeLa);
		
		JButton btnNewButton_1 = new JButton("Actualizar lista   ");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comercio.getArchivoRemitos().cargarRemitosDesdeBD();
				remitos = comercio.getArchivoRemitos().getRemitos();
				cbBusqueda.removeAllItems();
				for (Remito i : remitos) {
					cbBusqueda.addItem(i.toString());
				}
				cbBusqueda.setSelectedIndex(0);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(VistaArchivoRemitos.class.getResource("/imagenes/refresh1.png")));
		btnNewButton_1.setHorizontalTextPosition(SwingConstants.LEFT);
		btnNewButton_1.setBounds(23, 332, 174, 46);
		frmArchivoDeRemitos.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Nuevo remito");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						new VistaAgregarRemito(comercio, label);
					}			
				});
			}
		});
		btnNewButton_1_1.setIcon(new ImageIcon(VistaArchivoRemitos.class.getResource("/imagenes/netvibes.png")));
		btnNewButton_1_1.setHorizontalTextPosition(SwingConstants.LEFT);
		btnNewButton_1_1.setBounds(221, 332, 174, 46);
		frmArchivoDeRemitos.getContentPane().add(btnNewButton_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(VistaArchivoRemitos.class.getResource("/imagenes/search - copia.png")));
		lblNewLabel_1.setBounds(12, 101, 27, 26);
		frmArchivoDeRemitos.getContentPane().add(lblNewLabel_1);
		
		cbBusqueda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		tfBuscador.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cbBusqueda.removeAllItems();
				for (Remito i : remitos) {
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
		
		cbBusqueda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (null != cbBusqueda.getSelectedItem()) {
					for (Remito i : remitos) {
						if (cbBusqueda.getSelectedItem().equals(i.toString())) {
							remito = i;
							
							tfCodigo.setText(String.valueOf(i.getId()));
							if (comercio.getListaClientes().getCliente_byID(i.getId_cliente()) != null) {
								tfNombre.setText(comercio.getListaClientes().getCliente_byID(i.getId_cliente()).getNombre());
							}
							tfFecha.setText(i.dateToString());
							if (comercio.getListaVendedores().getVendedor_byID(i.getId_vendedor()) != null){
								tfVendedor.setText(comercio.getListaVendedores().getVendedor_byID(i.getId_vendedor()).getNombre());
							}
							tfSubtotal.setText(formateador.format(i.getSubtotal()));
							tfDescuento.setText(String.valueOf(i.getDescuento()));
							tfTotal.setText(formateador.format(i.getTotal()));
							
							cbDetalle.removeAllItems();
							ArrayList<Detalle> detalles = i.getListaDetalle();
							for (Detalle j : detalles) {
								cbDetalle.addItem(j.toString());
							}
								
							
							break;
						}
					}
				}
			}
		});
		
		for (Remito i : remitos) {
			cbBusqueda.addItem(i.toString());
		}
	}
}
