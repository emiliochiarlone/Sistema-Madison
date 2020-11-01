
package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

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

public class VistaAgregarGasto {

	private JFrame frmRegistrarNuevoGasto;
	private JTextField tfDescripcion;
	private JTextField tfMonto;


	/**
	 * Create the application.
	 */
	public VistaAgregarGasto(Comercio comercio, JComboBox<String> combobox) {
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
		
		Gasto gasto = new Gasto();
		frmRegistrarNuevoGasto = new JFrame();
		frmRegistrarNuevoGasto.setResizable(false);
		frmRegistrarNuevoGasto.setTitle("Registrar nuevo gasto");
		frmRegistrarNuevoGasto.setBounds(100, 100, 356, 245);
		frmRegistrarNuevoGasto.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmRegistrarNuevoGasto.getContentPane().setLayout(null);
		
		JLabel lblRegistrarNuevoGasto = new JLabel("Registrar nuevo gasto");
		lblRegistrarNuevoGasto.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblRegistrarNuevoGasto.setBounds(10, 11, 172, 20);
		frmRegistrarNuevoGasto.getContentPane().add(lblRegistrarNuevoGasto);
		
		tfDescripcion = new JTextField();
		tfDescripcion.setBounds(133, 44, 182, 27);
		frmRegistrarNuevoGasto.getContentPane().add(tfDescripcion);
		tfDescripcion.setColumns(10);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n:");
		lblDescripcin.setBounds(10, 51, 100, 14);
		frmRegistrarNuevoGasto.getContentPane().add(lblDescripcin);
		
		JLabel lblMonto = new JLabel("Monto ($):");
		lblMonto.setBounds(10, 87, 100, 14);
		frmRegistrarNuevoGasto.getContentPane().add(lblMonto);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerDateModel(new Date(1603422000000L), null, null, Calendar.DAY_OF_YEAR));
		spinner.setBounds(133, 113, 182, 23);
		frmRegistrarNuevoGasto.getContentPane().add(spinner);
		frmRegistrarNuevoGasto.setVisible(true);
		spinner.setValue(new Date());
		
		tfMonto = new JTextField();
		tfMonto.setColumns(10);
		tfMonto.setBounds(133, 80, 71, 27);
		frmRegistrarNuevoGasto.getContentPane().add(tfMonto);
		
		JButton btnNewButton = new JButton("Registrar gasto");
		btnNewButton.setIcon(new ImageIcon(VistaAgregarGasto.class.getResource("/imagenes/button_ok.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "¿Está seguro/a que desea registrar el gasto?", "Confirmar gasto", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					if (tfDescripcion.getText().equals("") || tfMonto.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Debe ingresar descripción y monto");
					}
					else {
						String sMonto = tfMonto.getText();
						boolean flag = false;
						for (int i = 0; i< sMonto.length(); i++) {
							if (sMonto.charAt(i)<48 || sMonto.charAt(i)>57) {
								JOptionPane.showMessageDialog(null, "Ingrese sólo números en el campo de monto");
								flag = true;
								i = sMonto.length();
							}
						}
						if (!flag) {
							gasto.setDescripcion(tfDescripcion.getText());
							gasto.setMonto(Double.valueOf(tfMonto.getText()));
							gasto.setFecha((Date) spinner.getValue());
							comercio.getArchivoGastos().insertarGastoBD(gasto);
							comercio.getArchivoGastos().cargarGastosDesdeBD();
							if (combobox != null) {
								combobox.removeAllItems();
								ArrayList<Gasto> gastos = comercio.getArchivoGastos().getGastos();
								for (Gasto i : gastos) {
									combobox.addItem(i.toString());
								}
							}
							JOptionPane.showMessageDialog(null, "Se ha registrado el gasto correctamente");
							
							frmRegistrarNuevoGasto.dispose();
						}
					}
				}
			}
		});
		btnNewButton.setBounds(73, 161, 182, 34);
		frmRegistrarNuevoGasto.getContentPane().add(btnNewButton);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(37, 148, 259, 2);
		frmRegistrarNuevoGasto.getContentPane().add(separator);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(VistaAgregarGasto.class.getResource("/imagenes/gastos.png")));
		lblNewLabel.setBounds(166, 0, 46, 50);
		frmRegistrarNuevoGasto.getContentPane().add(lblNewLabel);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(10, 118, 100, 14);
		frmRegistrarNuevoGasto.getContentPane().add(lblFecha);
		
		
	}
}
