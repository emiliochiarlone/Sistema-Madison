package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;

import modelo.Cliente;
import modelo.Comercio;
import modelo.Remito;
import modelo.Gasto;
import modelo.Producto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import imprimibles.ListaClientesImp;
import imprimibles.ListaClientesImp_onlyTable;
import imprimibles.ListaPreciosImp;
import imprimibles.ListaPreciosImp_onlyTable;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import java.awt.Color;

public class VistaListaClientes {

	private JFrame frmHistorialDeGastos;
	private JTextField tfNombre;
	private JTextField tfID;
	private JTextField tfDireccion;
	private ArrayList<Cliente> clientes;
	private JTextField tfNombre_local;
	private JTextField tfTelefono;
	private JTextField tfCuit;
	private JTextField tfFecha_incorp;
	private JTextField tfCantidadFacturas;
	private JTextField tfTotalFacturas;
	private ArrayList<Remito> remitos;
	private Cliente cliente;


	/**
	 * Create the application.
	 */
	public VistaListaClientes(Comercio comercio) {
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
		
		frmHistorialDeGastos = new JFrame();
		frmHistorialDeGastos.setResizable(false);
		frmHistorialDeGastos.setTitle("Lista de clientes");
		frmHistorialDeGastos.setBounds(100, 100, 469, 621);
		frmHistorialDeGastos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmHistorialDeGastos.getContentPane().setLayout(null);
		
		JLabel lblHistorialDeGastos = new JLabel("Lista de clientes");
		lblHistorialDeGastos.setFont(new Font("Microsoft JhengHei", Font.BOLD, 17));
		lblHistorialDeGastos.setBounds(76, 10, 187, 30);
		frmHistorialDeGastos.getContentPane().add(lblHistorialDeGastos);
		
		comercio.getListaClientes().cargarClientesDesdeBD();
		clientes = comercio.getListaClientes().getClientes();
		DecimalFormat formateador = new DecimalFormat("###,###.##");
		
		tfNombre = new JTextField();
		tfNombre.setEditable(false);
		tfNombre.setBounds(166, 120, 279, 27);
		frmHistorialDeGastos.getContentPane().add(tfNombre);
		tfNombre.setColumns(10);
		
		JComboBox<String> cbClientes = new JComboBox<String>();
		cbClientes.setBounds(20, 56, 355, 23);
		frmHistorialDeGastos.getContentPane().add(cbClientes);
		
		JLabel lblDescripcin = new JLabel("Nombre:");
		lblDescripcin.setBounds(20, 127, 137, 14);
		frmHistorialDeGastos.getContentPane().add(lblDescripcin);
		
		JLabel lblMonto = new JLabel("ID:");
		lblMonto.setBounds(20, 95, 137, 14);
		frmHistorialDeGastos.getContentPane().add(lblMonto);
		
		tfID = new JTextField();
		tfID.setEditable(false);
		tfID.setColumns(10);
		tfID.setBounds(166, 89, 51, 27);
		frmHistorialDeGastos.getContentPane().add(tfID);
		
		JLabel lblFecha = new JLabel("Direcci\u00F3n:");
		lblFecha.setBounds(20, 164, 137, 14);
		frmHistorialDeGastos.getContentPane().add(lblFecha);
		
		JLabel lCambios = new JLabel("Cambios guardados.");
		lCambios.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lCambios.setHorizontalAlignment(SwingConstants.LEFT);
		lCambios.setForeground(Color.GREEN);
		lCambios.setBackground(Color.GREEN);
		lCambios.setBounds(323, 430, 138, 28);
		frmHistorialDeGastos.getContentPane().add(lCambios);
		lCambios.setVisible(false);
		
		tfDireccion = new JTextField();
		tfDireccion.setEditable(false);
		tfDireccion.setColumns(10);
		tfDireccion.setBounds(166, 157, 279, 27);
		frmHistorialDeGastos.getContentPane().add(tfDireccion);
		
		cbClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Cliente i : clientes) {
					if (cbClientes.getSelectedItem() != null) {
						if (cbClientes.getSelectedItem().equals(i.toString())) {
							cliente = i;
							tfNombre.setText(i.getNombre());
							tfID.setText(String.valueOf(i.getId()));
							tfDireccion.setText(i.getDireccion());
							tfNombre_local.setText(i.getNombre_local());
							tfCuit.setText(i.getCuit());
							tfFecha_incorp.setText(i.dateToString());
							tfTelefono.setText(i.getTelefono());
							
							comercio.getArchivoRemitos().cargarRemitos_ClienteBD(i.getId());
							remitos = comercio.getArchivoRemitos().getRemitos();
							tfCantidadFacturas.setText(String.valueOf(remitos.size()));
							tfTotalFacturas.setText(formateador.format(comercio.getArchivoRemitos().getDineroTotal()));
							break;
						}
					}		
				}
			}
		});
		
		JSeparator separator = new JSeparator();
		separator.setBounds(30, 470, 396, 2);
		frmHistorialDeGastos.getContentPane().add(separator);
		
		JButton btnNewButton = new JButton("Eliminar cliente");
		btnNewButton.setIcon(new ImageIcon(VistaListaClientes.class.getResource("/imagenes/error.png")));
		btnNewButton.setHorizontalTextPosition(SwingConstants.LEFT);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "¿Está seguro/a que desea eliminar el cliente?", "Eliminar cliente", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					comercio.getListaClientes().eliminarClienteBD(Integer.valueOf(tfID.getText()));
					comercio.getListaClientes().cargarClientesDesdeBD();
					clientes = comercio.getListaClientes().getClientes();
					JOptionPane.showMessageDialog(null, "Cliente " + tfID.getText() + " eliminado.");
					cbClientes.removeAllItems();
					for (Cliente i : clientes) {
						cbClientes.addItem(i.toString());
					}
				}
			}
		});
		btnNewButton.setBounds(6, 536, 197, 40);
		frmHistorialDeGastos.getContentPane().add(btnNewButton);
		
		JButton btnNuevoGasto = new JButton("Nuevo cliente");
		btnNuevoGasto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						new VistaAgregarCliente(comercio, cbClientes);
					}
					
				});
			}
		});
		btnNuevoGasto.setIcon(new ImageIcon(VistaListaClientes.class.getResource("/imagenes/new_doc.png")));
		btnNuevoGasto.setHorizontalTextPosition(SwingConstants.LEFT);
		btnNuevoGasto.setBounds(248, 536, 197, 40);
		frmHistorialDeGastos.getContentPane().add(btnNuevoGasto);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(VistaListaClientes.class.getResource("/imagenes/customers.png")));
		lblNewLabel.setBounds(30, 10, 46, 35);
		frmHistorialDeGastos.getContentPane().add(lblNewLabel);
		
		JLabel lblNombreDelLocal = new JLabel("Nombre del local:");
		lblNombreDelLocal.setBounds(20, 205, 137, 14);
		frmHistorialDeGastos.getContentPane().add(lblNombreDelLocal);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono:");
		lblTelfono.setBounds(20, 244, 137, 14);
		frmHistorialDeGastos.getContentPane().add(lblTelfono);
		
		JLabel lblCuit = new JLabel("CUIT:");
		lblCuit.setBounds(20, 278, 137, 14);
		frmHistorialDeGastos.getContentPane().add(lblCuit);
		
		JLabel lblFechaDeIncorporacin = new JLabel("Fecha de incorporaci\u00F3n:");
		lblFechaDeIncorporacin.setBounds(20, 315, 152, 14);
		frmHistorialDeGastos.getContentPane().add(lblFechaDeIncorporacin);
		
		tfNombre_local = new JTextField();
		tfNombre_local.setEditable(false);
		tfNombre_local.setColumns(10);
		tfNombre_local.setBounds(166, 198, 279, 27);
		frmHistorialDeGastos.getContentPane().add(tfNombre_local);
		
		tfTelefono = new JTextField();
		tfTelefono.setEditable(false);
		tfTelefono.setColumns(10);
		tfTelefono.setBounds(166, 237, 137, 27);
		frmHistorialDeGastos.getContentPane().add(tfTelefono);
		
		tfCuit = new JTextField();
		tfCuit.setEditable(false);
		tfCuit.setColumns(10);
		tfCuit.setBounds(166, 273, 137, 27);
		frmHistorialDeGastos.getContentPane().add(tfCuit);
		
		tfFecha_incorp = new JTextField();
		tfFecha_incorp.setEditable(false);
		tfFecha_incorp.setColumns(10);
		tfFecha_incorp.setBounds(172, 308, 108, 27);
		frmHistorialDeGastos.getContentPane().add(tfFecha_incorp);
		
		JLabel lblCantidadDeVentas = new JLabel("Cantidad de remitos realizados:");
		lblCantidadDeVentas.setBounds(20, 350, 197, 14);
		frmHistorialDeGastos.getContentPane().add(lblCantidadDeVentas);
		
		tfCantidadFacturas = new JTextField();
		tfCantidadFacturas.setEditable(false);
		tfCantidadFacturas.setColumns(10);
		tfCantidadFacturas.setBounds(214, 343, 51, 27);
		frmHistorialDeGastos.getContentPane().add(tfCantidadFacturas);
		
		JLabel tf = new JLabel("Dinero total en remitos");
		tf.setBounds(20, 385, 197, 14);
		frmHistorialDeGastos.getContentPane().add(tf);
		
		tfTotalFacturas = new JTextField();
		tfTotalFacturas.setEditable(false);
		tfTotalFacturas.setColumns(10);
		tfTotalFacturas.setBounds(214, 378, 137, 27);
		frmHistorialDeGastos.getContentPane().add(tfTotalFacturas);
		
		JButton btnNewButton_1 = new JButton("Guardar cambios");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cliente.setNombre(tfNombre.getText());
				cliente.setDireccion(tfDireccion.getText());
				cliente.setNombre_local(tfNombre_local.getText());
				cliente.setCuit(tfCuit.getText());
				cliente.setTelefono(tfTelefono.getText());
				
				comercio.getListaClientes().modificarClienteBD(cliente);
				comercio.getListaClientes().cargarClientesDesdeBD();
				
				cbClientes.removeAllItems();
				clientes = comercio.getListaClientes().getClientes();
				for (Cliente i : clientes) {
					cbClientes.addItem(i.toString());
				}
				cbClientes.setSelectedItem(cliente.toString());
				lCambios.setVisible(true);
				Timer timer = new Timer(4000, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						lCambios.setVisible(false);
						
					}
				});
				timer.start();
				timer.setRepeats(false);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(VistaListaClientes.class.getResource("/imagenes/save.png")));
		btnNewButton_1.setBounds(145, 424, 166, 40);
		frmHistorialDeGastos.getContentPane().add(btnNewButton_1);
		
		JCheckBox cbEditable = new JCheckBox("Editable");
		cbEditable.setBounds(20, 433, 97, 23);
		frmHistorialDeGastos.getContentPane().add(cbEditable);
		cbEditable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbEditable.isSelected()) {
					tfNombre.setEditable(true);
					tfDireccion.setEditable(true);
					tfNombre_local.setEditable(true);
					tfTelefono.setEditable(true);
					tfCuit.setEditable(true);
					
				}
				else {
					tfNombre.setEditable(false);
					tfDireccion.setEditable(false);
					tfNombre_local.setEditable(false);
					tfTelefono.setEditable(false);
					tfCuit.setEditable(false);
				}
			}
		});
		
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comercio.getListaClientes().cargarClientesDesdeBD();
				clientes = comercio.getListaClientes().getClientes();
				cbClientes.removeAllItems();
				for (Cliente i : clientes) {
					cbClientes.addItem(i.toString());
				}
				cbClientes.setSelectedIndex(0);
				lCambios.setVisible(false);
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(VistaListaClientes.class.getResource("/imagenes/refresh1.png")));
		btnNewButton_2.setBounds(385, 34, 46, 44);
		frmHistorialDeGastos.getContentPane().add(btnNewButton_2);
		
		JLabel lblNewLabel_2 = new JLabel("Actualizar lista");
		lblNewLabel_2.setFont(new Font("Sitka Small", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(360, 20, 101, 14);
		frmHistorialDeGastos.getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton_3 = new JButton("Imprimir lista de clientes");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrinterJob job = PrinterJob.getPrinterJob();
				SwingUtilities.invokeLater(new Runnable() {			
					@Override
					public void run() {
						ListaClientesImp listaimprimible = new ListaClientesImp(comercio);
						job.setPrintable(listaimprimible);
					}
				});
				boolean top = job.printDialog();
				if (top) {
					try {
						job.print();
					} catch (PrinterException e2) {
						JOptionPane.showMessageDialog(null, "ERROR " + e2);
					}
				}
				int contador = 0;
				ArrayList<Cliente> clientes_40 = new ArrayList<Cliente>();
				for (int i = 35; i < clientes.size(); i++) {
					clientes_40.add(clientes.get(i));
					contador++;
					if (contador == 40 || clientes.indexOf(clientes.get(i)) == clientes.size()-1) {
						contador = 0;
						SwingUtilities.invokeLater(new Runnable() {					
							@Override
							public void run() {
								ListaClientesImp_onlyTable listaimprimible_onlytable = new ListaClientesImp_onlyTable(comercio, clientes_40);
								job.setPrintable(listaimprimible_onlytable);
							}
						});
						top = job.printDialog();
						if (top) {
							try {
								job.print();
							} catch (PrinterException e2) {
								JOptionPane.showMessageDialog(null, "ERROR " + e2);
							}
						}
						clientes_40.clear();
					}
				}
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(VistaListaClientes.class.getResource("/imagenes/print.png")));
		btnNewButton_3.setBounds(96, 484, 255, 40);
		frmHistorialDeGastos.getContentPane().add(btnNewButton_3);
		frmHistorialDeGastos.setVisible(true);
		for (Cliente i : clientes) {
			cbClientes.addItem(i.toString());
		}
	}
}
