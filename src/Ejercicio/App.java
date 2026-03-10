package Ejercicio;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;

/* FUNCIONAMIENTO:
 * 
 * · Visualizar todos los registros : Cuando se abre la aplicación se muestran todos los registros en un JTable
 * · Insertar un registro nuevo: Se relena el formulario y se pulsa el botón guardar. Se guarda en la base de datos y se muestra el nuevo dato en la tabla.
 * · Eliminar un registro: Se selecciona el registro deseado sobre la JTable y aparecera un botón para Eliminar. Se elimina en la base de datos y se quitará el dato de la tabla.
 * · Mostrar registros que cumplan una condición: En el Menu bar se debe seleccionar "Buscar registro" y aparecera el botón de buscar además de  varios checkbox. 
 * 					Esto funciona de forma que se rellena el campo del dato que se deba buscar. Si se marca el checkbox buscara que la palabra este dentro del campo.
 * · Mostrar cuántos registros cumplen una determinada condición: Debajo de la Jtabla, hay un JLabel que mostrara siempre el número de registros que se muestran en la tabla.
 * 
 * · PLUS: También se pueden editar los datos ya guardados cuando se pulsa sobre un registro en la tabla.
 * 
 */

public class App extends JFrame {
	
	//Declaraciones
	String nombresColumnas[] = {"id", "Titulo", "Autor", "Descripción", "País", "Código"};
	static ArrayList<Libros> libros = new ArrayList<Libros>();
	private int idLibro_Edit;
	private boolean ventanaBuscar = false;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField txtTitulo;
	private JTextField txtAutor;
	private JTextField txtPais;
	private JTextField txtCodigo;
	private JTextArea taDescripcion;
	private JLabel lblTitulo;
	private JLabel lblAutor;
	private JLabel lblDescripcion;
	private JLabel lblPais;
	private JLabel lblCodigo;
	private JButton btnGuardar;
	private JButton btnActualizar;
	private JButton btnCancelar;
	private JButton btnBorrar;
	private JLabel lblNFilas;
	private JMenuBar menuBar;
	private JLabel lblFiltroTexto_1;
	private JLabel lblFiltroTexto_2;
	private JButton btnBuscar;
	private JCheckBox chckbxTitulo;
	private JCheckBox chckbxAutor;
	private JCheckBox chckbxDescripcion;
	private JCheckBox chckbxPais;
	private JCheckBox chckbxCodigo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App frame = new App();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public App() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNFilas = new JLabel("N filas mostradas");
		lblNFilas.setBounds(256, 440, 518, 14);
		contentPane.add(lblNFilas);
		
		table = new JTable();
		//DefaultTableModel modelTable = (DefaultTableModel) table.getModel();
		DefaultTableModel modelTable = new DefaultTableModel();
		//"Dibujar" datos tabla
		modelTable.setColumnIdentifiers(nombresColumnas);			
		table.setModel(modelTable);
		
		ActualizarTable(false, modelTable);
		//ocultar idLibro
        table.removeColumn(table.getColumnModel().getColumn(0));
        
