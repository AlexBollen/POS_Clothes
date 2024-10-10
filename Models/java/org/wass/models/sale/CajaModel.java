/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.wass.models.sale;
import java.util.Date;
/**
 *
 * @author SamuelQ
 */
public class CajaModel {
    private int idCaja;
    private float montoInicial;
    private float monto;
    private boolean estadoCaja;
    private Date fechaApertura;
    private boolean estado;
    private int idUsuario;

    public CajaModel(float montoInicial, float monto, boolean estadoCaja, int idUsuario) {
        this.montoInicial = montoInicial;
        this.monto = monto;
        this.estadoCaja = estadoCaja;
        this.idUsuario = idUsuario;
    }

    public int getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(int idCaja) {
        this.idCaja = idCaja;
    }

    public float getMontoInicial() {
        return montoInicial;
    }

    public void setMontoInicial(float montoInicial) {
        this.montoInicial = montoInicial;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public boolean getEstadoCaja() {
        return estadoCaja;
    }

    public void setEstadoCaja(boolean estadoCaja) {
        this.estadoCaja = estadoCaja;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

}
