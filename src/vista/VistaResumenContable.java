package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import modelo.Comercio;

import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.util.Date;
import java.util.Calendar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class VistaResumenContable {

	private JFrame frmResumenContable;
	private JTextField tfCantidadVentas;
	private JTextField tfDineroTotal;
	private JTextField tfGanancia;
	private JTextField tfCantidadTotal;
	private JTextField tfDineroTotal2;
	private JTextField tfGananciaNeta;


	/**
	 * Create the application.
	 */
	public VistaResumenContable(Comercio comercio) {
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
		
		frmResumenContable = new JFrame();
		frmResumenContable.setResizable(false);
		frmResumenContable.setVisible(true);
		frmResumenContable.setTitle("Resumen contable");
		frmResumenContable.setBounds(100, 100, 417, 403);
		frmResumenContable.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmResumenContable.getContentPane().setLayout(null);
		
		DecimalFormat formateador = new DecimalFormat("###,###.##");
		
		JLabel lblResumenContable = new JLabel("Resumen contable");
		lblResumenContable.setIcon(new ImageIcon(VistaResumenContable.class.getResource("/imagenes/pie_chart.png")));
		lblResumenContable.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 22));
		lblResumenContable.setBounds(20, 0, 271, 54);
		frmResumenContable.getContentPane().add(lblResumenContable);
		lblResumenContable.setHorizontalTextPosition(SwingConstants.LEFT);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 52, 360, 2);
		frmResumenContable.getContentPane().add(separator);
		
		tfCantidadVentas = new JTextField();
		tfCantidadVentas.setEditable(false);
		tfCantidadVentas.setBounds(267, 62, 86, 27);
		frmResumenContable.getContentPane().add(tfCantidadVentas);
		tfCantidadVentas.setColumns(10);
		
		JLabel lblCantidadDeVentas = new JLabel("Cantidad de ventas realizadas:");
		lblCantidadDeVentas.setBounds(20, 69, 219, 14);
		frmResumenContable.getContentPane().add(lblCantidadDeVentas);
		
		JLabel lblDineroTotalAcumulado = new JLabel("Dinero total acumulado en ventas ($):");
		lblDineroTotalAcumulado.setBounds(20, 96, 219, 14);
		frmResumenContable.getContentPane().add(lblDineroTotalAcumulado);
		
		tfDineroTotal = new JTextField();
		tfDineroTotal.setEditable(false);
		tfDineroTotal.setBounds(267, 89, 86, 27);
		frmResumenContable.getContentPane().add(tfDineroTotal);
		tfDineroTotal.setColumns(10);
		
		JLabel lblGananciaBruta = new JLabel("Ganancia bruta total ($):");
		lblGananciaBruta.setBounds(20, 124, 219, 14);
		frmResumenContable.getContentPane().add(lblGananciaBruta);
		
		tfGanancia = new JTextField();
		tfGanancia.setEditable(false);
		tfGanancia.setColumns(10);
		tfGanancia.setBounds(267, 117, 86, 27);
		frmResumenContable.getContentPane().add(tfGanancia);
		
		comercio.getArchivoRemitos().cargarRemitosDesdeBD();
		comercio.getArchivoGastos().cargarGastosDesdeBD();
		comercio.getHistorialGanancias().cargarGananciasDesdeBD();
		
		tfCantidadVentas.setText(formateador.format(comercio.getArchivoRemitos().getCantidadVentas()));
		tfDineroTotal.setText(formateador.format(comercio.getArchivoRemitos().getDineroTotal()));
		tfGanancia.setText(formateador.format(comercio.getHistorialGanancias().calcularGananciaTotal()));
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 148, 360, 2);
		frmResumenContable.getContentPane().add(separator_1);
		
		JLabel label = new JLabel("Resumen por fecha");
		label.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
		label.setBounds(20, 161, 219, 25);
		frmResumenContable.getContentPane().add(label);
		
		JSpinner sDesde = new JSpinner();
		sDesde.setModel(new SpinnerDateModel(new Date(1595991600000L), null, null, Calendar.DAY_OF_YEAR));
		sDesde.setBounds(74, 197, 130, 28);
		frmResumenContable.getContentPane().add(sDesde);
		sDesde.setValue(new Date());
		
		JSpinner sHasta = new JSpinner();
		sHasta.setModel(new SpinnerDateModel(new Date(1595991600000L), null, null, Calendar.DAY_OF_YEAR));
		sHasta.setBounds(74, 228, 130, 25);
		frmResumenContable.getContentPane().add(sHasta);
		sHasta.setValue(new Date());
		
		JLabel label_1 = new JLabel("Desde:");
		label_1.setBounds(20, 200, 219, 14);
		frmResumenContable.getContentPane().add(label_1);
		
		JLabel lblHasta = new JLabel("Hasta:");
		lblHasta.setBounds(20, 234, 219, 14);
		frmResumenContable.getContentPane().add(lblHasta);
				
		JButton button = new JButton("Calcular");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfGananciaNeta.setText(formateador.format((comercio.getHistorialGanancias().calcularGananciaEntreFechas((Date)sDesde.getValue(), (Date) sHasta.getValue())-comercio.getArchivoGastos().getMontoEntreFechas((Date)sDesde.getValue(), (Date) sHasta.getValue()))));
				tfCantidadTotal.setText(formateador.format(comercio.getArchivoRemitos().cantidadVentasEntreFechas((Date)sDesde.getValue(), (Date) sHasta.getValue())));
				tfDineroTotal2.setText(formateador.format(comercio.getArchivoRemitos().getDineroTotalEntreFechas((Date)sDesde.getValue(), (Date) sHasta.getValue())));
			}
		});
		button.setBounds(235, 196, 130, 48);
		frmResumenContable.getContentPane().add(button);
		
		tfCantidadTotal = new JTextField();
		tfCantidadTotal.setText("0");
		tfCantidadTotal.setEditable(false);
		tfCantidadTotal.setColumns(10);
		tfCantidadTotal.setBounds(235, 270, 86, 27);
		frmResumenContable.getContentPane().add(tfCantidadTotal);
		
		tfDineroTotal2 = new JTextField();
		tfDineroTotal2.setText("0");
		tfDineroTotal2.setEditable(false);
		tfDineroTotal2.setColumns(10);
		tfDineroTotal2.setBounds(235, 301, 86, 27);
		frmResumenContable.getContentPane().add(tfDineroTotal2);
		
		tfGananciaNeta = new JTextField();
		tfGananciaNeta.setText("0");
		tfGananciaNeta.setEditable(false);
		tfGananciaNeta.setColumns(10);
		tfGananciaNeta.setBounds(235, 332, 86, 27);
		frmResumenContable.getContentPane().add(tfGananciaNeta);
		
		JLabel label_2 = new JLabel("Cantidad de ventas realizadas:");
		label_2.setBounds(20, 277, 219, 14);
		frmResumenContable.getContentPane().add(label_2);
		
		JLabel lblDineroTotalAcumulado_1 = new JLabel("Dinero total acumulado en ventas ($):");
		lblDineroTotalAcumulado_1.setBounds(20, 305, 219, 20);
		frmResumenContable.getContentPane().add(lblDineroTotalAcumulado_1);
		
		JLabel lblGananciaNetaTotal = new JLabel("Ganancia neta total ($):");
		lblGananciaNetaTotal.setBounds(20, 336, 219, 20);
		frmResumenContable.getContentPane().add(lblGananciaNetaTotal);
	}
}
