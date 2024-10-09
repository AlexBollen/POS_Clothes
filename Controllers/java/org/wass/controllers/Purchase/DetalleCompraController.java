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

public class DetalleCompraController {
    private DetalleCompraDAO detalleCompraDao;

    public DetalleCompraController(DetalleCompraDAO detalleCompraDao) {
        this.detalleCompraDao = detalleCompraDao;
    }

    // Obtener los detalles de una compra específica
    public List<DetalleCompraModel> obtenerDetallesPorCompra(int idCompra) {
        return detalleCompraDao.obtenerDetallesPorCompra(idCompra);
    }
}
