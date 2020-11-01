
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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class VistaAgregarCliente {

	private JFrame frmRegistrarNuevoGasto;
	private JTextField tfNombre;
	private JTextField tfDireccion;
	private JTextField tfNombreLocal;
	private JTextField tfTelefono;
	private JTextField tfCUIT;
	private Cliente cliente;


	/**
	 * Create the application.
	 */
	public VistaAgregarCliente(Comercio comercio, JComboBox<String> combobox) {
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
		cliente = new Cliente();
		frmRegistrarNuevoGasto = new JFrame();
		frmRegistrarNuevoGasto.setResizable(false);
		frmRegistrarNuevoGasto.setTitle("Registrar nuevo cliente");
		frmRegistrarNuevoGasto.setBounds(100, 100, 356, 309);
		frmRegistrarNuevoGasto.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmRegistrarNuevoGasto.getContentPane().setLayout(null);
		
		JLabel lblRegistrarNuevoGasto = new JLabel("Registrar nuevo cliente");
		lblRegistrarNuevoGasto.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblRegistrarNuevoGasto.setBounds(10, 11, 172, 20);
		frmRegistrarNuevoGasto.getContentPane().add(lblRegistrarNuevoGasto);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(133, 44, 182, 27);
		frmRegistrarNuevoGasto.getContentPane().add(tfNombre);
		tfNombre.setColumns(10);
		
		JLabel lblDescripcin = new JLabel("Nombre:");
		lblDescripcin.setBounds(10, 47, 100, 14);
		frmRegistrarNuevoGasto.getContentPane().add(lblDescripcin);
		
		JLabel lblMonto = new JLabel("Direcci\u00F3n:");
		lblMonto.setBounds(10, 77, 100, 14);
		frmRegistrarNuevoGasto.getContentPane().add(lblMonto);
		
		tfDireccion = new JTextField();
		tfDireccion.setColumns(10);
		tfDireccion.setBounds(133, 74, 182, 27);
		frmRegistrarNuevoGasto.getContentPane().add(tfDireccion);
		
		JButton btnNewButton = new JButton("Agregar cliente");
		btnNewButton.setFont(new Font("Nirmala UI", Font.PLAIN, 14));
		btnNewButton.setIcon(new ImageIcon(VistaAgregarCliente.class.getResource("/imagenes/button_ok.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "¿Está seguro/a que desea registrar el cliente?", "Agregar cliente", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					String sMonto = tfTelefono.getText();
					boolean flag = false;
					for (int i = 0; i< sMonto.length(); i++) {
						if (sMonto.charAt(i)<48 || sMonto.charAt(i)>57) {
							JOptionPane.showMessageDialog(null, "Ingrese sólo números en el campo de teléfono.");
							flag = true;
							i = sMonto.length();
						}
					}
					if (!flag) {
						cliente.setNombre(tfNombre.getText());
						cliente.setDireccion(tfDireccion.getText());
						cliente.setNombre_local(tfNombreLocal.getText());
						cliente.setCuit(tfCUIT.getText());
						cliente.setTelefono(tfTelefono.getText());
						cliente.setFecha_incorp(new Date());
						
						comercio.getListaClientes().insertarClienteoBD(cliente);
						comercio.getListaClientes().cargarClientesDesdeBD();
						
						if (combobox != null) {
							combobox.removeAllItems();
							ArrayList<Cliente> clientes = comercio.getListaClientes().getClientes();
							for (Cliente i : clientes) {
								combobox.addItem(i.toString());
							}
						}
						JOptionPane.showMessageDialog(null, "Se ha registrado el nuevo cliente correctamente");
						frmRegistrarNuevoGasto.dispose();
						}
					}
				}
		});
		btnNewButton.setBounds(43, 218, 231, 41);
		frmRegistrarNuevoGasto.getContentPane().add(btnNewButton);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(33, 205, 259, 2);
		frmRegistrarNuevoGasto.getContentPane().add(separator);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(VistaAgregarCliente.class.getResource("/imagenes/customers.png")));
		lblNewLabel.setBounds(178, 7, 32, 32);
		frmRegistrarNuevoGasto.getContentPane().add(lblNewLabel);
		
		JLabel lblNombreDelLocal = new JLabel("Nombre del local:");
		lblNombreDelLocal.setBounds(10, 108, 113, 14);
		frmRegistrarNuevoGasto.getContentPane().add(lblNombreDelLocal);
		
		tfNombreLocal = new JTextField();
		tfNombreLocal.setColumns(10);
		tfNombreLocal.setBounds(133, 105, 182, 27);
		frmRegistrarNuevoGasto.getContentPane().add(tfNombreLocal);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono:");
		lblTelfono.setBounds(10, 139, 113, 14);
		frmRegistrarNuevoGasto.getContentPane().add(lblTelfono);
		
		tfTelefono = new JTextField();
		tfTelefono.setColumns(10);
		tfTelefono.setBounds(133, 136, 182, 27);
		frmRegistrarNuevoGasto.getContentPane().add(tfTelefono);
		
		JLabel lblCuit = new JLabel("CUIT:");
		lblCuit.setBounds(10, 170, 113, 14);
		frmRegistrarNuevoGasto.getContentPane().add(lblCuit);
		
		tfCUIT = new JTextField();
		tfCUIT.setColumns(10);
		tfCUIT.setBounds(133, 167, 182, 27);
		frmRegistrarNuevoGasto.getContentPane().add(tfCUIT);
		frmRegistrarNuevoGasto.setVisible(true);
		
	}
}
