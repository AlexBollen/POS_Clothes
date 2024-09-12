/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.wass.models.Sales;
import java.util.Date;
/**
 *
 * @author SamuelQ
 */
public class SerieFacturaModel {
    private String idSerie;
    private Date fechaInicio;
    private boolean estado;

    public SerieFacturaModel(Date fechaInicio, boolean estado) {
        this.fechaInicio = fechaInicio;
        this.estado = estado;
    }

    public String getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(String idSerie) {
        this.idSerie = idSerie;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
}
