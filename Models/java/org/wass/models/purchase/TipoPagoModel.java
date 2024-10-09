/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.wass.models.purchase;

/**
 *
 * @author SamuelQ
 */
public class TipoPagoModel {
    private int idTipoPago;
    private String nombreTipoPago;
    private boolean estado;

    public TipoPagoModel(String nombreTipoPago) {
        this.nombreTipoPago = nombreTipoPago;
    }

    public int getIdTipoPago() {
        return idTipoPago;
    }

    public void setIdTipoPago(int idTipoPago) {
        this.idTipoPago = idTipoPago;
    }

    public String getNombreTipoPago() {
        return nombreTipoPago;
    }

    public void setNombreTipoPago(String nombreTipoPago) {
        this.nombreTipoPago = nombreTipoPago;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return nombreTipoPago; // Esto es importante para mostrar el nombre en el ComboBox
    }
}
