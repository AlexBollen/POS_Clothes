package org.wass.controllers.Purchase;
/**
 *
 * @author SamuelQ
 */
import java.util.List;

import org.wass.models.purchase.DetalleCompraModel;
import org.wass.models.purchase.DetalleCompraDAO;

public class DetalleCompraController {
    private DetalleCompraDAO detalleCompraDao;

    public DetalleCompraController(DetalleCompraDAO detalleCompraDao) {
        this.detalleCompraDao = detalleCompraDao;
    }

    // Obtener los detalles de una compra específica
    public List<DetalleCompraModel> obtenerDetallesPorCompra(int idCompra) {
        return detalleCompraDao.obtenerDetallesPorCompra(idCompra);
    }
    public List<DetalleCompraModel> obtenerTodosLosDetalles() {
        return detalleCompraDao.obtenerTodosLosDetalles();
    }
}
