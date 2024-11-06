package org.wass.models;

/**
 * @author SamuelQ
 */
public class ProductoModel {
    private int idProducto;
    private String nombreProducto;
    private float precioCosto;
    private float precioVenta;
    private float descuentoPorcentual;
    private int idTipoProducto;
    private boolean estado;

    public ProductoModel(String nombreProducto, float precioCosto, float precioVenta, float descuentoPorcentual, int idTipoProducto) {
        this.nombreProducto = nombreProducto;
        this.precioCosto = precioCosto;
        this.precioVenta = precioVenta;
        this.descuentoPorcentual = descuentoPorcentual;
        this.idTipoProducto = idTipoProducto;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public float getPrecioCosto() {
        return precioCosto;
    }

    public void setPrecioCosto(float precioCosto) {
        this.precioCosto = precioCosto;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public float getDescuentoPorcentual() {
        return descuentoPorcentual;
    }

    public void setDescuentoPorcentual(float descuentoPorcentual) {
        this.descuentoPorcentual = descuentoPorcentual;
    }

    public int getIdTipoProducto() {
        return idTipoProducto;
    }

    public void setIdTipoProducto(int idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return nombreProducto;
    }
}
