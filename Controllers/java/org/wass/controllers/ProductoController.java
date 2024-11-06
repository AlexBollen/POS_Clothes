package org.wass.controllers;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.beans.ExceptionListener;
import java.util.List;

import org.wass.models.TableDataModel;
import org.wass.models.dao.ProductoDAO;
import org.wass.models.ProductoModel;

/**
 * @author SamuelQ
 */
public class ProductoController {
    private ProductoDAO productoDao;
    private ExceptionListener exceptionListener;

    // Constructor para inicializar productoDao    
    public ProductoController() {
        this(new ProductoDAO());
    }
    @Deprecated
    public ProductoController(ProductoDAO productoDao) {
        this.productoDao = productoDao;
    }

    public void setExceptionListener(ExceptionListener exceptionListener) {
        this.exceptionListener = exceptionListener;
    }

    // Método para agregar un nuevo producto
    public boolean agregarProducto(ProductoModel producto) {
        if (producto.getNombreProducto() == null || producto.getNombreProducto().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre del producto es requerido", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (producto.getPrecioCosto() <= 0) {
            JOptionPane.showMessageDialog(null, "El precio de Costo debe ser mayor que cero", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (producto.getPrecioVenta() <= 0) {
            JOptionPane.showMessageDialog(null, "El precio de Venta debe ser mayor que cero", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (producto.getDescuentoPorcentual() < 0){
            JOptionPane.showMessageDialog(null, " El descuento porcentual debe ser mayor que cero", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;

        }else if (producto.getDescuentoPorcentual() == 0){
            producto.setDescuentoPorcentual(0);
        }
        return productoDao.agregarProducto(producto);
    }

    // Método para obtener todos los productos
    public List<ProductoModel> obtenerProductos() {
        return productoDao.obtenerTodosLosProductos();
    }

    // Método para obtener un producto
    public ProductoModel obtenerProductoPorNombre(int idProducto) {
        return productoDao.obtenerProductoPorId(idProducto);
    }

    // Método para eliminar un producto
    public boolean eliminarProducto(int productoId) {
        boolean resultado = productoDao.eliminarProducto(productoId);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Producto eliminado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al eliminar el producto", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
    }
    
    public boolean actualizarProducto(ProductoModel producto,int IdProducto) {
        if (producto.getNombreProducto() == null || producto.getNombreProducto().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre del producto es requerido", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (producto.getPrecioCosto() <= 0) {
            JOptionPane.showMessageDialog(null, "El precio de Costo debe ser mayor que cero", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (producto.getPrecioVenta() <= 0) {
            JOptionPane.showMessageDialog(null, "El precio de Venta debe ser mayor que cero", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (producto.getDescuentoPorcentual() < 0){
            JOptionPane.showMessageDialog(null, " El descuento porcentual debe ser mayor que cero", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
            
        }else if (producto.getDescuentoPorcentual() == 0){
            producto.setDescuentoPorcentual(0);
        }
        return  productoDao.actualizarProducto(producto,IdProducto);
    }

    public DefaultTableModel getProductsTable() {
        List<ProductoModel> models = exceptionListener == null
                                    ? productoDao.obtenerTodosLosProductos()
                                    : productoDao.obtenerTodosLosProductos(exceptionListener);
        TableDataModel<ProductoModel> dataModel = new TableDataModel<>(new String[] {
                "ID", "Producto", "Costo", "Venta", "Descuento", "Tipo Producto"
        });
        for (final ProductoModel m : models) {
            dataModel.addRow(m, (ProductoModel model) -> {
                return new Object[] {
                        model.getIdProducto(),
                        model.getNombreProducto(),
                        model.getPrecioCosto(),
                        model.getPrecioVenta(),
                        model.getDescuentoPorcentual(),
                        model.getTipoProducto().getNombreTipoProducto()
                };
            });
        }
        return dataModel;
    }
}