package org.wass.models.product;

/**
 *
 * @author SamuelQ
 */
public class ProductoCantidadModel extends ProductoModel{
    private int cantidadProductos;

    public ProductoCantidadModel(String nombreProducto, float precioCosto, float precioVenta, float descuentoPorcentual, int idTipoProducto, int cantidadProductos) {
        super(nombreProducto, precioCosto, precioVenta, descuentoPorcentual, idTipoProducto);
        this.cantidadProductos = cantidadProductos;
    }

    public int getCantidad() {
        return cantidadProductos;
    }

    public void setCantidad(int cantidad) {
        this.cantidadProductos = cantidad;
    }
    @Override
    public String toString() {
        return getNombreProducto() + " - Cantidad: " + cantidadProductos;
    }
}
