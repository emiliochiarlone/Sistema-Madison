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

public class RemitoImp_onlyTable extends JFrame implements Printable{
	private ArrayList<Producto> productos;
	private JTable table;
	private JTextField tfSubtotal;
	private JTextField tfDescuento;
	private JTextField tfTotal;

	/**
	 * Create the frame.
	 * @param <T>
	 */
	public RemitoImp_onlyTable(Comercio comercio, Remito remito, ArrayList<Detalle> detalle_30) {
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
		
		setBounds(100, 100, 595, 222);
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
		table.setBounds(29, 53, 544, 0);
		getContentPane().add(table);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel = new JLabel("Remito N\u00B0");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		lblNewLabel.setBounds(29, 0, 127, 42);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Fecha:");
		lblNewLabel_3_1_1.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblNewLabel_3_1_1.setBounds(250, 8, 64, 28);
		getContentPane().add(lblNewLabel_3_1_1);
		
		JLabel lFecha = new JLabel("");
		lFecha.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lFecha.setBounds(316, 8, 141, 28);
		getContentPane().add(lFecha);
		

		tfSubtotal = new JTextField();
		tfSubtotal.setBounds(277, 53, 134, 28);
		getContentPane().add(tfSubtotal);
		tfSubtotal.setColumns(10);
		
		JLabel lDescuento = new JLabel("DESCUENTO(%):");
		lDescuento.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lDescuento.setBounds(116, 96, 151, 24);
		getContentPane().add(lDescuento);
		
		tfDescuento = new JTextField();
		tfDescuento.setColumns(10);
		tfDescuento.setBounds(277, 92, 69, 28);
		getContentPane().add(tfDescuento);
		
		JLabel lTotal = new JLabel("TOTAL($):");
		lTotal.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lTotal.setBounds(177, 132, 86, 24);
		getContentPane().add(lTotal);
		
		tfTotal = new JTextField();
		tfTotal.setColumns(10);
		tfTotal.setBounds(277, 128, 134, 28);
		getContentPane().add(tfTotal);
		
		JLabel lRemitoNoValido = new JLabel("(Remito no v\u00E1lido como factura)");
		lRemitoNoValido.setFont(new Font("SansSerif", Font.PLAIN, 10));
		lRemitoNoValido.setBounds(30, 156, 169, 16);
		getContentPane().add(lRemitoNoValido);
		
		
		JLabel lNumeroRemito = new JLabel("");
		lNumeroRemito.setFont(new Font("Arial", Font.PLAIN, 24));
		lNumeroRemito.setBounds(152, 5, 127, 32);
		getContentPane().add(lNumeroRemito);
		
		comercio.getListaDePrecios().cargarProductosDesdeBD();
		
		productos = comercio.getListaDePrecios().getProductos();
		DecimalFormat formateador = new DecimalFormat("###,###.##");
		
		lNumeroRemito.setText(String.valueOf(remito.getId()));
		lFecha.setText(remito.dateToString());
		tfSubtotal.setText(String.valueOf(remito.getSubtotal()));
		tfDescuento.setText(String.valueOf(remito.getDescuento()));
		tfTotal.setText(String.valueOf(remito.getTotal()));
		
		
		JLabel lblNewLabel_4 = new JLabel("C\u00F3digo");
		lblNewLabel_4.setBounds(39, 34, 55, 16);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("Descripci\u00F3n");
		lblNewLabel_4_1.setBounds(131, 34, 109, 16);
		getContentPane().add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_4_1_1 = new JLabel("Cant.");
		lblNewLabel_4_1_1.setBounds(372, 34, 33, 16);
		getContentPane().add(lblNewLabel_4_1_1);
		
		JLabel lblNewLabel_4_1_1_1 = new JLabel("%Dto");
		lblNewLabel_4_1_1_1.setBounds(414, 34, 43, 16);
		getContentPane().add(lblNewLabel_4_1_1_1);
		
		JLabel lblNewLabel_4_1_1_1_1 = new JLabel("P/unit");
		lblNewLabel_4_1_1_1_1.setBounds(467, 34, 43, 16);
		getContentPane().add(lblNewLabel_4_1_1_1_1);
		
		JLabel lblNewLabel_4_1_1_1_1_1 = new JLabel("Importe total");
		lblNewLabel_4_1_1_1_1_1.setBounds(503, 34, 70, 16);
		getContentPane().add(lblNewLabel_4_1_1_1_1_1);
		
		JLabel lSubtotal = new JLabel("SUBTOTAL($):");
		lSubtotal.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lSubtotal.setBounds(138, 59, 127, 22);
		getContentPane().add(lSubtotal);
		
		if (detalle_30 != null) {
			int contador = 0;
			for (Detalle i: detalle_30) {
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
