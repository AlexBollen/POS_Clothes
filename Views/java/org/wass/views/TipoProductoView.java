package org.wass.views;


import javax.swing.*;

import org.wass.controllers.product.TipoProductoController;
import org.wass.models.product.TipoProductoModel;

import javax.swing.table.DefaultTableModel;


public class TipoProductoView extends JFrame {
    private TipoProductoController tipoProductoController;
    private DefaultTableModel tipoProductoTableModel;
    private JTable tipoProductoTable;
    private JTextField nombreTipoField;
    private JButton agregarButton, actualizarButton, eliminarButton;
    int idProducto;

    public TipoProductoView(TipoProductoController tipoProductoController) {
        this.tipoProductoController = tipoProductoController;

        initComponents();
        setLocationRelativeTo(null);
        loadTipoProductos();
    }

    private void initComponents() {
        // Panel y layout
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Tabla de tipos de productos
        tipoProductoTableModel = new DefaultTableModel(new String[]{"ID", "Nombre"}, 0);
        tipoProductoTable = new JTable(tipoProductoTableModel);
        JScrollPane scrollPane = new JScrollPane(tipoProductoTable);
        scrollPane.setBounds(20, 20, 300, 200);
        panel.add(scrollPane);

        // Campo de entrada para nombre
        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setBounds(20, 230, 100, 25);
        panel.add(nombreLabel);

        nombreTipoField = new JTextField();
        nombreTipoField.setBounds(120, 230, 200, 25);
        panel.add(nombreTipoField);

        // Botones
        agregarButton = new JButton("Agregar");
        agregarButton.setBounds(20, 270, 100, 30);
        panel.add(agregarButton);

        actualizarButton = new JButton("Actualizar");
        actualizarButton.setBounds(130, 270, 100, 30);
        panel.add(actualizarButton);

        eliminarButton = new JButton("Eliminar");
        eliminarButton.setBounds(240, 270, 100, 30);
        panel.add(eliminarButton);

        // Agregar panel al frame
        this.add(panel);
        this.setSize(350, 350);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Agregar listeners para botones
        agregarButton.addActionListener(e -> agregarTipoProducto());
        actualizarButton.addActionListener(e -> actualizarTipoProducto());
        eliminarButton.addActionListener(e -> eliminarTipoProducto());

        //Listener cuando se seleccione un elemento de la tabla
        tipoProductoTable.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && tipoProductoTable.getSelectedRow() != -1) {
                // Obtener la fila seleccionada
                int selectedRow = tipoProductoTable.getSelectedRow();

                // Cargar los valores en los campos de texto y ComboBox
                idProducto = (int) tipoProductoTable.getValueAt(selectedRow, 0);
                nombreTipoField.setText(tipoProductoTable.getValueAt(selectedRow, 1).toString());
            }
        });
    }

    private void loadTipoProductos() {
        tipoProductoTableModel.setRowCount(0); // Limpiar tabla
        tipoProductoController.obtenerTiposProducto().forEach(tipoProducto -> {
            tipoProductoTableModel.addRow(new Object[]{
                    tipoProducto.getIdTipoProducto(),
                    tipoProducto.getNombreTipoProducto()
            });
        });
    }

    private void agregarTipoProducto() {
        String nombre = nombreTipoField.getText();
        tipoProductoController.agregarTipoProducto(new TipoProductoModel(nombre,true));
        loadTipoProductos(); // Recargar tipos
        nombreTipoField.setText("");
    }

    private void actualizarTipoProducto() {
        int filaSeleccionada = tipoProductoTable.getSelectedRow();
        if (filaSeleccionada >= 0) {
            int idTipoProducto = (int) tipoProductoTableModel.getValueAt(filaSeleccionada, 0);
            String nombre = nombreTipoField.getText();
            tipoProductoController.actualizarTipoProducto(nombre, idTipoProducto);
            loadTipoProductos(); // Recargar tipos
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un tipo de producto a actualizar.");
        }
    }

    private void eliminarTipoProducto() {
        int filaSeleccionada = tipoProductoTable.getSelectedRow();
        if (filaSeleccionada >= 0) {
            int idTipoProducto = (int) tipoProductoTableModel.getValueAt(filaSeleccionada, 0);
            tipoProductoController.eliminarTipoProducto(idTipoProducto);
            loadTipoProductos(); // Recargar tipos
            nombreTipoField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un tipo de producto a eliminar.");
        }
    }
}
