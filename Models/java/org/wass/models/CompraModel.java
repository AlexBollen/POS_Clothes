package org.wass.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author SamuelQ
 */
public class CompraModel {
    private int idCompra;
    private String descripcionCompra;
    private int cantidadPedida;
    private int cantidadRecibida;
    private float totalCompra;
    private Date fechaCompra;
    private boolean estado;
    private int idProveedor;
    private int idTipoPago;
    private int idEstadoCompra;
    private List<DetalleCompraModel> detalleCompra;

    public CompraModel() {
    }
    @Deprecated
    public CompraModel 
     (
            String descripcionCompra, 
            int cantidadPedida, 
            float totalCompra, 
            int idProveedor, 
            int idTipoPago
     ) {
        this.descripcionCompra = descripcionCompra;
        this.cantidadPedida = cantidadPedida;
        this.totalCompra = totalCompra;
        this.idProveedor = idProveedor;
        this.idTipoPago = idTipoPago;
        this.detalleCompra = new ArrayList<>(); // Inicializa la lista
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public String getDescripcionCompra() {
        return descripcionCompra;
    }

    public void setDescripcionCompra(String descripcionCompra) {
        this.descripcionCompra = descripcionCompra;
    }

    public int getCantidadPedida() {
        return cantidadPedida;
    }

    public void setCantidadPedida(int cantidadPedida) {
        this.cantidadPedida = cantidadPedida;
    }

    public int getCantidadRecibida() {
        return cantidadRecibida;
    }

    public void setCantidadRecibida(int cantidadRecibida) {
        this.cantidadRecibida = cantidadRecibida;
    }

    public float getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(float totalCompra) {
        this.totalCompra = totalCompra;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getIdTipoPago() {
        return idTipoPago;
    }

    public void setIdTipoPago(int idTipoPago) {
        this.idTipoPago = idTipoPago;
    }

    public int getIdEstadoCompra() {
        return idEstadoCompra;
    }

    public void setIdEstadoCompra(int idEstadoCompra) {
        this.idEstadoCompra = idEstadoCompra;
    }

    public List<DetalleCompraModel> getDetalleCompra() {
        return detalleCompra;
    }

    public void setDetalleCompra(List<DetalleCompraModel> detalleCompra) {
        this.detalleCompra = detalleCompra;
    }
}
