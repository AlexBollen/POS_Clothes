package org.wass.models;

import java.util.Date;

/**
 * @author SamuelQ
 */
public class SerieFacturaModel {
    private String idSerie;
    private Date fechaInicio;
    private boolean estado;

    public SerieFacturaModel(String idSerie, Date fechaInicio) {
        this.idSerie = idSerie;
        this.fechaInicio = fechaInicio;
    }

    public String getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(String idSerie) {
        this.idSerie = idSerie;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() { return idSerie; }
}
