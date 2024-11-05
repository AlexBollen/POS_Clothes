package org.wass.controllers;

import javax.swing.JOptionPane;
import java.util.List;
import org.wass.models.dao.ProductoDAO;
import org.wass.models.ProductoModel;

/**
 * @author SamuelQ
 */
public class ProductoController {
    private ProductoDAO productoDao;

    // Constructor para inicializar productoDao    
    public ProductoController() {
        this(new ProductoDAO());
    }
    @Deprecated
    public ProductoController(ProductoDAO productoDao) {
        this.productoDao = productoDao;
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
        boolean resultado = productoDao.agregarProducto(producto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Producto agregado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al agregar el producto", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
    }

    // Método para obtener todos los productos
    public List<ProductoModel> obtenerProductos() {
        return productoDao.obtenerTodosLosProductos();
    }

    // Método para obtener un producto
    public ProductoModel obtenerProductoPorNombre(int idProducto) {
        return productoDao.obtenerProductoPorId(idProducto);
    }

    // Método para obtener un producto
    public ProductoModel obtenerProductoPorNombre(String nombreProducto) {
        return productoDao.obtenerDatosDeProductosPorNombre(nombreProducto);
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
        boolean resultado = productoDao.actualizarProducto(producto,IdProducto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Producto actualizado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al actualizar el producto", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
    }
}