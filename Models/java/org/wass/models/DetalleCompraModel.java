package org.wass.models;

/**
 *
 * @author SamuelQ
 */
public class DetalleCompraModel {
    private int idDetalleCompra;
    private int idCompra;
    private int idProducto;
    private int cantidadProducto;
    private boolean estado;

    public DetalleCompraModel(int idCompra, int idProducto, int cantidadProducto) {
        this.idCompra = idCompra;
        this.idProducto = idProducto;
        this.cantidadProducto = cantidadProducto;
    }

    public int getIdDetalleCompra() {
        return idDetalleCompra;
    }

    public void setIdDetalleCompra(int idDetalleCompra) {
        this.idDetalleCompra = idDetalleCompra;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
}
