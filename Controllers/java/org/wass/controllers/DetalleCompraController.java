package org.wass.controllers;

import java.util.List;

import org.wass.models.DetalleCompraModel;
import org.wass.models.dao.DetalleCompraDAO;

/**
 * @author SamuelQ
 */
public class DetalleCompraController {
    private DetalleCompraDAO detalleCompraDao;

    public DetalleCompraController() {
        this.detalleCompraDao = new DetalleCompraDAO();
    }

    public DetalleCompraController(DetalleCompraDAO detalleCompraDao) {
        this.detalleCompraDao = detalleCompraDao;
    }

    public List<DetalleCompraModel> obtenerDetallesPorCompra(int idCompra) {
        return detalleCompraDao.obtenerDetallesPorCompra(idCompra);
    }
    public List<DetalleCompraModel> obtenerTodosLosDetalles() {
        return detalleCompraDao.obtenerTodosLosDetalles();
    }
}
