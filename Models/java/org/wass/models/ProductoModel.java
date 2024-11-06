package org.wass.models;

/**
 * @author SamuelQ
 */
public class ProductoModel implements Model {
    private int idProducto;
    private String nombreProducto;
    private float precioCosto;
    private float precioVenta;
    private float descuentoPorcentual;
    private TipoProductoModel tipoProducto;
    private boolean estado;

    public ProductoModel() { }
    public ProductoModel(String nombreProducto, float precioCosto, float precioVenta, float descuentoPorcentual, TipoProductoModel tipoProducto) {
        this.nombreProducto = nombreProducto;
        this.precioCosto = precioCosto;
        this.precioVenta = precioVenta;
        this.descuentoPorcentual = descuentoPorcentual;
        this.tipoProducto = tipoProducto;
    }

    @Override
    public int getId() { return idProducto; }

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

    public TipoProductoModel getTipoProducto() {
        return tipoProducto;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void setTipoProducto(TipoProductoModel tipoProductoModel) { this.tipoProducto = tipoProductoModel; }

    @Override
    public String toString() {
        return nombreProducto;
    }
}
