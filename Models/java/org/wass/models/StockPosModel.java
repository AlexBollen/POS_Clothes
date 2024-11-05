package org.wass.models;

/**
 * @author Alex
 */
public class StockPosModel {
    private int idStock;
    private String nombreProducto;
    private String tipoProducto;
    private int cantidadDisponible;
    private double precioVenta;
    private String ubicacionBodega;

    public StockPosModel(int idStock, String nombreProducto, String tipoProducto, int cantidadDisponible, double precioVenta, String ubicacionBodega) {
        this.idStock = idStock;
        this.nombreProducto = nombreProducto;
        this.tipoProducto = tipoProducto;
        this.cantidadDisponible = cantidadDisponible;
        this.precioVenta = precioVenta;
        this.ubicacionBodega = ubicacionBodega;
    }

    public int getIdStock() {
        return idStock;
    }

    public void setIdStock(int idStock) {
        this.idStock = idStock;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getUbicacionBodega() {
        return ubicacionBodega;
    }

    public void setUbicacionBodega(String ubicacionBodega) {
        this.ubicacionBodega = ubicacionBodega;
    }
}