        // Evento si selecciona una fila
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // Para evitar múltiples eventos al seleccionar
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) { // Verifica que haya una fila seleccionada
                    	idLibro_Edit = Integer.parseInt(table.getModel().getValueAt(selectedRow, 0).toString());
                        txtTitulo.setText(table.getModel().getValueAt(selectedRow, 1).toString());
                        txtAutor.setText(table.getModel().getValueAt(selectedRow, 2).toString());
                        taDescripcion.setText(table.getModel().getValueAt(selectedRow, 3).toString());
                        txtPais.setText(table.getModel().getValueAt(selectedRow, 4).toString());
                        txtCodigo.setText(table.getModel().getValueAt(selectedRow, 5).toString());
                        
                        BotonesEditar();
                    }
                }
            }
        });
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(256, 37, 518, 402);
		contentPane.add(scrollPane);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtTitulo.getText().isEmpty() || txtCodigo.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Es obligatorio poner Título y el Código", "¡Aviso!", JOptionPane.INFORMATION_MESSAGE);
				}else {
					ConexionDB.addLibroBBDD(txtTitulo.getText(), txtAutor.getText(), taDescripcion.getText(), txtPais.getText(), txtCodigo.getText());
					ActualizarTable(false, modelTable);
					LimpiarFormulario();
				}
				
				
				
			}
		});
		btnGuardar.setBounds(6, 395, 150, 23);
		contentPane.add(btnGuardar);
		
		txtTitulo = new JTextField();
		txtTitulo.setBounds(6, 63, 150, 30);
		contentPane.add(txtTitulo);
		txtTitulo.setColumns(10);
		
		txtAutor = new JTextField();
		txtAutor.setBounds(6, 110, 150, 30);
		contentPane.add(txtAutor);
		txtAutor.setColumns(10);
		
		txtPais = new JTextField();
		txtPais.setBounds(6, 311, 150, 30);
		contentPane.add(txtPais);
		txtPais.setColumns(10);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(6, 358, 150, 30);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		lblTitulo = new JLabel("Título:");
		lblTitulo.setBounds(6, 49, 46, 14);
		contentPane.add(lblTitulo);
		
		lblAutor = new JLabel("Autor:");
		lblAutor.setBounds(6, 96, 46, 14);
		contentPane.add(lblAutor);
		
		lblDescripcion = new JLabel("Descripción:");
		lblDescripcion.setBounds(6, 143, 89, 14);
		contentPane.add(lblDescripcion);
		
		lblPais = new JLabel("País:");
		lblPais.setBounds(6, 297, 46, 14);
		contentPane.add(lblPais);
		
		lblCodigo = new JLabel("Código:");
		lblCodigo.setBounds(6, 344, 46, 14);
		contentPane.add(lblCodigo);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtTitulo.getText().isEmpty() || txtCodigo.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Es obligatorio poner Título y el Código", "¡Aviso!", JOptionPane.INFORMATION_MESSAGE);
				}else {
					ConexionDB.updateLibroBBDD(idLibro_Edit,txtTitulo.getText(), txtAutor.getText(), taDescripcion.getText(), txtPais.getText(), txtCodigo.getText());
					ActualizarTable(false, modelTable);
					LimpiarFormulario();
	                
					if(ventanaBuscar)
						BotonesBuscar();
					else 
						BotonesCrear();
				}
			}
		});
		btnActualizar.setVisible(false);
		btnActualizar.setBounds(6, 395, 150, 23);
		contentPane.add(btnActualizar);
		
		btnCancelar = new JButton("X");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LimpiarFormulario();
				
				if(ventanaBuscar)
					BotonesBuscar();
				else 
					BotonesCrear();
				
			}
		});
		btnCancelar.setForeground(new Color(0, 0, 0));
		btnCancelar.setBackground(new Color(239, 94, 86));
		btnCancelar.setBounds(109, 37, 46, 23);
		btnCancelar.setVisible(false);
		contentPane.add(btnCancelar);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConexionDB.deleteLibroBBDD(idLibro_Edit);
				ActualizarTable(false, modelTable);
				LimpiarFormulario();
				
				if(ventanaBuscar)
					BotonesBuscar();
				else 
					BotonesCrear();
				
				
			}
		});
		btnBorrar.setForeground(new Color(0, 0, 0));
		btnBorrar.setBackground(new Color(255, 0, 0));
		btnBorrar.setBounds(6, 427, 150, 23);
		btnBorrar.setVisible(false);
		contentPane.add(btnBorrar);
		
		menuBar = new JMenuBar();
		menuBar.setBounds(-4, 0, 788, 22);
		contentPane.add(menuBar);
		
		JMenu mnForm = new JMenu("Acción");
		menuBar.add(mnForm);
		
		JMenuItem mntmFormGuardar = new JMenuItem("Guardar registro");
		mntmFormGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaBuscar = false;
				
				LimpiarFormulario();
				BotonesCrear();
			}
		});
		mnForm.add(mntmFormGuardar);
		
		JMenuItem mntmFormBuscar = new JMenuItem("Buscar registro");
		mntmFormBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaBuscar = true;
				
				LimpiarFormulario();
				BotonesBuscar();
			}
		});
		mnForm.add(mntmFormBuscar);
		
		lblFiltroTexto_1 = new JLabel("Debe contener");
		lblFiltroTexto_1.setBounds(165, 37, 95, 14);
		lblFiltroTexto_1.setVisible(false);
		contentPane.add(lblFiltroTexto_1);
		
		lblFiltroTexto_2 = new JLabel("el texto");
		lblFiltroTexto_2.setBounds(182, 47, 78, 14);
		lblFiltroTexto_2.setVisible(false);
		contentPane.add(lblFiltroTexto_2);
		
		chckbxTitulo = new JCheckBox("");
		chckbxTitulo.setBounds(192, 68, 21, 21);
		chckbxTitulo.setVisible(false);
		contentPane.add(chckbxTitulo);
		
		chckbxAutor = new JCheckBox("");
		chckbxAutor.setBounds(192, 115, 21, 21);
		chckbxAutor.setVisible(false);
		contentPane.add(chckbxAutor);
		
		chckbxDescripcion = new JCheckBox("");
		chckbxDescripcion.setBounds(192, 162, 21, 21);
		chckbxDescripcion.setVisible(false);
		contentPane.add(chckbxDescripcion);
		
		chckbxPais = new JCheckBox("");
		chckbxPais.setBounds(192, 316, 21, 21);
		chckbxPais.setVisible(false);
		contentPane.add(chckbxPais);
		
		chckbxCodigo = new JCheckBox("");
		chckbxCodigo.setBounds(192, 363, 21, 21);
		chckbxCodigo.setVisible(false);
		contentPane.add(chckbxCodigo);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActualizarTable(true, modelTable);
			}
		});
		btnBuscar.setBounds(6, 395, 150, 23);
		btnBuscar.setVisible(false);
		contentPane.add(btnBuscar);
		
		taDescripcion = new JTextArea();
		taDescripcion.setLineWrap(true);
		taDescripcion.setWrapStyleWord(true);
		JScrollPane taDescripcionScrollPane = new JScrollPane(taDescripcion);
		taDescripcionScrollPane.setBounds(6, 157, 150, 136);
		contentPane.add(taDescripcionScrollPane);
	}
	
	private void ActualizarTable(boolean opcionLectura, DefaultTableModel m) {
		//Actualizar array
		if(!opcionLectura)
			libros = ConexionDB.getLibrosBBDD();
		else
			libros = ConexionDB.searchLibroBBDD(txtTitulo.getText(), chckbxTitulo.isSelected(), txtAutor.getText(), chckbxAutor.isSelected(), taDescripcion.getText(),
					chckbxDescripcion.isSelected(), txtPais.getText(), chckbxPais.isSelected(), txtCodigo.getText(), chckbxCodigo.isSelected());
		
		//borrar tabla
		m.setRowCount(0);
		
		//Mostrar datos en tabla
		String d1,d2,d3,d4,d5,d6;
		for(Libros l : libros) {
			d1 = Integer.toString(l.getIdLibros());
			d2 = l.getNombre();
			d3 = l.getAutor();
			d4 = l.getDescripcion();
			d5 = l.getPais();
			d6 = l.getCodigo();
			String row[] = {d1,d2,d3,d4,d5,d6};
			m.addRow(row);
		}
		
		ActualizarFilas();
	}
	
	private void LimpiarFormulario() {
		txtTitulo.setText("");
		txtAutor.setText("");
		taDescripcion.setText("");
		txtPais.setText("");
		txtCodigo.setText("");
	}
	
	private void ActualizarFilas() {
		if(table.getRowCount() > 0)
			lblNFilas.setText(table.getRowCount() + " filas mostradas");
		else
			lblNFilas.setText("0 filas mostradas");
		
	}
	
	private void BotonesEditar() {
        btnGuardar.setVisible(false);
        btnActualizar.setVisible(true);
        btnCancelar.setVisible(true);
        btnBorrar.setVisible(true);
        
        btnBuscar.setVisible(false);
	}
	
	private void BotonesCrear() {
        btnGuardar.setVisible(true);
        btnActualizar.setVisible(false);
        btnCancelar.setVisible(false);
        btnBorrar.setVisible(false);
        
        btnBuscar.setVisible(false);
        
        //Texto buscar
        lblFiltroTexto_1.setVisible(false);
        lblFiltroTexto_2.setVisible(false);
        
        //Checkbox
        chckbxTitulo.setVisible(false);
        chckbxAutor.setVisible(false);
        chckbxDescripcion.setVisible(false);
        chckbxPais.setVisible(false);
        chckbxCodigo.setVisible(false);
	}
	
	private void BotonesBuscar() {
        btnGuardar.setVisible(false);
        btnActualizar.setVisible(false);
        btnCancelar.setVisible(false);
        btnBorrar.setVisible(false);
        
        btnBuscar.setVisible(true);
        
        //Texto buscar
        lblFiltroTexto_1.setVisible(true);
        lblFiltroTexto_2.setVisible(true);
        
        //Checkbox
        chckbxTitulo.setVisible(true);
        chckbxTitulo.setSelected(false);
        chckbxAutor.setVisible(true);
        chckbxAutor.setSelected(false);
        chckbxDescripcion.setVisible(true);
        chckbxDescripcion.setSelected(false);
        chckbxPais.setVisible(true);
        chckbxPais.setSelected(false);
        chckbxCodigo.setVisible(true);
        chckbxCodigo.setSelected(false);
	}
}
