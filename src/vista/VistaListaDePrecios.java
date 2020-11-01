package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import imprimibles.ListaPreciosImp;
import imprimibles.ListaPreciosImp_onlyTable;
import modelo.Categoria;
import modelo.Comercio;
import modelo.Producto;
public class VistaListaDePrecios {

	private JFrame frmCatlogo;
	private JTextField tfNombre;
	private JTextField tfPrecioC;
	private JTextField tfPrecioV;
	private JTextField tfUnidades;
	private JTextField tfCantStock;
	private JTextField tfCodigo;
	private JTextField tfBuscador;
	private Producto producto;
	private ArrayList<Producto> productos;
	private ArrayList<Categoria> categorias;

	public VistaListaDePrecios(Comercio comercio, JButton label) {
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
		
		frmCatlogo = new JFrame();
		frmCatlogo.setResizable(false);
		frmCatlogo.setTitle("Lista de precios");
		frmCatlogo.setBounds(100, 100, 745, 556);
		frmCatlogo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCatlogo.getContentPane().setLayout(null);
		
		JLabel lblCatlogo = new JLabel("Lista de precios");
		lblCatlogo.setFont(new Font("Microsoft JhengHei Light", Font.BOLD, 18));
		lblCatlogo.setBounds(10, 11, 145, 31);
		frmCatlogo.getContentPane().add(lblCatlogo);
		
		JComboBox<String> cbBusqueda = new JComboBox<String>();
		cbBusqueda.setBackground(Color.WHITE);
		cbBusqueda.setFont(new Font("SansSerif", Font.BOLD, 22));
		cbBusqueda.setMaximumRowCount(16);
		cbBusqueda.setBounds(10, 162, 389, 33);
		frmCatlogo.getContentPane().add(cbBusqueda);
		
		tfCodigo = new JTextField();
		tfCodigo.setEditable(false);
		tfCodigo.setColumns(10);
		tfCodigo.setBounds(525, 65, 74, 27);
		frmCatlogo.getContentPane().add(tfCodigo);
		
		JLabel lblDatosDelProducto = new JLabel("Datos del producto");
		lblDatosDelProducto.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblDatosDelProducto.setBounds(484, 18, 145, 20);
		frmCatlogo.getContentPane().add(lblDatosDelProducto);
		
		JCheckBox cbxStockeable = new JCheckBox("No");
		cbxStockeable.setEnabled(false);
		cbxStockeable.setBounds(661, 283, 78, 23);
		frmCatlogo.getContentPane().add(cbxStockeable);
		
		tfNombre = new JTextField();
		tfNombre.setEditable(false);
		tfNombre.setBounds(523, 100, 191, 27);
		frmCatlogo.getContentPane().add(tfNombre);
		tfNombre.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(439, 107, 56, 14);
		frmCatlogo.getContentPane().add(lblNombre);
		
		JLabel lblPrecioDeCompra = new JLabel("Precio de compra ($):");
		lblPrecioDeCompra.setBounds(439, 137, 135, 14);
		frmCatlogo.getContentPane().add(lblPrecioDeCompra);
		
		JLabel lblGuardado = new JLabel("Datos del producto guardados.");
		lblGuardado.setBackground(Color.WHITE);
		lblGuardado.setForeground(Color.GREEN);
		lblGuardado.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblGuardado.setBounds(484, 402, 222, 27);
		frmCatlogo.getContentPane().add(lblGuardado);
		lblGuardado.setVisible(false);
		
		
		JComboBox<String> cbCategorias = new JComboBox<String>();
		cbCategorias.setEnabled(false);
		cbCategorias.setBounds(523, 202, 191, 27);
		frmCatlogo.getContentPane().add(cbCategorias);
		
		tfPrecioC = new JTextField();
		tfPrecioC.setEditable(false);
		tfPrecioC.setColumns(10);
		tfPrecioC.setBounds(570, 134, 74, 27);
		frmCatlogo.getContentPane().add(tfPrecioC);
		
		JLabel lblPrecioDeVenta = new JLabel("Precio de venta ($):");
		lblPrecioDeVenta.setBounds(439, 168, 135, 14);
		frmCatlogo.getContentPane().add(lblPrecioDeVenta);
		
		tfPrecioV = new JTextField();
		tfPrecioV.setEditable(false);
		tfPrecioV.setColumns(10);
		tfPrecioV.setBounds(570, 165, 74, 27);
		frmCatlogo.getContentPane().add(tfPrecioV);
		
		tfCantStock = new JTextField();
		tfCantStock.setText("0");
		tfCantStock.setEditable(false);
		tfCantStock.setColumns(10);
		tfCantStock.setBounds(645, 315, 74, 27);
		frmCatlogo.getContentPane().add(tfCantStock);
		
		JLabel lblUnidadesParaAviso = new JLabel("Unidades para aviso de falta:");
		lblUnidadesParaAviso.setBounds(458, 349, 171, 14);
		frmCatlogo.getContentPane().add(lblUnidadesParaAviso);
		
		tfUnidades = new JTextField();
		tfUnidades.setEditable(false);
		tfUnidades.setColumns(10);
		tfUnidades.setBounds(645, 346, 74, 27);
		frmCatlogo.getContentPane().add(tfUnidades);
		
		JCheckBox cbEditable = new JCheckBox("Editable");
		cbEditable.setBounds(570, 378, 78, 23);
		frmCatlogo.getContentPane().add(cbEditable);
		
		JButton btnAgregarNuevoProducto = new JButton("Agregar nuevo producto");
		btnAgregarNuevoProducto.setIcon(new ImageIcon(VistaListaDePrecios.class.getResource("/imagenes/new_doc.png")));
		btnAgregarNuevoProducto.setHorizontalTextPosition(SwingConstants.LEFT);
		btnAgregarNuevoProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 SwingUtilities.invokeLater(new Runnable() {
			         @Override
			         public void run() {
			            new VistaAgregarProducto(comercio, label, cbBusqueda); 
			         }
			      });
			}
		});
		btnAgregarNuevoProducto.setBounds(84, 402, 207, 46);
		frmCatlogo.getContentPane().add(btnAgregarNuevoProducto);
		
		JButton btnGuardarDatosDel = new JButton("Guardar nuevos datos");
		btnGuardarDatosDel.setIcon(new ImageIcon(VistaListaDePrecios.class.getResource("/imagenes/save.png")));
		btnGuardarDatosDel.setFont(new Font("SansSerif", Font.BOLD, 11));
		btnGuardarDatosDel.setSelectedIcon(null);
		btnGuardarDatosDel.setHorizontalTextPosition(SwingConstants.LEFT);
		btnGuardarDatosDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfNombre.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "No se puede tener un producto sin nombre");
				}
				else {
					if (tfPrecioC.getText().equals("") || tfPrecioV.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Debe ingresar el precio de costo y venta del producto");
					}
					else {
						String sMonto = tfPrecioC.getText();
						boolean flag = false;
						for (int i = 0; i< sMonto.length(); i++) {
							if ((sMonto.charAt(i)<48 || sMonto.charAt(i)>57) && (sMonto.charAt(i) != 46)) {
								JOptionPane.showMessageDialog(null, "Ingrese números en los campos de dinero");
								flag = true;
								break;
							}
						}
						if (!flag) {
							sMonto = tfPrecioV.getText();
							for (int i = 0; i< sMonto.length(); i++) {
								if ((sMonto.charAt(i)<48 || sMonto.charAt(i)>57) && (sMonto.charAt(i) != 46)) {
									JOptionPane.showMessageDialog(null, "Ingrese números en los campos de dinero");
									flag = true;
									break;
								}
							}
						}
						if (!flag) {
							if (tfCantStock.getText().equals("") && cbxStockeable.isSelected()) {
								flag = true;
							}
						}
						if (!flag) {
							if (tfUnidades.getText().equals("") && cbxStockeable.isSelected()) {
								flag = true;
							}
						}
						if (!flag) {
							sMonto = tfCantStock.getText();
							for (int i = 0; i< sMonto.length(); i++) {
								if (sMonto.charAt(i)<48 || sMonto.charAt(i)>57) {
									JOptionPane.showMessageDialog(null, "Ingrese números en los campos correspondientes");
									flag = true;
									break;
								}
							}
						}
						if (!flag) {
							sMonto = tfUnidades.getText();
							for (int i = 0; i< sMonto.length(); i++) {
								if (sMonto.charAt(i)<48 || sMonto.charAt(i)>57) {
									JOptionPane.showMessageDialog(null, "Ingrese números en los campos correspondientes");
									flag = true;
									break;
								}
							}
						}
						if (!flag) {
							producto.setNombre(tfNombre.getText());
							producto.setCodigo(Integer.parseInt(tfCodigo.getText()));
							producto.setPrecio_costo(Double.parseDouble(tfPrecioC.getText()));
							producto.setPrecio_venta(Double.parseDouble(tfPrecioV.getText()));
							producto.setCategoria(comercio.getListaDePrecios().getIdCategoria_byNombre((String) cbCategorias.getSelectedItem()));
							if (cbxStockeable.isSelected()) {
								producto.setCant_falta_stock(Integer.parseInt(tfUnidades.getText()));
								producto.setCant_stock(Integer.parseInt(tfCantStock.getText()));
							} else {
								producto.setCant_falta_stock(-1);
								producto.setCant_stock(-1);
							}
							
							comercio.getListaDePrecios().modificarProductoBD(producto);
							comercio.getListaDePrecios().cargarProductosDesdeBD();
							String prodactual = producto.toString();
							lblGuardado.setVisible(true);
							cbBusqueda.removeAllItems();
							productos = comercio.getListaDePrecios().getProductos();
							for (Producto i : productos) {
								cbBusqueda.addItem(i.toString());
							}
							cbBusqueda.setSelectedItem(prodactual);
							lblGuardado.setVisible(true);
							Timer timer = new Timer(4000, new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									lblGuardado.setVisible(false);
									
								}
							});
							timer.start();
							timer.setRepeats(false);
						}
					}
				}
			}
		});
		btnGuardarDatosDel.setBounds(458, 431, 240, 35);
		frmCatlogo.getContentPane().add(btnGuardarDatosDel);
		
		comercio.getListaDePrecios().cargarCategoriasDesdeBD();
		comercio.getListaDePrecios().cargarProductosDesdeBD_orderbyCat();
		
		productos = comercio.getListaDePrecios().getProductos();
		categorias = comercio.getListaDePrecios().getCategorias();
		
		for (Producto i : productos) {
			cbBusqueda.addItem(i.toString());
		}
		
		for (Categoria i : categorias) {
			cbCategorias.addItem(i.getNombre());
		}
		
		cbBusqueda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (null != cbBusqueda.getSelectedItem()) {
					for (Producto i : productos) {
						if (cbBusqueda.getSelectedItem().equals(i.toString())) {
							producto = i;
							
							tfCodigo.setText(String.valueOf(i.getCodigo()));
							tfNombre.setText(i.getNombre());
							tfPrecioC.setText(String.valueOf(i.getPrecio_costo()));
							tfPrecioV.setText(String.valueOf(i.getPrecio_venta()));
							
							cbCategorias.setSelectedItem(comercio.getListaDePrecios().getNombreCategoria_byId(i.getCategoria()));;
							
							if ( (i.getCant_stock() == -1) || (i.getCant_falta_stock() == -1) ) {
								tfCantStock.setText("");
								tfUnidades.setText("");
								tfUnidades.setEditable(false);
								tfCantStock.setEditable(false);
								cbxStockeable.setSelected(false);
								cbxStockeable.setText("No");
							} else {
								tfCantStock.setText(String.valueOf(i.getCant_stock()));
								tfUnidades.setText(String.valueOf(i.getCant_falta_stock()));
								cbxStockeable.setSelected(true);
								cbxStockeable.setText("Si");
							}
							break;
						}
					}
				}
			}
		});
		
		cbEditable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbEditable.isSelected()) {
					tfNombre.setEditable(true);
					tfPrecioC.setEditable(true);
					tfPrecioV.setEditable(true);
					if (cbxStockeable.isSelected()) {
						tfUnidades.setEditable(true);
						tfCantStock.setEditable(true);
					}
					cbCategorias.setEnabled(true);
					cbxStockeable.setEnabled(true);
					cbCategorias.setSelectedItem(comercio.getListaDePrecios().getNombreCategoria_byId(producto.getCategoria()));
					
				}
				else {
					tfNombre.setEditable(false);
					tfPrecioC.setEditable(false);
					tfPrecioV.setEditable(false);
					tfUnidades.setEditable(false);
					tfCantStock.setEditable(false);
					cbCategorias.setEnabled(false);
					cbxStockeable.setEnabled(false);
				}
			}
		});
		
		for (Producto i : productos) {
			if (i.toString().equals(cbBusqueda.getSelectedItem())) {
				producto = i;
			}
		}
		
		tfNombre.setText(producto.getNombre());
		tfPrecioC.setText(String.valueOf(producto.getPrecio_costo()));
		tfPrecioV.setText(String.valueOf(producto.getPrecio_venta()));
		tfCodigo.setText(String.valueOf(producto.getCodigo()));
		
		if ( (producto.getCant_stock() == -1) || (producto.getCant_falta_stock() == -1) ) {
			tfCantStock.setText("");
			tfUnidades.setText("");
			cbxStockeable.setSelected(false);
			cbxStockeable.setText("No");
		} else {
			tfCantStock.setText(String.valueOf(producto.getCant_stock()));
			tfUnidades.setText(String.valueOf(producto.getCant_falta_stock()));
			cbxStockeable.setSelected(true);
			cbxStockeable.setText("Si");
		}
		
		JLabel lblCantidadEnStock = new JLabel("Cantidad en stock:");
		lblCantidadEnStock.setBounds(458, 318, 171, 14);
		frmCatlogo.getContentPane().add(lblCantidadEnStock);
		
		
		JLabel lblseGestionaStock = new JLabel("\u00BFSe gestiona stock de este producto?");
		lblseGestionaStock.setBounds(431, 287, 222, 14);
		frmCatlogo.getContentPane().add(lblseGestionaStock);
		
		JLabel catyer = new JLabel("Categor\u00EDa:");
		catyer.setBounds(439, 206, 135, 14);
		frmCatlogo.getContentPane().add(catyer);
		
		JLabel lblC = new JLabel("C\u00F3digo:");
		lblC.setBounds(439, 72, 56, 14);
		frmCatlogo.getContentPane().add(lblC);
		
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(484, 235, 194, 10);
		frmCatlogo.getContentPane().add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(412, 59, 17, 402);
		frmCatlogo.getContentPane().add(separator_2);
		
		tfBuscador = new JTextField();
		tfBuscador.setBounds(71, 85, 283, 27);
		frmCatlogo.getContentPane().add(tfBuscador);
		tfBuscador.setColumns(10);
		
		JLabel lblBuscadorDeProductos = new JLabel(">> Buscador de productos <<");
		lblBuscadorDeProductos.setFont(new Font("Serif", Font.PLAIN, 18));
		lblBuscadorDeProductos.setBounds(90, 58, 249, 21);
		frmCatlogo.getContentPane().add(lblBuscadorDeProductos);
		
		JLabel lblResultadosDeLa = new JLabel("Resultados de la b\u00FAsqueda");
		lblResultadosDeLa.setIcon(null);
		lblResultadosDeLa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblResultadosDeLa.setBounds(103, 133, 191, 33);
		frmCatlogo.getContentPane().add(lblResultadosDeLa);
		
		JButton btnImprimirListaDe = new JButton("Imprimir lista de precios   ");
		btnImprimirListaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrinterJob job = PrinterJob.getPrinterJob();
				SwingUtilities.invokeLater(new Runnable() {			
					@Override
					public void run() {
						ListaPreciosImp listaimprimible = new ListaPreciosImp(comercio);
						job.setPrintable(listaimprimible);
					}
				});
				boolean top = job.printDialog();
				if (top) {
					try {
						job.print();
					} catch (PrinterException e2) {
						JOptionPane.showMessageDialog(null, "ERROR " + e2);
					}
				}
				int contador = 0;
				ArrayList<Producto> prods_35 = new ArrayList<Producto>();
				for (int i = 35; i < productos.size(); i++) {
					prods_35.add(productos.get(i));
					contador++;
					if (contador == 40 || productos.indexOf(productos.get(i)) == productos.size()-1) {
						contador = 0;
						SwingUtilities.invokeLater(new Runnable() {					
							@Override
							public void run() {
								ListaPreciosImp_onlyTable listaimprimible_onlytable = new ListaPreciosImp_onlyTable(comercio, prods_35);
								job.setPrintable(listaimprimible_onlytable);
							}
						});
						top = job.printDialog();
						if (top) {
							try {
								job.print();
							} catch (PrinterException e2) {
								JOptionPane.showMessageDialog(null, "ERROR " + e2);
							}
						}
						prods_35.clear();
					}
				}
			}
		});
		btnImprimirListaDe.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnImprimirListaDe.setIcon(new ImageIcon(VistaListaDePrecios.class.getResource("/imagenes/print.png")));
		btnImprimirListaDe.setBounds(84, 455, 207, 46);
		btnImprimirListaDe.setHorizontalTextPosition(SwingConstants.LEFT);
		frmCatlogo.getContentPane().add(btnImprimirListaDe);
		
		JButton btnNewButton = new JButton("Actualizar lista   ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comercio.getListaDePrecios().cargarProductosDesdeBD_orderbyCat();
				productos = comercio.getListaDePrecios().getProductos();
				cbBusqueda.removeAllItems();
				for (Producto i : productos) {
					cbBusqueda.addItem(i.toString());
				}
				cbBusqueda.setSelectedIndex(0);
				lblGuardado.setVisible(false);
			}
		});
		btnNewButton.setIcon(new ImageIcon(VistaListaDePrecios.class.getResource("/imagenes/refresh1.png")));
		btnNewButton.setSelectedIcon(new ImageIcon(VistaListaDePrecios.class.getResource("/imagenes/refresh1.png")));
		btnNewButton.setBounds(84, 349, 207, 46);
		btnNewButton.setHorizontalTextPosition(SwingConstants.LEFT);
		frmCatlogo.getContentPane().add(btnNewButton);
		
		JButton btnEliminarProducto = new JButton("Eliminar producto  ");
		btnEliminarProducto.setHorizontalTextPosition(SwingConstants.LEFT);
		btnEliminarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar el producto " + producto.toString() + "?", "Eliminar producto", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					comercio.getListaDePrecios().eliminarProductoBD(producto.getCodigo());
					comercio.getListaDePrecios().cargarProductosDesdeBD();
					productos = comercio.getListaDePrecios().getProductos();
				}
			}
		});
		btnEliminarProducto.setIcon(new ImageIcon(VistaListaDePrecios.class.getResource("/imagenes/error.png")));
		btnEliminarProducto.setFont(new Font("SansSerif", Font.BOLD, 11));
		btnEliminarProducto.setBounds(458, 474, 240, 35);
		frmCatlogo.getContentPane().add(btnEliminarProducto);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(VistaListaDePrecios.class.getResource("/imagenes/price_tag.png")));
		lblNewLabel.setBounds(146, 5, 46, 46);
		frmCatlogo.getContentPane().add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.BLACK);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(27, 318, 7, 161);
		frmCatlogo.getContentPane().add(separator);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(Color.BLACK);
		separator_3.setBackground(Color.BLACK);
		separator_3.setBounds(28, 477, 47, 2);
		frmCatlogo.getContentPane().add(separator_3);
		
		JSeparator separator_3_1 = new JSeparator();
		separator_3_1.setBackground(Color.BLACK);
		separator_3_1.setForeground(Color.BLACK);
		separator_3_1.setBounds(28, 423, 47, 14);
		frmCatlogo.getContentPane().add(separator_3_1);
		
		JSeparator separator_3_2 = new JSeparator();
		separator_3_2.setBackground(Color.BLACK);
		separator_3_2.setForeground(Color.BLACK);
		separator_3_2.setBounds(28, 368, 46, 6);
		frmCatlogo.getContentPane().add(separator_3_2);
		
		JLabel lblNewLabel_1 = new JLabel("Men\u00FA");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 17));
		lblNewLabel_1.setBounds(59, 265, 64, 31);
		frmCatlogo.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(VistaListaDePrecios.class.getResource("/imagenes/menu.png")));
		lblNewLabel_2.setBounds(10, 256, 46, 47);
		frmCatlogo.getContentPane().add(lblNewLabel_2);
		
		JLabel lResultados = new JLabel("No se han encontrado resultados para la b\u00FAsqueda.");
		lResultados.setForeground(Color.RED);
		lResultados.setBackground(Color.RED);
		lResultados.setBounds(84, 206, 315, 14);
		frmCatlogo.getContentPane().add(lResultados);
		
		JLabel lblNewLabel_3 = new JLabel("Stock");
		lblNewLabel_3.setFont(new Font("Calibri", Font.ITALIC, 20));
		lblNewLabel_3.setBounds(535, 256, 64, 22);
		frmCatlogo.getContentPane().add(lblNewLabel_3);
		
		JButton btnAgregarCategora = new JButton("Gestionar categor\u00EDas");
		btnAgregarCategora.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {	
					@Override
					public void run() {
						new VistaCategorias(comercio, cbCategorias);
					}
				});
			}
		});
		btnAgregarCategora.setIcon(new ImageIcon(VistaListaDePrecios.class.getResource("/imagenes/menu_2.png")));
		btnAgregarCategora.setHorizontalTextPosition(SwingConstants.LEFT);
		btnAgregarCategora.setBounds(84, 296, 207, 46);
		frmCatlogo.getContentPane().add(btnAgregarCategora);
		
		JSeparator separator_3_2_1 = new JSeparator();
		separator_3_2_1.setForeground(Color.BLACK);
		separator_3_2_1.setBackground(Color.BLACK);
		separator_3_2_1.setBounds(28, 317, 46, 6);
		frmCatlogo.getContentPane().add(separator_3_2_1);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(VistaListaDePrecios.class.getResource("/imagenes/search - copia.png")));
		lblNewLabel_4.setBounds(47, 85, 23, 23);
		frmCatlogo.getContentPane().add(lblNewLabel_4);
		lResultados.setVisible(false);
		
		
		
		cbxStockeable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cbxStockeable.isSelected()) {
					cbxStockeable.setText("Si");
					tfCantStock.setText("0");
					tfUnidades.setText("0");
					tfCantStock.setEditable(true);
					tfUnidades.setEditable(true);
				} else {
					cbxStockeable.setText("No");
					tfCantStock.setText("");
					tfUnidades.setText("");
					tfCantStock.setEditable(false);
					tfUnidades.setEditable(false);
				}
			}
		});
		
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
		
		frmCatlogo.setVisible(true);
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
