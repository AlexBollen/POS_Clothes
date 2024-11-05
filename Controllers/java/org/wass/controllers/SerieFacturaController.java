package org.wass.controllers;

import org.wass.models.dao.SerieFacturaDAO;
import org.wass.models.SerieFacturaModel;

import java.util.List;

/**
 * @author Alex
 */

public class SerieFacturaController {
    private SerieFacturaDAO serieDao;

    public SerieFacturaController() {
        this(new SerieFacturaDAO());
    }
    @Deprecated
    public SerieFacturaController(SerieFacturaDAO serieDao) { this.serieDao = serieDao; }

    public List<SerieFacturaModel> obtenerSeriesFactura() {
        return serieDao.obtenerSeriesFactura();
    }
}
