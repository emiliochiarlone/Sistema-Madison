package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import modelo.Comercio;
import modelo.Producto;

import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;

public class VistaStockDisponible {

	private JFrame frame;
	private JTextField tfValorStock;
	private ArrayList<Producto> productos;

	/**
	 * Create the application.
	 * @param bevande 
	 */
	public VistaStockDisponible(Comercio comercio, JButton label) {
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
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 529, 178);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Gestión de stock");
		
		JLabel lblStockDisponible = new JLabel("Stock disponible:");
		lblStockDisponible.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblStockDisponible.setBounds(10, 11, 134, 22);
		frame.getContentPane().add(lblStockDisponible);
		
		JComboBox<String> cbProductos = new JComboBox<String>();
		cbProductos.setBounds(154, 9, 349, 24);
		frame.getContentPane().add(cbProductos);
		
		comercio.getStock().cargarStockDesdeBD();
		productos = comercio.getStock().getProductos();
		
		JButton btnNewButton = new JButton("Registrar compra de mercader\u00EDa");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
			         @Override
			         public void run() {
			            new VistaRegistrarCompraStock(comercio, cbProductos, label, tfValorStock ); 
			         }
			      });
			}
		});
		btnNewButton.setBounds(269, 87, 232, 49);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblValorDeStock = new JLabel("Valor de Stock ($):");
		lblValorDeStock.setBounds(10, 57, 110, 14);
		frame.getContentPane().add(lblValorDeStock);
		
		tfValorStock = new JTextField();
		tfValorStock.setEditable(false);
		tfValorStock.setBounds(154, 50, 96, 27);
		frame.getContentPane().add(tfValorStock);
		tfValorStock.setColumns(10);
		DecimalFormat formateador = new DecimalFormat("###,###.##");
		tfValorStock.setText((formateador.format(comercio.getStock().getValorTotal())));
		
		JButton btnVerFaltasDe = new JButton("Ver faltas de stock");
		btnVerFaltasDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
			         @Override
			         public void run() {
			            new VistaFaltasStock(comercio); 
			         }
			      });
			}
		});
		btnVerFaltasDe.setBounds(10, 87, 232, 49);
		frame.getContentPane().add(btnVerFaltasDe);
		
		for (Producto i: productos) {
			cbProductos.addItem(i.toString() + " x " + String.valueOf(i.getCant_stock()));
		}
		frame.setVisible(true);
	}
}
