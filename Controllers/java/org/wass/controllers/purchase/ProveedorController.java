package org.wass.controllers.purchase;

/**
 *
 * @author SamuelQ
 */
import javax.swing.JOptionPane;
import java.util.List;

import org.wass.models.purchase.ProveedorDAO;
import org.wass.models.purchase.ProveedorModel;

public class ProveedorController {
    private ProveedorDAO proveedorDao;

    // Constructor para inicializar productoDao
    public ProveedorController(ProveedorDAO proveedorDao) {
        this.proveedorDao = proveedorDao;
    }


    // Método para agregar un nuevo proveedor
    public boolean agregarProveedor(ProveedorModel proveedor) {
        if (proveedor.getNombreComercial() == null || proveedor.getNombreComercial().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre comercial es requerido", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (proveedor.getTelefono2() == null || proveedor.getTelefono2().isEmpty()) {
            proveedor.setTelefono2("");
        }
        if (proveedor.getCorreo() == null || proveedor.getCorreo().isEmpty()) {
            proveedor.setCorreo("");
        }
        if (proveedor.getIdPersona() <= 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una persona", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        boolean resultado = proveedorDao.agregarProveedor(proveedor);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Proveedor agregado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al agregar el proveedor", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
    }


    // Método para obtener todos los proveedores
    public List<ProveedorModel> obtenerProveedores() {
        return proveedorDao.obtenerProveedores();
    }

    // Método para obtener un proveedor
    public ProveedorModel obtenerProveedorPorId(int idProducto) {
        return proveedorDao.obtenerProveedorPorId(idProducto);
    }


    // Método para eliminar un proveedor
    public boolean eliminarProveedor(int productoId) {
        boolean resultado = proveedorDao.eliminarProveedor(productoId);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Proveedor eliminado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al eliminar el proveedor", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
    }


    // Método para actualizar un Proveedor
    public boolean actualizarProveedor(ProveedorModel proveedor,int IdProveedor) {
        if (proveedor.getNombreComercial() == null || proveedor.getNombreComercial().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre comercial es requerido", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (proveedor.getTelefono2() == null || proveedor.getTelefono2().isEmpty()) {
            proveedor.setTelefono2("");
        }
        if (proveedor.getCorreo() == null || proveedor.getCorreo().isEmpty()) {
            proveedor.setCorreo("");
        }
        if (proveedor.getIdPersona() <= 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una persona", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        boolean resultado = proveedorDao.actualizarProveedor(proveedor,IdProveedor);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Proveedor actualizado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al actualizar el proveedor", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
    }

    //Método para obtener un proveedor por su nombre
    public ProveedorModel obtenerProveedorPorNombre(String nombreProveedor) {
        return proveedorDao.obtenerProveedorPorNombre(nombreProveedor);
    }
}