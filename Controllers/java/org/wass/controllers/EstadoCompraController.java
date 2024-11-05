package org.wass.controllers;

import org.wass.models.EstadoCompraModel;
import java.util.List;

import org.wass.models.dao.EstadoCompraDAO;

/**
 *
 * @author SamuelQ
 */

public class EstadoCompraController {
    private EstadoCompraDAO estadoCompraDao;

    public EstadoCompraController() {
        this(new EstadoCompraDAO());
    }
    @Deprecated
    public EstadoCompraController(EstadoCompraDAO estadoCompraDao) {
        this.estadoCompraDao = estadoCompraDao;
    }

    // Obtener una lista de los nombres de estados de compra
    public List<EstadoCompraModel> obtenerEstadosCompra() {
        return estadoCompraDao.obtenerEstadosCompra();
    }

    // Obtener un estado de compra por su ID
    public EstadoCompraModel obtenerUnEstadoCompraPorId(int idEstadoCompra) {
        return estadoCompraDao.obtenerUnEstadoCompraPorId(idEstadoCompra);
    }
    //Método para obtener un estado de compra por su nombre
    public EstadoCompraModel obtenerEstadoCompraPorNombre(String nombreEstadoCompra) {
        return estadoCompraDao.obtenerEstadoCompraPorNombre(nombreEstadoCompra);
    }
}
