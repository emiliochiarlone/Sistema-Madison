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

public class ListaClientesImp_onlyTable extends JFrame implements Printable{

	private JTable table;

	/**
	 * Create the frame.
	 * @param <T>
	 */
	public ListaClientesImp_onlyTable(Comercio comercio, ArrayList<Cliente> clientes) {
		getContentPane().setBackground(Color.WHITE);
		setTitle("Lista de clientes");
		
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
		
		setBounds(100, 100, 595, 106);
		getContentPane().setLayout(null);
		
		table = new JTable();
		table.setFont(new Font("Arial", Font.PLAIN, 11));
		table.setShowVerticalLines(true);
		table.setShowHorizontalLines(true);
		table.setFillsViewportHeight(true);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"Nombre", "Comercio", "Direcci\u00F3n", "Cuit", "Tel\u00E9fono"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(83);
		table.getColumnModel().getColumn(1).setPreferredWidth(89);
		table.getColumnModel().getColumn(2).setPreferredWidth(82);
		table.getColumnModel().getColumn(3).setPreferredWidth(51);
		table.getColumnModel().getColumn(4).setPreferredWidth(50);
		table.setBounds(10, 58, 547, 0);
		getContentPane().add(table);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Fecha:");
		lblNewLabel_3_1_1.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblNewLabel_3_1_1.setBounds(202, 6, 64, 28);
		getContentPane().add(lblNewLabel_3_1_1);
		
		JLabel lFecha = new JLabel("");
		lFecha.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lFecha.setBounds(265, 6, 141, 28);
		getContentPane().add(lFecha);
		
		lFecha.setText((new Date().getMonth()+1) + "/" + (new Date().getYear()+1900));
		
		JLabel lblNewLabel_1_1 = new JLabel("Nombre");
		lblNewLabel_1_1.setBounds(31, 43, 46, 14);
		getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Comercio");
		lblNewLabel_1_1_1.setBounds(164, 43, 64, 14);
		getContentPane().add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Direcci\u00F3n");
		lblNewLabel_1_1_1_1.setBounds(294, 43, 56, 14);
		getContentPane().add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("CUIT");
		lblNewLabel_1_1_1_1_1.setBounds(415, 43, 56, 14);
		getContentPane().add(lblNewLabel_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Tel\u00E9fono");
		lblNewLabel_1_1_1_1_1_1.setBounds(501, 42, 56, 14);
		getContentPane().add(lblNewLabel_1_1_1_1_1_1);
		
		int contador = 0;
		for (Cliente i: clientes) {
			table.setValueAt(i.getNombre(),contador, 0);
			table.setValueAt(i.getNombre_local(),contador, 1);
			table.setValueAt(i.getDireccion(),contador, 2);
			table.setValueAt(i.getCuit(),contador, 3);
			table.setValueAt(i.getTelefono(),contador, 4);
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
