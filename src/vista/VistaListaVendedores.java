package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;

import modelo.Cliente;
import modelo.Comercio;
import modelo.Remito;
import modelo.Gasto;
import modelo.Producto;
import modelo.Vendedor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
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
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import java.awt.Color;

public class VistaListaVendedores {

	private JFrame frmHistorialDeGastos;
	private JTextField tfNombre;
	private JTextField tfID;
	private ArrayList<Vendedor> vendedores;
	private JTextField tfCantidadFacturas;
	private JTextField tfTotalFacturas;
	private ArrayList<Remito> remitos;
	private Vendedor vendedor;
	private Date fechaactual;
	private JTextField tfTotalMes;
	private JTextField tfCantidadFacturasMes;
	private JTextField tfTotalACobrar;
	private JTextField tfMesPasado;


	/**
	 * Create the application.
	 */
	public VistaListaVendedores(Comercio comercio) {
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
		frmHistorialDeGastos.setTitle("Lista de vendedores");
		frmHistorialDeGastos.setBounds(100, 100, 471, 557);
		frmHistorialDeGastos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmHistorialDeGastos.getContentPane().setLayout(null);
		
		JLabel lblHistorialDeGastos = new JLabel("Lista de vendedores");
		lblHistorialDeGastos.setFont(new Font("Microsoft JhengHei", Font.BOLD, 17));
		lblHistorialDeGastos.setBounds(76, 10, 187, 30);
		frmHistorialDeGastos.getContentPane().add(lblHistorialDeGastos);
		
		fechaactual = new Date();
		comercio.getListaVendedores().cargarVendedoresDesdeBD();
		vendedores = comercio.getListaVendedores().getVendedores();
		DecimalFormat formateador = new DecimalFormat("###,###.##");
		
		tfNombre = new JTextField();
		tfNombre.setEditable(false);
		tfNombre.setBounds(166, 120, 279, 27);
		frmHistorialDeGastos.getContentPane().add(tfNombre);
		tfNombre.setColumns(10);
		
		JComboBox<String> cbVendedores = new JComboBox<String>();
		cbVendedores.setBounds(20, 56, 355, 23);
		frmHistorialDeGastos.getContentPane().add(cbVendedores);
		
		JLabel lblDescripcin = new JLabel("Nombre:");
		lblDescripcin.setBounds(20, 127, 137, 14);
		frmHistorialDeGastos.getContentPane().add(lblDescripcin);
		
		JLabel lblMonto = new JLabel("ID:");
		lblMonto.setBounds(20, 94, 137, 14);
		frmHistorialDeGastos.getContentPane().add(lblMonto);
		
		tfID = new JTextField();
		tfID.setEditable(false);
		tfID.setColumns(10);
		tfID.setBounds(166, 89, 51, 27);
		frmHistorialDeGastos.getContentPane().add(tfID);
		
		cbVendedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Vendedor i : vendedores) {
					if (cbVendedores.getSelectedItem() != null) {
						if (cbVendedores.getSelectedItem().equals(i.toString())) {
							vendedor = i;
							tfNombre.setText(i.getNombre());
							tfID.setText(String.valueOf(i.getId()));
						
							comercio.getArchivoRemitos().cargarRemitos_VendedorBD(i.getId());
							remitos = comercio.getArchivoRemitos().getRemitos();
							tfCantidadFacturas.setText(String.valueOf(remitos.size()));
							tfTotalFacturas.setText(formateador.format(comercio.getArchivoRemitos().getDineroTotal()));
							
							Date fechadesde = new Date();
							fechadesde.setDate(1);
							fechadesde.setHours(0);
							fechadesde.setMinutes(0);
							fechadesde.setSeconds(0);
							
							Date fechahasta = new Date();
							fechahasta.setDate(1);
							fechahasta.setHours(0);
							fechahasta.setMinutes(0);
							fechahasta.setSeconds(0);
							if ((fechahasta.getMonth()+1) == 12) {
								fechahasta.setMonth(0);
								fechahasta.setYear(fechahasta.getYear()+1);
							} else {
								fechahasta.setMonth(fechahasta.getMonth()+1);
							}
							
							tfTotalACobrar.setText(formateador.format(comercio.getArchivoRemitos().getDineroTotalEntreFechas(fechadesde, fechahasta)*(5.0/100.0)));
							tfTotalMes.setText(formateador.format(comercio.getArchivoRemitos().getDineroTotalEntreFechas(fechadesde, fechahasta)));
							tfCantidadFacturasMes.setText(Integer.toString(comercio.getArchivoRemitos().cantidadVentasEntreFechas(fechadesde, fechahasta)));
							
							fechahasta.setMonth(fechaactual.getMonth());
							fechahasta.setYear(fechaactual.getYear());
							fechahasta.setDate(1);
							
							if ((fechaactual.getMonth()+1) == 1) {
								fechadesde.setMonth(11);
								fechadesde.setYear(fechaactual.getYear()-1);
							} else {
								fechadesde.setMonth(fechaactual.getMonth()-1);
							}
							tfMesPasado.setText(formateador.format(comercio.getArchivoRemitos().getDineroTotalEntreFechas(fechadesde, fechahasta)*(5.0/100.0)));
							break;
						}
					}		
				}
			}
		});
		
		JSeparator separator = new JSeparator();
		separator.setBounds(35, 443, 396, 2);
		frmHistorialDeGastos.getContentPane().add(separator);
		
		JButton btnNewButton = new JButton("Eliminar vendedor");
		btnNewButton.setIcon(new ImageIcon(VistaListaVendedores.class.getResource("/imagenes/error.png")));
		btnNewButton.setHorizontalTextPosition(SwingConstants.LEFT);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "¿Está seguro/a que desea eliminar el vendedor?", "Eliminar vendedor", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					comercio.getListaVendedores().eliminarVendedorBD(vendedor);
					comercio.getListaVendedores().cargarVendedoresDesdeBD();
					vendedores = comercio.getListaVendedores().getVendedores();
					JOptionPane.showMessageDialog(null, "Vendedor " + tfID.getText() + " eliminado.");
					cbVendedores.removeAllItems();
					for (Vendedor i : vendedores) {
						cbVendedores.addItem(i.toString());
					}
				}
			}
		});
		btnNewButton.setBounds(20, 456, 197, 55);
		frmHistorialDeGastos.getContentPane().add(btnNewButton);
		
		JButton btnNuevoGasto = new JButton("Nuevo vendedor");
		btnNuevoGasto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						new VistaAgregarVendedor(comercio, cbVendedores);
					}
					
				});
			}
		});
		btnNuevoGasto.setIcon(new ImageIcon(VistaListaVendedores.class.getResource("/imagenes/new_doc.png")));
		btnNuevoGasto.setHorizontalTextPosition(SwingConstants.LEFT);
		btnNuevoGasto.setBounds(248, 456, 197, 55);
		frmHistorialDeGastos.getContentPane().add(btnNuevoGasto);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(VistaListaVendedores.class.getResource("/imagenes/preferences_contact_list.png")));
		lblNewLabel.setBounds(30, 10, 46, 35);
		frmHistorialDeGastos.getContentPane().add(lblNewLabel);
		
		JLabel lblCantidadDeVentas = new JLabel("Cantidad de remitos realizadas:");
		lblCantidadDeVentas.setBounds(20, 164, 197, 18);
		frmHistorialDeGastos.getContentPane().add(lblCantidadDeVentas);
		
		tfCantidadFacturas = new JTextField();
		tfCantidadFacturas.setEditable(false);
		tfCantidadFacturas.setColumns(10);
		tfCantidadFacturas.setBounds(214, 159, 51, 27);
		frmHistorialDeGastos.getContentPane().add(tfCantidadFacturas);
		
		JLabel tf = new JLabel("Dinero total en remitos:");
		tf.setBounds(20, 198, 197, 18);
		frmHistorialDeGastos.getContentPane().add(tf);
		
		tfTotalFacturas = new JTextField();
		tfTotalFacturas.setEditable(false);
		tfTotalFacturas.setColumns(10);
		tfTotalFacturas.setBounds(214, 193, 137, 27);
		frmHistorialDeGastos.getContentPane().add(tfTotalFacturas);
		
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comercio.getListaVendedores().cargarVendedoresDesdeBD();
				vendedores = comercio.getListaVendedores().getVendedores();
				cbVendedores.removeAllItems();
				for (Vendedor i : vendedores) {
					cbVendedores.addItem(i.toString());
				}
				cbVendedores.setSelectedIndex(0);
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(VistaListaVendedores.class.getResource("/imagenes/refresh1.png")));
		btnNewButton_2.setBounds(385, 34, 46, 44);
		frmHistorialDeGastos.getContentPane().add(btnNewButton_2);
		
		JLabel lblNewLabel_2 = new JLabel("Actualizar lista");
		lblNewLabel_2.setFont(new Font("Sitka Small", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(360, 20, 101, 14);
		frmHistorialDeGastos.getContentPane().add(lblNewLabel_2);
		
		JLabel lblResumenMensualDel = new JLabel("Resumen mensual del vendedor");
		lblResumenMensualDel.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblResumenMensualDel.setBounds(108, 243, 287, 14);
		frmHistorialDeGastos.getContentPane().add(lblResumenMensualDel);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(57, 230, 327, 2);
		frmHistorialDeGastos.getContentPane().add(separator_1);
		
		JLabel lblNewLabel_1 = new JLabel("Per\u00EDodo actual: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(146, 268, 187, 14);
		frmHistorialDeGastos.getContentPane().add(lblNewLabel_1);
		frmHistorialDeGastos.setVisible(true);
		lblNewLabel_1.setText("Período actual: " + (fechaactual.getMonth()+1) + "/" + (fechaactual.getYear()+1900));
		
		JLabel lblDineroEnFacturas = new JLabel("Monto total en ventas($):");
		lblDineroEnFacturas.setBounds(66, 343, 197, 18);
		frmHistorialDeGastos.getContentPane().add(lblDineroEnFacturas);
		
		JLabel lblCantidadDeFacturas = new JLabel("Cantidad de remitos");
		lblCantidadDeFacturas.setBounds(66, 373, 197, 18);
		frmHistorialDeGastos.getContentPane().add(lblCantidadDeFacturas);
		
		tfTotalMes = new JTextField();
		tfTotalMes.setEditable(false);
		tfTotalMes.setColumns(10);
		tfTotalMes.setBounds(248, 338, 103, 27);
		frmHistorialDeGastos.getContentPane().add(tfTotalMes);
		
		tfCantidadFacturasMes = new JTextField();
		tfCantidadFacturasMes.setEditable(false);
		tfCantidadFacturasMes.setColumns(10);
		tfCantidadFacturasMes.setBounds(248, 368, 103, 27);
		frmHistorialDeGastos.getContentPane().add(tfCantidadFacturasMes);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(Color.BLACK);
		separator_1_1.setBackground(Color.WHITE);
		separator_1_1.setBounds(108, 257, 210, 2);
		frmHistorialDeGastos.getContentPane().add(separator_1_1);
		
		JLabel lblTotalACobrar = new JLabel("Total a cobrar ($):");
		lblTotalACobrar.setBounds(66, 313, 197, 18);
		frmHistorialDeGastos.getContentPane().add(lblTotalACobrar);
		
		tfTotalACobrar = new JTextField();
		tfTotalACobrar.setEditable(false);
		tfTotalACobrar.setColumns(10);
		tfTotalACobrar.setBounds(248, 308, 103, 27);
		frmHistorialDeGastos.getContentPane().add(tfTotalACobrar);
		
		JLabel lblTotalACobrar_1 = new JLabel("Total a cobrar del mes pasado ($):");
		lblTotalACobrar_1.setBounds(66, 414, 197, 18);
		frmHistorialDeGastos.getContentPane().add(lblTotalACobrar_1);
		
		tfMesPasado = new JTextField();
		tfMesPasado.setEditable(false);
		tfMesPasado.setColumns(10);
		tfMesPasado.setBounds(272, 411, 103, 27);
		frmHistorialDeGastos.getContentPane().add(tfMesPasado);
		for (Vendedor i : vendedores) {
			cbVendedores.addItem(i.toString());
		}
	}
}
