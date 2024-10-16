/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.wass.models.purchase;

/**
 *
 * @author SamuelQ
 */
public class EstadoCompraModel {
    private int idEstadoCompra;
    private String nombreEstadoCompra;
    private boolean Estado;

    public EstadoCompraModel(String nombreEstadoCompra) {
        this.nombreEstadoCompra = nombreEstadoCompra;
    }

    public int getIdEstadoCompra() {
        return idEstadoCompra;
    }

    public void setIdEstadoCompra(int idEstadoCompra) {
        this.idEstadoCompra = idEstadoCompra;
    }

    public String getNombreEstadoCompra() {
        return nombreEstadoCompra;
    }

    public void setNombreEstadoCompra(String nombreEstadoCompra) {
        this.nombreEstadoCompra = nombreEstadoCompra;
    }

    public boolean isEstado() {
        return Estado;
    }

    public void setEstado(boolean estado) {
        Estado = estado;
    }

    @Override
    public String toString() {
        return nombreEstadoCompra; // Esto es importante para mostrar el nombre en el ComboBox
    }
}
