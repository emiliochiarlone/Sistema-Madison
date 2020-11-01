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
import java.util.Collection;
import java.util.Collections;
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

public class HojaDeRutaImp_onlyTable extends JFrame implements Printable{
	private ArrayList<Cliente> clientes;
	private JTable table;
	private JTextField tfTotal;

	/**
	 * Create the frame.
	 * @param <T>
	 */
	public HojaDeRutaImp_onlyTable(Comercio comercio, ArrayList<Remito> remitos) {
		getContentPane().setBackground(Color.WHITE);
		setTitle("Imprimir hoja de ruta");
		
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
		
		setBounds(100, 100, 595, 129);
		getContentPane().setLayout(null);
		
		table = new JTable();
		table.setFont(new Font("Arial", Font.PLAIN, 12));
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
			},
			new String[] {
				"Direcci\u00F3n", "Cliente", "Importe", "Anterior"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(0).setMinWidth(200);
		table.getColumnModel().getColumn(0).setMaxWidth(2147483636);
		table.getColumnModel().getColumn(1).setPreferredWidth(170);
		table.getColumnModel().getColumn(1).setMinWidth(170);
		table.getColumnModel().getColumn(2).setPreferredWidth(40);
		table.getColumnModel().getColumn(2).setMinWidth(40);
		table.getColumnModel().getColumn(3).setPreferredWidth(40);
		table.getColumnModel().getColumn(3).setMinWidth(40);
		table.setBounds(10, 58, 559, 0);
		getContentPane().add(table);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lFecha = new JLabel("");
		lFecha.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lFecha.setBounds(176, 0, 257, 28);
		getContentPane().add(lFecha);
		
		JLabel lTotal = new JLabel("TOTAL($):");
		lTotal.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lTotal.setBounds(164, 58, 95, 24);
		getContentPane().add(lTotal);
		
		tfTotal = new JTextField();
		tfTotal.setColumns(10);
		tfTotal.setBounds(258, 58, 134, 28);
		getContentPane().add(tfTotal);
		
		comercio.getListaClientes().cargarClientesDesdeBD_orderByDireccion();
		
		
		clientes = comercio.getListaClientes().getClientes();
		DecimalFormat formateador = new DecimalFormat("###,###.##");
		lFecha.setText("HOJA DE RUTA "  + new Date().getDate() + "/" + (new Date().getMonth()+1) + "/" + (new Date().getYear()+1900));
		
		
		JLabel lblNewLabel_4 = new JLabel("Direcci\u00F3n");
		lblNewLabel_4.setBounds(84, 39, 55, 16);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("Cliente");
		lblNewLabel_4_1.setBounds(319, 39, 109, 16);
		getContentPane().add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_4_1_1 = new JLabel("Importe");
		lblNewLabel_4_1_1.setBounds(448, 39, 75, 16);
		getContentPane().add(lblNewLabel_4_1_1);
		
		JLabel lblNewLabel_4_1_1_1_1_1 = new JLabel("Anterior");
		lblNewLabel_4_1_1_1_1_1.setBounds(509, 39, 70, 16);
		getContentPane().add(lblNewLabel_4_1_1_1_1_1);
		
		int contador = 0;
		if (remitos != null) {
			if (remitos.size()>31) {
				lTotal.setVisible(false);
				tfTotal.setVisible(false);
			}
			for (Cliente i: clientes) {
				for (Remito j : remitos) {
					if (i.getId() == j.getId_cliente()) {
						table.setValueAt(i.getDireccion(),contador, 0);
						table.setValueAt(i.getNombre(),contador, 1);
						table.setValueAt(j.getTotal(),contador, 2);
						table.setBounds(table.getX(), table.getY(), table.getWidth(), table.getHeight()+16);
						this.setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight()+16);
						lTotal.setBounds(lTotal.getX(), lTotal.getY()+16, lTotal.getWidth(), lTotal.getHeight());
						tfTotal.setBounds(tfTotal.getX(), tfTotal.getY()+16, tfTotal.getWidth(), tfTotal.getHeight());
						
						contador++;
					}
				}
			}
		}
		
		if (lTotal.isVisible()) {
			comercio.getArchivoRemitos().cargarRemitos_porFecha(new Date());
			tfTotal.setText(formateador.format(comercio.getArchivoRemitos().getDineroTotal()));
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
