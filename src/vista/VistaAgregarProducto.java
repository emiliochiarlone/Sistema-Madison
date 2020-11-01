package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import modelo.Categoria;
import modelo.Comercio;
import modelo.Producto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class VistaAgregarProducto {

	private JFrame frmAgregarNuevoProducto;
	private JTextField tfNombre;
	private JTextField tfPrecioC;
	private JTextField tfPrecioV;
	private JTextField tfCUnidades;
	private JButton btnNewButton;
	private JSeparator separator;
	private JTextField tfCodigo;
	private JLabel lblCategora;
	private JLabel lblGestinDeStock;

	/**
	 * Create the application.
	 * @param cbProductos 
	 */
	public VistaAgregarProducto(Comercio comercio, JButton label, JComboBox cbBusqueda) {
		initialize(comercio, label, cbBusqueda);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Comercio comercio, JButton label, JComboBox<String> cbBusqueda) {
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
		
		frmAgregarNuevoProducto = new JFrame();
		frmAgregarNuevoProducto.setResizable(false);
		frmAgregarNuevoProducto.setTitle("Agregar nuevo producto");
		frmAgregarNuevoProducto.setBounds(100, 100, 377, 430);
		frmAgregarNuevoProducto.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAgregarNuevoProducto.getContentPane().setLayout(null);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(157, 36, 183, 27);
		frmAgregarNuevoProducto.getContentPane().add(tfNombre);
		tfNombre.setColumns(10);
		
		JLabel lblAgregarNuevoProducto = new JLabel("Agregar nuevo producto a la lista");
		lblAgregarNuevoProducto.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblAgregarNuevoProducto.setBounds(57, 6, 330, 20);
		frmAgregarNuevoProducto.getContentPane().add(lblAgregarNuevoProducto);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(20, 43, 86, 14);
		frmAgregarNuevoProducto.getContentPane().add(lblNewLabel);
		
		JComboBox<String> cbCategorias = new JComboBox();
		cbCategorias.setBounds(157, 169, 183, 27);
		frmAgregarNuevoProducto.getContentPane().add(cbCategorias);
		
		JCheckBox cbStockeable = new JCheckBox("No");
		cbStockeable.setBounds(243, 247, 97, 23);
		frmAgregarNuevoProducto.getContentPane().add(cbStockeable);
		frmAgregarNuevoProducto.setVisible(true);
		
		JLabel lblPrecioDeCompra = new JLabel("Precio de compra ($):");
		lblPrecioDeCompra.setBounds(20, 109, 148, 14);
		frmAgregarNuevoProducto.getContentPane().add(lblPrecioDeCompra);
		
		tfPrecioC = new JTextField();
		tfPrecioC.setColumns(10);
		tfPrecioC.setBounds(157, 102, 96, 27);
		frmAgregarNuevoProducto.getContentPane().add(tfPrecioC);
		
		tfPrecioV = new JTextField();
		tfPrecioV.setColumns(10);
		tfPrecioV.setBounds(157, 136, 96, 27);
		frmAgregarNuevoProducto.getContentPane().add(tfPrecioV);
		
		JLabel lblPrecioDeVenta = new JLabel("Precio de venta ($):");
		lblPrecioDeVenta.setBounds(20, 143, 148, 14);
		frmAgregarNuevoProducto.getContentPane().add(lblPrecioDeVenta);
		
		tfCUnidades = new JTextField();
		tfCUnidades.setEnabled(false);
		tfCUnidades.setColumns(10);
		tfCUnidades.setBounds(243, 277, 39, 23);
		frmAgregarNuevoProducto.getContentPane().add(tfCUnidades);
		
		comercio.getListaDePrecios().cargarCategoriasDesdeBD();
		
		ArrayList<Categoria> categorias = comercio.getListaDePrecios().getCategorias();
		
		for (Categoria i : categorias) {
			cbCategorias.addItem(i.getNombre());
		}
	
		
		
		JLabel lblUnidadesParaAviso = new JLabel("- Unidades para aviso de falta:");
		lblUnidadesParaAviso.setBounds(69, 282, 184, 14);
		frmAgregarNuevoProducto.getContentPane().add(lblUnidadesParaAviso);
		
		btnNewButton = new JButton("Agregar producto a la lista");
		btnNewButton.setIcon(new ImageIcon(VistaAgregarProducto.class.getResource("/imagenes/button_ok.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "¿Está seguro que desea agregar el producto?", "Confirmar producto", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					if (tfNombre.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "No se puede registrar un producto sin nombre");
					}
					else {
						if (tfPrecioC.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Debe ingresar el precio de costo del producto");
						}
						else {
							String sMonto = tfPrecioC.getText();
							boolean flag = false;
							for (int i = 0; i< sMonto.length(); i++) {
								if ((sMonto.charAt(i)<48 || sMonto.charAt(i)>57) && (sMonto.charAt(i) != 46)) {
									JOptionPane.showMessageDialog(null, "Ingrese números en los campos de dinero");
									flag = true;
									i = sMonto.length();
								}
							}
							if (!flag) {
								sMonto = tfPrecioV.getText();
								for (int i = 0; i< sMonto.length(); i++) {
									if ((sMonto.charAt(i)<48 || sMonto.charAt(i)>57) && (sMonto.charAt(i) != 46)) {
										JOptionPane.showMessageDialog(null, "Ingrese números en los campos de dinero");
										flag = true;
										i = sMonto.length();
									}
								}
							}
							if (!flag) {
								sMonto = tfCUnidades.getText();
								for (int i = 0; i< sMonto.length(); i++) {
									if (sMonto.charAt(i)<48 || sMonto.charAt(i)>57) {
										JOptionPane.showMessageDialog(null, "Ingrese solo números en los campos correspondientes. \nTenga cuidado con no ingresar espacios.");
										flag = true;
										i = sMonto.length();
									}
								}
							}
							if (!flag) {
								sMonto = tfCodigo.getText();
								for (int i = 0; i< sMonto.length(); i++) {
									if (sMonto.charAt(i)<48 || sMonto.charAt(i)>57) {
										JOptionPane.showMessageDialog(null, "Ingrese solo números en los campos correspondientes. \nTenga cuidado con no ingresar espacios.");
										flag = true;
										i = sMonto.length();
									}
								}
							}
							if (!flag) {
								if (tfPrecioV.getText().equals("")) {
									flag= true;
									JOptionPane.showMessageDialog(null, "Debe ingresar precio de venta del producto.");
								}
							}
							if (!flag) {
								if (tfCodigo.getText().equals("")) {
									
								}
							}
							if (!flag) {
								Producto producto = new Producto();
								producto.setNombre(tfNombre.getText());
								producto.setCodigo(Integer.parseInt(tfCodigo.getText()));
								if (!cbStockeable.isSelected()) {
									producto.setCant_falta_stock(-1);
									producto.setCant_stock(-1);
								}
								else {
									producto.setCant_falta_stock(Integer.parseInt(tfCUnidades.getText()));
									producto.setCant_stock(0);
								}
								
								producto.setPrecio_costo(Double.parseDouble(tfPrecioC.getText()));
								producto.setPrecio_venta(Double.parseDouble(tfPrecioV.getText()));
								producto.setCategoria(comercio.getListaDePrecios().getIdCategoria_byNombre( (String)cbCategorias.getSelectedItem()));
								comercio.getListaDePrecios().insertarProductoBD(producto);
								
								comercio.getStock().cargarStockDesdeBD();
								comercio.getListaDePrecios().cargarProductosDesdeBD();
								if (cbBusqueda != null) {
									ArrayList <Producto> productos = comercio.getListaDePrecios().getProductos();
									cbBusqueda.removeAllItems();
									for (Producto i : productos) {
										cbBusqueda.addItem(i.toString());
									}
								}
								if (comercio.getStock().faltaStock()) {
									if (label != null) {
										label.setIcon(new ImageIcon(VistaAgregarProducto.class.getResource("/imagenes/faltastock.png")));
								
									}
								}
							
								frmAgregarNuevoProducto.dispose();
								JOptionPane.showMessageDialog(null, "Producto agregado");
							}
						}
					}
				}
			}
		});
		btnNewButton.setBounds(57, 327, 240, 47);
		frmAgregarNuevoProducto.getContentPane().add(btnNewButton);
		
		cbStockeable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cbStockeable.isSelected()) {
					cbStockeable.setText("Sí");
					tfCUnidades.setEnabled(true);
				} else {
					cbStockeable.setText("No");
					tfCUnidades.setText("");
					tfCUnidades.setEnabled(false);
				}
				
			}
		});
		
		
		separator = new JSeparator();
		separator.setBounds(20, 307, 320, 15);
		frmAgregarNuevoProducto.getContentPane().add(separator);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo (solo n\u00FAmeros):");
		lblCdigo.setBounds(20, 75, 148, 14);
		frmAgregarNuevoProducto.getContentPane().add(lblCdigo);
		
		tfCodigo = new JTextField();
		tfCodigo.setColumns(10);
		tfCodigo.setBounds(157, 68, 96, 27);
		frmAgregarNuevoProducto.getContentPane().add(tfCodigo);
		
		JLabel lblEsteProductoSer = new JLabel("- \u00BFSer\u00E1 gestionado en stock?:");
		lblEsteProductoSer.setBounds(69, 250, 184, 14);
		frmAgregarNuevoProducto.getContentPane().add(lblEsteProductoSer);
		
		lblCategora = new JLabel("Categor\u00EDa:");
		lblCategora.setBounds(20, 175, 148, 14);
		frmAgregarNuevoProducto.getContentPane().add(lblCategora);
		
		lblGestinDeStock = new JLabel("Gesti\u00F3n de stock del producto");
		lblGestinDeStock.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
		lblGestinDeStock.setBounds(57, 215, 330, 20);
		frmAgregarNuevoProducto.getContentPane().add(lblGestinDeStock);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(100, 201, 163, 15);
		frmAgregarNuevoProducto.getContentPane().add(separator_1);
		
	}
}
