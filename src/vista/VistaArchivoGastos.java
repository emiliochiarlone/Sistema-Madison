package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;

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

public class VistaArchivoGastos {

	private JFrame frmHistorialDeGastos;
	private JTextField tfDescripcion;
	private JTextField tfMonto;
	private JTextField tfFecha;
	private ArrayList<Gasto> gastos;


	/**
	 * Create the application.
	 */
	public VistaArchivoGastos(Comercio comercio) {
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
		frmHistorialDeGastos.setTitle("Historial de gastos");
		frmHistorialDeGastos.setBounds(100, 100, 715, 300);
		frmHistorialDeGastos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmHistorialDeGastos.getContentPane().setLayout(null);
		
		JLabel lblHistorialDeGastos = new JLabel("Historial de gastos");
		lblHistorialDeGastos.setFont(new Font("Microsoft JhengHei", Font.BOLD, 17));
		lblHistorialDeGastos.setBounds(20, 10, 187, 30);
		frmHistorialDeGastos.getContentPane().add(lblHistorialDeGastos);
		
		comercio.getArchivoGastos().cargarGastosDesdeBD();
		gastos = comercio.getArchivoGastos().getGastos();
		DecimalFormat formateador = new DecimalFormat("###,###.##");
		
		tfDescripcion = new JTextField();
		tfDescripcion.setEditable(false);
		tfDescripcion.setBounds(166, 89, 428, 27);
		frmHistorialDeGastos.getContentPane().add(tfDescripcion);
		tfDescripcion.setColumns(10);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(20, 56, 669, 23);
		frmHistorialDeGastos.getContentPane().add(comboBox);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n:");
		lblDescripcin.setBounds(20, 92, 137, 14);
		frmHistorialDeGastos.getContentPane().add(lblDescripcin);
		
		JLabel lblMonto = new JLabel("Monto ($):");
		lblMonto.setBounds(20, 123, 137, 14);
		frmHistorialDeGastos.getContentPane().add(lblMonto);
		
		tfMonto = new JTextField();
		tfMonto.setEditable(false);
		tfMonto.setColumns(10);
		tfMonto.setBounds(166, 120, 114, 27);
		frmHistorialDeGastos.getContentPane().add(tfMonto);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(20, 154, 137, 14);
		frmHistorialDeGastos.getContentPane().add(lblFecha);
		
		tfFecha = new JTextField();
		tfFecha.setEditable(false);
		tfFecha.setColumns(10);
		tfFecha.setBounds(166, 151, 114, 27);
		frmHistorialDeGastos.getContentPane().add(tfFecha);
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Gasto i : gastos) {
					if (comboBox.getSelectedItem() != null) {
						if (comboBox.getSelectedItem().equals(i.toString())) {
							tfDescripcion.setText(i.getDescripcion());
							tfMonto.setText(formateador.format(i.getMonto()));
							tfFecha.setText(i.dateToString());
							break;
						}
					}		
				}
			}
		});
		
		JSeparator separator = new JSeparator();
		separator.setBounds(53, 197, 606, 2);
		frmHistorialDeGastos.getContentPane().add(separator);
		
		JButton btnNewButton = new JButton("Eliminar gasto");
		btnNewButton.setIcon(new ImageIcon(VistaArchivoGastos.class.getResource("/imagenes/error.png")));
		btnNewButton.setHorizontalTextPosition(SwingConstants.LEFT);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "¿Está seguro/a que desea eliminar el gasto?", "Confirmar gasto", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					for (Gasto i : gastos) {
						if (i.toString().equals(comboBox.getSelectedItem())){
							comercio.getArchivoGastos().eliminarGastoBD(i);;
							if (comboBox != null) {
								comercio.getArchivoGastos().cargarGastosDesdeBD();
								gastos = comercio.getArchivoGastos().getGastos();
								comboBox.removeAllItems();
								for (Gasto j : gastos) {
									comboBox.addItem(j.toString());
								}
							}
						}
					}
				}
			}
		});
		btnNewButton.setBounds(63, 210, 253, 48);
		frmHistorialDeGastos.getContentPane().add(btnNewButton);
		
		JButton btnNuevoGasto = new JButton("Nuevo gasto");
		btnNuevoGasto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						new VistaAgregarGasto(comercio, comboBox);
					}
					
				});
			}
		});
		btnNuevoGasto.setIcon(new ImageIcon(VistaArchivoGastos.class.getResource("/imagenes/new_doc.png")));
		btnNuevoGasto.setHorizontalTextPosition(SwingConstants.LEFT);
		btnNuevoGasto.setBounds(389, 210, 253, 48);
		frmHistorialDeGastos.getContentPane().add(btnNuevoGasto);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(VistaArchivoGastos.class.getResource("/imagenes/gastos.png")));
		lblNewLabel.setBounds(190, 11, 46, 35);
		frmHistorialDeGastos.getContentPane().add(lblNewLabel);
		frmHistorialDeGastos.setVisible(true);
		for (Gasto i : gastos) {
			comboBox.addItem(i.toString());
		}
	}
}
