package org.wass.models;

import java.util.Date;

/**
 * @author SamuelQ
 */
public class CajaModel implements Model {
    
    private int id;
    private int idUsuario;
    
    private float montoInicial;
    private float monto;
    
    private Date fechaApertura;
    
    private boolean estadoCaja;
    private boolean estado;

    /**
     * Constructor del modelo <code>Caja</code>.
     */
    public CajaModel() { }
    @Deprecated
    public CajaModel(float montoInicial, float monto, boolean estadoCaja, int idUsuario) {
        this.montoInicial = montoInicial;
        this.monto        = monto;
        this.estadoCaja = estadoCaja;
        this.idUsuario  = idUsuario;
    }

    //=== ------------------------------------------------------------------ ===
    //===                           SETTERS
    //=== ------------------------------------------------------------------ ===    
    public void setId(int id) {
        this.id = id;
    }
    @Deprecated
    public void setIdCaja(int idCaja) {
        this.setId(idCaja);
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public void setMontoInicial(float montoInicial) {
        this.montoInicial = montoInicial;
    }
    public void setMonto(float monto) {
        this.monto = monto;
    }
    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }
    public void setEstadoCaja(boolean estadoCaja) {
        this.estadoCaja = estadoCaja;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    //=== ------------------------------------------------------------------ ===
    //===                           GETTERS
    //=== ------------------------------------------------------------------ ===    
    public int getIdUsuario() {
        return idUsuario;
    }
    public float getMontoInicial() {
        return montoInicial;
    }
    public float getMonto() {
        return monto;
    }
    public Date getFechaApertura() {
        return fechaApertura;
    }
    public boolean isEstadoCaja() {
        return estadoCaja;
    }
    public boolean isEstado() {
        return estado;
    }
    @Override
    public int getId() {
        return id;
    }
    @Deprecated
    public int getIdCaja() {
        return getId();
    }
    @Deprecated
    public boolean getEstadoCaja() {
        return isEstadoCaja();
    }
}
