package packages;

import org.wass.controllers.product.ProductoController;
import org.wass.controllers.purchase.CompraController;
import org.wass.controllers.purchase.EstadoCompraController;
import org.wass.controllers.purchase.ProveedorController;
import org.wass.controllers.purchase.TipoPagoController;
import org.wass.models.product.ProductoModel;
import org.wass.models.purchase.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class CompraView extends JFrame{
    private ProductoController productoController;
    private ProveedorController proveedorController;
    private TipoPagoController tipoPagoController;
    private EstadoCompraController estadoCompraController;
    private CompraController compraController;

    private DefaultTableModel compraTableModel;
    private JTable compraTable;
    private JTable detallesTable;
    private JComboBox<ProductoModel> productoComboBox;
    private JComboBox<ProveedorModel> proveedorComboBox;
    private JComboBox<TipoPagoModel> tipoPagoComboBox;
    private JComboBox<EstadoCompraModel> estadoCompraComboBox;

    private JTextField cantidadField, descripcionField, cantidadPedidaField, cantidadRecibidaField, totalCompraField;
    private JButton agregarProductoButton, eliminarProductoButton, finalizarCompraButton;

    public CompraView(ProductoController productoController, ProveedorController proveedorController,
                      TipoPagoController tipoPagoController, EstadoCompraController estadoCompraController,
                      CompraController compraController) {
        this.productoController = productoController;
        this.proveedorController = proveedorController;
        this.tipoPagoController = tipoPagoController;
        this.estadoCompraController = estadoCompraController;
        this.compraController = compraController;

        initComponents();
        setLocationRelativeTo(null); // Centra la ventana
        loadProductos();
        loadProveedores();
        loadTipoPago();
        loadEstadoCompra();
    }

    private void initComponents() {
        // Panel y layout
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Campos para la compra
        JLabel descripcionLabel = new JLabel("Descripción:");
        descripcionLabel.setBounds(20, 20, 100, 25);
        panel.add(descripcionLabel);
        descripcionField = new JTextField();
        descripcionField.setBounds(120, 20, 200, 25);
        panel.add(descripcionField);

        JLabel cantidadPedidaLabel = new JLabel("Cantidad Pedida:");
        cantidadPedidaLabel.setBounds(20, 50, 120, 25);
        panel.add(cantidadPedidaLabel);
        cantidadPedidaField = new JTextField();
        cantidadPedidaField.setBounds(150, 50, 170, 25);
        panel.add(cantidadPedidaField);
        cantidadPedidaField.setEnabled(false);

        JLabel cantidadRecibidaLabel = new JLabel("Cantidad Recibida:");
        cantidadRecibidaLabel.setBounds(20, 80, 120, 25);
        panel.add(cantidadRecibidaLabel);
        cantidadRecibidaField = new JTextField();
        cantidadRecibidaField.setBounds(150, 80, 170, 25);
        panel.add(cantidadRecibidaField);

        JLabel totalCompraLabel = new JLabel("Total Compra:");
        totalCompraLabel.setBounds(20, 110, 100, 25);
        panel.add(totalCompraLabel);
        totalCompraField = new JTextField();
        totalCompraField.setBounds(120, 110, 200, 25);
        panel.add(totalCompraField);
        totalCompraField.setEnabled(false);

        // ComboBox para seleccionar Proveedor
        JLabel proveedorLabel = new JLabel("Proveedor:");
        proveedorLabel.setBounds(20, 140, 100, 25);
        panel.add(proveedorLabel);
        proveedorComboBox = new JComboBox<>();
        proveedorComboBox.setBounds(120, 140, 200, 25);
        panel.add(proveedorComboBox);

        // ComboBox para seleccionar Tipo de Pago
        JLabel tipoPagoLabel = new JLabel("Tipo de Pago:");
        tipoPagoLabel.setBounds(20, 170, 100, 25);
        panel.add(tipoPagoLabel);
        tipoPagoComboBox = new JComboBox<>();
        tipoPagoComboBox.setBounds(120, 170, 200, 25);
        panel.add(tipoPagoComboBox);

        // ComboBox para seleccionar Estado de la Compra
        JLabel estadoCompraLabel = new JLabel("Estado Compra:");
        estadoCompraLabel.setBounds(20, 200, 120, 25);
        panel.add(estadoCompraLabel);
        estadoCompraComboBox = new JComboBox<>();
        estadoCompraComboBox.setBounds(150, 200, 170, 25);
        panel.add(estadoCompraComboBox);

        // Tabla para productos agregados a la compra
        compraTableModel = new DefaultTableModel(new String[]{"ID", "Nombre", "Cantidad", "Precio Unitario", "Total"}, 0);
        compraTable = new JTable(compraTableModel);
        JScrollPane scrollPane = new JScrollPane(compraTable);
        scrollPane.setBounds(20, 230, 600, 200);
        panel.add(scrollPane);

        // ComboBox para seleccionar el producto
        JLabel productoLabel = new JLabel("Producto:");
        productoLabel.setBounds(20, 450, 100, 25);
        panel.add(productoLabel);

        productoComboBox = new JComboBox<>();
        productoComboBox.setBounds(120, 450, 200, 25);
        panel.add(productoComboBox);

        // Campo para ingresar la cantidad del producto
        JLabel cantidadLabel = new JLabel("Cantidad:");
        cantidadLabel.setBounds(20, 480, 100, 25);
        panel.add(cantidadLabel);

        cantidadField = new JTextField();
        cantidadField.setBounds(120, 480, 200, 25);
        panel.add(cantidadField);

        // Botones
        agregarProductoButton = new JButton("Agregar");
        agregarProductoButton.setBounds(340, 450, 100, 30);
        panel.add(agregarProductoButton);

        eliminarProductoButton = new JButton("Eliminar");
        eliminarProductoButton.setBounds(340, 480, 100, 30);
        panel.add(eliminarProductoButton);

        finalizarCompraButton = new JButton("Finalizar Compra");
        finalizarCompraButton.setBounds(240, 530, 150, 30);
        panel.add(finalizarCompraButton);

        // Agregar panel al frame
        this.add(panel);
        this.setSize(650, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Agregar listeners para botones
        agregarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarProducto();
            }
        });

        eliminarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProducto();
            }
        });

        finalizarCompraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalizarCompra();
            }
        });
    }

    private void loadProductos() {
        productoComboBox.removeAllItems();
        try {
            List<ProductoModel> productos = productoController.obtenerProductos();
            for (ProductoModel producto : productos) {
                productoComboBox.addItem(producto); // Agrega los productos al ComboBox
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar productos: " + e.getMessage());
        }
    }

    private void loadProveedores() {
        proveedorComboBox.removeAllItems();
        try {
            List<ProveedorModel> proveedores = proveedorController.obtenerProveedores();
            for (ProveedorModel proveedor : proveedores) {
                proveedorComboBox.addItem(proveedor); // Agrega los proveedores al ComboBox
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar proveedores: " + e.getMessage());
        }
    }

    private void loadTipoPago() {
        tipoPagoComboBox.removeAllItems();
        try {
            List<TipoPagoModel> tipoPagoList = tipoPagoController.obtenerTiposPago();
            for (TipoPagoModel tipoPago : tipoPagoList) {
                tipoPagoComboBox.addItem(tipoPago); // Agrega los tipos de pago al ComboBox
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar tipos de pago: " + e.getMessage());
        }
    }

    private void loadEstadoCompra() {
        estadoCompraComboBox.removeAllItems();
        try {
            List<EstadoCompraModel> estadosCompra = estadoCompraController.obtenerEstadosCompra();
            for (EstadoCompraModel estadoCompra : estadosCompra) {
                estadoCompraComboBox.addItem(estadoCompra); // Agrega los estados de la compra al ComboBox
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar estados de compra: " + e.getMessage());
        }
    }

    private void agregarProducto() {
        ProductoModel productoSeleccionado = (ProductoModel) productoComboBox.getSelectedItem();
        int cantidad = Integer.parseInt(cantidadField.getText());
        float precioUnitario = productoSeleccionado.getPrecioVenta();
        float total = cantidad * precioUnitario;
        compraTableModel.addRow(new Object[]{productoSeleccionado.getIdProducto(), productoSeleccionado.getNombreProducto(), cantidad, precioUnitario, total});
        totalCompraField.setText(String.valueOf(obtenerTotalDeCompra()));
        cantidadPedidaField.setText(String.valueOf(obtenerCantidadSolicitada()));
        cantidadField.setText("");
    }

    private void eliminarProducto() {
        int selectedRow = compraTable.getSelectedRow();
        if (selectedRow >= 0) {
            compraTableModel.removeRow(selectedRow);
            totalCompraField.setText(String.valueOf(obtenerTotalDeCompra()));
            cantidadPedidaField.setText(String.valueOf(obtenerCantidadSolicitada()));
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un producto para eliminar.");
        }
    }

    private void finalizarCompra() {
        ProveedorModel proveedorSeleccionado = (ProveedorModel) proveedorComboBox.getSelectedItem();
        int idProveedor = proveedorSeleccionado.getIdProveedor();
        //JOptionPane.showMessageDialog(this, "IdProveedor: "+idProveedor);

        TipoPagoModel tipopagoSeleccionado = (TipoPagoModel) tipoPagoComboBox.getSelectedItem();
        int idTipoPago = tipopagoSeleccionado.getIdTipoPago();
        //JOptionPane.showMessageDialog(this, "IdTipopago: "+idTipoPago);

        EstadoCompraModel estadoCompraSeleccionado = (EstadoCompraModel) estadoCompraComboBox.getSelectedItem();
        int idEstadoCompra = estadoCompraSeleccionado.getIdEstadoCompra();
        //JOptionPane.showMessageDialog(this, "IdEstadoCompra: "+idEstadoCompra);
        CompraModel compra = null;
        try {
            compra = new CompraModel("",0,0,(float)0,0,0,0);
            compra.setDescripcionCompra(descripcionField.getText());
            compra.setCantidadPedida(obtenerCantidadSolicitada());
            compra.setCantidadRecibida(Integer.parseInt(cantidadRecibidaField.getText()));
            compra.setTotalCompra(obtenerTotalDeCompra());
            compra.setIdProveedor(idProveedor);
            compra.setIdTipoPago(idTipoPago);
            compra.setIdEstadoCompra(idEstadoCompra);


            boolean resultado = compraController.agregarCompra(compra, obtenerListaDetalle());

            if (resultado) {
                JOptionPane.showMessageDialog(this, "Compra guardada exitosamente.");
                limpiarDatos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar la compra.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
    private List<DetalleCompraModel> obtenerListaDetalle(){
        List<DetalleCompraModel> listaDetalles = new ArrayList<>();

        // Iterar sobre las filas de la tabla
        for (int row = 0; row < compraTable.getRowCount(); row++) {

            int idProducto = (int) compraTable.getValueAt(row, 0);
            int cantidad = (int) compraTable.getValueAt(row, 2); // Cantidad

            DetalleCompraModel detalle = new DetalleCompraModel(0, idProducto, cantidad);
            listaDetalles.add(detalle);
        }
        return listaDetalles;
    }
    private float obtenerTotalDeCompra(){
        float total = (float) 0;
        for (int row = 0; row < compraTable.getRowCount(); row++) {
            total += (float) compraTable.getValueAt(row, 4); // Total
        }
        return total;
    }
    private int obtenerCantidadSolicitada(){
        int cantidad = 0;
        for (int row = 0; row < compraTable.getRowCount(); row++) {
            cantidad += (int) compraTable.getValueAt(row, 2);
        }
        return cantidad;
    }
    private void limpiarDatos(){
        descripcionField.setText("");
        cantidadPedidaField.setText("");
        cantidadRecibidaField.setText("");
        totalCompraField.setText("");
        cantidadField.setText("");
        compraTableModel.setRowCount(0);
    }
}
