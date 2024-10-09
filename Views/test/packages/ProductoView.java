package packages;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.wass.controllers.Product.ProductoController;
import org.wass.controllers.Product.TipoProductoController;
import org.wass.models.product.TipoProductoModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import org.wass.models.product.ProductoModel;

public class ProductoView extends JFrame {
    private ProductoController productoController;
    private TipoProductoController tipoProductoController;
    private DefaultTableModel productTableModel;
    private JTable productTable;
    private JComboBox<TipoProductoModel> tipoProductoComboBox;
    private JButton agregarProductoButton, actualizarProductoButton, eliminarProductoButton;
    private JTextField nombreProductoField, precioCostoField, precioVentaField, descuentoField;
    private int idProducto;


    public ProductoView(ProductoController productoController, TipoProductoController tipoProductoController) {
        this.productoController = productoController;
        this.tipoProductoController = tipoProductoController;

        initComponents();
        setLocationRelativeTo(null); // Centra la ventana
        loadProducts();
        loadTipoProductos();
    }

    private void initComponents() {
        // Panel y layout
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Tabla de productos
        productTableModel = new DefaultTableModel(new String[]{"ID", "Nombre", "Precio Costo", "Precio Venta", "Descuento", "Tipo Producto"}, 0);
        productTable = new JTable(productTableModel);
        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setBounds(20, 20, 600, 200);
        panel.add(scrollPane);

        // Campos de entrada para producto
        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setBounds(20, 230, 100, 25);
        panel.add(nombreLabel);

        nombreProductoField = new JTextField();
        nombreProductoField.setBounds(120, 230, 200, 25);
        panel.add(nombreProductoField);

        JLabel precioCostoLabel = new JLabel("Precio Costo:");
        precioCostoLabel.setBounds(20, 260, 100, 25);
        panel.add(precioCostoLabel);

        precioCostoField = new JTextField();
        precioCostoField.setBounds(120, 260, 200, 25);
        panel.add(precioCostoField);

        JLabel precioVentaLabel = new JLabel("Precio Venta:");
        precioVentaLabel.setBounds(20, 290, 100, 25);
        panel.add(precioVentaLabel);

        precioVentaField = new JTextField();
        precioVentaField.setBounds(120, 290, 200, 25);
        panel.add(precioVentaField);

        JLabel descuentoLabel = new JLabel("Descuento:");
        descuentoLabel.setBounds(20, 320, 100, 25);
        panel.add(descuentoLabel);

        descuentoField = new JTextField();
        descuentoField.setBounds(120, 320, 200, 25);
        panel.add(descuentoField);

        // ComboBox para seleccionar el tipo de producto
        JLabel tipoProductoLabel = new JLabel("Tipo Producto:");
        tipoProductoLabel.setBounds(20, 350, 100, 25);
        panel.add(tipoProductoLabel);

        tipoProductoComboBox = new JComboBox<>();
        tipoProductoComboBox.setBounds(120, 350, 200, 25);
        panel.add(tipoProductoComboBox);

        // Botones
        agregarProductoButton = new JButton("Agregar");
        agregarProductoButton.setBounds(20, 390, 100, 30);
        panel.add(agregarProductoButton);

        actualizarProductoButton = new JButton("Actualizar");
        actualizarProductoButton.setBounds(130, 390, 100, 30);
        panel.add(actualizarProductoButton);

        eliminarProductoButton = new JButton("Eliminar");
        eliminarProductoButton.setBounds(240, 390, 100, 30);
        panel.add(eliminarProductoButton);

        // Agregar panel al frame
        this.add(panel);
        this.setSize(650, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Agregar listeners para botones
        agregarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarProducto();
            }
        });

        actualizarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarProducto();
            }
        });

        eliminarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProducto();
            }
        });

        productTable.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && productTable.getSelectedRow() != -1) {
                // Obtener la fila seleccionada
                int selectedRow = productTable.getSelectedRow();

                // Cargar los valores en los campos de texto y ComboBox
                idProducto = (int) productTableModel.getValueAt(selectedRow, 0);
                nombreProductoField.setText(productTableModel.getValueAt(selectedRow, 1).toString());
                precioCostoField.setText(productTableModel.getValueAt(selectedRow, 2).toString());
                precioVentaField.setText(productTableModel.getValueAt(selectedRow, 3).toString());
                descuentoField.setText(productTableModel.getValueAt(selectedRow, 4).toString());

                // Seleccionar el TipoProducto en el ComboBox (Basado en el nombre del TipoProducto)
                String tipoProductoNombre = productTableModel.getValueAt(selectedRow, 5).toString();
                for (int i = 0; i < tipoProductoComboBox.getItemCount(); i++) {
                    if (tipoProductoComboBox.getItemAt(i).toString().equals(tipoProductoNombre)) {
                        tipoProductoComboBox.setSelectedIndex(i);
                        break;
                    }
                }
            }
        });

    }

    private void loadProducts() {
        productTableModel.setRowCount(0); // Limpiar la tabla antes de cargar
        try {
            List<ProductoModel> productos = productoController.obtenerProductos();
            TipoProductoModel tipoProductoBuscado;
            for (ProductoModel producto : productos) {
                tipoProductoBuscado = tipoProductoController.obtenerTipoProducto(producto.getIdTipoProducto()); // Se envía el id del TipoProducto para obtener su nombre
                productTableModel.addRow(new Object[]{
                        producto.getIdProducto(),
                        producto.getNombreProducto(),
                        producto.getPrecioCosto(),
                        producto.getPrecioVenta(),
                        producto.getDescuentoPorcentual(),
                        tipoProductoBuscado.getNombreTipoProducto()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar productos: " + e.getMessage());
        }
    }

    private void loadTipoProductos() {
        tipoProductoComboBox.removeAllItems();
        List<TipoProductoModel> tipos = tipoProductoController.obtenerTiposProducto();
        for (TipoProductoModel tipo : tipos) {
            tipoProductoComboBox.addItem(tipo); // Agrega el objeto tipoProducto directamente
        }
    }

    private void agregarProducto() {
        // Obtener valores desde los campos y comboBox
        String nombre = nombreProductoField.getText();
        float precioCosto = Float.parseFloat(precioCostoField.getText());
        float precioVenta = Float.parseFloat(precioVentaField.getText());
        float descuento = Float.parseFloat(descuentoField.getText());
        TipoProductoModel tipoProductoSeleccionado = (TipoProductoModel) tipoProductoComboBox.getSelectedItem();

        productoController.agregarProducto(new ProductoModel(nombre, precioCosto, precioVenta, descuento, tipoProductoSeleccionado.getIdTipoProducto()));
        loadProducts(); // Recargar productos
        limpiarDatos();
    }

    private void actualizarProducto() {
        int filaSeleccionada = productTable.getSelectedRow();
        if (filaSeleccionada >= 0) {
            int idProducto = (int) productTableModel.getValueAt(filaSeleccionada, 0);
            String nombre = nombreProductoField.getText();
            float precioCosto = Float.parseFloat(precioCostoField.getText());
            float precioVenta = Float.parseFloat(precioVentaField.getText());
            float descuento = Float.parseFloat(descuentoField.getText());
            TipoProductoModel tipoProductoSeleccionado = (TipoProductoModel) tipoProductoComboBox.getSelectedItem();

            productoController.actualizarProducto(new ProductoModel(nombre, precioCosto, precioVenta, descuento, tipoProductoSeleccionado.getIdTipoProducto()),idProducto);
            loadProducts(); // Recargar productos
            limpiarDatos(); // Limpiar Datos
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto a actualizar.");
        }
    }

    private void eliminarProducto() {
        int filaSeleccionada = productTable.getSelectedRow();
        if (filaSeleccionada >= 0) {
            int idProducto = (int) productTableModel.getValueAt(filaSeleccionada, 0);
            productoController.eliminarProducto(idProducto);
            loadProducts(); // Recargar productos
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto a eliminar.");
        }
    }

    private void limpiarDatos(){
        idProducto=0;
        nombreProductoField.setText("");
        precioCostoField.setText("");
        precioVentaField.setText("");
        descuentoField.setText("");

    }
}
