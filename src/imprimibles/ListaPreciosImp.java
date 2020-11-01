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

import modelo.Categoria;
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

public class ListaPreciosImp extends JFrame implements Printable{
	private ArrayList<Categoria> categorias;
	private ArrayList<Producto> productos;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
	private JTable table_4;
	private JTable table_5;

	/**
	 * Create the frame.
	 * @param <T>
	 */
	public ListaPreciosImp(Comercio comercio) {
		getContentPane().setBackground(Color.WHITE);
		setTitle("Imprimir lista de precios");
		
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
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"C\u00F3digo", "Descripci\u00F3n", "Precio", "Categor\u00EDa"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setPreferredWidth(270);
		table.getColumnModel().getColumn(2).setPreferredWidth(15);
		table.getColumnModel().getColumn(3).setPreferredWidth(60);
		table.setBounds(23, 163, 555, 0);
		getContentPane().add(table);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Remito.class.getResource("/imagenes/logo2.png")));
		lblNewLabel_1.setBounds(66, 11, 95, 94);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("MADISON DISTRIBUCIONES");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(218, 56, 214, 22);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Tel\u00E9fono: 2346-596435");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1_1.setBounds(39, 110, 169, 22);
		getContentPane().add(lblNewLabel_2_1_1);
		
		JLabel lFecha = new JLabel("LISTA DE PRECIOS");
		lFecha.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lFecha.setBounds(218, 89, 230, 22);
		getContentPane().add(lFecha);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.LIGHT_GRAY);
		separator.setBounds(218, 77, 206, 9);
		getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBackground(Color.LIGHT_GRAY);
		separator_1.setBounds(218, 110, 205, 9);
		getContentPane().add(separator_1);
		
		comercio.getListaDePrecios().cargarProductosDesdeBD_orderbyCat();
		comercio.getListaDePrecios().cargarCategoriasDesdeBD();

		productos = comercio.getListaDePrecios().getProductos();
		categorias = comercio.getListaDePrecios().getCategorias();
		DecimalFormat formateador = new DecimalFormat("###,###.##");
		Date fechaactual = new Date();
		lFecha.setText("LISTA DE PRECIOS " + (fechaactual.getMonth()+1) + "/"+ (fechaactual.getYear()+1900));
		
		JLabel lblNewLabel = new JLabel("C\u00F3digo");
		lblNewLabel.setBounds(36, 149, 46, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n");
		lblDescripcin.setBounds(187, 149, 75, 14);
		getContentPane().add(lblDescripcin);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(423, 149, 75, 14);
		getContentPane().add(lblPrecio);
		
		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setBounds(499, 149, 75, 14);
		getContentPane().add(lblCategora);
		
		
		int contador = 0;
		
		for (Producto j : productos) {
			if (contador > 34) {
				break;
			}
			table.setValueAt(j.getCodigo(), contador, 0);
			table.setValueAt(j.getNombre(), contador, 1);
			table.setValueAt(formateador.format(j.getPrecio_venta()), contador, 2);
			table.setValueAt(comercio.getListaDePrecios().getNombreCategoria_byId(j.getCategoria()), contador, 3);
			table.setBounds(table.getX(), table.getY(), table.getWidth(), table.getHeight()+16);
			this.setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight()+16);
			contador++;
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
