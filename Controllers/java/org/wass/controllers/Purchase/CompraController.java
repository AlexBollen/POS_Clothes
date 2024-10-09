package org.wass.controllers.Purchase;

/**
 *
 * @author SamuelQ
 */
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.List;

import org.wass.models.purchase.CompraModel;
import org.wass.models.purchase.CompraDAO;
import org.wass.models.purchase.DetalleCompraModel;
import org.wass.models.purchase.DetalleCompraDAO;

public class CompraController {
    private CompraDAO compraDao;
    private DetalleCompraDAO detalleCompraDao;

    // Constructor para inicializar compraDao
    public CompraController(CompraDAO compraDao, DetalleCompraDAO detalleCompraDao) {
        this.compraDao = compraDao;
        this.detalleCompraDao = detalleCompraDao;
    }

    // Método para agregar una compra
    public boolean agregarCompra(CompraModel compra,List<DetalleCompraModel> detallesCompra) throws SQLException {
        if (compra.getDescripcionCompra() == null || compra.getDescripcionCompra().isEmpty()) {
            JOptionPane.showMessageDialog(null, "La descripciòn de la compra es requerida", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (compra.getCantidadPedida() <= 0) {
            JOptionPane.showMessageDialog(null, "La cantidad pedida debe ser mayor que cero", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (compra.getCantidadRecibida() <= 0) {
            JOptionPane.showMessageDialog(null, "La cantidad recibida debe ser mayor que cero", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (compra.getTotalCompra() <= 0) {
            JOptionPane.showMessageDialog(null, "El total de la compra debe ser mayor que cero", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (compra.getIdProveedor() <= 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un proveedor", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (compra.getIdTipoPago() <= 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de pago", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (compra.getIdEstadoCompra() <= 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un estado de compra", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        boolean resultado = compraDao.agregarCompra(compra,detallesCompra);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Compra agregada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al agregar la compra", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
    }

    // Método para obtener todas las compras
    public List<CompraModel> obtenerTodasLasCompras() {
        return compraDao.obtenerTodasLasCompras();
    }

    // Método para obtener una compra
    public CompraModel obtenerCompraPorId(int idCompra) {
        return compraDao.obtenerCompraPorId(idCompra);
    }

    // Método para eliminar una compra
    public boolean eliminarCompra(int compraId) {
        boolean resultado = compraDao.eliminarCompra(compraId);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Compra eliminada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al eliminar compra", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
    }

    public boolean actualizarCompra(CompraModel compra,int IdCompra) {
        if (compra.getDescripcionCompra() == null || compra.getDescripcionCompra().isEmpty()) {
            JOptionPane.showMessageDialog(null, "La descripciòn de la compra es requerida", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (compra.getCantidadPedida() <= 0) {
            JOptionPane.showMessageDialog(null, "La cantidad pedida debe ser mayor que cero", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (compra.getCantidadRecibida() <= 0) {
            JOptionPane.showMessageDialog(null, "La cantidad recibida debe ser mayor que cero", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (compra.getTotalCompra() <= 0) {
            JOptionPane.showMessageDialog(null, "El total de la compra debe ser mayor que cero", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (compra.getIdProveedor() <= 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un proveedor", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (compra.getIdTipoPago() <= 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de pago", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (compra.getIdEstadoCompra() <= 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un estado de compra", "Requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        boolean resultado = compraDao.actualizarCompra(compra,IdCompra);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Compra actualizada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al actualizar compra", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
    }
}
