
package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import modelo.Categoria;
import modelo.Comercio;
import modelo.Gasto;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.util.Calendar;

public class VistaAgregarCategoria {

	private JFrame frmRegistrarNuevoGasto;
	private JTextField tfNombre;


	/**
	 * Create the application.
	 */
	public VistaAgregarCategoria(Comercio comercio, JComboBox<String> combobox) {
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
		
		Categoria categoria = new Categoria();
		frmRegistrarNuevoGasto = new JFrame();
		frmRegistrarNuevoGasto.setResizable(false);
		frmRegistrarNuevoGasto.setTitle("Registrar nueva categor\u00EDa");
		frmRegistrarNuevoGasto.setBounds(100, 100, 356, 184);
		frmRegistrarNuevoGasto.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmRegistrarNuevoGasto.getContentPane().setLayout(null);
		
		JLabel lblRegistrarNuevoGasto = new JLabel("Registrar nueva categor\u00EDa");
		lblRegistrarNuevoGasto.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblRegistrarNuevoGasto.setBounds(10, 11, 207, 20);
		frmRegistrarNuevoGasto.getContentPane().add(lblRegistrarNuevoGasto);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(133, 44, 182, 27);
		frmRegistrarNuevoGasto.getContentPane().add(tfNombre);
		tfNombre.setColumns(10);
		
		JLabel lblDescripcin = new JLabel("Nombre:");
		lblDescripcin.setBounds(10, 51, 100, 14);
		frmRegistrarNuevoGasto.getContentPane().add(lblDescripcin);
		frmRegistrarNuevoGasto.setVisible(true);
		
		JButton btnNewButton = new JButton("Registrar categoría");
		btnNewButton.setIcon(new ImageIcon(VistaAgregarCategoria.class.getResource("/imagenes/button_ok.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "¿Está seguro/a que desea registrar esta categoría?", "Confirmar categoría", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					if (tfNombre.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Debe ingresar un nombre");
					}
					else {
							categoria.setNombre(tfNombre.getText());
							comercio.getListaDePrecios().agregarCategoriaBD(categoria);
							comercio.getListaDePrecios().cargarCategoriasDesdeBD();
							if (combobox != null) {
								combobox.removeAllItems();
								ArrayList<Categoria> categorias = comercio.getListaDePrecios().getCategorias();
								for (Categoria i : categorias) {
									combobox.addItem(i.toString());
								}
							}
							JOptionPane.showMessageDialog(null, "Se ha registrado la categoria correctamente");
							
							frmRegistrarNuevoGasto.dispose();
						}
				}
			}
		});
		btnNewButton.setBounds(71, 100, 201, 42);
		frmRegistrarNuevoGasto.getContentPane().add(btnNewButton);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(41, 80, 259, 2);
		frmRegistrarNuevoGasto.getContentPane().add(separator);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(VistaAgregarCategoria.class.getResource("/imagenes/menu_2.png")));
		lblNewLabel.setBounds(184, -3, 46, 50);
		frmRegistrarNuevoGasto.getContentPane().add(lblNewLabel);
		
		
	}
}
