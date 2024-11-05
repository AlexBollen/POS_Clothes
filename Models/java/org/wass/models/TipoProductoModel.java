package org.wass.models;

/**
 * @author SamuelQ
 */
public class TipoProductoModel {
    private int idTipoProducto;
    private String nombreTipoProducto;
    private boolean estado;

    public TipoProductoModel(String nombreTipoProducto, boolean estado) {
        this.nombreTipoProducto = nombreTipoProducto;
        this.estado = estado;
    }

    // Getters y Setters
    public int getIdTipoProducto() {
        return idTipoProducto;
    }

    public void setIdTipoProducto(int idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
    }

    public String getNombreTipoProducto() {
        return nombreTipoProducto;
    }

    public void setNombreTipoProducto(String nombreTipoProducto) {
        this.nombreTipoProducto = nombreTipoProducto;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return nombreTipoProducto; // Esto es importante para mostrar el nombre en el ComboBox
    }
}
