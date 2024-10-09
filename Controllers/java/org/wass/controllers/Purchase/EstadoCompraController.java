package org.wass.controllers.Purchase;

import org.wass.models.purchase.EstadoCompraModel;
import java.util.List;
import javax.swing.JOptionPane;
import org.wass.models.purchase.EstadoCompraDAO;
import org.wass.models.purchase.ProveedorModel;

/**
 *
 * @author SamuelQ
 */

public class EstadoCompraController {
    private EstadoCompraDAO estadoCompraDao;

    public EstadoCompraController(EstadoCompraDAO estadoCompraDao) {
        this.estadoCompraDao = estadoCompraDao;
    }

    // Obtener una lista de los nombres de estados de compra
    public List<EstadoCompraModel> obtenerEstadosCompra() {
        return estadoCompraDao.obtenerEstadosCompra();
    }

    // Obtener un estado de compra por su ID
    public List<EstadoCompraModel> obtenerUnEstadoCompraPorId(int idEstadoCompra) {
        return estadoCompraDao.obtenerUnEstadoCompraPorId(idEstadoCompra);
    }
    // Obtener el nombre de un estado de compra por su ID
    public String obtenerNombreUnEstadoCompraPorId(int idEstadoCompra) {
        return estadoCompraDao.obtenerNombreUnEstadoCompraPorId(idEstadoCompra);
    }
    //Método para obtener un estado de compra por su nombre
    public EstadoCompraModel obtenerEstadoCompraPorNombre(String nombreEstadoCompra) {
        return estadoCompraDao.obtenerEstadoCompraPorNombre(nombreEstadoCompra);
    }
}
