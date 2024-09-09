/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.wass.models.Sales;
import java.util.Date;
/**
 *
 * @author SamuelQ
 */
public class FacturaModel {
    private int idFactura;
    private String noFactura;
    private float totalFactura;
    private Date fechaFactura;
    private boolean estado;
    private int idCliente;
    private int idTipoPago;
    private int idCaja;
    private String idSerie;

    public FacturaModel(int idFactura, String noFactura, float totalFactura, Date fechaFactura, boolean estado, int idCliente, int idTipoPago, int idCaja, String idSerie) {
        this.idFactura = idFactura;
        this.noFactura = noFactura;
        this.totalFactura = totalFactura;
        this.fechaFactura = fechaFactura;
        this.estado = estado;
        this.idCliente = idCliente;
        this.idTipoPago = idTipoPago;
        this.idCaja = idCaja;
        this.idSerie = idSerie;
    }

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

    public float getTotalFactura() {
        return totalFactura;
    }

    public void setTotalFactura(float totalFactura) {
        this.totalFactura = totalFactura;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdTipoPago() {
        return idTipoPago;
    }

    public void setIdTipoPago(int idTipoPago) {
        this.idTipoPago = idTipoPago;
    }

    public int getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(int idCaja) {
        this.idCaja = idCaja;
    }

    public String getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(String idSerie) {
        this.idSerie = idSerie;
    }

    
}
