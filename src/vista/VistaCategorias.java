package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;

import modelo.Categoria;
import modelo.Comercio;
import modelo.Gasto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class VistaCategorias {

	private JFrame frmHistorialDeGastos;
	private JTextField tfID;
	private ArrayList<Categoria> categorias;


	/**
	 * Create the application.
	 */
	public VistaCategorias(Comercio comercio, JComboBox<String> combobox) {
		initialize(comercio, combobox);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Comercio comercio, JComboBox<String> combobox) {
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
		
		frmHistorialDeGastos = new JFrame();
		frmHistorialDeGastos.setResizable(false);
		frmHistorialDeGastos.setTitle("Categor\u00EDas");
		frmHistorialDeGastos.setBounds(100, 100, 477, 254);
		frmHistorialDeGastos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmHistorialDeGastos.getContentPane().setLayout(null);
		
		JLabel lblHistorialDeGastos = new JLabel("Categor\u00EDas");
		lblHistorialDeGastos.setFont(new Font("Microsoft JhengHei", Font.BOLD, 17));
		lblHistorialDeGastos.setBounds(20, 10, 129, 30);
		frmHistorialDeGastos.getContentPane().add(lblHistorialDeGastos);
		
		comercio.getListaDePrecios().cargarCategoriasDesdeBD();
		categorias = comercio.getListaDePrecios().getCategorias();
		DecimalFormat formateador = new DecimalFormat("###,###.##");
		
		JTextField tfNombre = new JTextField();
		tfNombre.setEditable(false);
		tfNombre.setBounds(166, 89, 189, 27);
		frmHistorialDeGastos.getContentPane().add(tfNombre);
		tfNombre.setColumns(10);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(20, 56, 429, 27);
		frmHistorialDeGastos.getContentPane().add(comboBox);
		
		JLabel lblDescripcin = new JLabel("Nombre");
		lblDescripcin.setBounds(20, 92, 137, 14);
		frmHistorialDeGastos.getContentPane().add(lblDescripcin);
		
		JLabel lblMonto = new JLabel("ID:");
		lblMonto.setBounds(20, 123, 137, 14);
		frmHistorialDeGastos.getContentPane().add(lblMonto);
		
		tfID = new JTextField();
		tfID.setEditable(false);
		tfID.setColumns(10);
		tfID.setBounds(166, 120, 114, 27);
		frmHistorialDeGastos.getContentPane().add(tfID);
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Categoria i : categorias) {
					if (comboBox.getSelectedItem() != null) {
						if (comboBox.getSelectedItem().equals(i.toString())) {
							tfNombre.setText(i.getNombre());
							tfID.setText(String.valueOf(i.getId()));
							break;
						}
					}		
				}
			}
		});
		
		JSeparator separator = new JSeparator();
		separator.setBounds(52, 151, 363, 2);
		frmHistorialDeGastos.getContentPane().add(separator);
		
		JButton btnNewButton = new JButton("Eliminar categor\u00EDa");
		btnNewButton.setIcon(new ImageIcon(VistaCategorias.class.getResource("/imagenes/error.png")));
		btnNewButton.setHorizontalTextPosition(SwingConstants.LEFT);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "¿Está seguro/a que desea eliminar la categoría?", "Eliminar categoría", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					for (Categoria i : categorias) {
						if (i.toString().equals(comboBox.getSelectedItem())){
							comercio.getListaDePrecios().eliminarCategoriarBD(i.getId());
							if (comboBox != null) {
								comercio.getListaDePrecios().cargarCategoriasDesdeBD();
								categorias = comercio.getListaDePrecios().getCategorias();
								comboBox.removeAllItems();
								for (Categoria j : categorias) {
									comboBox.addItem(j.toString());
								}
							}
						}
					}
				}
			}
		});
		btnNewButton.setBounds(20, 164, 196, 40);
		frmHistorialDeGastos.getContentPane().add(btnNewButton);
		
		JButton btnNuevoGasto = new JButton("Agregar categor\u00EDa");
		btnNuevoGasto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						new VistaAgregarCategoria(comercio, comboBox);
					}
					
				});
			}
		});
		btnNuevoGasto.setIcon(new ImageIcon(VistaCategorias.class.getResource("/imagenes/new_doc.png")));
		btnNuevoGasto.setHorizontalTextPosition(SwingConstants.LEFT);
		btnNuevoGasto.setBounds(255, 164, 196, 40);
		frmHistorialDeGastos.getContentPane().add(btnNuevoGasto);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(VistaCategorias.class.getResource("/imagenes/menu_2.png")));
		lblNewLabel.setBounds(115, 10, 40, 35);
		frmHistorialDeGastos.getContentPane().add(lblNewLabel);
		frmHistorialDeGastos.setVisible(true);
		for (Categoria i : categorias) {
			comboBox.addItem(i.toString());
		}
	}
}
