
package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import modelo.Cliente;
import modelo.Comercio;
import modelo.Gasto;
import modelo.Vendedor;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class VistaAgregarVendedor {

	private JFrame frmRegistrarNuevoGasto;
	private JTextField tfNombre;
	private Vendedor vendedor;


	/**
	 * Create the application.
	 */
	public VistaAgregarVendedor(Comercio comercio, JComboBox<String> combobox) {
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
		
		vendedor = new Vendedor();
		frmRegistrarNuevoGasto = new JFrame();
		frmRegistrarNuevoGasto.setResizable(false);
		frmRegistrarNuevoGasto.setTitle("Registrar nuevo vendedor");
		frmRegistrarNuevoGasto.setBounds(100, 100, 356, 178);
		frmRegistrarNuevoGasto.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmRegistrarNuevoGasto.getContentPane().setLayout(null);
		
		JLabel lblRegistrarNuevoGasto = new JLabel("Registrar nuevo vendedor");
		lblRegistrarNuevoGasto.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblRegistrarNuevoGasto.setBounds(10, 11, 190, 20);
		frmRegistrarNuevoGasto.getContentPane().add(lblRegistrarNuevoGasto);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(133, 44, 182, 27);
		frmRegistrarNuevoGasto.getContentPane().add(tfNombre);
		tfNombre.setColumns(10);
		
		JLabel lblDescripcin = new JLabel("Nombre:");
		lblDescripcin.setBounds(10, 47, 100, 14);
		frmRegistrarNuevoGasto.getContentPane().add(lblDescripcin);
		
		JButton btnNewButton = new JButton("Agregar vendedor");
		btnNewButton.setFont(new Font("Nirmala UI", Font.PLAIN, 14));
		btnNewButton.setIcon(new ImageIcon(VistaAgregarVendedor.class.getResource("/imagenes/button_ok.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "¿Está seguro/a que desea registrar el cliente?", "Agregar cliente", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					boolean flag = false;
					if (tfNombre.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Debe ingresar un nombre para el vendedor.");
						flag = true;
					}
					if (!flag) {
						vendedor.setNombre(tfNombre.getText());
						
						comercio.getListaVendedores().insertarVendedorBD(vendedor);
						comercio.getListaVendedores().cargarVendedoresDesdeBD();
						
						if (combobox != null) {
							combobox.removeAllItems();
							ArrayList<Vendedor> vendedores = comercio.getListaVendedores().getVendedores();
							for (Vendedor i : vendedores) {
								combobox.addItem(i.toString());
							}
						}
						JOptionPane.showMessageDialog(null, "Se ha registrado el vendedor " + vendedor.getNombre() + " correctamente");
						frmRegistrarNuevoGasto.dispose();
						}
					}
				}
		});
		btnNewButton.setBounds(52, 88, 231, 41);
		frmRegistrarNuevoGasto.getContentPane().add(btnNewButton);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(42, 75, 259, 2);
		frmRegistrarNuevoGasto.getContentPane().add(separator);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(VistaAgregarVendedor.class.getResource("/imagenes/preferences_contact_list.png")));
		lblNewLabel.setBounds(203, 6, 40, 32);
		frmRegistrarNuevoGasto.getContentPane().add(lblNewLabel);
		frmRegistrarNuevoGasto.setVisible(true);
		
	}
}
