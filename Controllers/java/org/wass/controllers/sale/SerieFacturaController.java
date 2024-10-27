package org.wass.controllers.sale;

import org.wass.models.sale.SerieFacturaDao;
import org.wass.models.sale.SerieFacturaModel;

import java.util.List;

/**
 * @author Alex
 */

public class SerieFacturaController {
    private SerieFacturaDao serieDao;

    public SerieFacturaController(SerieFacturaDao serieDao) { this.serieDao = serieDao; }

    public List<SerieFacturaModel> obtenerSeriesFactura() {
        return serieDao.obtenerSeriesFactura();
    }
}
