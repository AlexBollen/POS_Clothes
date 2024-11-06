package org.wass.models;

import java.util.Date;

/**
 * @author AlexBollen
 */
public class FacturaListModel implements Model {
    private int idFactura;
    private String noFactura;
    private double totalFactura;
    private String nombreCliente;
    private Date fechaFactura;
    private String idSerie;

    public FacturaListModel() { }
    public FacturaListModel(int idFactura, String noFactura, double totalFactura, String nombreCliente, Date fechaFactura, String idSerie) {
        this.idFactura = idFactura;
        this.noFactura = noFactura;
        this.totalFactura = totalFactura;
        this.nombreCliente = nombreCliente;
        this.fechaFactura = fechaFactura;
        this.idSerie = idSerie;
    }

    @Override
    public int getId() { return idFactura; }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public String getNoFactura() {
        return noFactura;
    }

    public void setNoFactura(String noFactura) {
        this.noFactura = noFactura;
    }

    public double getTotalFactura() {
        return totalFactura;
    }

    public void setTotalFactura(double totalFactura) {
        this.totalFactura = totalFactura;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public String getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(String idSerie) {
        this.idSerie = idSerie;
    }
}
