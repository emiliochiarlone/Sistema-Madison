package vista;

import java.awt.EventQueue;

import java.awt.Image;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import imprimibles.HojaDeRutaImp;
import imprimibles.HojaDeRutaImp_onlyTable;
import imprimibles.ListaClientesImp;
import imprimibles.ListaClientesImp_onlyTable;
import modelo.Cliente;
import modelo.Comercio;
import modelo.Remito;

import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import java.awt.CardLayout;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import javax.swing.JToolBar;
import javax.swing.JTabbedPane;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JPasswordField;
import java.awt.Toolkit;
import java.awt.Window.Type;

public class VistaMenuPrincipal {

	private JFrame frmComercialmenteMen;
	private JPasswordField passwordField;
	private ArrayList<Remito> remitos_dehoy;

	/**
	 * Create the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public VistaMenuPrincipal(Comercio comercio) {
		initialize(comercio);
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	private void initialize(Comercio comercio)  {
		frmComercialmenteMen = new JFrame();
		frmComercialmenteMen.setResizable(false);
		frmComercialmenteMen.setIconImage(Toolkit.getDefaultToolkit().getImage(VistaMenuPrincipal.class.getResource("/imagenes/logo2.png")));
		frmComercialmenteMen.getContentPane().setBackground(SystemColor.controlHighlight);
//		frame.getContentPane().setBackground(new Color(85, 107, 47));
//		frame.getContentPane().setForeground(Color.WHITE);
//		frame.setForeground(Color.GRAY);
		frmComercialmenteMen.setBounds(100, 100, 1053, 587);
		frmComercialmenteMen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmComercialmenteMen.setTitle("Madison - Men\u00FA principal");
		frmComercialmenteMen.getContentPane().setLayout(null);
		
		comercio.getStock().cargarStockDesdeBD();
		
		JButton bStock = new JButton("STOCK");
		bStock.setEnabled(false);
		bStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
			        @Override
			        public void run() {
						new VistaStockDisponible(comercio, bStock);
			        }
			     });
			}
		});
		
		JButton bNuevaFactura = new JButton("NUEVO REMITO");
		bNuevaFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
			        @Override
			        public void run() {
						new VistaAgregarRemito(comercio, bStock);
			        }
			     });
			}
		});
		bNuevaFactura.setEnabled(false);
		bNuevaFactura.setFont(new Font("SansSerif", Font.PLAIN, 11));
		bNuevaFactura.setToolTipText("Realizar una factura");
		bNuevaFactura.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/imagenes/netvibes.png")));
		bNuevaFactura.setBounds(483, 428, 245, 45);
		frmComercialmenteMen.getContentPane().add(bNuevaFactura);
		
		JButton bNuevoProducto = new JButton("AGREGAR PRODUCTO");
		bNuevoProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
			        @Override
			        public void run() {
						new VistaAgregarProducto(comercio, bStock, null);
			        }
			     });
			}
		});
		bNuevoProducto.setEnabled(false);
		bNuevoProducto.setFont(new Font("SansSerif", Font.PLAIN, 11));
		bNuevoProducto.setToolTipText("Agregar nuevo producto a la lista de precios");
		bNuevoProducto.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/imagenes/netvibes.png")));
		bNuevoProducto.setBounds(483, 487, 245, 45);
		frmComercialmenteMen.getContentPane().add(bNuevoProducto);
		
		JButton bNuevoCliente = new JButton("AGREGAR CLIENTE");
		bNuevoCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
			        @Override
			        public void run() {
						new VistaAgregarCliente(comercio, null);
			        }
			     });
			}
		});
		bNuevoCliente.setEnabled(false);
		bNuevoCliente.setFont(new Font("SansSerif", Font.PLAIN, 11));
		bNuevoCliente.setToolTipText("Registrar datos de un nuevo cliente");
		bNuevoCliente.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/imagenes/netvibes.png")));
		bNuevoCliente.setBounds(759, 428, 245, 45);
		frmComercialmenteMen.getContentPane().add(bNuevoCliente);
		
		
		JLabel lblContraseaIncorrecta = new JLabel("Contrase\u00F1a incorrecta");
		lblContraseaIncorrecta.setForeground(Color.RED);
		lblContraseaIncorrecta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblContraseaIncorrecta.setBounds(48, 428, 197, 24);
		frmComercialmenteMen.getContentPane().add(lblContraseaIncorrecta);
		lblContraseaIncorrecta.setVisible(false);
		
		JButton bNuevoGasto = new JButton("AGREGAR GASTO");
		bNuevoGasto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
			        @Override
			        public void run() {
						new VistaAgregarGasto(comercio, null);
			        }
			     });
			}
		});
		bNuevoGasto.setEnabled(false);
		bNuevoGasto.setFont(new Font("SansSerif", Font.PLAIN, 11));
		bNuevoGasto.setToolTipText("Registrar un nuevo gasto");
		bNuevoGasto.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/imagenes/netvibes.png")));
		bNuevoGasto.setBounds(759, 487, 245, 45);
		frmComercialmenteMen.getContentPane().add(bNuevoGasto);
		
		JLabel lComunicarse2 = new JLabel("Ante cualquier consulta del programa");
		lComunicarse2.setHorizontalAlignment(SwingConstants.CENTER);
		lComunicarse2.setBounds(10, 324, 217, 29);
		frmComercialmenteMen.getContentPane().add(lComunicarse2);
		lComunicarse2.setVisible(false);
		
		JLabel lComunicarse = new JLabel("comunicarse con 2346-593924");
		lComunicarse.setHorizontalAlignment(SwingConstants.CENTER);
		lComunicarse.setBounds(10, 338, 209, 29);
		frmComercialmenteMen.getContentPane().add(lComunicarse);
		lComunicarse.setVisible(false);
		
		bStock.setFont(new Font("SansSerif", Font.PLAIN, 11));
		bStock.setBounds(850, 178, 154, 45);
		frmComercialmenteMen.getContentPane().add(bStock);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/imagenes/logo.png")));
		lblNewLabel.setBounds(22, 11, 180, 181);
		frmComercialmenteMen.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Listas");
		lblNewLabel_1.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/imagenes/redtick_document.png")));
		lblNewLabel_1.setForeground(new Color(0, 0, 51));
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(275, 121, 136, 33);
		frmComercialmenteMen.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/imagenes/menu.png")));
		lblNewLabel_2.setBounds(275, 22, 46, 45);
		frmComercialmenteMen.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Men\u00FA principal");
		lblNewLabel_3.setFont(new Font("Dubai Medium", Font.PLAIN, 30));
		lblNewLabel_3.setBounds(331, 22, 209, 45);
		frmComercialmenteMen.getContentPane().add(lblNewLabel_3);
		
		JButton bPrecios = new JButton("PRECIOS");
		bPrecios.setEnabled(false);
		bPrecios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
			        @Override
			        public void run() {
						new VistaListaDePrecios(comercio, bStock);
			        }
			     });
			}
		});
		bPrecios.setFont(new Font("SansSerif", Font.PLAIN, 11));
		bPrecios.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/imagenes/price_tag - copia.png")));
		bPrecios.setBounds(320, 175, 154, 51);
		frmComercialmenteMen.getContentPane().add(bPrecios);
		
		JButton bClientes = new JButton("CLIENTES");
		bClientes.setEnabled(false);
		bClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
			        @Override
			        public void run() {
						new VistaListaClientes(comercio);
			        }
			     });
			}
		});
		bClientes.setFont(new Font("SansSerif", Font.PLAIN, 11));
		bClientes.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/imagenes/customers - copia.png")));
		bClientes.setBounds(320, 250, 154, 51);
		frmComercialmenteMen.getContentPane().add(bClientes);
		
		JButton bVendedores = new JButton("VENDEDORES");
		bVendedores.setEnabled(false);
		bVendedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
			        @Override
			        public void run() {
						new VistaListaVendedores(comercio);
			        }
			     });
			}
		});
		JButton btnHojaDeRuta = new JButton("HOJA DE RUTA");
		btnHojaDeRuta.setEnabled(false);
		bVendedores.setFont(new Font("SansSerif", Font.PLAIN, 11));
		bVendedores.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/imagenes/preferences_contact_list - copia.png")));
		bVendedores.setBounds(320, 326, 154, 50);
		frmComercialmenteMen.getContentPane().add(bVendedores);
		
		JLabel lblNewLabel_1_1 = new JLabel("Archivos");
		lblNewLabel_1_1.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/imagenes/storage_files.png")));
		lblNewLabel_1_1.setForeground(new Color(0, 0, 51));
		lblNewLabel_1_1.setFont(new Font("SansSerif", Font.PLAIN, 25));
		lblNewLabel_1_1.setBounds(518, 115, 173, 45);
		frmComercialmenteMen.getContentPane().add(lblNewLabel_1_1);
		
		JButton bFacturas = new JButton("REMITOS");
		bFacturas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
			        @Override
			        public void run() {
						new VistaArchivoRemitos(comercio,bStock);
			        }
			     });
			}
		});
		bFacturas.setEnabled(false);
		bFacturas.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/imagenes/my_invoices - copia.png")));
		bFacturas.setFont(new Font("SansSerif", Font.PLAIN, 11));
		bFacturas.setBounds(592, 175, 154, 51);
		frmComercialmenteMen.getContentPane().add(bFacturas);
		
		JButton bGastos = new JButton("GASTOS");
		bGastos.setEnabled(false);
		bGastos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
			        @Override
			        public void run() {
						new VistaArchivoGastos(comercio);
			        }
			     });
			}
		});
		bGastos.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/imagenes/gastos - copia.png")));
		bGastos.setFont(new Font("SansSerif", Font.PLAIN, 11));
		bGastos.setBounds(592, 250, 154, 51);
		frmComercialmenteMen.getContentPane().add(bGastos);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Gesti\u00F3n");
		lblNewLabel_1_1_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1_1_1.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/imagenes/emblem_money - copia.png")));
		lblNewLabel_1_1_1.setForeground(new Color(0, 0, 51));
		lblNewLabel_1_1_1.setFont(new Font("SansSerif", Font.PLAIN, 25));
		lblNewLabel_1_1_1.setBounds(790, 117, 173, 39);
		frmComercialmenteMen.getContentPane().add(lblNewLabel_1_1_1);
		
		JButton bResumen = new JButton("RESUMEN");
		bResumen.setEnabled(false);
		bResumen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
			        @Override
			        public void run() {
						new VistaResumenContable(comercio);
			        }
			     });
			}
		});
		bResumen.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/imagenes/pie_chart - copia.png")));
		bResumen.setFont(new Font("SansSerif", Font.PLAIN, 11));
		bResumen.setBounds(850, 253, 154, 45);
		frmComercialmenteMen.getContentPane().add(bResumen);
		
		JLabel lblNewLabel_4 = new JLabel("Sistema de gesti\u00F3n");
		lblNewLabel_4.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(47, 225, 180, 19);
		frmComercialmenteMen.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("MADISON DISTRIBUCIONES");
		lblNewLabel_5.setFont(new Font("Candara Light", Font.BOLD, 18));
		lblNewLabel_5.setBounds(10, 203, 220, 29);
		frmComercialmenteMen.getContentPane().add(lblNewLabel_5);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setToolTipText("Configuraci\u00F3n");
		btnNewButton_1.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/imagenes/settings.png")));
		btnNewButton_1.setBounds(88, 487, 51, 50);
		frmComercialmenteMen.getContentPane().add(btnNewButton_1);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(237, 11, 8, 526);
		frmComercialmenteMen.getContentPane().add(separator);
		
		JToggleButton tglbtnNewToggleButton = new JToggleButton("");
		tglbtnNewToggleButton.setToolTipText("Modo nocturno");
		tglbtnNewToggleButton.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/imagenes/moon_3.png")));
		tglbtnNewToggleButton.setBounds(10, 508, 26, 27);
		frmComercialmenteMen.getContentPane().add(tglbtnNewToggleButton);
		
		JLabel lIngresar = new JLabel("Ingresar al sistema");
		lIngresar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lIngresar.setBounds(58, 291, 168, 24);
		frmComercialmenteMen.getContentPane().add(lIngresar);
		
		JLabel lIngresar2 = new JLabel("Ingrese su contrase\u00F1a");
		lIngresar2.setHorizontalAlignment(SwingConstants.CENTER);
		lIngresar2.setBounds(8, 326, 217, 14);
		frmComercialmenteMen.getContentPane().add(lIngresar2);
		
		JLabel lIngresar3 = new JLabel("para entrar al sistema.");
		lIngresar3.setHorizontalAlignment(SwingConstants.CENTER);
		lIngresar3.setBounds(8, 342, 217, 14);
		frmComercialmenteMen.getContentPane().add(lIngresar3);
		
		JButton bIngresar = new JButton("Ingresar");
		bIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ( passwordField.getText().equals(comercio.getContraseña())) {
					lIngresar.setText("¡Bienvenido!");
					lIngresar.setBounds(lIngresar.getX()+18, lIngresar.getY(), lIngresar.getWidth(), lIngresar.getHeight());
					lIngresar.setBackground(SystemColor.GREEN);
					lIngresar2.setVisible(false);
					lIngresar3.setVisible(false);
					passwordField.setEnabled(false);
					passwordField.setVisible(false);
					bIngresar.setEnabled(false);
					bIngresar.setVisible(false);
					lComunicarse.setVisible(true);
					lComunicarse2.setVisible(true);
					lblContraseaIncorrecta.setVisible(false);
					
					bPrecios.setEnabled(true);
					bClientes.setEnabled(true);
					bVendedores.setEnabled(true);
					bFacturas.setEnabled(true);
					bGastos.setEnabled(true);
					bStock.setEnabled(true);
					bResumen.setEnabled(true);
					bNuevaFactura.setEnabled(true);
					bNuevoProducto.setEnabled(true);
					bNuevoCliente.setEnabled(true);
					bNuevoGasto.setEnabled(true);
					btnHojaDeRuta.setEnabled(true);
					
				} else {
					lblContraseaIncorrecta.setVisible(true);
				}
			}
		});
		bIngresar.setBounds(71, 397, 89, 23);
		frmComercialmenteMen.getContentPane().add(bIngresar);
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/imagenes/dot.png")));
		lblNewLabel_8.setBounds(578, 269, 10, 14);
		frmComercialmenteMen.getContentPane().add(lblNewLabel_8);
		
		JLabel lblNewLabel_8_1 = new JLabel("");
		lblNewLabel_8_1.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/imagenes/dot.png")));
		lblNewLabel_8_1.setBounds(306, 194, 10, 14);
		frmComercialmenteMen.getContentPane().add(lblNewLabel_8_1);
		
		JLabel lblNewLabel_8_2 = new JLabel("");
		lblNewLabel_8_2.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/imagenes/dot.png")));
		lblNewLabel_8_2.setBounds(306, 269, 10, 14);
		frmComercialmenteMen.getContentPane().add(lblNewLabel_8_2);
		
		JLabel lblNewLabel_8_3 = new JLabel("");
		lblNewLabel_8_3.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/imagenes/dot.png")));
		lblNewLabel_8_3.setBounds(306, 345, 10, 14);
		frmComercialmenteMen.getContentPane().add(lblNewLabel_8_3);
		
		JLabel lblNewLabel_8_4 = new JLabel("");
		lblNewLabel_8_4.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/imagenes/dot.png")));
		lblNewLabel_8_4.setBounds(578, 194, 10, 14);
		frmComercialmenteMen.getContentPane().add(lblNewLabel_8_4);
		
		JLabel lblNewLabel_8_5 = new JLabel("");
		lblNewLabel_8_5.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/imagenes/dot.png")));
		lblNewLabel_8_5.setBounds(837, 194, 10, 14);
		frmComercialmenteMen.getContentPane().add(lblNewLabel_8_5);
		
		JLabel lblNewLabel_8_6 = new JLabel("");
		lblNewLabel_8_6.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/imagenes/dot.png")));
		lblNewLabel_8_6.setBounds(837, 271, 10, 14);
		frmComercialmenteMen.getContentPane().add(lblNewLabel_8_6);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.LIGHT_GRAY);
		separator_1.setBackground(Color.LIGHT_GRAY);
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(552, 154, 9, 122);
		frmComercialmenteMen.getContentPane().add(separator_1);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBackground(Color.LIGHT_GRAY);
		separator_1_1.setForeground(Color.LIGHT_GRAY);
		separator_1_1.setOrientation(SwingConstants.VERTICAL);
		separator_1_1.setBounds(807, 158, 17, 122);
		frmComercialmenteMen.getContentPane().add(separator_1_1);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(Color.LIGHT_GRAY);
		separator_1_2.setBackground(Color.LIGHT_GRAY);
		separator_1_2.setOrientation(SwingConstants.VERTICAL);
		separator_1_2.setBounds(285, 154, 8, 197);
		frmComercialmenteMen.getContentPane().add(separator_1_2);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.LIGHT_GRAY);
		separator_2.setBackground(Color.LIGHT_GRAY);
		separator_2.setBounds(285, 200, 25, 2);
		frmComercialmenteMen.getContentPane().add(separator_2);
		
		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setForeground(Color.LIGHT_GRAY);
		separator_2_1.setBackground(Color.LIGHT_GRAY);
		separator_2_1.setBounds(285, 275, 25, 2);
		frmComercialmenteMen.getContentPane().add(separator_2_1);
		
		JSeparator separator_2_2 = new JSeparator();
		separator_2_2.setForeground(Color.LIGHT_GRAY);
		separator_2_2.setBackground(Color.LIGHT_GRAY);
		separator_2_2.setBounds(285, 351, 25, 2);
		frmComercialmenteMen.getContentPane().add(separator_2_2);
		
		JSeparator separator_2_2_1 = new JSeparator();
		separator_2_2_1.setForeground(Color.LIGHT_GRAY);
		separator_2_2_1.setBackground(Color.LIGHT_GRAY);
		separator_2_2_1.setBounds(553, 275, 28, 2);
		frmComercialmenteMen.getContentPane().add(separator_2_2_1);
		
		JSeparator separator_2_2_2 = new JSeparator();
		separator_2_2_2.setForeground(Color.LIGHT_GRAY);
		separator_2_2_2.setBackground(Color.LIGHT_GRAY);
		separator_2_2_2.setBounds(553, 200, 25, 2);
		frmComercialmenteMen.getContentPane().add(separator_2_2_2);
		
		JSeparator separator_2_2_1_1 = new JSeparator();
		separator_2_2_1_1.setForeground(Color.LIGHT_GRAY);
		separator_2_2_1_1.setBackground(Color.LIGHT_GRAY);
		separator_2_2_1_1.setBounds(809, 278, 28, 2);
		frmComercialmenteMen.getContentPane().add(separator_2_2_1_1);
		
		JSeparator separator_2_2_1_2 = new JSeparator();
		separator_2_2_1_2.setForeground(Color.LIGHT_GRAY);
		separator_2_2_1_2.setBackground(Color.LIGHT_GRAY);
		separator_2_2_1_2.setBounds(809, 200, 28, 2);
		frmComercialmenteMen.getContentPane().add(separator_2_2_1_2);
		
		JSeparator separator_2_2_2_1_2 = new JSeparator();
		separator_2_2_2_1_2.setForeground(Color.LIGHT_GRAY);
		separator_2_2_2_1_2.setBackground(Color.DARK_GRAY);
		separator_2_2_2_1_2.setBounds(256, 78, 759, 2);
		frmComercialmenteMen.getContentPane().add(separator_2_2_2_1_2);
		
		JSeparator separator_2_2_2_1_2_1 = new JSeparator();
		separator_2_2_2_1_2_1.setForeground(Color.LIGHT_GRAY);
		separator_2_2_2_1_2_1.setBackground(Color.DARK_GRAY);
		separator_2_2_2_1_2_1.setBounds(255, 406, 759, 2);
		frmComercialmenteMen.getContentPane().add(separator_2_2_2_1_2_1);
		
		JLabel lblNewLabel_9 = new JLabel("ACCESO R\u00C1PIDO");
		lblNewLabel_9.setForeground(new Color(0, 51, 0));
		lblNewLabel_9.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/imagenes/search.png")));
		lblNewLabel_9.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_9.setBounds(269, 457, 205, 45);
		lblNewLabel_9.setHorizontalTextPosition(SwingConstants.LEFT);
		frmComercialmenteMen.getContentPane().add(lblNewLabel_9);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(Color.LIGHT_GRAY);
		separator_3.setBackground(Color.DARK_GRAY);
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(255, 78, 2, 330);
		frmComercialmenteMen.getContentPane().add(separator_3);
		
		JSeparator separator_3_1 = new JSeparator();
		separator_3_1.setForeground(Color.LIGHT_GRAY);
		separator_3_1.setBackground(Color.DARK_GRAY);
		separator_3_1.setOrientation(SwingConstants.VERTICAL);
		separator_3_1.setBounds(1013, 78, 2, 330);
		frmComercialmenteMen.getContentPane().add(separator_3_1);
		
		JSeparator separator_2_2_2_1_2_2 = new JSeparator();
		separator_2_2_2_1_2_2.setForeground(Color.LIGHT_GRAY);
		separator_2_2_2_1_2_2.setBackground(Color.DARK_GRAY);
		separator_2_2_2_1_2_2.setBounds(256, 419, 757, 2);
		frmComercialmenteMen.getContentPane().add(separator_2_2_2_1_2_2);
		
		JSeparator separator_2_2_2_1_2_2_1 = new JSeparator();
		separator_2_2_2_1_2_2_1.setForeground(Color.LIGHT_GRAY);
		separator_2_2_2_1_2_2_1.setBackground(Color.DARK_GRAY);
		separator_2_2_2_1_2_2_1.setBounds(255, 540, 757, 2);
		frmComercialmenteMen.getContentPane().add(separator_2_2_2_1_2_2_1);
		
		JSeparator separator_3_2 = new JSeparator();
		separator_3_2.setOrientation(SwingConstants.VERTICAL);
		separator_3_2.setForeground(Color.LIGHT_GRAY);
		separator_3_2.setBackground(Color.DARK_GRAY);
		separator_3_2.setBounds(255, 419, 4, 122);
		frmComercialmenteMen.getContentPane().add(separator_3_2);
		
		JSeparator separator_3_2_1 = new JSeparator();
		separator_3_2_1.setOrientation(SwingConstants.VERTICAL);
		separator_3_2_1.setForeground(Color.LIGHT_GRAY);
		separator_3_2_1.setBackground(Color.DARK_GRAY);
		separator_3_2_1.setBounds(1011, 420, 4, 122);
		frmComercialmenteMen.getContentPane().add(separator_3_2_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(29, 367, 173, 19);
		frmComercialmenteMen.getContentPane().add(passwordField);
		
		btnHojaDeRuta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrinterJob job = PrinterJob.getPrinterJob();
				comercio.getArchivoRemitos().cargarRemitos_porFecha(new Date());
				remitos_dehoy = comercio.getArchivoRemitos().getRemitos();
				SwingUtilities.invokeLater(new Runnable() {			
					@Override
					public void run() {
						HojaDeRutaImp hojaruta_imp = new HojaDeRutaImp(comercio, remitos_dehoy);
						job.setPrintable(hojaruta_imp);
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
				ArrayList<Remito> remitos_40 = new ArrayList<Remito>();
				for (int i = 35; i < remitos_dehoy.size(); i++) {
					remitos_40.add(remitos_dehoy.get(i));
					contador++;
					if (contador == 40 || remitos_dehoy.indexOf(remitos_dehoy.get(i)) == remitos_dehoy.size()-1) {
						contador = 0;
						SwingUtilities.invokeLater(new Runnable() {					
							@Override
							public void run() {
								HojaDeRutaImp_onlyTable hojaruta_imp = new HojaDeRutaImp_onlyTable(comercio, remitos_40);
								job.setPrintable(hojaruta_imp);
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
						remitos_40.clear();
					}
				}
			}
		});
		btnHojaDeRuta.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/imagenes/print.png")));
		btnHojaDeRuta.setFont(new Font("SansSerif", Font.PLAIN, 11));
		btnHojaDeRuta.setEnabled(false);
		btnHojaDeRuta.setBounds(824, 345, 180, 50);
		frmComercialmenteMen.getContentPane().add(btnHojaDeRuta);
		
		tglbtnNewToggleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Component[] componentes = frmComercialmenteMen.getContentPane().getComponents();
				if (tglbtnNewToggleButton.isSelected()) {
					frmComercialmenteMen.getContentPane().setBackground(Color.DARK_GRAY);
					for (int i = 0; i<componentes.length; i++) {
						if ((componentes[i] instanceof JSeparator) || (componentes[i] instanceof JLabel)) {
							componentes[i].setBackground(SystemColor.WHITE);
							componentes[i].setForeground(SystemColor.WHITE);
						}
					}
				}
				else {
					frmComercialmenteMen.getContentPane().setBackground(SystemColor.controlHighlight);
					for (int i = 0; i<componentes.length; i++) {
						if ((componentes[i] instanceof JSeparator)) {
							componentes[i].setBackground(SystemColor.BLACK);
							componentes[i].setForeground(SystemColor.GRAY);
						}
						if (componentes[i] instanceof JLabel){
							componentes[i].setBackground(SystemColor.BLACK);
							componentes[i].setForeground(SystemColor.BLACK);
						}
					}
				}
				
			}
		});

		comercio.getStock().calcularFaltas();
		if (comercio.getStock().faltaStock()) {
			bStock.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/imagenes/faltastock.png")));
		}
		else {
			bStock.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/imagenes/accept1.png")));
		}
		
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
		
		frmComercialmenteMen.setVisible(true);
		//BORRAR DESPUES
	}
}
