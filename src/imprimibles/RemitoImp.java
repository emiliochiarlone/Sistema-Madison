package imprimibles;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
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
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.mysql.fabric.xmlrpc.base.Array;

import modelo.Cliente;
import modelo.Comercio;
import modelo.Detalle;
import modelo.Producto;
import modelo.Remito;
import modelo.Vendedor;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;

public class RemitoImp extends JFrame implements Printable{
	private ArrayList<Vendedor> vendedores;
	private ArrayList<Cliente> clientes;
	private ArrayList<Detalle> detalle;
	private ArrayList<Producto> productos;
	private JTable table;
	private JTextField tfNombreCliente;
	private JTextField tfVendedor;
	private JTextField tfNumeroCliente;
	private JTextField tfNombreComercio;
	private JTextField tfTelefono;
	private JTextField tfDireccion;
	private JTextField tfSubtotal;
	private JTextField tfDescuento;
	private JTextField tfTotal;

	/**
	 * Create the frame.
	 * @param <T>
	 */
	public RemitoImp(Comercio comercio, Remito remito) {
		getContentPane().setBackground(Color.WHITE);
		setTitle("Imprimir remito");
		
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
		
		setBounds(100, 100, 595, 453);
		getContentPane().setLayout(null);
		
		table = new JTable();
		table.setFont(new Font("Arial", Font.PLAIN, 11));
		table.setShowVerticalLines(true);
		table.setShowHorizontalLines(true);
		table.setFillsViewportHeight(true);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"C\u00F3digo", "Descripci\u00F3n", "Cantidad", "%Dto", "P/Unitario", "Imp. total"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(0).setMaxWidth(2147483636);
		table.getColumnModel().getColumn(1).setPreferredWidth(270);
		table.getColumnModel().getColumn(2).setPreferredWidth(25);
		table.getColumnModel().getColumn(3).setPreferredWidth(30);
		table.getColumnModel().getColumn(4).setPreferredWidth(40);
		table.getColumnModel().getColumn(5).setPreferredWidth(50);
		table.setBounds(16, 273, 557, 0);
		getContentPane().add(table);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel = new JLabel("Remito N\u00B0");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		lblNewLabel.setBounds(35, 24, 127, 42);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Remito.class.getResource("/imagenes/logo2.png")));
		lblNewLabel_1.setBounds(35, 78, 95, 94);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("MADISON DISTRIBUCIONES");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(130, 89, 230, 22);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Lautaro Tolosa");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(138, 123, 230, 22);
		getContentPane().add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Tel\u00E9fono: 2346-596435");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1_1.setBounds(138, 150, 169, 22);
		getContentPane().add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_3 = new JLabel("Vendedor:");
		lblNewLabel_3.setBounds(24, 199, 64, 16);
		getContentPane().add(lblNewLabel_3);
		
		tfNombreCliente = new JTextField();
		tfNombreCliente.setEditable(false);
		tfNombreCliente.setBounds(400, 102, 173, 28);
		getContentPane().add(tfNombreCliente);
		tfNombreCliente.setColumns(10);
		
		JLabel lblNewLabel_3_1 = new JLabel("Cliente:");
		lblNewLabel_3_1.setBounds(358, 108, 55, 16);
		getContentPane().add(lblNewLabel_3_1);
		
		tfVendedor = new JTextField();
		tfVendedor.setColumns(10);
		tfVendedor.setBounds(89, 193, 194, 28);
		getContentPane().add(tfVendedor);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBackground(Color.GRAY);
		separator.setBounds(29, 179, 321, 2);
		getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.LIGHT_GRAY);
		separator_1.setBackground(Color.GRAY);
		separator_1.setBounds(29, 72, 321, 2);
		getContentPane().add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.LIGHT_GRAY);
		separator_2.setBackground(Color.GRAY);
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(349, 73, 4, 108);
		getContentPane().add(separator_2);
		
		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setForeground(Color.LIGHT_GRAY);
		separator_2_1.setBackground(Color.GRAY);
		separator_2_1.setOrientation(SwingConstants.VERTICAL);
		separator_2_1.setBounds(29, 73, 4, 108);
		getContentPane().add(separator_2_1);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Fecha:");
		lblNewLabel_3_1_1.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblNewLabel_3_1_1.setBounds(250, 32, 64, 28);
		getContentPane().add(lblNewLabel_3_1_1);
		
		JLabel lFecha = new JLabel("");
		lFecha.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lFecha.setBounds(310, 32, 141, 28);
		getContentPane().add(lFecha);
		
		JLabel lblNewLabel_3_1_1_1 = new JLabel("Datos del cliente");
		lblNewLabel_3_1_1_1.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblNewLabel_3_1_1_1.setBounds(384, 62, 157, 28);
		getContentPane().add(lblNewLabel_3_1_1_1);
		
		JLabel lblNewLabel_3_1_2 = new JLabel("N\u00FAmero de cliente:");
		lblNewLabel_3_1_2.setBounds(358, 137, 109, 16);
		getContentPane().add(lblNewLabel_3_1_2);
		
		tfNumeroCliente = new JTextField();
		tfNumeroCliente.setEditable(false);
		tfNumeroCliente.setColumns(10);
		tfNumeroCliente.setBounds(465, 131, 48, 28);
		getContentPane().add(tfNumeroCliente);
		
		JLabel lblNewLabel_3_1_2_1 = new JLabel("Comercio:");
		lblNewLabel_3_1_2_1.setBounds(358, 167, 64, 16);
		getContentPane().add(lblNewLabel_3_1_2_1);
		
		tfNombreComercio = new JTextField();
		tfNombreComercio.setEditable(false);
		tfNombreComercio.setColumns(10);
		tfNombreComercio.setBounds(416, 161, 157, 28);
		getContentPane().add(tfNombreComercio);
		
		JLabel lblNewLabel_3_1_2_1_1 = new JLabel("Tel\u00E9fono:");
		lblNewLabel_3_1_2_1_1.setBounds(358, 199, 77, 16);
		getContentPane().add(lblNewLabel_3_1_2_1_1);
		
		tfTelefono = new JTextField();
		tfTelefono.setEditable(false);
		tfTelefono.setColumns(10);
		tfTelefono.setBounds(416, 191, 157, 28);
		getContentPane().add(tfTelefono);
		
		JLabel lblNewLabel_3_1_2_1_1_1 = new JLabel("Direcci\u00F3n:");
		lblNewLabel_3_1_2_1_1_1.setBounds(358, 227, 62, 16);
		getContentPane().add(lblNewLabel_3_1_2_1_1_1);
		

		tfSubtotal = new JTextField();
		tfSubtotal.setBounds(277, 295, 134, 28);
		getContentPane().add(tfSubtotal);
		tfSubtotal.setColumns(10);
		
		JLabel lDescuento = new JLabel("DESCUENTO(%):");
		lDescuento.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lDescuento.setBounds(114, 332, 151, 24);
		getContentPane().add(lDescuento);
		
		tfDescuento = new JTextField();
		tfDescuento.setColumns(10);
		tfDescuento.setBounds(277, 332, 69, 28);
		getContentPane().add(tfDescuento);
		
		JLabel lTotal = new JLabel("TOTAL($):");
		lTotal.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lTotal.setBounds(176, 368, 95, 24);
		getContentPane().add(lTotal);
		
		tfTotal = new JTextField();
		tfTotal.setColumns(10);
		tfTotal.setBounds(277, 368, 134, 28);
		getContentPane().add(tfTotal);
		
		JLabel lRemitoNoValido = new JLabel("(Remito no v\u00E1lido como factura)");
		lRemitoNoValido.setFont(new Font("SansSerif", Font.PLAIN, 10));
		lRemitoNoValido.setBounds(16, 392, 169, 16);
		getContentPane().add(lRemitoNoValido);
		
		tfDireccion = new JTextField();
		tfDireccion.setEditable(false);
		tfDireccion.setColumns(10);
		tfDireccion.setBounds(416, 221, 157, 28);
		getContentPane().add(tfDireccion);
		
		JLabel lblNewLabel_3_1_1_1_1 = new JLabel("Detalle del remito");
		lblNewLabel_3_1_1_1_1.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblNewLabel_3_1_1_1_1.setBounds(24, 227, 157, 28);
		getContentPane().add(lblNewLabel_3_1_1_1_1);
		
		
		JLabel lNumeroRemito = new JLabel("");
		lNumeroRemito.setFont(new Font("Arial", Font.PLAIN, 24));
		lNumeroRemito.setBounds(159, 29, 127, 32);
		getContentPane().add(lNumeroRemito);
		
		comercio.getListaClientes().cargarClientesDesdeBD();
		comercio.getListaVendedores().cargarVendedoresDesdeBD();
		comercio.getListaDePrecios().cargarProductosDesdeBD();
		
		vendedores = comercio.getListaVendedores().getVendedores();
		clientes = comercio.getListaClientes().getClientes();
		productos = comercio.getListaDePrecios().getProductos();
		detalle = new ArrayList<Detalle>();
		if (remito.getListaDetalle().size() > 23) {
			if (remito.getListaDetalle().size()>29) {
				for (int i=0 ; i<30; i++) {
					detalle.add(remito.getListaDetalle().get(i));
				}
			} else {
				for (int i=0 ; i<remito.getListaDetalle().size(); i++) {
					detalle.add(remito.getListaDetalle().get(i));
				}
			}
		} else {
			detalle = remito.getListaDetalle();
		}
		DecimalFormat formateador = new DecimalFormat("###,###.##");
		
		lNumeroRemito.setText(String.valueOf(remito.getId()));
		lFecha.setText(remito.dateToString());
		if (comercio.getListaVendedores().getVendedor_byID(remito.getId_vendedor()) != null) {
			tfVendedor.setText(comercio.getListaVendedores().getVendedor_byID(remito.getId_vendedor()).getNombre());
		}
		if (comercio.getListaClientes().getCliente_byID(remito.getId_cliente()) != null) {
			tfNombreCliente.setText(comercio.getListaClientes().getCliente_byID(remito.getId_cliente()).getNombre());
			tfNombreComercio.setText(comercio.getListaClientes().getCliente_byID(remito.getId_cliente()).getNombre_local());
			tfTelefono.setText(comercio.getListaClientes().getCliente_byID(remito.getId_cliente()).getTelefono());
			tfDireccion.setText(comercio.getListaClientes().getCliente_byID(remito.getId_cliente()).getDireccion());
			tfNumeroCliente.setText(String.valueOf(comercio.getListaClientes().getCliente_byID(remito.getId_cliente()).getId()));
		}
		tfSubtotal.setText(String.valueOf(remito.getSubtotal()));
		tfDescuento.setText(String.valueOf(remito.getDescuento()));
		tfTotal.setText(String.valueOf(remito.getTotal()));
		
		
		JLabel lblNewLabel_4 = new JLabel("C\u00F3digo");
		lblNewLabel_4.setBounds(22, 256, 55, 16);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("Descripci\u00F3n");
		lblNewLabel_4_1.setBounds(130, 256, 109, 16);
		getContentPane().add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_4_1_1 = new JLabel("Cant.");
		lblNewLabel_4_1_1.setBounds(358, 255, 33, 16);
		getContentPane().add(lblNewLabel_4_1_1);
		
		JLabel lblNewLabel_4_1_1_1 = new JLabel("%Dto");
		lblNewLabel_4_1_1_1.setBounds(400, 255, 43, 16);
		getContentPane().add(lblNewLabel_4_1_1_1);
		
		JLabel lblNewLabel_4_1_1_1_1 = new JLabel("P/unit");
		lblNewLabel_4_1_1_1_1.setBounds(455, 256, 43, 16);
		getContentPane().add(lblNewLabel_4_1_1_1_1);
		
		JLabel lblNewLabel_4_1_1_1_1_1 = new JLabel("Importe total");
		lblNewLabel_4_1_1_1_1_1.setBounds(503, 256, 70, 16);
		getContentPane().add(lblNewLabel_4_1_1_1_1_1);
		
		JLabel lSubtotal = new JLabel("SUBTOTAL($):");
		lSubtotal.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lSubtotal.setBounds(138, 296, 127, 22);
		getContentPane().add(lSubtotal);
		
		
		int contador = 0;
		if (detalle.size()<23) {
			for (Detalle i: detalle) {
				table.setValueAt(i.getProducto_codigo(),contador, 0);
				table.setValueAt(comercio.getListaDePrecios().getProducto_byCodigo(i.getProducto_codigo()).getNombre(),contador, 1);
				table.setValueAt(i.getCantidad(),contador, 2);
				table.setValueAt(i.getDescuento(),contador, 3);
				table.setValueAt(i.getPrecio(),contador, 4);
				table.setValueAt(i.getTotal(), contador, 5);
				table.setBounds(table.getX(), table.getY(), table.getWidth(), table.getHeight()+16);
				this.setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight()+16);
				lSubtotal.setBounds(lSubtotal.getX(), lSubtotal.getY()+16, lSubtotal.getWidth(), lSubtotal.getHeight());
				tfSubtotal.setBounds(tfSubtotal.getX(), tfSubtotal.getY()+16, tfSubtotal.getWidth(), tfSubtotal.getHeight());
				lDescuento.setBounds(lDescuento.getX(), lDescuento.getY()+16, lDescuento.getWidth(), lDescuento.getHeight());
				tfDescuento.setBounds(tfDescuento.getX(), tfDescuento.getY()+16, tfDescuento.getWidth(), tfDescuento.getHeight());
				lTotal.setBounds(lTotal.getX(), lTotal.getY()+16, lTotal.getWidth(), lTotal.getHeight());
				tfTotal.setBounds(tfTotal.getX(), tfTotal.getY()+16, tfTotal.getWidth(), tfTotal.getHeight());
				lRemitoNoValido.setBounds(lRemitoNoValido.getX(), lRemitoNoValido.getY()+16, lRemitoNoValido.getWidth(), lRemitoNoValido.getHeight());
				
				contador++;
			}
		} else {
			lSubtotal.setVisible(false);
			tfSubtotal.setVisible(false);
			lDescuento.setVisible(false);
			tfDescuento.setVisible(false);
			lTotal.setVisible(false);
			tfTotal.setVisible(false);
			lRemitoNoValido.setVisible(false);
			
			for (Detalle i : detalle) {
				table.setValueAt(i.getProducto_codigo(),contador, 0);
				table.setValueAt(comercio.getListaDePrecios().getProducto_byCodigo(i.getProducto_codigo()).getNombre(),contador, 1);
				table.setValueAt(i.getCantidad(),contador, 2);
				table.setValueAt(i.getDescuento(),contador, 3);
				table.setValueAt(i.getPrecio(),contador, 4);
				table.setValueAt(i.getTotal(), contador, 5);
				table.setBounds(table.getX(), table.getY(), table.getWidth(), table.getHeight()+16);
				this.setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight()+16);
				contador++;
			}
		}
		
		
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

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		if (pageIndex>0) {
			return NO_SUCH_PAGE;
		}
		Paper paper = new Paper();
		paper.setSize(842, 595);
		pageFormat.setPaper(paper);
		Graphics2D hub = (Graphics2D) graphics;
//		hub.translate(pageFormat.getImageableX() + 20, pageFormat.getImageableY() + 20);
		this.getContentPane().printAll(graphics);
		return PAGE_EXISTS;
	}
}
