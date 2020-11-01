package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import modelo.Comercio;
import modelo.Producto;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.ImageIcon;

public class VistaFaltasStock {

	private JFrame frmFaltasDeStock;
	private ArrayList<Producto> productos;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public VistaFaltasStock(Comercio comercio) {
		initialize(comercio);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Comercio comercio) {
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
		
		frmFaltasDeStock = new JFrame();
		frmFaltasDeStock.setResizable(false);
		frmFaltasDeStock.setTitle("Faltas de stock");
		frmFaltasDeStock.setBounds(100, 100, 439, 183);
		frmFaltasDeStock.setVisible(true);
		frmFaltasDeStock.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmFaltasDeStock.getContentPane().setLayout(null);
		
		JLabel lblLosProductosQue = new JLabel("Los productos que se encuentran en falta son:");
		lblLosProductosQue.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
		lblLosProductosQue.setBounds(23, 47, 401, 20);
		frmFaltasDeStock.getContentPane().add(lblLosProductosQue);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(63, 78, 303, 27);
		frmFaltasDeStock.getContentPane().add(comboBox);
		
		JLabel lNoFalta = new JLabel("No hay productos en falta actualmente");
		lNoFalta.setForeground(Color.RED);
		lNoFalta.setBounds(114, 111, 252, 14);
		frmFaltasDeStock.getContentPane().add(lNoFalta);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(VistaFaltasStock.class.getResource("/imagenes/faltastock.png")));
		lblNewLabel.setBounds(188, 11, 43, 39);
		frmFaltasDeStock.getContentPane().add(lblNewLabel);
		lNoFalta.setVisible(false);
		
		comercio.getStock().cargarStockDesdeBD();
		productos = comercio.getStock().calcularFaltas();
		
		if (productos.isEmpty()) {
			lNoFalta.setVisible(true);
		}
		else {
			for (Producto i : productos) {
				comboBox.addItem(i.toString());
			}
		}
	}
}
