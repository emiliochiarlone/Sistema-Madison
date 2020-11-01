package vista;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import modelo.Comercio;
import modelo.Producto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class VistaRegistrarCompraStock {

	private JFrame frmRegistrarCompraDe;
	private ArrayList<Producto> productos;
	private ArrayList<Producto> prodstock;

	public VistaRegistrarCompraStock(Comercio comercio, JComboBox<String> cbProductos, JButton label, JTextField textfield) {
		initialize(comercio, cbProductos, label, textfield);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Comercio comercio, JComboBox<String> pcbProductos, JButton label, JTextField textfield) {
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
		
		frmRegistrarCompraDe = new JFrame();
		frmRegistrarCompraDe.setResizable(false);
		frmRegistrarCompraDe.setTitle("Registrar compra de stock");
		frmRegistrarCompraDe.setVisible(true);
		frmRegistrarCompraDe.setBounds(100, 100, 445, 438);
		frmRegistrarCompraDe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmRegistrarCompraDe.getContentPane().setLayout(null);
		
		JComboBox <String> cbProductos = new JComboBox();
		cbProductos.setBounds(97, 45, 322, 27);
		frmRegistrarCompraDe.getContentPane().add(cbProductos);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinner.setBounds(195, 80, 76, 27);
		frmRegistrarCompraDe.getContentPane().add(spinner);
		
		JLabel lblProducto = new JLabel("Producto:");
		lblProducto.setBounds(23, 49, 65, 14);
		frmRegistrarCompraDe.getContentPane().add(lblProducto);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(119, 87, 65, 14);
		frmRegistrarCompraDe.getContentPane().add(lblCantidad);
		
		JButton btnAgregarProducto = new JButton("Agregar Producto");
		btnAgregarProducto.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 14));
		btnAgregarProducto.setBounds(75, 119, 277, 48);
		frmRegistrarCompraDe.getContentPane().add(btnAgregarProducto);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(21, 179, 382, 2);
		frmRegistrarCompraDe.getContentPane().add(separator);
		
		JComboBox cbProdCompra = new JComboBox();
		cbProdCompra.setMaximumRowCount(16);
		cbProdCompra.setBounds(52, 250, 322, 27);
		frmRegistrarCompraDe.getContentPane().add(cbProdCompra);
		
		JLabel lblProductosDeLa = new JLabel("Productos de la compra:");
		lblProductosDeLa.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
		lblProductosDeLa.setBounds(119, 192, 176, 22);
		frmRegistrarCompraDe.getContentPane().add(lblProductosDeLa);
		
		JLabel lblProducto_1 = new JLabel("Producto");
		lblProducto_1.setBounds(97, 225, 76, 14);
		frmRegistrarCompraDe.getContentPane().add(lblProducto_1);
		
		JLabel lblX = new JLabel("X");
		lblX.setBounds(195, 225, 46, 14);
		frmRegistrarCompraDe.getContentPane().add(lblX);
		
		JLabel lblCantidad_1 = new JLabel("Cantidad");
		lblCantidad_1.setBounds(274, 225, 62, 14);
		frmRegistrarCompraDe.getContentPane().add(lblCantidad_1);
		
		JButton btnEliminarTodosLos = new JButton("Eliminar todos los productos     ");
		btnEliminarTodosLos.setIcon(new ImageIcon(VistaRegistrarCompraStock.class.getResource("/imagenes/error.png")));
		btnEliminarTodosLos.setHorizontalTextPosition(SwingConstants.LEFT);
		btnEliminarTodosLos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				productos.clear();
				cbProdCompra.removeAllItems();		
			}
		});
		btnEliminarTodosLos.setBounds(97, 290, 242, 33);
		frmRegistrarCompraDe.getContentPane().add(btnEliminarTodosLos);
		
		comercio.getStock().cargarStockDesdeBD();
		prodstock = comercio.getStock().getProductos();
		productos = new ArrayList<Producto>();
		
		JButton btnRegistrarCompra = new JButton("Registrar compra      ");
		btnRegistrarCompra.setIcon(new ImageIcon(VistaRegistrarCompraStock.class.getResource("/imagenes/accept1.png")));
		btnRegistrarCompra.setHorizontalTextPosition(SwingConstants.LEFT);
		btnRegistrarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "¿Está seguro que desea registrar la compra?", "Confirmar compra", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					if (productos.isEmpty()) {
						JOptionPane.showMessageDialog(null, "No hay productos seleccionados");
					}
					else {
						for (Producto i : productos) {
							comercio.getStock().NuevaCompra(i.getCodigo(), i.getCant_stock());
						}
						JOptionPane.showMessageDialog(null, "Compra registrada correctamente");
						
						comercio.getStock().guardarStockBD();
						
						pcbProductos.removeAllItems();
						
						if (!comercio.getStock().faltaStock() && (label != null)) {
							label.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/imagenes/accept1.png")));
						}
						for (Producto i: prodstock) {
							pcbProductos.addItem(i.toString() + " x " + i.getCant_stock());
						}
						DecimalFormat formateador = new DecimalFormat("###,###.##");
						textfield.setText(formateador.format(comercio.getStock().getValorTotal()));
						frmRegistrarCompraDe.dispose();
					}
				}
			}
		});
		btnRegistrarCompra.setBounds(97, 347, 242, 47);
		frmRegistrarCompraDe.getContentPane().add(btnRegistrarCompra);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(34, 334, 351, 2);
		frmRegistrarCompraDe.getContentPane().add(separator_1);
		
		JLabel lblNewLabel = new JLabel("Sumar mercader\u00EDa al stock");
		lblNewLabel.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 15));
		lblNewLabel.setBounds(23, 11, 272, 23);
		frmRegistrarCompraDe.getContentPane().add(lblNewLabel);
		
		for (Producto i: prodstock) {
			cbProductos.addItem(i.toString());
		}
		
		
		btnAgregarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Producto prod = new Producto();
				for (Producto i : prodstock) {
					if (i.toString().equals( (String)cbProductos.getSelectedItem())) {
						prod.setCodigo(i.getCodigo());
						prod.setNombre(i.getNombre());
						prod.setCant_stock((int) spinner.getValue());;
					}
				}
				productos.add(prod);
				cbProdCompra.addItem(prod.toString() + " x " + prod.getCant_stock());
			}
		});
	}
}
